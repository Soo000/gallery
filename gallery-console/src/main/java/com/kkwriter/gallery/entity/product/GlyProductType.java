package com.kkwriter.gallery.entity.product;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.kkwriter.gallery.entity.BaseEntity;

@Entity
public class GlyProductType extends BaseEntity {
	private static final long serialVersionUID = -1881080964481322664L;
	
	@Id
	private Integer productTypeId;
	
	private Integer parentProductTypeId;
	
	private String productTypeName;
	
	private String productTypeValue;
	
	private Float productTypeOrder;
	
	private Integer isValid;

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getParentProductTypeId() {
		return parentProductTypeId;
	}

	public void setParentProductTypeId(Integer parentProductTypeId) {
		this.parentProductTypeId = parentProductTypeId;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductTypeValue() {
		return productTypeValue;
	}

	public void setProductTypeValue(String productTypeValue) {
		this.productTypeValue = productTypeValue;
	}

	public Float getProductTypeOrder() {
		return productTypeOrder;
	}

	public void setProductTypeOrder(Float productTypeOrder) {
		this.productTypeOrder = productTypeOrder;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}
