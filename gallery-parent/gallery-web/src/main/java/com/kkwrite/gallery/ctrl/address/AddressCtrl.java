package com.kkwrite.gallery.ctrl.address;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.address.GlyAddress;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.address.AddressService;
import com.kkwrite.gallery.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lisha
 */
@Controller
@RequestMapping("/addressctrl")
public class AddressCtrl extends BaseCtrl {

	private Logger logger = Logger.getLogger(AddressCtrl.class);
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^1[3-9][0-9]{9}$");
	private static final String ADDRESS_TYPE_DEFAULT = "1";
	private static final String ADDRESS_TYPE_NONE_DEFAULT = "0";
	private static final Set<String> ADDRESS_LEVEL_TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("0", "1", "2")));
	
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "addressService")
	private AddressService addressService;
	
	/**
	 * 登录以及登录失败
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
			
			if ("order".equals(from)) {
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
		return new ModelAndView("/address/addressEdit");
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		return new ModelAndView("/address/address");
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
		if (isDefaultAddress.equals(ADDRESS_TYPE_DEFAULT)) {
			if (changeDefaultAddress(modelAndView)) {
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
		// 0-非默认 1-默认
		address.setIsDefault(Integer.parseInt(isDefaultAddress));
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

	private boolean changeDefaultAddress(ModelAndView modelAndView) {
		try {
			// 先查找现有的默认地址，若没有默认地址，则跳过该步骤
			Integer id = addressService.queryDefAddrId();
			if (id != null) {
				addressService.cancelDefaultAddr(id);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			modelAndView.addObject("errorMsg", "服务异常！" + e.toString());
			return true;
		}
		return false;
	}

	@RequestMapping("/deladd")
	public ModelAndView delete(String id) {
		
		ModelAndView modelAndView = new ModelAndView("forward:/addressctrl/pagectrl");
		
		if (id == null || !NUMBER_PATTERN.matcher(id).matches()) {
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
		
		GlyUser user;
		try {
			String username = getUsername();
			user = userService.queryUserByName(username);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			modelAndView.addObject("errorMsg", "服务异常！" + e.toString());
			return modelAndView;
		}
		
		// 如果isDefaultAddress值为1，则修改原默认地址为非默认
		if (isDefaultAddress.equals(ADDRESS_TYPE_DEFAULT)) {
			if (changeDefaultAddress(modelAndView)) {
				return modelAndView;
			}
		}
		
		GlyAddress address = new GlyAddress();
		address.setUserId(user.getUserId());
		address.setReceiver(receiver);
		address.setPhoneNum(phoneNum);
		address.setAddressLabels(addressLable);
		// 0-非默认 1-默认
		address.setIsDefault(Integer.parseInt(isDefaultAddress));
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
		
		Map<String, Object> retMap = new HashMap<>(16);
		boolean isSuccess = true;
		String retMsg = null;
		List<Map<String, String>> list = null;
		if ("".equals(type) || !ADDRESS_LEVEL_TYPES.contains(type)) {
			isSuccess = false;
			retMsg = "请求参数不合法！type为空或值不合法！";
		} else {
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
				Matcher isNum = NUMBER_PATTERN.matcher(provinceId);
				if (provinceId.isEmpty() || !isNum.matches()) {
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
				Matcher isNum2 = NUMBER_PATTERN.matcher(cityId);
				if (cityId.isEmpty() || !isNum2.matches()) {
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
			default:
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

		if (phoneNum == null || !PHONE_NUMBER_PATTERN.matcher(phoneNum).matches()) {
			throw new Exception("手机号为空或不合法！");
		}

		if (provinceId == null || !NUMBER_PATTERN.matcher(provinceId).matches()) {
			throw new Exception("省份为空或不合法！");
		}
		
		if (cityId == null || !NUMBER_PATTERN.matcher(cityId).matches()) {
			throw new Exception("城市为空或不合法！");
		}
		
		if (countyId == null || !NUMBER_PATTERN.matcher(countyId).matches()) {
			throw new Exception("区县为空或不合法！");
		}
		
		if (addressDetail == null || addressDetail.isEmpty()) {
			throw new Exception("详细地址为空或不合法！");
		}

		if (isDefaultAddress == null) {
			throw new Exception("默认地址参数不合法！");
		}

		if (!isDefaultAddress.equals(ADDRESS_TYPE_DEFAULT) && !isDefaultAddress.equals(ADDRESS_TYPE_NONE_DEFAULT)) {
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
