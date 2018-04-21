package com.kkwrite.gallery.ctrl.weixin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.common.JsonUtil;
import com.kkwrite.gallery.common.XmlUtil;
import com.kkwrite.gallery.component.weixin.WeiXinTokenUtil;
import com.kkwrite.gallery.ctrl.BaseCtrl;

@Controller
@RequestMapping("/weixinctrl")
public class WeiXinCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(WeiXinCtrl.class);

	@RequestMapping("/getcode")
	public ModelAndView getCode(String code) {
		ModelAndView modelAndView = new ModelAndView("/weixin/codeResult");
		modelAndView.addObject("code", code);
		return modelAndView;
	}
	
	@RequestMapping("/authorizeback")
	public ModelAndView authorizeBack(String code) throws Exception {
		logger.debug("[ begin ] WeiXinCtrl.authorizeBack().");
		System.out.println("[ run ] WeiXinCtrl.authorizeBack(), 调用微信用户同意授权接口返回，code = " + code);
		
		ModelAndView modelAndView = new ModelAndView("/weixin/callPay");
		
		System.out.println("[ run ] WeiXinCtrl.authorizeBack(), 准备通过code 获取 webToken");
		String webToken = WeiXinTokenUtil.generateWebToken(code);
		System.out.println("[ run ] WeiXinCtrl.authorizeBack(), 完成通过code 获取 webToken, webToken = " + webToken);
		
		String openId = null;
		Map<String, String> webTokenMap = JsonUtil.generateBean(webToken);
		System.out.println("调用 getWebToken() 获取 webToken, webTokenMap = " + webTokenMap);
		if (webTokenMap != null) {
			openId = webTokenMap.get("openid");
		}
		System.out.println("[ run ] WeiXinCtrl.authorizeBack(), 获取到 openId = " + openId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String body = "test";
		String nonce_str = "2";
		String notify_url = "http://artlyt.com.cn/jsp/weixin/payResult.jsp";
		String out_trade_no = sdf.format(Calendar.getInstance().getTime());
		String spbill_create_ip = "0.0.0.0";
		String total_fee = "1";
		String trade_type = "JSAPI";
		String sign = WeiXinTokenUtil.sign(body, nonce_str, notify_url, openId, out_trade_no, spbill_create_ip, total_fee, trade_type);
		
		System.out.println("准备统一下单");
		String result = WeiXinTokenUtil.sendPayTrade(body, nonce_str, notify_url, openId, out_trade_no, spbill_create_ip, total_fee, trade_type, sign);
		
		String returnCode = "FAIL";
		String prepayId = "";
		String packageValue = "";
		String signType = "MD5";
		String paySign = "";
		Map<String, String> tradeMap = XmlUtil.tradeXmlToMap(result);
		if (tradeMap != null) {
			returnCode = tradeMap.get("return_code");
			if ("SUCCESS".equals(returnCode)) {
				prepayId = tradeMap.get("prepay_id");
				System.out.println("获取到 prepayId = " + prepayId);
				packageValue = "prepay_id=" + prepayId;
				paySign = WeiXinTokenUtil.paySign("2", "2", packageValue, signType);
				System.out.println("获取到 paySign = " + paySign);
			}
		}
		
		modelAndView.addObject("signType", signType);
		modelAndView.addObject("packageValue", packageValue);
		modelAndView.addObject("paySign", paySign);
		
		logger.debug("[ end ] WeiXinCtrl.authorizeBack().");
		return modelAndView;
	}
	
}
