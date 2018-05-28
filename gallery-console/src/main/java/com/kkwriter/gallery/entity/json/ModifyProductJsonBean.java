package com.kkwriter.gallery.entity.json;

import com.kkwriter.gallery.entity.product.GlyProductPicture;

import java.util.List;

/**
 * Created by lisha on 2018/5/4 19:28.
 *
 * @author lisha
 */
public class ModifyProductJsonBean {

    private Integer productId;
    private String productName;
    private String productIntro;
    private String productDetail;
    private String initialPrice;
    private String discount;
    private String inventoryNumber;
    private String bookNumber;
    private String productOrder;
    private String isValid;
    private List<String> productAttrs;
    private List<String> productTypes;
    private List<PictureInfo> mainPictures;
    private List<PictureInfo> detailPictures;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<PictureInfo> getDetailPictures() {
        return detailPictures;
    }

    public void setDetailPictures(List<PictureInfo> detailPictures) {
        this.detailPictures = detailPictures;
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

    public String getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(String initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(String productOrder) {
        this.productOrder = productOrder;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public List<String> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(List<String> productAttrs) {
        this.productAttrs = productAttrs;
    }

    public List<String> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<String> productTypes) {
        this.productTypes = productTypes;
    }

    public List<PictureInfo> getMainPictures() {
        return mainPictures;
    }

    public void setMainPictures(List<PictureInfo> mainPictures) {
        this.mainPictures = mainPictures;
    }

    @Override
    public String toString() {
        return "ModifyProductJsonBean{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productIntro='" + productIntro + '\'' +
                ", productDetail='" + productDetail + '\'' +
                ", initialPrice='" + initialPrice + '\'' +
                ", discount='" + discount + '\'' +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", bookNumber='" + bookNumber + '\'' +
                ", productOrder='" + productOrder + '\'' +
                ", isValid='" + isValid + '\'' +
                ", productAttrs=" + productAttrs +
                ", productTypes=" + productTypes +
                ", mainPictures=" + mainPictures +
                ", detailPictures=" + detailPictures +
                '}';
    }

    public static class PictureInfo {
        private int position;
        private String operateType;
        private GlyProductPicture oldPicture;
        private String newPicture;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getOperateType() {
            return operateType;
        }

        public void setOperateType(String operateType) {
            this.operateType = operateType;
        }

        public GlyProductPicture getOldPicture() {
            return oldPicture;
        }

        public void setOldPicture(GlyProductPicture oldPicture) {
            this.oldPicture = oldPicture;
        }

        public String getNewPicture() {
            return newPicture;
        }

        public void setNewPicture(String newPicture) {
            this.newPicture = newPicture;
        }

        @Override
        public String toString() {
            return "PictureInfo{" +
                    "position=" + position +
                    ", operateType='" + operateType + '\'' +
                    ", oldPicture=" + oldPicture +
                    ", newPicture='" + newPicture + '\'' +
                    '}';
        }
    }
}
