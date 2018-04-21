package com.kkwrite.gallery.pojo.reservation;

import java.util.Date;

public class GlyReservation {
    private String reservationCode;

    private String orderCode;

    private String reservationName;

    private Integer reservationType;

    private String reservationUsername;

    private String reservationPhoneNum;
    
    private String reservationArea;

    private String reservationAddress;

    private String reservationDesc;

    private Date reservationTime;

    private Integer reservationStatus;

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

    public String getReservationArea() {
		return reservationArea;
	}

	public void setReservationArea(String reservationArea) {
		this.reservationArea = reservationArea;
	}

	public String getReservationId() {
        return reservationCode;
    }

    public void setReservationId(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName == null ? null : reservationName.trim();
    }

    public Integer getReservationType() {
        return reservationType;
    }

    public void setReservationType(Integer reservationType) {
        this.reservationType = reservationType;
    }

    public String getReservationUsername() {
        return reservationUsername;
    }

    public void setReservationUsername(String reservationUsername) {
        this.reservationUsername = reservationUsername == null ? null : reservationUsername.trim();
    }

    public String getReservationPhoneNum() {
        return reservationPhoneNum;
    }

    public void setReservationPhoneNum(String reservationPhoneNum) {
        this.reservationPhoneNum = reservationPhoneNum == null ? null : reservationPhoneNum.trim();
    }

    public String getReservationAddress() {
        return reservationAddress;
    }

    public void setReservationAddress(String reservationAddress) {
        this.reservationAddress = reservationAddress == null ? null : reservationAddress.trim();
    }

    public String getReservationDesc() {
        return reservationDesc;
    }

    public void setReservationDesc(String reservationDesc) {
        this.reservationDesc = reservationDesc == null ? null : reservationDesc.trim();
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Integer getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
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