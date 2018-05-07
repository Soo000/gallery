package com.kkwriter.gallery.exception;

import com.kkwriter.gallery.result.ReturnEnum;

/**
 *
 * @author lisha
 */
public class GalleyConsoleException extends RuntimeException {

	private static final long serialVersionUID = 8618629825736071623L;
	private ReturnEnum ret;
	
	public ReturnEnum getRet() {
		return ret;
	}

	public void setRet(ReturnEnum ret) {
		this.ret = ret;
	}

	public GalleyConsoleException(ReturnEnum ret, Throwable cause) {
		super(ret.getRetMsg(), cause);
		this.ret = ret;
	}
	
	public GalleyConsoleException(ReturnEnum ret) {
		super(ret.getRetMsg());
		this.ret = ret;
	}
	
}
