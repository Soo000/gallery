package com.kkwrite.gallery.ctrl.login;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/loginctrl")
public class LoginCtrl {
	
	private Logger logger = Logger.getLogger(LoginCtrl.class);

	/**
	 * 跳转到登录页
	 * @param error
	 * @param model
	 * @return
	 */
	@RequestMapping("/prelogin")
	public String preLogin(@RequestParam(value = "error", required = false) String error, Model model) {
		logger.debug("[ begin ] LoginCtrl.login()");
		logger.debug("[ end ] LoginCtrl.login()");
		return "/login/login";
	}
	
	/**
	 * 执行登录
	 * @param username
	 * @param password
	 * @param validCode
	 */
	@RequestMapping("/dologin")
	public void doLogin(String username, String password, String validCode) {
		logger.debug("[ begin ] LoginCtrl.doLogin()");
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal();  
			  
		String securityUsername = userDetails.getUsername();
		logger.info("[ run ] LoginCtrl.doLogin(), securityUsername=" + securityUsername);
		
		logger.debug("[ end ] LoginCtrl.doLogin()");
	}
	
	/**
	 * 登录成功
	 * @param username
	 * @param password
	 * @param validCode
	 */
	@RequestMapping("/loginsuccess")
	public String loginSuccess(String username, String password, String validCode) {
		logger.debug("[ begin ] LoginCtrl.loginSuccess()");
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal();
			  
		String securityUsername = userDetails.getUsername();
		logger.info("[ run ] LoginCtrl.loginSuccess(), securityUsername=" + securityUsername);
		
		StringBuffer url = new StringBuffer("redirect:/homectrl/pagectrl");
		logger.debug("[ end ] LoginCtrl.loginSuccess()");
		return url.toString();
	}
	
}
