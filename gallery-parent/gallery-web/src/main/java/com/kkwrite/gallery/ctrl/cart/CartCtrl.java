package com.kkwrite.gallery.ctrl.cart;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.https.HttpsUtil;
import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.ctrl.BaseCtrlResult;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.cart.CartService;
import com.kkwrite.gallery.service.product.ProductService;
import com.kkwrite.gallery.service.user.UserService;

@Controller
@RequestMapping("/cartctrl")
public class CartCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(CartCtrl.class);
	
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] CartCtrl.pageCtrl().");
		ModelAndView modelAndView = new ModelAndView("/cart/cart");
		
		// 首先从 session 中获取 username
		String username = (String) getRequest().getSession().getAttribute("username");
		System.out.println("[ 从 Session 中获取 username = " + username + " ]");
		// 如果从 session 中获取不到用户名，则调用微信接口获取openId
		if (username == null || username.trim().length() <= 0) {
			Object userObject = SecurityContextHolder
					.getContext()  
				    .getAuthentication()  
				    .getPrincipal();
			
			if (userObject instanceof UserDetails) {
				username = (String) userObject;
			} else if ("anonymousUser".equals(userObject)) {
				username = "anonymousUser";
			}
			System.out.println("[ 从 SecurityContextHolder 中获取 username = " + username + " ]");
		}
		
		// 如果为匿名用户登录，则调用 微信接口获取用户 openId
		if (username == null || username.trim().length() <= 0 || "anonymousUser".equals(username)) {
			System.out.println("[ 调用微信接口获取 openId. ]");
			
			String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
		            + "?appid=wx6e6fe98f77e9d950"
		            + "&redirect_uri=http://artlyt.com.cn/cartctrl/init"
		            + "&response_type=code"
		            + "&scope=snsapi_userinfo"
		            + "&state=STATE#wechat_redirect";
			modelAndView.setViewName("redirect:" + codeUrl);
			return modelAndView;
		}
		
		try {
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
		
		return modelAndView;
	}
	
	@RequestMapping("/init")
	public ModelAndView init(String code, HttpServletRequest request) {
		/* 2. 通过code换取网页授权access_token
	     * 获取code后，请求以下链接获取access_token：  
	     * https://api.weixin.qq.com/sns/oauth2/access_token
	     * ?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	     */
	    String webAuthAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" 
	         + "?appid=APPID"
	         + "&secret=SECRET"
	         + "&code=CODE"
	         + "&grant_type=authorization_code";
	    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("APPID", "wx6e6fe98f77e9d950");
	    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("SECRET", "356fa56285c980bacfc3044f88e66b81");
	    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("CODE", code);
	    String webAuthAccessTokenResponse = HttpsUtil.httpsRequest(webAuthAccessTokenUrl, "GET", null);
	    logger.info("获取网页授权回调，webAuthAccessTokenResponse = " + webAuthAccessTokenResponse);
	    Map<String, String> webAuthAccessTokenMap = JsonUtil.generateBean(webAuthAccessTokenResponse);
	    String openId = webAuthAccessTokenMap.get("openid");
	    logger.info("openId = " + openId);
	    // 根据 openId 查询用户
	    GlyUser glyUser = userService.queryUserByOpenId(openId);
	    if (glyUser == null) {
	    	logger.info("[ run ] HomeCtrl.prePage(), openId = " + openId + " 的用户不存在，准备创建该用户");
	    	GlyUser glyUserNew = new GlyUser();
	    	glyUserNew.setUsername(openId);
	    	glyUserNew.setIsValid(1);
	    	glyUserNew.setOpenId(openId);
	    	glyUserNew.setUserType(11);
	    	glyUserNew.setPassword("123321");
	    	glyUserNew.setUserLevel(1);
	    	glyUserNew.setScore(0);
	    	
	    	int saveResult = userService.saveUser(glyUserNew);
	    	logger.info("[ run ] HomeCtrl.prePage(), 创建 openId = " + openId + " 的新用户结果 saveResult = " + saveResult);
	    }
	    
	    String username = glyUser != null ? glyUser.getUsername() : openId;
	    logger.info("[ run ] HomeCtrl.prePage(), Session中设置用户名 username = " + username);
	    request.getSession().setAttribute("username", username);
		
		ModelAndView modelAndView = new ModelAndView("/cart/cart");
		try {
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
		
		return modelAndView;
	}

	@RequestMapping("/addtocart")
	@ResponseBody
	public BaseCtrlResult addToCart(int productId, String productPropIds, int productNum) {
		logger.debug("[ begin ] CartCtrl.addToCart().");
		BaseCtrlResult result = new BaseCtrlResult();
		try {
			// 判断购物车中是否已经有该商品
			Product product = cartService.queryCartProduct(getUsername(), productId);
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
			boolean isAdded = cartService.addToCart(getUsername(), productId, productPropId, productNum);
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
			cartService.removeFromCart(getUsername(), productIds);
		} catch (Exception e) {
			logger.error("[ run ] CartCtrl.removeFromCart(), 移除购物车内产品出错！");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] CartCtrl.removeFromCart().");
		return modelAndView;
	}
	
}
