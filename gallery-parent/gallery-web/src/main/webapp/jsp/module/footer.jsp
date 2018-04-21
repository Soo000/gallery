<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default navbar-fixed-bottom nav-menubar">
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<a href="<%=request.getContextPath() %>/homectrl/pagectrl">
					<c:choose>
						<c:when test='${param.menuId == "home" }'>
							<div id="homeMenu" class="nav-menubar-menu active">
								<i class="fa fa-home fa-2x menu-icon"></i>
								<div class="menu-text">首页</div>
							</div>
						</c:when>
						<c:otherwise>
							<div id="homeMenu" class="nav-menubar-menu">
								<i class="fa fa-home fa-2x menu-icon"></i>
								<div class="menu-text">首页</div>
							</div>
						</c:otherwise>
					</c:choose>
				</a>
			</div>
			<div class="col-xs-3">
				<a href="<%=request.getContextPath() %>/categoriesctrl/pagectrl">
					<c:choose>
						<c:when test='${param.menuId == "categories" }'>
							<div class="nav-menubar-menu">
								<i class="fa fa-navicon fa-2x menu-icon active"></i>
								<div class="menu-text">分类</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="nav-menubar-menu">
								<i class="fa fa-navicon fa-2x menu-icon"></i>
								<div class="menu-text">分类</div>
							</div>
						</c:otherwise>
					</c:choose>
				</a>
			</div>
			<div class="col-xs-3">
				<a href="<%=request.getContextPath()%>/cartctrl/pagectrl">
					<c:choose>
						<c:when test='${param.menuId == "cart" }'>
							<div class="nav-menubar-menu">
								<i class="fa fa-cart-plus fa-2x menu-icon active"></i>
								<div class="menu-text">购物车</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="nav-menubar-menu">
								<i class="fa fa-cart-plus fa-2x menu-icon"></i>
								<div class="menu-text">购物车</div>
							</div>
						</c:otherwise>
					</c:choose>
				</a>
			</div>
			<div class="col-xs-3">
				<a href="<%=request.getContextPath() %>/myselfctrl/pagectrl">
					<c:choose>
						<c:when test='${param.menuId == "myself" }'>
							<div class="nav-menubar-menu">
								<i class="fa fa-user fa-2x menu-icon active"></i>
								<div class="menu-text">我的</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="nav-menubar-menu">
								<i class="fa fa-user fa-2x menu-icon"></i>
								<div class="menu-text">我的</div>
							</div>
						</c:otherwise>
					</c:choose>
				</a>						
			</div>
		</div>
	</div>
</nav>