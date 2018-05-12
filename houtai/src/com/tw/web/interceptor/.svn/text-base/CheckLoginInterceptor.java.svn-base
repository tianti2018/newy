package com.tw.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.User;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -8102958534076552631L;

	public static final String USER_SESSION_KEY = "wallet.session.user";

	public static String URL = "";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		User user = null;
		AdminUser adminUser = null;
		if (obj instanceof User) {
			user = (User) obj;
		}
		if (obj instanceof AdminUser) {
			adminUser = (AdminUser) obj;
		}

		String url = request.getRequestURI();

		if (StringUtils.contains(url.toString(), "doLogin.action")) {
			URL = "doLogin";
		}
		if (StringUtils.contains(url.toString(), "doAdminLogin.action")) {
			URL = "doAdminLogin";
		}

		System.out.println(" >>>>>>>>>>>>>>>>>>> url " + url);

		if (request.getSession() != null && (user != null || adminUser != null)) {
			return invocation.invoke();
		} else {
			if(StringUtils.contains(url.toString(), "createUser.action")
			|| StringUtils.contains(url.toString(), "rand.action")
			|| StringUtils.contains(url.toString(), "doLogin.action")
			|| StringUtils.contains(url.toString(), "egg!indexWinPrizeList.action")
			|| StringUtils.contains(url.toString(), "egg!webCreateQrcode.action")
			|| StringUtils.contains(url.toString(), "initAddUser1.action")
			|| StringUtils.contains(url.toString(),	"doAdminLogin.action")
			|| StringUtils.contains(url.toString(),	"initAddUser.action")) {
				return invocation.invoke();
			}
			if ("doAdminLogin".equals(URL)) {
				return "login";
			} else {
				return "login";
			}

		}

	}

}
