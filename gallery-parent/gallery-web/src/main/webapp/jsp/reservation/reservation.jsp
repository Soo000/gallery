<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8">
		<!-- 视口标签 -->
		<meta name="viewport" content="width=device-width, initial-scale=1"/>		
		<!-- 设置IE浏览器采用的解析模式 -->
		<meta http-equiv="X-UA-Campatible" content="IE=edge">
		<!-- Bootstrap CSS -->
		<link href="<%=request.getContextPath() %>/res/bootstrap-3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/res/css/common.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/gallery.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath() %>/res/css/reservation.css" type="text/css" rel="stylesheet"/>
		<!-- 兼容低版本浏览器 -->
	    <!--[if lt IE 9]>
			<script src="../../js/bootstrap-3.3.5/js/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="../../js/bootstrap-3.3.5/js/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <!-- title -->
		<title>预约服务</title>
	    <!-- local -->
	    <style type="text/css">
	    	/*.row > div {
	    		border: 1px solid #C0A16B;
	    	}*/
	    </style>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="jumbotron reservation-img" style="padding-top: 0; padding-bottom: 0; margin-bottom: 0;">
					<img src="<%=request.getContextPath() %>/res/img/common/reservation.png" class="img-responsive" />
				</div>
				
				<div class="reservation-bar">
					目前已经有 ${reservationCount } 人预约服务
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="panel panel-info">
					<div class="panel-heading">
						填写你的预约信息，享受上门服务
					</div>
					<div class="panel-body">
						<form id="reservationForm">
							<div class="form-group">
								<input type="text" id="reservationUsername" class="form-control" placeholder="您的姓名"/>
							</div>
							<div class="form-group">
								<input type="tel" id="reservationPhoneNum" class="form-control" placeholder="您的手机号码"/>
							</div>
							<div class="form-group">
								<select id="reservationType" class="form-control">
								  	<option value="-1">预约类型</option>
								  	<option value="1">测量设计</option>
								  	<option value="2">以旧换新</option>
								  	<option value="3">维修保养</option>
								  	<option value="4">作品装裱</option>
								</select>
							</div>
							<div class="form-group col-xs-4">
								<select class="form-control" name="provinceId" id="province_id">
									<option value='610'>陕西省</option>
								</select>
			                </div>
							<div class="form-group col-xs-4">
								<select class="form-control" name="cityId" id="city_id">
			                      	<option value='610100000000'>西安市</option>
			                    </select>
			                </div>
			                <div class="form-group col-xs-4">
			                	<select class="form-control" name="countyId" id="village">
			                      	<option value='-1'>-- 区/县 --</option>
			                    </select>
			                </div>
							<div class="form-group">
								<input type="text" id="reservationAddress" class="form-control" placeholder="详细地址"/>
							</div>
							<div class="form-group">
								<textarea id="reservationDesc" rows="5" class="form-control" placeholder="预约说明"></textarea>
							</div>
							<div class="form-group">
								<button id="reservationSub" type="button" class="btn btn-danger form-control">立即预约</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
		      </div>
		      <div id="modalContent" class="modal-body"></div>
		      <div class="modal-footer" style="display: none;">
		        <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
		        <button type="button" class="btn btn-primary" id="toLogin">是</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="<%=request.getContextPath() %>/res/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<%=request.getContextPath() %>/res/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	    <!-- gallery.js -->
	    <script src="<%=request.getContextPath() %>/res/js/gallery.js"></script>
	</body>
	<script type="text/javascript">
		function submit() {
			$.post("<%=request.getContextPath()%>/reservationctrl/reservation", {
				"reservationUsername": $("#reservationUsername").val(),
				"reservationPhoneNum": $("#reservationPhoneNum").val(),
				"reservationType": $("#reservationType").val(),
				"reservationArea": $("#province_id option:selected").text() + $("#city_id option:selected").text() + $("#village option:selected").text(),
				"reservationAddress": $("#reservationAddress").val(),
				"reservationDesc": $("#reservationDesc").val()
			}, function(result) {
				switch (result.retCode) {
				case "-1":
					// 会话失效，需重新登录
					$("#myModalLabel").text("预约失败"); // 设置标题
					$("#modalContent").text("您可能尚未登录！是否需要登录？"); // 设置内容
					$(".modal-footer").show();
					$("#myModal").modal();
					break;
				case "-2":
					// 保存失败，重新提交
					$("#myModalLabel").text("预约失败"); // 设置标题
					$("#modalContent").text("预约内容提交失败！请重新提交！"); // 设置内容
					$("#myModal").modal();
					break;
				case "0":
					// 保存成功
					$("#reservationUsername").val("");
					$("#reservationPhoneNum").val("");
					$("#reservationType").val("-1");
					$("#village").val("-1");
					$("#reservationAddress").val("");
					$("#reservationDesc").val("");
					
					$("#myModalLabel").text("预约成功"); // 设置标题
					$("#modalContent").text("您已成功预约，请耐心等候！"); // 设置内容
					$("#myModal").modal();
					break;
				}
			}, "json");
		}
		
		function queryCounty() {
			$.get("<%=request.getContextPath() %>/addressctrl/getprocitvil", 
					{"type": "2", "cityId": $("#city_id").val()}, function(result) {
				var str = "<option value='-1'>-- 区/县--</option>";
				$("#village").html("");
				if (result.retCode === "0") {
					$.each(result.data, function(i, item) {
						str += "<option value=" + item.county_id + ">" + item.county_name + "</option>";
					});
				}
				$("#village").append(str);
			});
		}
		
		/**
		 * init
		 */
		$(document).ready(function() {
			queryCounty();
			
			$("#toLogin").click(function () {
				$("#modalContent").modal('hide');
				window.location.href = "<%=request.getContextPath() %>/loginctrl/prelogin";
			});
			
			$("div.form-group input").focus(function() {
				$(this).parent().removeClass("has-error");
			});
			$("div.form-group select").focus(function() {
				$(this).parent().removeClass("has-error");
			});
			
			$("#reservationSub").click(function(e) {
				e.preventDefault();
				if (!$.trim($("#reservationUsername").val())) {
					$("#reservationUsername").parent().addClass("has-error");
					return;
				}
				var phone = $("#reservationPhoneNum").val();
				var phoneReg = /^1[3-9][0-9]{9}$/;
				if (!$.trim(phone) || !phoneReg.test(phone)) {
					$("#reservationPhoneNum").parent().addClass("has-error");
					return;
				}
				if ($("#reservationType").val() === "-1") {
					$("#reservationType").parent().addClass("has-error");
					return;
				}
				if ($("#village").val() === "-1") {
					$("#village").parent().addClass("has-error");
					return;
				}
				if (!$.trim($("#reservationAddress").val())) {
					$("#reservationAddress").parent().addClass("has-error");
					return;
				}
				submit();
			});
		});
	</script>
</html>