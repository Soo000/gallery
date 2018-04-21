package com.kkwrite.gallery.pojo.product;

public class GlyRProductPropCarKey {
    private Integer propId;

    private Integer productId;

    private String cartCode;

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
        this.cartCode = cartCode == null ? null : cartCode.trim();
    }
}