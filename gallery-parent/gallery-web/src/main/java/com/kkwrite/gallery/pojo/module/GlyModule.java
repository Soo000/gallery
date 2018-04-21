package com.kkwrite.gallery.pojo.module;

import java.util.Date;

public class GlyModule {
    private Integer moduleId;

    private String moduleName;

    private Integer pModuleId;

    private String pModuleName;

    private String moduleDesc;

    private Integer isValid;

    private Date creationTime;

    private String creator;

    private Date updateTime;

    private String updator;

    private String ext0;

    private String 扩展字段1;

    private String 扩展字段2;

    private String 扩展字段3;

    private String 扩展字段4;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Integer getpModuleId() {
        return pModuleId;
    }

    public void setpModuleId(Integer pModuleId) {
        this.pModuleId = pModuleId;
    }

    public String getpModuleName() {
        return pModuleName;
    }

    public void setpModuleName(String pModuleName) {
        this.pModuleName = pModuleName == null ? null : pModuleName.trim();
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc == null ? null : moduleDesc.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public String getExt0() {
        return ext0;
    }

    public void setExt0(String ext0) {
        this.ext0 = ext0 == null ? null : ext0.trim();
    }

    public String get扩展字段1() {
        return 扩展字段1;
    }

    public void set扩展字段1(String 扩展字段1) {
        this.扩展字段1 = 扩展字段1 == null ? null : 扩展字段1.trim();
    }

    public String get扩展字段2() {
        return 扩展字段2;
    }

    public void set扩展字段2(String 扩展字段2) {
        this.扩展字段2 = 扩展字段2 == null ? null : 扩展字段2.trim();
    }

    public String get扩展字段3() {
        return 扩展字段3;
    }

    public void set扩展字段3(String 扩展字段3) {
        this.扩展字段3 = 扩展字段3 == null ? null : 扩展字段3.trim();
    }

    public String get扩展字段4() {
        return 扩展字段4;
    }

    public void set扩展字段4(String 扩展字段4) {
        this.扩展字段4 = 扩展字段4 == null ? null : 扩展字段4.trim();
    }
}