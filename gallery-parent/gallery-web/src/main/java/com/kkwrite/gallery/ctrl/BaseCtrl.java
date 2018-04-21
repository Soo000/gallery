package com.kkwrite.gallery.ctrl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseCtrl {

	private HttpServletRequest request;
	
	public UserDetails getUserDetails() {
		Object userObject = SecurityContextHolder.getContext()  
							    .getAuthentication()  
							    .getPrincipal();
		
		if (userObject instanceof UserDetails) {
			return (UserDetails) userObject;
		} else {
			return null;
		}
	}
	
	/**
	 * 生成一个 uuid
	 * @return
	 */
	public String generateUUID() {
		String uuid = UUID.randomUUID().toString(); 
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * 生成订单编号
	 * @return
	 */
	public String generateOrderCode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = Calendar.getInstance().getTime();
		return sdf.format(date);
	}
	
	public Date computerDate(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
