package com.kkwriter.gallery.ctrl.product;

import com.kkwriter.gallery.entity.product.GlyProduct;
import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.entity.product.GlyProductType;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCtrl {
	
	@Resource(name = "productServiceImpl")
	private ProductService productService;
	
	@PostMapping("/delete")
	public Result<?> deleteProduct(int productId) {
		
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
		ModelAndView model = new ModelAndView("product_detail");

		return model;
	}

	@PostMapping("/modify")
	public Result<?> modifyProduct() {
		
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
