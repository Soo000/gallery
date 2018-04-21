<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link href="<%=request.getContextPath() %>/res/css/cart.css" type="text/css" rel="stylesheet"/>
	</head>
	<body style="margin-top: 0">
		<form class="form-horizontal" id="cartForm" action="<%=request.getContextPath()%>/orderctrl/placeorder">
			<input type="hidden" id="subType" name="subType" value="addToCart"/>
			<div class="panel panel-default cart">
				<div class="panel-heading">
					<div class="pull-left">
						<input type="checkbox" class="selectAll" checked/>&nbsp;全选
					</div>
					<div class="pull-right">
						<button id="editBtn" class="btn btn-default btn-xs">编辑</button>
						<button id="endEditBtn" class="btn btn-default btn-xs hide">完成</button>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="panel-body">
					<c:choose>
						<c:when test="${requestScope.products != null && requestScope.products.size() > 0}">
							<c:forEach var="product" items="${requestScope.products }">
								<div class="row product-item">
									<div class="col-xs-1">
										<div class="product-checkbox">
											<input name="productCheckbox" type="checkbox" value="${product.productId }" checked/>
										</div>
									</div>
									<div class="col-xs-5">
										<a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${product.productId }">
											<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
												class="img-rounded product-pic"/>	
										</a>
									</div>
									<div class="col-xs-6">
										<input name="productId" type="hidden" value="${product.productId }"/>
										<a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${product.productId }">
											<div class="product-name">${product.productName }</div>
											<div class="product-intro">${product.productIntro }</div>
										</a>
										<div class="pull-left product-price">
											<span class="money-icon">￥</span>
											<span class="money-value">${product.realPrice}</span>
										</div>
										<div class="pull-right btn-group product-number" role="group" aria-label="">
											<button type="button" name="minusBtn" class="btn btn-default btn-xs">－</button>
											<button type="button" name="purchaseNumBtn" class="btn btn-default btn-xs">${product.productNum }</button>
											<button type="button" name="plusBtn" class="btn btn-default btn-xs">＋</button>
											<!-- 购买数量隐藏域 -->
											<input type="hidden" name="productNum" value="${product.productNum }" />
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							您的购物车已经饿扁 😭
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form>
		<!-- 删除产品表单 -->
		<form id="deleteProductForm" action="<%=request.getContextPath()%>/cartctrl/removefromcart" method="post">
			<div class="container edit-bar">
				<div class="row">
					<div class="col-xs-1">
					</div>
					<div class="col-xs-2">
						&nbsp;
					</div>
					<div class="col-xs-6">
					</div>
					<div class="col-xs-2">
						<input type="hidden" id="products" name="products"/>
						<input type="button" id="deleteBtn" class="btn btn-default" value="删除"/>
					</div>
					<div class="col-xs-1">
					</div>
				</div>
			</div>
		</form>
		
		<!-- 结算栏 -->
		<nav class="navbar navbar-default settlement-bar">
			<div class="pull-left settlement">
				&nbsp;
			</div>
			<div class="pull-left settlement amount">
				合计：￥<span class="amount-value">${requestScope.acount }</span>
			</div>
			<div class="pull-right settlement go-settlement">
				去结算 (<span class="product-amount">${requestScope.count }</span>)
			</div>
			<div class="clearfix"></div>
		</nav>

		<jsp:include page="../module/footer.jsp">
			<jsp:param name="menuId" value="cart"/>
		</jsp:include>	
		
		<%@ include file="../module/footRes.jsp" %>
		<script src="<%=request.getContextPath() %>/res/layer.mobile-v2.0/layer_mobile/layer.js"></script>
	</body>
	<script type="text/javascript">
		/**
		 * 计算价钱
		 */
		function calculate() {
			var acount = 0.0;
			$(".product-item input:checkbox:checked").each(function(i) {
				var productItemEle = $(this).parent().parent().parent();
				var price = productItemEle.find(".money-value").eq(0).text();
				var num = productItemEle.find("[name='purchaseNumBtn']").text();
				acount = (parseFloat(acount) + parseFloat(price)) * num;
			});
			$(".amount-value").text(acount);
		}
		
		/**
		 * 计算选中产品数量
		 */
		function calProAmount() {
			var amount = 0;
			$(".product-item input:checkbox:checked").each(function(i) {
				var productItemEle = $(this).parent().parent().parent();
				var num = productItemEle.find("[name='purchaseNumBtn']").text();
				amount += parseInt(num);
			});
			$(".product-amount").text(amount);
		}
	
		/**
		 * init
		 */
		$(document).ready(function() {
			/**
			 * 编辑动作
			 */
			$("#editBtn").click(function(evt) {
				evt.preventDefault();
				$(this).hide();
				$(".settlement-bar").hide();
				
				$("#endEditBtn").removeClass("hide");
				$("#endEditBtn").show();
				$("#edit-bar").show();
			});
			/**
			 * 编辑完成动作
			 */
			$("#endEditBtn").click(function(evt) {
				evt.preventDefault();
				$(this).hide();
				$("#editBtn").show();
				$(".settlement-bar").show();
				$("#edit-bar").hide();
			});
			/**
			 * 全选动作
			 */
			$(".selectAll").click(function(evt) {
				if ($(this).is(":checked")) {
					$(".product-item input:checkbox").prop("checked", true);
				} else {
					$(".product-item input:checkbox").prop("checked", false);
				}
				
				// 计算价钱
				calculate();
				
				// 计算选中产品数量
				calProAmount();
			});
			/**
			 * 产品选中复选框动作
			 */
			$("input[name='productCheckbox']").click(function(evt) {
				// 计算价钱
				calculate();
				
				// 计算选中产品数量
				calProAmount();
			});
			/**
			 * 购买数量 -1
			 */
			$(":input[name='minusBtn']").click(function() {
				var purchaseNumBtn = $(this).siblings("[name='purchaseNumBtn']");
				var purchaseNum = purchaseNumBtn.text();
				if (parseInt(purchaseNum) <= 1) {
					return;
				}
				purchaseNumBtn.text(--purchaseNum);
				
				// 设置购买数量隐藏域的值
				var productNumEle = $(this).siblings("[name='productNum']");
				productNumEle.val(purchaseNumBtn.text());
				
				// 计算价钱
				calculate();
				
				// 计算选中产品数量
				calProAmount();
			});
			
			/**
			 * 购买数量 +1
			 */
			$(":input[name='plusBtn']").click(function() {
				var purchaseNumBtn = $(this).siblings("[name='purchaseNumBtn']");
				var purchaseNum = purchaseNumBtn.text();
				purchaseNumBtn.text(++purchaseNum);
				
				// 设置购买数量隐藏域的值
				var productNumEle = $(this).siblings("[name='productNum']");
				productNumEle.val(purchaseNumBtn.text());
				
				// 计算价钱
				calculate();
				
				// 计算选中产品数量
				calProAmount();
			});
			/**
			 * 删除动作
			 */
			$("#deleteBtn").click(function(evt) {
				layer.open({
				    content: '确定要删除吗？',
				    btn: ['删除', '取消'],
				    yes: function(index) {
				    	var productIdArray = new Array();
						$(".product-item input:checked").each(function(evt) {
							productIdArray.push($(this).val());
						});
						var products = productIdArray.join(",");
						
						$("#products").val(products);
						$("#deleteProductForm").submit();
				        layer.close(index);
				    }
				});
			});
			/**
			 * 去结算动作
			 */
			$(".go-settlement").click(function() {
				$("#cartForm").submit();
			});
		});
	</script>
</html>