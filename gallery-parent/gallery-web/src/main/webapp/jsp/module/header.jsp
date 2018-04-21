<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="gallery-navbar navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<c:if test="${param.backUrl != null }">
					<a href="${param.backUrl}">
						<img src="<%=request.getContextPath() %>/res/img/common/previous.png" class="icon-previous"/>
					</a>
				</c:if>
				<c:if test="${param.close != null }">
					<img src="<%=request.getContextPath() %>/res/img/common/close.png" class="icon-close"/>
				</c:if>
			</div>
			<div class="col-xs-4">
				<div style="color: #FFFFFF;" class="gallery-navbar-title">
					${param.headTitle }
				</div>
			</div>
			<div class="col-xs-4">
				
			</div>
		</div>
	</div>
</nav>