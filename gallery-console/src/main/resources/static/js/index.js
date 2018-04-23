;$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	// 退出
	$("#logout_btn").click(function (e) {
		e.preventDefault();
		$("#logout_form").submit();
    });
	
	// 页面切换菜单选中样式
	$("#side_bar li").click(function() {
		$("#side_bar li").removeClass("active");
		$(this).addClass("active");
	});
	
	// 侧边添加商品按钮单击
	$("#add_product_bar").click(function() {
		$.get("/product/pre-add", {"_": new Date().getTime()}, function(page) {
			$("div.main").html("").html(page);
		});
	});

	// 侧边编辑商品按钮单击
    $("#edit_product_bar").click(function () {
        $.get("/product/pre-edit", {"_": new Date().getTime()}, function (page) {
            $("div.main").html("").html(page);
            getProductByPage(1);
        });
    });

	// 侧边栏全部订单按钮单击
	$("#all_order_show").click(function () {
		$.get("/order/all-order", {"_": new Date().getTime()}, function (page) {
            $("div.main").html("").html(page);
            // 查询第一页数据
            getAllOrders(1);
        });
    });

	// 侧边栏待付款商品按钮单击
	$("#pending_payment_order_show").click(function () {
        openOrderPageByStatus(1);
    });

	// 侧边栏已支付商品按钮单击
	$("#paid_order_show").click(function () {
        openOrderPageByStatus(2);
    });

	// 侧边栏待收货商品按钮单击
	$("#no_receive_order_show").click(function () {
		openOrderPageByStatus(3)
    });

    // 侧边栏已完成商品按钮单击
	$("#completed_order_show").click(function () {
		openOrderPageByStatus(4)
    });

    // 侧边栏已过期商品按钮单击
    $("#expired_order_show").click(function () {
        openOrderPageByStatus(5)
    });

    // 侧边栏已取消商品按钮单击
    $("#cancelled_order_show").click(function () {
        openOrderPageByStatus(6)
    });

	// 根据状态获取订单页面
	function openOrderPageByStatus(status) {
        // 1-待付款 2-已支付 3-待收货 4-已完成 5-已过期 6-已取消
		$.get("/order/order-status", {"status": status, "_": new Date().getTime()}, function (page) {
            $("div.main").html("").html(page);
            // 查询第一页数据
            getStatusOrder(1, status);
        });
    }

	// 根据状态获取订单
	function getStatusOrder(page, status) {
        $.get("/order/statusorder/" + status + "/", {"page": page - 1, "size": 10}, function(result) {
            if (result.code !== 0) {
                return;
            }
        	buildOrdersTable(result, status);
            // 构建分页插件
            buildPagePlugin("div.main ul", result.data.totalElements, result.data.size, function (page) {
                getStatusOrder(page, status);
            });
        });
    }

	// 获取所有订单数据
	function getAllOrders(page) {
        $.get("/order/all", {"page": page - 1, "size": 10}, function(result) {
            if (result.code !== 0) {
            	return;
			}
        	buildOrdersTable(result);
            // 构建分页插件
            buildPagePlugin("div.main ul", result.data.totalElements, result.data.size, function (page) {
                getAllOrders(page);
            });
        });
	}

	// 构建订单表格
	function buildOrdersTable(result, status) {
        // 构建table body
        // 先清空
        $("div.main tbody").html("");
        // 新建
        var orders = result.data.content;
        var operateTd = "";
        if (status) {
            switch (status) {
                // 1-待付款 2-已支付 3-待收货 4-已完成 5-已过期 6-已取消
                case 1:
                    operateTd += "<button type='button' class='btn btn-link edit-price-btn'>修改金额</button>";
                    break;
                case 2:
                    operateTd += "<button type='button' class='btn btn-link'>发货</button>";
                    break;
                case 3:
                    operateTd += "-";
                    break;
                case 4:
                    operateTd += "-";
                    break;
                case 5:
                    operateTd += "-";
                    break;
                case 6:
                    operateTd += "-";
                    break;
                case 7:
                    operateTd += "-";
                    break;
            }
		}
        var str = "";
        $.each(orders, function(i, v) {
            str = "";
            str += "<tr>";
            str += "<td>" + v.orderCode + "</td>";
            str += "<td>" + v.glyUser.username + "</td>";
            str += "<td>" + v.glyAddress.receiver + "</td>";
            str += "<td>" + v.glyAddress.address + "</td>";
            str += "<td>" + v.productNum + "</td>";
            str += "<td>" + v.realPayment + "</td>";
            str += "<td>" + formatOrderType(v.orderType) + "</td>";
            str += "<td>" + formatOrderStatus(v.orderStatus) + "</td>";
            str += "<td>" + formatTimestamp(v.validTime) + "</td>";
            str += "<td>" + formatTimestamp(v.invalidTime) + "</td>";
            str += "<td>" + formatIsValid(v.isValid) + "</td>";
            str += "<td>" + formatTimestamp(v.creationTime) + "</td>";
            str += "<td>" + formatTimestamp(v.updateTime) + "</td>";
            if (operateTd) {
                str += "<td>" + operateTd + "</td>";
			}
            str += "</tr>";
            $("div.main tbody").append($(str));
        });
    }

	// 构建分页插件
	function buildPagePlugin(selector, counts, size, onChange) {
		if ($(selector + " li").length  > 0)
			return;
		$(selector).jqPaginator({
            totalCounts: counts,
            pageSize: size,
            visiblePages: 10,
            currentPage: 1,
            first: '<li class="first"><a href="javascript:void(0);">首页</a></li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
            last: '<li class="last"><a href="javascript:void(0);">尾页</a></li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
            onPageChange: function (page, type) {
                if (type !== "init") {
                    onChange.call(this, page);
                }
            }
        });
	}

	// 修改价格按钮单击事件
    $("div.main").on("click", "button.edit-price-btn", function (e) {
        e.preventDefault();
        var _this = $(this);
        var oldPrice = _this.parent().prevAll().eq(7).text();
        var newPrice = prompt("请输入新价格：", oldPrice);
        if (newPrice) {
            if (!/^\d+$/.test(newPrice) || newPrice < 0) {
                alert("请输入正确价格！");
                return;
            }
            // 修改价格
            $.post("/order/editprice/" + _this.parent().parent().children().eq(0).text(), {"price": newPrice}, function (result) {
                if (result.code !== 0) {
                    alert("修改失败！");
                } else {
                    _this.parent().prevAll().eq(7).text(newPrice);
                }
            });
        }
    });

	// 添加商品页面，保存按钮单击事件
	$("div.main").on("click", "#add_a_product_btn", function(e) {
		e.preventDefault();
		// 参数校验，一定要校验文件是否为图片
		var files = $("#add_product_div input[name='productPics']")[0].files;
		var allowPicTypes = ["image/jpg", "image/jpeg","image/png","image/x-png","image/bmp"];
		if (files.length === 0) {
			alert("请选择图片！");
			return;
		}
		var acceptN = 0;
		for (var i=0; i<files.length; i++) {
			var fileType = files[i].type;
			var typeAccepted = false;
			for (var j=0; j<allowPicTypes.length; j++) {
				if (fileType === allowPicTypes[j]) {
					typeAccepted = true;
				}
			}
			if (typeAccepted) {
				acceptN++;
			}
		}
		if (acceptN !== files.length) {
			alert("请检查图片类型是否正确！");
			return;
		}
		// 提交操作
		$.ajax({
			"url": "/product/add",
			"type": "post",
			"data": new FormData($("#add_product_div form")[0]),
			"cache": false,
			"processData": false,
			"contentType": false
		}).done(function(res) {
			if (res.code === 0) {
				alert("添加商品成功！");
			} else {
				alert("添加商品失败！" + res.msg);
			}
		}).fail(function() {
			alert("添加商品失败！");
		});
	});

	// 添加商品页面，导入按钮单击事件
    $("div.main").on("click", "#import_product_btn", function (e) {
        e.preventDefault();
        $.ajax({
            "url": "/product/upload",
            "type": "post",
            "data": new FormData($("#add_product_div form")[0]),
            "cache": false,
            "processData": false,
            "contentType": false
        }).done(function(res) {
            if (res.code === 0) {
                alert("导入成功！");
            } else {
                alert("导入失败！");
            }
        }).fail(function() {
            alert("导入失败！");
        });
    });

    // 分页查询产品
    function getProductByPage(page) {
        $.get("/product/query-product", {"page": page - 1, "size": 10, "_": new Date().getTime()}, function (result) {
            if (result.code !== 0) {
                return;
            }
            // 构建table body
            // 先清空
            $("div.main tbody").html("");
            var products = result.data.content;
            var str = "";
            $.each(products, function(i, v) {
                str = "";
                str += "<tr>";
                str += "<td>" + v.productId + "</td>";
                str += "<td>" + v.productName + "</td>";
                str += "<td>" + (v.productIntro.length > 8 ? (v.productIntro.substr(0, 6) + "...") : v.productIntro) + "</td>";
                str += "<td>" + v.initialPrice + "</td>";
                str += "<td>" + v.realPrice + "</td>";
                str += "<td>" + v.discount + "</td>";
                str += "<td>" + v.inventoryNumber + "</td>";
                str += "<td>" + v.saleNumber + "</td>";
                str += "<td>" + v.bookNumber + "</td>";
                str += "<td>" + v.productOrder + "</td>";
                str += "<td>" + formatIsValid(v.isValid) + "</td>";
                str += "<td>" + formatTimestamp(v.creationTime) + "</td>";
                str += "<td>" + formatTimestamp(v.updateTime) + "</td>";
                str += "<td><button type='button' class='btn btn-link product_edit_btn'>修改</button>" +
                    "<button type='button' class='btn btn-link product_delete_btn'>删除</button></td>";
                str += "</tr>";
                $("div.main tbody").append($(str));
            });
            buildPagePlugin("div.main ul", result.data.totalElements, result.data.size, function (page) {
                getProductByPage(page);
            });
            // 删除按钮绑定单击事件
            $("div.main button.product_delete_btn").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                var _this = $(this);
                $.post("/product/delete", {"productId":$.trim($(this).parent().parent().find("td").eq(0).text())}, function (result) {
                    if (result.code === 0) {
                        _this.parent().parent().remove();
                    } else {
                        alert("删除产品服务异常！");
                    }
                });
            });
        });
    }

    // 产品编辑页面，产品列表行单击事件
    $("div.main").on("click", "#product_list_in_edit_page tbody tr", function (e) {
        e.preventDefault();
        var _this = $(this);
        var productId = $.trim(_this.children().eq(0).text());
        $.get("/product/getProductById", {"productId": productId, "_":new Date().getTime()}, function (page) {
            $("#product_detail_edit_area").html("").html(page);
            $("html,body").animate({scrollTop: $("#product_detail_edit_area").offset().top}, 1000);
        });
    });

});