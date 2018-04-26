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
			/* wx.config({
			    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: '${appId}', // 必填，公众号的唯一标识
			    timestamp: 1, // 必填，生成签名的时间戳
			    nonceStr: '1', // 必填，生成签名的随机串
			    signature: '',// 必填，签名，见附录1
			    jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			
			wx.ready(function(){
			    /* config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，
			     * config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。
			     * 对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			     */
			});
			
			wx.error(function(res){
			    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			}); */
		
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