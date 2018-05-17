package com.zklc.framework.util;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.zklc.framework.hibernate.persistent.UserBO;

/**
 * <p>
 * 功能：得到SESSION中的用户 存入log4j的MDC对象中  用于打印日志显示用户信息
 * </p>
 * <p>
 * Copyright 北京海辉高科软件有限公司 2012 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2012-6-20 上午10:42:24
 * @version 1.0 </br> 最后修改人 无
 */

public class GetUserFilter implements Filter {

    // 定义默认用户姓名
    private final static String DEFAULT_USER = "guest";


    public void destroy(){

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        
        UserBO user = null;
        
        // 如果用户信息存在
        if(session!=null && (user = (UserBO)session.getAttribute("user")) != null){
            MDC.put("id", user.getId());
            MDC.put("name", user.getName());
            MDC.put("username", user.getUsername());
        }else{
            MDC.put("id", new Random().nextInt(1000));
            MDC.put("name", DEFAULT_USER);
            MDC.put("username", DEFAULT_USER);
        }
        
        chain.doFilter(request, response);
    }


    public void init(FilterConfig Config) throws ServletException{

    }
}