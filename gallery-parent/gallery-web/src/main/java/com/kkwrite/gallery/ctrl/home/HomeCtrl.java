package com.kkwrite.gallery.ctrl.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.activity.ActivityDTO;
import com.kkwrite.gallery.pojo.activity.ActivityQuery;
import com.kkwrite.gallery.service.activity.ActivityService;
import com.kkwrite.gallery.service.home.HomeService;
import com.kkwrite.gallery.service.home.ModuleBO;
import com.kkwrite.gallery.service.home.ModuleItemBO;
import com.kkwrite.gallery.service.home.ModuleItemQuery;
import com.kkwrite.gallery.service.home.ModuleQuery;

@Controller
@RequestMapping("/homectrl")
public class HomeCtrl extends BaseCtrl {

	private Logger logger = Logger.getLogger(HomeCtrl.class);
	
	@Autowired
	private HomeService homeService;
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] HomeCtrl.pageCtrl().");
		ModelAndView modelAndView = new ModelAndView("/home/home");
		
		// 查询首页模块
		List<ModuleBO> modules = qryHomeModule(0); // TODO 参数写死了;
		if (modules == null || modules.isEmpty()) {
			modelAndView.setViewName("/error"); // TODO 跳转到错误页面；
		}
		
		// 查询首页每个模块的模块项
		List<HomeModuleVO> homeModuleVOs = qryModuleItem(modules);
		modelAndView.addObject("homeModuleVOs", homeModuleVOs);
		
		logger.debug("[ end ] HomeCtrl.pageCtrl().");
		return modelAndView;
	}
	
	/**
	 * 查询首页模块
	 * @param pModuleId
	 * @return
	 */
	private List<ModuleBO> qryHomeModule(int pModuleId) {
		ModuleQuery moduleQuery = new ModuleQuery();
		moduleQuery.setPModuleId(pModuleId);
		moduleQuery.setValid(BasePojo.IS_VALID_Y);
		return homeService.queryModuleByPModuleId(moduleQuery); 
	}
	
	/**
	 * 查询每一个模块下的模块项
	 * @param modules
	 * @return
	 */
	private List<HomeModuleVO> qryModuleItem(List<ModuleBO> moduleBOs) {
		List<HomeModuleVO> homeModuleVOs = new ArrayList<HomeModuleVO>();
		for (ModuleBO moduleBO: moduleBOs) {
			HomeModuleVO homeModuleVO = new HomeModuleVO();
			homeModuleVO.setModuleId(moduleBO.getModuleId());
			homeModuleVO.setModuleName(moduleBO.getModuleName());
			homeModuleVO.setModuleTitle(moduleBO.getModuleTitle());
			homeModuleVO.setModuleTemplate(moduleBO.getModuleTemplate());
			homeModuleVO.setModuleOrder(moduleBO.getModuleOrder());
			
			// 查询模块下的模块项
			ModuleItemQuery moduleItemQuery = new ModuleItemQuery();
			moduleItemQuery.setModuleId(moduleBO.getModuleId());
			moduleItemQuery.setValid(BasePojo.IS_VALID_Y);
			List<ModuleItemBO> moduleItemBOs = homeService.queryModuleItems(moduleItemQuery);
			if (moduleItemBOs == null || moduleItemBOs.isEmpty()) {
				continue;
			}
			
			List<HomeModuleItemVO> homeModuleItemVOs = new ArrayList<HomeModuleItemVO>();
			for (ModuleItemBO moduleItemBO: moduleItemBOs) {
				HomeModuleItemVO homeModuleItemVO = new HomeModuleItemVO();
				homeModuleItemVO.setModuleItemId(moduleItemBO.getModuleId());
				homeModuleItemVO.setModuleItemName(moduleItemBO.getModuleItemName());
				homeModuleItemVO.setModuleItemImage(moduleItemBO.getModuleItemImage());
				homeModuleItemVO.setModuleItemOrder(moduleItemBO.getModuleItemOrder());
				homeModuleItemVO.setModuleItemType(moduleItemBO.getModuleItemType());
				homeModuleItemVO.setProductId(moduleItemBO.getProductId());
				if (moduleItemBO.getModuleItemType() == BasePojo.ModuleItem.MODULE_ITEM_TYPE_ACTIVATE) {
					ActivityQuery activityQuery = new ActivityQuery();
					activityQuery.setActivityId(moduleItemBO.getProductId());
					activityQuery.setValid(BasePojo.IS_VALID_Y);
					ActivityDTO activityDTO = activityService.getActivityById(activityQuery);
					if (activityDTO != null) {
						homeModuleItemVO.setActivityName(activityDTO.getActivityName());
						homeModuleItemVO.setActivityUrl(activityDTO.getActivityUrl());
						homeModuleItemVO.setActivityPictureFileName(activityDTO.getActivityPictureFileName());
					}
				}
				homeModuleItemVOs.add(homeModuleItemVO);
			}
			homeModuleVO.setHomeModuleItemVOs(homeModuleItemVOs);
			
			homeModuleVOs.add(homeModuleVO);
		}
		return homeModuleVOs;
	}

}
