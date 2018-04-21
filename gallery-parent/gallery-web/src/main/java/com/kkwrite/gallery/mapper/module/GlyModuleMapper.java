package com.kkwrite.gallery.mapper.module;

import com.kkwrite.gallery.pojo.module.GlyModule;
import com.kkwrite.gallery.pojo.module.GlyModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyModuleMapper {
    int countByExample(GlyModuleExample example);

    int deleteByExample(GlyModuleExample example);

    int deleteByPrimaryKey(Integer moduleId);

    int insert(GlyModule record);

    int insertSelective(GlyModule record);

    List<GlyModule> selectByExample(GlyModuleExample example);

    GlyModule selectByPrimaryKey(Integer moduleId);

    int updateByExampleSelective(@Param("record") GlyModule record, @Param("example") GlyModuleExample example);

    int updateByExample(@Param("record") GlyModule record, @Param("example") GlyModuleExample example);

    int updateByPrimaryKeySelective(GlyModule record);

    int updateByPrimaryKey(GlyModule record);
}