package com.kkwrite.gallery.service.product;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.product.GlyProductType;

public interface ProductTypeService {

	public GlyProductType queryProductType(Integer productTypeId) throws ServiceException;
	
	public List<GlyProductType> queryProductType(int[] productTypeIds) throws ServiceException;
	
	public List<GlyProductType> queryProductTypeByParentId(Integer parentProductTypeId) throws ServiceException;
	
	public List<GlyProductType> queryProductType(GlyProductType param) throws ServiceException;
	
}