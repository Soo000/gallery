<%@page import="com.kkwrite.gallery.common.XmlUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.kkwrite.gallery.component.weixin.WeiXinTokenUtil" %>
<%@ page import="com.kkwrite.gallery.common.JsonUtil" %>
<%
	// js-sdk wx.config() 需要的参数
	String appId = WeiXinTokenUtil.getAppId();
	String timestamp = "1";
	String nonceStr = "1";
	String url = "http://artlyt.com.cn"  
	    + request.getContextPath() // 项目名称  
	    + request.getServletPath() // 请求页面或其他地址  
		+ "?" + (request.getQueryString()); // 参数
	System.out.println("获取到 url = " + url);
	String signature = WeiXinTokenUtil.signature(nonceStr, timestamp, url);
	System.out.println("获取到 signature = " + signature);
	
	// 微信支付需要的参数
	String code = request.getParameter("code");
	System.out.println("获取到 code = " + code);

	String openId = null;
	String webToken = WeiXinTokenUtil.generateWebToken(code);
	System.out.println("获取到 webToken = " + webToken);
	Map<String, String> webTokenMap = JsonUtil.generateBean(webToken);
	System.out.println("调用 getWebToken() 获取 webToken, webTokenMap = " + webTokenMap);
	if (webTokenMap != null) {
		openId = webTokenMap.get("openid");
	}
	System.out.println("获取到 openId = " + openId);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	String orderCode = (String) session.getAttribute("orderCode");
	System.out.println("需要支付的订单编号 orderCode = " + orderCode);
	Float acount = (Float) session.getAttribute("acount");
	System.out.println("需要支付的金额 acount = " + acount);
	Integer count = (Integer) session.getAttribute("count");
	System.out.println("需要购买的数量 count = " + count);
	
	String body = "OrderCode: ";
	String nonce_str = "2";
	String notify_url = "http://artlyt.com.cn/jsp/weixin/payResult.jsp";
	String out_trade_no = sdf.format(Calendar.getInstance().getTime());
	String spbill_create_ip = "0.0.0.0";
	String total_fee = "1"; // 支付金额，单位分
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
%>
<!DOCTYPE html">
<html>
	<head>
		<meta charset="UTF-8">
		<!-- 视口标签 -->
		<meta name="viewport" content="width=device-width, initial-scale=1"/>		
		<!-- 设置IE浏览器采用的解析模式 -->
		<meta http-equiv="X-UA-Campatible" content="IE=edge">
		<!-- 缓存设置 -->
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Expires" content="Sunday, 31 December 2017 23:59 GMT">
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		<title>获取 code 结果页面</title>
		<script type="text/javascript">
			// alert(location.href.split('#')[0]); 当前页面 url
			wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: "<%=appId%>", // 必填，公众号的唯一标识
			    nonceStr: "<%=nonceStr%>", // 必填，生成签名的随机串
			    timestamp: "<%=timestamp%>", // 必填，生成签名的时间戳
			    signature: "<%=signature%>",// 必填，签名，见附录1
			    jsApiList: ['getNetworkType', 'chooseImage', 'chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			/**
			 * config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，
			 * 所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，
			 * 则可以直接调用，不需要放在ready函数中。
			 */
			wx.ready(function() {
				//console.log("执行了wx.ready()，准备好了");
				// 获取网络类型接口
				/* wx.getNetworkType({
					success: function(res) {
						console.log("wx.getNetworkType()执行成功, res = " + res);
						var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
					},
					fail: function() {
						console.log("wx.getNetworkType()执行失败");
					},
					complete: function() {
						console.log("wx.getNetworkType()执行完毕");
					}
				}); */
				
				/**
				 * 支付接口
				 */
				wx.chooseWXPay({
					timestamp: 2, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
					nonceStr: '2', // 支付签名随机串，不长于 32 位
					'package': '<%=packageValue%>', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
					signType: '<%=signType%>', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
					paySign: '<%=paySign%>', // 支付签名
					success: function (res) {
						// 支付成功后的回调函数
						alert("支付成功");
					}
				});
			});
	
			/**
			 * config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，
			 * 也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			 */
			wx.error(function(res) {
				console.log("发生错误了，执行了wx.error()方法：" + res);
			});
		</script>
	</head>
	<body>
		正在支付...
	</body>
</html>