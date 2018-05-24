package com.kkwriter.gallery.ctrl.address;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwriter.gallery.entity.address.GlyAddress;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.address.AddressService;

/**
 *
 * @author lisha
 */
@Controller
@RequestMapping("/address")
@ResponseBody
@Validated
public class AddressCtrl {
	@Resource(name = "addressServiceImpl")
	private AddressService addressService;
	
	@GetMapping("/getone/{id}")
	public Result<GlyAddress> findById(@Min(value = 0, message = "ID必须未数字且大于0") @PathVariable Integer id) {
		return ResultUtil.success(addressService.queryAddressById(id));
	}
	
}
