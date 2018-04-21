package com.kkwrite.gallery.mapper.cart;

import java.util.List;

import com.kkwrite.gallery.pojo.cart.GlyCart;
import com.kkwrite.gallery.pojo.product.Product;

public interface CartProductMapper {
	
	public List<Product> selectCartProducts(GlyCart glyCart);
	
}
