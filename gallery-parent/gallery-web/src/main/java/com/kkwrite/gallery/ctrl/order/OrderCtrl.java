package com.kkwrite.gallery.ctrl.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.component.weixin.WeiXinTokenUtil;
import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.address.GlyAddress;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.order.OrderProducts;
import com.kkwrite.gallery.pojo.product.GlyProductInstance;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.address.AddressService;
import com.kkwrite.gallery.service.order.OrderService;
import com.kkwrite.gallery.service.product.ProductService;
import com.kkwrite.gallery.service.user.UserService;

@Controller
@RequestMapping("/orderctrl")
public class OrderCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(OrderCtrl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl(int productId) {
		logger.debug("[ begin ] OrderCtrl.pageCtrl(), productId=" + productId);
		
		ModelAndView modelAndView = new ModelAndView("/order/order");
		
		logger.debug("[ end ] OrderCtrl.pageCtrl().");
		return modelAndView;
	}
	
	/**
	 * 下订单
	 * @param productId
	 * @return
	 */
	@RequestMapping("/placeorder")
	public ModelAndView placeOrder(int[] productId, int productNum[], String addrId, String subType) {
		logger.debug("[ begin ] OrderCtrl.placeorder(), productId=" + productId);
		ModelAndView modelAndView = new ModelAndView("/order/order");
		
		try {
			if (productId == null || productId.length <= 0) {
				modelAndView.setViewName("/cart/cart");
				return modelAndView;
			}
			
			// 查询产品信息
			List<Product> products = productService.queryProduct(productId);
			// 设置购买的产品数量
			if (productNum == null || productNum.length <= 0 
					|| productId.length != productNum.length 
					|| productNum.length != products.size()) {
				throw new ServiceException("产品购买数量异常");
			}
			for (int i = 0; i < productNum.length; i++) {
				products.get(i).setProductNum(productNum[i]);
			}
			modelAndView.addObject("products", products);
			
			// 查询产品配图
			products = productService.queryProductsPictures(products);
			modelAndView.addObject("products", products);
			
			// 查询金额
			float acount = 0.0f;
			// 产品数量
			int count = 0;
			if (products != null) {
				for (Product product: products) {
					int proNum = product.getProductNum() <= 0 ? 1: product.getProductNum();
					acount += product.getRealPrice();
					// 计算产品金额
					acount += product.getRealPrice() * proNum;
					// 计算产品数量
					count += proNum;
				}
			}
			modelAndView.addObject("acount", acount);
			modelAndView.addObject("count", count);
			// 提交方式：addToCart-加入购物车，buyNow-立即购买
			modelAndView.addObject("subType", subType);
		} catch (ServiceException e) {
			logger.error("[ run ] OrderCtrl.pageCtrl(), 查询产品信息出错！");
			e.printStackTrace();
		}
		
		try {
			// 查询用户
			String username = getUserDetails().getUsername();
			GlyUser user = userService.queryUserByName(username);
			modelAndView.addObject("user", user);
			
			// 查询用户地址信息
			GlyAddress glyAddress = null;
			if (addrId == null) {
				glyAddress = addressService.queryAddress(user.getUserId());
			} else {
				glyAddress = addressService.queryAddressByAddId(Integer.parseInt(addrId));
			}
			modelAndView.addObject("glyAddress", glyAddress);
			
		} catch (ServiceException e) {
			logger.error("[ run ] OrderCtrl.placeorder(), 查询用户或用户地址信息出错！");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] OrderCtrl.placeorder().");
		return modelAndView;
	}
	
	/**
	 * 提交订单
	 * @param productIds
	 * @param productNum
	 * @param addressId
	 * @return
	 */
	@RequestMapping("/submitorder")
	public ModelAndView submitOrder(int[] productId, int[] productNum, String addressId, String subType) {
		ModelAndView modelAndView = new ModelAndView("/order/orderPay");
		
		if (productId == null || productId.length <= 0) {
			modelAndView.setViewName("/order/pagectrl");
			return modelAndView;
		}
		
		if (productNum == null || productNum.length <= 0) {
			modelAndView.setViewName("/order/pagectrl");
			return modelAndView;
		}
		
		if (addressId == null) {
			modelAndView.setViewName("/order/pagectrl");
			return modelAndView;
		}
		
		// 获取当前登录用户
		String username = getUserDetails().getUsername();
		GlyUser glyUser = userService.queryUserByName(username);
		
		// 查询产品信息
		List<Product> products = productService.queryProduct(productId);
		for (int i = 0; i < productNum.length; i++) {
			products.get(i).setProductNum(productNum[i]);
		}
		
		// 生成订单
		String orderCode = generateOrderCode();
		GlyOrder glyOrder = new GlyOrder();
		glyOrder.setOrderCode(orderCode);
		glyOrder.setUserId(glyUser.getUserId());
		glyOrder.setAddressId(Integer.parseInt(addressId));
		glyOrder.setOrderType(BasePojo.OrderDict.ORDER_TYPE_PRODUCT);
		glyOrder.setOrderStatus(BasePojo.OrderDict.ORDER_STATUS_WAITING_PAY);
		glyOrder.setValidTime(Calendar.getInstance().getTime());
		// TODO 设置失效时间 glyOrder.setInvalidTime();
		glyOrder.setIsValid(BasePojo.IS_VALID_Y);
		
		// 生成产品实例
		List<GlyProductInstance> glyProductInstances = new ArrayList<GlyProductInstance>();
		for (int i = 0; i < products.size(); i++) {
			int proNum = products.get(i).getProductNum();
			for (int j = 0; j < proNum; j++) {
				GlyProductInstance glyProductInstance = new GlyProductInstance();
				glyProductInstance.setProductInstanceId(generateUUID());
				glyProductInstance.setOrderCode(orderCode);
				glyProductInstance.setProductId(productId[i]);
				glyProductInstance.setIsValid(BasePojo.IS_VALID_Y);
				glyProductInstances.add(glyProductInstance);
			}
		}
		
		// 查询金额
		float acount = 0.0f;
		// 产品数量
		int count = 0;
		if (products != null) {
			for (Product product: products) {
				int proNum = product.getProductNum() <= 0 ? 1: product.getProductNum();
				// 计算产品金额
				acount += product.getRealPrice() * proNum;
				// 计算产品数量
				count += proNum;
			}
		}
		// 实付金额
		glyOrder.setRealPayment(acount);
		glyOrder.setProductNum(count);
		
		GlyAddress glyAddress = new GlyAddress();
		glyAddress.setAddressId(Integer.parseInt(addressId));
		
		// 保存订单 
		orderService.saveOrder(glyOrder, glyProductInstances, glyAddress, subType); // subType 提交方式：addToCart-加入购物车，buyNow-立即购买
		
		modelAndView.addObject("acount", acount);
		modelAndView.addObject("count", count);
		modelAndView.addObject("orderCode", orderCode);
		
		return modelAndView;
	}
	
	@RequestMapping("/payorder")
	public ModelAndView payOrder() {
		ModelAndView modelAndView = new ModelAndView();
		
		String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=#APPID#"
				+ "&redirect_uri=#REDIRECT_URI#"
				+ "&response_type=code"
				+ "&scope=#SCOPE#"
				+ "#wechat_redirect";
		
		codeUrl = codeUrl.replaceFirst("#APPID#", WeiXinTokenUtil.getAppId());
		codeUrl = codeUrl.replaceFirst("#REDIRECT_URI#", "http://artlyt.com.cn/jsp/weixin/codeResult.jsp");
		codeUrl = codeUrl.replaceFirst("#SCOPE#", "snsapi_userinfo");
		
		modelAndView.setViewName("redirect:" + codeUrl);
		return modelAndView;
	}
	
	@RequestMapping("/myorder")
	public ModelAndView myOrder(int orderStatus) {
		ModelAndView modelAndView = new ModelAndView("/order/myOrder");
		
		try {
			// 查询我的订单
			String username = getUserDetails().getUsername();
			GlyUser user = userService.queryUserByName(username);
			List<GlyOrder> orders = orderService.getMyOrder(user.getUserId());
			
			// 查询订单下的所有产品
			List<OrderProducts> ordersProducts = orderService.getOrdersProducts(orders);
			modelAndView.addObject("ordersProducts", ordersProducts);
			modelAndView.addObject("orderStatus", orderStatus);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/setorderstatus")
	public ModelAndView setOrderStatus(String orderCode, int orderStatus, int newOrderStatus) {
		ModelAndView modelAndView = new ModelAndView("redirect:/orderctrl/myorder?orderStatus=" + orderStatus);
		try {
			int result = orderService.setOrderStatus(orderCode, newOrderStatus);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping("/deleteorder")
	public ModelAndView deleteOrder(String orderCode) {
		ModelAndView modelAndView = new ModelAndView("redirect:/orderctrl/myorder?orderStatus=" + BasePojo.OrderDict.ORDER_STATUS_CANCELED);
		try {
			int result = orderService.deleteOrder(orderCode);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
}