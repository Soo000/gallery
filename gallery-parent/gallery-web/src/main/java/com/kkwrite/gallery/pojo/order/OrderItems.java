package com.kkwrite.gallery.pojo.order;

import com.kkwrite.gallery.pojo.BasePojo;

public class OrderItems {
	
	private String orderCode;
	private int orderStatus;
	private String orderStatusText;
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderStatusText() {
		orderStatusText = BasePojo.OrderDict.getOrderStatus(orderStatus);
		return orderStatusText;
	}
	public void setOrderStatusText(String orderStatusText) {
		this.orderStatusText = orderStatusText;
	}
}
