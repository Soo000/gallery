<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.kkwrite.gallery.pojo.product.GlyProductType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String selectedProductTypeStr = "";
	List<GlyProductType> selectedProductTypes = (List<GlyProductType>) request.getAttribute("selectedProductTypes");
	if (selectedProductTypes != null) {
		StringBuffer tmpProductType = new StringBuffer();
		for (GlyProductType glyProductType: selectedProductTypes) {
			tmpProductType.append(glyProductType.getProductTypeId());
			tmpProductType.append(",");
		}
		selectedProductTypeStr = tmpProductType.toString();
		if (selectedProductTypeStr != null && selectedProductTypeStr.endsWith(",")) {
			selectedProductTypeStr = selectedProductTypeStr.substring(0, selectedProductTypeStr.length() - 1);
		}
	}
%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<%@ include file="../module/headRes.jsp" %>
		<link href="<%=request.getContextPath() %>/res/css/categories.css" type="text/css" rel="stylesheet"/>
		<title>产品分类</title>
	</head>
	<body style="margin-top: 0">
		<!-- 风格 -->
		<div class="cond-title">
			<span class="cond-title-text">风格</span>
		</div>
		<div>
			<ul id="styleList" class="cond">
				<li>全部</li>
				<c:if test="${requestScope.styleTypeItems != null }">
					<c:forEach var="styleTypeItem" items="${requestScope.styleTypeItems }">
						<li value="${styleTypeItem.productTypeId }">${styleTypeItem.productTypeName }</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!-- 空间 -->
		<div class="cond-title">
			<span class="cond-title-text">空间</span>
		</div>
		<div>
			<ul id="spaceList" class="cond">
				<li>全部</li>
				<c:if test="${requestScope.spaceTypeItems != null }">
					<c:forEach var="spaceTypeItem" items="${requestScope.spaceTypeItems }">
						<li value="${spaceTypeItem.productTypeId }">${spaceTypeItem.productTypeName }</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!-- 特色 -->
		<div class="cond-title">
			<span class="cond-title-text">特色</span>
		</div>
		<div>
			<ul id="featureList" class="cond">
				<li>全部</li>
				<c:if test="${requestScope.featureTypeItems != null }">
					<c:forEach var="featureTypeItem" items="${requestScope.featureTypeItems }">
						<li value="${featureTypeItem.productTypeId }">${featureTypeItem.productTypeName }</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!-- 内容 -->
		<div class="container">
			<c:if test="${requestScope.products != null }">
				<div class="row">
					<c:forEach var="product" items="${requestScope.products }" varStatus="i">
						<div class="col-xs-6">
							<div class="thumbnail">
								<a href="<%=request.getContextPath()%>/prodetailsctrl/pagectrl?productId=${product.productId }">
									<img style="height: 130px" src="<%=request.getContextPath() %>/res/img/${product.productPictures[0].productPictureFileName }" />
									<div class="caption">
										<!--<h6>小桥流水</h6>-->
										<p>${product.productName }</p>
										<div>
											<span class="pull-left">￥</span>${product.realPrice }
											<small class="pull-right">月订${product.bookNumber }幅</small>
										</div>
									</div>
								</a>
							</div>
						</div>	
						<c:if test="${i.count % 2 == 0 }">
							<c:out value="</div>" escapeXml="false"/>
							<c:out value='<div class="row">' escapeXml="false"/>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
		</div>
	
		<jsp:include page="../module/footer.jsp">
			<jsp:param name="menuId" value="categories"/>
		</jsp:include>
		
		<%@ include file="../module/footRes.jsp" %>
	</body>
	<script type="text/javascript">
		// 已选择的 产品类型ID 串，如 3,4,5
		var selectedProductTypeStr = new String("<%=selectedProductTypeStr%>");
		
		/**
		 * 初始化产品类型状态
		 */
		function initProductTypeStatus(elements) {
			var hasSelected = false;
			elements.each(function() {
				var selectedProductTypeArray = selectedProductTypeStr.split(",");
				for (var i = 0; i < selectedProductTypeArray.length; i++) {
					if ($(this).val() == selectedProductTypeArray[i]) {
						hasSelected = true;
						$(this).addClass("active");
						break;
					}
				}
			});
			if (!hasSelected) {
				elements.eq(0).addClass("active");
			}
		}
	
		/**
		 * 生成产品类型参数
		 */
		function generateProductTypeIds() {
			var url = "<%=request.getContextPath() %>/categoriesctrl/pagectrl";
			var styleProductId = $("#styleList > li[class=active]").val();
			var spaceProductId = $("#spaceList > li[class=active]").val();
			var featureProductId = $("#featureList > li[class=active]").val();
			if (styleProductId && styleProductId != 0) {
				if (url.indexOf("?") == -1) {
					url += "?";
				} else {
					url += "&";
				}
				url += "productTypeId=" + styleProductId;
			}
			if (spaceProductId && spaceProductId != 0) {
				if (url.indexOf("?") == -1) {
					url += "?";
				} else {
					url += "&";
				}
				url += "productTypeId=" + spaceProductId;
			}
			if (featureProductId && featureProductId != 0) {
				if (url.indexOf("?") == -1) {
					url += "?";
				} else {
					url += "&";
				}
				url += "productTypeId=" + featureProductId;
			}
			return url;
		}
	
		/**
		 * init
		 */
		$(document).ready(function() {
			// 初始化风格类型状态
			initProductTypeStatus($("#styleList > li"));
			// 初始化空间类型状态
			initProductTypeStatus($("#spaceList > li"));
			// 初始化特色类型状态
			initProductTypeStatus($("#featureList > li"));
			
			/**
			 * 给风格过滤条件添加单击事件
			 */
			$("#styleList").on("click", "li", function(evt) {
				$(this).siblings(".active").removeClass();
				$(this).addClass("active");
				var url = generateProductTypeIds();
				window.location.href = url;
			});
			
			/**
			 * 给空间过滤条件添加单击事件
			 */
			$("#spaceList").on("click", "li", function(evt) {
				$(this).siblings(".active").removeClass();
				$(this).addClass("active");
				var url = generateProductTypeIds();
				window.location.href = url;
			});
			
			/**
			 * 给特色过滤条件添加单击事件
			 */
			$("#featureList").on("click", "li", function(evt) {
				$(this).siblings(".active").removeClass();
				$(this).addClass("active");
				var url = generateProductTypeIds();
				window.location.href = url;
			});
		});
	</script>
</html>