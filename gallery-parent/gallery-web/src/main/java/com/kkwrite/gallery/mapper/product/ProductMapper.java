package com.kkwrite.gallery.mapper.product;

import java.util.List;

import com.kkwrite.gallery.pojo.product.Product;

public interface ProductMapper {
	
	public Product selectByPrimaryKey(int productId);

	public List<Product> selectAllProduct(int isValid);
	
	public List<Product> selectProducts(int[] products);
	
}
