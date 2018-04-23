package com.kkwriter.gallery.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.result.ReturnEnum;

@ControllerAdvice
public class ExceptionHandle {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result<Object> handle(Exception e) {
		if (e instanceof GalleyConsoleException) {
			GalleyConsoleException girlException = (GalleyConsoleException) e;
			return ResultUtil.error(girlException.getRet());
		} else {
			logger.error(e.getMessage(), e);
			return ResultUtil.error(ReturnEnum.UNKNOWN_ERROR);
		}
	}
	
}
