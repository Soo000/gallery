package com.kkwriter.gallery.ctrl.module;

import com.kkwriter.gallery.entity.module.GlyModule;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.module.IModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lisha on 2018/5/14 11:05.
 *
 * @author lisha
 */
@RestController
@RequestMapping(value = "/module")
public class ModuleController {
    @Resource(name = "moduleService")
    private IModuleService moduleService;

    @GetMapping(value = "/openHomeModuleItemManagePage")
    public ModelAndView openHomeModuleItemManagePage() {
        return new ModelAndView("home_module_item_manage");
    }

    @GetMapping(value = "/openHomeModuleManagePage")
    public ModelAndView openHomeModuleManagePage() {
        ModelAndView model = new ModelAndView("home_module_manage");
        List<GlyModule> list = moduleService.findAllModule();
        model.addObject("modules", list);
        return model;
    }

    @PostMapping(value = "/saveModule")
    public Result saveOrUpdateModule(String moduleName, int parentModuleId, String moduleDescription, Integer moduleId) {
        GlyModule module = new GlyModule();
        module.setModuleName(moduleName);
        module.setParentModuleId(parentModuleId);
        module.setModuleDescription(moduleDescription);
        if (moduleId != -1) {
            module.setModuleId(moduleId);
        }
        moduleService.saveOrUpdateModule(module);
        return ResultUtil.success();
    }

    @PostMapping(value = "/deleteModule")
    public Result deleteModule(Integer id) {
        moduleService.deleteModuleById(id);
        return ResultUtil.success();
    }

}
