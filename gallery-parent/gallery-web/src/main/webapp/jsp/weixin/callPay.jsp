<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.kkwrite.gallery.pojo.BasePojo" %>
<%@ page import="com.kkwrite.gallery.common.XmlUtil"%>
<%@ page import="com.kkwrite.gallery.component.weixin.WeiXinTokenUtil" %>
<!DOCTYPE html>
<%
	//js-sdk wx.config() 需要的参数
	String appId = WeiXinTokenUtil.getAppId();
	String timestamp = "1";
	String nonceStr = "1";
	String url = "http://artlyt.com.cn"  
	    + request.getContextPath() // 项目名称  
	    + request.getServletPath() // 请求页面或其他地址  
		+ "?" + (request.getQueryString()); // 参数
	System.out.println("签名参数 url = " + url);
	String signature = WeiXinTokenUtil.signature(nonceStr, timestamp, url);
	System.out.println("计算出签名值 signature = " + signature);
	
	//
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
    // 获取 openId
    String openId = (String) session.getAttribute("openId");
    System.out.println("进行支付页面中，从 session 获取 openId 值为，openId = " + openId);
    
    // TODO 参数校验 支付金额
    String acount = request.getParameter("acount");
    System.out.println("进行支付页面中，获取支付金额，acount = " + acount);
    // 订单编号
    String orderCode = request.getParameter("orderCode");
    
    String body = "OrderCode: " + orderCode;
    String nonce_str = "2";
    String notify_url = "http://artlyt.com.cn/jsp/weixin/payResult.jsp";
    String out_trade_no = orderCode;
    String spbill_create_ip = "0.0.0.0";
    // 支付金额（单位分）
    String total_fee = String.valueOf((Double.parseDouble(acount) * 100));
    total_fee = total_fee.substring(0, total_fee.lastIndexOf("."));
    String trade_type = "JSAPI";
    String sign = WeiXinTokenUtil.sign(body, nonce_str, notify_url, openId, out_trade_no, spbill_create_ip, total_fee, trade_type);
    
    System.out.println("准备统一下单");
    String result = WeiXinTokenUtil.sendPayTrade(body, nonce_str, notify_url, openId, out_trade_no, spbill_create_ip, total_fee, trade_type, sign);
    System.out.println("进行支付页面中，调用统一下单结果返回，result = " + result);
    
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
		<title>进行支付</title>
	</head>
	<body>
		<script type="text/javascript">
		    // 当前页面 url
            //alert(location.href.split('#')[0]);
		
            /**
             * config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，
             * 所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，
             * 则可以直接调用，不需要放在ready函数中。
             */
             wx.config({
                 debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                 appId: "<%=appId%>", // 必填，公众号的唯一标识
                 nonceStr: "<%=nonceStr%>", // 必填，生成签名的随机串
                 timestamp: "<%=timestamp%>", // 必填，生成签名的时间戳
                 signature: "<%=signature%>",// 必填，签名，见附录1
                 jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
             });
             /**
              * config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，
              * 也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
              */
             wx.error(function(res) {
                 console.log("发生错误了，执行了wx.error()方法：" + res);
             });
            
            /**
             * ready 函数
             */
            wx.ready(function() {
                /**
                 * 支付接口
                 */
                wx.chooseWXPay({
                    timestamp: 2, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                    nonceStr: '2', // 支付签名随机串，不长于 32 位
                    'package': '<%=packageValue%>', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
                    signType: '<%=signType%>', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                    paySign: '<%=paySign%>', // 支付签名
                    success: function(res) {
                    	window.location.href = "<%=request.getContextPath()%>/orderctrl/myorder?orderStatus=<%=BasePojo.OrderDict.ORDER_STATUS_WAITING_RECV%>";
                    },
                    fail: function(res) {
                    	alert("支付失败，请在我的订单中查看订单，并尝试再次支付，谢谢！");
                    	window.location.href = "<%=request.getContextPath()%>/orderctrl/myorder?orderStatus=<%=BasePojo.OrderDict.ORDER_STATUS_WAITING_PAY%>";
                    }
                });
            });
        </script>
	</body>
</html>