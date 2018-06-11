package com.kkwriter.gallery.service.product;

import com.kkwriter.gallery.entity.product.GlyProductType;

import java.util.List;

/**
 * Created by lisha on 2018/6/8 18:42.
 *
 * @author lisha
 */
public interface IGlyProductTypeService {
    /**
     * 查询所有的产品类型
     * @return list
     */
    List<GlyProductType> findAll();

    /**
     * 保存一个产品类型
     * @param type 待保存的类型
     */
    void save(GlyProductType type);

    /**
     * 根据ID删除产品类型
     * @param id 产品类型ID
     */
    void delete(Integer id);
}
