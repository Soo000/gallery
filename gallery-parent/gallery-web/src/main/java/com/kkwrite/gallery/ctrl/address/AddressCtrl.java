package com.kkwrite.gallery.ctrl.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.address.GlyAddress;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.address.AddressService;
import com.kkwrite.gallery.service.user.UserService;

@Controller
@RequestMapping("/addressctrl")
public class AddressCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(AddressCtrl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	
	/**
	 * 登录以及登录失败
	 * @param error
	 * @param model
	 * @return
	 */
	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl(HttpServletRequest request) {
		String from = request.getParameter("from");
		String productIds = request.getParameter("productIds");
		
		// ModelAndView 默认使用转发forward
		ModelAndView modelAndView = new ModelAndView("/address/address");
		
		try {
			// 查询用户
			String username = getUsername();
			GlyUser user = userService.queryUserByName(username);
			modelAndView.addObject("user", user);
			
			// 查询用户地址信息
			List<GlyAddress> addresses = addressService.queryAddresses(user.getUserId());
			modelAndView.addObject("addresses", addresses);
			
			if (from != null && from.equals("order")) {
				modelAndView.addObject("productIds", productIds.split("-"));
				modelAndView.addObject("from", from);
			}
		} catch (ServiceException e) {
			logger.error("[ run ] AddressCtrl.pageCtrl(), 查询用户或用户地址信息出错！");
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/preadd")
	public ModelAndView preAdd() {
		
		ModelAndView modelAndView = new ModelAndView("/address/addressEdit");
		
		return modelAndView;
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		
		ModelAndView modelAndView = new ModelAndView("/address/address");
		
		return modelAndView;
	}
	
	@RequestMapping("/preedit")
	public ModelAndView preEdit(String id) {
		
		ModelAndView modelAndView = new ModelAndView("/address/addressEdit");
		
		if (id != null && !id.isEmpty()) {
			try {
				GlyAddress address = addressService.queryAddressByAddId(Integer.parseInt(id));
				modelAndView.addObject("address", address);
			} catch (ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request) {
		
		String addressId = request.getParameter("addressId");
		if (addressId == null || addressId.isEmpty()) {
			return new ModelAndView("forward:/addressctrl/pagectrl");
		}
		
		ModelAndView modelAndView = new ModelAndView("forward:/addressctrl/preedit?id=" + addressId);
		
		String receiver = request.getParameter("receiver");
		String phoneNum = request.getParameter("phoneNum");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		String addressDetail = request.getParameter("addressDetail");
		String isDefaultAddress = request.getParameter("defaultAddress");
		String addressLable = request.getParameter("addressLable");
		if (addressLable == null) {
			addressLable = "";
		}
		
		try {
			addressSubParamsCheck(receiver, phoneNum, provinceId, cityId, countyId, addressDetail, isDefaultAddress);
		} catch (Exception e1) {
			modelAndView.addObject("errorMsg", "服务异常！" + e1.getMessage());
			return modelAndView;
		}
		
		try {
			addressDetail = getDetailAddress(countyId, addressDetail);
		} catch (Exception e2) {
			modelAndView.addObject("errorMsg", "服务异常！" + e2.getMessage());
			return modelAndView;
		}
		
		// 如果isDefaultAddress值为1，则修改原默认地址为非默认
		if (isDefaultAddress.equals("1")) {
			try {
				// 先查找现有的默认地址，若没有默认地址，则跳过该步骤
				Integer id = addressService.queryDefAddrId();
				if (id != null) {
					addressService.cancelDefaultAddr(id);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				modelAndView.addObject("errorMsg", "服务异常！" + e.toString());
				return modelAndView;
			}
		}
		
		GlyAddress address = new GlyAddress();
		address.setAddressId(Integer.parseInt(addressId));
		address.setReceiver(receiver);
		address.setPhoneNum(phoneNum);
		address.setAddressLabels(addressLable);
		address.setProvinceId(Integer.parseInt(provinceId));
		address.setCityId(Long.parseLong(cityId));
		address.setCountyId(Long.parseLong(countyId));
		address.setAddress(addressDetail);
		address.setIsDefault(Integer.parseInt(isDefaultAddress)); // 0-非默认 1-默认
		address.setIsValid(1);
		
		try {
			addressService.updateAddress(address);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			modelAndView.addObject("errorMsg", "服务异常！" + e.getCause().toString());
			return modelAndView;
		}
		
		modelAndView.setViewName("forward:/addressctrl/pagectrl");
		return modelAndView;
	}
	
	@RequestMapping("/deladd")
	public ModelAndView delete(String id) {
		
		ModelAndView modelAndView = new ModelAndView("forward:/addressctrl/pagectrl");
		
		if (id == null || !Pattern.compile("^\\d+$").matcher(id).matches()) {
			modelAndView.addObject("errorMsg", "请求参数不合法!");
			return modelAndView;
		}
		
		try {
			addressService.deleteAddress(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			modelAndView.addObject("errorMsg", e.getCause().toString());
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("/address/addressEdit");
		
		String receiver = request.getParameter("receiver");
		String phoneNum = request.getParameter("phoneNum");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		String addressDetail = request.getParameter("addressDetail");
		String isDefaultAddress = request.getParameter("defaultAddress");
		String addressLable = request.getParameter("addressLable");
		if (addressLable == null) {
			addressLable = "";
		}
		
		try {
			addressSubParamsCheck(receiver, phoneNum, provinceId, cityId, countyId, addressDetail, isDefaultAddress);
		} catch (Exception e1) {
			modelAndView.addObject("errorMsg", "服务异常！" + e1.getMessage());
			return modelAndView;
		}
		
		try {
			addressDetail = getDetailAddress(countyId, addressDetail);
		} catch (Exception e2) {
			modelAndView.addObject("errorMsg", "服务异常！" + e2.getMessage());
			return modelAndView;
		}
		
		GlyUser user = null;
		try {
			String username = getUsername();
			user = userService.queryUserByName(username);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			modelAndView.addObject("errorMsg", "服务异常！" + e.toString());
			return modelAndView;
		}
		
		// 如果isDefaultAddress值为1，则修改原默认地址为非默认
		if (isDefaultAddress.equals("1")) {
			try {
				// 先查找现有的默认地址，若没有默认地址，则跳过该步骤
				Integer id = addressService.queryDefAddrId();
				if (id != null) {
					addressService.cancelDefaultAddr(id);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				modelAndView.addObject("errorMsg", "服务异常！" + e.toString());
				return modelAndView;
			}
		}
		
		GlyAddress address = new GlyAddress();
		address.setUserId(user.getUserId());
		address.setReceiver(receiver);
		address.setPhoneNum(phoneNum);
		address.setAddressLabels(addressLable);
		address.setIsDefault(Integer.parseInt(isDefaultAddress)); // 0-非默认 1-默认
		address.setProvinceId(Integer.parseInt(provinceId));
		address.setCityId(Long.parseLong(cityId));
		address.setCountyId(Long.parseLong(countyId));
		address.setAddress(addressDetail);
		address.setIsValid(1);
		
		try {
			addressService.saveAddress(address);
		} catch (ServiceException e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			modelAndView.addObject("errorMsg", "服务异常！" + e.getCause().toString());
			return modelAndView;
		}
		
		modelAndView.setViewName("forward:/addressctrl/pagectrl");
		return modelAndView;
	}
	
	@RequestMapping("/getprocitvil")
	@ResponseBody
	public Map<String, Object> getProCitVil(String type, String provinceId, String cityId) {
		
		Map<String, Object> retMap = new HashMap<>();
		boolean isSuccess = true;
		String retMsg = null;
		List<Map<String, String>> list = null;
		if (type == null || type.isEmpty() || !(type.equals("0") || type.equals("1") || type.equals("2"))) {
			isSuccess = false;
			retMsg = "请求参数不合法！type为空或值不合法！";
		} else {
			Pattern pattern = Pattern.compile("^\\d+$");
			switch (type) {
			case "0":
				try {
					list = addressService.queryProvince();
				} catch (ServiceException e) {
					logger.error(e.getMessage(), e);
					isSuccess = false;
					retMsg = e.toString();
				}
				break;
			case "1":
				Matcher isNum = pattern.matcher(provinceId);
				if (provinceId == null || provinceId.isEmpty() || !isNum.matches()) {
					isSuccess = false;
					retMsg = "请求参数不合法！provinceId为空或值不合法！";
					break;
				}
				try {
					list = addressService.queryCity(provinceId);
				} catch (ServiceException e) {
					logger.error(e.getMessage(), e);
					isSuccess = false;
					retMsg = e.toString();
				}
				break;
			case "2":
				Matcher isNum2 = pattern.matcher(cityId);
				if (cityId == null || cityId.isEmpty() || !isNum2.matches()) {
					isSuccess = false;
					retMsg = "请求参数不合法！cityId为空或值不合法！";
					break;
				}
				try {
					list = addressService.queryCountry(cityId);
				} catch (ServiceException e) {
					logger.error(e.getMessage(), e);
					isSuccess = false;
					retMsg = e.toString();
				}
				break;
			}
		}
		
		if (isSuccess) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "成功！");
			retMap.put("data", list);
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retMsg", "服务异常！ " + retMsg);
		}
		
		return retMap;
	}
	
	private void addressSubParamsCheck(String receiver, String phoneNum, 
			String provinceId, String cityId, 
			String countyId, String addressDetail, String isDefaultAddress) throws Exception {
		
		if (receiver == null || receiver.isEmpty()) {
			throw new Exception("收货人为空或不合法！");
		}
		
		Pattern pattern = Pattern.compile("^1[3-9][0-9]{9}$");
		if (phoneNum == null || !pattern.matcher(phoneNum).matches()) {
			throw new Exception("手机号为空或不合法！");
		}
		
		Pattern numPattern = Pattern.compile("^\\d+$");
		if (provinceId == null || !numPattern.matcher(provinceId).matches()) {
			throw new Exception("省份为空或不合法！");
		}
		
		if (cityId == null || !numPattern.matcher(cityId).matches()) {
			throw new Exception("城市为空或不合法！");
		}
		
		if (countyId == null || !numPattern.matcher(countyId).matches()) {
			throw new Exception("区县为空或不合法！");
		}
		
		if (addressDetail == null || addressDetail.isEmpty()) {
			throw new Exception("详细地址为空或不合法！");
		}
		
		if (isDefaultAddress == null || (!isDefaultAddress.equals("0") && !isDefaultAddress.equals("1"))) {
			throw new Exception("默认地址参数不合法！");
		}
	}
	
	private String getDetailAddress(String countyId, String addressDetail) throws Exception {
		try {
			addressDetail = addressService.queryProCitCountName(countyId) + addressDetail;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			throw new Exception("服务异常！" + e.toString());
		}
		return addressDetail;
	}
	
}
