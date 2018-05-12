package com.zklc.weishangcheng.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.service.CoreService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.util.SignUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/core")
@Action(value = "coreAction")
public class CoreAction extends BaseAction {
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	public String execute() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		PrintWriter out = null;
		
		if (request.getMethod().toLowerCase().equals("get")) {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			try {
				out = response.getWriter();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
			System.out.println(">>>>>>>>>>>>>>>>>>  1111111111111111122222222222222");
			System.out.println(">>>>>>>>>>>>>>>>>>  33333333333");

			System.out.println(">>>>>>>>>>>>>>>>>>  4444444444444444");

			System.out.println(">>>>>>>>>>>>>>>>>>  5555555555555555");

			out.close();
			out = null;
		} 
		else if(request.getMethod().toLowerCase().equals("post")) {
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			try {
				response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				out = response.getWriter();
				out.print("");
				out.flush();
				
				// 调用核心业务类接收消息、处理消息
				String respMessage = coreService.processRequest(request);
				out = response.getWriter();
				out.print(respMessage);
				out.flush();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (out != null)
					out.close();
			}
		}
		return null;
	}
	
	public String testInit(){
		return null;
	}

}
