package com.kkwriter.gallery.service.order;

import com.kkwriter.gallery.entity.BaseEntity;
import com.kkwriter.gallery.entity.order.GlyReservation;
import com.kkwriter.gallery.exception.GalleyConsoleException;
import com.kkwriter.gallery.repository.order.GlyReservationRepository;
import com.kkwriter.gallery.result.ReturnEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisha on 2018/6/15 9:47.
 *
 * @author lisha
 */
@Service("reservationService")
public class GlyReservationServiceImpl implements IGlyReservationService {
    @Resource(name = "glyReservationRepository")
    private GlyReservationRepository repository;

    @Override
    public List<GlyReservation> getOnePage(Pageable pageable) {
        Page<GlyReservation> page = repository.findAll(pageable);
        if (page.hasContent()) {
            return page.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public Long getTotalNum() {
        return repository.count();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public GlyReservation save(GlyReservation reservation) {
        GlyReservation old = repository.findById(reservation.getReservationCode())
                .orElseThrow(() -> new GalleyConsoleException(ReturnEnum.formErrorMessage("该订单不存在！")));
        try {
            BaseEntity.update(old, reservation);
        } catch (IllegalAccessException e) {
            throw new GalleyConsoleException(ReturnEnum.formErrorMessage("更新失败！"));
        }
        old.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return repository.saveAndFlush(old);
    }
}
