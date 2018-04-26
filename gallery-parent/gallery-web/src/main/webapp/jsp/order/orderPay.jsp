<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kkwrite.gallery.component.weixin.WeiXinTokenUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 将支付信息保存到session
	String orderCode = (String) request.getAttribute("orderCode");
	session.setAttribute("orderCode", orderCode);
	System.out.println("Session中保存订单编号 orderCode = " + orderCode);
	
	Float acount = (Float) request.getAttribute("acount");
	session.setAttribute("acount", acount);
	System.out.println("Session中保存支付的金额 account = " + acount);
	
	Integer count = (Integer) request.getAttribute("count");
	session.setAttribute("count", request.getAttribute("count"));
	System.out.println("Session中保存购买数量 count = " + count);
%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link href="<%=request.getContextPath() %>/res/res/css/orderpay.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		<title>小画仓收银台</title>
	</head>
	<body>
		<!-- header -->
		<jsp:include page="../module/header.jsp">
			<jsp:param name="headTitle" value="小画仓收银台"/>
			<jsp:param name="backUrl" value='../orderctrl/pagectrl'/>
		</jsp:include>
		
		<div class="alert alert-info" role="alert">
			需支付：￥ ${requestScope.acount }元
		</div>
		
		<!-- 支付 form -->
		<div class="panel panel-primary">
			<div class="panel-heading">
				支付方式
			</div>
			<div class="panel-body">
				微信支付（目前只支持微信支付）
			</div>
			<!-- <div class="panel-footer">
			</div> -->
		</div>
	
		<nav class="navbar navbar-default navbar-fixed-bottom nav-menubar">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<input type="button" id="payButton" class="btn btn-danger btn-block" 
							value="支付 ${requestScope.acount } 元">
					</div>
				</div>
			</div>
		</nav>	
		<%@ include file="../module/footRes.jsp" %>
	</body>
	<script type="text/javascript">
		/**
		 * init
		 */
		$(document).ready(function() {
			/**
			 * 支付按钮动作
			 */
			$("#payButton").on("click", function(evt) {
				var callPayUrl = "<%=request.getContextPath()%>/jsp/weixin/callPay.jsp?orderCode=<%=orderCode%>&acount=<%=acount%>&count=<%=count%>";
				window.location.href = callPayUrl;
			});
		});
	</script>
</html>