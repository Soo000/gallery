package com.kkwriter.gallery.result;

public class ResultUtil {
	
	public static <T> Result<T> success(T t) {
		Result<T> result = new Result<>();
		result.setCode(ReturnEnum.SUCCESS.getRetCode());
		result.setMsg(ReturnEnum.SUCCESS.getRetMsg());
		result.setData(t);
		return result;
	}
	
	public static <T> Result<T> success() {
		return success(null);
	}
	
	public static <T> Result<T> error(ReturnEnum ret) {
		Result<T> result = new Result<>();
		result.setCode(ret.getRetCode());
		result.setMsg(ret.getRetMsg());
		return result;
	}
	
	public static <T> Result<T> error(ReturnEnum ret, T t) {
		Result<T> result = new Result<>();
		result.setCode(ret.getRetCode());
		result.setMsg(ret.getRetMsg());
		result.setData(t);
		return result;
	}
	
}
