package com.kkwriter.gallery.service.address;

import com.kkwriter.gallery.entity.address.GlyAddress;

/**
 * @author lisha
 */
public interface AddressService {

	/**
	 * 通过ID查询地址信息
	 * @param id id
	 * @return 地址对象
	 */
	GlyAddress queryAddressById(Integer id);
	
}
