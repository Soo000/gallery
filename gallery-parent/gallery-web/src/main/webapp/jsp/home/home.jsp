<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kkwrite.gallery.pojo.BasePojo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/res/swiper-3.4.2/css/swiper.min.css">
		<link href="<%=request.getContextPath() %>/res/css/home.css" type="text/css" rel="stylesheet"/>
		<title>小画仓</title>
	</head>
	<body style="margin-top: 0; margin-bottom: 50px;">
	    <!-- 首页轮播图 -->
	    <div class="swiper-container">
		    <div class="swiper-wrapper">
				<c:if test="${requestScope.carouselItems != null }">
					<c:forEach var="carouselItem" items="${requestScope.carouselItems}" varStatus="i">
						<div class="swiper-slide">
							<a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${carouselItem.productId }">
								<img src="<%=request.getContextPath() %>/res/img/${carouselItem.moduleItemImage}" class="carousel-item-img"/>
							</a>
						</div>
					</c:forEach>
				</c:if>
		    </div>
		    <!-- 如果需要分页器 -->
		    <div class="swiper-pagination"></div>
		</div>
		
		<div style="margin-top: 1px; background: rgba(255,255,255);">
		</div>
		
		<div class="container-fluid area">
			<!-- 上新区 -->
			<c:if test="${requestScope.newAreaModuleItem != null }">
				<div class="row">
					<div class="col-xs-12">
						<div class="area-title">
							<img src="<%=request.getContextPath() %>/res/img/common/area_bg.png" class="area-pic"/>
							<div class="area-text">${requestScope.newAreaModuleItem.moduleItemName }</div>
						</div>
					</div>
					<div class="col-xs-12">
						<a href="<%=request.getContextPath() %>/categoriesctrl/pagectrl?productTypeId=<%=BasePojo.ProductType.PRODUCT_TYPE_NEW%>">
							<img src="<%=request.getContextPath() %>/res/img/${requestScope.newAreaModuleItem.moduleItemImage }" class="img-responsive"/>	
						</a>
					</div>
				</div>
			</c:if>
			<!-- 折扣区 -->
			<c:if test="${requestScope.discountModuleItem != null }">
				<div class="row">
					<div class="col-xs-12">
						<div class="area-title">
							<img src="<%=request.getContextPath() %>/res/img/common/area_bg.png" class="area-pic"/>
							<div class="area-text">${requestScope.discountModuleItem.moduleItemName }</div>
						</div>
					</div>
					<div class="col-xs-12">
						<a href="<%=request.getContextPath() %>/categoriesctrl/pagectrl?productTypeId=<%=BasePojo.ProductType.PRODUCT_TYPE_DISCOUNT%>">
							<img src="<%=request.getContextPath() %>/res/img/${requestScope.discountModuleItem.moduleItemImage }" class="img-responsive"/>	
						</a>
					</div>
				</div>
			</c:if>
			<!-- 生活好物 -->
			<c:if test="${requestScope.goodsModuleItem != null }">
				<div class="row">
					<div class="col-xs-12">
						<div class="area-title">
							<img src="<%=request.getContextPath() %>/res/img/common/area_bg.png" class="area-pic"/>
							<div class="area-text">${requestScope.goodsModuleItem.moduleItemName }</div>
						</div>
					</div>
					<div class="col-xs-12">
						<a href="<%=request.getContextPath() %>/categoriesctrl/pagectrl?productTypeId=<%=BasePojo.ProductType.PRODUCT_TYPE_GOODS%>">
							<img src="<%=request.getContextPath() %>/res/img/${requestScope.goodsModuleItem.moduleItemImage }" class="img-responsive"/>	
						</a>
					</div>
				</div>
			</c:if>
		</div>
		
		<div style="position: fixed; left: 0; bottom:80px;">
			<button style="line-height:1.2; width: 30px; font-size:11px; padding: 1px;" id="reservationBtn" class="btn btn-danger" >
				立即<br>预约
			</button>
		</div>
		
		<jsp:include page="../module/footer.jsp">
			<jsp:param name="menuId" value="home"/>
		</jsp:include>
		
		<%@ include file="../module/footRes.jsp" %>
		<script src="<%=request.getContextPath() %>/res/swiper-3.4.2/js/swiper.jquery.min.js"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		<script type="text/javascript">
			/**
			 * init
			 */
			$(document).ready(function() {
				// 轮播
				var mySwiper = new Swiper ('.swiper-container', {
				    loop: true,
				 	// 如果需要分页器
				    pagination: '.swiper-pagination'
				  });
				// 预约按钮单击事件
				$("#reservationBtn").click(function() {
					window.location.href = "<%=request.getContextPath() %>/reservationctrl/pagectrl";
				});
			});
		</script>
	</body>
</html>