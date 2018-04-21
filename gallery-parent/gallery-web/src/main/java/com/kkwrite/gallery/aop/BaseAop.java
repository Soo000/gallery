package com.kkwrite.gallery.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public abstract class BaseAop {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 打印方法开始运行信息
	 * @param joinPoint
	 */
	public void printBeginMsg(JoinPoint joinPoint) {
		String classSimpleName = joinPoint.getThis().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		StringBuffer beginMsg = new StringBuffer(" [ begin ] " + classSimpleName + "." + methodName + "()");
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			for (int i = 0; i< args.length; i++) {
				if (i == 1) {
					beginMsg.append(", arg[" + i + "]=" + args[i]);
				} else if (i > 1 && i < args.length - 1) {
					beginMsg.append(" arg[" + i + "]=" + args[i]);
				} else if ( i == args.length - 1) {
					beginMsg.append(".");
				}
			}
		} else {
			beginMsg.append(".");
		}
		
		logger.debug(beginMsg);
	}
	
	/**
	 * 打印方法结束运行信息
	 * @param joinPoint
	 */
	public void printEndMsg(JoinPoint joinPoint) {
		String classSimpleName = joinPoint.getThis().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		StringBuffer beginMsg = new StringBuffer(" [ end ] " + classSimpleName + "." + methodName + "().");
		logger.debug(beginMsg);
	}

	/**
	 * 打印方法运行结果
	 * @param joinPoint
	 * @param result
	 */
	public void printReturnMsg(JoinPoint joinPoint, Object result) {
		String classSimpleName = joinPoint.getThis().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		StringBuffer beginMsg = new StringBuffer(" [ run] " + classSimpleName + "." + methodName + "(), result=" + result);
		logger.debug(beginMsg);
	}
	
	public abstract void beforeMethod(JoinPoint joinPoint);
	
	public abstract void aroundMethod(ProceedingJoinPoint proceedingJoinPoint);
	
	public abstract void afterMethod(JoinPoint joinPoint);
	
	public abstract void afterReturning(JoinPoint joinPoint, Object result);
	
	public abstract void afterThrowing(JoinPoint joinPoint, Exception ex);
	
}
