package com.zklc.weishangcheng.member.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.CoreService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.messageS.TextMessage;
import com.zklc.weixin.util.MessageUtil;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.UserInfoUtil;
@Service
public class CoreServiceImpl extends BaseServiceImp<Users, Integer> implements CoreService {
	@Autowired
	private UseryService useryService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = "";
		String fromUserName = null;
		Users jifenUser = (Users) request.getSession().getAttribute("loginUser");
		Usery usery = null;
		String refName = null;
		UserInfoUtil userInfo = null;
		String toUserName = null;
		String msgKey = null;
		Usery parentUsery = null;
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 得到 ticket
			 String ticket = requestMap.get("Ticket");
			 boolean cunchu = false;
			// 发送方帐号（open_id）
			fromUserName = requestMap.get("FromUserName");
			request = ServletActionContext.getRequest();
			toUserName = (String)requestMap.get("ToUserName");
			System.out.println("-------------------fromUserName--------------"+fromUserName);
			String msgType = requestMap.get("MsgType");
			TextMessage textMessage = new TextMessage();
		    textMessage.setToUserName(fromUserName);
		    textMessage.setFromUserName(toUserName);
		    textMessage.setCreateTime((int) new Date().getTime()/1000);
		    msgKey = (String)requestMap.get("Content");
		    
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String msgVal = "";
				msgVal=SystemMessage.getString(msgKey);
				if(msgVal!=null&&!msgVal.equals("")&&!msgVal.equals("!"+msgKey+"!")){
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setContent(msgVal);
				}else {
					textMessage.setMsgType("transfer_customer_service");
				}
				//return MessageUtil.textMessageToXml(textMessage);
			}
//			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				textMessage.setMsgType("transfer_customer_service");
				//return MessageUtil.textMessageToXml(textMessage);
			}
//			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				textMessage.setMsgType("transfer_customer_service");
				//return MessageUtil.textMessageToXml(textMessage);
			}
//			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				textMessage.setMsgType("transfer_customer_service");
				//return MessageUtil.textMessageToXml(textMessage);
			}
//			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				textMessage.setMsgType("transfer_customer_service");
				//return MessageUtil.textMessageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					cunchu = true;
					
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					usery = useryService.findbyWxOpenId(fromUserName);
					if(usery!=null){
						usery.setSubscribe(0);
						useryService.update(usery);
					}
					
					return respMessage;
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					return respMessage;
				}else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					return respMessage;
				}else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
					
				}else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					cunchu = true;
				}
				System.out.println(eventType);
				
			}
			if(cunchu){
				userInfo = autosendmsgService.processUserInfoObject(fromUserName);
				if(userInfo == null){
					for(int i=0;i<5;i++){
						userInfo = autosendmsgService.processUserInfoObject(fromUserName);
						if(userInfo != null){
							break;
						}
					}
				}
				usery = useryService.findbyWxOpenId(fromUserName);
				if (usery==null) {
					System.out.println("查询不到插入");
					usery = new Usery();
					usery.setSubscribe(0);
					usery.setUnionid(userInfo.getUnionid());
					usery.setWxOpenid(fromUserName);
					usery.setAppDate(new Date());
					usery.setUserName(userInfo.getNickname().trim());
					usery.setHeadUrl(userInfo.getHeadimgurl());
					usery.setUnionid(userInfo.getUnionid());
					usery.setLevel(0);
			    	if (null!=ticket&&!"".equals(ticket)) {
			    	  	List<Usery> listOne = useryService.findByProperty("ticket", ticket);
			    	  	if (listOne.size()!=0) {
			    	  		parentUsery = listOne.get(0);
			    	  	}
			    	}
			    	if(parentUsery!=null){
	    	  			usery.setParentId(parentUsery.getId());
	    	  			usery.setReferrerId(parentUsery.getUserId());
	    	  			parentUsery.setChildNum((parentUsery.getChildNum()==null?0:parentUsery.getChildNum())+1);
	    	  			useryService.update(parentUsery);
						String mess = "您好:\n您的队友:";
						mess += "【"+usery.getId()+" ： "+usery.getUserName()+"】 ,已成功关注！\n";
						if(parentUsery !=null&&parentUsery.getWxOpenid()!=null)
							autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
	    	  		}
					if(jifenUser!=null){
						usery.setUserId(jifenUser.getUserId());
					}
					useryService.save(usery);
			    	
			    	StringBuilder sb = new StringBuilder();
					sb.append("在大自然的所有生物都有属于自己的声音，感谢您的加入和我们共同传递生态自然的语言。");
					sb.append("\n");
//					sb.append("星火草原帮扶计划响应国家大众创业，万众创新的号召，充分发挥技术创新优势，商业模式创新优势。致力于打造最亲民，最平民化，最适合老百姓创业消费的平台，不管你是学生，还是在家务农，全职妈妈等家庭人员，这里都可以展现你的才能，为你搭建人生财富梦想的舞台。星火草原帮扶计划愿与您共同谱写2016年新的财富梦想！！");
//					sb.append("\n");
//					sb.append("\n【申请二维码】消费一款产品，点击我的助手，获取二维码,");
//					sb.append("<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//							+ SystemMessage.getString("APPID")
//							+ "&redirect_uri="
//							+ SystemMessage.getString("YUMING")
//							+ "/user/userAction!buy.action&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect\">"
//							+ "立即购买" + "</a>，");
//					sb.append("从此开启自动赚钱模式，开启健康、财富之旅！");
//					sb.append("\n");
					
					autosendmsgService.sendMsg(fromUserName, sb.toString());
				}else {
					usery.setUserName(userInfo.getNickname());
					usery.setHeadUrl(userInfo.getHeadimgurl());
					if(usery.getAppDate()==null){
						usery.setAppDate(new Date());
					}
					usery.setSubscribe(0);
					useryService.update(usery);
//					autosendmsgService.sendMsg(fromUserName, "欢迎回来!");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return respMessage;
		}

		return respMessage;
	}
    
}

