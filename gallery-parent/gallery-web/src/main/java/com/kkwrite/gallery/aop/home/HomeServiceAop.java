package com.kkwrite.gallery.aop.home;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.kkwrite.gallery.aop.MethodRunLogAop;

@Aspect
public class HomeServiceAop extends MethodRunLogAop {
	
	//private Logger logger = Logger.getLogger(this.getClass());

	@Override
	@Before("execution(* com.kkwrite.gallery.service.home.HomeService.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		super.beforeMethod(joinPoint);
	}
	
	/*@Override
	@AfterReturning("execution(* com.kkwrite.gallery.service.home.HomeService.*(..))")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		super.printReturnMsg(joinPoint, result);
	}*/
	
	@Override
	@After("execution(* com.kkwrite.gallery.service.home.HomeService.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		super.afterMethod(joinPoint);
	}

}
