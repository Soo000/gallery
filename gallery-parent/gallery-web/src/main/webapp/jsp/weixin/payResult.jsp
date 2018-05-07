<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.util.Map" %>
<%@ page import="javax.servlet.ServletInputStream" %>
<%@ page import="com.kkwrite.gallery.common.XmlUtil" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.kkwrite.gallery.service.order.OrderService" %>
<%@ page import="com.kkwrite.gallery.pojo.BasePojo" %>

<!DOCTYPE html>
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
		<title>支付结果通知</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<%
		   ServletInputStream is = request.getInputStream();
		   InputStreamReader isr = new InputStreamReader(is, "utf-8");
           BufferedReader br = new BufferedReader(isr);
           StringBuffer buffer = new StringBuffer();
           String line = null;
           while ((line = br.readLine()) != null) {
               buffer.append(line);
           }
           
           System.out.println("回调返回数据：" + buffer.toString());
           
           Map<String, String> payResult = XmlUtil.tradeXmlToMap(buffer.toString());
           System.out.println("payResult = " + payResult);
           
           String returnCode = payResult.get("return_code");
           if ("SUCCESS".equals(returnCode)) {
        	   String outTradeNo = payResult.get("out_trade_no");
        	   System.out.println("订单 " + outTradeNo + " 支付成功！");
        	   
        	   WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        	   OrderService orderService = (OrderService) context.getBean("orderService");
        	   System.out.println("orderService = " + orderService);
        	   int updateResult = orderService.setOrderStatus(outTradeNo, BasePojo.OrderDict.ORDER_STATUS_WAITING_RECV);
        	   System.out.println("updateResult = " + updateResult);
           }
           
           response.getWriter().write("SUCCESS");
           response.getWriter().flush();
           System.out.println("写回 SUCCESS");
           System.out.println("-------------------");
		%>
	</body>
</html>