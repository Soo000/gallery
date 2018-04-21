<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<link href="<%=request.getContextPath() %>/res/css/categories.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/signup.css" type="text/css" rel="stylesheet"/>
		<!-- 兼容低版本浏览器 -->
	    <!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <!-- title -->
		<title>注册</title>
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
			<jsp:param name="headTitle" value="注册"/>
			<jsp:param name="backUrl" value=''/>
		</jsp:include>
	    
		<div class="container">
			<form style="margin-top: 50px;" method="post" action="<%=request.getContextPath()%>/signctrl/signup">
				<div class="form-group">
					<label for="phone_num">手机号码</label>
					<input type="text" class="form-control" id="phoneNum" name="phoneNum" placeholder="手机号码"> 
				</div>
				<div class="form-group">
			    	<label for="password">密码</label>
			    	<input type="password" class="form-control" name="password" placeholder="......">
				</div>
				<div class="form-group">
			    	<label for="password">验证码</label>
			    	<br />
			    	<input type="password" class="" name="verifyCode" placeholder="验证码" style="width: 165px; height: 30px;">
			    	<input type="button" class="btn btn-default" value="点击获取" />
				</div>
				
			  	<button type="submit" class="btn btn-danger btn-group-justified login-button">
			  		注册
			  	</button>
			</form>
		</div>
		
		<br/>
	    
	    <div style="margin-left: 15px;">
	    	已有账号 <a href="<%=request.getContextPath() %>/loginctrl/prelogin">登录</a>
	    </div>
	
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	    <!-- gallery.js -->
	    <script src="<%=request.getContextPath() %>/res/js/gallery.js"></script>
	</body>
	<script type="text/javascript">
		/**
		 * init
		 */
		$(document).ready(function() {
			$("#reservationBtn").on("click", function(evt) {
				window.location.href = "../reservation/reservation.html";
			});
	
			/**
			 * 滚动监听
			 */
			$(window).scroll(function() {
				// scrollTop就是触发滚轮事件时滚轮的高度，为了保证兼容性，这里取两个值，哪个有值取哪一个　　
				var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
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