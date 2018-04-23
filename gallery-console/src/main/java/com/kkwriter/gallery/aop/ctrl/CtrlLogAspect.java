package com.kkwriter.gallery.aop.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class CtrlLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(CtrlLogAspect.class);

	@Pointcut("execution(public * com.kkwriter.gallery.ctrl..*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = attributes.getRequest();
		logger.info("收到{}请求，转发至controller：{}，入参为{}", req.getMethod(),
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
				joinPoint.getArgs());
	}

	@AfterReturning(returning = "obj", pointcut = "log()")
	public void doAfterReturning(Object obj) {
		logger.info("controller返回，response={}", obj);
	}

}
