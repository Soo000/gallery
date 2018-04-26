<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8">
		<!-- 视口标签 -->
		<meta name="viewport" content="width=device-width, initial-scale=1"/>		
		<!-- 设置IE浏览器采用的解析模式 -->
		<meta http-equiv="X-UA-Campatible" content="IE=edge">
		<!-- Bootstrap CSS -->
		<link href="<%=request.getContextPath() %>/res/bootstrap-3.3.2/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/css/common.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/gallery.css" type="text/css" rel="stylesheet"/>
		<!-- 兼容低版本浏览器 -->
		<!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- title -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/res/swiper-3.4.2/css/swiper.min.css">
		<link href="<%=request.getContextPath() %>/res/css/prodetails.css" type="text/css" rel="stylesheet"/>
		<title>产品详情</title>
	</head>
	<body style="margin-top: 0">
		<!-- 如果没有产品信息，则跳转到错误页面 //TODO -->
		<c:if test="${requestScope.product == null }">
			<c:redirect url=""/>
		</c:if>
		<div class="product-info">
			<div class="product-detail">
				${requestScope.product.productDetail }
			</div>
			<div class="product-features">
				<c:if test="${requestScope.product.productTypes != null}">
					<c:forEach var="productType" items="${requestScope.product.productTypes }">
						${productType.productTypeName }
					</c:forEach>
				</c:if>
				<span class="label label-info">中式</span>
				<span class="label label-info">客厅</span>
				<span class="label label-info">卧室</span>
			</div>
		</div>
		<!-- 产品轮播图 -->
	    <div class="swiper-container">
		    <div class="swiper-wrapper">
				<c:if test="${requestScope.product.productPictures != null }">
					<c:forEach var="productPicture" items="${requestScope.product.productPictures}" varStatus="i">
						<c:if test='${productPicture.productPictureType != null 
							&& (productPicture.productPictureType == "11" || productPicture.productPictureType == "12") }'>
							<div class="swiper-slide">
							<img src="<%=request.getContextPath() %>/res/img/${productPicture.productPictureFileName}" class="img-responsive"/>
						</div>
						</c:if>
					</c:forEach>
				</c:if>
		    </div>
		    <!-- 如果需要分页器 -->
		    <div class="swiper-pagination"></div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading" style="text-align: center;">
				产品选择
			</div>
			<!-- <div class="panel-body">
			</div> -->
			<ul class="list-group">
			    <li class="list-group-item">
			    	<div class="row" style="padding-left: 15px;">
			    		<div class="col-xs-4">
							<span class="price-text">价格：</span> 
							<span class="price-value">￥${requestScope.product.initialPrice }</span>
						</div>
						<div class="col-xs-4">
							<span class="discount-text">折扣价：</span> 
							<span class="discount-value">￥${requestScope.product.realPrice }</span>
						</div>
						<div class="col-xs-4">
							已售 ${requestScope.product.saleNumber }幅
						</div>
			    	</div>
			    </li>
			    <li class="list-group-item" id="chooseAttr">
			    	请选择尺寸、画框
			    	<i class="fa fa-angle-right fa-2x pull-right"></i>
			    </li>
			    <li class="list-group-item commitment-bar" >
			    	<span>
						<i class="fa fa-check-circle-o"></i>
						<span >正品保证</span>
					</span>
					<span>
						<i class="fa fa-check-circle-o"></i>
						<span>会员折扣</span>
					</span>
					<span>
						<i class="fa fa-check-circle-o"></i>
						<span>7天退货</span>
					</span>
					<span>
						<i class="fa fa-check-circle-o"></i>
						<span>发/退货说明</span>
					</span>
					<i class="fa fa-angle-right fa-2x pull-right"></i>
			    </li>
			</ul>
		</div>
		<!-- 产品详情 -->
		<div id="intro" class="panel panel-default">
			<div class="panel-heading">
				<div class="text-center">产品详情</div>
			</div>
			<div class="panel-body" style="padding: 0">
				<c:if test="${requestScope.product.productPictures != null }">
					<c:forEach var="productPicture" items="${requestScope.product.productPictures }" varStatus="i">
						<c:choose>
							<c:when test='${fn:startsWith(productPicture.productPictureType, "2")}'>
								<img src="<%=request.getContextPath() %>/res/img/${productPicture.productPictureFileName }" class="img-responsive" />
							</c:when>
						</c:choose>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<br />
		<!-- 产品评价 -->
		<div id="comment" class="panel panel-default">
			<div class="panel-heading">
				<div class="text-center">产品评价</div>
			</div>
			<ul class="list-group">
				<li class="list-group-item">全部评价（${requestScope.evaluatesCount }）</li>
				<c:if test="${requestScope.evaluates != null }">
					<c:forEach var="evaluate" items="${requestScope.evaluates }" varStatus="i">
						<li class="list-group-item">
							<div class="pull-left">
					  			<img src="<%=request.getContextPath() %>/res/img/common/header-customer.png" class="img-responsive comment-head pull-left"/>
					  			<div class="pull-left comment-username-text">${evaluate.username }</div>
					  			<div class="clearfix">&nbsp;</div>
					  		</div>
					  		<div class="pull-right comment-date">
					  			<fmt:formatDate value="${evaluate.evaluateTime }" type="both" />
					  		</div>
					  		<div class="clearfix">&nbsp;</div>
					  		<div class="comment-content">
					  			${evaluate.evaluateContent }
					  		</div>
						</li>
					</c:forEach>
				</c:if>
				<li id="showAllComments" class="list-group-item">
					<div style="cursor: pointer;" class="text-center comment-more-button">查看全部评价</div>
				</li>
			</ul>
		</div>
		<!-- 到底了 -->
		<div class="text-center at-bottom">
			亲，已经没有了
		</div>
		<!-- 订购栏 -->
		<nav class="navbar navbar-default navbar-fixed-bottom order-bar">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-2">
						<a href="<%=request.getContextPath()%>/homectrl/pagectrl">
							<div class="icon icon-collect">
								<i class="fa fa-home fa-2x"></i>
							</div>
							<div class="text">主页</div>
						</a>
					</div>
					<div class="col-xs-2">
						<a href="<%=request.getContextPath()%>/cartctrl/pagectrl">
							<div class="icon">
								<i class="fa fa-opencart fa-2x"></i>
							</div>
							<div class="text">购物车</div>
						</a>
					</div>
					<div class="col-xs-4">
						<div class="add-to-cart">
							加入购物车
						</div>
					</div>
					<div class="col-xs-4">
						<div class="buy">
							立即购买
						</div>
					</div>
				</div>
			</div>
		</nav>
		
		<!-- 选择属性模特对话框 -->
		<form id="productForm" method="post">
			<div class="modal fade" id="choosen" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 100%;">
				    <div class="modal-content" style="height: 460px;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h5 class="modal-title" id="myModalLabel">
								<img src="<%=request.getContextPath() %>/res/img/${requestScope.product.productPictures[0].productPictureFileName }" class="img-rounded product-pic" />
								<div class="pull-right">
									<div>
										<span class="money-icon">￥ </span>
										<span class="money-value">${requestScope.product.realPrice }</span>
									</div>
									<!-- 
									<div>
										<span>库存</span>
										<span>${requestScope.product.inventoryNumber }件</span>
									</div>
									-->
									<div>
										请选择
										<span class="size-tip">尺寸，</span>
										<span class="materials-tip">画框</span>
									</div>
								</div>
								<div class="clearfix"></div>
							</h5>
						</div>
						<div class="modal-body">
							<div class="container product-props">
								<c:if test="${requestScope.propGroups != null }">
									<c:forEach var="productPropGroup" items="${requestScope.propGroups }" varStatus="i">
										<c:if test="${productPropGroup.value != null}">
											<div class="row">
												<div class="col-xs-12">
													<h5>${productPropGroup.value[0].propName }</h5>
												</div>
											</div>
											<div class="row">
												<!-- 属性标识隐藏域 -->
												<input type="hidden" name="propId" />
												<c:forEach var="productProp" items="${productPropGroup.value }" varStatus="j">
													<div class="col-xs-6">
														<div propid="${productProp.propId }" class="product-prop-frame">${productProp.propValue }</div>
													</div>
												</c:forEach>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
								<div class="row">
									<div class="col-xs-12">
										&nbsp;
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4">
										<h5>选择数量</h5>
									</div>
									<div class="col-xs-8">
										<div class="btn-group pull-right" role="group" aria-label="">
											<button type="button" id="minusButton" class="btn btn-default btn-sm">－</button>
											<button type="button" id="productNumButton" class="btn btn-default btn-sm">1</button>
											<button type="button" id="plusButton" class="btn btn-default btn-sm">＋</button>
										</div>
										<!-- 购买数量隐藏域  -->
										<input type="hidden" id="productNum" name="productNum" value="1"/>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<!-- 产品标识隐藏域 -->
							<input type="hidden" id="productId" name="productId" value="${requestScope.product.productId }"/>
							<!-- 提交方式隐藏域 addToCart-加入购物车，buyNow-立即购买 -->
							<input type="hidden" id="subType" name="subType" value="addToCart"/>
	    					<div class="confirm-button">确定</div>
						</div>
				    </div>
				</div>
			</div>
		</form>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	    <script src="<%=request.getContextPath() %>/res/swiper-3.4.2/js/swiper.jquery.min.js"></script>
	    <script src="<%=request.getContextPath() %>/res/layer.mobile-v2.0/layer_mobile/layer.js"></script>
	</body>
	<script type="text/javascript">
		// 查看所有评价
		$("#showAllComments").click(function() {
			var productId = $("#productId").val();
			window.location.href = "<%=request.getContextPath() %>/prodetailsctrl/pagectrl?productId="
					+ productId + "&type=comments";
		});
		
		
		/**
		 * 加入购物车
		 */
		function addToCart() {
			var url = "<%=request.getContextPath()%>/cartctrl/addtocart";
			var productId = $("#productId").val();
			var productNum = $("#productNum").val();
			var productPropIds = null;
			var propIdEles = $(":input[name='propId']");
			if (propIdEles) {
				propIdEles.each(function(i) {
					if ($(this).val()) {
						if (productPropIds) {
							productPropIds += ",";
							productPropIds += $(this).val();
						} else {
							productPropIds = $(this).val();
						}
					}
				});
			}
			
			var param = {"productId": productId, "productPropIds": productPropIds, "productNum": productNum};
			$.post(url, param, function(data) {
				$('#choosen').modal("hide");
				if (data.retCode != "0000") {
					layer.open({
				    	content: data.retMsg,
				    	skin: 'msg',
				    	time: 2
				   });
				} else {
					layer.open({
				    	content: '成功加入购物车',
				    	skin: 'msg',
				    	time: 2
				   });
				}
			});
		}
		
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
			/**
			 * 请选择尺寸画框动作
			 */
			$("#chooseAttr").click(function(evt) {
				$("#productForm").attr("action", "<%=request.getContextPath()%>/cartctrl/addtocart");
				$("#subType").val("addToCart");
				$('#choosen').modal("show");
			});
			
			/**
			 * 选择属性
			 */
			$(".product-prop-frame").click(function() {
				var parentEle = $(this).parent().parent();
				parentEle.find(".active").removeClass("active");
				parentEle.find(":input[name='propId']").val($(this).attr("propid"));
				$(this).addClass("active");
			});
			
			/**
			 * 购买数量 -1
			 */
			$("#minusButton").click(function() {
				var productNum = $("#productNumButton").text();
				if (parseInt(productNum) <= 1) {
					return;
				}
				$("#productNumButton").text(--productNum);
				$("#productNum").val($("#productNumButton").text());
			});
			
			/**
			 * 购买数量 +1
			 */
			$("#plusButton").click(function() {
				var productNum = $("#productNumButton").text();
				$("#productNumButton").text(++productNum);
				$("#productNum").val($("#productNumButton").text());
			});
			
			/**
			 * 加入到购物车动作
			 */
			$(".add-to-cart").click(function() {
				$("#productForm").attr("action", "<%=request.getContextPath()%>/cartctrl/addtocart");
				$("#subType").val("addToCart");
				$('#choosen').modal("show");
			});
			
			/**
			 * 立即购买
			 */
			$(".buy").click(function() {
				$("#productForm").attr("action", "<%=request.getContextPath()%>/orderctrl/placeorder");
				$("#subType").val("buyNow");
				$('#choosen').modal("show");
			});			
			
			/**
			 * 确定
			 */
			$(".confirm-button").click(function(evt) {
				// 产品属组个数
				var productPropsSize = "${requestScope.propGroups.size() }";
				// 是否可以提交
				var canSubmit = true;
				
				// 如果有要选中的属性
				if (productPropsSize && productPropsSize > 0) {
					// 判断属性是否已选
					var propIdEles = $(":input[name='propId']");
					if (propIdEles) {
						propIdEles.each(function(i) {
							if (!$(this).val()) {
								canSubmit = false;
							}
						});
					}
				}
				
				if (!canSubmit) {
					layer.open({
                        content: '请选择产品属性！',
                        skin: 'msg',
                        time: 2
                    });
					return;
				}
				
				var action = $("#productForm").attr("action");
				var target = action.substring(action.lastIndexOf("/"));
				if (target == "/addtocart") {
					addToCart();
				} else {
					$("#productForm").submit();
				}
			});
		});
	</script>
</html>