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
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.CityService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.UserService;
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
	private UserService userService;
	@Autowired
	private UseryService useryService;
	
	private String cityWord;
	private Integer parentId;
	private Integer cityId;
	private String code;// 微信code
	private String wxOpenid;// 用户微信标识符
	public Integer userId;//传值用的    
   

	/**
	 * 插入City表中城市名称对应的id到users表的city字段
	 * @by zhang_wanqiang 
	 */
//	public void putCity(){
//		userVo = getSessionUser(); //获取当前用户信息
//		
//		if(userVo.getUsery()!=null&& (userVo.getUsery().getCity()==null || userVo.getUsery().getCity().equals(""))){
//			String cityNames = request.getParameter("cityNames"); //获取当前登录用户的城市名称，如:济南
//			System.out.println("当前用户所在的城市为："+cityNames);
//			City city = cityService.findUniqueByProperty("shortname", cityNames);
//			if(city!=null){
//				userVo.setCity(city.getId());
//				userService.update(user);
//			}
//		}
//	}
	
	/**
	 * 获取城市名称
	 * @by zhang_wanqiang 
	 */
//	public void showCity(){
//		JSONObject json = new JSONObject();
//		json.put("success", false);
//		user = getSessionUser();
//		
//		if(user.getCity() == null || user.getCity().equals("")){
//			try {
//				ServletActionContext.getResponse().getWriter().print(json.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}else {
//			City city = cityService.findById(user.getCity());
//			if(city!=null){
//				String shortname = city.getShortname();
//				System.out.println("城市为："+shortname);
//				json.put("success", true);
//				json.put("city", shortname);
//			}
//			try {
//				ServletActionContext.getResponse().getWriter().print(json.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}	


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
//		user = getSessionUser();
		//查询当前用户所在的城市
		String currName = request.getParameter("cityNames");
		System.out.println("当前所在的城市为："+currName);
		
		request.getSession().setAttribute("currCity",cityId);
		City c=this.cityService.findById(cityId);
		String chooseName = c.getShortname();
		System.out.println("用户选择的城市："+chooseName);
		
		if(currName.equals(chooseName)){
			List<Users> updateList = new ArrayList<Users>();  //定义一个updateList集合
//			request.setAttribute("city", c);
//			Users jfUser = userService.findById(user.getUserId());
//			jfUser.setCity(cityId);
//			updateList.add(jfUser);
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
