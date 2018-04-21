package com.kkwrite.gallery.mapper.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.order.GlyOrderExample;

public interface GlyOrderMapper {
    int countByExample(GlyOrderExample example);

    int deleteByExample(GlyOrderExample example);

    int deleteByPrimaryKey(String orderCode);

    int insert(GlyOrder record);

    int insertSelective(GlyOrder record);

    List<GlyOrder> selectByExample(GlyOrderExample example);

    GlyOrder selectByPrimaryKey(String orderCode);
    
    List<GlyOrder> selectSelective(GlyOrder record);
    
    List<Map<String, Object>> selectOrderProducts(Map<String, String> orderParam);

    int updateByExampleSelective(@Param("record") GlyOrder record, @Param("example") GlyOrderExample example);

    int updateByExample(@Param("record") GlyOrder record, @Param("example") GlyOrderExample example);

    int updateByPrimaryKeySelective(GlyOrder record);

    int updateByPrimaryKey(GlyOrder record);
}