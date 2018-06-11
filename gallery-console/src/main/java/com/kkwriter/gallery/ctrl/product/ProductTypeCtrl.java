package com.kkwriter.gallery.ctrl.product;

import com.kkwriter.gallery.entity.product.GlyProductType;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.product.IGlyProductTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

/**
 * Created by lisha on 2018/6/8 18:45.
 *
 * @author lisha
 */
@RestController
@RequestMapping(value = "/product-type")
public class ProductTypeCtrl {

    @Resource(name = "glyProductTypeService")
    private IGlyProductTypeService service;

    @GetMapping(value = "/open")
    public ModelAndView openPage() {
        ModelAndView model = new ModelAndView("product_type_manage");
        model.addObject("typeList", service.findAll());
        return model;
    }

    @PostMapping(value = "/save")
    public Result save(Integer productTypeId, Integer parentProductTypeId, String productTypeName, String productTypeValue,
                       Float productTypeOrder, Integer isValid) {
        GlyProductType type = new GlyProductType();
        type.setProductTypeId(productTypeId);
        type.setParentProductTypeId(parentProductTypeId);
        type.setProductTypeName(productTypeName);
        type.setProductTypeValue(productTypeValue);
        type.setIsValid(isValid);
        type.setProductTypeOrder(productTypeOrder);
        service.save(type);
        return ResultUtil.success();
    }

    @PostMapping(value = "delete")
    public Result delete(Integer id) {
        service.delete(id);
        return ResultUtil.success();
    }

}
