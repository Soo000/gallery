package com.kkwrite.gallery.service.home;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.module.GlyModuleItem;

public interface HomeService {

	public GlyModuleItem queryModuleItem(int moduleId) throws ServiceException;
	
	public List<GlyModuleItem> queryModuleItems(int moduleId) throws ServiceException;
	
}