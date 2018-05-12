package com.test;

import java.util.Date;

import com.utils.Sha1Util;

//import redis.clients.jedis.Jedis;

public class TestJedis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//String ss = "{"status":"1203","msgid":"","description":"不支持该流量包"}";
		/*String[] result = {"status":"1","msgid":"1512012236302616","description":"成功"};
		JSONObject  dataJson=new JSONObject("{\"status\":\"1\",\"msgid\":\"1512012236302616\",\"description\":\"成功\"}');
				JSONObject  response=dataJson.getJSONObject("response");
				JSONArray data=response.getJSONArray("data");
				JSONObject info=data.getJSONObject(0);
				String province=info.getString("province");
				String city=info.getString("city");
				String district=info.getString("district");
				String address=info.getString("address");
				 System.out.println(province+city+district+address);*/
		
		Long time = new Date().getTime();
		System.out.println(time);
		String ssString = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VAaXLeCMJt2uwZYlExRStSo_zG82u9srWjAhPPHgzwdzsA538KmvSdmYHDmNj9L6qg"+
		"&noncestr=cvbffddddCXX&timestamp="+time+"&url=http://mp.weixin.qq.com?params=value";
		System.out.println(Sha1Util.getSha1(ssString));
          
     
	
	}

}
