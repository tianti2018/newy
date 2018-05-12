package com.zklc.weishangcheng.member.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.CoreService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.messageS.TextMessage;
import com.zklc.weixin.util.MessageUtil;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.UserInfoUtil;
@Service
public class CoreServiceImpl extends BaseServiceImp<JifenUser, Integer> implements CoreService {
	@Autowired
	private JifenUserService jifenUserService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private JiFenRecordService jiFenRecordService;
	
	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = "";
		String fromUserName = null;
		JifenUser jifenUser = null;
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
				boolean songjifen = false;
				if (usery==null) {
					System.out.println("查询不到插入");
					usery = new Usery();
					usery.setSubscribe(0);
					usery.setUnionid(userInfo.getUnionid());
					usery.setWxOpenid(fromUserName);
					usery.setAppDate(new Date());
					jifenUser = jifenUserService.findbyUnionId(userInfo.getUnionid());
					if(jifenUser==null){
						songjifen = true;
						System.out.println("旧表中不存在");
						jifenUser = new JifenUser();
						jifenUser.setAppDate(new Date());
				    	jifenUser.setSubscribe(1);
				    	jifenUser.setLevel(0);
				    	jifenUser.setBlock("1");
				    	jifenUser.setBuyCount(60);
				    	
				    	if(userInfo!= null) {
				    		jifenUser.setUserName(userInfo.getNickname().trim());
				    		jifenUser.setHeadUrl(userInfo.getHeadimgurl());
				    	}
				    	jifenUser.setUnionid(userInfo.getUnionid());
				    	System.out.println(">>>>>>>>>>>>>>>> ticket >>>>>>>> "+ticket);
				    	if (null!=ticket&&!"".equals(ticket)) {
				    	  	List<Usery> listOne = useryService.findByProperty("ticket", ticket);
				    	  	if (listOne.size()!=0) {
				    	  		parentUsery = listOne.get(0);
				    	  		jifenUser.setReferrerId(parentUsery.getUserId());
				    	  	}else{
					    		System.out.println("没有找到推荐人");
					    		autosendmsgService.sendMsg(fromUserName, "您的推荐人没有找到,请取消关注,重新扫描推荐人的二维码关注,否则无法加入本平台!");
					    		return "";
					    	}
				    	}else{
				    		System.out.println("没有找到推荐人");
				    		autosendmsgService.sendMsg(fromUserName, "您的推荐人没有找到,请取消关注,重新扫描推荐人的二维码关注,否则无法加入本平台!");
				    		return "";
				    	}
				    	jifenUserService.save(jifenUser);
				    	if(songjifen){
				    		JiFenRecord record = new JiFenRecord();
							record.setCreateDate(new Date());
							record.setJifen(60);
							record.setMemo("新用户关注");
							//if(juser == true){
								record.setStatus(1);
							//}else{
							//	record.setStatus(3);//不可使用
							//}
							
							record.setUserId(jifenUser.getUserId());
							record.setType(5);
							jiFenRecordService.save(record);
				    	}
					}
					//boolean juser = jifenUserService.checkUserJifen(parentUsery.getUserId());
					
					
					
					usery.setUserId(jifenUser.getUserId());
					useryService.save(usery);
			    	
			    	
			    	StringBuilder sb = new StringBuilder();
		        	
//					sb.append("XXXXX号星星火种会员，平台全体家人将会竭诚为您服务！！！");
//					sb.append("\n\n");
//					sb.append("恭喜您由【");
//					// 获取推荐人的用户名
//					String userName = (null == refName) ? SystemMessage
//							.getString("company") : refName;
//					sb.append(userName);
//					sb.append("】");
					sb.append("感谢您成为");
					// 会员userId
					sb.append(jifenUser.getUserId());
					
					if(songjifen){
						//赠送您60星火微积分，
						sb.append("号星星火种会员，赠送您60星火微积分，平台全体家人将会竭诚为您服务！！！");
					}else{
						sb.append("号星星火种会员，平台全体家人将会竭诚为您服务！！！");
					}
					sb.append("\n");
					sb.append("星火草原帮扶计划响应国家大众创业，万众创新的号召，充分发挥技术创新优势，商业模式创新优势。致力于打造最亲民，最平民化，最适合老百姓创业消费的平台，不管你是学生，还是在家务农，全职妈妈等家庭人员，这里都可以展现你的才能，为你搭建人生财富梦想的舞台。星火草原帮扶计划愿与您共同谱写2016年新的财富梦想！！");
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
//					sb.append("【商品入驻】");
//					sb.append("\n");
//					sb.append("【分销系统购买】:18001392511   尹女士 ");
//					sb.append("\n");
//					sb.append("【咨询学习】加客服微信号:18001392511");
					JifenUser refferUser = null;
					if(jifenUser.getReferrerId()!=null){
						refferUser = jifenUserService.findById(jifenUser.getReferrerId());
						parentUsery = useryService.findbyUserId(jifenUser.getReferrerId());
					}
					autosendmsgService.sendMsg(fromUserName, sb.toString());
					String mess = "";
					if(refferUser !=null){
						refferUser.setChild1((refferUser.getChild1()==null?0:refferUser.getChild1())+1);
						if(songjifen){
							JiFenRecord record1 = new JiFenRecord();
							record1.setCreateDate(new Date());
							record1.setJifen(5);
							record1.setMemo("新超级粉丝["+jifenUser.getUserId()+"]关注,赠送["+refferUser.getUserId()+"]"+record1.getJifen()+"积分");
							//boolean suser = jifenUserService.checkUserJifen(refferUser.getUserId());
							//if(suser == true){
								record1.setStatus(1);
							/*}else{
								record1.setStatus(3);
								record1.setMemo(record1.getMemo()+",但已达上限,消费后方可继续获得");
							}*/
							
							
							record1.setUserId(refferUser.getUserId());
							record1.setFromUserId(jifenUser.getUserId());
							record1.setType(5);
							jiFenRecordService.save(record1);
						}
						jifenUserService.update(refferUser);
						mess = "您好:\n您的超级粉丝:";
						mess += "【"+jifenUser.getUserId()+" ： "+jifenUser.getUserName()+"】 ,已成功关注！\n";
						if(parentUsery !=null&&parentUsery.getWxOpenid()!=null)
							autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
						if(refferUser.getReferrerId()!=null){
							JifenUser r2 = jifenUserService.findById(refferUser.getReferrerId());
							if(r2!=null){
								r2.setChild2((r2.getChild2()==null?0:r2.getChild2())+1);
								if(songjifen){
									JiFenRecord record2 = new JiFenRecord();
									record2.setCreateDate(new Date());
									record2.setJifen(2);
									record2.setMemo("新铁杆粉丝["+jifenUser.getUserId()+"]关注,赠送["+r2.getUserId()+"]"+record2.getJifen()+"积分");
									/*boolean suser = jifenUserService.checkUserJifen(r2.getUserId());
									if(suser == true){*/
										record2.setStatus(1);
									/*}else{
										record2.setStatus(3);
										record2.setMemo(record2.getMemo()+",但已达上限,消费后方可继续获得");
									}*/
									record2.setUserId(r2.getUserId());
									record2.setFromUserId(jifenUser.getUserId());
									record2.setType(5);
									jiFenRecordService.save(record2);
								}
								
								jifenUserService.update(r2);
								Usery usery2 = useryService.findbyUserId(r2.getUserId());
								if(usery2!=null&&usery2.getWxOpenid()!=null){
									mess = "您好:\n您的铁杆粉丝:";
									mess += "【"+jifenUser.getUserId()+" ： "+jifenUser.getUserName()+"】,已成功关注！\n";
									autosendmsgService.sendMsg(usery2.getWxOpenid(),mess);
								}
								if(r2.getReferrerId()!=null){
									Usery u3 = useryService.findbyUserId(r2.getReferrerId());
									JifenUser r3 = jifenUserService.findById(r2.getReferrerId());
									if(songjifen){
										JiFenRecord record3 = new JiFenRecord();
										record3.setCreateDate(new Date());
										record3.setJifen(1);
										record3.setMemo("新忠实粉丝["+jifenUser.getUserId()+"]关注,赠送["+r3.getUserId()+"]"+record3.getJifen()+"积分");
										/*boolean suser = jifenUserService.checkUserJifen(r3.getUserId());
										if(suser == true){*/
											record3.setStatus(1);
										/*}else{
											record3.setStatus(3);
											record3.setMemo(record3.getMemo()+",但已达上限,消费后方可继续获得");
										}*/
										record3.setUserId(r3.getUserId());
										record3.setFromUserId(jifenUser.getUserId());
										record3.setType(5);
										jiFenRecordService.save(record3);
									}
									
									if(r3!=null){
										r3.setChild3((r3.getChild3()==null?0:r3.getChild3())+1);
										jifenUserService.update(r3);
									}
									if(u3!=null&&u3.getWxOpenid()!=null){
										mess = "您好:\n您的忠实粉丝:";
										mess += "【"+jifenUser.getUserId()+" ： "+jifenUser.getUserName()+"】,已成功关注！\n";
										autosendmsgService.sendMsg(u3.getWxOpenid(),mess);
									}
								}
							}
						}
					}
				}else {
					jifenUser = jifenUserService.findById(usery.getUserId());
					if(jifenUser!=null&&userInfo!=null){
						jifenUser.setUserName(userInfo.getNickname());
						jifenUser.setHeadUrl(userInfo.getHeadimgurl());
						jifenUserService.update(jifenUser);
					}
					usery.setSubscribe(0);
					useryService.update(usery);
					List<JiFenRecord> records = jiFenRecordService.findByProperty("fromUserId", jifenUser.getUserId());
					if(records.size()>0){
						for(JiFenRecord record:records){
							if(record.getStatus()==0&&record.getType()==5){
								record.setStatus(1);
								jiFenRecordService.update(record);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return respMessage;
		}

		return respMessage;
	}
    
	@Override
	public JifenUser findByOpenid(String openid) {
		StringBuffer hql = new StringBuffer("from JifenUser t where 1=1 ");
		if(openid!=null&&!"".equals(openid)){
			hql.append(" and t.wxOpenid ='"+openid+"'");
			List userList=findByHql(hql.toString(), null);
			if(userList!=null&&userList.size()>0)
				return (JifenUser) userList.get(0);
		}
		return null;
	}
    
}

