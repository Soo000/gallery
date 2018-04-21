package com.kkwrite.gallery.pojo.order;

import java.util.List;

import com.kkwrite.gallery.pojo.reservation.GlyReservation;

public class OrderReservations extends OrderItems {

	private List<GlyReservation> reservation;

	public List<GlyReservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<GlyReservation> reservation) {
		this.reservation = reservation;
	}
	
}
