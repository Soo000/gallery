package com.kkwrite.gallery.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MethodRunLogAop extends BaseAop {

	@Override
	public void beforeMethod(JoinPoint joinPoint) {
		printBeginMsg(joinPoint);
	}

	@Override
	public void aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
		
	}

	@Override
	public void afterMethod(JoinPoint joinPoint) {
		printEndMsg(joinPoint);
	}

	@Override
	public void afterReturning(JoinPoint joinPoint, Object result) {
		printReturnMsg(joinPoint, result);
	}

	@Override
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
	}

}
