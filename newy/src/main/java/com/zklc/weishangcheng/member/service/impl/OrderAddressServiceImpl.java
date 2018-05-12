package com.zklc.weishangcheng.member.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utils.MyUtils;
import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.CityDao;
import com.zklc.weishangcheng.member.dao.OrderAddressDao;
import com.zklc.weishangcheng.member.hibernate.persistent.City;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderAddressVO;
import com.zklc.weishangcheng.member.service.OrderAddressService;
@Service
public class OrderAddressServiceImpl extends BaseServiceImp<OrderAddress, Integer> implements OrderAddressService {
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private CityDao cityDao;
	@Override
	public void defaultAddr(Integer aid,Integer userId) {
		String sql ="update OrderAddress t set t.isFirst =0 where t.userId=? ";
		orderAddressDao.update(sql,userId);
		//当前秒杀商品的状态改为1
		sql ="update OrderAddress t set t.isFirst =1 where t.id=? ";
		orderAddressDao.update(sql,aid);
		
	}

	@Override
	public OrderAddressVO findOrderAddressByUserId(Integer userId) {
		OrderAddressVO vo = null;
		List<OrderAddress> list=super.findByHql("from OrderAddress t  where t.isFirst=1 and t.userId="+userId+"", null);
		if(MyUtils.isNotEmpty(list))
		{
			vo = new OrderAddressVO();
			OrderAddress address=list.get(0);
			if(MyUtils.isNotEmpty(address.getProvinceId()))
			{
			  City province=this.cityDao.findById(address.getProvinceId());
			  vo.setProvince(province.getName());
			  vo.setProvinceId(province.getId());
			}
			if(MyUtils.isNotEmpty(address.getCityId()))
			{
			  City city=this.cityDao.findById(address.getCityId());
			   vo.setCity(city.getName());
			   vo.setCityId(city.getId());
			}
			if(MyUtils.isNotEmpty(address.getAreaId()))
			{
			   City area=this.cityDao.findById(address.getAreaId());
			   vo.setRegion(area.getName());
			   vo.setRegionId(area.getId());
			}
			 vo.setCreateDate(address.getCreateDate()==null?new Date():address.getCreateDate());
			 vo.setId(address.getId());
			 vo.setIsFirst(address.getIsFirst());
			 vo.setMobile(address.getMobile());
			 vo.setUserId(address.getUserId());
			 vo.setUserName(address.getUserName());
			 vo.setZipcode(address.getZipcode());
			 vo.setAddress(vo.getProvince()+vo.getCity()+vo.getRegion()+address.getAddress());
			 vo.setIdCard(address.getIdCard());
			 
		}
		return vo;
	}

	@Override
	public OrderAddressVO getAddressVoById(Integer id) {
		OrderAddressVO vo = null;
		OrderAddress address=super.findById(id);
		if(MyUtils.isNotEmpty(address))
		{
			vo = new OrderAddressVO();
			if(MyUtils.isNotEmpty(address.getProvinceId()))
			{
			  City province=this.cityDao.findById(address.getProvinceId());
			  vo.setProvince(province.getName());
			  vo.setProvinceId(province.getId());
			}
			if(MyUtils.isNotEmpty(address.getCityId()))
			{
			  City city=this.cityDao.findById(address.getCityId());
			   vo.setCity(city.getName());
			   vo.setCityId(city.getId());
			}
			if(MyUtils.isNotEmpty(address.getAreaId()))
			{
			   City area=this.cityDao.findById(address.getAreaId());
			   vo.setRegion(area.getName());
			   vo.setRegionId(area.getId());
			}
			 vo.setCreateDate(address.getCreateDate()==null?new Date():address.getCreateDate());
			 vo.setId(address.getId());
			 vo.setIsFirst(address.getIsFirst());
			 vo.setMobile(address.getMobile());
			 vo.setUserId(address.getUserId());
			 vo.setUserName(address.getUserName());
			 vo.setZipcode(address.getZipcode());
			 vo.setAddress(vo.getProvince()+vo.getCity()+vo.getRegion()+address.getAddress());
			 vo.setIdCard(address.getIdCard());
		}
		return vo;
	}
	
	
	@Override
	public List findOrderAddressListByid(Integer userId) {
		List list = null;
		if(null != userId){
			String sql = "select t.id,t.address,t.zipcode,t.mobile,t.user_name,t.user_id,t.provinceId,t.cityId,t.areaId from order_address t where t.user_id ="+userId;
			list = super.findBySql( sql, null);
		}
		return list;
	}
	@Override
	public List<OrderAddress> findByUserId(Integer userId)
	{
		List<OrderAddress> list=super.findByHql("from OrderAddress t  where t.userId="+userId+"", null);
		 
		return list;
	}

}
 
