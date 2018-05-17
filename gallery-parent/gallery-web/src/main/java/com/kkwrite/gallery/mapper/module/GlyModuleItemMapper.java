package com.kkwrite.gallery.mapper.module;

import java.util.List;

import com.kkwrite.gallery.pojo.module.GlyModuleItemDO;
import com.kkwrite.gallery.service.home.ModuleItemQuery;

public interface GlyModuleItemMapper {

    int deleteByPrimaryKey(Integer moduleItemId);

    int insert(GlyModuleItemDO record);

    int insertSelective(GlyModuleItemDO record);

    List<GlyModuleItemDO> selectSelective(ModuleItemQuery moduleItemQuery);

    GlyModuleItemDO selectByPrimaryKey(Integer moduleItemId);

    int updateByPrimaryKeySelective(GlyModuleItemDO record);

    int updateByPrimaryKey(GlyModuleItemDO record);
}