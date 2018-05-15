package com.kkwriter.gallery.repository.module;

import com.kkwriter.gallery.entity.module.GlyModuleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lisha on 2018/5/14 10:34.
 *
 * @author lisha
 */
public interface GlyModuleItemRepository extends JpaRepository<GlyModuleItem, Integer> {
    /**
     * 根据moduleId查询所有的moduleItem
     * @param id moduleId
     * @return moduleItem list
     */
    List<GlyModuleItem> findAllByModuleId(int id);
}
