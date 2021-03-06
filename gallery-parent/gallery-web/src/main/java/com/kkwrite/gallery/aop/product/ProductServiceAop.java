package com.kkwrite.gallery.aop.product;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.kkwrite.gallery.aop.MethodRunLogAop;

@Component
@Aspect
public class ProductServiceAop extends MethodRunLogAop {
	
	//private Logger logger = Logger.getLogger(this.getClass());

	@Override
	@Before("execution(* com.kkwrite.gallery.service.product.ProductService.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		super.beforeMethod(joinPoint);
	}
	
	/*@Override
	@AfterReturning("execution(* com.kkwrite.gallery.service.product.ProductService.*(..))")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		super.printReturnMsg(joinPoint, result);
	}*/
	
	@Override
	@After("execution(* com.kkwrite.gallery.service.product.ProductService.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		super.afterMethod(joinPoint);
	}

}
