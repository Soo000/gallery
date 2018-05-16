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
        
        <!-- 3图模块 -->
        <div class="container area">
            <div class="row">
                <div class="col-xs-6" style="padding: 0 1px 0 0">
                    <img src="<%=request.getContextPath() %>/res/img/product/10015/001.jpg" class="img-responsive" />
                </div>
                <div class="col-xs-6">
                    <div class="row">
                        <div class="col-xs-12" style="padding: 0 0 0 1px">
                            <img src="<%=request.getContextPath() %>/res/img/product/10003/001.jpg" class="img-responsive"/>
                        </div>
                        <div class="col-xs-12" style="padding: 0 0 0 1px">
                            <img src="<%=request.getContextPath() %>/res/img/product/10003/002.jpg" class="img-responsive"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- 1图模块 -->
        <div class="area">
            <div>
                <img class="area-single-pic" src="<%=request.getContextPath() %>/res/img/product/10003/002.jpg"/>
            </div>
        </div>
        
        <!-- 上新区 N图模块区域 -->
        <div class="container area">
            <div class="row">
                <div class="col-xs-12 area-title">
                                                    上&nbsp;新&nbsp;区
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <img src="<%=request.getContextPath() %>/res/img/product/00001/001.png" class="img-responsive"/>
                    </div>
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/001.jpg" class="img-responsive"/>
                            </div>
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/002.jpg" class="img-responsive"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/002.jpg" class="img-responsive"/>
                            </div>
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/001.jpg" class="img-responsive"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- 折扣区 N图模块区域 -->
        <div class="container area">
            <div class="row">
                <div class="col-xs-12 area-title">
                                                    折&nbsp;扣&nbsp;区
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <img src="<%=request.getContextPath() %>/res/img/product/00002/001.png" class="img-responsive"/>
                    </div>
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/001.jpg" class="img-responsive"/>
                            </div>
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/002.jpg" class="img-responsive"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/002.jpg" class="img-responsive"/>
                            </div>
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/001.jpg" class="img-responsive"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- 生活好物 N图模块区域 -->
        <div class="container area">
            <div class="row">
                <div class="col-xs-12 area-title">
                                                    生&nbsp;活&nbsp;好&nbsp;物
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <img src="<%=request.getContextPath() %>/res/img/product/00003/001.png" class="img-responsive"/>
                    </div>
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/001.jpg" class="img-responsive"/>
                            </div>
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/002.jpg" class="img-responsive"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/002.jpg" class="img-responsive"/>
                            </div>
                            <div class="col-xs-6" style="padding: 0 0 0 1px">
                                <img src="<%=request.getContextPath() %>/res/img/product/10002/001.jpg" class="img-responsive"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
        
        <div class="common-bottom-line">
                            我是有底线的
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