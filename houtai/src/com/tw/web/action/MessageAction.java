package com.tw.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.MessageDAO;
import com.tw.web.dao.RoleDAO;
import com.tw.web.dao.UserDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Function;
import com.tw.web.hibernate.persistent.Message;
import com.tw.web.hibernate.persistent.Role;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.service.LoginService;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAdd", 			value="/WEB-INF/jsp/other/addMessage.jsp"),
			@Result(name="initModify", 		value="/WEB-INF/jsp/other/addMessage.jsp"),
			@Result(name="initReply", 		value="/WEB-INF/jsp/other/addReply.jsp"),
			
			@Result(name="create", 				value="/WEB-INF/jsp/other/addMessage.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/other/messageList.jsp"),
			@Result(name="listAll1", 			value="/WEB-INF/jsp/other/messageList1.jsp"),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="message", params = {"method", "listAll"}),
			@Result(name="goBackList1", type=ActionChainResult.class, 	value="message", params = {"method", "listAll1"})
		}
)
public class MessageAction extends ExtJSONActionSuport {
	
	private MessageDAO messageDAO;
	private UserDAO userDAO;
	private Integer messageId;
	private Integer fromUserId;
	private Integer toUserId;
	private String contents;
	private String title;
	private String replyContents;

	
	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}
	@Autowired
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}


	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	//会员操作
	public String listAll () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("fromUserId", loginUser.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("fromUserId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		Integer count = messageDAO.cout_size(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<Message> litPager = messageDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAll";
	}
	
	//后台管理员操作
	public String listAll1 () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("fromUserId", fromUserId);
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("fromUserId", 0);
		
		if (null==fromUserId || 0==fromUserId) {
			conditionProperties = null;
			compare = null;
		}
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		Integer count = messageDAO.cout_size(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<Message> litPager = messageDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAll1";
	}
	
	public String initAdd () throws Exception {
		
		return "initAdd";
	}
	
	
	public String initReply () throws Exception {
		Message message = (Message)messageDAO.findById(messageId);
		if (null == message) { 
			ServletActionContext.getRequest().setAttribute("message", "此留言不存在");
			return "goBackList";
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", message);
			return "initReply";
		}
	}
	
	public String initModify () throws Exception {
		Message message = (Message)messageDAO.findById(messageId);
		if (null == message) { 
			ServletActionContext.getRequest().setAttribute("message", "此留言不存在");
			
			return "goBackList";
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", message);
			return "initModify";
		}
	}
	
	public String create() throws Exception {
		Message message = new Message ();
		User formUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		message.setFromUser(formUser);
		message.setCreateDate(new Date());
		message.setContents(contents);
		message.setTitle(title);
		messageDAO.create(message);
		ServletActionContext.getRequest().setAttribute("messages", " 新增成功 ");
		return "create";
	}
	
	public String delete() throws Exception {
		
		Message message = (Message)messageDAO.findById(messageId);
		if (message != null) {
			messageDAO.delete(message);
			ServletActionContext.getRequest().setAttribute("message", "删除成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此留言不存在");
		}
		
		return "goBackList";
	}
	
	public String reply() {
		Message message = (Message)messageDAO.findById(messageId);
		AdminUser replyUser = (AdminUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		if (message != null) {
			message.setReplyContents(replyContents);
			message.setReplyDate(new Date());
			message.setToUser(replyUser);
			
			messageDAO.update(message);
			ServletActionContext.getRequest().setAttribute("message", " 回复成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此留言不存在");
		}
		
		return "goBackList1";
	}
	
	public String update() throws Exception  {
		Message message = (Message)messageDAO.findById(messageId);
		if (message != null) {
			message.setContents(contents);
			message.setTitle(title);
			
			messageDAO.update(message);
			ServletActionContext.getRequest().setAttribute("message", " 修改成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此留言不存在");
		}
		
		return "goBackList";
	}
}
