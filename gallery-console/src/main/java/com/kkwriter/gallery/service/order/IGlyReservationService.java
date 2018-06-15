package com.kkwriter.gallery.service.order;

import com.kkwriter.gallery.entity.order.GlyReservation;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lisha on 2018/6/15 9:47.
 *
 * @author lisha
 */
public interface IGlyReservationService {
    /**
     * 获取一页数据
     * @param pageable 分页参数
     * @return List&lt;GlyReservation&gt;
     */
    List<GlyReservation> getOnePage(Pageable pageable);

    /**
     * 获取预约订单总数
     * @return 预约订单总数
     */
    Long getTotalNum();

    /**
     * 保存一个预约数据
     * @param reservation 需要保存的预约对象
     */
    GlyReservation save(GlyReservation reservation);
}
