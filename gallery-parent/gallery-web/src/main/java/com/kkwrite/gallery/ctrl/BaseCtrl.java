package com.kkwrite.gallery.ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.https.HttpsUtil;

public class BaseCtrl {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	/**
	 * 获取当前用户名
	 * @return
	 */
	public String getUsername() {
		System.out.println("[ Begin BaseCtrl.getUsername().]");
		// 首先从 session 中获取 username
		String username = (String) request.getSession().getAttribute("username");
		System.out.println("[ BaseCtrl.getUsername(), 从 Session 中获取 username = " + username + " ]");
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
			System.out.println("[ BaseCtrl.getUsername(), 从 SecurityContextHolder 中获取 username = " + username + " ]");
		}
		
		// 如果为匿名用户登录，则调用 微信接口获取用户 openId
		if (username == null || username.trim().length() <= 0 || "anonymousUser".equals(username)) {
			System.out.println("[ BaseCtrl.getUsername(), 调用微信接口获取 openId. ]");
			getWeiXinCode("", "/cartctrl/init");
		}
		
		return username;
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
	
	private void getWeiXinCode(String appId, String redirectUri) {
		String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
	            + "?appid=wx6e6fe98f77e9d950"
	            + "&redirect_uri=" + redirectUri
	            + "&response_type=code"
	            + "&scope=snsapi_userinfo"
	            + "&state=STATE#wechat_redirect";
		try {
			request.getRequestDispatcher("/cartctrl/pagectrl").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getAccessToken(String code) {
		String webAuthAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" 
		         + "?appid=APPID&secret=SECRET"
		         + "&code=CODE"
		         + "&grant_type=authorization_code";
		    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("APPID", "wx6e6fe98f77e9d950");
		    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("SECRET", "356fa56285c980bacfc3044f88e66b81");
		    webAuthAccessTokenUrl = webAuthAccessTokenUrl.replace("CODE", code);
		    String webAuthAccessTokenResponse = HttpsUtil.httpsRequest(webAuthAccessTokenUrl, "GET", null);
		    Map<String, String> webAuthAccessTokenMap = JsonUtil.generateBean(webAuthAccessTokenResponse);
		    // 调用接口返回码
		    String retCode = webAuthAccessTokenMap.get("openid");
		    String openId = webAuthAccessTokenMap.get("openid");
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
