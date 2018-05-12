package com.zklc.weishangcheng.member.service.impl;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.AccessTokenDao;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.weishangcheng.member.hibernate.persistent.WeixinAutosendmsg;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.WxOpenIds;
import com.zklc.weishangcheng.member.service.AccessTokenService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.messageSend.SendText;
import com.zklc.weixin.messageSend.text;
import com.zklc.weixin.util.MessageUtil;
import com.zklc.weixin.util.UserInfoUtil;
import com.zklc.weixin.util.WeixinUtil;
@Service
public class WeixinAutosendmsgServiceImpl extends BaseServiceImp<WeixinAutosendmsg, Integer> implements WeixinAutosendmsgService {
	public  Logger log = LoggerFactory.getLogger(WeixinAutosendmsgServiceImpl.class);
	
	@Autowired
	private AccessTokenDao accessTokenDao;
	
	@Autowired
	AccessTokenService accessTokenService;
	
	@Override
	public WeixinAutosendmsg findByOId(String openid) {
		StringBuffer hql = new StringBuffer("from weixinAutosendmsg t where 1=1 ");
		if(openid!=null&&!"".equals(openid)){
			hql.append(" and t.openid ='"+openid+"'");
			List userList=super.findByHql(hql.toString(), null);
			if(userList!=null&&userList.size()>0)
				return (WeixinAutosendmsg) userList.get(0);
		}
		return null;
	}
	@Override
	public AccessToken processAccessToken() {
		//String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		//String  appId = SystemMessage.getString("APPID");
		//String  appSecret = SystemMessage.getString("APPSECRET");
		AccessToken accessToken = accessTokenDao.findByAccessTokenTopnewOne();
		
		//查询出最新的一条accessToken进行时间的对比
	     if (null==accessToken) {
    	    com.zklc.weixin.pojo.AccessToken at =   WeixinUtil.getAccessToken(WeixinUtil.appId, WeixinUtil.appSecret);
    	    if (null!=at) {
    	    	accessToken = new AccessToken();
	    		accessToken.setCreateDate(new Date());
	    		accessToken.setExpiresIn(at.getExpiresIn());
	    		accessToken.setToken(at.getToken());
	    		accessToken.setTicket(WeixinUtil.getJsapiTicket(at.getToken()));
    	    	accessTokenService.save(accessToken);
    	    }
    	    return accessToken;
	   }
	   else {
// 		System.out.println("accessToken!=null&&(new Date().getTime()/1000)-accessToken.getCreateDate().getTime()/1000<100"
// 		+(accessToken!=null&&(new Date().getTime()/1000)-accessToken.getCreateDate().getTime()/1000<100));
// 				
// 		System.out.println("accessToken!=null&&(new Date().getTime()/1000)-accessToken.getCreateDate().getTime()/1000<100"
// 				+(accessToken!=null&&(new Date().getTime()/1000)-accessToken.getCreateDate().getTime()/1000<100));
 		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		
 		 if(accessToken!=null&&(new Date().getTime()/1000)-accessToken.getCreateDate().getTime()/1000<100){
 		    return accessToken;
 		 }
 		 else {
 			com.zklc.weixin.pojo.AccessToken at =   WeixinUtil.getAccessToken(WeixinUtil.appId, WeixinUtil.appSecret);
 			if (accessToken!=null) {
	    		accessToken.setCreateDate(new Date());
    	    	accessToken.setExpiresIn(at.getExpiresIn());
    	    	accessToken.setToken(at.getToken());
    	    	accessToken.setTicket(WeixinUtil.getJsapiTicket(at.getToken()));
    	    	accessTokenService.update(accessToken);
	    	}
 		 }
	   }
	    return accessToken;
	}

	@Override
	public UserInfoUtil processUserInfoObject(String openId) {
		UserInfoUtil userInfo =null;
		AccessToken accessToken = processAccessToken();
        String token=accessToken.getToken();
        // 获取个人信息
        String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openId;
        JSONObject jsonObject=WeixinUtil.httpRequest(userInfoUrl, "GET", null);
        System.out.println(jsonObject.toString());
        // 如果请求成功
        if (null != jsonObject) {
        	try {
        		userInfo= new UserInfoUtil();
				if(jsonObject.get("subscribe").equals(1)){
					String tempSex="";
				    if(jsonObject.getInt("sex")==1){
				        tempSex="男";
				    } else{
				        tempSex="女";
				     }
				    userInfo.setNickname(jsonObject.getString("nickname"));
				    userInfo.setSex(tempSex);
				    userInfo.setLanguage(jsonObject.getString("language"));
				    userInfo.setCity(jsonObject.getString("city"));
				    userInfo.setProvince(jsonObject.getString("province"));
				    userInfo.setCountry(jsonObject.getString("country"));
				    userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
				    userInfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
				    userInfo.setRemark(jsonObject.getString("remark"));
				    userInfo.setGroupid(jsonObject.getString("groupid"));
				}
				userInfo.setSubscribe(jsonObject.getString("subscribe"));
				userInfo.setOpenid(jsonObject.getString("openid"));
				userInfo.setUnionid(jsonObject.getString("unionid"));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
            
        }
        return userInfo;
	}
	@Override
	public void sendMsg(String openid, String content) {
		if (openid != null) {
			SendText sendText = new SendText();
			sendText.setTouser(openid);
			sendText.setMsgtype(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			text text = new text();
			text.setContent(content);
			sendText.setText(text);
			JSONObject jObject = JSONObject.fromObject(sendText);
			AccessToken accessToken = processAccessToken();
	        String token=accessToken.getToken();
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+ token;
			HttpPost pmethod = new HttpPost(url);
			StringEntity entity = null;
			entity = new StringEntity(jObject.toString(), Consts.UTF_8);
			entity.setContentType("application/json");
			pmethod.setEntity(entity);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			try {
				HttpResponse res = httpClient.execute(pmethod);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public JSONObject processTicketByToken(String scene_str) {
		AccessToken accessToken = processAccessToken();
        String token=accessToken.getToken();
//		System.out.println(" >>>>>>>> token"+token);
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token+"";
		StringBuilder sb = new StringBuilder();
		sb.append("{\"action_name\":\"QR_LIMIT_STR_SCENE\",");
		sb.append(" \"action_info\": { \"scene\":  {\"scene_str\":\""+scene_str+"\"}}} ");
		 
		 //String paramJoson = "{\\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": \{\"scene\"\: \{\"scene_str\": \"123\"}}}";
		 JSONObject jsonObject=WeixinUtil.httpRequest(url, "POST",sb.toString());
		  
		 System.out.println(">>>>>>>>>> ticket "+jsonObject.get("ticket"));
//		 System.out.println(">>>>>>>>>> expire_seconds  "+jsonObject.get("expire_seconds "));
//		 System.out.println(">>>>>>>>>> url "+jsonObject.get("url"));
		  
		 return jsonObject;
	}
	@Override
	public JSONObject processTicketByToken() {
		AccessToken accessToken = processAccessToken();
		String date1 = new Date().getTime()+"666666"+new Date().getTime();
        String token=accessToken.getToken();
//		System.out.println(" >>>>>>>> token"+token);
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token+"";
		StringBuilder sb = new StringBuilder();
		sb.append("{\"expire_seconds\":2592000,");
		sb.append("\"action_name\":\"QR_SCENE\",");
		sb.append("\"action_info\": { \"scene\":  {\"scene_id\":"+date1+"}}} ");
		 
		 //String paramJoson = "{\\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": \{\"scene\"\: \{\"scene_str\": \"123\"}}}";
		 JSONObject jsonObject=WeixinUtil.httpRequest(url, "POST",sb.toString());
		  System.out.println(jsonObject.toString());
		 System.out.println(">>>>>>>>>> ticket "+jsonObject.get("ticket"));
//		 System.out.println(">>>>>>>>>> expire_seconds  "+jsonObject.get("expire_seconds "));
//		 System.out.println(">>>>>>>>>> url "+jsonObject.get("url"));
		  
		 return jsonObject;
	}
	
	 public String[] getUserOpenIdList() {
	        String token=processAccessToken().getToken();
	        // 获取网页源代码
//	        String html = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+str1;
//	        // 从网页中抽取信息
////	        String result = httpRequest(html);
//	        String result ="";
//	        JSONObject jsonObject=WeixinUtil.httpRequest(html, "GET", null);
////	        JSONArray list=jsonObject.getJSONArray("data");
////	        JSONObject info=list.getJSONObject(1);
////	        String name=info.getString("openid");
//	        String tempStr2=jsonObject.getJSONObject("data").getString("openid");
//	        tempStr2=tempStr2.substring(1,tempStr2.length()-1);
//	       String[] arryStrs=tempStr2.split(",");
//	       String tempStr3=arryStrs[3];
//	       tempStr3=tempStr3.replace("\"", "");
	       
	       // 获取个人信息
	       String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token;
	       JSONObject jsonObject=WeixinUtil.httpRequest(userInfoUrl, "GET", null);
//	       JSONArray list=jsonObject.getJSONArray("data");
	       String tempStr2=jsonObject.getJSONObject("data").getString("openid");
	       tempStr2=tempStr2.substring(1,tempStr2.length()-1);
	       String[] arryStrs=tempStr2.split(",");
	       return arryStrs;
	    }
	 
	 
	 public String[] getUserOpenIdListTest() {
       String token=WeixinUtil.getAccessToken(WeixinUtil.appId, WeixinUtil.appSecret).getToken();
       String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token;
       JSONObject jsonObject=WeixinUtil.httpRequest(userInfoUrl, "GET", null);
//	       JSONArray list=jsonObject.getJSONArray("data");
       String tempStr2=jsonObject.getJSONObject("data").getString("openid");
       tempStr2=tempStr2.substring(1,tempStr2.length()-1);
       String[] arryStrs=tempStr2.split(",");
       return arryStrs;
	 }
	 
	 public WxOpenIds getUserOpenIdList(String next_openId) {
	       String token=WeixinUtil.getAccessToken(WeixinUtil.appId, WeixinUtil.appSecret).getToken();
	       
	       // 获取个人信息
	       String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token;
	       if(next_openId!=null&&!next_openId.isEmpty()){
	    	   userInfoUrl += "&next_openid="+next_openId;
	       }
	       
	       JSONObject jsonObject=WeixinUtil.httpRequest(userInfoUrl, "GET", null);
//	       JSONArray list=jsonObject.getJSONArray("data");
	       String tempStr2=jsonObject.getJSONObject("data").getString("openid");
	       tempStr2=tempStr2.substring(1,tempStr2.length()-1);
	       String[] arryStrs=tempStr2.split(",");
	       WxOpenIds ids = new WxOpenIds();
	       ids.setOpenIds(arryStrs);
	       String next_openid = jsonObject.getString("next_openid");
	       ids.setNext_openId(next_openid);
	       return ids;
	    }
	 
	 
	    
	 
	    public List<UserInfoUtil> getUserInfoList(){
	        String[] arryStrs=getUserOpenIdList();
	        List<UserInfoUtil> list=new ArrayList<UserInfoUtil>();
	        UserInfoUtil userInfo;
	        if(arryStrs!=null){
	            for(int i = 0; i < arryStrs.length; i++){
	                String tempStr=arryStrs[i];
	                tempStr=tempStr.replace("\"", "");
	                userInfo=processUserInfoObject(tempStr);
	                if(userInfo!=null)
	                    list.add(userInfo);
	            }
	        }
	        return list;
	    }
	
	public static void main(String[] args) {
		WeixinAutosendmsgService service = new WeixinAutosendmsgServiceImpl();
		String[] openIds = service.getUserOpenIdList();
		System.out.println(openIds.length);
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		df.format(new Date());
		
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		try

		{
			calendar.add(Calendar.HOUR, -1);//当前系统时间的  前一小时
			String onehourago = df.format(calendar.getTime());
			//System.out.println("====onehourago=====" + onehourago);
			
			
		   Date d1 = df.parse("2015-10-07 1:37:00");
		   
		    Date d2 = df.parse("2004-01-02 11:30:24");
		    long diff = d1.getTime() - d2.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
		    
		    System.out.println((d1.getTime()/1000)+3700 < new Date().getTime()/1000);
		    
		 System.out.println(new Date().getTime()/1000);
		}

		catch (Exception e)

		{

		}*/

	}
}

