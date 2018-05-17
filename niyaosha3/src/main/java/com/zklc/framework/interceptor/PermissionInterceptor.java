package com.zklc.framework.interceptor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 限制没有登录的用户进入访问页面(拦截器demo)
 * 
 * @author zhusg
 *
 */
@SuppressWarnings("serial")
public class PermissionInterceptor  implements Interceptor {
    public void destroy() {
    }
    public void init() {
    }
    public String intercept(ActionInvocation invocation) throws Exception {
        Object user = ActionContext.getContext().getSession().get("user");
        // 如果user不为null,代表用户已经登录,允许执行action中的方法
        if (user != null){
            return invocation.invoke(); 
        }
        ActionContext.getContext().put("message", "你没有权限执行该操作");
        return "success";
    }
}