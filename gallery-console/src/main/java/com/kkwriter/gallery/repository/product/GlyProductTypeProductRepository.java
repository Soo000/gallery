package com.kkwriter.gallery.repository.product;

import com.kkwriter.gallery.entity.product.GlyRProductTypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author lisha
 */
public interface GlyProductTypeProductRepository extends JpaRepository<GlyRProductTypeProduct, Integer> {

    /**
     * 根据产品ID删除产品类型
     * @param productId 产品ID
     */
    void deleteByProductId(int productId);

    /**
     * 根据产品ID查询所有产品类型
     * @param productId 产品ID
     * @return 产品类型
     */
    List<GlyRProductTypeProduct> findAllByProductId(Integer productId);

}
