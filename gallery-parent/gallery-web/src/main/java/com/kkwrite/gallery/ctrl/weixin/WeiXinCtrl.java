package com.kkwrite.gallery.ctrl.weixin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;

@Controller
@RequestMapping("/weixinctrl")
public class WeiXinCtrl extends BaseCtrl {

	private final String DOMAIN = "http://artlyt.com.cn";
	
	private Logger logger = Logger.getLogger(WeiXinCtrl.class);
	
	
	@RequestMapping("/getcode")
	public ModelAndView getCode(HttpServletRequest request) {
		// 获得 code 后的回调地址
		String codeBackUrl = (String) request.getAttribute("codeBackUrl");
		
		// 1. 首先访问 codeUrl 值对应的地址，redirect_uri 值对应的是其回调页面的地址，带有 code 参数
		String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
	            + "?appid=wx6e6fe98f77e9d950"
	            + "&redirect_uri=#redirect_uri#"
	            + "&response_type=code"
	            + "&scope=snsapi_userinfo"
	            + "&state=STATE#wechat_redirect";
		
		codeUrl = codeUrl.replace("#redirect_uri#", DOMAIN + codeBackUrl);
		logger.info("获取微信 code 的 url = " + codeUrl);
		logger.info("获取微信 codeBackUrl 的回调地址 codeBackUrl = " + codeBackUrl);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:" + codeUrl);
		return modelAndView;
	}
	
}
