package com.zklc.weishangcheng.member.service;


import java.util.List;

import net.sf.json.JSONObject;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.weishangcheng.member.hibernate.persistent.WeixinAutosendmsg;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.WxOpenIds;
import com.zklc.weixin.util.UserInfoUtil;

public interface WeixinAutosendmsgService extends IBaseService<WeixinAutosendmsg, Integer> {
	public WeixinAutosendmsg findByOId(String openid);
	public AccessToken processAccessToken();
	public  UserInfoUtil processUserInfoObject(String openId);
	
	/**
	 * 向微信用户发送消息
	 * 
	 * @param openid
	 *            用户微信标识符
	 * @param content
	 *            消息内容
	 */
	public  void sendMsg(String openid, String content);
	
	//创建二维码ticket
    public  JSONObject processTicketByToken(String scene_str);
    
  //创建临时二维码ticket
    public  JSONObject processTicketByToken();
    
    /**
     * 获取微信号里面所有的wxOpenId
     * @return
     */
    String[] getUserOpenIdList();
    
    /**
     * 获取公众号所有的关注信息
     * @return
     */
    public List<UserInfoUtil> getUserInfoList();
    
    public WxOpenIds getUserOpenIdList(String next_openId);
}