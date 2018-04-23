package com.kkwriter.gallery.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	
	String value() default "";
	
	LevelEnum level() default LevelEnum.DEBUG;
	
	enum LevelEnum {
		ERROR, WARN, INFO, DEBUG
	}
	
}
