package com.zklc.weishangcheng.member.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.utils.MyUtils;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.City;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.CityService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

@ParentPackage("json")
@Namespace("/city")
@Action(value = "cityAction")
@Results({ 
	   @Result(name = "cities", location = "/WEB-INF/jsp/citylist.jsp"), 
	   @Result(name = "list", type="json", params={"result","cities"}),
	   @Result(name = "toIndex", location = "/WEB-INF/jsp/market.jsp"),

})

public class CityAction extends BaseAction {
	
	private static final long serialVersionUID = -2572221943122463952L;
	@Autowired
	private CityService cityService;
	@Autowired
	private JiFenRecordService jfrecordService;
	@Autowired
	private JifenUserService userService;
	@Autowired
	private UseryService useryService;
	
	private String cityWord;
	private Integer parentId;
	private Integer cityId;
	private JifenUser user;
	private String code;// 微信code
	private String wxOpenid;// 用户微信标识符
	public Integer userId;//传值用的    
   

	/**
	 * 插入City表中城市名称对应的id到users表的city字段
	 * @by zhang_wanqiang 
	 */
	public String putCity(){
		user = getSessionUser(); //获取当前用户信息
		
		if(user.getCity()==null || user.getCity().equals("")){
			String cityNames = request.getParameter("cityNames"); //获取当前登录用户的城市名称，如:济南
			System.out.println("当前用户所在的城市为："+cityNames);
			
			
			String hqlCity = "from City c where shortname = "+"'"+cityNames+"'"; //从City表中查询出shortname=cityNames
			List<City> cityList = jfrecordService.findByHql(hqlCity, null); //执行hql语句，得到结果
			
			List<JifenUser> updateList = new ArrayList<JifenUser>();  //定义一个updateList集合
			
			for(City city:cityList){  //遍历cityList，得到每一条记录	
				Integer id = city.getId();	//得到城市名为cityNames(济南的)的相对应的id号
				System.out.println("id======"+id);
				
				String hqlJifenUser = "from JifenUser j where userId = "+"'"+user.getUserId()+"'"; //从users表中查出当前登录用户的信息
				List<JifenUser> jfUserList = jfrecordService.findByHql(hqlJifenUser, null); //执行hql语句，得到结果
				for(JifenUser jfuser:jfUserList){  //遍历jifenUser得到每一条记录
					if(jfuser.getCity()==null){
						jfuser.setCity(id); //存入city的值
						updateList.add(jfuser); //把存入的值放到updateList集合中
					}
				}
			}
			jfrecordService.saveOrUpdateAll(updateList); //执行update修改语句
		}
		return null;
	}
	
	/**
	 * 获取城市名称
	 * @by zhang_wanqiang 
	 */
	public String showCity(){
		JSONObject json = new JSONObject();
		json.put("success", false);
		user = getSessionUser();
		JifenUser jfUser = userService.findById(user.getUserId());
		System.out.println("cityNum===="+jfUser.getCity());
		
//		String currName = request.getParameter("cityNames"); //获取当前登录用户的城市名称，如:济南
//		System.out.println("当前用户所在的城市为："+currName);
		
		if(jfUser.getCity() == null || jfUser.getCity().equals("")){
			try {
				ServletActionContext.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			City city = cityService.findById(jfUser.getCity());
			if(city!=null){
				String shortname = city.getShortname();
				System.out.println("城市为："+shortname);
				json.put("success", true);
				json.put("city", shortname);
			}
			try {
				ServletActionContext.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}	


/**
    * 城市列表
    * @return
    */
	public String cities() {
		Map<String, List<City>> map = new HashMap<String, List<City>>();
		List<City> cities = cityService.getCities();
		List<City> hotcities = new ArrayList<City>();
		for (City city : cities) {
			// 热门城市
			if (city.getStatus().equals(2)) {
				hotcities.add(city);
			}
			// 生成城市的map
			if (MyUtils.isNotEmpty(cityWord)) {
				if (city.getName().contains(cityWord)) {
					if (map.containsKey(city.getAleph())) {
						map.get(city.getAleph()).add(city);
					} else {
						List<City> list = new ArrayList<City>();
						list.add(city);
						map.put(city.getAleph(), list);
					}
				}
			} else {
				if (map.containsKey(city.getAleph())) {
					map.get(city.getAleph()).add(city);
				} else {
					List<City> list = new ArrayList<City>();
					list.add(city);
					map.put(city.getAleph(), list);
				}
			}
		}
		request.setAttribute("hotcities", hotcities);
		request.setAttribute("citymap", map);
		if (MyUtils.isNotEmpty(cities)) {
			return "cities";
		} else {
			return "timeOut";
		}
	}
	/**
	 * 根据parentd id查询城市列表
	 */
	public void findCityByParentId()
	{
		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		response.setContentType("application/json;charset=utf-8");
		
		 List<City> cities=cityService.findByParentId(parentId);
		 json.put("result", cities);
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用户选择当前城市 加到session中 ，其他地方可以使用
	 */
	public String choiceCity(){
		JSONObject json = new JSONObject();
		json.put("success",false);
		user = getSessionUser();
		//查询当前用户所在的城市
		String currName = request.getParameter("cityNames");
		System.out.println("当前所在的城市为："+currName);
		
		request.getSession().setAttribute("currCity",cityId);
		City c=this.cityService.findById(cityId);
		String chooseName = c.getShortname();
		System.out.println("用户选择的城市："+chooseName);
		
		if(currName.equals(chooseName)){
			List<JifenUser> updateList = new ArrayList<JifenUser>();  //定义一个updateList集合
//			request.setAttribute("city", c);
			JifenUser jfUser = userService.findById(user.getUserId());
			jfUser.setCity(cityId);
			updateList.add(jfUser);
			jfrecordService.saveOrUpdateAll(updateList); //执行update修改语句
			json.put("success", true);
			json.put("city", c);
			try {
				ServletActionContext.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
//			request.setAttribute("tipMessage", "您当前选择的城市和您所在的城市不符！");
			json.put("success", false);
			json.put("tipMessage", "您当前选择的城市和您所在的城市不符！");
			try {
				ServletActionContext.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		return "toIndex";
		return null;
	}
	
	private JifenUser getSessionUser(){
		
		 user = (JifenUser) request.getSession().getAttribute("loginUser");
		 if(user==null){
			 if(!StringUtils.isNotEmpty(code)){
					try {
						code = request.getParameter("code");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!StringUtils.isNotEmpty(wxOpenid)){
					 wxOpenid=(String) request.getSession().getAttribute("wxOpenid");
				}
			  if(code!=null){
				  if(wxOpenid==null){
					  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> code333------ "+code);
					  wxOpenid = WeixinUtil.code2openid(code);
					  request.getSession().setAttribute("wxOpenid",wxOpenid);
				  }
			  }
			 if(wxOpenid!=null){
				 Usery usery = useryService.findbyWxOpenId(wxOpenid);
				 if(usery!=null){
					 user = useryService.findJifenUserByUsery(usery);
					 if (user!=null) {
						 request.getSession().setAttribute("loginUser",user);
					 }
				}
			 }
		 }else{
			 user = userService.findById(user.getUserId());
			 request.getSession().setAttribute("loginUser",user);
		 }
		 if(user!=null&&!"".equals(user.getAppCenterId())&& null != user.getAppCenterId() && user.getAppCenterId().equals(2)){
			 return null;
		 }
		if(userId != null && user == null){
			user = userService.findById(userId);
			request.getSession().setAttribute("loginUser",user);
		}

		return  user;
	} 
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCityWord() {
		return cityWord;
	}

	public void setCityWord(String cityWord) {
		this.cityWord = cityWord;
	}
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
