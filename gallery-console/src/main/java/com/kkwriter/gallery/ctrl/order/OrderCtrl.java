package com.kkwriter.gallery.ctrl.order;

import com.kkwriter.gallery.entity.order.GlyOrder;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Controller
@RequestMapping("/order")
@Validated
public class OrderCtrl {

	@Resource(name = "orderServiceImpl")
	private OrderService orderService;

	@GetMapping("/all-order")
	public String showAllOrder() {
		return "show_all_order";
	}

	@GetMapping("/order-status")
	public ModelAndView showStatusOrder(int status) {
		ModelAndView model = new ModelAndView();
		model.setViewName("show_order_by_status");
		model.addObject("status", status);
		return model;
	}
	
	@PostMapping("/editprice/{orderCode}")
	@ResponseBody
	public Result<?> editOrderPrice(
			@PathVariable @Pattern(regexp="\\d+") String orderCode,
			@NotNull @Min(0) Float price) {
		orderService.editOrderPrice(orderCode, price);
		return ResultUtil.success();
	}
	
	@GetMapping("/statusorder/{status}")
	@ResponseBody
	public Result<Page<GlyOrder>> getByStatus(
			@PathVariable @NotNull @Min(1) @Max(6) Integer status,
			@PageableDefault(sort = { "creationTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResultUtil.success(orderService.getOrderByStatus(status, pageable));
	}

	@GetMapping("/all")
	@ResponseBody
	public Result<Page<GlyOrder>> getAll(
			@PageableDefault(sort = { "creationTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResultUtil.success(orderService.getAllOrder(pageable));
	}

}
