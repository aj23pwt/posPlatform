package com.greencloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface LogInfo {
	/**
	 * 日志类型
	 * @return
	 */
	LogType logType();
	/**
	 * 描述
	 * @return
	 */
	String operationContent();
}
