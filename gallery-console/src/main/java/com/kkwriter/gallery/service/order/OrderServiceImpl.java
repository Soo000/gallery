package com.kkwriter.gallery.service.order;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkwriter.gallery.entity.order.GlyOrder;
import com.kkwriter.gallery.exception.GalleyConsoleException;
import com.kkwriter.gallery.repository.order.GlyOrderRepository;
import com.kkwriter.gallery.result.ReturnEnum;

@Service
public class OrderServiceImpl implements OrderService {
	@Resource(name = "glyOrderRepository")
	private GlyOrderRepository glyOrderRepository;

	@Override
	public Page<GlyOrder> getAllOrder(Pageable pageable) {
		Page<GlyOrder> orders = glyOrderRepository.findAll(pageable);
		if (!orders.hasContent()) {
			throw new GalleyConsoleException(ReturnEnum.DO_NOT_HAVE_DATA);
		}
		return orders;
	}

	@Override
	public Page<GlyOrder> getOrderByStatus(Integer orderStatus, Pageable pageable) {
		Page<GlyOrder> orders = glyOrderRepository.findByOrderStatus(orderStatus, pageable);
		if (!orders.hasContent()) {
			throw new GalleyConsoleException(ReturnEnum.DO_NOT_HAVE_DATA);
		}
		return orders;
	}

	@Override
	@Transactional
	public void editOrderPrice(String orderCode, Float price) {
		GlyOrder order = glyOrderRepository.findById(orderCode).get();
		order.setRealPayment(price);
		order.setUpdateTime(new Timestamp(new Date().getTime()));
		glyOrderRepository.save(order);
	}

}
