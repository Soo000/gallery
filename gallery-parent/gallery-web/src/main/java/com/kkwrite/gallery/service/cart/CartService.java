package com.kkwrite.gallery.service.cart;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.product.Product;

public interface CartService {

	public Product queryCartProduct(String username, int productId) throws ServiceException;
	
	public List<Product> queryCartProducts(String username) throws ServiceException;
	
	public boolean addToCart(String username, int productId, int[] props, int productNum) throws ServiceException;
	
	public boolean removeFromCart(String username, int[] productIds) throws ServiceException;
	
}