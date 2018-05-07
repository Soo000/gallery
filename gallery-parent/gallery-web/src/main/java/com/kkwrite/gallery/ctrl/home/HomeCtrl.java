package com.kkwrite.gallery.ctrl.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.ModuleDict;
import com.kkwrite.gallery.common.https.HttpsUtil;
import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.module.GlyModuleItem;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.home.HomeService;
import com.kkwrite.gallery.service.user.UserService;

@Controller
@RequestMapping("/homectrl")
public class HomeCtrl extends BaseCtrl {

	private Logger logger = Logger.getLogger(HomeCtrl.class);
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/prepage")
	public String prePage(String code, HttpServletRequest request) {
		logger.debug("[ begin ] HomeCtrl.prePage().");
		
		// 1. 获取 code
	    // code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
		logger.info("[ run ] HomeCtrl.prePage(), code = " + code);
	    
	    /* 2. 通过code换取网页授权access_token
	     * 获取code后，请求以下链接获取access_token：  
	     * https://api.weixin.qq.com/sns/oauth2/access_token
	     * ?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	     */
	    String webAuthAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" 
	         + "?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("APPID", "wx6e6fe98f77e9d950");
	    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("SECRET", "356fa56285c980bacfc3044f88e66b81");
	    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("CODE", code);
	    String webAuthAccessTokenResponse = HttpsUtil.httpsRequest(webAuthAccessTokenUrl, "GET", null);
	    logger.info("[ run ] HomeCtrl.prePage(), 获取网页授权回调，webAuthAccessTokenResponse = " + webAuthAccessTokenResponse);
	    Map<String, String> webAuthAccessTokenMap = JsonUtil.generateBean(webAuthAccessTokenResponse);
	    String openId = webAuthAccessTokenMap.get("openid");
	    logger.info("[ run ] HomeCtrl.prePage(), openId = " + openId);
	    
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
	    
		logger.debug("[ begin ] HomeCtrl.prePage().");
		return "forward:/homectrl/pagectrl";
	}

	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] HomeCtrl.pageCtrl().");
		
		ModelAndView modelAndView = new ModelAndView("/home/home");
		
		/*// WeiXin appId
		String appId = WeiXinTokenUtil.getAppId();
		modelAndView.addObject("appId", appId);
		logger.info("[ run ] HomeCtrl.pageCtrl()，appId = " + appId);
		
		String nonceStr = "1";
		String timestamp = "1";
		String url = "http://artlyt.com.cn"  
			    + request.getContextPath() // 项目名称  
			    + request.getServletPath() // 请求页面或其他地址  
				+ "?" + (request.getQueryString()); // 参数
			System.out.println("获取到 url = " + url);
		String signature = WeiXinTokenUtil.signature(nonceStr, timestamp, url);
		modelAndView.addObject("signature", signature);
		logger.info("[ run ] HomeCtrl.pageCtrl()，signature = " + signature);*/
		
		// 查询首页轮播图模块
		try {
			List<GlyModuleItem> carouselItems = qryCarousel(ModuleDict.ModuleIds.MODULE_HOME_CAROUSEL);
			modelAndView.addObject("carouselItems", carouselItems);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询首页轮播图出错");
			e.printStackTrace();
		}
		
		// 查询上新区模块
		try {
			GlyModuleItem newAreaModuleItem = qryArea(ModuleDict.ModuleIds.MODULE_HOME_NEWAREA);
			modelAndView.addObject("newAreaModuleItem", newAreaModuleItem);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询上新区模块出错");
			e.printStackTrace();
		}
		
		// 查询折扣区模块
		try {
			GlyModuleItem discountModuleItem = qryArea(ModuleDict.ModuleIds.MODULE_HOME_DISCOUNT_AREA);
			modelAndView.addObject("discountModuleItem", discountModuleItem);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询折扣区模块出错");
			e.printStackTrace();
		}
		
		// 查询生活好物模块
		try {
			GlyModuleItem goodsModuleItem = qryArea(ModuleDict.ModuleIds.MODULE_HOME_GOODS_AREA);
			modelAndView.addObject("goodsModuleItem", goodsModuleItem);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询生活好物模块出错");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] HomeCtrl.pageCtrl().");
		return modelAndView;
	}
	
	/**
	 * 查询首页轮播图
	 * @param moduleId
	 * @return
	 */
	private List<GlyModuleItem> qryCarousel(int moduleId) throws ServiceException  {
		List<GlyModuleItem> carouselItems = homeService.queryModuleItems(moduleId);
		return carouselItems;
	}
	
	private GlyModuleItem qryArea(int moduleId) throws ServiceException {
		return homeService.queryModuleItem(moduleId);
	}

}
