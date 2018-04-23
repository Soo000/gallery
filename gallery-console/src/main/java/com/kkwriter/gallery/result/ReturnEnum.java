package com.kkwriter.gallery.result;

public enum ReturnEnum {
	SUCCESS(0, "成功！"), 
	UNKNOWN_ERROR(-1001, "未知错误！"),
	DO_NOT_HAVE_DATA(1001, "执行成功！返回数据为空！"),
	PARAM_ERROR(1002, "入参异常！");
	
	private int retCode;
	private String retMsg;

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	ReturnEnum(int retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
}
