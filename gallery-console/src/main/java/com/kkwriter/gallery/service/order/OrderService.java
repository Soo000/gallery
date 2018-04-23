package com.kkwriter.gallery.service.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kkwriter.gallery.entity.order.GlyOrder;

public interface OrderService {
	
	Page<GlyOrder> getAllOrder(Pageable pageable);
	
	Page<GlyOrder> getOrderByStatus(Integer orderStatus, Pageable pageable);
	
	void editOrderPrice(String orderCode, Float price);
	
}
