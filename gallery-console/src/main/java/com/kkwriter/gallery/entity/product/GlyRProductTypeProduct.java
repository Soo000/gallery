package com.kkwriter.gallery.entity.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lisha
 */
@Entity(name = "gly_r_product_type_product")
public class GlyRProductTypeProduct implements Serializable {
	private static final long serialVersionUID = -7397675005270694600L;
	
	@Id
	private Integer id;
	
	private Integer productId;
	
	private Integer productTypeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	
}
