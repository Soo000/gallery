package com.kkwrite.gallery.ctrl.home;

import java.util.Date;

import com.kkwrite.gallery.service.home.ModuleItemBO;

/**
 * 首页模块项 VO
 * @author Ke.Wang
 * @date 2018年5月16日 下午4:16:51
 */
public class HomeModuleItemVO extends ModuleItemBO {

	private String activityUrl;
	private String activityPictureFileName;
	private String activityName;
	private Date activityBeginTime;
	private Date activityEndTime;
	
	public String getActivityUrl() {
		return activityUrl;
	}
	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}
	public String getActivityPictureFileName() {
		return activityPictureFileName;
	}
	public void setActivityPictureFileName(String activityPictureFileName) {
		this.activityPictureFileName = activityPictureFileName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getActivityBeginTime() {
		return activityBeginTime;
	}
	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}
	public Date getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	@Override
	public String toString() {
		return "HomeModuleItemVO [activityUrl=" + activityUrl + ", activityPictureFileName=" + activityPictureFileName
				+ ", activityName=" + activityName + ", activityBeginTime=" + activityBeginTime + ", activityEndTime="
				+ activityEndTime + "]";
	}
}
