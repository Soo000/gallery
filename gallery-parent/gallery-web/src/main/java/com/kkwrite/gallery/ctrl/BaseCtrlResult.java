package com.kkwrite.gallery.ctrl;

public class BaseCtrlResult {
	
	public static final String DEFAULT_RETCODE_KEY = "retCode";
	public static final String DEFAULT_RETMSG_KEY = "retMsg";
	
	public static final String DEFAULT_SUCCESS_CODE = "0000";
	public static final String DEFAULT_SUCCESS_MSG = "success";

	private String retCode;
	private String retMsg;
	
	public BaseCtrlResult() {
		this.retCode = DEFAULT_SUCCESS_CODE;
		this.retMsg = DEFAULT_SUCCESS_MSG;
	}
	
	public BaseCtrlResult(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
}
