package com.kkwrite.gallery.mapper.activity;

import java.util.List;

import com.kkwrite.gallery.pojo.activity.ActivityQuery;
import com.kkwrite.gallery.pojo.activity.GlyActivityDO;

public interface GlyActivityMapper {

    int deleteByPrimaryKey(Integer activityId);

    int insert(GlyActivityDO record);

    int insertSelective(GlyActivityDO record);

    GlyActivityDO selectByPrimaryKey(Integer activityId);
    
    List<GlyActivityDO> selectSelective(ActivityQuery activityQuery);
    
    int updateByPrimaryKeySelective(GlyActivityDO record);

    int updateByPrimaryKey(GlyActivityDO record);
}