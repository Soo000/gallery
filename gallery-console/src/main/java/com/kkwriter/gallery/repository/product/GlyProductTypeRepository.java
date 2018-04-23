package com.kkwriter.gallery.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkwriter.gallery.entity.product.GlyProductType;

public interface GlyProductTypeRepository extends JpaRepository<GlyProductType, Integer> {

	List<GlyProductType> findByParentProductTypeId(Integer parentId);

}
