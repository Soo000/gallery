package com.kkwriter.gallery.repository.product;

import com.kkwriter.gallery.entity.product.GlyProductEvaluate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lisha on 2018/5/17 15:49.
 *
 * @author lisha
 */
public interface GlyProductEvaluateRepository extends JpaRepository<GlyProductEvaluate, Integer> {
    /**
     * 根据产品ID删除该产品的所有评价
     * @param productId 产品ID
     */
    void deleteAllByProductId(int productId);
}
