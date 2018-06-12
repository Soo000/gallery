package com.kkwriter.gallery.ctrl.product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kkwriter.gallery.entity.json.ModifyProductJsonBean;
import com.kkwriter.gallery.entity.product.GlyProduct;
import com.kkwriter.gallery.entity.product.GlyProductPicture;
import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.entity.product.GlyProductType;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.product.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lisha
 */
@RestController
@RequestMapping("/product")
public class ProductCtrl {

    @Resource(name = "IProductServiceImpl")
    private IProductService productService;

    @GetMapping(value = "/open-choose-page")
    public ModelAndView openChoosePage() {
        return new ModelAndView("product_choose_page");
    }

    @GetMapping(value = "/downloadTemplate")
    public void downloadTemplateFile(HttpServletRequest request, HttpServletResponse response) {
        productService.downloadTemplateFile(request, response);
    }

    @GetMapping(value = "/preImport")
    public ModelAndView preImport() {
        return new ModelAndView("import_product");
    }

    @GetMapping(value = "/getTypeAndAttr/{id}")
    public Result<Map<String, List<Integer>>> getTypeAndAttr(@PathVariable Integer id) {
        Map<String, List<Integer>> data = productService.getProductTypeAndAttrByProductId(id);
        return ResultUtil.success(data);
    }

    @PostMapping("/delete")
    public Result<?> deleteProduct(int productId) {
        productService.deleteProduct(productId);
        return ResultUtil.success();
    }

    @GetMapping("/pre-edit")
    public ModelAndView preEdit() {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit_product");
        return model;
    }

    @GetMapping("/query-product")
    public Result<Page<GlyProduct>> queryProductByPage(@PageableDefault(sort = {"creationTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResultUtil.success(productService.queryProductByPage(pageable));
    }

    @GetMapping("/getProductById")
    public ModelAndView queryById(int productId) {
        // 查询商品信息
        GlyProduct product = productService.getProductInfoById(productId);
        // 查询所有属性
        List<GlyProductProp> allProps = queryAllProps();
        if (allProps == null) {
            allProps = new ArrayList<>();
        }
        // 查询所有类型
        List<GlyProductType> allTypes = queryAllTypes();
        if (allTypes == null) {
            allTypes = new ArrayList<>();
        }
        ModelAndView model = new ModelAndView("modify_product_modal");
        model.addObject("product", product);
        model.addObject("allTypes", allTypes);
        model.addObject("allProps", allProps);
        model.addObject("productId", productId);
        return model;
    }

    @GetMapping(value = "/getProductPicturesByProductId")
    public Result<List<GlyProductPicture>> getProductPicturesByProductId(int productId) {
        // 查询商品配图
        List<GlyProductPicture> pictures = productService.getAllPictures(productId);
        return ResultUtil.success(pictures);
    }

    @PostMapping("/modify")
    public Result<?> modifyProduct(HttpServletRequest request) {
        Gson gson = new Gson();
        Type type = new TypeToken<ModifyProductJsonBean>(){}.getType();
        ModifyProductJsonBean bean = gson.fromJson(request.getParameter("params"), type);
        productService.modifyProduct(bean);
        return ResultUtil.success();
    }

    @GetMapping("pre-add")
    public ModelAndView preAdd() {
        ModelAndView model = new ModelAndView("add_product");
        // 查询所有属性
        List<GlyProductProp> allProps = queryAllProps();
        if (allProps == null) {
            allProps = new ArrayList<>();
        }
        model.addObject("allProps", allProps);
        // 查询所有类型
        List<GlyProductType> allTypes = queryAllTypes();
        if (allTypes == null) {
            allTypes = new ArrayList<>();
        }
        model.addObject("allTypes", allTypes);
        return model;
    }

    @PostMapping("/add")
    public Result<?> add(MultipartFile[] productPics, HttpServletRequest request) {
        productService.addProduct(productPics, request);
        return ResultUtil.success();
    }

    @PostMapping("/upload")
    public Result<?> importProduct(MultipartFile file) {
        productService.uploadProductFile(file);
        return ResultUtil.success();
    }

    private List<GlyProductProp> queryAllProps() {
        return productService.queryAllProps();
    }

    private List<GlyProductType> queryAllTypes() {
        return productService.queryAllTypes();
    }

}
