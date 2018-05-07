package com.kkwrite.gallery.service.reservation;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.reservation.GlyReservation;

public interface ReservationService {

	int queryReservationCount() throws ServiceException;
	
	boolean reservation(GlyOrder order, GlyReservation reservation) throws ServiceException;
	
}