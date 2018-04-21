package com.kkwrite.gallery.ctrl.myself;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.order.OrderService;
import com.kkwrite.gallery.service.user.UserService;

@Controller
@RequestMapping("/myselfctrl")
public class MyselfCtrl extends BaseCtrl {

	private Logger logger = Logger.getLogger(MyselfCtrl.class);
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl() {
		logger.debug("[ begin ] HomeCtrl.pageCtrl().");
		
		ModelAndView modelAndView = new ModelAndView("/myself/myself");
		String username = getUserDetails().getUsername();
		modelAndView.addObject("username", username);
		
		// 获取用户信息
		Map<String, Integer> orderInfo = getUserOrderInfo(username);
		modelAndView.addObject("orderInfo", orderInfo);
		
		logger.debug("[ end ] HomeCtrl.pageCtrl().");
		return modelAndView;
	}
	
	/**
	 * 获取用户订单情况
	 * @param username
	 * @return
	 */
	private Map<String, Integer> getUserOrderInfo(String username) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		// 全部订单
		int allNum = 0;
		// 等待支付
		int waitingNum = 0;
		// 已经支付
		int paiedNum = 0;
		// 已取消
		int cancelNum = 0;
		// 已完成
		int finishedNum = 0;
		
		GlyUser glyUser = userService.queryUserByName(username);
		List<GlyOrder> glyOrders = orderService.getMyOrder(glyUser.getUserId());
		if (glyOrders != null && glyOrders.size() > 0) {
			allNum = glyOrders.size();
			for (int i = 0; i < glyOrders.size(); i++) {
				int orderStatus = glyOrders.get(i).getOrderStatus();
				switch (orderStatus) {
				case BasePojo.OrderDict.ORDER_STATUS_WAITING_PAY:
					waitingNum++;
				break;
				case BasePojo.OrderDict.ORDER_STATUS_PAYED:
					paiedNum++;
				break;
				case BasePojo.OrderDict.ORDER_STATUS_CANCELED:
					cancelNum++;
				break;
				case BasePojo.OrderDict.ORDER_STATUS_FINISHED:
					finishedNum++;
				break;
				}
			}
		}
		
		result.put("allNum", allNum);
		result.put("waitingNum", waitingNum);
		result.put("paiedNum", paiedNum);
		result.put("cancelNum", cancelNum);
		result.put("finishedNum", finishedNum);
		
		return result;
	}

}
