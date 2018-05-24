package com.kkwriter.gallery.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkwriter.gallery.entity.product.GlyRProductProps;

import java.util.List;

public interface GlyRProductPropsRepository extends JpaRepository<GlyRProductProps, Integer> {

    /**
     * 根据产品ID删除所有的产品属性
     * @param productId 产品ID
     */
    void deleteByProductId(int productId);

    /**
     * 根据产品ID查询所有产品属性
     * @param productId 产品ID
     * @return 所有属性
     */
    List<GlyRProductProps> findAllByProductId(Integer productId);

}
