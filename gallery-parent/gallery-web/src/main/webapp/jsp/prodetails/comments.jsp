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
		<link href="<%=request.getContextPath() %>/res/css/prodetails.css" type="text/css" rel="stylesheet"/>
		<title>产品评价</title>
	</head>
	<body style="margin-top: 0">
		<!-- 如果没有产品信息，则跳转到错误页面 //TODO -->
		<c:if test="${requestScope.product == null }">
			<c:redirect url=""/>
		</c:if>
		<div style="display:none;">
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
			<!-- 巨幅 -->
			<div id="product" class="jumbotron" style="padding: 0; margin: 0">
				<img src="<%=request.getContextPath() %>/res/img/${requestScope.product.productPictures[0].productPictureFileName }" class="img-responsive"/>
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
				    <li class="list-group-item">
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
		</div>
		<!-- 产品评价 -->
		<div id="comment" class="panel panel-default">
			<div class="panel-heading">
				<div class="text-center">产品评价</div>
			</div>
			<ul class="list-group">
				<li class="list-group-item">全部评价（${requestScope.evaluatesCount }）</li>
				
				<li id="showAllComments" class="list-group-item">
					<div class="text-center comment-more-button" id="more_evaluates">查看更多评价</div>
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
						<div class="icon icon-collect"><i class="fa fa-star-o fa-2x"></i></div>
						<div class="text">收藏</div>
					</div>
					<div class="col-xs-2">
						<div class="icon"><i class="fa fa-share-alt fa-2x"></i></div>
						<div class="text">分享</div>
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
									<div class="">
										￥ ${requestScope.product.realPrice }
									</div>
									<div class="">
										库存${requestScope.product.inventoryNumber }件
									</div>
									<div class="">请选择尺寸，画框，数量</div>
								</div>
								<div class="clearfix"></div>
							</h5>
						</div>
						<div class="modal-body">
							<div class="container">
								<c:if test="${requestScope.product.productProps != null }">
									<div class="row">
										<div class="col-xs-12">
											<h5>选择尺寸</h5>
										</div>
									</div>
									<div class="row">
										<c:forEach var="productProp" items="${requestScope.product.productProps }" varStatus="i">
											<c:if test='${productProp.propName == "size" }'>
												<div class="col-xs-6">
													<div class="frame-size">${productProp.propValue }</div>
												</div>
												<%-- <c:if test="${i.count % 2 == 0 }">
													<c:out value="</div>" escapeXml="false"/>
													<c:out value='<div class="row">' escapeXml="false"/>
												</c:if> --%>
											</c:if>
										</c:forEach>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<h5>选择画框</h5>
										</div>
									</div>
									<div class="row">
										<c:forEach var="productProp" items="${requestScope.product.productProps }" varStatus="i">
											<c:if test='${productProp.propName == "materials" }'>
												<div class="col-xs-4">
													<div class="materials">${productProp.propValue }</div>
												</div>
												<%-- <c:if test="${i.count % 2 == 0 }">
													<c:out value="</div>" escapeXml="false"/>
													<c:out value='<div class="row">' escapeXml="false"/>
												</c:if> --%>
											</c:if>
										</c:forEach>
									</div>
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
											<button type="button" id="purchaseNumButton" class="btn btn-default btn-sm">1</button>
											<button type="button" id="plusButton" class="btn btn-default btn-sm">＋</button>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<input type="hidden" id="productId" name="productId" value="${requestScope.product.productId }"/>
	    					<div class="confirm-button">确定</div>
						</div>
				    </div>
				</div>
			</div>
		</form>
		
		<div id="resultDialog" class="modal fade" tabindex="-1" role="dialog">
		  <div class="modal-dialog modal-sm" role="document" style="width: 100%;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">添加结果</h5>
		      </div>
		      <div class="modal-body">
		        <p style="text-align: center;" id="resultContent"></p>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade bs-example-modal-sm" id="tip_modal" tabindex="-1" role="dialog" aria-labelledby="tipModalLabel">
		  <div class="modal-dialog modal-sm" role="document" aria-hidden="true">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title" id="tipModalLabel">亲，已经没有了</h4>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">
		var header = "<%=request.getContextPath() %>/res/img/common/header-customer.png";
		// 初始化页码变量
		var pageNum = 1;
		// 每页评价数量
		var pageSize = 5;
		// 查询标识
		var toGet = true;
		
		function moreEvaluates() {
			if (toGet) {
				toGet = false;
				$.ajax({
					"type": "GET",
					"url": "<%=request.getContextPath() %>/prodetailsctrl/morevaluates",
					"dataType": "json",
					"data": {
						"productId": $("#productId").val(),
						"pageNum": pageNum,
						"pageSize": pageSize,
						"_": new Date().getTime()
					},
					"success": function(result) {
						if (result.retCode === "0") {
							buildLi(result.data);
							pageNum += 1;
						} else {
							// 查询失败
							$("#tip_modal").modal();
							
							setTimeout(function() {
								$("#tip_modal").modal("hide");
							}, 2000);
						}
						toGet = true;
					},
					"error": function() {
						toGet = true;
					}
				});
			}
		}
		
		function buildLi(data) {
			$.each(data, function(i, value) {
				var str = '';
				str += '<li class="list-group-item">';
				str += '<div class="pull-left">';
				str += '<img src="' + header + '" class="img-responsive comment-head pull-left"/>';
				str += '<div class="pull-left comment-username-text">';
				str += value.username ? value.username : '';
				str += '</div>';
				str += '<div class="clearfix">&nbsp;</div>';
				str += '</div>';
				str += '<div class="pull-right comment-date">';
				str += formatDate(value.evaluateTime);
				str += '</div>';
				str += '<div class="clearfix">&nbsp;</div>';
				str += '<div class="comment-content">';
				str += value.evaluateContent;
				str += '</div>';
				str += '</li>';
				$("#showAllComments").before($(str));
			});
		}
		
		function formatDate(milliseconds) {
			var date = new Date(milliseconds);
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = date.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			var hour = date.getHours();
			if (hour < 10) {
				hour = "0" + hour;
			}
			var minutes = date.getMinutes();
			if (minutes < 10) {
				minutes = "0" + minutes;
			}
			var seconds = date.getSeconds();
			if (seconds < 10) {
				seconds = "0" + seconds;
			}
			return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
		}
		
		/**
		 * 加入购物车
		 */
		function addToCart() {
			var url = "<%=request.getContextPath()%>/cartctrl/addtocart";
			var productId = $("#productId").val();
			var param = {"productId": productId};
			$.post(url, param, function(data) {
				$('#choosen').modal("hide");
				if (data.retCode != "0000") {
					$("#resultContent").text(data.retMsg);
					$('#resultDialog').modal("show");
				} else {
					$("#resultContent").text("亲，已经帮您加入到购物车了");
					$('#resultDialog').modal("show");
				}
				
				setTimeout(function() {
					$('#resultDialog').modal("hide");
				}, 2000);
			});
		}
		
		/**
		 * init
		 */
		$(document).ready(function() {
			// 打开页面查询第一页评价
			moreEvaluates();
			
			// 查看更多评价
			$("#more_evaluates").click(function() {
				moreEvaluates();
			});
			
			/**
			 * 加入到购物车动作
			 */
			$(".add-to-cart").click(function() {
				$("#productForm").attr("action", "<%=request.getContextPath()%>/cartctrl/addtocart");
				$('#choosen').modal("show");
			});
			
			/**
			 * 立即购买
			 */
			$(".buy").click(function() {
				$("#productForm").attr("action", "<%=request.getContextPath()%>/orderctrl/pagectrl");
				$('#choosen').modal("show");
			});
			
			/**
			 * 选择尺寸
			 */
			$(".frame-size").click(function() {
				$(".frame-size").removeClass("active");
				$(this).addClass("active");			
			});
			
			/**
			 * 选择画框
			 */
			$(".materials").click(function() {
				$(".materials").removeClass("active");
				$(this).addClass("active");
			});
			
			/**
			 * 购买数量 -1
			 */
			$("#minusButton").click(function() {
				var purchaseNum = $("#purchaseNumButton").text();
				if (parseInt(purchaseNum) <= 1) {
					return;
				}
				$("#purchaseNumButton").text(--purchaseNum);
			});
			
			/**
			 * 购买数量 +1
			 */
			$("#plusButton").click(function() {
				var purchaseNum = $("#purchaseNumButton").text();
				$("#purchaseNumButton").text(++purchaseNum);
			});
			
			/**
			 * 确定
			 */
			$(".confirm-button").click(function(evt) {
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