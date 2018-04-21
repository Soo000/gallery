package com.kkwrite.gallery.service.param;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.mapper.param.GlyParamMapper;
import com.kkwrite.gallery.pojo.param.GlyParam;

@Service("paramService")
public class ParamServiceImpl implements ParamService {
	
	@Autowired
	private GlyParamMapper glyParamMapper;

	@Override
	public GlyParam getParam(String key) {
		return glyParamMapper.selectByParamKey(key);
	}
	
	@Override
	public String getParamValue(String key) {
		GlyParam glyParam = glyParamMapper.selectByParamKey(key);
		if (glyParam != null) {
			return glyParam.getParamValue();
		}
		return null;
	}

	@Override
	public List<GlyParam> getParams(String parentParamId) {
		return null;
	}

	@Override
	public boolean setParam(String key, String value) {
		GlyParam glyParam = new GlyParam();
		glyParam.setParamKey(key);
		glyParam.setParamValue(value);
		int result = glyParamMapper.updateByParamKeySelective(glyParam);
		if (result >= 0) {
			return true;
		}
		return false;
	}

}
