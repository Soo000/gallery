package com.kkwriter.gallery.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkwriter.gallery.entity.product.GlyRProductProps;

public interface GlyRProductPropsRepository extends JpaRepository<GlyRProductProps, Integer> {

    /**
     * 根据产品ID删除所有的产品属性
     * @param productId 产品ID
     */
    void deleteByProductId(int productId);

}
