package com.kkwriter.gallery.entity.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "gly_r_product_props")
public class GlyRProductProps implements Serializable {
	private static final long serialVersionUID = 2506820335828094102L;
	
	@Id
	private Integer id;
	
	private Integer propId;

	private Integer productId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
