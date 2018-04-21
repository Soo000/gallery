package com.kkwrite.gallery.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.product.GlyProductPictureMapper;
import com.kkwrite.gallery.mapper.product.GlyProductPropMapper;
import com.kkwrite.gallery.mapper.product.GlyRProductTypeProductMapper;
import com.kkwrite.gallery.mapper.product.ProductMapper;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.Pagination;
import com.kkwrite.gallery.pojo.product.GlyProductPicture;
import com.kkwrite.gallery.pojo.product.GlyProductProp;
import com.kkwrite.gallery.pojo.product.GlyProductType;
import com.kkwrite.gallery.pojo.product.GlyRProductTypeProductKey;
import com.kkwrite.gallery.pojo.product.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private GlyProductPropMapper glyProductPropMapper;
	@Autowired
	private GlyProductPictureMapper glyProductPictureMapper;
	@Autowired
	private GlyRProductTypeProductMapper glyRProductTypeProductMapper;
	
	@Override
	public Product queryProduct(int productId) throws ServiceException {
		// 查询产品
		Product product = productMapper.selectByPrimaryKey(productId);
		if (product == null) {
			return null;
		}
		
		// TODO 查询已选择产品分类
		
		// 查询产品配图
		GlyProductPicture param = new GlyProductPicture();
		param.setProductId(product.getProductId());
		param.setIsValid(BasePojo.IS_VALID_Y);
		List<GlyProductPicture> productPictures = queryProductPictures(param);
		product.setProductPictures(productPictures);
		
		// 查询产品属性
		List<GlyProductProp> productProps = queryProductProps(product.getProductId(), BasePojo.IS_VALID_Y);
		if (product != null) {
			product.setProductProps(productProps);
		}
		
		return product;
	}
	
	/**
	 * 查询 product
	 */
	@Override
	public List<Product> queryProduct(int[] productIds) throws ServiceException {
		List<Product> products = productMapper.selectProducts(productIds);
		if (products != null) {
			for (Product product: products) {
				// 查询产品配图
				GlyProductPicture param = new GlyProductPicture();
				param.setProductId(product.getProductId());
				param.setIsValid(BasePojo.IS_VALID_Y);
				List<GlyProductPicture> productPictures = queryProductPictures(param);
				product.setProductPictures(productPictures);
				
				// 查询产品属性
				List<GlyProductProp> productProps = queryProductProps(product.getProductId(), BasePojo.IS_VALID_Y);
				if (product != null) {
					product.setProductProps(productProps);
				}
			}
		}
		return products;
	}
	
	@Override
	public List<Product> queryProductPagination(List<GlyProductType> productTypes, Pagination pagination) throws ServiceException {
		List<Product> products = null;
		if (productTypes == null || productTypes.size() <= 0) {
			products = productMapper.selectAllProduct(BasePojo.IS_VALID_Y);
		} else {
			// 查询一组产品类型关系
			List<GlyRProductTypeProductKey> rProductTypeProducts = glyRProductTypeProductMapper.selectProductIdsByProductTypes(productTypes);
			if (rProductTypeProducts != null && rProductTypeProducts.size() > 0) {
				int[] productIds = new int[rProductTypeProducts.size()];
				for (int i = 0; i < rProductTypeProducts.size(); i++) {
					productIds[i] = rProductTypeProducts.get(i).getProductId();
				}
				products = productMapper.selectProducts(productIds);
			}
		}
		logger.debug("[ run ] ProductServiceImpl.queryProductPagination(), products=" + products);
		
		logger.debug("[ run ] ProductServiceImpl.queryProductPagination(), 查询 " + products + " 的配图");
		products = queryProductsPictures(products);
		
		logger.debug("[ run ] ProductServiceImpl.queryProductPagination(), 查询 " + products + " 的属性");
		products = queryProductsProps(products);
		return products;
	}
	
	/**
	 * 对产品属性进行分组
	 * @param productProps
	 * @return
	 */
	@Override
	public Map<String, List<GlyProductProp>> groupProductProps(List<GlyProductProp> productProps) {
		if (productProps == null || productProps.size() <= 0) {
			return null;
		}
		
		Map<String, List<GlyProductProp>> propGroup = new HashMap<String, List<GlyProductProp>>();
		for (GlyProductProp glyProductProp: productProps) {
			String propType = glyProductProp.getPropType();
			if (propGroup.containsKey(propType)) {
				List<GlyProductProp> tmpPropGroup = propGroup.get(propType);
				tmpPropGroup.add(glyProductProp);
			} else {
				List<GlyProductProp> tmpPropGroup = new ArrayList<GlyProductProp>();
				tmpPropGroup.add(glyProductProp);
				propGroup.put(propType, tmpPropGroup);
			}
		}
		return propGroup;
	}
	
	/**
	 * 查询一组产品配图
	 * @param products
	 * @return
	 * @throws ServiceException
	 */
	public List<Product> queryProductsPictures(List<Product> products) throws ServiceException {
		if (products == null || products.size() <= 0) return products;
		
		for (int i = 0; i < products.size(); i++) {
			GlyProductPicture param = new GlyProductPicture();
			param.setProductId(products.get(i).getProductId());
			param.setIsValid(BasePojo.IS_VALID_Y);
			List<GlyProductPicture> productPictures = queryProductPictures(param);
			products.get(i).setProductPictures(productPictures);
		}
		
		return products;
	}
	
	/**
	 * 查询一个产品配图
	 * @param product
	 * @return
	 * @throws ServiceException
	 */
	public List<GlyProductPicture> queryProductPictures(GlyProductPicture param) throws ServiceException {
		return glyProductPictureMapper.selectSelective(param);
	}
	
	/**
	 * 查询一组产品的属性
	 * @param products
	 * @return
	 */
	public List<Product> queryProductsProps(List<Product> products) {
		if (products == null || products.size() <= 0) return products;
		
		for (int i = 0; i < products.size(); i++) {
			List<GlyProductProp> productProps = queryProductProps(products.get(i).getProductId(), BasePojo.IS_VALID_Y);
			products.get(i).setProductProps(productProps);
		}
		
		return products;
	}
	
	/**
	 * 查询一个产品属性
	 * @param productId
	 * @return
	 */
	public List<GlyProductProp> queryProductProps(Integer productId, int isValid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("isValid", isValid);
		return glyProductPropMapper.selectProductProps(params);
	}

}
