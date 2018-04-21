package com.kkwrite.gallery.pojo.order;

import java.util.List;

import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.product.Product;

public class OrderProducts extends OrderItems {
	
	private int productNum;
	private int productClassNum;
	private float realPayment;
	private List<Product> products;
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public int getProductClassNum() {
		return productClassNum;
	}
	public void setProductClassNum(int productClassNum) {
		this.productClassNum = productClassNum;
	}
	public float getRealPayment() {
		return realPayment;
	}
	public void setRealPayment(float realPayment) {
		this.realPayment = realPayment;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
