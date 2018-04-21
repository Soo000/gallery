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
		<link href="<%=request.getContextPath() %>/res/bootstrap-3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/css/common.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/gallery.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/reservation.css" type="text/css" rel="stylesheet"/>
		<!-- 兼容低版本浏览器 -->
	    <!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <!-- title -->
		<title>预约服务</title>
	    <!-- local -->
	    <style type="text/css">
	    </style>
	</head>
	<body>
		<jsp:include page="../module/header.jsp">
			<jsp:param name="headTitle" value="预约结果"/>
			<jsp:param name="backUrl" value='../../homectrl/pagectrl'/>
		</jsp:include>
		
		<div class="container">
			<c:choose>
				<c:when test="${requestScope.reservationResult == true}">
					预约成功
				</c:when>
				<c:otherwise>
					预约失败
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">
	</script>
</html>