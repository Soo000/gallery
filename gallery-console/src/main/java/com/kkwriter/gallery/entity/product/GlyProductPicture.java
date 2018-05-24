package com.kkwriter.gallery.entity.product;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.kkwriter.gallery.entity.BaseEntity;

/**
 * @author lisha
 */
@Entity(name = "gly_product_picture")
public class GlyProductPicture extends BaseEntity {
	private static final long serialVersionUID = -3663355653844835580L;
	
	@Id
	private Integer productPictureCode;
	
	private Integer productId;
	
	private String productPictureName;
	
	private String productPictureFileName;
	
	private String productPictureWidth;
	
	private String productPictureHeight;
	
	private Integer productPictureType;
	
	private Float productPictureOrder;
	
	private Integer isValid;

	public Integer getProductPictureCode() {
		return productPictureCode;
	}

	public void setProductPictureCode(Integer productPictureCode) {
		this.productPictureCode = productPictureCode;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductPictureName() {
		return productPictureName;
	}

	public void setProductPictureName(String productPictureName) {
		this.productPictureName = productPictureName;
	}

	public String getProductPictureFileName() {
		return productPictureFileName;
	}

	public void setProductPictureFileName(String productPictureFileName) {
		this.productPictureFileName = productPictureFileName;
	}

	public String getProductPictureWidth() {
		return productPictureWidth;
	}

	public void setProductPictureWidth(String productPictureWidth) {
		this.productPictureWidth = productPictureWidth;
	}

	public String getProductPictureHeight() {
		return productPictureHeight;
	}

	public void setProductPictureHeight(String productPictureHeight) {
		this.productPictureHeight = productPictureHeight;
	}

	public Integer getProductPictureType() {
		return productPictureType;
	}

	public void setProductPictureType(Integer productPictureType) {
		this.productPictureType = productPictureType;
	}

	public Float getProductPictureOrder() {
		return productPictureOrder;
	}

	public void setProductPictureOrder(Float productPictureOrder) {
		this.productPictureOrder = productPictureOrder;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "GlyProductPicture{" +
				"productPictureCode=" + productPictureCode +
				", productId=" + productId +
				", productPictureName='" + productPictureName + '\'' +
				", productPictureFileName='" + productPictureFileName + '\'' +
				", productPictureWidth='" + productPictureWidth + '\'' +
				", productPictureHeight='" + productPictureHeight + '\'' +
				", productPictureType=" + productPictureType +
				", productPictureOrder=" + productPictureOrder +
				", isValid=" + isValid +
				'}';
	}
}
