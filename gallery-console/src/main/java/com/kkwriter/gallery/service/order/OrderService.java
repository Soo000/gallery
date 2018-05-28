package com.kkwriter.gallery.service.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kkwriter.gallery.entity.order.GlyOrder;

/**
 * @author lisha
 */
public interface OrderService {
	/**
	 * 分页查询所有订单
	 * @param pageable 分页参数
	 * @return 查询到的一页订单
	 */
	Page<GlyOrder> getAllOrder(Pageable pageable);

	/**
	 * 通过状态查询订单
	 * @param orderStatus 订单状态
	 * @param pageable 分页参数
	 * @return 查询到的一页订单数据
	 */
	Page<GlyOrder> getOrderByStatus(Integer orderStatus, Pageable pageable);

	/**
	 * 编辑订单价格
	 * @param orderCode 订单编码
	 * @param price 新价格
	 */
	void editOrderPrice(String orderCode, Float price);
	
}
