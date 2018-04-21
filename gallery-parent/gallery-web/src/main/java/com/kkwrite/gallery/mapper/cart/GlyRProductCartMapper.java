package com.kkwrite.gallery.mapper.cart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kkwrite.gallery.pojo.cart.GlyRProductCartExample;
import com.kkwrite.gallery.pojo.cart.GlyRProductCartKey;

public interface GlyRProductCartMapper {
    int countByExample(GlyRProductCartExample example);

    int deleteByExample(GlyRProductCartExample example);

    int deleteByPrimaryKey(GlyRProductCartKey key);
    
    int deleteProducts(Map<String, Object> params);

    int insert(GlyRProductCartKey record);

    int insertSelective(GlyRProductCartKey record);

    List<GlyRProductCartKey> selectByExample(GlyRProductCartExample example);

    int updateByExampleSelective(@Param("record") GlyRProductCartKey record, @Param("example") GlyRProductCartExample example);

    int updateByExample(@Param("record") GlyRProductCartKey record, @Param("example") GlyRProductCartExample example);
}