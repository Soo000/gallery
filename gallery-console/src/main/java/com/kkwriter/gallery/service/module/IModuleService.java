package com.kkwriter.gallery.service.module;

import com.kkwriter.gallery.entity.module.GlyModule;
import com.kkwriter.gallery.entity.module.GlyModuleItem;

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

    /**
     * 根据模块Id查询所有的模块内商品
     * @param id 模块ID
     * @return moduleItem list
     */
    List<GlyModuleItem> findAllModuleItemByModuleId(int id);

    /**
     * 根据模块ID获取模块名
     * @param id 模块ID
     * @return 模块名
     */
    String findModuleNameById(int id);

    /**
     * 保存一个模块内商品
     * @param item 需要保存的商品
     */
    void saveModuleItem(GlyModuleItem item);

    /**
     * 根据moduleItemID删除模块内商品
     * @param id moduleItem ID
     */
    void deleteModuleItemByItemId(int id);
}
