package com.kkwrite.gallery.aop.address;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.kkwrite.gallery.aop.MethodRunLogAop;

@Aspect
public class AddressServiceAop extends MethodRunLogAop {
	
	@Pointcut("execution(public * com.kkwrite.gallery.service.address.AddressService.*(..))")
	public void log() {
	}
	
	@Override
	@Before("log()")
	public void beforeMethod(JoinPoint joinPoint) {
		super.beforeMethod(joinPoint);
	}
	
	@Override
	@After("log()")
	public void afterMethod(JoinPoint joinPoint) {
		super.afterMethod(joinPoint);
	}
}
