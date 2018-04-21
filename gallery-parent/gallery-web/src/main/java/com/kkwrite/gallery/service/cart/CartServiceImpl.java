package com.kkwrite.gallery.service.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.cart.CartProductMapper;
import com.kkwrite.gallery.mapper.cart.GlyCartMapper;
import com.kkwrite.gallery.mapper.cart.GlyRProductCartMapper;
import com.kkwrite.gallery.mapper.product.GlyRProductPropCarMapper;
import com.kkwrite.gallery.mapper.user.GlyUserMapper;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.cart.GlyCart;
import com.kkwrite.gallery.pojo.cart.GlyRProductCartKey;
import com.kkwrite.gallery.pojo.product.GlyRProductPropCarKey;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.pojo.user.GlyUser;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyUserMapper glyUserMapper;
	@Autowired
	private GlyCartMapper glyCartMapper;
	@Autowired
	private GlyRProductCartMapper glyRProductCartMapper;
	@Autowired
	private CartProductMapper cartProductMapper;
	@Autowired
	private GlyRProductPropCarMapper glyRProductPropCarMapper;
	
	@Override
	public Product queryCartProduct(String username, int productId) throws ServiceException {
		List<Product> products = queryCartProducts(username);
		if (products == null || products.size() <= 0) {
			return null;
		}
		
		for (Product product: products) {
			Integer tmpProductId = product.getProductId(); 
			if (tmpProductId != null && tmpProductId.intValue() == productId) {
				return product;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Product> queryCartProducts(String username) throws ServiceException {
		logger.debug("[ begin ] CartServiceImpl.queryCartProduct(), username = " + username);
		
		// 查询用户购物车
		GlyCart userCart = queryUserCart(username);
		if (userCart == null) {
			return null;
		}
		
		// 查询购物车里产品
		List<Product> products = cartProductMapper.selectCartProducts(userCart);
		logger.debug("[ run ] CartServiceImpl.queryCartProduct(), products=" + products);
		
		logger.debug("[ end ] CartServiceImpl.queryCartProduct().");
		return products;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public boolean addToCart(String username, int productId, int[] propIds, int productNum) throws ServiceException {
		
		try {
			// 查找购物车
			GlyCart glyCart = queryUserCart(username);
			if (glyCart == null) {
				glyCart = createCart(username);
			}
			
			// 插入购物车产品表
			createCartProduct(glyCart.getCartCode(), productId, productNum);
			// 插入购物车产品产品属性表
			saveCartProductProps(glyCart.getCartCode(), productId, propIds);
			
			return true;
		} catch (Exception e) {
			logger.error("[ run ] CartServiceImpl.addToCart(), 添加产品到购物车出错！");
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public boolean removeFromCart(String username, int[] productIds) throws ServiceException {
		try {
			// 查找购物车
			GlyCart glyCart = queryUserCart(username);
			if (glyCart == null) {
				throw new ServiceException("用户的购物车不存在！");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cartCode", glyCart.getCartCode());
			params.put("productIds", productIds);
			int result = glyRProductCartMapper.deleteProducts(params);
			if (result <= 0) {
				throw new ServiceException("从购物车删除产品失败！");
			}
			
			if (productIds != null) {
				for (int i = 0; i < productIds.length; i++) {
					GlyRProductPropCarKey glyRProductPropCarKey = new GlyRProductPropCarKey();
					glyRProductPropCarKey.setCartCode(glyCart.getCartCode());
					glyRProductPropCarKey.setProductId(productIds[i]);
					List<GlyRProductPropCarKey> glyRProductPropCarKeys = glyRProductPropCarMapper.selectSelective(glyRProductPropCarKey);
					if (glyRProductPropCarKeys != null && glyRProductPropCarKeys.size() > 0) {
						for (int j = 0; j < glyRProductPropCarKeys.size(); j++) {
							int deleteNum = glyRProductPropCarMapper.deleteByPrimaryKey(glyRProductPropCarKeys.get(j));
							if (deleteNum != 1) {
								throw new ServiceException("删除选中的产品属性出错，请稍后重试！");
							}
						}
					}
				}
			}
			
			return true;
		} catch (Exception e) {
			String msg = e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			throw new ServiceException(msg);
		}
	}

	/**
	 * 查询用户的购物车
	 * @return
	 */
	private GlyCart queryUserCart(String username) throws ServiceException {
		GlyUser param = new GlyUser();
		param.setUsername(username);
		List<GlyUser> users = glyUserMapper.selectSelective(param);
		if (users == null || users.size() <= 0) {
			throw new ServiceException("没有查询到用户名为 " + username + " 的用户");
		}
		if (users.size() > 1) {
			throw new ServiceException("查询到用户名为 " + username + " 的用户不只一个");
		}
		
		return glyCartMapper.selectByUserId(users.get(0).getUserId());
	}
	
	/**
	 * 创建购物车
	 * @param userId
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	private GlyCart createCart(String username) throws ServiceException {
		GlyUser param = new GlyUser();
		param.setUsername(username);
		List<GlyUser> users = glyUserMapper.selectSelective(param);
		if (users == null || users.size() <= 0) {
			throw new ServiceException("没有查询到用户名为 " + username + " 的用户");
		}
		if (users.size() > 1) {
			throw new ServiceException("查询到用户名为 " + username + " 的用户不只一个");
		}
		
		GlyCart glyCart = new GlyCart();
		glyCart.setCartCode(String.valueOf(System.currentTimeMillis()));
		glyCart.setUserId(users.get(0).getUserId());
		glyCart.setIsValid(BasePojo.IS_VALID_Y);
		int result = glyCartMapper.insertSelective(glyCart);
		if (result != 1) {
			throw new ServiceException("创建购物车失败！");
		}
		
		return glyCart;
	}
	
	/**
	 * 创建购物车里产品数据
	 * @param cartCode
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	private int createCartProduct(String cartCode, int productId, int productNum) throws ServiceException {
		GlyRProductCartKey glyRProductCartKey = new GlyRProductCartKey();
		glyRProductCartKey.setCartCode(cartCode);
		glyRProductCartKey.setProductId(productId);
		glyRProductCartKey.setProductNum(productNum);
		int result = glyRProductCartMapper.insert(glyRProductCartKey);
		if (result != 1) {
			throw new ServiceException("创建购物车产品数据出错！");
		}
		return result;
	}
	
	/**
	 * 加入购物车时，保存选中产品的属性
	 * @param cartCode
	 * @param productId
	 * @param propId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	private int saveCartProductProps(String cartCode, int productId, int[] propIds) throws ServiceException {
		if (propIds == null || propIds.length <= 0) {
			return 0;
		}
		
		try {
			int result = -1;
			GlyRProductPropCarKey glyRProductPropCarKey = new GlyRProductPropCarKey();
			glyRProductPropCarKey.setCartCode(cartCode);
			glyRProductPropCarKey.setProductId(productId);
			for (int i = 0; i < propIds.length; i++) {
				glyRProductPropCarKey.setPropId(propIds[i]);
				result = glyRProductPropCarMapper.insert(glyRProductPropCarKey);
				if (result != 1) {
					throw new ServiceException("插入购物车产品，产品属性出错");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = e.getMessage(); 
			logger.error(errMsg);
			throw new ServiceException(errMsg);
		}
		
		return 0;
	}

}
