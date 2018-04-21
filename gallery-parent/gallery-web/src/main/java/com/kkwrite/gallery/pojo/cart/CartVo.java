package com.kkwrite.gallery.pojo.cart;

import java.util.ArrayList;
import java.util.List;

import com.kkwrite.gallery.pojo.product.Product;

public class CartVo {

	private int productId;
	private String cartCode;
	private int userId;
	private List<Product> products = new ArrayList<Product>();
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
