package com.kkwriter.gallery.aop.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lisha
 */
@Aspect
@Component
public class ServiceLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

	@Pointcut("execution(public * com.kkwriter.gallery.service..*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		logger.info("service：{} 开始执行，入参为：{}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
				joinPoint.getArgs());
	}

	@AfterReturning(returning = "obj", pointcut = "log()")
	public void doAfterReturning(Object obj) {
		logger.info("service返回：{}", obj);
	}
}
