package com.kkwriter.gallery.service.activity;

import com.kkwriter.gallery.entity.activity.GlyActivity;

import java.util.List;

/**
 * Created by lisha on 2018/5/21 11:23.
 *
 * @author lisha
 */
public interface IGlyActivityService {

    /**
     * 依据activityID删除activity
     * @param id activityID
     */
    void deleteActivity(Integer id);

    /**
     * 查询所有的activity，默认依据创建时间倒排
     * @return activity集合
     */
    List<GlyActivity> queryAllActivity();

    /**
     * 添加或更新一个活动
     * 若所给参数activity存在ID，则为更新，否则为添加
     * @param activity 需要添加的活动
     * @param activityTime 活动时间
     * @param picture 活动配图
     */
    void saveActivity(GlyActivity activity, String activityTime, String picture);
}
