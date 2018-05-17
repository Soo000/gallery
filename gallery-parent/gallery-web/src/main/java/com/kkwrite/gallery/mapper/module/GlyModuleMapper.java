package com.kkwrite.gallery.mapper.module;

import java.util.List;

import com.kkwrite.gallery.pojo.module.GlyModuleDO;
import com.kkwrite.gallery.service.home.ModuleQuery;

public interface GlyModuleMapper {

    int deleteByPrimaryKey(Integer moduleId);

    int insert(GlyModuleDO record);

    int insertSelective(GlyModuleDO record);

    GlyModuleDO selectByPrimaryKey(Integer moduleId);

    List<GlyModuleDO> selectByParentId(ModuleQuery moduleQuery);

    int updateByPrimaryKeySelective(GlyModuleDO record);

    int updateByPrimaryKey(GlyModuleDO record);
}