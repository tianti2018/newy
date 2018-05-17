package com.zklc.weishangcheng.member.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.utils.MyUtils;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.City5d;
import com.zklc.weishangcheng.member.hibernate.persistent.IvmCountry;
import com.zklc.weishangcheng.member.hibernate.persistent.JCity;
import com.zklc.weishangcheng.member.hibernate.persistent.JCounty;
import com.zklc.weishangcheng.member.hibernate.persistent.JProvice;
import com.zklc.weishangcheng.member.hibernate.persistent.JTown;
import com.zklc.weishangcheng.member.hibernate.persistent.JVillage;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.IvmCountryService;
import com.zklc.weishangcheng.member.service.JCityService;
import com.zklc.weishangcheng.member.service.JCountyService;
import com.zklc.weishangcheng.member.service.JProviceService;
import com.zklc.weishangcheng.member.service.JTownService;
import com.zklc.weishangcheng.member.service.JVillageService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weixin.util.WeixinUtil;

@ParentPackage("json")
@Namespace("/user")
@Action(value = "ivmcAction")
@Results({
		@Result(name = "goIvmCountryPage", location = "/WEB-INF/jsp/ivmcountry/edit_introduction.jsp"),
		@Result(name = "viewIvmCountryPage", location = "/WEB-INF/jsp/ivmcountry/view_introduction.jsp"),
		@Result(name = "saveOrUpdate", type = "redirect", location = "/user/ivmcAction!ivmCountryList.action"),
		@Result(name = "ivmCountryList", location = "/WEB-INF/jsp/ivmcountry/list_introduction.jsp"),
		@Result(name = "delIvmCountry", type = "redirect", location = "/user/ivmcAction!ivmCountryList.action" ),
		@Result(name = "cities", location = "/WEB-INF/jsp/ivmcountry/citylist.jsp")
})
/**
 * 我爱家乡
 */
public class IvmCountryAction extends BaseAction {
	private Users user;
	private String code;// 微信code
	private String wxOpenid;// 用户微信标识符
	public Integer userId;
	private IvmCountry ivmCountry;
	private Integer ivmcId;
	private List<IvmCountry> ivmcList;
	private List<IvmCountry> memberCountryList;

	private List<JProvice> provincelist;
	private List<JCity> citylist;
	private List<JCounty> countylist;
	private List<JTown> townlist;
	private List<JVillage> villagelist;
	@Autowired
	private JProviceService proviceService;
	@Autowired
	private JCityService cityService;
	@Autowired
	private JCountyService countyService;
	@Autowired
	private JTownService townService;
	@Autowired
	private JVillageService villageService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private UserService userService;
	@Autowired
	private IvmCountryService ivmCountryService;

	//前往新增页面
	public String goIvmCountryPage(){
		if(ivmcId!=null){
			ivmCountry = ivmCountryService.findById(ivmcId);
			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getProvinceId())) {
				citylist = cityService.findByProperty("proviceId", ivmCountry.getProvinceId());
			}
			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getCityId())) {
				countylist = countyService.findByProperty("cityId",ivmCountry.getCityId());
			}
			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getCountyId())) {
				townlist = townService.findByProperty("countyId",ivmCountry.getCountyId());
			}
			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getTownId())) {
				villagelist = villageService.findByProperty("townId",ivmCountry.getTownId());
			}
		}
		provincelist = proviceService.findAll();

		return "goIvmCountryPage";
	}
	
//	public String viewIvmCountryPage(){
//		if(ivmcId!=null){
//			ivmCountry = ivmCountryService.findById(ivmcId);
//			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getProvinceId())) {
//				List<City5d> province = city5dService.findByProperty("id", ivmCountry.getProvinceId());
//				if(province.size()>0){
//					ivmCountry.setProvince(province.get(0).getName());
//				}
//			}
//			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getCityId())) {
//				List<City5d> city = city5dService.findByProperty("id", ivmCountry.getCityId());
//				if(city.size()>0){
//					ivmCountry.setCity(city.get(0).getName());
//				}
//			}
//			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getCountyId())) {
//				List<City5d> county = city5dService.findByProperty("id", ivmCountry.getCountyId());
//				if(county.size()>0){
//					ivmCountry.setCounty(county.get(0).getName());
//				}
//			}
//			if(ivmCountry != null &&MyUtils.isGtZero(ivmCountry.getTownId())) {
//				List<City5d> town = city5dService.findByProperty("id", ivmCountry.getTownId());
//				if(town.size()>0){
//					ivmCountry.setTown(town.get(0).getName());
//				}
//			}
//		}
//		provincelist = city5dService.findByLevel(level_province);
//		return "viewIvmCountryPage";
//	}
	
	public String delIvmCountry(){
		if(ivmcId!=null){
			IvmCountry ivmc = ivmCountryService.findById(ivmcId);
			ivmCountryService.delete(ivmc);
		}
		return "delIvmCountry";
	}
	
	/**
	 * 保存或修改
	 * @return
	 */
	public String saveOrUpdate(){
		userVo = getSessionUser();
		user = userVo.getUser();
		if(user == null){
			return "error";
		}
		if(ivmCountry != null){
			if(ivmCountry.getId()!=null){
				ivmCountry.setCreateDate(new Date());
				ivmCountryService.update(ivmCountry);
			}else{
				ivmCountry.setCreateDate(new Date());
				ivmCountry.setUserId(user.getUserId());
				ivmCountry.setStatus(0);
				ivmCountryService.save(ivmCountry);
			}
		}
		
		return "saveOrUpdate";
	}

	public String ivmCountryList(){
		userVo = getSessionUser();
		user = userVo.getUser();
		if(user == null){
			return "error";
		}
		ivmcList = ivmCountryService.findByHql("from IvmCountry i where i.status = 0 and i.userId="+user.getUserId()+" order by createDate desc",null);
		//String sql = "select * from IvmCountry i where i.status = 0 and i.user_id="+user.getUserId()+" order by create_date desc";	
		if(ivmCountry != null){
			StringBuffer sb = new StringBuffer();
			sb.append("from IvmCountry i where i.status = 0 and i.userId!="+user.getUserId());
			if(MyUtils.isNotEmpty(ivmCountry.getCityId())){
				sb.append(" and i.cityId = "+ivmCountry.getCityId());
				countylist = countyService.findByProperty("cityId",ivmCountry.getCityId());
			}
			if(MyUtils.isNotEmpty(ivmCountry.getCountyId())){
				sb.append(" and i.countyId = "+ivmCountry.getCountyId());
				townlist = townService.findByProperty("countyId",ivmCountry.getCountyId());
			}
			if(MyUtils.isNotEmpty(ivmCountry.getTownId())){
				sb.append(" and i.townId = "+ivmCountry.getTownId());
				villagelist = villageService.findByProperty("townId",ivmCountry.getTownId());
			}
			if(MyUtils.isNotEmpty(ivmCountry.getVillageId())){
				sb.append(" and i.villageId = "+ivmCountry.getVillageId());
			}
			sb.append(" order by createDate desc");
			memberCountryList = ivmCountryService.findByHql(sb.toString(), null);
		} else{
			//获取定位城市
			Users jifenUser = userService.findById(user.getUserId());
//			List<JCity> jCity = cityService.findByProperty("oldCityId", jifenUser.getCity());
//			if(jCity != null &&jCity.size()>0){
//				countylist = countyService.findByProperty("cityId",jCity.get(0).getCityId());
//				memberCountryList = ivmCountryService.findByHql("from IvmCountry i where i.status = 0 and i.userId!="+user.getUserId()+""
//						+ " and i.cityId = "+jCity.get(0).getCityId()+" order by createDate desc",null);
//			}
			
			
		}
		return "ivmCountryList";
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<JProvice> getProvincelist() {
		return provincelist;
	}

	public void setProvincelist(List<JProvice> provincelist) {
		this.provincelist = provincelist;
	}

	public List<JCity> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<JCity> citylist) {
		this.citylist = citylist;
	}

	public List<JCounty> getCountylist() {
		return countylist;
	}

	public void setCountylist(List<JCounty> countylist) {
		this.countylist = countylist;
	}

	public List<JTown> getTownlist() {
		return townlist;
	}

	public void setTownlist(List<JTown> townlist) {
		this.townlist = townlist;
	}

	public List<JVillage> getVillagelist() {
		return villagelist;
	}

	public void setVillagelist(List<JVillage> villagelist) {
		this.villagelist = villagelist;
	}

	public IvmCountry getIvmCountry() {
		return ivmCountry;
	}

	public void setIvmCountry(IvmCountry ivmCountry) {
		this.ivmCountry = ivmCountry;
	}

	public Integer getIvmcId() {
		return ivmcId;
	}

	public void setIvmcId(Integer ivmcId) {
		this.ivmcId = ivmcId;
	}

	public List<IvmCountry> getIvmcList() {
		return ivmcList;
	}

	public void setIvmcList(List<IvmCountry> ivmcList) {
		this.ivmcList = ivmcList;
	}

	public List<IvmCountry> getMemberCountryList() {
		return memberCountryList;
	}

	public void setMemberCountryList(List<IvmCountry> memberCountryList) {
		this.memberCountryList = memberCountryList;
	}
	
	
}
