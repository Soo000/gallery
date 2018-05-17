package com.kkwrite.gallery.ctrl.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.https.HttpsUtil;
import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.home.HomeService;
import com.kkwrite.gallery.service.home.ModuleBO;
import com.kkwrite.gallery.service.home.ModuleItemBO;
import com.kkwrite.gallery.service.home.ModuleItemQuery;
import com.kkwrite.gallery.service.home.ModuleQuery;
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
		
		// 查询首页模块
		List<ModuleBO> modules = qryHomeModule(0); // TODO 参数写死了;
		if (modules == null || modules.isEmpty()) {
			modelAndView.setViewName("/error"); // TODO 跳转到错误页面；
		}
		
		// 查询首页每个模块的模块项
		List<HomeModuleVO> homeModuleVOs = qryModuleItem(modules);
		modelAndView.addObject("homeModuleVOs", homeModuleVOs);
		
		logger.debug("[ end ] HomeCtrl.pageCtrl().");
		return modelAndView;
	}
	
	/**
	 * 查询首页模块
	 * @param pModuleId
	 * @return
	 */
	private List<ModuleBO> qryHomeModule(int pModuleId) {
		ModuleQuery moduleQuery = new ModuleQuery();
		moduleQuery.setPModuleId(pModuleId);
		moduleQuery.setValid(BasePojo.IS_VALID_Y);
		return homeService.queryModuleByPModuleId(moduleQuery); 
	}
	
	/**
	 * 查询每一个模块下的模块项
	 * @param modules
	 * @return
	 */
	private List<HomeModuleVO> qryModuleItem(List<ModuleBO> moduleBOs) {
		List<HomeModuleVO> homeModuleVOs = new ArrayList<HomeModuleVO>();
		for (ModuleBO moduleBO: moduleBOs) {
			HomeModuleVO homeModuleVO = new HomeModuleVO();
			homeModuleVO.setModuleId(moduleBO.getModuleId());
			homeModuleVO.setModuleName(moduleBO.getModuleName());
			homeModuleVO.setModuleTitle(moduleBO.getModuleTitle());
			homeModuleVO.setModuleTemplate(moduleBO.getModuleTemplate());
			homeModuleVO.setModuleOrder(moduleBO.getModuleOrder());
			
			// 查询模块下的模块项
			ModuleItemQuery moduleItemQuery = new ModuleItemQuery();
			moduleItemQuery.setModuleId(moduleBO.getModuleId());
			moduleItemQuery.setValid(BasePojo.IS_VALID_Y);
			List<ModuleItemBO> moduleItemBOs = homeService.queryModuleItems(moduleItemQuery);
			if (moduleItemBOs == null || moduleItemBOs.isEmpty()) {
				continue;
			}
			
			List<HomeModuleItemVO> homeModuleItemVOs = new ArrayList<HomeModuleItemVO>();
			for (ModuleItemBO moduleItemBO: moduleItemBOs) {
				HomeModuleItemVO homeModuleItemVO = new HomeModuleItemVO();
				homeModuleItemVO.setModuleItemId(moduleItemBO.getModuleId());
				homeModuleItemVO.setModuleItemName(moduleItemBO.getModuleItemName());
				homeModuleItemVO.setModuleItemImage(moduleItemBO.getModuleItemImage());
				homeModuleItemVO.setModuleItemOrder(moduleItemBO.getModuleItemOrder());
				homeModuleItemVO.setModuleItemType(moduleItemBO.getModuleItemType());
				homeModuleItemVOs.add(homeModuleItemVO);
			}
			homeModuleVO.setHomeModuleItemVOs(homeModuleItemVOs);
			
			homeModuleVOs.add(homeModuleVO);
		}
		return homeModuleVOs;
	}

}
