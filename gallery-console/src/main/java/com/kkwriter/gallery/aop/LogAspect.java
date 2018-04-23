package com.kkwriter.gallery.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kkwriter.gallery.annotation.Log;
import com.kkwriter.gallery.annotation.Log.LevelEnum;

@Aspect
@Component
public class LogAspect {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(com.kkwriter.gallery.annotation.Log)")
	public void logPointCut() {
	}
	
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 打印日志消息
        printLog(point, time);
        return result;
	}

	private void printLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Log syslog = method.getAnnotation(Log.class);
		if (syslog != null) {
			// 注解上的描述
			String logMsg = syslog.value();
			LevelEnum level = syslog.level();
			switch (level) {
			case ERROR:
				logger.error(logMsg);
				break;
			case WARN:
				logger.warn(logMsg);
				break;
			case INFO:
				logger.info(logMsg);
				break;
			case DEBUG:
				logger.debug(logMsg);
				break;
			}
		}
	}
	
}
