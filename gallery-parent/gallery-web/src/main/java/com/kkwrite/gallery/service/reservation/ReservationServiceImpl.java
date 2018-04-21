package com.kkwrite.gallery.service.reservation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.order.GlyOrderMapper;
import com.kkwrite.gallery.mapper.reservation.GlyReservationMapper;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.reservation.GlyReservation;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyOrderMapper glyOrderMapper;
	@Autowired
	private GlyReservationMapper glyReservationMapper;

	@Override
	public int queryReservationCount() throws ServiceException {
		logger.debug("[ begin ] GlyReservation.queryReservationCount()");
		int count = glyReservationMapper.selectReservationCount();
		logger.debug("[ end ] GlyReservation.queryReservationCount()");
		return count;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=ServiceException.class)
	public boolean reservation(GlyOrder order, GlyReservation reservation) throws ServiceException {
		logger.debug("[ begin ] GlyReservation.reservation()");
		// 插入订单数据(预约修改为不插入订单表了)
		int result;
		/*try {
			result = glyOrderMapper.insertSelective(order);
			if (result != 1) {
				throw new ServiceException("[ run ] GlyReservation.reservation(), 插入订单数据出错！");
			}
		} catch (Exception e) {
			logger.error("[ run ] GlyReservation.reservation(), 插入订单数据出错！", e);
			throw new ServiceException("[ run ] GlyReservation.reservation(), 插入订单数据出错，" + e.getMessage());
		}*/
		
		// 插入预约数据
		try {
			result = glyReservationMapper.insertSelective(reservation);
			if (result != 1) {
				throw new ServiceException("[ run ] GlyReservation.reservation(), 插入预约数据出错！");
			}
		} catch (Exception e) {
			logger.error("[ run ] GlyReservation.reservation(), 插入预约数据出错！", e);
			throw new ServiceException("[ run ] GlyReservation.reservation(), 插入预约数据出错，" + e.getMessage());
		}
		logger.debug("[ end ] GlyReservation.reservation()");
		return true;
	}

}
