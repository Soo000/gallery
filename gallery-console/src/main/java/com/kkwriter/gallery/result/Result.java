package com.kkwriter.gallery.result;

import java.io.Serializable;

/**
 * 用于包装返回值
 * @param <T> 返回所携带的数据类型
 * @author lisha
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = -3552451381406005998L;
	private int code;
	private String msg;
	private T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
