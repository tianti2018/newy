package com.zklc.framework.aop.exception;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

import com.zklc.framework.exception.DbAppRunTimeException;
import com.zklc.framework.log.LoggerFactory;
import com.zklc.framework.log.SystemLogger;

public class DaoExceptionClass implements ThrowsAdvice {

    /**
     * 参数解释 Method method 执行的方法 Object[] args 方法参数 Object target 代理的目标对象
     * Throwable throwable 产生的异常
     * 
     * @throws AppException
     */

    public void afterThrowing(Method method, Object[] args, Object target, DbAppRunTimeException throwable){

        SystemLogger logger = LoggerFactory.getSystemLogger(target.getClass());
        logger.error(throwable.getMessage(), throwable);

        // System.out.println("产生异常的方法名称： " + method.getName());
        // for(Object o:args){
        // System.out.println("方法的参数：   " + o.toString());
        // }
        // System.out.println("代理对象：   " + target.getClass().getName());
        // System.out.println("抛出的异常:    " + throwable.getMessage()+">>>>>>>"
        // + throwable.getCause());
        // System.out.println("异常详细信息：　　　"+throwable.fillInStackTrace());

        // 异常日志记录

    }
}
