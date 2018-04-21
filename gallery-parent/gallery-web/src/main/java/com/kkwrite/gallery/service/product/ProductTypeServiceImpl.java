package com.kkwrite.gallery.service.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.product.GlyProductTypeMapper;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.product.GlyProductType;

@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyProductTypeMapper glyProductTypeMapper;
	
	@Override
	public GlyProductType queryProductType(Integer productTypeId) throws ServiceException {
		logger.debug("[ begin ] ProductTypeServiceImpl.queryProductType(), productTypeId = " + productTypeId);
		
		GlyProductType glyProductType = new GlyProductType();
		
		logger.debug("[ end ] ProductTypeServiceImpl.queryProductType().");
		return glyProductType;
	}
	
	@Override
	public List<GlyProductType> queryProductType(int[] productTypeId) throws ServiceException {
		logger.debug("[ begin ] ProductTypeServiceImpl.queryProductType(), productTypeId = " + productTypeId);
		
		List<GlyProductType> productTypes = glyProductTypeMapper.selectByPrimaryKeys(productTypeId);
		
		logger.debug("[ end ] ProductTypeServiceImpl.queryProductType().");
		return productTypes;
	}
	
	@Override
	public List<GlyProductType> queryProductTypeByParentId(Integer parentProductTypeId) throws ServiceException {
		logger.debug("[ begin ] ProductTypeServiceImpl.queryProductTypeByParentId(), parentProductTypeId = " + parentProductTypeId);
		
		GlyProductType param = new GlyProductType();
		param.setParentProductTypeId(parentProductTypeId);
		param.setIsValid(BasePojo.IS_VALID_Y);
		List<GlyProductType> productTypeItems = glyProductTypeMapper.selectSelective(param);
		
		logger.debug("[ end ] ProductTypeServiceImpl.queryProductTypeByParentId().");
		return productTypeItems;
	}

	@Override
	public List<GlyProductType> queryProductType(GlyProductType param) throws ServiceException {
		logger.debug("[ begin ] ProductTypeServiceImpl.queryProductTypeItems(), param = " + param);
		
		List<GlyProductType> productTypeItems = glyProductTypeMapper.selectSelective(param);
		logger.debug("[ run ] ProductTypeServiceImpl.queryProductTypeItems(), productTypeItems = " + productTypeItems);
		
		logger.debug("[ end ] ProductTypeServiceImpl.queryProductTypeItems().");
		return productTypeItems;
	}

}
