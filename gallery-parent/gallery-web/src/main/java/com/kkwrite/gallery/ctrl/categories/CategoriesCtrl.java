package com.kkwrite.gallery.ctrl.categories;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.Pagination;
import com.kkwrite.gallery.pojo.product.GlyProductType;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.service.product.ProductService;
import com.kkwrite.gallery.service.product.ProductTypeService;

@Controller
@RequestMapping("/categoriesctrl")
public class CategoriesCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(CategoriesCtrl.class);
	
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl(int[] productTypeId) {
		logger.debug("[ begin ] CategoriesCtrl.pageCtrl(), productTypeId=" + productTypeId);
		
		ModelAndView modelAndView = new ModelAndView("/categories/categories");
		
		// 查询风格分类
		try {
			List<GlyProductType> styleTypeItems = qryProductTypeItems(BasePojo.ProductType.PARENT_PRODUCT_TYPE_STYLE);
			modelAndView.addObject("styleTypeItems", styleTypeItems);
		} catch (ServiceException e) {
			logger.error("[ run ] CategoriesCtrl.pageCtrl(), 查询风格分类出错");
			e.printStackTrace();
		}
		
		// 查询空间分类
		try {
			List<GlyProductType> spaceTypeItems = qryProductTypeItems(BasePojo.ProductType.PARENT_PRODUCT_TYPE_SPACE);
			modelAndView.addObject("spaceTypeItems", spaceTypeItems);
		} catch (ServiceException e) {
			logger.error("[ run ] CategoriesCtrl.pageCtrl(), 查询空间分类出错");
			e.printStackTrace();
		}
		
		// 特色
		try {
			List<GlyProductType> featureTypeItems = qryProductTypeItems(BasePojo.ProductType.PARENT_PRODUCT_TYPE_FEATURE);
			modelAndView.addObject("featureTypeItems", featureTypeItems);
		} catch (ServiceException e) {
			logger.error("[ run ] CategoriesCtrl.pageCtrl(), 查询特色分类出错");
			e.printStackTrace();
		}
		
		// 内容
		try {
			// 查询已选择产品分类
			List<GlyProductType> selectedProductTypes = new ArrayList<GlyProductType>();
			if (productTypeId != null && productTypeId.length > 0) {
				selectedProductTypes = productTypeService.queryProductType(productTypeId);
				modelAndView.addObject("selectedProductTypes", selectedProductTypes);
			}
			
			// 查询产品信息
			Pagination pagination = new Pagination();
			List<Product> products = qryProducts(selectedProductTypes, pagination);
			modelAndView.addObject("products", products);
		} catch (ServiceException e) {
			logger.error("[ run ] CategoriesCtrl.pageCtrl(), 查询空间分类出错");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] CategoriesCtrl.pageCtrl().");
		return modelAndView;
	}

	/**
	 * 根据 parentProductTypeId 查询产品分类项目
	 * @param parentProductTypeId
	 * @return
	 */
	private List<GlyProductType> qryProductTypeItems(Integer parentProductTypeId) throws ServiceException {
		return productTypeService.queryProductTypeByParentId(parentProductTypeId);
	}
	
	/**
	 * 查询产品信息
	 * @param productTypes
	 * @return
	 */
	private List<Product> qryProducts(List<GlyProductType> productTypes, Pagination pagination) throws ServiceException {
		return productService.queryProductPagination(productTypes, pagination);
	}
}
