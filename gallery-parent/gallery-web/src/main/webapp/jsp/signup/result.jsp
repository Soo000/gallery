<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- 添加 视口标签 -->
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no"/>		
		<!-- 设置IE浏览器采用的解析模式 -->
		<meta http-equiv="X-UA-Campatible" content="IE=edge">
		
		<!-- Bootstrap CSS -->
		<link href="<%=request.getContextPath() %>/res/bootstrap-3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/css/common.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/gallery.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/categories.css" type="text/css" rel="stylesheet"/>
	    <!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
		<title>注册结果</title>
		
		<style>
		</style>
	</head>	
	<body>
		<jsp:include page="../module/header.jsp">
			<jsp:param name="headTitle" value="注册结果"/>
			<jsp:param name="backUrl" value='../../home/home.jsp'/>
		</jsp:include>
		
		<div class="container">
			<c:choose>
				<c:when test="${retCode == 1}">
					注册成功，请<a href="<%=request.getContextPath()%>/loginctrl/prelogin">登录</a>
				</c:when>
				<c:otherwise>
					注册失败
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	    <!-- gallery.js -->
	    <script src="<%=request.getContextPath() %>/res/js/gallery.js"></script>
	</body>
</html>