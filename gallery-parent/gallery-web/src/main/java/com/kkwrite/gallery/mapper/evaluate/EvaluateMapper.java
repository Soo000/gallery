package com.kkwrite.gallery.mapper.evaluate;

import java.util.List;
import java.util.Map;

import com.kkwrite.gallery.pojo.evaluate.EvaluateVo;

public interface EvaluateMapper {

	int selectEvaluatesCountByProductId(int productId);
	
	List<EvaluateVo> selectEvaluatesByProductId(int productId);

	List<EvaluateVo> selectEvaluatesByProductIdAndPage(Map<String, Integer> param);
	
}
