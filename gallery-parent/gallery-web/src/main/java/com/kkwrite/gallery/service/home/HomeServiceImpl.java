package com.kkwrite.gallery.service.home;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.module.GlyModuleItemMapper;
import com.kkwrite.gallery.pojo.module.GlyModuleItem;

@Service("homeService")
public class HomeServiceImpl implements HomeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyModuleItemMapper glyModuleItemMapper;
	
	@Override
	public GlyModuleItem queryModuleItem(int moduleId) throws ServiceException {
		logger.debug("[ begin ] HomeServiceImpl.queryModuleItem(), moduleId = " + moduleId);
		
		List<GlyModuleItem> moduleItems = queryModuleItems(moduleId);
		if (moduleItems == null || moduleItems.size() <= 0) return null;
		
		if (moduleItems.size() > 1) {
			throw new ServiceException("模块 ID 为 " + moduleId + " 得数据只能有 1 个");
		}
		
		logger.debug("[ end ] HomeServiceImpl.queryModuleItem().");
		return moduleItems.get(0);
	}

	@Override
	public List<GlyModuleItem> queryModuleItems(int moduleId) throws ServiceException {
		logger.debug("[ begin ] HomeServiceImpl.queryModuleItems(), moduleId = " + moduleId);
		
		GlyModuleItem param = new GlyModuleItem();
		param.setModuleId(moduleId);
		param.setIsValid(GlyModuleItem.IS_VALID_Y);
		List<GlyModuleItem> moduleItems = glyModuleItemMapper.selectSelective(param);
		logger.debug("[ run ] HomeServiceImpl.queryModuleItems(), moduleItems = " + moduleItems);
		
		logger.debug("[ end ] HomeServiceImpl.queryModuleItems().");
		return moduleItems;
	}

}
