package com.kkwriter.gallery.service.module;

import com.kkwriter.gallery.entity.module.GlyModule;

import java.util.List;

/**
 * Created by lisha on 2018/5/14 10:40.
 *
 * @author lisha
 */
public interface IModuleService {

    /**
     * 查询所有的首页模块
     * @return 首页模块集合
     */
    List<GlyModule> findAllModule();

    /**
     * 保存或者更新一个模块
     * @param module 需要操作的模块
     */
    void saveOrUpdateModule(GlyModule module);

    /**
     * 根据模块ID删除模块
     * @param id 模块ID
     */
    void deleteModuleById(Integer id);
}
