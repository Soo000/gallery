<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kkwrite.gallery.pojo.BasePojo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link href="<%=request.getContextPath() %>/res/css/myself.css" type="text/css" rel="stylesheet"/>
		<title>个人中心</title>
	</head>
	<body style="margin-top: 0">
		<div class="myself-header">
			<img src="<%=request.getContextPath() %>/res/img/common/myself-head.png" class="img-responsive pull-left pic" />
			<div class="pull-left persnal-info">
				<div class="phone-number">
					${requestScope.username }
				</div>
				<div class="level">
					金牌会员
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<div class="container" style="border-bottom: 1px solid #ccc; background-color: #FFF;">
			<div class="row" style="padding-top: 10px;">
				<div class="col-xs-3 text-center">
					<div class="">0</div>
					<div class="">收藏</div>
				</div>
				<div class="col-xs-1">
					<div style="width: 1px; height: 35px; border-left: 1px dashed #B17979;; margin: 8px 0;"></div>
				</div>
				<div class="col-xs-4 text-center">
					<div class="">0</div>
					<div class="">积分</div>
				</div>
				<div class="col-xs-1">
					<div style="width: 1px; height: 35px; border-left: 1px dashed #B17979;; margin: 8px 0;"></div>
				</div>
				<div class="col-xs-3 text-center">
					<div class="">0</div>
					<div class="">优惠券</div>
				</div>
			</div>
		</div>
		
		<br />
		
		<div class="panel panel-info">
			<div class="panel-heading">
				我的订单
			</div>
			<div class="panel-body" style="padding-top: 10px;">
				<div class="container order">
					<div class="row header">
						<div class="col-xs-3">
							<a href="<%=request.getContextPath()%>/orderctrl/myorder?orderStatus=<%=BasePojo.OrderDict.ORDER_STATUS_WAITING_PAY%>">
								<div>
									<i class="fa fa-cc-paypal fa-2x menu-icon"></i>
								</div>
								<div>待付款(${requestScope.orderInfo.waitingNum })</div>
							</a>
						</div>
						<div class="col-xs-3">
							<a href="<%=request.getContextPath()%>/orderctrl/myorder?orderStatus=<%=BasePojo.OrderDict.ORDER_STATUS_WAITING_RECV%>">
								<div>
									<i class="fa fa-archive fa-2x menu-icon"></i>
								</div>
								<div>待收货</div>
							</a>
						</div>
						<div class="col-xs-3">
							<a href="<%=request.getContextPath()%>/orderctrl/myorder?orderStatus=<%=BasePojo.OrderDict.ORDER_STATUS_WAITING_PAY%>">
								<div>
									<i class="fa fa-commenting fa-2x menu-icon"></i>
								</div>
								<div>待评价</div>
							</a>
						</div>
						<div class="col-xs-3">
							<a href="<%=request.getContextPath()%>/orderctrl/myorder?orderStatus=0">
								<div>
									<i class="fa fa-square fa-2x menu-icon"></i>
								</div>
								我的订单(${requestScope.orderInfo.allNum })
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<ul class="list-group" style="margin-bottom: 5px;">
		    <li class="list-group-item">
		    	<a href="../addressctrl/pagectrl">
			    	<div class="pull-left">我的预约</div>
			    	<i class="fa fa-angle-right fa-2x pull-right"></i>
			    	<div class="clearfix"></div>
		    	</a>
		    </li>
		</ul>
		
		<ul class="list-group">
		    <li class="list-group-item">
		    	<a href="../addressctrl/pagectrl">
			    	<div class="pull-left">地址管理</div>
			    	<i class="fa fa-angle-right fa-2x pull-right"></i>
			    	<div class="clearfix"></div>
		    	</a>
		    </li>
		    <li class="list-group-item">
		    	<a href="">
			    	<div class="pull-left">接触绑定</div>
			    	<i class="fa fa-angle-right fa-2x pull-right"></i>
			    	<div class="clearfix"></div>
		    	</a>
		    </li>
		</ul>
		
		<div style="margin-bottom: 50px;">
			&nbsp;
		</div>
	
		<jsp:include page="../module/footer.jsp">
			<jsp:param name="menuId" value="myself"/>
		</jsp:include>
		
		<%@ include file="../module/footRes.jsp" %>
	</body>
	<script type="text/javascript">
	</script>
</html>