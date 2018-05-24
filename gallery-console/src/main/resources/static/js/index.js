;$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr) {
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

    // 侧边栏首页模块管理按钮单击
    $("#home_module_manage").click(function () {
        openHomeModuleManagePage();
    });

    // 侧边栏首页分模块管理按钮单击
    $("#home_module_item_manage").click(function () {
        openHomeModuleItemManagePage();
    });

    // 广告活动管理导航按钮单击事件
    $("#activity_manage_bar").click(function (e) {
        e.preventDefault();
        openActivityManagePage();
    });

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

    // 修改产品页面，删除图片操作
    $("body").on("click", "div.card a.delete-btn", function (e) {
        e.preventDefault();
        // 获取到img元素
        var $img = $(this).parent().parent().parent().find("img");
        $img.attr("src", "/img/add_picture.jpg");
        // 判断该位置是否有图片
        if ($img.data("picture")) {
            $img.data("operate", "delete");
        } else {
            $img.data("operate", "-1");
        }
    });

    // 修改产品页面，添加图片操作
    $("body").on("click", "div.card a.add-btn", function (e) {
        e.preventDefault();
        // 获取到img元素
        var $img = $(this).parent().parent().parent().find("img");
        // 判断该位置是否有图片
        if (!$img.data("picture")) {
            $img.data("operate", "add");
        } else {
            $img.data("operate", "modify");
        }
        $img.parent().find("input").click();
    });

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
                operateTd += "<button type='button' class='btn btn-link edit-price-btn'>修改<br/>金额</button>";
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
        str += "<th>" + v.orderCode + "</th>";
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
        first: '<li class="page-item first"><a class="page-link" href="javascript:void(0);">首页</a></li>',
        prev: '<li class="page-item prev"><a class="page-link" href="javascript:void(0);">上一页</a></li>',
        next: '<li class="page-item next"><a class="page-link" href="javascript:void(0);">下一页</a></li>',
        last: '<li class="page-item last"><a class="page-link" href="javascript:void(0);">尾页</a></li>',
        page: '<li class="page-item"><a class="page-link" href="javascript:void(0);">{{page}}</a></li>',
        onPageChange: function (page, type) {
            if (type !== "init") {
                onChange.call(this, page);
            }
        }
    });
}

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
        // 修改按钮绑定单击事件
        $("div.main button.product_edit_btn").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            var _this = $(this);
            var productId = $.trim(_this.parent().parent().find("td").eq(0).text());
            $.get("/product/getProductById", {"productId": productId, "_":new Date().getTime()}, function (page) {
                if ($('#editProductModal').length > 0) {
                    $('#editProductModal').remove();
                }
                $("body").append(page);
                // 取出type值和attr值做设置
                $.get("/product/getTypeAndAttr/" + productId + "?_=" + new Date().getTime(), function (result) {
                    if (result.code === 0) {
                        var types = result.data.types;
                        var props = result.data.props;
                        if (types instanceof Array && types.length !== 0) {
                            $.each(types, function (i, v) {
                                $("#productTypes-" + v).prop("checked", true);
                            });
                        }
                        if (props instanceof Array && props.length !== 0) {
                            $.each(props, function (i, v) {
                                $("#productAttrs-" + v).prop("checked", true);
                            });
                        }
                    }
                    // 获取产品配图
                    $.get("/product/getProductPicturesByProductId", {"productId": productId, "_":new Date().getTime()}, function (result) {
                        var mainPictures = [], detailPictures = [];
                        // 分拣图片
                        $.each(result.data, function (i, picture) {
                            if (picture.productPictureType === 11) {
                                mainPictures.push(picture);
                            } else if (picture.productPictureType === 21) {
                                detailPictures.push(picture);
                            }
                        });
                        // 替换主图
                        var $imgs = $("div.main-picture-area img");
                        $.each(mainPictures, function (i, picture) {
                            $($imgs[i]).attr("src", "http://www.artlyt.com.cn/res/img/" + picture.productPictureFileName);
                            $($imgs[i]).data("picture", picture);
                        });
                        // 替换详情图
                        $imgs = $("div.detail-picture-area img");
                        $.each(detailPictures, function (i, picture) {
                            $($imgs[i]).attr("src", "http://www.artlyt.com.cn/res/img/" + picture.productPictureFileName);
                            $($imgs[i]).data("picture", picture);
                        });
                        // 显示modal
                        $('#editProductModal').modal();
                    });
                });
            });
        });
        // 删除按钮绑定单击事件
        $("div.main button.product_delete_btn").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            var _this = $(this);
            if (confirm("删除操作数据不可恢复，是否继续？")) {
                $.post("/product/delete", {"productId":$.trim($(this).parent().parent().find("td").eq(0).text())}, function (result) {
                    if (result.code === 0) {
                        _this.parent().parent().remove();
                    } else {
                        alert("删除产品服务异常！");
                    }
                });
            } else {
            }
        });
    });
}

function modifyAddOnchange(inputObject) {
    var reader = new FileReader();
    reader.onload = function() {
        var $img = $(inputObject).parent().parent().parent().find("img");
        $img.attr("src", reader.result);
        $img.data("picture_new", reader.result);
    };
    reader.readAsDataURL(inputObject.files[0]);
}

function modifyProductModalSubmit() {
    var productName = $.trim($("#productName").val());
    var productIntro = $.trim($("#productIntro").val());
    var productDetail = $.trim($("#productDetail").val());
    var initialPrice = $.trim($("#initialPrice").val());
    var discount = $.trim($("#discount").val());
    var inventoryNumber = $.trim($("#inventoryNumber").val());
    var bookNumber = $.trim($("#bookNumber").val());
    var productOrder = $.trim($("#productOrder").val());
    var isValid = $("input[name='valid']:checked").val();
    var productAttrs = [];
    $("input[name='productAttrs']").each(function () {
        if (this.checked) {
            productAttrs.push($(this).val());
        }
    });
    var productTypes = [];
    $("input[name='productTypes']").each(function () {
        if (this.checked) {
            productTypes.push($(this).val());
        }
    });

    if (!productName || !productIntro || !productDetail) {
        return;
    }
    if (!initialPrice || !$.isNumeric(initialPrice)) {
        return;
    }
    if (!discount || !$.isNumeric(discount)) {
        return;
    }
    if (!inventoryNumber || !$.isNumeric(inventoryNumber)) {
        return;
    }
    if (!bookNumber || !$.isNumeric(bookNumber)) {
        return;
    }
    if (!productOrder || !$.isNumeric(productOrder)) {
        return;
    }

    var params = {
        "productId": $("#productId").val(),
        "productName": productName,
        "productIntro":productIntro,
        "productDetail":productDetail,
        "initialPrice":initialPrice,
        "discount":discount,
        "inventoryNumber":inventoryNumber,
        "bookNumber":bookNumber,
        "productOrder":productOrder,
        "isValid":isValid
    };

    if (productAttrs.length !== 0) {
        params.productAttrs = productAttrs;
    }
    if (productTypes.length !== 0) {
        params.productTypes = productTypes;
    }

    var mainPictures = [];
    $("#modifyProductModalForm div.main-picture-area img").each(function (index) {
        var operateType = $(this).data("operate");
        if (operateType && operateType !== "-1") {
            var img = {};
            img.position = index;
            img.operateType = operateType;
            if (operateType !== "delete") {
                img.newPicture = $(this).data("picture_new");
            }
            if (operateType !== "add") {
                img.oldPicture = $(this).data("picture");
            }
            mainPictures.push(img);
        }
    });
    if (mainPictures.length !== 0) {
        params.mainPictures = mainPictures;
    }

    var detailPictures = [];
    $("#modifyProductModalForm div.detail-picture-area img").each(function (index) {
        var operateType = $(this).data("operate");
        if (operateType && operateType !== "-1") {
            var img = {};
            img.position = index;
            img.operateType = operateType;
            if (operateType !== "delete") {
                img.newPicture = $(this).data("picture_new");
            }
            if (operateType !== "add") {
                img.oldPicture = $(this).data("picture");
            }
            detailPictures.push(img);
        }
    });
    if (detailPictures.length !== 0) {
        params.detailPictures = detailPictures;
    }

    $.post("/product/modify", {"params":JSON.stringify(params)}, function (result) {
        if (result.code === 0) {
            $("#edit_product_bar").click();
            $("#editProductModal").modal('toggle');
            alert("修改成功！");
        } else {
            alert("修改失败！");
        }
    }, 'json');
}

function openHomeModuleManagePage() {
    $.get("/module/openHomeModuleManagePage", function (page) {
        $("div.main").html("").html(page);
        // 模态框注册事件
        $("#editModuleModal").on("hidden.bs.modal", function (e) {
            $("#editModuleModalForm")[0].reset();
            $("#moduleId").val("-1");
        });
    });
}

function editModuleSubmit() {
    var formData = new FormData($("#editModuleModalForm")[0]);
    $.ajax({
        "url": "/module/saveModule",
        "type": "post",
        "data": formData,
        "dataType": "json",
        "catch": false,
        "contentType": false,
        "processData": false
    }).done(function (result) {
        if (result.code === 0) {
            $("#editModuleModal").modal('hide');
            setTimeout(function () {
                $("#home_module_manage").click();
            }, 500);
        } else {
            alert(result.msg);
        }
    });
}

function preUpdateModule(element) {
    var event = window.event || arguments.callee.caller.arguments[0];
    event.stopPropagation();
    var $tr = $(element).parent().parent();
    $("#moduleId").val($.trim($tr.children().eq(0).text()));
    $("#moduleName").val($.trim($tr.children().eq(1).text()));
    $("#parentModuleId").val($.trim($tr.children().eq(2).text()));
    $("#moduleDescription").val($.trim($tr.children().eq(6).text()));
    $("#moduleTitle").val($.trim($tr.children().eq(4).text()));
    $("#moduleTemplate").val($.trim($tr.children().eq(5).text()));
    $("#moduleOrder").val($.trim($tr.children().eq(7).text()));
    var valid = $.trim($tr.children().eq(8).text());
    switch (valid) {
        case "无效":
            valid = 0;
            break;
        default:
            valid = 1;
    }
    $("#valid" + valid).attr("checked", true);
    $("#editModuleModal").modal('show');
    return false;
}

function deleteModule(element) {
    var event = window.event || arguments.callee.caller.arguments[0];
    event.stopPropagation();
    var $tr = $(element).parent().parent();
    var id = $tr.children().eq(0).text();
    $.post("/module/deleteModule", {"id": id}, function (result) {
        if (result.code === 0) {
            $tr.remove();
        } else {
            alert("删除失败！");
        }
    });
    return false;
}

function openHomeModuleItemManagePage(callBack) {
    $.get("/module/openHomeModuleItemManagePage", function (page) {
        $("div.main").html("").html(page);
        if (typeof callBack === 'function') {
            callBack();
        }
    });
}

function selectModule2Manage(element) {
    var moduleId;
    if (typeof element !== "number") {
        moduleId = $.trim($(element).parent().children("span").text());
    } else {
        moduleId = element;
    }
    $.get("/module/getModuleItemListPage/" + moduleId, function (moduleList) {
        $("div.module-item-list").html("").html(moduleList);
        // 注册modal事件
        $("#moduleItemManageModal").on("hidden.bs.modal", function (e) {
            $("#editModuleItemForm")[0].reset();
            $("#moduleItemId").val("-1");
        });
    });
}

function moduleItemEditSubmit() {
    $.ajax({
        "url": "/module/saveModuleItem",
        "type": "post",
        "data": new FormData($("#editModuleItemForm")[0]),
        "dataType": "json",
        "catch": false,
        "contentType": false,
        "processData": false
    }).done(function (result) {
        if (result.code === 0) {
            $("#moduleItemManageModal").modal('hide');
            setTimeout(function () {
                $("#module-select-dropdown-span-" + $("#moduleId").val()).siblings('a').click();
            }, 500);
        } else {
            alert(result.msg);
        }
    });
}

function modifyModuleItem(element) {
    var $tr = $(element).parent().parent();
    $("#moduleItemId").val($.trim($tr.children().eq(1).text()));
    $("#productId").val($.trim($tr.children().eq(2).text()));
    $("#moduleItemName").val($.trim($tr.children().eq(3).text()));
    $("#moduleItemOrder").val($.trim($tr.children().eq(5).text()));
    var type = $.trim($tr.children().eq(4).text());
    switch (type) {
        case "广告":
            type = 0;
            break;
        case "物品":
            type = 2;
            break;
        default:
            type = 1;
    }
    $("#editModuleItemForm input[name='moduleItemType']").eq(type).attr("checked", true);
    var valid = $.trim($tr.children().eq(6).text());
    switch (valid) {
        case "无效":
            valid = 0;
            break;
        default:
            valid = 1;
    }
    $("#editModuleItemForm input[name='valid']").eq(valid).attr("checked", true);
    $("#moduleItemManageModal").modal('show');
}

function deleteModuleItem(element) {
    var $tr = $(element).parent().parent();
    var itemId = $.trim($tr.children().eq(1).text());
    $.post("/module/deleteModuleItem/" + itemId, function (result) {
        if (result.code === 0) {
            $tr.remove();
        } else {
            alert("删除失败！");
        }
    });
}

function gotoModuleItemManage(element) {
    openHomeModuleItemManagePage(function () {
        selectModule2Manage(parseInt($.trim($(element).find("th").eq(0).text()), 0));
    });
}

function resetEditActivityForm() {
    $("#editActivityForm")[0].reset();
    $("#activityId").val("-1");
    $("#addImg").attr("src", "/img/add_picture.jpg");
    $("#addImg").data("picture", null);
    $('#activityTime').val(formatDatetimeLocal(new Date()) + " - " + formatDatetimeLocal(new Date(new Date().getTime() + 24 * 60 * 60 * 1000)));
}

function openActivityManagePage() {
    $.get("/activity/managePage", function (page) {
        $("div.main").html("").html(page);
        // 初始化日期选择控件
        laydate.render({
            elem: '#activityTime',
            type: 'datetime',
            range: true,
            calendar: true,
            value: formatDatetimeLocal(new Date()) + " - " + formatDatetimeLocal(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
        });
        // modal hidden事件
        $("#activityManageModal").on("hidden.bs.modal", function () {
            resetEditActivityForm();
        });
    });
}

function formatDatetimeLocal(datetime) {
    if (!datetime instanceof Date) {
        return;
    }
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1;
    month = month >= 10 ? month : "0" + month;
    var day = datetime.getDate();
    day = day >= 10 ? day : "0" + day;
    var hour = datetime.getHours();
    hour = hour >= 10 ? hour : "0" + hour;
    var minute = datetime.getMinutes();
    minute = minute >= 10 ? minute : "0" + minute;
    var seconds = datetime.getSeconds();
    seconds = seconds >= 10 ? seconds : "0" + seconds;
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds;
}

function addActivityPicture(element) {
    var reader = new FileReader();
    reader.onload = function() {
        var $img = $("#addImg");
        $img.attr("src", reader.result);
        $img.data("picture", reader.result);
    };
    reader.readAsDataURL(element.files[0]);
}

function editActivitySubmit() {
    var activityId = $.trim($("#activityId").val());
    var activityName = $.trim($("#activityName").val());
    var activityUrl = $.trim($("#activityUrl").val());
    var activityType = $("input[name='activityType']:checked").val();
    var activityTime = $.trim($("#activityTime").val());
    var activityDescription = $.trim($("#activityDescription").val());
    var valid = $("input[name='valid']:checked").val();
    var picture = $("#addImg").data("picture");

    var params = {};
    if (activityId !== "-1") {
        params.activityId = activityId;
    }
    if (activityName) {
        params.activityName = activityName;
    }
    if (activityUrl) {
        params.activityUrl = activityUrl;
    }
    if (activityType) {
        params.activityType = activityType;
    }
    if (activityTime) {
        params.activityTime = activityTime;
    }
    if (activityDescription) {
        params.activityDescription = activityDescription;
    }
    if (valid) {
        params.valid = valid;
    }
    if (picture) {
        params.picture = picture;
    }

    $.post("/activity/save", params, function (result) {
        if (result.code === 0) {
            $("#activityManageModal").modal('hide');
            resetEditActivityForm();
            setTimeout(function () {
                $("#activity_manage_bar").click();
            }, 500);
        } else {
            alert(result.msg);
        }
    });
}

function preEditActivity(element) {
    var $tds = $(element).parent().parent().children();
    $("#addImg").attr("src", $($tds.eq(0)).find("img").attr("src"));
    $("#activityId").val($($tds.eq(1)).text());
    $("#activityName").val($($tds.eq(2)).text());
    $("#activityUrl").val($($tds.eq(3)).text());
    $("#activityType" + $($tds.eq(4)).attr("value")).prop("checked", true);
    $("#activityTime").val($($tds.eq(5)).text().substr(0, 19) + " - " + $($tds.eq(6)).text().substr(0, 19));
    $("#activityDescription").val($($tds.eq(7)).text());
    $("#valid" + $($tds.eq(8)).attr("value")).prop("checked", true);
    $("#activityManageModal").modal('show');
}

function deleteActivity(element) {
    $.post("/activity/delete/", {"id": $($(element).parent().parent().children().eq(1)).text()}, function (result) {
        if (result.code === 0) {
            $(element).parent().parent().remove();
        } else {
            alert(result.msg);
        }
    });
}

function addProductSubmit() {
    var _this = this;
    // 参数校验，一定要校验文件是否为图片
    if (!$("#product_name").val()) {
        $("#product_name").focus();
        return;
    }
    if (!$("#product_intro").val()) {
        $("#product_intro").focus();
        return;
    }
    if (!$("#product_detail").val()) {
        $("#product_detail").focus();
        return;
    }
    if (!$("#init_price").val()) {
        $("#init_price").focus();
        return;
    }
    if (!$("#discount").val()) {
        $("#discount").val(10);
    }
    if (!$("#inventory_number").val()) {
        $("#inventory_number").focus();
        return;
    }
    if (!$("#product_order").val()) {
        $("#product_order").val(999);
    }

    var total = 0;
    var productAttrInputs = $("input[name='productAttrs']");
    for (var i = 0; i < productAttrInputs.length; i++) {
        if ($(productAttrInputs[i]).prop("checked")) {
            total++;
        }
    }
    if (total === 0) {
        alert("请选择产品属性！");
        return;
    }
    total = 0;
    var productTypeInputs = $("input[name='productTypes']");
    for (var i = 0; i < productTypeInputs.length; i++) {
        if ($(productTypeInputs[i]).prop("checked")) {
            total++;
        }
    }
    if (total === 0) {
        alert("请选择产品类型！");
        return;
    }

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
                break;
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
            $(_this).siblings('button').click();
            alert("添加商品成功！");
        } else {
            alert("添加商品失败！" + res.msg);
        }
    }).fail(function() {
        alert("添加商品失败！");
    });
}