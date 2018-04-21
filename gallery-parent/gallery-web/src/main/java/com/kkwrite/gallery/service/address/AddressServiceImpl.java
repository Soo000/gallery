package com.kkwrite.gallery.service.address;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.common.ModuleDict;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.address.GlyAddressMapper;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.address.GlyAddress;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyAddressMapper glyAddressMapper;
	
	@Override
	public GlyAddress queryAddress(int userId) throws ServiceException {
		
		List<GlyAddress> addresses = queryAddresses(userId);
		if (addresses == null || addresses.size() <= 0) return null;
		
		for (GlyAddress glyAddress: addresses) {
			if (glyAddress.getIsDefault().intValue() == ModuleDict.DEFAULT_VALUE_FLAG) {
				return glyAddress;
			}
		}
		
		return addresses.get(0);
	}

	@Override
	public List<GlyAddress> queryAddresses(int userId) throws ServiceException {
		
		GlyAddress param = new GlyAddress();
		param.setUserId(userId);
		param.setIsValid(BasePojo.IS_VALID_Y);
		List<GlyAddress> addresses = glyAddressMapper.selectSelective(param);
		
		return addresses;
	}

	@Override
	public List<Map<String, String>> queryProvince() throws ServiceException {
		List<Map<String, String>> list = null;
		
		try {
			list = glyAddressMapper.selectProvince();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return list;
	}

	@Override
	public List<Map<String, String>> queryCity(String provinceId) throws ServiceException {
		
		List<Map<String, String>> list = null;
		try {
			list = glyAddressMapper.selectCity(provinceId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return list;
	}

	@Override
	public List<Map<String, String>> queryCountry(String cityId) throws ServiceException {
		
		List<Map<String, String>> list = null;
		try {
			list = glyAddressMapper.selectCountry(cityId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return list;
	}

	@Override
	public String queryProCitCountName(String countyId) throws ServiceException {
		String result = null;
		try {
			result = glyAddressMapper.selectProCitCountName(countyId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public void saveAddress(GlyAddress address) throws ServiceException {
		try {
			int n = glyAddressMapper.insertSelective(address);
			if (n != 1) {
				throw new ServiceException("保存地址信息异常！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public GlyAddress queryAddressByAddId(int addId) throws ServiceException {
		GlyAddress param = new GlyAddress();
		param.setAddressId(addId);
		GlyAddress address = null;
		try {
			List<GlyAddress> list = glyAddressMapper.selectSelective(param);
			if (list != null && !list.isEmpty()) {
				address = list.get(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return address;
	}

	@Override
	public void updateAddress(GlyAddress address) throws ServiceException {
		try {
			glyAddressMapper.updateByPrimaryKeySelective(address);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteAddress(int addressId) throws ServiceException {
		try {
			glyAddressMapper.deleteByPrimaryKey(addressId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void cancelDefaultAddr(int addrId) throws ServiceException {
		try {
			glyAddressMapper.cancelDefAddr(addrId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Integer queryDefAddrId() throws ServiceException {
		try {
			return glyAddressMapper.selectDefAddrId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
