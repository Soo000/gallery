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
        <c:choose>
            <c:when test="${requestScope.homeModuleVOs != null}">
                <c:forEach var="homeModuleVO" items="${requestScope.homeModuleVOs}">
                    <c:if test="${homeModuleVO.moduleTemplate == 0 }">
				        <div class="swiper-container">
				            <div class="swiper-wrapper">
				                <c:if test="${homeModuleVO.homeModuleItemVOs != null }">
				                    <c:forEach var="homeModuleItemVO" items="${homeModuleVO.homeModuleItemVOs}" varStatus="i">
				                        <div class="swiper-slide">
				                            <a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${homeModuleItemVO.productId }">
				                                <img src="<%=request.getContextPath() %>/res/img/${homeModuleItemVO.moduleItemImage}" 
				                                    class="carousel-item-img"/>
				                            </a>
				                        </div>
				                    </c:forEach>
				                </c:if>
				            </div>
				            <!-- 如果需要分页器 -->
				            <div class="swiper-pagination"></div>
				        </div>	                   
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 11 }">
                        <!-- 1图模块 -->
				        <div class="area">
				            <div>
				                <!-- 如果是广告连接 -->
				                <c:if test="${homeModuleVO.homeModuleItemVOs[0].moduleItemType == 0 }">
				                    <a href="<%=request.getContextPath()%>/${homeModuleVO.homeModuleItemVOs[0].activityUrl }">
						                <img class="area-single-pic" src="<%=request.getContextPath() %>/res/img/${homeModuleVO.homeModuleItemVOs[0].activityPictureFileName}"/>
				                    </a>
				                </c:if>
				                <!-- 如果是产品 -->
				                <c:if test="${homeModuleVO.homeModuleItemVOs[0].moduleItemType == 1 }">
				                    <a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${homeModuleVO.homeModuleItemVOs[0].productId}">
                                        <img class="area-single-pic" src="<%=request.getContextPath() %>/res/img/product/00006/001.jpg"/>
                                    </a>
				                </c:if>
				            </div>
				        </div>
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 21 }">
                        21
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 22 }">
                        22
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 22 }">
                        22
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 31 }">
	                   31
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 32 }">
				        <div class="container area">
				            <div class="row">
				                <div class="col-xs-6" style="">
				                    <c:if test="${homeModuleVO.homeModuleItemVOs[0] != null}">
                                        <a href="<%=request.getContextPath() %>/prodetailsctrl/pagectrl?productId=${homeModuleVO.homeModuleItemVOs[0].productId }">
                                            <img src="<%=request.getContextPath() %>/res/img/${homeModuleVO.homeModuleItemVOs[0].moduleItemImage}" 
                                              class="img-responsive" style="padding: 0 1px 0 0; height: 274px;"/>
                                        </a>
                                    </c:if>
				                </div>
				                <div class="col-xs-6" style="">
				                    <div class="container">
					                    <div class="row">
	                                        <div class="col-xs-12">
	                                            <c:if test="${homeModuleVO.homeModuleItemVOs[1] != null}">
	                                                <a href="<%=request.getContextPath() %>/prodetailsctrl/pagectrl?productId=${homeModuleVO.homeModuleItemVOs[1].productId }">
	                                                    <img src="<%=request.getContextPath() %>/res/img/${homeModuleVO.homeModuleItemVOs[1].moduleItemImage}" 
	                                                      class="img-responsive" style="padding: 0 0 0 0;"/>
	                                                </a>
	                                            </c:if>
	                                        </div>
	                                    </div>
				                    </div>
				                    <div class="container">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <c:if test="${homeModuleVO.homeModuleItemVOs[2] != null}">
                                                    <a href="<%=request.getContextPath() %>/prodetailsctrl/pagectrl?productId=${homeModuleVO.homeModuleItemVOs[2].productId }">
                                                        <img src="<%=request.getContextPath() %>/res/img/${homeModuleVO.homeModuleItemVOs[2].moduleItemImage}" 
                                                          class="img-responsive" style="padding: 1px 0 0 0;"/>
                                                    </a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
				                </div>
				            </div>
				        </div>                       
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 33 }">
                       33
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 34 }">
                       34
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 41 }">
                       41
                    </c:if>
                    <c:if test="${homeModuleVO.moduleTemplate == 51 }">
						<div class="container area">
						    <div class="row">
						        <div class="col-xs-12 area-title">
						            ${homeModuleVO.moduleTitle }
						        </div>
						        <c:if test="${homeModuleVO.homeModuleItemVOs != null}">
									<div class="row">
									    <div class="col-xs-12" style="margin-bottom: 1px;">
									        <c:if test="${homeModuleVO.homeModuleItemVOs[0] != null}">
                                                <c:choose>
                                                   <c:when test="${homeModuleItemVO.moduleItemType == 1}">
                                                       <a href="<%=request.getContextPath() %>/prodetailsctrl/pagectrl?productId=${homeModuleVO.homeModuleItemVOs[0].productId }">
                                                           <img src="<%=request.getContextPath() %>/res/img/${homeModuleVO.homeModuleItemVOs[0].moduleItemImage}" class="img-responsive"/>
                                                       </a>
                                                   </c:when>
                                                   <c:otherwise>
                                                       <a href="<%=request.getContextPath() %>/${homeModuleVO.homeModuleItemVOs[0].activityUrl }">
                                                           <img src="<%=request.getContextPath() %>/res/img/${homeModuleVO.homeModuleItemVOs[0].activityPictureFileName}" class="img-responsive"/>
                                                       </a>
                                                   </c:otherwise>
                                               </c:choose>
									        </c:if>
									    </div>
									    <div class="col-xs-12">
									        <div class="row">
									           <c:if test="${homeModuleVO.homeModuleItemVOs[1] != null }">
									               <c:forEach var="homeModuleItemVO" items="${homeModuleVO.homeModuleItemVOs }" begin="1" varStatus="i">
									                   <div class="col-xs-6" style="padding: 0 1px 1px 0">
									                       <c:choose>
									                           <c:when test="${homeModuleItemVO.moduleItemType == 1}">
									                               <a href="<%=request.getContextPath() %>/prodetailsctrl/pagectrl?productId=${homeModuleItemVO.productId }">
									                                   <img src="<%=request.getContextPath() %>/res/img/${homeModuleItemVO.moduleItemImage}" class="img-responsive"/>
									                               </a>
									                           </c:when>
									                           <c:otherwise>
									                               <a href="<%=request.getContextPath()%>/${homeModuleItemVO.activityUrl } }">
                                                                       <img src="<%=request.getContextPath() %>/res/img/${homeModuleItemVO.activityPictureFileName}" class="img-responsive"/>
                                                                   </a>
									                           </c:otherwise>
									                       </c:choose>
                                                        </div>
                                                        <c:if test="${i.count % 2 == 0 }">
								                            <c:out value="</div>" escapeXml="false"/>
								                            <c:out value='<div class="row">' escapeXml="false"/>
								                        </c:if>
									               </c:forEach>
									           </c:if>
									        </div>
									    </div>
									</div>
						        </c:if>
						    </div>
						</div>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div style="text-align: center;">首页空空如也</div>
            </c:otherwise>
        </c:choose>
        
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