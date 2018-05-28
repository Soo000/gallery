package com.kkwriter.gallery.result;

/**
 * Result工具类，用于快速创建Result对象
 * @author lisha
 */
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
