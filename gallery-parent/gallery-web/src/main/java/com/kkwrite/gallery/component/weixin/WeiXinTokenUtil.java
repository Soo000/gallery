package com.kkwrite.gallery.component.weixin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.MD5Util;
import com.kkwrite.gallery.common.XmlUtil;
import com.kkwrite.gallery.common.https.HttpsUtil;
import com.kkwrite.gallery.component.MyConfig;
import com.kkwrite.gallery.service.param.ParamService;

public class WeiXinTokenUtil implements Runnable {

	private static Logger logger = Logger.getLogger(WeiXinTokenUtil.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//private MyConfig myConfig;
	private static ParamService paramService;
	
	private static String appId;
	
	private static String appSecret;
	
	private static String token;
	
	private static String jsTicket;
	
	public WeiXinTokenUtil(MyConfig myConfig, ParamService paramService) {
		//this.myConfig = myConfig;
		this.paramService = paramService;
	}
	
	@Override
	public void run() {
		Date curDate = Calendar.getInstance().getTime();
		String curDateStr = sdf.format(curDate);
		logger.info("在 " + curDateStr + " 获取一次微信 Token ");
		
		appId = paramService.getParamValue("weixin.appid");
		logger.info("[ run ] WeiXinTokenUtil.run(), appId = " + appId);
		
		appSecret = paramService.getParamValue("weixin.appsecret");
		logger.info("[ run ] WeiXinTokenUtil.run(), appSecret = " + appSecret);
		
		// 查询最后一次获取的 token 值
		token = paramService.getParamValue("weixin.token");
		// 查询最后一次获取的 jsTicket 值
		jsTicket = paramService.getParamValue("weixin.js_ticket");
		// 判断是否需要重新生成
		if (token == null || token.length() <= 0) {
			logger.info("[ run ] WeiXinTokenUtil.run(), 当前没有 token 值，准备获取 token");

			String tokenUrl = paramService.getParamValue("weixin.token_url");
			tokenUrl = tokenUrl.replaceFirst("#APPID#", appId);
			tokenUrl = tokenUrl.replaceFirst("#APPSECRET#", appSecret);
			logger.info("[ run ] WeiXinTokenUtil.run(), tokenUrl = " + tokenUrl);
			
			// 调用生成token接口
			generateToken(tokenUrl);
			
			// 调用生成jsTicket接口
			String jsTicketUrl = paramService.getParamValue("weixin.js_ticket_url");
			jsTicketUrl = jsTicketUrl.replaceFirst("#ACCESS_TOKEN#", token);
			generateJsTicket(jsTicketUrl);
			return;
		}
		
		// 判断最后一次生成 token 时间，是否需要重新生成 token
		String tokenTime = paramService.getParamValue("weixin.token_time");
		long tokenTimeLong = 0l;
		try {
			tokenTimeLong = sdf.parse(tokenTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long curTimeLong = System.currentTimeMillis();
		long timeLong = (curTimeLong - tokenTimeLong) / 1000;
		if (timeLong < 7000) {
			logger.info("[ run ] WeiXinTokenUtil.run(), 距离最后一次生成 token 的时间(" + tokenTime + ") 不足 7200s，无需生成新 token");
			return;
		}
		
		// 调用生成token接口
		String tokenUrl = paramService.getParamValue("weixin.token_url");
		tokenUrl = tokenUrl.replaceFirst("#APPID#", appId);
		tokenUrl = tokenUrl.replaceFirst("#APPSECRET#", appSecret);
		logger.info("[ run ] WeiXinTokenUtil.run(), tokenUrl = " + tokenUrl);
		generateToken(tokenUrl);
		
		// 调用生成jsTicket接口
		String jsTicketUrl = paramService.getParamValue("weixin.js_ticket_url");
		jsTicketUrl = jsTicketUrl.replaceFirst("#ACCESS_TOKEN#", token);
		generateJsTicket(jsTicketUrl);
	}
	
	/**
	 * 生成 token
	 */
	private void generateToken(String tokenUrl) {
		String responseContent = HttpsUtil.httpsRequest(tokenUrl, "GET", null);
		logger.info("[ run ] WeiXinTokenUtil.generateToken(), 调用获取 token 接口返回, responseContent=" + responseContent);
		if (responseContent != null) {
			Map resultMap = JsonUtil.generateBean(responseContent);
			if (resultMap.containsKey("access_token")) {
				token = (String) resultMap.get("access_token");
				boolean isUpdate = paramService.setParam("weixin.token", token);
				String time = sdf.format(Calendar.getInstance().getTime());
				isUpdate = paramService.setParam("weixin.token_time", time);
				logger.info("[ run ] WeiXinTokenUtil.generateToken(), 生成 token 成功, token=" + token + ", 更新生成 token 时间 isUpdate=" + isUpdate);
				return;
			} else {
				String errcode = (String) resultMap.get("errcode");
				String errmsg = (String) resultMap.get("errmsg");
				logger.error("[ run ] WeiXinTokenUtil.generateToken(), 生成 token 失败, errcode=" + errcode + "errmsg=" + errmsg);
			}
		}
	}
	
	/**
	 * js_sdk 签名算法
	 * noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分）
	 * @return
	 */
	public static String signature(String noncestr, String timestamp, String url) {
		String string1 = "jsapi_ticket=" + jsTicket 
				+ "&noncestr=" + noncestr 
				+ "&timestamp=" + timestamp 
				+ "&url=" + url;
		logger.info("[ run ] WeiXinTokenUtil.signature(), string1=" + string1);
		String result = DigestUtils.sha1Hex(string1);
		return result;
	}
	
	/**
	 * 生成 jsapi_ticket
	 */
	private void generateJsTicket(String jsTicketUrl) {
		String responseContent = HttpsUtil.httpsRequest(jsTicketUrl, "GET", null);
		logger.info("[ run ] WeiXinTokenUtil.generateJsTicket(), 调用获取 js_ticket 接口返回, responseContent=" + responseContent);
		if (responseContent != null) {
			Map resultMap = JsonUtil.generateBean(responseContent);
			if (resultMap.containsKey("ticket")) {
				jsTicket = (String) resultMap.get("ticket");
				boolean isUpdate = paramService.setParam("weixin.js_ticket", jsTicket);
				String time = sdf.format(Calendar.getInstance().getTime());
				isUpdate = paramService.setParam("weixin.js_ticket_time", time);
				logger.info("[ run ] WeiXinTokenUtil.generateToken(), 生成 jsTicket 成功, jsTicket=" + jsTicket + ", 更新生成 jsTicket 时间 isUpdate=" + isUpdate);
				return;
			} else {
				String errcode = (String) resultMap.get("errcode");
				String errmsg = (String) resultMap.get("errmsg");
				logger.error("[ run ] WeiXinTokenUtil.generateToken(), 生成 token 失败, errcode=" + errcode + "errmsg=" + errmsg);
			}
		}
	}
	
	/**
	 * 通过code换取网页授权access_token,也获取到了openid
	 * @param appId
	 * @param secret
	 * @param code, code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
	 * @param grant_type
	 * @return
	 */
	public static String generateWebToken(String code) {
		String webTokenUrl = paramService.getParamValue("weixin.web_token_url");
		webTokenUrl = webTokenUrl.replaceFirst("#APPID#", appId);
		webTokenUrl = webTokenUrl.replaceFirst("#SECRET#", appSecret);
		webTokenUrl = webTokenUrl.replaceFirst("#CODE#", code);
		logger.info("[ run ] WeiXinTokenUtil.generateWebToken(), 准备调用通过 code 换取网页授权 access_token 接口, webTokenUrl=" + webTokenUrl);
		String responseContent = HttpsUtil.httpsRequest(webTokenUrl, "GET", null);
		logger.info("[ run ] WeiXinTokenUtil.generateWebToken(), 调用通过 code 换取网页授权 access_token 接口返回, responseContent=" + responseContent);
		return responseContent;
	}
	
	/**
	 * 通过code换取网页授权access_token是否有效
	 * @param webAccessToken
	 * @param openId
	 * @return
	 */
	public static boolean isWebTokenValid(String webAccessToken, String openId) {
		return false;
	}
	
	/**
	 * 统一下单签名算法
	 * noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分）
	 * @return
	 */
	public static String sign(String body, String nonce_str, String notify_url, String openid,
			String out_trade_no, String spbill_create_ip, String total_fee, String trade_type) {
		String mch_id = paramService.getParamValue("weixin.mch_id");
		String key = paramService.getParamValue("weixin.mch_key");
		
		String sign = "appid=" + appId 
				+ "&body=" + body 
				+ "&mch_id=" + mch_id
				+ "&nonce_str=" + nonce_str 
				+ "&notify_url=" + notify_url
				+ "&openid=" + openid
				+ "&out_trade_no=" + out_trade_no
				+ "&spbill_create_ip=" + spbill_create_ip
				+ "&total_fee=" + total_fee
				+ "&trade_type=" + trade_type
				+ "&key=" + key;
		
		logger.info("[ run ] WeiXinTokenUtil.sign(), sign串 =  " + sign);
		return MD5Util.getMD5(sign);
	}
	
	/**
	 * 发起预支付订单
	 * @param appid
	 * @param body
	 * @param mch_id
	 * @param nonce_str
	 * @param notify_url
	 * @param openid
	 * @param out_trade_no
	 * @param spbill_create_ip
	 * @param total_fee
	 * @param trade_type
	 * @param sign
	 * @return
	 */
	public static String sendPayTrade(String body, String nonce_str, String notify_url,
			String openid, String out_trade_no, String spbill_create_ip, String total_fee, String trade_type,
			String sign) {
		String result = "";
		
		String mch_id = paramService.getParamValue("weixin.mch_id");
		try {
			logger.info("[ run ] WeiXinTokenUtil.sendPayTrade()");
			String xmlBody = XmlUtil.generateTradeXml(appId, body, mch_id, nonce_str, notify_url, openid, out_trade_no, 
					spbill_create_ip, total_fee, trade_type, sign);
			logger.info("[ run ] WeiXinTokenUtil.sendPayTrade(), xmlBody = " + xmlBody);
			// weixin.trade_url 值为 https://api.mch.weixin.qq.com/pay/unifiedorder
			String url = paramService.getParamValue("weixin.trade_url");
			logger.info("[ run ] WeiXinTokenUtil.sendPayTrade(), url = " + url);
			result = HttpsUtil.httpsRequest(url, "POST", xmlBody);
			logger.info("[ run ] WeiXinTokenUtil.sendPayTrade(), result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 支付签名
	 * @param timeStamp
	 * @param nonceStr
	 * @param packageValue
	 * @param signType
	 * @return
	 */
	public static String paySign(String timeStamp, String nonceStr, String packageValue, String signType) {
		String key = paramService.getParamValue("weixin.mch_key");
		
		String paySign = "appId=" + appId 
				+ "&nonceStr=" + nonceStr 
				+ "&package=" + packageValue
				+ "&signType=" + signType
				+ "&timeStamp=" + timeStamp
				+ "&key=" + key;
		
		logger.info("[ run ] WeiXinTokenUtil.sign(), paySign串 =  " + paySign);
		return MD5Util.getMD5(paySign);
	}
	
	public static String getAppId() {
		return appId;
	}

	public static String getToken() {
		return token;
	}

	public static String getJsTicket() {
		return jsTicket;
	}

}
