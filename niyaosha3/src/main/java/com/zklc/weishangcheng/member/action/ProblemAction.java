package com.zklc.weishangcheng.member.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.Problem;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.service.ProblemService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/user")
@Action(value = "problemAction")
@Results({
	@Result(name = "goProblePage", location = "/WEB-INF/jsp/problem_add.jsp"),
	@Result(name = "saveOrUpdate", location = "/WEB-INF/jsp/list_dian_hongbao.jsp"),
	@Result(name = "problemList", location = "/WEB-INF/jsp/problem_list.jsp"),
	@Result(name = "delProblem", type = "redirect", location = "/user/problemAction!problemList.action" ),
	@Result(name = "meiyouzige", location = "/WEB-INF/jsp/meiyouzige.jsp"),
	
	@Result(name = "ajaxResult", type = "json", params = { "message",
	"message" }),
	@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
	
})
public class ProblemAction extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private UseryService useryService;
	
	private Users user;
	
	public String code;
	public String wxOpenid;
	
	public String message;// 回显信息
	
	private Integer userId;
	private Integer problemId;
	private Problem problem;
	
	/**
	 *跳转到问题新增页面
	 * @return
	 */
	public String goProblePage(){
		if(problemId!=null){
			request.setAttribute("problem", problemService.findById(problemId));
		}
		return "goProblePage";
	}
	public String delProblem(){
		if(problemId!=null){
			Problem pro = problemService.findById(problemId);
			problemService.delete(pro);
		}
		return "delProblem";
	}
	
	/**
	 *  保存或修改问题
	 * @return
	 */
	public String saveOrUpdate(){
		userVo = getSessionUser();
		user = userVo.getUser();
		if(user == null){
			return "error";
		}
		if(problem != null){
			if(problem.getId()!=null){
				problemService.update(problem);
			}else{
				problem.setDescribez(problem.getDescribez().trim());
				problem.setCreateDate(new Date());
				problem.setCreateUserId(user.getUserId());
				problem.setStatus(0);
				problemService.save(problem);
				
			}
		}
		
		return "saveOrUpdate";
	}

	public String problemList(){
		userVo = getSessionUser();
		user = userVo.getUser();
		if(user == null){
			return "error";
		}
		List weiList = problemService.findByHql("from Problem p where p.status in(0) and p.createUserId="+user.getUserId(),null);
		List yiList = problemService.findByHql("from Problem p where p.status in(1,2) and p.createUserId="+user.getUserId(), null);
		if(weiList.size() >0){
			request.setAttribute("weiList", weiList);
		}
		if(yiList.size() >0){
			request.setAttribute("yiList", yiList);
		}
		return "problemList";
	}
	
	
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getWxOpenid() {
		return wxOpenid;
	}


	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}


	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	/**
	 * @return the problem
	 */
	public Problem getProblem() {
		return problem;
	}

	/**
	 * @param problem the problem to set
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

}
