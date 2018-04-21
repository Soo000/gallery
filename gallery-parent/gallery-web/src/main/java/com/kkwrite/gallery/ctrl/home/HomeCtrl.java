package com.kkwrite.gallery.ctrl.home;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.common.ModuleDict;
import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.module.GlyModuleItem;
import com.kkwrite.gallery.service.home.HomeService;

@Controller
@RequestMapping("/homectrl")
public class HomeCtrl extends BaseCtrl {

	private Logger logger = Logger.getLogger(HomeCtrl.class);
	
	@Autowired
	private HomeService homeService;

	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] HomeCtrl.pageCtrl().");
		
		ModelAndView modelAndView = new ModelAndView("/home/home");
		
		// 查询首页轮播图模块
		try {
			List<GlyModuleItem> carouselItems = qryCarousel(ModuleDict.ModuleIds.MODULE_HOME_CAROUSEL);
			modelAndView.addObject("carouselItems", carouselItems);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询首页轮播图出错");
			e.printStackTrace();
		}
		
		// 查询上新区模块
		try {
			GlyModuleItem newAreaModuleItem = qryArea(ModuleDict.ModuleIds.MODULE_HOME_NEWAREA);
			modelAndView.addObject("newAreaModuleItem", newAreaModuleItem);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询上新区模块出错");
			e.printStackTrace();
		}
		
		// 查询折扣区模块
		try {
			GlyModuleItem discountModuleItem = qryArea(ModuleDict.ModuleIds.MODULE_HOME_DISCOUNT_AREA);
			modelAndView.addObject("discountModuleItem", discountModuleItem);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询折扣区模块出错");
			e.printStackTrace();
		}
		
		// 查询生活好物模块
		try {
			GlyModuleItem goodsModuleItem = qryArea(ModuleDict.ModuleIds.MODULE_HOME_GOODS_AREA);
			modelAndView.addObject("goodsModuleItem", goodsModuleItem);
		} catch (ServiceException e) {
			logger.error("[ run ] HomeCtrl.pageCtrl(), 查询生活好物模块出错");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] HomeCtrl.pageCtrl().");
		return modelAndView;
	}
	
	/**
	 * 查询首页轮播图
	 * @param moduleId
	 * @return
	 */
	private List<GlyModuleItem> qryCarousel(int moduleId) throws ServiceException  {
		List<GlyModuleItem> carouselItems = homeService.queryModuleItems(moduleId);
		return carouselItems;
	}
	
	private GlyModuleItem qryArea(int moduleId) throws ServiceException {
		return homeService.queryModuleItem(moduleId);
	}

}
