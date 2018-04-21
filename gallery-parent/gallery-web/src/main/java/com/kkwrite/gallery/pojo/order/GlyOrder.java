package com.kkwrite.gallery.pojo.order;

import java.util.Date;

public class GlyOrder {
    private String orderCode;

    private Integer userId;

    private Integer addressId;

    private Integer orderType;

    private Integer orderStatus;

    private Date validTime;

    private Date invalidTime;
    
    private float realPayment;
    
    private int productNum;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public float getRealPayment() {
		return realPayment;
	}

	public void setRealPayment(float realPayment) {
		this.realPayment = realPayment;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
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

    public String getext1() {
        return ext1;
    }

    public void setext1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getext2() {
        return ext2;
    }

    public void setext2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getext3() {
        return ext3;
    }

    public void setext3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getext4() {
        return ext4;
    }

    public void setext4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }
}