<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link href="<%=request.getContextPath() %>/res/css/order.css" type="text/css" rel="stylesheet"/>
		<title>确认订单</title>
	</head>
	<body>
		<!-- header -->
		<jsp:include page="../module/header.jsp">
			<jsp:param name="headTitle" value="确认订单"/>
			<jsp:param name="backUrl" value='../cartctrl/pagectrl'/>
		</jsp:include>
		
		<form id="orderForm" action="<%=request.getContextPath()%>/orderctrl/submitorder">
			<!-- Address information -->
			<c:choose>
				<c:when test="${requestScope.glyAddress != null }">
					<div class="address-bar">
						<a onclick="changeAddress();">
							<!-- 地址标识隐藏域 -->
							<input type="hidden" name="addressId" value="${requestScope.glyAddress.addressId }"/>
							<div class="pull-left contact-info">
								<div class="contact-name">
									<c:if test="${requestScope.glyAddress != null }">
										<span>${requestScope.glyAddress.receiver }</span>
										&nbsp;&nbsp;
										<span>${requestScope.glyAddress.phoneNum }</span>
									</c:if>
								</div>
								<div class="contact-address">
									<c:if test="${requestScope.glyAddress != null }">
										<span>${requestScope.glyAddress.address }</span>
									</c:if>
								</div>
							</div>
							<div class="pull-right contact-more">
								<i class="fa fa-angle-right fa-2x pull-right" style="margin-top: 25px;"></i>
							</div>
							<div class="clearfix"></div>
						</a>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			
			<br />
			
			<div class="container product-bar">
				<c:choose>
					<c:when test="${requestScope.products != null }">
						<c:forEach var="product" items="${requestScope.products }">
							<div class="row product-item">
								<!-- productId -->
								<input type="hidden" name="productId" value="${product.productId }"/>
								<div class="col-xs-5">
									<a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${product.productId }">
										<img src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" 
											class="img-rounded product-pic"/>
									</a>
								</div>
								<div class="col-xs-7">
									<div class="product-id" style="display:none;">
										${product.productId }
									</div>
									<a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${product.productId }">
										<div class="product-name">${product.productName }</div>
										<div class="product-intro">${product.productIntro }</div>
									</a>
									<div class="pull-left product-price">
										<span class="money-icon">￥</span> 
										<span class="money-value">${product.realPrice }</span>
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
					</c:otherwise>
				</c:choose>
			</div>
			
			<!-- Place order bar -->
			<nav class="navbar navbar-default navbar-fixed-bottom settlement-bar">
				<div class="pull-left settlement">
					实付款：￥<span class="amount-value">${requestScope.acount }</span>
				</div>
				<div id="submitOrder" class="pull-right settlement go-settlement">
					<!-- 提交方式：addToCart-加入购物车，buyNow-立即购买 -->
					<input type="hidden" id="subType" name="subType" value="${requestScope.subType }" />
					提交订单 (<span class="product-amount">${requestScope.count }</span>)
				</div>
				<div class="clearfix"></div>
			</nav>
		</form>
	
		<%@ include file="../module/footRes.jsp" %>
	</body>
	<script type="text/javascript">
		/**
		 * 变更收货人，收货地址
		 */
		function changeAddress() {
			var productId = "";
			$.each($(".product-id"), function(i, v) {
				productId += $(v).text() + "-";
			});
			window.location.href = "<%=request.getContextPath()%>/addressctrl/pagectrl?from=order&productIds=" + productId;
		}
		
		/**
		 * 计算价钱
		 */
		function calculate() {
			var acount = 0.0;
			$(".product-item").each(function(i) {
				var price = $(this).find(".money-value").text();
				var num = $(this).find("[name='purchaseNumBtn']").text();
				acount = (parseFloat(acount) + parseFloat(price)) * num;
			});
			$(".amount-value").text(acount);
		}
		
		/**
		 * 计算选中产品数量
		 */
		function calProAmount() {
			var amount = 0;
			$(".product-item").each(function(i) {
				var num = $(this).find("[name='purchaseNumBtn']").text();
				amount += parseInt(num);
			});
			$(".product-amount").text(amount);
		}
	
		/**
		 * init
		 */
		$(document).ready(function() {
			/**
			 * 购买数量 -1
			 */
			$(":input[name='minusBtn']").click(function(evt) {
				evt.preventDefault();
				evt.stopPropagation();
				
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
			$(":input[name='plusBtn']").click(function(evt) {
				evt.preventDefault();
				evt.stopPropagation();
				
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
			 * 提交订单
			 */
			$("#submitOrder").on("click", function(evt) {
				$("#orderForm").submit();
			});
		});
	</script>
</html>