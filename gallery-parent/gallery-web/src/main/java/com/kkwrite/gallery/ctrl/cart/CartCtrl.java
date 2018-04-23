package com.kkwrite.gallery.ctrl.cart;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.ctrl.BaseCtrlResult;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.service.cart.CartService;
import com.kkwrite.gallery.service.product.ProductService;

@Controller
@RequestMapping("/cartctrl")
public class CartCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(CartCtrl.class);
	
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] CartCtrl.pageCtrl().");
		ModelAndView modelAndView = new ModelAndView("/cart/cart");
		
		try {
			// 查询产品
			String username = getUserDetails().getUsername();
			List<Product> products = cartService.queryCartProducts(username);
			// 查询产品配图
			products = productService.queryProductsPictures(products);
			modelAndView.addObject("products", products);
			
			// 查询金额
			float acount = 0.0f;
			// 产品数量
			int count = 0;
			if (products != null) {
				for (Product product: products) {
					int productNum = product.getProductNum() <= 0 ? 1: product.getProductNum();
					// 计算产品金额
					acount += product.getRealPrice() * productNum;
					// 计算产品数量
					count += productNum;
				}
			}
			modelAndView.addObject("acount", acount);
			modelAndView.addObject("count", count);
		} catch (ServiceException e) {
			logger.debug("[ run ] CartCtrl.pageCtrl(), 查询购物车里产品出错！");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] CartCtrl.pageCtrl().");
		return modelAndView;
	}

	@RequestMapping("/addtocart")
	@ResponseBody
	public BaseCtrlResult addToCart(int productId, String productPropIds, int productNum) {
		logger.debug("[ begin ] CartCtrl.addToCart().");
		BaseCtrlResult result = new BaseCtrlResult();
		try {
			// 判断购物车中是否已经有该商品
			Product product = cartService.queryCartProduct(getUserDetails().getUsername(), productId);
			if (product != null) {
				result.setRetCode("1001");
				throw new ServiceException("宝贝已经在您的购物车里面了，不用重新添加！");
			}
			
			int[] productPropId = null;
			if (productPropIds != null && productPropIds.length() > 0) {
				String[] tmpProductPropIdArray = productPropIds.split(",");
				productPropId = new int[tmpProductPropIdArray.length];
				for (int i = 0; i < tmpProductPropIdArray.length; i++) {
					productPropId[i] = Integer.parseInt(tmpProductPropIdArray[i]);
				}
			}
			// 添加到购物车
			boolean isAdded = cartService.addToCart(getUserDetails().getUsername(), productId, productPropId, productNum);
			if (!isAdded) {
				result.setRetCode("1002");
				throw new ServiceException("宝贝加入购物车失败，请稍后重试！");
			}
		} catch (ServiceException e) {
			logger.error("[ run ] CartCtrl.addToCart(), " + e.getMessage());
			e.printStackTrace();
			result.setRetMsg(e.getMessage());
		}
		
		logger.debug("[ end ] CartCtrl.addToCart().");
		return result;
	}
	
	@RequestMapping("/removefromcart")
	@ResponseBody
	public ModelAndView removeFromCart(String products) {
		logger.debug("[ begin ] CartCtrl.removeFromCart().");
		ModelAndView modelAndView = new ModelAndView("forward:/cartctrl/pagectrl");
		try {
			if (products == null || products.length() <= 0) {
				return modelAndView;
			}
			
			// 移除购物车内产品
			String[] tmpProductIds = products.split(",");
			int[] productIds = new int[tmpProductIds.length];
			for (int i = 0; i < tmpProductIds.length; i++) {
				productIds[i] = Integer.parseInt(tmpProductIds[i]);
			}
			cartService.removeFromCart(getUserDetails().getUsername(), productIds);
		} catch (Exception e) {
			logger.error("[ run ] CartCtrl.removeFromCart(), 移除购物车内产品出错！");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] CartCtrl.removeFromCart().");
		return modelAndView;
	}
	
}