package com.kkwrite.gallery.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kkwrite.gallery.pojo.product.GlyProductProp;
import com.kkwrite.gallery.pojo.product.GlyProductPropExample;

public interface GlyProductPropMapper {
    int countByExample(GlyProductPropExample example);

    int deleteByExample(GlyProductPropExample example);

    int deleteByPrimaryKey(Integer propId);

    int insert(GlyProductProp record);

    int insertSelective(GlyProductProp record);

    List<GlyProductProp> selectByExample(GlyProductPropExample example);

    GlyProductProp selectByPrimaryKey(Integer propId);
    
    List<GlyProductProp> selectProductProps(Map<String, Object> params);

    int updateByExampleSelective(@Param("record") GlyProductProp record, @Param("example") GlyProductPropExample example);

    int updateByExample(@Param("record") GlyProductProp record, @Param("example") GlyProductPropExample example);

    int updateByPrimaryKeySelective(GlyProductProp record);

    int updateByPrimaryKey(GlyProductProp record);
}