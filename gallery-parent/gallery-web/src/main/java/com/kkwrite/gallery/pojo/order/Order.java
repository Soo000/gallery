package com.kkwrite.gallery.pojo.order;

import java.util.List;

import com.kkwrite.gallery.pojo.product.GlyProductInstance;

public class Order extends GlyOrder {

	private List<GlyProductInstance> productInstance;

	public List<GlyProductInstance> getProductInstance() {
		return productInstance;
	}

	public void setProductInstance(List<GlyProductInstance> productInstance) {
		this.productInstance = productInstance;
	}
	
}
