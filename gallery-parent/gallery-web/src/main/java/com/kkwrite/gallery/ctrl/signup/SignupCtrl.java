package com.kkwrite.gallery.ctrl.signup;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.service.signup.SignupService;

@Controller
@RequestMapping("/signctrl")
public class SignupCtrl {
	
	private Logger logger = Logger.getLogger(SignupCtrl.class);
	
	@Autowired
	private SignupService signupService;

	/**
	 * 登录以及登录失败
	 * @param error
	 * @param model
	 * @return
	 */
	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] SignupCtrl.login()");
		
		ModelAndView modelAndView = new ModelAndView("/signup/signup");
		
		logger.debug("[ end ] SignupCtrl.login()");
		return modelAndView;
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/signup")
	public ModelAndView signup(String phoneNum, String password, String verifyCode) {
		logger.debug("[ begin ] SignupCtrl.signup()");
		
		ModelAndView modelAndView = new ModelAndView("/signup/result");
		try {
			signupService.signup(phoneNum, password);
			modelAndView.addObject("retCode", "1");
		} catch (ServiceException e) {
			logger.error("[ run ] SignupCtrl.signup()");
			e.printStackTrace();
			
			modelAndView.addObject("retCode", "0");
			modelAndView.addObject("retMsg", "注册失败");
		}
		
		logger.debug("[ end ] SignupCtrl.signup()");
		return modelAndView;
	}
	
}
