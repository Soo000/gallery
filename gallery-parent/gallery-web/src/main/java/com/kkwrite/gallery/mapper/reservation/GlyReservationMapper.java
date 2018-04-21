package com.kkwrite.gallery.mapper.reservation;

import com.kkwrite.gallery.pojo.reservation.GlyReservation;
import com.kkwrite.gallery.pojo.reservation.GlyReservationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyReservationMapper {
    int countByExample(GlyReservationExample example);

    int deleteByExample(GlyReservationExample example);

    int deleteByPrimaryKey(Integer reservationId);

    int insert(GlyReservation record);

    int insertSelective(GlyReservation record);

    List<GlyReservation> selectByExample(GlyReservationExample example);

    GlyReservation selectByPrimaryKey(Integer reservationId);
    
    int selectReservationCount();

    int updateByExampleSelective(@Param("record") GlyReservation record, @Param("example") GlyReservationExample example);

    int updateByExample(@Param("record") GlyReservation record, @Param("example") GlyReservationExample example);

    int updateByPrimaryKeySelective(GlyReservation record);

    int updateByPrimaryKey(GlyReservation record);
}