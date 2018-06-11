package com.kkwriter.gallery.entity.product;

import com.kkwriter.gallery.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author lisha
 */
@Entity
public class GlyProductType extends BaseEntity {
	private static final long serialVersionUID = -1881080964481322664L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_type_id")
	private Integer productTypeId;

	@Column(name = "parent_product_type_id")
	private Integer parentProductTypeId;

	@Column(name = "product_type_name", length = 30)
	private String productTypeName;

	@Column(name = "product_type_value", length = 30)
	private String productTypeValue;

	@Column(name = "product_type_order")
	private Float productTypeOrder;

	@Column(name = "is_valid")
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
