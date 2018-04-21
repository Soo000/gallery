package com.kkwrite.gallery.ctrl.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCtrl {

	@RequestMapping("/init")
	public String init() {
		return "/user/user";
	}
	
}
