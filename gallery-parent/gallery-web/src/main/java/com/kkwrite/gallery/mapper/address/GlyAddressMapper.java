package com.kkwrite.gallery.mapper.address;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kkwrite.gallery.pojo.address.GlyAddress;
import com.kkwrite.gallery.pojo.address.GlyAddressExample;

public interface GlyAddressMapper {
    int countByExample(GlyAddressExample example);

    int deleteByExample(GlyAddressExample example);

    int deleteByPrimaryKey(Integer addressId);

    int insert(GlyAddress record);

    int insertSelective(GlyAddress record);

    List<GlyAddress> selectByExample(GlyAddressExample example);

    GlyAddress selectByPrimaryKey(Integer addressId);
    
    List<GlyAddress> selectSelective(GlyAddress record);

    int updateByExampleSelective(@Param("record") GlyAddress record, @Param("example") GlyAddressExample example);

    int updateByExample(@Param("record") GlyAddress record, @Param("example") GlyAddressExample example);

    int updateByPrimaryKeySelective(GlyAddress record);

    int updateByPrimaryKey(GlyAddress record);
    
    List<Map<String, String>> selectProvince() throws Exception;
    
    List<Map<String, String>> selectCity(String provinceId) throws Exception;
    
    List<Map<String, String>> selectCountry(String cityId) throws Exception;
    
    String selectProCitCountName(String countyId) throws Exception;
    
    void cancelDefAddr(int addrId) throws Exception;
    
    Integer selectDefAddrId() throws Exception;
}