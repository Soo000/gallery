package com.kkwrite.gallery.ctrl.prodetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkwrite.gallery.ctrl.BaseCtrl;
import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.evaluate.EvaluateVo;
import com.kkwrite.gallery.pojo.product.GlyProductProp;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.service.evaluate.EvaluateService;
import com.kkwrite.gallery.service.product.ProductService;

@Controller
@RequestMapping("/prodetailsctrl")
public class ProdetailsCtrl extends BaseCtrl {
	
	private Logger logger = Logger.getLogger(ProdetailsCtrl.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	@RequestMapping("/pagectrl")
	public ModelAndView pageCtrl(int productId, String type) {
		logger.debug("[ begin ] ProdetailsCtrl.pageCtrl(). productId=" + productId);
		
		ModelAndView modelAndView = new ModelAndView();
		if (type != null && type.equals("comments")) {
			modelAndView.setViewName("/prodetails/comments");
		} else {
			modelAndView.setViewName("/prodetails/prodetails");
		}
		
		try {
			// 查询产品信息
			Product product = productService.queryProduct(productId);
			modelAndView.addObject("product", product);
			
			// 产品属性
			List<GlyProductProp> productProps = product.getProductProps();
			if (productProps != null && productProps.size() > 0) {
				Map<String, List<GlyProductProp>> propGroups = productService.groupProductProps(productProps);
				modelAndView.addObject("propGroups", propGroups);
			}
		} catch (ServiceException e) {
			logger.error("ProdetailsCtrl.pageCtrl(), 根据产品id productId=" + productId + " 查询产品信息出错！");
			e.printStackTrace();
		}
		
		try {
			// 查询评价信息数量
			int evaluatesCount = evaluateService.queryEvaluatesCount(productId);
			modelAndView.addObject("evaluatesCount", evaluatesCount);
			
			// 查询评价信息，最多取2条
			if (type == null || !type.equals("comments")) {
				List<EvaluateVo> evaluates = evaluateService.queryEvaluatesByPage(productId, 1, 2);
				modelAndView.addObject("evaluates", evaluates);
			}
			
		} catch (ServiceException e) {
			logger.error("ProdetailsCtrl.pageCtrl(), 查询产品评价出错！");
			e.printStackTrace();
		}
		
		logger.debug("[ end ] ProdetailsCtrl.pageCtrl().");
		return modelAndView;
	}
	
	@RequestMapping("/morevaluates")
	@ResponseBody
	public Map<String, Object> moreEvaluates(int productId, int pageNum, int pageSize) {
		logger.debug("[ begin ] ProdetailsCtrl.moreEvaluates(). productId=" + productId 
				+ ", pageNum=" + pageNum + ", pageSize=" +pageSize);
		
		Map<String, Object> retMap = new HashMap<>();
		
		try {
			List<EvaluateVo> evaluates = evaluateService.queryEvaluatesByPage(productId, pageNum, pageSize);
			if (evaluates != null && evaluates.size() > 0) {
				retMap.put("retCode", "0");
				retMap.put("retMsg", "成功！");
				retMap.put("data", evaluates);
			} else {
				retMap.put("retCode", "-1");
				retMap.put("retMsg", "未查询到评价信息！");
			}
		} catch (ServiceException e) {
			logger.error("ProdetailsCtrl#moreEvaluates(), 查询评价信息出错！" + e.getMessage(), e);
			retMap.put("retCode", "-2");
			retMap.put("retMsg", "服务异常！" + e.toString());
		}
		
		logger.debug("[ end ] ProdetailsCtrl.moreEvaluates().");
		return retMap;
	}

}
