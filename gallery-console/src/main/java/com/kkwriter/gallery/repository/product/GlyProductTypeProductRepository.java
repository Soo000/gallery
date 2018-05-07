package com.kkwriter.gallery.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkwriter.gallery.entity.product.GlyRProductTypeProduct;

public interface GlyProductTypeProductRepository extends JpaRepository<GlyRProductTypeProduct, Integer> {

    /**
     * 根据产品ID删除产品类型
     * @param productId 产品ID
     */
    void deleteByProductId(int productId);
}
