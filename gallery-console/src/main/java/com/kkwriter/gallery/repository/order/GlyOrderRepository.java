package com.kkwriter.gallery.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kkwriter.gallery.entity.order.GlyOrder;

/**
 * @author lisha
 */
public interface GlyOrderRepository extends PagingAndSortingRepository<GlyOrder, String> {

	/**
	 * 根据订单状态查询订单
	 * @param orderStatus 订单状态
	 * @param pageable 分页参数
	 * @return 查询到的一页数据
	 */
	Page<GlyOrder> findByOrderStatus(Integer orderStatus, Pageable pageable);
	
}
