package com.zklc.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * 
 * @author YaoJuxian
 * 
 */
public class LoginFilter  implements Filter {

	private static final long serialVersionUID = 4349384506115395269L;
	
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {

		// 获取request和response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("P3P","CP=CAO PSA OUR");
		// 获取访问的uri地址
		String uri = request.getServletPath();
		// 如果uri不存在,设置uri默认为根路径 /
		if (uri == null || "".equals(uri.trim())) {
			uri = "/";
		}
		// 获取session1
		HttpSession session1 = request.getSession();
		// 过滤以/开始的所有的访问路径,排除掉/login的路径,检查是否存在username属性
		if (uri.startsWith("/") && !uri.startsWith("/login.jsp") && !uri.startsWith("/Login")
				) {
			
			String username  = (String) session1.getAttribute("guid");
			if (username == null || "".equals(username.trim())) {
				response.sendRedirect((request.getContextPath() + "/login.jsp"));
				return;
			}
		}
		filterChain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
