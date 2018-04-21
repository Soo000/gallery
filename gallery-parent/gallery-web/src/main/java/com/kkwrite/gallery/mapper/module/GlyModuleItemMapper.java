package com.kkwrite.gallery.mapper.module;

import com.kkwrite.gallery.pojo.module.GlyModuleItem;
import com.kkwrite.gallery.pojo.module.GlyModuleItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyModuleItemMapper {
    int countByExample(GlyModuleItemExample example);

    int deleteByExample(GlyModuleItemExample example);

    int deleteByPrimaryKey(Integer moduleItemId);

    int insert(GlyModuleItem record);

    int insertSelective(GlyModuleItem record);

    List<GlyModuleItem> selectByExample(GlyModuleItemExample example);
    
    List<GlyModuleItem> selectSelective(GlyModuleItem param);

    GlyModuleItem selectByPrimaryKey(Integer moduleItemId);

    int updateByExampleSelective(@Param("record") GlyModuleItem record, @Param("example") GlyModuleItemExample example);

    int updateByExample(@Param("record") GlyModuleItem record, @Param("example") GlyModuleItemExample example);

    int updateByPrimaryKeySelective(GlyModuleItem record);

    int updateByPrimaryKey(GlyModuleItem record);
}