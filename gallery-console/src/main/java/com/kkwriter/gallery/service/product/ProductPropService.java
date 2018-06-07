package com.kkwriter.gallery.service.product;

import com.kkwriter.gallery.entity.product.GlyProductProp;

import java.util.List;

/**
 * Created by lisha on 2018/6/7 17:54.
 *
 * @author lisha
 */
public interface ProductPropService {
    /**
     * 查询所有的产品属性
     * @return list
     */
    List<GlyProductProp> findAll();

    /**
     * 添加或者修改一个产品属性<br>
     * 通过判断ID是否存在来决定是新增还是修改
     * @param prop 需要操作的属性对象
     */
    void save(GlyProductProp prop);

    /**
     * 根据ID删除产品属性
     * @param id 需要删除的属性ID
     */
    void delete(Integer id);
}
