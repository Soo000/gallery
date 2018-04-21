<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
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
	    
		<title>登录</title>
		
		<style>
		</style>
	</head>
	<body>
	    <div class="container">
		    <!-- header -->
			<jsp:include page="../module/header.jsp">
				<jsp:param name="headTitle" value="登录"/>
				<jsp:param name="backUrl" value=''/>
			</jsp:include>
		    
		    <div style="margin-top: 50px;"></div>
		    
		    <form name='loginForm' action='<%=request.getContextPath() %>/loginctrl/dologin' method='POST'>
				<div class="form-group">
					<label for="username">账号</label>
					<input type="text" class="form-control" id="username" name="username" placeholder="手机号码/账号/邮箱"> 
				</div>
				<div class="form-group">
			    	<label for="password">密码</label>
			    	<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
				</div>
				<div class="checkbox">
			    	<label>
			      		<input type="checkbox"> 记住登录
			    	</label>
				</div>
			  	<button type="submit" class="btn btn-danger btn-group-justified login-button">
			  		登录
			  	</button>
			</form>
		</div>
		
		<br/>
	    
	    <div class="text-center">
	    	<a href="<%=request.getContextPath()%>/jsp/myself/myself.jsp">个人中心</a> &nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="<%=request.getContextPath()%>/jsp/myself/about.jsp">关于小画仓</a> &nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="<%=request.getContextPath()%>/jsp/signup/signup.jsp">注册</a> &nbsp;&nbsp;&nbsp;&nbsp;
	    </div>
	    
	    <%--@include file="../module/footer.jsp" --%>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	    <!-- gallery.js -->
	    <script src="<%=request.getContextPath() %>/res/js/gallery.js"></script>
	</body>
</html>