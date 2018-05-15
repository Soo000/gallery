package com.kkwriter.gallery.ctrl.module;

import com.kkwriter.gallery.entity.module.GlyModule;
import com.kkwriter.gallery.entity.module.GlyModuleItem;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.module.IModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<GlyModule> list = moduleService.findAllModule();
        ModelAndView model = new ModelAndView("home_module_item_manage");
        model.addObject("modules", list);
        return model;
    }

    @GetMapping(value = "/getModuleItemListPage/{id}")
    public ModelAndView selectModuleItemByModuleId(@PathVariable int id) {
        List<GlyModuleItem> list = moduleService.findAllModuleItemByModuleId(id);
        String moduleName = moduleService.findModuleNameById(id);
        ModelAndView model = new ModelAndView("module_item_list");
        model.addObject("moduleItems", list);
        model.addObject("moduleName", moduleName);
        model.addObject("moduleId", id);
        return model;
    }

    @PostMapping(value = "/saveModuleItem")
    public Result saveModuleItem(Integer moduleId, Integer productId, String moduleItemName,
                                 Integer moduleItemType, Float moduleItemOrder, Integer valid, Integer moduleItemId) {
        GlyModuleItem item = new GlyModuleItem();
        item.setModuleId(moduleId);
        item.setProductId(productId);
        item.setModuleItemName(moduleItemName);
        item.setModuleItemType(moduleItemType);
        item.setModuleItemOrder(moduleItemOrder);
        item.setValid(valid);
        if (moduleItemId != -1) {
            item.setModuleItemId(moduleItemId);
        }
        moduleService.saveModuleItem(item);
        return ResultUtil.success();
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
