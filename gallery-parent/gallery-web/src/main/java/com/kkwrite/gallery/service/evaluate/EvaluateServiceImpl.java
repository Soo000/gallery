package com.kkwrite.gallery.service.evaluate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.evaluate.EvaluateMapper;
import com.kkwrite.gallery.pojo.evaluate.EvaluateVo;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private EvaluateMapper evaluateMapper;
	
	@Override
	public int queryEvaluatesCount(int productId) throws ServiceException {
		logger.debug("[ begin ] EvaluateServiceImpl.queryEvaluatesCount(), productId = " + productId);
		
		int evaluatesCount = evaluateMapper.selectEvaluatesCountByProductId(productId);
		logger.debug("[ run ] EvaluateServiceImpl.queryEvaluatesCount(), evaluatesCount = " + evaluatesCount);
		
		logger.debug("[ end ] EvaluateServiceImpl.queryEvaluatesCount().");
		return evaluatesCount;
	}
	
	@Override
	public List<EvaluateVo> queryEvaluates(int productId) throws ServiceException {
		logger.debug("[ begin ] EvaluateServiceImpl.queryEvaluates(), productId = " + productId);
		
		List<EvaluateVo> evaluates = evaluateMapper.selectEvaluatesByProductId(productId);
		logger.debug("[ run ] EvaluateServiceImpl.queryEvaluates(), evaluates = " + evaluates);
		
		logger.debug("[ end ] EvaluateServiceImpl.queryEvaluates().");
		return evaluates;
	}

	@Override
	public List<EvaluateVo> queryEvaluatesByPage(int productId, int pageNum, int pageSize) throws ServiceException {
		logger.debug("[ begin ] EvaluateServiceImpl.queryEvaluatesByPage(), productId = " + productId 
				+ ", pageNum=" + pageNum + ", pageSize=" + pageSize);
		
		Map<String, Integer> param = new HashMap<>();
		param.put("begin", (pageNum - 1) * pageSize);
		param.put("count", pageSize);
		param.put("productId", productId);
		
		List<EvaluateVo> evaluates = evaluateMapper.selectEvaluatesByProductIdAndPage(param);
		
		logger.debug("[ end ] EvaluateServiceImpl.queryEvaluatesByPage().");
		return evaluates;
	}

}
