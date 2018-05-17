package com.kkwrite.gallery.service.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.module.GlyModuleItemMapper;
import com.kkwrite.gallery.mapper.module.GlyModuleMapper;
import com.kkwrite.gallery.pojo.module.GlyModuleDO;
import com.kkwrite.gallery.pojo.module.GlyModuleItemDO;

@Service("homeService")
public class HomeServiceImpl implements HomeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyModuleMapper glyModuleMapper;
	
	@Autowired
	private GlyModuleItemMapper glyModuleItemMapper;
	
	@Override
	public List<ModuleBO> queryModuleByPModuleId(ModuleQuery moduleQuery) throws ServiceException {
		List<GlyModuleDO> glyModuleDOs = glyModuleMapper.selectByParentId(moduleQuery);
		if (glyModuleDOs == null || glyModuleDOs.isEmpty()) {
			return null;
		}
		
		List<ModuleBO> moduleBOs = new ArrayList<ModuleBO>(glyModuleDOs.size());
		for (GlyModuleDO glyModule: glyModuleDOs) {
			ModuleBO moduleBO = new ModuleBO();
			moduleBO.setModuleId(glyModule.getModuleId());
			moduleBO.setPModuleId(glyModule.getPModuleId());
			moduleBO.setModuleName(glyModule.getModuleName());
			moduleBO.setModuleTitle(glyModule.getModuleTitle());
			moduleBO.setModuleTemplate(glyModule.getModuleTemplate());
			moduleBO.setModuleDesc(glyModule.getModuleDesc());
			moduleBO.setModuleOrder(glyModule.getModuleOrder());
			moduleBO.setModuleTemplate(glyModule.getModuleTemplate());
			moduleBOs.add(moduleBO);
		}
		return moduleBOs;
	}
	
	@Override
	public ModuleItemBO queryModuleItem(ModuleItemQuery moduleItemQuery) throws ServiceException {
		List<ModuleItemBO> moduleItemBOs = queryModuleItems(moduleItemQuery);
		if (moduleItemBOs == null || moduleItemBOs.size() <= 0) return null;
		
		if (moduleItemBOs.size() > 1) {
			throw new ServiceException("查询模框项数据异常！");
		}
		return moduleItemBOs.get(0);
	}

	@Override
	public List<ModuleItemBO> queryModuleItems(ModuleItemQuery moduleItemQuery) throws ServiceException {
		List<GlyModuleItemDO> glyModuleItemDOs = glyModuleItemMapper.selectSelective(moduleItemQuery);
		if (glyModuleItemDOs == null || glyModuleItemDOs.isEmpty()) {
			return null;
		}
		
		List<ModuleItemBO> moduleItemBOs = new ArrayList<ModuleItemBO>(glyModuleItemDOs.size());
		for (GlyModuleItemDO glyModuleItemDO: glyModuleItemDOs) {
			ModuleItemBO moduleItemBO = new ModuleItemBO();
			moduleItemBO.setModuleItemId(glyModuleItemDO.getModuleItemId());
			moduleItemBO.setModuleId(glyModuleItemDO.getModuleId());
			moduleItemBO.setModuleItemName(glyModuleItemDO.getModuleItemName());
			moduleItemBO.setModuleItemImage(glyModuleItemDO.getModuleItemImage());
			moduleItemBO.setModuleItemType(glyModuleItemDO.getModuleItemType());
			moduleItemBO.setModuleItemOrder(glyModuleItemDO.getModuleItemOrder());
			moduleItemBO.setProductId(glyModuleItemDO.getProductId());
			
			moduleItemBOs.add(moduleItemBO);
		}
		return moduleItemBOs;
	}

}
