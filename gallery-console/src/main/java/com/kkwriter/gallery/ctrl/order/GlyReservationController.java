package com.kkwriter.gallery.ctrl.order;

import com.kkwriter.gallery.entity.order.GlyReservation;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.order.IGlyReservationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lisha on 2018/6/15 9:49.
 *
 * @author lisha
 */
@Controller
@RequestMapping(value = "/reservation")
public class GlyReservationController {
    @Resource(name = "reservationService")
    private IGlyReservationService service;

    @GetMapping(value = "/open")
    public ModelAndView openPage() {
        return new ModelAndView("reservation_order_manage");
    }

    @GetMapping(value = "/query-total-num")
    @ResponseBody
    public Result getTotalNum() {
        return ResultUtil.success(service.getTotalNum());
    }

    @GetMapping(value = "/query-data")
    public ModelAndView getData(@PageableDefault(sort = {"creationTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView model = new ModelAndView("reservation_order_manage_table_body");
        model.addObject("data", service.getOnePage(pageable));
        return model;
    }

    @PostMapping(value = "/modify")
    @ResponseBody
    public Result modify(GlyReservation reservation) {
        reservation = service.save(reservation);
        return ResultUtil.success(reservation.getUpdateTime().toLocalDateTime());
    }

}
