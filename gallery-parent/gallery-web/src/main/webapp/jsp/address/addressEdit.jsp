<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<!-- Font-awesome -->
		<link href="<%=request.getContextPath() %>/res/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/css/common.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/gallery.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/address.css" type="text/css" rel="stylesheet"/>
		<!-- 兼容低版本浏览器 -->
	    <!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <!-- title -->
		<title>编辑地址</title>
	    <!-- local -->
	    <style type="text/css">
	    	/*.row > div {
	    		border: 1px solid #C0A16B;
	    	}*/
	    	.error {
	    		border: 1px solid red;
	    	}
	    </style>
	</head>
	<body>
		<div class="container">
			<c:choose>
				<c:when test="${errorMsg != null }">
					<div class="alert alert-warning alert-dismissible" role="alert">
			  			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			  				<span aria-hidden="true">&times;</span>
			  			</button>
			  			${errorMsg }
					</div>
				</c:when>
			</c:choose>
			<form method="post">
				<c:choose>
					<c:when test="${address != null }">
						<div class="form-group hide">
					    	<label for="addressId">地址</label>
					    	<input type="text" id="addressId" name="addressId" value="${address.addressId }" readonly="readonly">
					  	</div>
					</c:when>
				</c:choose>
				<div class="form-group">
		    		<label class="control-label" for="receiver">收货人</label>
					<c:choose>
						<c:when test="${address != null }">
							<input type="text" id="receiver" class="form-control" name="receiver" value="${address.receiver }" placeholder="收货人">
						</c:when>
						<c:otherwise>
							<input type="text" id="receiver" class="form-control" name="receiver" placeholder="收货人">
						</c:otherwise>
					</c:choose>
		  		</div>
		  		<div class="form-group">
			    	<label class="control-label" for="phoneNum">手机号码</label>
			    	<c:choose>
		      			<c:when test="${address != null }">
		      				<input type="text" id="phoneNum" class="form-control" name="phoneNum" value="${address.phoneNum }" placeholder="手机号码">
			      		</c:when>
			      		<c:otherwise>
			      			<input type="text" id="phoneNum" class="form-control" name="phoneNum" placeholder="手机号码">
			      		</c:otherwise>
		      		</c:choose>
				</div>
				<div class="form-group col-xs-4">
					<label class="control-label sr-only">所在省</label>
					<select class="form-control" name="provinceId" id="province">
						<option value='-1'>-- 省--</option>
					</select>
				</div>
				<div class="form-group col-xs-4">
					<label class="control-label sr-only">所在市</label>
					<select class="form-control" name="cityId" id="city">
                      	<option value='-1'>-- 市 --</option>
                    </select>
				</div>
				<div class="form-group col-xs-4">
					<label class="control-label sr-only">所在区/县</label>
					<select class="form-control" name="countyId" id="village">
                      	<option value='-1'>-- 区/县 --</option>
                    </select>
				</div>
				<div class="form-group">
			    	<label class="control-label" for="addressDetail">详细地址</label>
			    	<input type="text" id="addressDetail" class="form-control" name="addressDetail" placeholder="街道，楼牌号等">
			  	</div>
			  	<div class="form-group">
			  		<label class="control-label">标签</label>
			  		<div>
			      		<div class="btn-group" data-toggle="buttons">
						  <label class="btn btn-default active">
						    <input type="radio" name="addressLable" value="家" autocomplete="off" checked> 家
						  </label>
						  <label class="btn btn-default">
						    <input type="radio" name="addressLable" value="公司" autocomplete="off"> 公司
						  </label>
						  <label class="btn btn-default">
						    <input type="radio" name="addressLable" value="学校" autocomplete="off"> 学校
						  </label>
					  	  <button id="addLable" type="button" class="btn btn-default" data-toggle="button" aria-pressed="false">
						 	+
						  </button>
					  	  <div class="input-group">
					        <input type="text" id="addLabelInput" class="form-control hide" placeholder="最多五个字">
					        <span class="input-group-btn">
					          <button id="sureAdd" class="btn btn-default hide" type="button">确定</button>
					        </span>
					      </div>
						</div>
			    	</div>
			  	</div>
			  	<div class="form-group">
			  		<label class="control-label">设为默认</label>
			  		<div>
			      		<div class="btn-group" data-toggle="buttons">
						  <label class="btn btn-default active">
						    <input type="radio" name="defaultAddress" value="0" autocomplete="off" checked> 否
						  </label>
						  <label class="btn btn-default">
						    <input type="radio" name="defaultAddress" value="1" autocomplete="off"> 是
						  </label>
						</div>
			    	</div>
			  	</div>
				<c:choose>
					<c:when test="${address != null }">
						<button id="editAddress" class="btn btn-danger btn-group-justified">修改</button>
					</c:when>
					<c:otherwise>
						<button id="btnSaveAndUse" class="btn btn-danger btn-group-justified">保存并使用</button>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	    <!-- gallery.js -->
	    <script src="<%=request.getContextPath() %>/res/js/gallery.js"></script>
	    <script type="text/javascript">
	    </script>
	</body>
	<script type="text/javascript">
		function isSafety(str) {
			return (str.indexOf("<") < 0) && (str.indexOf(">") < 0);
		}
		
		function checkForm() {
			var receiver = $("#receiver").val();
			if (!$.trim(receiver) || !isSafety(receiver)) {
				$("#receiver").parent().addClass("has-error");
				return false;
			}
			var phone = $("#phoneNum").val();
			var phoneReg = /^1[3-9][0-9]{9}$/;
			if (!$.trim(phone) || !phoneReg.test(phone)) {
				$("#phoneNum").parent().addClass("has-error");
				return false;
			}
			if ($("#province").val() === "-1") {
				$("#province").parent().addClass("has-error");
				return false;
			}
			if ($("#city").val() === "-1") {
				$("#city").parent().addClass("has-error");
				return false;
			}
			if ($("#village").val() === "-1") {
				$("#village").parent().addClass("has-error");
				return false;
			}
			var addressDetail = $("#addressDetail").val();
			if (!$.trim(addressDetail) || !isSafety(addressDetail)) {
				$("#addressDetail").parent().addClass("has-error");
				return false;
			}
			return true;
		}
		
		function submitForm() {
			if (checkForm()) {
				$("form").attr("action", "<%=request.getContextPath() %>/addressctrl/save").submit();
			}
		}
		
		function editSubmit() {
			if (checkForm()) {
				$("form").attr("action", "<%=request.getContextPath() %>/addressctrl/edit").submit();
			}
		}
		
		function sendRequest(params, successFunc, errorFunc) {
			var _this = this;
			$.ajax({
				"type": "GET",
				"url": "<%=request.getContextPath() %>/addressctrl/getprocitvil",
				"data": params,
				"dataType": "JSON",
				"success": function(result) {
					if (typeof successFunc === "function") {
						successFunc.call(_this, result);
					}
				},
				"error": function(XMLHttpRequest, textStatus, errorThrown) {
					if (typeof errorFunc === "function") {
						errorFunc.call(_this, XMLHttpRequest, textStatus, errorThrown);
					}
				}
			});
		}
		function proviceBind() {
			$("#province").html("");
			var str = "<option value='-1'>-- 省--</option>";
			sendRequest({"type": "0"}, function(result) {
				if (result.retCode === "0") {
					$.each(result.data, function(i, item) {
						str += "<option value=" + item.provice_id + ">" + item.provice_name + "</option>";
					});
				}
				$("#province").append(str);
			});
		}
		function cityBind() {
			var provinceId = $("#province").val();
			var str = "<option value='-1'>-- 市--</option>";
			if (provinceId !== "-1") {
				$("#city").html("");
				sendRequest({"type": "1", "provinceId": provinceId}, function(result) {
					if (result.retCode === "0") {
						$.each(result.data, function(i, item) {
							str += "<option value=" + item.city_id + ">" + item.city_name + "</option>";
						});
					}
					$("#city").append(str);
					$("#village").html("").append("<option value='-1'>-- 区/县--</option>");
				});
			} else {
				$("#city").html("").append(str);
				$("#village").html("").append("<option value='-1'>-- 区/县--</option>");
			}
		}
		function villageBind() {
			var cityId = $("#city").val();
			var str = "<option value='-1'>-- 区/县--</option>";
			if (cityId !== "-1") {
				$("#village").html("");
				sendRequest({"type": "2", "cityId": cityId}, function(result) {
					if (result.retCode === "0") {
						$.each(result.data, function(i, item) {
							str += "<option value=" + item.county_id + ">" + item.county_name + "</option>";
						});
					}
					$("#village").append(str);
				});
			} else {
				$("#village").html("").append(str);
			}
		}
		
		function addLables(value){
			if (value && $.trim(value)) {
				if (value.length > 5) {
					return;
				}
				var str = "";
				str += '<label class="btn btn-default active">';
			    str += '<input type="radio" name="addressLable" value="' + value + '" autocomplete="off" checked> ' + value;
			  	str += '</label>';
			  	$("#addLabelInput").parent().parent().find("label").removeClass("active");
				$("#addLabelInput").parent().parent().find("input").removeAttr("checked");
				$("#addLable").before($(str));
			}
			$("#addLabelInput").addClass("hide");
			$("#sureAdd").addClass("hide");
			$("#addLable").removeClass("hide");
		}
		
		/**
		 * init
		 */
		$(document).ready(function() {
			proviceBind();
			$("#province").change(function() {
		        cityBind();
		    });
		    $("#city").change(function() {
		    	villageBind();
		    });
			
		    $("#btnSaveAndUse").click(function(evt) {
		    	evt.preventDefault();
				submitForm();
			});
		    
		    $("#editAddress").click(function(evt) {
		    	evt.preventDefault();
		    	editSubmit();
		    });
		    
		    $("form input").focus(function() {
		    	$(this).parent().removeClass("has-error");
		    });
		    $("form select").focus(function() {
		    	$(this).parent().removeClass("has-error");
		    });
		    
			$("#reservationBtn").on("click", function(evt) {
				window.location.href = "../reservation/reservation.html";
			});
			
			$("#addLable").click(function() {
				$('#addLabelInput').removeClass("hide");
				$('#sureAdd').removeClass("hide");
				$(this).addClass("hide");
			});
			
			$("#sureAdd").click(function() {
				addLables($('#addLabelInput').val());
				$('#addLabelInput').val("");
			});
			
			/**
			 * 滚动监听
			 */
			$(window).scroll(function() {
				// scrollTop就是触发滚轮事件时滚轮的高度，为了保证兼容性，这里取两个值，哪个有值取哪一个　　
				var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				if (scrollTop < 400) {
					$("#reservationBtn").css("position", "fixed")
					$("#reservationBtn").css("left", "0");
					$("#reservationBtn").css("bottom", "0");
				} else {
					$("#reservationBtn").css("position", "static")
				}
			});
		});
	</script>
</html>