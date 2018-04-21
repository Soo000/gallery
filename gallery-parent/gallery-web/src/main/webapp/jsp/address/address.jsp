<%@page import="com.kkwrite.gallery.pojo.address.GlyAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8">
		<!-- 视口标签 -->
		<meta name="viewport" content="width=device-width, initial-scale=1"/>		
		<!-- 设置IE浏览器采用的解析模式 -->
		<meta http-equiv="X-UA-Campatible" content="IE=edge">
		<!-- Bootstrap CSS -->
		<link href="<%=request.getContextPath() %>/res/bootstrap-3.3.2/css/bootstrap.css" type="text/css" rel="stylesheet">
		<!-- Font-awesome -->
		<link href="<%=request.getContextPath() %>/res/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/css/common.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/gallery.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/address.css" type="text/css" rel="stylesheet"/>
		<!-- 兼容低版本浏览器 -->
	    <!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <!-- title -->
		<title>收货地址</title>
	    <!-- local -->
	    <style type="text/css">
	    	/*.row > div {
	    		border: 1px solid #C0A16B;
	    	}*/
	    </style>
	</head>
	<body>
		<!-- header -->
		<jsp:include page="../module/header.jsp">
			<jsp:param name="headTitle" value="收货地址"/>
			<jsp:param name="backUrl" value=''/>
		</jsp:include>
		
		<div class="container">
			<c:if test="${requestScope.from == 'order' }">
				<form id="goBackOrderForm" style="display: none;" action="<%=request.getContextPath() %>/orderctrl/placeorder">
					<c:forEach var="product" items="${requestScope.productIds }">
						<input name="productId" type="hidden" value="${product }"/>
					</c:forEach>
					<input class="addr" name="addrId" type="hidden">
				</form>
			</c:if>
			<c:choose>
				<c:when test="${requestScope.addresses != null }">
					<c:forEach var="address" items="${requestScope.addresses }">
						<div class="row address-item">
							<div class="col-xs-1">
								<div class="address-checkbox">
									<input type="checkbox" />
								</div>
							</div>
							<div class="col-xs-10">
								<c:if test="${requestScope.from == 'order' }"><a class="goBackOrderA"></c:if>
									<div class="address-contact contact-info-${address.isDefault }">
										<span style="display: none;">${address.addressId }</span>
										<span>${address.receiver }</span> &nbsp;&nbsp;&nbsp;&nbsp; <span>${address.phoneNum }</span> 
										<span class="type">${address.addressLabels }</span>
									</div>
									<div class="address-details">
										${address.address }
									</div>
								<c:if test="${requestScope.from == 'order' }"></a></c:if>
							</div>
							<div class="col-xs-1 address-edit">
								<a class="editAddress">
									<i class="fa fa-edit fa-2x"></i>
								</a>
								<a class="deleteAddress">
									<i class="fa fa-trash-o fa-2x"></i>
								</a>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
		
		<nav class="navbar navbar-fixed-bottom">
			<button id="btnAddAddress" class="btn btn-group-justified btn-danger">＋ 新建地址</button>
		</nav>
	
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	    <!-- gallery.js -->
	    <script src="<%=request.getContextPath() %>/res/js/gallery.js"></script>
	</body>
	<script type="text/javascript">
		$(".goBackOrderA").click(function(e) {
			e.preventDefault();
			var addrId = $($(this).find("div.address-contact span").first()).text();
			$("#goBackOrderForm input.addr").val(addrId);
			$("#goBackOrderForm").submit();
		});
		
		/**
		 * init
		 */
		$(document).ready(function() {
			$(".editAddress").click(function(e) {
				e.preventDefault();
				var info = $(this).parent().parent().find("div.address-contact span").first();
				window.location.href = "<%=request.getContextPath()%>/addressctrl/preedit?id=" + $(info).text();
			});
			
			$(".deleteAddress").click(function(e) {
				e.preventDefault();
				var info = $(this).parent().parent().find("div.address-contact span").first();
				window.location.href = "<%=request.getContextPath()%>/addressctrl/deladd?id=" + $(info).text();
			});
			
			$("#reservationBtn").on("click", function(evt) {
				window.location.href = "../reservation/reservation.html";
			});
			
			$("#btnAddAddress").on("click", function(evt) {
				window.location.href = "<%=request.getContextPath()%>/addressctrl/preadd";
			});
			
			/**
			 * 滚动监听
			 */
			$(window).scroll(function() {
				// scrollTop就是触发滚轮事件时滚轮的高度，为了保证兼容性，这里取两个值，哪个有值取哪一个　　
				var scrollTop = (document.documentElement.scrollTop) || (document.body.scrollTop);　　
				if (scrollTop < 400) {
					$("#reservationBtn").css("position", "fixed")
					$("#reservationBtn").css("left", "0");
					$("#reservationBtn").css("bottom", "0");
				} else {
					$("#reservationBtn").css("position", "static")
				}
			});
			
		});
	</script>
</html>