package com.kkwrite.gallery.service.param;

import java.util.List;

import com.kkwrite.gallery.pojo.param.GlyParam;

public interface ParamService {

	public GlyParam getParam(String key);
	
	public String getParamValue(String key);
	
	public List<GlyParam> getParams(String parentParamId);
	
	public boolean setParam(String key, String value);
	
}