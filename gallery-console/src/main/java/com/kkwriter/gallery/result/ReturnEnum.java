package com.kkwriter.gallery.result;

/**
 *
 * @author lisha
 */
public enum ReturnEnum {
	/**
	 * 成功返回
	 */
	SUCCESS(0, "成功！"),
	/**
	 * 返回错误信息通用
	 */
	ERROR_MESSAGE(-1, ""),
	/**
	 * 未知错误返回
	 */
	UNKNOWN_ERROR(-1001, "未知错误！"),
	/**
	 * 执行未出现异常但查询数据为空
	 */
	DO_NOT_HAVE_DATA(1001, "执行成功！返回数据为空！"),
	/**
	 * 入参不合法
	 */
	PARAM_ERROR(1002, "入参异常！"),
	/**
	 * 更新失败
	 */
	UPDATE_FAILED(1003, "更新数据失败！");
	
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

	public static ReturnEnum formErrorMessage(String errorMessage) {
		ERROR_MESSAGE.setRetMsg(errorMessage);
		return ERROR_MESSAGE;
	}
}
