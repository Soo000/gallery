package com.kkwrite.gallery.service.home;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;

public interface HomeService {

	public List<ModuleBO> queryModuleByPModuleId(ModuleQuery moduleQuery) throws ServiceException;
	
	public ModuleItemBO queryModuleItem(ModuleItemQuery moduleItemQuery) throws ServiceException;
	
	public List<ModuleItemBO> queryModuleItems(ModuleItemQuery moduleItemQuery) throws ServiceException;
	
}