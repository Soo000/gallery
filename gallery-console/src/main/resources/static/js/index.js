;$(function() {
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
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

    // 批量导入导航栏按钮单击事件
    $("#import_product_info").click(function (e) {
        e.preventDefault();
        openImportProductsInfoPage();
    });

    // 商品属性管理导航栏按钮单击事件
    $("#product_prop_manage_bar").click(function (e) {
        e.preventDefault();
        openProductPropManagePage();
    });

    // 商品类型管理导航栏按钮单击事件
    $("#product_type_manage_bar").click(function (e) {
        e.preventDefault();
        openProductTypeManagePage();
    });

	// 修改价格按钮单击事件
    $("div.main").on("click", "button.edit-price-btn", function (e) {
        e.preventDefault();
        let _this = $(this);
        let oldPrice = _this.parent().prevAll().eq(7).text();
        let newPrice = prompt("请输入新价格：", oldPrice);
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

    // 修改产品页面，删除图片操作
    let $body = $("body");
    $body.on("click", "div.card a.delete-btn", function (e) {
        e.preventDefault();
        // 获取到img元素
        let $img = $(this).parent().parent().parent().find("img");
        $img.attr("src", "/img/add_picture.jpg");
        // 判断该位置是否有图片
        if ($img.data("picture")) {
            $img.data("operate", "delete");
        } else {
            $img.data("operate", "-1");
        }
    });

    // 修改产品页面，添加图片操作
    $body.on("click", "div.card a.add-btn", function (e) {
        e.preventDefault();
        // 获取到img元素
        let $img = $(this).parent().parent().parent().find("img");
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
    let orders = result.data.content;
    let operateTd = "";
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
    let str = "";
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
        let products = result.data.content;
        let str = "";
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
            str += "<td><button type='button' class='btn btn-sm btn-primary product_edit_btn'><span class='oi oi-pencil'></span></button>" +
                "<button type='button' class='btn btn-sm btn-danger product_delete_btn'><span class='oi oi-trash'></span></button></td>";
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
            let _this = $(this);
            let productId = $.trim(_this.parent().parent().find("td").eq(0).text());
            $.get("/product/getProductById", {"productId": productId, "_":new Date().getTime()}, function (page) {
                let $editProductModal = $('#editProductModal');
                if ($editProductModal.length > 0) {
                    $editProductModal.remove();
                }
                $("body").append(page);
                // 取出type值和attr值做设置
                $.get("/product/getTypeAndAttr/" + productId + "?_=" + new Date().getTime(), function (result) {
                    if (result.code === 0) {
                        let types = result.data.types;
                        let props = result.data.props;
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
                        let mainPictures = [], detailPictures = [];
                        // 分拣图片
                        $.each(result.data, function (i, picture) {
                            if (picture.productPictureType === 11) {
                                mainPictures.push(picture);
                            } else if (picture.productPictureType === 21) {
                                detailPictures.push(picture);
                            }
                        });
                        // 替换主图
                        let $imgs = $("div.main-picture-area img");
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
            let _this = $(this);
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
    let reader = new FileReader();
    reader.onload = function() {
        let $img = $(inputObject).parent().parent().parent().find("img");
        $img.attr("src", reader.result);
        $img.data("picture_new", reader.result);
    };
    reader.readAsDataURL(inputObject.files[0]);
}

function modifyProductModalSubmit() {
    let productName = $.trim($("#productName").val());
    let productIntro = $.trim($("#productIntro").val());
    let productDetail = $.trim($("#productDetail").val());
    let initialPrice = $.trim($("#initialPrice").val());
    let discount = $.trim($("#discount").val());
    let inventoryNumber = $.trim($("#inventoryNumber").val());
    let bookNumber = $.trim($("#bookNumber").val());
    let productOrder = $.trim($("#productOrder").val());
    let isValid = $("input[name='valid']:checked").val();
    let productAttrs = [];
    $("input[name='productAttrs']").each(function () {
        if (this.checked) {
            productAttrs.push($(this).val());
        }
    });
    let productTypes = [];
    $("input[name='productTypes']").each(function () {
        if (this.checked) {
            productTypes.push($(this).val());
        }
    });

    let params = {
        "productId": $("#productId").val()
    };
    if (productName) {
        params.productName = productName;
    }
    if (productIntro) {
        params.productIntro = productIntro;
    }
    if (productDetail) {
        params.productDetail = productDetail;
    }
    if (initialPrice) {
        params.initialPrice = initialPrice;
    }
    if (discount) {
        params.discount = discount;
    }
    if (inventoryNumber) {
        params.inventoryNumber = inventoryNumber;
    }
    if (bookNumber) {
        params.bookNumber = bookNumber;
    }
    if (productOrder) {
        params.productOrder = productOrder;
    }
    if (isValid) {
        params.isValid = isValid;
    }
    if (productAttrs.length !== 0) {
        params.productAttrs = productAttrs;
    }
    if (productTypes.length !== 0) {
        params.productTypes = productTypes;
    }

    let mainPictures = [];
    $("#modifyProductModalForm div.main-picture-area img").each(function (index) {
        let operateType = $(this).data("operate");
        if (operateType && operateType !== "-1") {
            let img = {};
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

    let detailPictures = [];
    $("#modifyProductModalForm div.detail-picture-area img").each(function (index) {
        let operateType = $(this).data("operate");
        if (operateType && operateType !== "-1") {
            let img = {};
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
        $("#editModuleModal").on("hidden.bs.modal", function () {
            $("#editModuleModalForm")[0].reset();
            $("#moduleId").val("-1");
        });
    });
}

function editModuleSubmit() {
    let formData = new FormData($("#editModuleModalForm")[0]);
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
    let event = window.event || arguments.callee.caller.arguments[0];
    event.stopPropagation();
    let $tr = $(element).parent().parent();
    $("#moduleId").val($.trim($tr.children().eq(0).text()));
    $("#moduleName").val($.trim($tr.children().eq(1).text()));
    $("#parentModuleId").val($.trim($tr.children().eq(2).text()));
    $("#moduleDescription").val($.trim($tr.children().eq(6).text()));
    $("#moduleTitle").val($.trim($tr.children().eq(4).text()));
    $("#moduleTemplate").val($.trim($tr.children().eq(5).text()));
    $("#moduleOrder").val($.trim($tr.children().eq(7).text()));
    let valid = $.trim($tr.children().eq(8).text());
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
    let event = window.event || arguments.callee.caller.arguments[0];
    event.stopPropagation();
    let $tr = $(element).parent().parent();
    let id = $tr.children().eq(0).text();
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
    let moduleId;
    if (typeof element !== "number") {
        moduleId = $.trim($(element).parent().children("span").text());
    } else {
        moduleId = element;
    }
    $.get("/module/getModuleItemListPage/" + moduleId, function (moduleList) {
        $("div.module-item-list").html("").html(moduleList);
        // 注册modal事件
        $("#moduleItemManageModal").on("hidden.bs.modal", function () {
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
    let $tr = $(element).parent().parent();
    $("#moduleItemId").val($.trim($tr.children().eq(1).text()));
    $("#productId").val($.trim($tr.children().eq(2).text()));
    $("#moduleItemName").val($.trim($tr.children().eq(3).text()));
    $("#moduleItemOrder").val($.trim($tr.children().eq(5).text()));
    let type = $.trim($tr.children().eq(4).text());
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
    let valid = $.trim($tr.children().eq(6).text());
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
    let $tr = $(element).parent().parent();
    let itemId = $.trim($tr.children().eq(1).text());
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
    let $addImg = $("#addImg");
    $addImg.attr("src", "/img/add_picture.jpg");
    $addImg.data("picture", null);
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
    let year = datetime.getFullYear();
    let month = datetime.getMonth() + 1;
    month = month >= 10 ? month : "0" + month;
    let day = datetime.getDate();
    day = day >= 10 ? day : "0" + day;
    let hour = datetime.getHours();
    hour = hour >= 10 ? hour : "0" + hour;
    let minute = datetime.getMinutes();
    minute = minute >= 10 ? minute : "0" + minute;
    let seconds = datetime.getSeconds();
    seconds = seconds >= 10 ? seconds : "0" + seconds;
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds;
}

function addActivityPicture(element) {
    let reader = new FileReader();
    reader.onload = function() {
        let $img = $("#addImg");
        $img.attr("src", reader.result);
        $img.data("picture", reader.result);
    };
    reader.readAsDataURL(element.files[0]);
}

function editActivitySubmit() {
    let activityId = $.trim($("#activityId").val());
    let activityName = $.trim($("#activityName").val());
    let activityUrl = $.trim($("#activityUrl").val());
    let activityType = $("input[name='activityType']:checked").val();
    let activityTime = $.trim($("#activityTime").val());
    let activityDescription = $.trim($("#activityDescription").val());
    let valid = $("input[name='valid']:checked").val();
    let picture = $("#addImg").data("picture");

    let params = {};
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
    let $tds = $(element).parent().parent().children();
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

function addProductSubmit(element) {
    let _this = element;
    $(_this).prop("disabled", true);

    // 参数校验，一定要校验文件是否为图片
    let $productName = $("#product_name");
    if (!$productName.val()) {
        $productName.focus();
        $(_this).prop("disabled", false);
        return;
    }
    let $productIntro = $("#product_intro");
    if (!$productIntro.val()) {
        $productIntro.focus();
        $(_this).prop("disabled", false);
        return;
    }
    let $productDetail = $("#product_detail");
    if (!$productDetail.val()) {
        $productDetail.focus();
        $(_this).prop("disabled", false);
        return;
    }
    let $initPrice = $("#init_price");
    if (!$initPrice.val()) {
        $initPrice.focus();
        $(_this).prop("disabled", false);
        return;
    }
    let $discount = $("#discount");
    if (!$discount.val()) {
        $discount.val(10);
    }
    let $inventoryNumber = $("#inventory_number");
    if (!$inventoryNumber.val()) {
        $inventoryNumber.focus();
        $(_this).prop("disabled", false);
        return;
    }
    let $productOrder = $("#product_order");
    if (!$productOrder.val()) {
        $productOrder.val(999);
    }

    let total = 0;
    let productAttrInputs = $("input[name='productAttrs']");
    for (let i = 0; i < productAttrInputs.length; i++) {
        if ($(productAttrInputs[i]).prop("checked")) {
            total++;
        }
    }
    if (total === 0) {
        $(_this).prop("disabled", false);
        alert("请选择产品属性！");
        return;
    }
    total = 0;
    let productTypeInputs = $("input[name='productTypes']");
    for (let i = 0; i < productTypeInputs.length; i++) {
        if ($(productTypeInputs[i]).prop("checked")) {
            total++;
        }
    }
    if (total === 0) {
        $(_this).prop("disabled", false);
        alert("请选择产品类型！");
        return;
    }

    let files = $("#add_product_div input[name='productPics']")[0].files;
    let allowPicTypes = ["image/jpg", "image/jpeg","image/png","image/x-png","image/bmp"];
    if (files.length === 0) {
        $(_this).prop("disabled", false);
        alert("请选择图片！");
        return;
    }
    let acceptN = 0;
    for (let i=0; i<files.length; i++) {
        let fileType = files[i].type;
        let typeAccepted = false;
        for (let j=0; j<allowPicTypes.length; j++) {
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
        $(_this).prop("disabled", false);
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
    }).always(function () {
        $(_this).prop("disabled", false);
    });
}

function openImportProductsInfoPage() {
    $.get("/product/preImport").done(function (page) {
        $("div.main").html("").html(page);
    });
}

function importProductInfo() {
    $.ajax({
        "url": "/product/upload",
        "type": "post",
        "data": new FormData($("#import_product_div form")[0]),
        "cache": false,
        "processData": false,
        "contentType": false
    }).done(function(res) {
        if (res.code === 0) {
            alert("导入成功！");
        } else {
            alert(res.msg);
        }
    }).fail(function() {
        alert("导入失败！");
    });
}

function openProductPropManagePage() {
    $.get("/product-prop/open").done(function (page) {
        $("div.main").html("").html(page);
        $("#productPropManageModal").on("hidden.bs.modal", function () {
            $("#productPropManageForm")[0].reset();
        });
    });
}

function productPropManageSubmit() {
    let formData = new FormData($("#productPropManageForm")[0]);
    if (!$.trim(formData.get("propType"))) {
        return;
    }
    if (!$.trim(formData.get("propValue"))) {
        $("#propValue").focus();
        return;
    }
    if (!$.trim(formData.get("valid"))) {
        return;
    }

    $.ajax({
        "url": "/product-prop/save",
        "type": "post",
        "data": formData,
        "cache": false,
        "processData": false,
        "contentType": false
    }).done(function(res) {
        if (res.code === 0) {
            $("#productPropManageModal").modal('hide');
            setTimeout(function () {
                $("#product_prop_manage_bar").click();
            }, 500);
            alert("提交成功！");
        } else {
            alert(res.msg);
        }
    }).fail(function() {
        alert("提交失败！");
    });
}

function modifyProductProp(element) {
    let $tr = $(element).parent().parent();
    let $tds = $tr.find("td");
    $("#propId").val($.trim($tr.find("th")[0].innerText));
    $("#propName").val($.trim($tds.eq(0).text()));
    $("#propType").val($.trim($tds.eq(1).text()));
    $("#propValue").val($.trim($tds.eq(2).text()));
    let valid;
    switch ($.trim($tds.eq(3).text())) {
        case "无效":
            valid = 0;
            break;
        default:
            valid = 1;
    }
    $("#valid-" + valid).prop("checked", true);
    $("#productPropManageModal").modal('show');
}

function deleteProductProp(element) {
    let $tr = $(element).parent().parent();
    let id = $tr.find("th")[0].innerText;
    $.post("/product-prop/delete", {"id":id})
    .done(function (result) {
        if (result.code === 0) {
            $tr.remove();
        } else {
            alert("删除失败！");
        }
    })
    .fail(function () {
        alert("删除失败！");
    });
}

function openProductTypeManagePage() {
    $.get("/product-type/open").done(function (page) {
        $("div.main").html("").html(page);
        $("#productTypeManageModal").on("hidden.bs.modal", function () {
            $("#productTypeManageForm")[0].reset();
        });
    });
}

function productTypeAjaxSubmit() {
    $.ajax({
        "url": "/product-type/save",
        "type": "post",
        "data": new FormData($("#productTypeManageForm")[0]),
        "cache": false,
        "processData": false,
        "contentType": false
    }).done(function (res) {
        if (res.code === 0) {
            $("#productTypeManageModal").modal('hide');
            setTimeout(function () {
                $("#product_type_manage_bar").click();
            }, 500);
            alert("提交成功！");
        } else {
            alert(res.msg);
        }
    }).fail(function () {
        alert("提交失败！");
    });
}

function productTypeManageSubmit() {
    let $typeName = $("#productTypeName");
    if (!$.trim($typeName.val())) {
        $typeName.focus();
        return;
    }
    let $typeOrder = $("#productTypeOrder");
    if (!$.trim($typeOrder.val())) {
        $typeOrder.focus();
        return;
    }
    productTypeAjaxSubmit();
}

function productTypeManageAddSubItem(element) {
    let $tr = $(element).parent().parent();
    $("#parentProductTypeId").val($tr.find("th").text());
    $("#productTypeManageModal").modal('show');
}

function modifyProductType(element) {
    let $tr = $(element).parent().parent();
    $("#productTypeId").val($tr.find('th').text());
    $("#parentProductTypeId").val($tr.find('td').eq(0).text());
    $("#productTypeName").val($tr.find('td').eq(1).text());
    $("#productTypeValue").val($tr.find('td').eq(2).text());
    $("#productTypeOrder").val($tr.find('td').eq(3).text());
    let valid;
    switch ($tr.find('td').eq(4).text()) {
        case '无效':
            valid = 0;
            break;
        default:
            valid = 1;
    }
    $("#valid-" + valid).prop("checked", true);
    $("#productTypeManageModal").modal('show');
}

function deleteProductType(element) {
    let $tr = $(element).parent().parent();
    $.post("/product-type/delete", {"id": $tr.find('th').text()}).done(function (result) {
        if (result.code === 0) {
            $tr.remove();
        } else {
            alert(result.msg);
        }
    }).fail(function () {
        alert("删除失败！");
    });
}

function chooseProductId() {
    window.open("/product/open-choose-page", "choose product");
}
