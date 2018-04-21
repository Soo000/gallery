package com.kkwrite.gallery.mapper.param;

import com.kkwrite.gallery.pojo.param.GlyParam;
import com.kkwrite.gallery.pojo.param.GlyParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyParamMapper {
    int countByExample(GlyParamExample example);

    int deleteByExample(GlyParamExample example);

    int deleteByPrimaryKey(Integer paramId);

    int insert(GlyParam record);

    int insertSelective(GlyParam record);

    List<GlyParam> selectByExample(GlyParamExample example);

    GlyParam selectByPrimaryKey(Integer paramId);
    
    GlyParam selectByParamKey(String key);

    int updateByExampleSelective(@Param("record") GlyParam record, @Param("example") GlyParamExample example);

    int updateByExample(@Param("record") GlyParam record, @Param("example") GlyParamExample example);

    int updateByPrimaryKeySelective(GlyParam record);

    int updateByPrimaryKey(GlyParam record);
    
    int updateByParamKeySelective(GlyParam record);
}