package com.kkwriter.gallery.entity.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kkwriter.gallery.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author lisha
 */
@Entity
public class GlyProduct extends BaseEntity {

    private static final long serialVersionUID = 4129983160369429913L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productIntro;
    private String productDetail;
    private Float initialPrice;
    private Float realPrice;
    private Float discount;
    private Integer inventoryNumber;
    private Integer saleNumber;
    private Integer residueNumber;
    private Integer bookNumber;
    private Float productOrder;
    private Integer isValid;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Float getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Float initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Float realPrice) {
        this.realPrice = realPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Integer getResidueNumber() {
        return residueNumber;
    }

    public void setResidueNumber(Integer residueNumber) {
        this.residueNumber = residueNumber;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Float getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(Float productOrder) {
        this.productOrder = productOrder;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "GlyProduct{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productIntro='" + productIntro + '\'' +
                ", productDetail='" + productDetail + '\'' +
                ", initialPrice=" + initialPrice +
                ", realPrice=" + realPrice +
                ", discount=" + discount +
                ", inventoryNumber=" + inventoryNumber +
                ", saleNumber=" + saleNumber +
                ", residueNumber=" + residueNumber +
                ", bookNumber=" + bookNumber +
                ", productOrder=" + productOrder +
                ", isValid=" + isValid +
                '}' + " | " + super.toString();
    }

    public void updateMe(GlyProduct that) throws IllegalAccessException {
        GlyProduct obj = this;
        Field[] field = that.getClass().getDeclaredFields();
        Field[] superField = that.getClass().getSuperclass().getDeclaredFields();
        Field[] result = Arrays.copyOf(field, field.length + superField.length);
        System.arraycopy(superField, 0, result, field.length, superField.length);
        for (Field f : result) {
            if ("serialVersionUID".equals(f.getName())) {
                continue;
            }
            f.setAccessible(true);
            Object value = f.get(that);
            if (value != null) {
                f.set(obj, value);
            }
        }
    }

}
