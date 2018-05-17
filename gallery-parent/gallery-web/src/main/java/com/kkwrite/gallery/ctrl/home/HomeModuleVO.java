package com.kkwrite.gallery.ctrl.home;

import java.util.List;

import com.kkwrite.gallery.service.home.ModuleBO;

/**
 * GlyModulePO 类
 * @author Ke.Wang
 * @date 2018年5月16日 下午4:59:26
 */
public class HomeModuleVO extends ModuleBO {

	private List<HomeModuleItemVO> homeModuleItemVOs;

	public List<HomeModuleItemVO> getHomeModuleItemVOs() {
		return homeModuleItemVOs;
	}

	public void setHomeModuleItemVOs(List<HomeModuleItemVO> homeModuleItemVOs) {
		this.homeModuleItemVOs = homeModuleItemVOs;
	}
	
}
