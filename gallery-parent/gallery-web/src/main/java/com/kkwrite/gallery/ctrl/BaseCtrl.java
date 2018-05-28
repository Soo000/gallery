package com.kkwrite.gallery.ctrl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.https.HttpsUtil;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.user.UserService;

public class BaseCtrl {

	private Logger logger = Logger.getLogger(BaseCtrl.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 生成一个 uuid
	 * @return
	 */
	public String generateUUID() {
		String uuid = UUID.randomUUID().toString(); 
		return uuid.replaceAll("-", "");
	}
	
	public Date computerDate(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
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
	
	/**
	 * 获取微信 code
	 * @param code
	 * @return
	 */
	public String getOpenId(String code) {
		/* 2. 通过code换取网页授权access_token
	     * 获取code后，请求以下链接获取access_token
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
	    return openId;
	}
	
	/**
	 * 构建用户信息
	 * @param openId
	 */
	public void buildUserInfo(String openId) {
		GlyUser glyUser = userService.queryUserByOpenId(openId);
		if (glyUser == null) {
			GlyUser glyUserNew = new GlyUser();
			glyUserNew.setUsername(openId);
			glyUserNew.setIsValid(1);
			glyUserNew.setOpenId(openId);
			glyUserNew.setUserType(11);
			glyUserNew.setPassword("123321");
		glyUserNew.setUserLevel(1);
		glyUserNew.setScore(0);
		
		int saveResult = userService.saveUser(glyUserNew);
			logger.info("[ run ] BaseCtrl.buildUserInfo(), saveResult = " + saveResult);
		}
		
		// 设置用户名
		request.getSession().setAttribute("username", glyUser.getUsername());
		logger.info("[ run ] BaseCtrl.buildUserInfo() 在 Session 中设置 username " + getUsername());
	}
	
	public String getUsername() {
		return (String) request.getSession().getAttribute("username");
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
