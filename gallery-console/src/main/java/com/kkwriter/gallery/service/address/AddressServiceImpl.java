package com.kkwriter.gallery.service.address;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kkwriter.gallery.entity.address.GlyAddress;
import com.kkwriter.gallery.repository.address.GlyAddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	@Resource(name = "glyAddressRepository")
	private GlyAddressRepository glyAddressRepository;

	@Override
	public GlyAddress queryAddressById(Integer id) {
		return glyAddressRepository.findById(id).get();
	}
	
}
