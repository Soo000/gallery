package com.kkwriter.gallery.ctrl.product;

import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.product.ProductPropService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lisha on 2018/6/7 17:53.
 *
 * @author lisha
 */
@RestController
@RequestMapping(value = "/product-prop")
public class ProductPropCtrl {

    @Resource(name = "productPropService")
    private ProductPropService propService;

    @GetMapping(value = "/open")
    public ModelAndView openPage() {
        ModelAndView model = new ModelAndView("product_prop_manage");
        model.addObject("propList", propService.findAll());
        return model;
    }

    @PostMapping(value = "/save")
    public Result save(Integer propId, String propName, String propType, String propValue, Integer valid) {
        GlyProductProp prop = new GlyProductProp();
        prop.setPropId(propId);
        prop.setPropName(propName);
        prop.setPropType(propType);
        prop.setPropValue(propValue);
        prop.setIsValid(valid);
        propService.save(prop);
        return ResultUtil.success();
    }

    @PostMapping(value = "delete")
    public Result delete(Integer id) {
        propService.delete(id);
        return ResultUtil.success();
    }

}
