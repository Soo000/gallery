package com.kkwrite.gallery.service.product;

import java.util.List;
import java.util.Map;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.Pagination;
import com.kkwrite.gallery.pojo.product.GlyProductProp;
import com.kkwrite.gallery.pojo.product.GlyProductType;
import com.kkwrite.gallery.pojo.product.Product;

public interface ProductService {

	/**
	 * 根据 productId 查询产品
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	public Product queryProduct(int productId) throws ServiceException;
	
	/**
	 * 根据 productId[] 查询产品
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	public List<Product> queryProduct(int[] productIds) throws ServiceException;
	
	/**
	 * 分页查询产品
	 * @param productTypes
	 * @param pagination
	 * @return
	 * @throws ServiceException
	 */
	public List<Product> queryProductPagination(List<GlyProductType> productTypes, Pagination pagination) throws ServiceException;
	
	/**
	 * 查询产品配图
	 * @param products
	 * @return
	 * @throws ServiceException
	 */
	public List<Product> queryProductsPictures(List<Product> products) throws ServiceException;
	
	/**
	 * 对产品属性进行分组
	 * @param productProps
	 * @return
	 */
	public Map<String, List<GlyProductProp>> groupProductProps(List<GlyProductProp> productProps);
	
}