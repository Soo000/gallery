package com.kkwrite.gallery.ctrl.reservation;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.reservation.GlyReservation;
import com.kkwrite.gallery.service.reservation.ReservationService;
import com.kkwrite.gallery.service.user.UserService;

@Controller
@RequestMapping("/reservationctrl")
public class ReservationCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(ReservationCtrl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ReservationService reservationService;

	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] ReservationCtrl.pageCtrl().");
		ModelAndView modelAndView = new ModelAndView("/reservation/reservation");

		String username = getUsername();
		logger.info("从 Session 中获取 username = " + username);
		// 如果用户名为空，转到
		if (StringUtils.isEmpty(username)) {
			logger.info("准备将请求转发到 /weixinctrl/getcode");
			modelAndView.addObject("codeBackUrl", "/reservationctrl/codeback");
			modelAndView.setViewName("forward:/weixinctrl/getcode");
			return modelAndView;
		}

		try {
			// 查询预约人数
			int reservationCount = reservationService.queryReservationCount();
			modelAndView.addObject("reservationCount", reservationCount);
		} catch (ServiceException e) {
			logger.error("[ run ] ReservationCtrl.pageCtrl(), 查询预约人数出错", e);
		}
		
		logger.debug("[ end ] HomeCtrl.pageCtrl().");
		return modelAndView;
	}

	@RequestMapping("/codeback")
	public ModelAndView codeBack(String code) {
		// 获取用户的 openId
		String openId = getOpenId(code);
		// 构建用户信息
		buildUserInfo(openId);
		// 用户登录用户
		String username = getUsername();
		logger.info("[ run ] HomeCtrl.init(), 从 Session 中获取 username = " + username);
		return new ModelAndView("forward:/reservationctrl/pagectrl");
	}

	@RequestMapping("/reservation")
	@ResponseBody
	public Map<String, String> reservation(GlyReservation reservation) {
		logger.debug("[ begin ] ReservationCtrl.reservation().");
		Map<String, String> resMap = new HashMap<>(16);
		saveReservation(reservation, resMap);
		logger.debug("[ end ] HomeCtrl.reservation().");
		return resMap;
	}

	private void saveReservation(GlyReservation reservation, Map<String, String> resMap) {
		String username = getUsername();
		int userId = userService.queryUserByName(username).getUserId();
		// 生效时间
		Date validTime = Calendar.getInstance().getTime();
		// 生成订单数据
		GlyOrder order = new GlyOrder();
		order.setOrderCode(generateOrderCode());
		order.setUserId(userId);
		order.setOrderType(BasePojo.OrderDict.ORDER_TYPE_RESERVATION);
		order.setOrderStatus(BasePojo.OrderDict.ORDER_STATUS_WAITING_PAY);
		order.setValidTime(validTime);
		order.setInvalidTime(computerDate(validTime, Calendar.HOUR_OF_DAY, 3));
		order.setIsValid(BasePojo.IS_VALID_Y);
		// 预约数据
		reservation.setReservationId(order.getOrderCode());
		reservation.setOrderCode(order.getOrderCode());
		reservation.setReservationName(BasePojo.ReservationTypeEnum.getReservationName(reservation.getReservationType()));
		reservation.setReservationAddress(reservation.getReservationArea() + reservation.getReservationAddress());
		reservation.setReservationTime(validTime);
		reservation.setReservationStatus(BasePojo.ReservationStatus.RESERVATION_STATUS_BEGIN);
		reservation.setIsValid(BasePojo.IS_VALID_Y);
		try {
			boolean result = reservationService.reservation(order, reservation);
			if (result) {
				resMap.put("retCode", "0");
				resMap.put("retMsg", "订单保存成功！");
			} else {
				resMap.put("retCode", "-2");
				resMap.put("retMsg", "订单保存失败！");
			}
		} catch (ServiceException e) {
			logger.error("[ run ] ReservationCtrl.saveReservation(), 调用预约服务出错", e);
			resMap.put("retCode", "-2");
			resMap.put("retMsg", "订单保存失败！" + e.toString());
		} catch (Exception e) {
			logger.error("[ run ] ReservationCtrl.saveReservation(), 预约控制逻辑出错", e);
			resMap.put("retCode", "-2");
			resMap.put("retMsg", "订单保存失败！" + e.toString());
		}
	}

}
