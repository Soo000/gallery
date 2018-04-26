package com.kkwriter.gallery.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkwriter.gallery.entity.product.GlyProductPicture;

import java.util.List;

/**
 * @author lisha
 */
public interface GlyProductPictureRepository extends JpaRepository<GlyProductPicture, Integer> {

    /**
     * 根据产品ID查询产品所有配图
     * @param productId 产品ID
     * @return 产品的所有配图
     */
    List<GlyProductPicture> findAllByProductId(int productId);

}
