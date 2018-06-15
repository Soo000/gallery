package com.kkwriter.gallery.entity.order;

import com.kkwriter.gallery.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by lisha on 2018/6/15 9:21.
 *
 * @author lisha
 */
@Entity(name = "gly_reservation")
public class GlyReservation extends BaseEntity {
    private static final long serialVersionUID = 6667714547511571703L;

    @Id
    @Column(name = "reservation_code", length = 50)
    private String reservationCode;
    @Column(name = "order_code", length = 50)
    private String orderCode;
    @Column(name = "reservation_name", length = 200)
    private String reservationName;
    @Column(name = "reservation_type")
    private Integer reservationType;
    @Column(name = "reservation_username", length = 50)
    private String reservationUsername;
    @Column(name = "reservation_phone_num", length = 50)
    private String reservationPhoneNumber;
    @Column(name = "reservation_address", length = 500)
    private String reservationAddress;
    @Column(name = "reservation_desc", length = 500)
    private String reservationDescription;
    @Column(name = "reservation_time")
    private Timestamp reservationTime;
    @Column(name = "reservation_status")
    private Integer reservationStatus;
    @Column(name = "is_valid")
    private Integer valid;

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public Integer getReservationType() {
        return reservationType;
    }

    public void setReservationType(Integer reservationType) {
        this.reservationType = reservationType;
    }

    public String getReservationUsername() {
        return reservationUsername;
    }

    public void setReservationUsername(String reservationUsername) {
        this.reservationUsername = reservationUsername;
    }

    public String getReservationPhoneNumber() {
        return reservationPhoneNumber;
    }

    public void setReservationPhoneNumber(String reservationPhoneNumber) {
        this.reservationPhoneNumber = reservationPhoneNumber;
    }

    public String getReservationAddress() {
        return reservationAddress;
    }

    public void setReservationAddress(String reservationAddress) {
        this.reservationAddress = reservationAddress;
    }

    public String getReservationDescription() {
        return reservationDescription;
    }

    public void setReservationDescription(String reservationDescription) {
        this.reservationDescription = reservationDescription;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Integer getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "GlyReservation{" +
                "reservationCode='" + reservationCode + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", reservationName='" + reservationName + '\'' +
                ", reservationType=" + reservationType +
                ", reservationUsername='" + reservationUsername + '\'' +
                ", reservationPhoneNumber='" + reservationPhoneNumber + '\'' +
                ", reservationAddress='" + reservationAddress + '\'' +
                ", reservationDescription='" + reservationDescription + '\'' +
                ", reservationTime=" + reservationTime +
                ", reservationStatus=" + reservationStatus +
                ", valid=" + valid +
                '}' + " + " + super.toString();
    }

}
