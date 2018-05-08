package com.kkwriter.gallery.service.order;

import com.kkwriter.gallery.entity.order.GlyOrder;
import com.kkwriter.gallery.exception.GalleyConsoleException;
import com.kkwriter.gallery.repository.address.GlyAddressRepository;
import com.kkwriter.gallery.repository.order.GlyOrderRepository;
import com.kkwriter.gallery.repository.user.GlyUserRepository;
import com.kkwriter.gallery.result.ReturnEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * 订单服务实现类
 * @author lisha
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Resource(name = "glyOrderRepository")
	private GlyOrderRepository glyOrderRepository;
	@Resource(name = "glyUserRepository")
	private GlyUserRepository glyUserRepository;
	@Resource(name = "glyAddressRepository")
	private GlyAddressRepository glyAddressRepository;

	@Override
	public Page<GlyOrder> getAllOrder(Pageable pageable) {
		Page<GlyOrder> orders = glyOrderRepository.findAll(pageable);
		if (!orders.hasContent()) {
			throw new GalleyConsoleException(ReturnEnum.DO_NOT_HAVE_DATA);
		}
		orders.forEach((order) -> {
			glyUserRepository.findById(order.getUserId()).ifPresent(order::setGlyUser);
			glyAddressRepository.findById(order.getAddressId()).ifPresent(order::setGlyAddress);
		});
		return orders;
	}

	@Override
	public Page<GlyOrder> getOrderByStatus(Integer orderStatus, Pageable pageable) {
		Page<GlyOrder> orders = glyOrderRepository.findByOrderStatus(orderStatus, pageable);
		if (!orders.hasContent()) {
			throw new GalleyConsoleException(ReturnEnum.DO_NOT_HAVE_DATA);
		}
		orders.forEach((order) -> {
			glyUserRepository.findById(order.getUserId()).ifPresent(order::setGlyUser);
			glyAddressRepository.findById(order.getAddressId()).ifPresent(order::setGlyAddress);
		});
		return orders;
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void editOrderPrice(String orderCode, Float price) {
		GlyOrder order = glyOrderRepository.findById(orderCode).orElse(null);
		if (order == null) {
			throw new GalleyConsoleException(ReturnEnum.UPDATE_FAILED);
		}
		order.setRealPayment(price);
		order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		glyOrderRepository.save(order);
	}

}
