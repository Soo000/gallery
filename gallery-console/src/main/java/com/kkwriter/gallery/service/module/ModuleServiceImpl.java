package com.kkwriter.gallery.service.module;

import com.kkwriter.gallery.entity.module.GlyModule;
import com.kkwriter.gallery.entity.module.GlyModuleItem;
import com.kkwriter.gallery.entity.product.GlyProductPicture;
import com.kkwriter.gallery.repository.module.GlyModuleItemRepository;
import com.kkwriter.gallery.repository.module.GlyModuleRepository;
import com.kkwriter.gallery.repository.product.GlyProductPictureRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lisha on 2018/5/14 10:40.
 *
 * @author lisha
 */
@Service("moduleService")
public class ModuleServiceImpl implements IModuleService {

    @Resource(name = "glyModuleRepository")
    private GlyModuleRepository moduleRepository;
    @Resource(name = "glyModuleItemRepository")
    private GlyModuleItemRepository moduleItemRepository;
    @Resource(name = "glyProductPictureRepository")
    private GlyProductPictureRepository pictureRepository;

    @Override
    public List<GlyModule> findAllModule() {
        return moduleRepository.findAll(new Sort(Sort.Direction.ASC, "moduleOrder"));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrUpdateModule(GlyModule module) {
        String name = "首页";
        if (module.getParentModuleId() != 0) {
            // 根据parentModuleId查询parentModuleName
            name = moduleRepository.findById(module.getParentModuleId()).orElseThrow(null).getModuleName();
        }
        module.setParentModuleName(name);
        moduleRepository.saveAndFlush(module);
    }

    @Override
    public void deleteModuleById(Integer id) {
        moduleRepository.deleteById(id);
    }

    @Override
    public List<GlyModuleItem> findAllModuleItemByModuleId(int id) {
        return moduleItemRepository.findAllByModuleId(id, new Sort(Sort.Direction.ASC, "moduleItemOrder"));
    }

    @Override
    public String findModuleNameById(int id) {
        return moduleRepository.findById(id).orElseThrow(null).getModuleName();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveModuleItem(GlyModuleItem item) {
        // 查询商品配图
        List<GlyProductPicture> pictureList = pictureRepository.findAllByProductId(item.getProductId(), new Sort(Sort.Direction.ASC, "productPictureType", "productPictureOrder"));
        if (pictureList == null || pictureList.isEmpty()) {
            throw new RuntimeException("该商品没有配图，请先添加商品配图！");
        }
        GlyProductPicture glyPicture = null;
        for (GlyProductPicture picture : pictureList) {
            if (picture.getProductPictureType() == 11) {
                glyPicture = picture;
                break;
            }
        }
        if (glyPicture == null) {
            glyPicture = pictureList.get(0);
        }
        item.setModuleItemImage(glyPicture.getProductPictureFileName());
        moduleItemRepository.saveAndFlush(item);
    }

    @Override
    public void deleteModuleItemByItemId(int id) {
        moduleItemRepository.deleteById(id);
    }
}
