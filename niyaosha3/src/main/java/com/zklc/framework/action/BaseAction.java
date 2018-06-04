package com.zklc.framework.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.zklc.framework.hibernate.persistent.Pager;
import com.zklc.framework.log.LoggerFactory;
import com.zklc.framework.log.SystemLogger;
import com.zklc.framework.service.PagerService;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.util.UserInfoUtil;
import com.zklc.weixin.util.WeixinUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseAction extends ActionSupport implements ServletRequestAware, SessionAware,ServletResponseAware
{

    protected final SystemLogger logger = LoggerFactory.getSystemLogger(getClass());
    private static final long serialVersionUID = 1656050495784169029L;
    @Autowired
    protected PagerService pagerService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UseryService useryService;
    @Autowired
    protected WeixinAutosendmsgService autosendmsgService;
    protected HttpServletRequest request;
    @SuppressWarnings("rawtypes")
    protected SessionMap sessionMap;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ServletContext application;
    @SuppressWarnings("rawtypes")
    protected Pager pager;
    protected List list;
    protected UserVo userVo;

    protected String currentPage;
    protected String pagerMethod;
    protected Integer pageSize;
    protected String jsonString = "";
    protected JSONObject json = new JSONObject();
    
    protected String goodIds;
    protected String goodNum;
    protected String cidAndActivityId;
    protected String code;
    protected String wxOpenid;
    
    /**
	 * 判断是否跳转到订单确认页面微信
	 * @return
	 */
	public boolean ifgotoCheckPageWx(){
		Object o1 = request.getSession().getAttribute("goodIds");
		Object o2 = request.getSession().getAttribute("goodNum");
		Object o3 = request.getSession().getAttribute("cidAndActivityId");
		boolean ifgotoCheckPageWx = false;
		if(o1 != null){
			goodIds = (String) o1;
			System.out.println(o1);
			request.getSession().removeAttribute("goodIds");
			ifgotoCheckPageWx = true;
		}
		if(o2 != null){
			goodNum = (String) o2;
			System.out.println(o2);
			request.getSession().removeAttribute("goodNum");
			ifgotoCheckPageWx = true;
		}
		if(o3 != null){
			cidAndActivityId = (String)o3;
			System.out.println(o3);
			request.getSession().removeAttribute("cidAndActivityId");
			ifgotoCheckPageWx = true;
		}
		return ifgotoCheckPageWx;
	}
	
	public UserVo getSessionUser(){
		UserInfoUtil userInfo = null;
		userVo = (UserVo) session.getAttribute("loginUser");
		if(userVo ==null){
			if(StringUtils.isEmpty(code)){
				try {
					code = request.getParameter("code");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.isEmpty(wxOpenid)){
				 wxOpenid=(String) session.getAttribute("wxOpenid");
			}
			if(StringUtils.isNotEmpty(code)){
				if(StringUtils.isEmpty(wxOpenid)){
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> code333------ "+code);
					userInfo = WeixinUtil.getUserInfoByCode(code);
					wxOpenid = userInfo.getOpenid();
					request.getSession().setAttribute("wxOpenid",wxOpenid);
				}
			}
			if(StringUtils.isNotEmpty(wxOpenid)){
				Usery usery = useryService.findbyWxOpenId(wxOpenid);
				if(usery!=null){
					userVo = new UserVo();
					userVo.setUsery(usery);
					if(usery.getUserId()!=null){
						Users user = userService.findById(usery.getUserId());
						if (user!=null) {
							userVo.setUser(user);
						}
					}
					session.setAttribute("loginUser", userVo);
					return userVo;
				}else if(userInfo!=null){
					System.out.println("走到else if了 userinfo!=null");
					usery = new Usery();
					usery.setSubscribe(0);
					usery.setUnionid(userInfo.getUnionid());
					usery.setWxOpenid(wxOpenid);
					usery.setAppDate(new Date());
					usery.setUserName(userInfo.getNickname().trim());
					usery.setHeadUrl(userInfo.getHeadimgurl());
					usery.setUnionid(userInfo.getUnionid());
					usery.setLevel(0);
					useryService.save(usery);
					userVo = new UserVo();
					userVo.setUsery(usery);
					session.setAttribute("loginUser", userVo);
					return userVo;
				}
			}
		}
		return userVo;
	}
	
	protected void jsonOut(Object object) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(JSONObject.fromObject(object));
	}
	protected void jsonOut(JSONObject json)throws Exception {
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(json);
	}
	protected void jsonArrayOut(Object object)throws Exception {
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(JSONArray.fromObject(object));
	}

	public JSONObject yanzheng(UserVo userVo){
		if(userVo!=null){
			if(userVo.getUsery()!=null){
				if(userVo.getUsery().getDianPuId()!=null){
					json.put("code", "000000");
				}else{
					//前三位第一个为1是用户登录问题,第二位为1是微信公众账号问题,第三位为1是店铺问题,错误码规则后三位为错误数字000表示不存在
					json.put("code", "001000");//请先去店铺管理-->修改店铺信息
				}
			}else{
				json.put("code", "010000");//没有资格   公众号未关注
			}
		}else{
			json.put("code", "100000");//用户未登录!请重新登录!!!!
		}
		return json;
	}

    public List getList()
    {

        return list;
    }


    public void setList(List list)
    {

        this.list = list;
    }


    public Pager getPager()
    {

        if(pager == null)
        {
            pager = new Pager();
            pager.setCurrentPage(1);
            pager.setPageSize(10);
        }
        return pager;
    }


    public void setPager(Pager pager)
    {

        this.list = pager.getEntityList();
        this.pager = pager;
        //this.pager.setPageSize(10);
        int add = pager.getTotalCounts() % pager.getPageSize() == 0 ? 0 : 1;
        this.pager.setTotalPages(pager.getTotalCounts() / pager.getPageSize() + add);
    }


    public void setSession(Map map)
    {

        this.sessionMap = (SessionMap)map;
    }


    @Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}


    public String getJsonString()
    {

        return jsonString;
    }


    public void setJsonString(String jsonString)
    {

        this.jsonString = jsonString;
    }


    public String jsonExecute() throws Exception
    {

        return super.execute();
    }

    public String getCurrentPage()
    {

        return currentPage;
    }


    public void setCurrentPage(String currentPage)
    {

        this.currentPage = currentPage;
    }


    public String getPagerMethod()
    {

        return pagerMethod;
    }


    public void setPagerMethod(String pagerMethod)
    {

        this.pagerMethod = pagerMethod;
    }
    
    protected HttpServletResponse getResponse() {
    	return ServletActionContext.getResponse();
    } 
    
    public String getGoodIds() {
		return goodIds;
	}
	public void setGoodIds(String goodIds) {
		this.goodIds = goodIds;
	}
	public String getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(String goodNum) {
		this.goodNum = goodNum;
	}
	public String getCidAndActivityId() {
		return cidAndActivityId;
	}
	public void setCidAndActivityId(String cidAndActivityId) {
		this.cidAndActivityId = cidAndActivityId;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
     
	
}
