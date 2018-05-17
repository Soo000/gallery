package com.kkwrite.gallery.service.home;

/**
 * 根据父模块ID查询模块查询参数
 * @author Ke.Wang
 * @date 2018年5月17日 上午10:22:11
 */
public class ModuleItemQuery {

	private Integer moduleId;
	private Integer valid;
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	
}
