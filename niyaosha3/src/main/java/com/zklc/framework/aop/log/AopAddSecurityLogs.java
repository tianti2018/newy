package com.zklc.framework.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.zklc.framework.log.LoggerWritter;

/**
 * 
 * <p>
 * 功能 系统操作日志切面
 * </p>
 * <p>
 * Copyright 北京中科联城软件有限公司 2012 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2012-9-5 下午3:00:40
 * @version 1.0 </br> 最后修改人 无
 */
public class AopAddSecurityLogs {
	
	private void beforeAddLog(JoinPoint joinPoint) {
		LoggerWritter.info("执行"+joinPoint.getTarget().getClass().getName()+"的"+joinPoint.getSignature().getName()+"方法开始", joinPoint.getTarget().getClass());
	}	
	private Object  aroundAddLog(ProceedingJoinPoint  joinPoint) throws Throwable {
		Class clazz = joinPoint.getTarget().getClass();
		LoggerWritter.info("执行"+joinPoint.getTarget().getClass().getName()+"的"+joinPoint.getSignature().getName()+"方法", joinPoint.getTarget().getClass());
		Object retVal = null;
		retVal = joinPoint.proceed();
		return retVal;
	}
	private void afterAddLog(JoinPoint joinPoint) {
		LoggerWritter.info("执行"+joinPoint.getTarget().getClass().getName()+"的"+joinPoint.getSignature().getName()+"方法结束", joinPoint.getTarget().getClass());
	}	
}