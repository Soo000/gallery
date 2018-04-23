package com.kkwriter.gallery.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kkwriter.gallery.entity.order.GlyOrder;

public interface GlyOrderRepository extends PagingAndSortingRepository<GlyOrder, String> {
	
	Page<GlyOrder> findByOrderStatus(Integer orderStatus, Pageable pageable);
	
}
