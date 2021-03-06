package com.kkwriter.gallery.entity.order;

import com.kkwriter.gallery.entity.BaseEntity;
import com.kkwriter.gallery.entity.address.GlyAddress;
import com.kkwriter.gallery.entity.user.GlyUser;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 订单实体类
 *
 * @author lisha
 */
@Entity
public class GlyOrder extends BaseEntity implements Persistable<String> {
    private static final long serialVersionUID = -2957338540890775377L;
    @Id
    private String orderCode;
    private Integer userId;
    private Integer addressId;
    private Integer orderType;
    private Integer orderStatus;
    private Date validTime;
    private Date invalidTime;
    private Float realPayment;
    private Integer productNum;
    private Integer isValid;
    @Transient
    private GlyUser glyUser;
    @Transient
    private GlyAddress glyAddress;

    public GlyAddress getGlyAddress() {
        return glyAddress;
    }

    public void setGlyAddress(GlyAddress glyAddress) {
        this.glyAddress = glyAddress;
    }

    public GlyUser getGlyUser() {
        return glyUser;
    }

    public void setGlyUser(GlyUser glyUser) {
        this.glyUser = glyUser;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Float getRealPayment() {
        return realPayment;
    }

    public void setRealPayment(Float realPayment) {
        this.realPayment = realPayment;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String getId() {
        return this.orderCode;
    }

    @Override
    public boolean isNew() {
        return this.orderCode == null;
    }
}
