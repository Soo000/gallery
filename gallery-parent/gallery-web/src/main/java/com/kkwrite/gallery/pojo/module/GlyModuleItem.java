package com.kkwrite.gallery.pojo.module;

import java.util.Date;

public class GlyModuleItem extends ModuleItem {
    private Integer moduleItemId;

    private Integer moduleId;

    private Integer productId;

    private String moduleItemName;

    private Integer moduleItemType;

    private String moduleItemImage;

    private Float moduleItemOrder;

    private Integer isValid;

    private Date creationTime;

    private String creator;

    private Date updateTime;

    private String updator;

    private String ext0;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    public Integer getModuleItemId() {
        return moduleItemId;
    }

    public void setModuleItemId(Integer moduleItemId) {
        this.moduleItemId = moduleItemId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getModuleItemName() {
        return moduleItemName;
    }

    public void setModuleItemName(String moduleItemName) {
        this.moduleItemName = moduleItemName == null ? null : moduleItemName.trim();
    }

    public Integer getModuleItemType() {
        return moduleItemType;
    }

    public void setModuleItemType(Integer moduleItemType) {
        this.moduleItemType = moduleItemType;
    }

    public String getModuleItemImage() {
        return moduleItemImage;
    }

    public void setModuleItemImage(String moduleItemImage) {
        this.moduleItemImage = moduleItemImage == null ? null : moduleItemImage.trim();
    }

    public Float getModuleItemOrder() {
        return moduleItemOrder;
    }

    public void setModuleItemOrder(Float moduleItemOrder) {
        this.moduleItemOrder = moduleItemOrder;
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

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }
}