package com.kkwriter.gallery.ctrl.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lisha
 */
@Controller
public class LoginCtrl {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
}
