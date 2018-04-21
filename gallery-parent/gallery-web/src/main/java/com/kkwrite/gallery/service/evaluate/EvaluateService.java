package com.kkwrite.gallery.service.evaluate;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.evaluate.EvaluateVo;

public interface EvaluateService {

	public int queryEvaluatesCount(int productId) throws ServiceException;
	
	public List<EvaluateVo> queryEvaluates(int productId) throws ServiceException;

	public List<EvaluateVo> queryEvaluatesByPage(int productId, int pageNum, int pageSize) throws ServiceException;
	
}