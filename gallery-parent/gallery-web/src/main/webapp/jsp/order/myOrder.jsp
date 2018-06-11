<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kkwrite.gallery.pojo.BasePojo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link href="<%=request.getContextPath() %>/res/css/myOrder.css" type="text/css" rel="stylesheet"/>
		<title>我的订单</title>
	</head>
	<body>
		<div>
			<!-- 订单标签页 -->
			<ul class="nav nav-tabs order-tab-header" role="tablist" id="orderTab">
				<li role="presentation" class="active">
					<a href="#allOrder" aria-controls="home" role="tab" data-toggle="tab">全部</a>
				</li>
				<li role="presentation">
					<a href="#waitingPay" aria-controls="profile" role="tab" data-toggle="tab">待付款</a>
				</li>
				<li role="presentation">
					<a href="#waitingReceive" aria-controls="messages" role="tab" data-toggle="tab">待收货</a>
				</li>
				<li role="presentation">
					<a href="#finishOrder" aria-controls="settings" role="tab" data-toggle="tab">已完成</a>
				</li>
				<li role="presentation">
					<a href="#cancelOrder" aria-controls="settings" role="tab" data-toggle="tab">已取消</a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="allOrder">
					<c:choose>
						<c:when test="${requestScope.ordersProducts != null }">
							<c:forEach var="orderProducts" items="${requestScope.ordersProducts }">
								<div class="order-item">
									<div class="header">
										<div class="order-title">
											${orderProducts.orderCode }&nbsp;&nbsp;&nbsp;&nbsp;
											${orderProducts.orderStatusText }
										</div>
									</div>
									<div class="body">
										<c:choose>
											<c:when test="${orderProducts.productClassNum == 1 }">
												<div class="pull-left">
													<img src="<%=request.getContextPath() %>/res/img/${orderProducts.products[0].productPictures[0].productPictureFileName }" 
														class="img-responsive product-pic"/>
												</div>
												<div class="pull-left">
													<div class="product-name">${orderProducts.products[0].productName }</div>
													<div class="product-intro">${orderProducts.products[0].productIntro }</div>
													<div class="product-num">
														× ${orderProducts.productNum }
													</div>
												</div>
												<div class="clearfix"></div>
											</c:when>
											<c:otherwise>
												<c:forEach var="product" items="${orderProducts.products }">
													<div class="pull-left">
														<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
															class="img-responsive product-pic"/>
													</div>
												</c:forEach>
												<div class="clearfix"></div>
											</c:otherwise>
										</c:choose>
									</div>
									<div class="footer">
										<div class="order-info">
											共 ${orderProducts.productNum } 件商品&nbsp;&nbsp;&nbsp;&nbsp;
											需付款 <span>￥${orderProducts.realPayment }</span> 元
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
				<div role="tabpanel" class="tab-pane" id="waitingPay">
					<c:choose>
						<c:when test="${requestScope.ordersProducts != null }">
							<c:forEach var="orderProducts" items="${requestScope.ordersProducts }">
								<!-- 判断是否待付款订单 -->
								<c:choose>
									<c:when test="${orderProducts.orderStatus == 1 }">
										<div class="order-item">
											<div class="header">
												<div class="order-title">
													${orderProducts.orderCode }&nbsp;&nbsp;&nbsp;&nbsp;
													${orderProducts.orderStatusText }
												</div>
											</div>
											<div class="body">
												<c:choose>
													<c:when test="${orderProducts.productClassNum == 1 }">
														<div class="pull-left">
															<img src="<%=request.getContextPath() %>/res/img/${orderProducts.products[0].productPictures[0].productPictureFileName }" 
																class="img-responsive product-pic"/>
														</div>
														<div class="pull-left">
															<div class="product-name">${orderProducts.products[0].productName }</div>
															<div class="product-intro">${orderProducts.products[0].productIntro }</div>
															<div class="product-num">
																× ${orderProducts.productNum }
															</div>
														</div>
														<div class="clearfix"></div>
													</c:when>
													<c:otherwise>
														<c:forEach var="product" items="${orderProducts.products }">
															<div class="pull-left">
																<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
																	class="img-responsive product-pic"/>
															</div>
														</c:forEach>
														<div class="clearfix"></div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="footer">
												<div class="order-info">
													共 ${orderProducts.productNum } 件商品&nbsp;&nbsp;&nbsp;&nbsp;
													需付款 <span>￥${orderProducts.realPayment }</span> 元
												</div>
												<div class="order-buttons-bar">
													<div class="pull-right order-buttons">
														<button name="cancelOrderBtn" 
															orderCode="${orderProducts.orderCode }"
															orderStatus="${orderProducts.orderStatus }"
															type="button" class="btn btn-default btn-xs">
															取消订单
														</button>
														<a type="button" class="btn btn-warning btn-xs"
														    href="<%=request.getContextPath()%>/jsp/weixin/callPay.jsp?orderCode=${orderProducts.orderCode }&acount=${orderProducts.realPayment }&count=${orderProducts.productNum }&openId=${requestScope.openId}">
														          去支付
														</a>
													</div>
													<div class="clearfix"></div>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
				<div role="tabpanel" class="tab-pane" id="waitingReceive">
					<c:choose>
						<c:when test="${requestScope.ordersProducts != null }">
							<c:forEach var="orderProducts" items="${requestScope.ordersProducts }">
								<!-- 判断是否待收货订单 -->
								<c:choose>
									<c:when test="${orderProducts.orderStatus == 3 }">
										<div class="order-item">
											<div class="header">
												<div class="order-title">
													${orderProducts.orderCode }&nbsp;&nbsp;&nbsp;&nbsp;
													${orderProducts.orderStatusText }
												</div>
											</div>
											<div class="body">
												<c:choose>
													<c:when test="${orderProducts.productClassNum == 1 }">
														<div class="pull-left">
															<img src="<%=request.getContextPath() %>/res/img/${orderProducts.products[0].productPictures[0].productPictureFileName }" 
																class="img-responsive product-pic"/>
														</div>
														<div class="pull-left">
															<div class="product-name">${orderProducts.products[0].productName }</div>
															<div class="product-intro">${orderProducts.products[0].productIntro }</div>
															<div class="product-num">
																× ${orderProducts.productNum }
															</div>
														</div>
														<div class="clearfix"></div>
													</c:when>
													<c:otherwise>
														<c:forEach var="product" items="${orderProducts.products }">
															<div class="pull-left">
																<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
																	class="img-responsive product-pic"/>
															</div>
														</c:forEach>
														<div class="clearfix"></div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="footer">
												<div class="order-info">
													共 ${orderProducts.productNum } 件商品&nbsp;&nbsp;&nbsp;&nbsp;
													需付款 <span>￥${orderProducts.realPayment }</span> 元
												</div>
												<div class="order-buttons-bar">
													<div class="pull-right order-buttons">
														<input type="button" class="btn btn-primary btn-xs" value="收货"/>
													</div>
													<div class="clearfix"></div>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
				<div role="tabpanel" class="tab-pane" id="finishOrder">
					<c:choose>
						<c:when test="${requestScope.ordersProducts != null }">
							<c:forEach var="orderProducts" items="${requestScope.ordersProducts }">
								<!-- 判断是否已完成订单 -->
								<c:choose>
									<c:when test="${orderProducts.orderStatus == 4 }">
										<div class="order-item">
											<div class="header">
												<div class="order-title">
													${orderProducts.orderCode }&nbsp;&nbsp;&nbsp;&nbsp;
													${orderProducts.orderStatusText }
												</div>
											</div>
											<div class="body">
												<c:choose>
													<c:when test="${orderProducts.productClassNum == 1 }">
														<div class="pull-left">
															<img src="<%=request.getContextPath() %>/res/img/${orderProducts.products[0].productPictures[0].productPictureFileName }" 
																class="img-responsive product-pic"/>
														</div>
														<div class="pull-left">
															<div class="product-name">${orderProducts.products[0].productName }</div>
															<div class="product-intro">${orderProducts.products[0].productIntro }</div>
															<div class="product-num">
																× ${orderProducts.productNum }
															</div>
														</div>
														<div class="clearfix"></div>
													</c:when>
													<c:otherwise>
														<c:forEach var="product" items="${orderProducts.products }">
															<div class="pull-left">
																<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
																	class="img-responsive product-pic"/>
															</div>
														</c:forEach>
														<div class="clearfix"></div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="footer">
												<div class="order-info">
													共 ${orderProducts.productNum } 件商品&nbsp;&nbsp;&nbsp;&nbsp;
													需付款 <span>￥${orderProducts.realPayment }</span> 元
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
				<div role="tabpanel" class="tab-pane" id="cancelOrder">
					<c:choose>
						<c:when test="${requestScope.ordersProducts != null }">
							<c:forEach var="orderProducts" items="${requestScope.ordersProducts }">
								<!-- 判断是否已取消订单 -->
								<c:choose>
									<c:when test="${orderProducts.orderStatus == 6 }">
										<div class="order-item">
											<div class="header">
												<div class="order-title">
													${orderProducts.orderCode }&nbsp;&nbsp;&nbsp;&nbsp;
													${orderProducts.orderStatusText }
													&nbsp;&nbsp;|&nbsp;&nbsp;
													<i name="deleteOrderBtn" orderCode="${orderProducts.orderCode }" class="fa fa-trash-o fa-2x"></i>
												</div>
											</div>
											<div class="body">
												<c:choose>
													<c:when test="${orderProducts.productClassNum == 1 }">
														<div class="pull-left">
															<img src="<%=request.getContextPath() %>/res/img/${orderProducts.products[0].productPictures[0].productPictureFileName }" 
																class="img-responsive product-pic"/>
														</div>
														<div class="pull-left">
															<div class="product-name">${orderProducts.products[0].productName }</div>
															<div class="product-intro">${orderProducts.products[0].productIntro }</div>
															<div class="product-num">
																× ${orderProducts.productNum }
															</div>
														</div>
														<div class="clearfix"></div>
													</c:when>
													<c:otherwise>
														<c:forEach var="product" items="${orderProducts.products }">
															<div class="pull-left">
																<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
																	class="img-responsive product-pic"/>
															</div>
														</c:forEach>
														<div class="clearfix"></div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="footer">
												<div class="order-info">
													共 ${orderProducts.productNum } 件商品&nbsp;&nbsp;&nbsp;&nbsp;
													需付款 <span>￥${orderProducts.realPayment }</span> 元
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<%@ include file="../module/footRes.jsp" %>
		<script src="<%=request.getContextPath() %>/res/layer.mobile-v2.0/layer_mobile/layer.js"></script>
	</body>
	<script type="text/javascript">
		/**
		 * init
		 */
		$(document).ready(function() {
			var orderStatus = "${requestScope.orderStatus }";
			if (orderStatus == "1") {
				$('#orderTab a[href="#waitingPay"]').tab('show');
			} else if (orderStatus == "3") {
				$('#orderTab a[href="#waitingReceive"]').tab('show');
			} else if (orderStatus == "6") {
				$('#orderTab a[href="#cancelOrder"]').tab('show');
			}
			
			/**
			 * 取消订单动作
			 */
			$("button[name='cancelOrderBtn']").click(function(evt) {
				var orderCode = $(this).attr("orderCode");
				var orderStatus = $(this).attr("orderStatus");
				
				layer.open({
				    content: '确定要取消吗？',
				    btn: ['取消', '不取消'],
				    yes: function(index) {
				    	window.location.href = "/orderctrl/setorderstatus" +
				    		"?orderCode=" +  orderCode +
				    		"&orderStatus=" + orderStatus +
				    		"&newOrderStatus=6";
				    }
				});
			});
			
			/**
			 * 删除订单动作
			 */
			$("i[name='deleteOrderBtn']").click(function(evt) {
				var orderCode = $(this).attr("orderCode");
				
				layer.open({
				    content: '确定要删除吗？',
				    btn: ['删除', '不删除'],
				    yes: function(index) {
				    	window.location.href = "/orderctrl/deleteorder" +
				    		"?orderCode=" +  orderCode;
				    }
				});
			});
		});
	</script>
</html>