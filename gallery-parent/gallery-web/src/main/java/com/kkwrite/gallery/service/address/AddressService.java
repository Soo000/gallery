package com.kkwrite.gallery.service.address;

import java.util.List;
import java.util.Map;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.address.GlyAddress;

public interface AddressService {

	public GlyAddress queryAddress(int userId) throws ServiceException;
	
	public List<GlyAddress> queryAddresses(int userId) throws ServiceException;
	
	public List<Map<String, String>> queryProvince() throws ServiceException;
	
	public List<Map<String, String>> queryCity(String provinceId) throws ServiceException;
	
	public List<Map<String, String>> queryCountry(String cityId) throws ServiceException;
	
	public String queryProCitCountName(String countyId) throws ServiceException;
	
	public void saveAddress(GlyAddress address) throws ServiceException;

	public GlyAddress queryAddressByAddId(int addId) throws ServiceException;
	
	public void updateAddress(GlyAddress address) throws ServiceException;
	
	public void deleteAddress(int addressId) throws ServiceException;
	
	public void cancelDefaultAddr(int addrId) throws ServiceException;
	
	public Integer queryDefAddrId() throws ServiceException;
	
}