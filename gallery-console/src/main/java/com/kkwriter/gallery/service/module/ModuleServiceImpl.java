package com.kkwriter.gallery.service.module;

import com.kkwriter.gallery.entity.module.GlyModule;
import com.kkwriter.gallery.repository.module.GlyModuleItemRepository;
import com.kkwriter.gallery.repository.module.GlyModuleRepository;
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

    @Override
    public List<GlyModule> findAllModule() {
        return moduleRepository.findAll(new Sort(Sort.Direction.ASC, "creationTime"));
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
        module.setValid(1);
        moduleRepository.saveAndFlush(module);
    }

    @Override
    public void deleteModuleById(Integer id) {
        moduleRepository.deleteById(id);
    }
}
