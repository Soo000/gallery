function formatTimestamp(timestamp) {
	return formatDate(new Date(timestamp));
}

function formatDate(date) {
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}
	var day = date.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	var hours = date.getHours();
	if (hours < 10) {
		hours = "0" + hours;
	}
	var minutes = date.getMinutes();
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	var seconds = date.getSeconds();
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

function formatOrderStatus(status) {
	// 1-待付款 2-已支付 3-待收货 4-已完成 5-已过期 6-已取消
	switch (status) {
	case 1:
		return "待付款";
	case 2:
		return "已支付";
	case 3:
		return "待收货";
	case 4:
		return "已完成";
	case 5:
		return "已过期";
	case 6:
		return "已取消";
	}
}

function formatOrderType(type) {
	// 1-产品订单 2-预约订单
	switch (type) {
	case 1:
		return "产品订单";
	case 2:
		return "预约订单";
	}
}

function formatIsValid(isValid) {
	// 无效-0  有效-1
	switch (isValid) {
	case 0:
		return "失效";
	case 1:
		return "有效";
	}
}