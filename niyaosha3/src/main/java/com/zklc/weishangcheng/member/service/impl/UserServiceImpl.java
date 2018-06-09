package com.zklc.weishangcheng.member.service.impl;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.util.ERCodeUtil;
import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.UserDao;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.OrderJinHuoService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.util.UserInfoUtil;

import net.sf.json.JSONObject;
@Service
public class UserServiceImpl extends BaseServiceImp<Users, Integer> implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	
	@Autowired
	private UseryService useryService;
	@Autowired
	private OrderJinHuoService orderService;
	
	@Override
	public Users findUserByLoginName(String loginName) {
		Users returnUser=null;
		StringBuffer hql = new StringBuffer("from Users t where 1=1 ");
		if(loginName!=null&&!"".equals(loginName)){
			hql.append(" and t.loginName='"+loginName+"'");
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnUser=(Users) list.get(0);
		}else{
			return null;
		}
		return returnUser;
	}

	@Override
	public BigInteger findFinalOrder(String orgflag) {
		String hql="SELECT max(orderOne)+1 from user_order where 1=1 ";
		if(orgflag!=null&&!"".equals(orgflag)){
			hql+="and  flagorg is null or  flagorg='"+orgflag+"'";
			List<BigInteger> noNum =super.findBySql(hql.toString(), null);
			if (null!=noNum) {
				return null==noNum.get(0)?null:noNum.get(0);
			}
		}else{
			return null;
		}
		return null;
	}
	
	@Override
	public Users findUserByOpenId(String openId) {
		Users returnUser=null;
		StringBuffer hql = new StringBuffer("from Users t where 1=1 ");
		if(openId!=null&&!"".equals(openId)){
			hql.append(" and t.wxOpenid='"+openId+"'");
			System.out.println("weixinOpenId======="+hql);
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnUser=(Users) list.get(0);
		}else{
			return null;
		}
		return returnUser;
	}
	@Override
	public BigInteger findCountReferrerId(Integer userId) {
		String hql="SELECT count(referrerId) from users u JOIN user_order o ON u.userId=o.userId  ";
		if(userId!=null&&!"".equals(userId)){
			hql+="and  u.referrerId="+userId;
			List<BigInteger> noNum =super.findBySql(hql.toString(), null);
			if (null!=noNum) {
				return null==noNum.get(0)?null:noNum.get(0);
			}
		}else{
			return null;
		}
		return null;
	}

	@Override
	public String bindWxUser(Users user, String openId) {
		if(openId!=null){
//			List<Users> userList=super.findByProperty("wxOpenid", openId);
//			if(userList!=null){
//				for (int i = 0; i < userList.size(); i++) {
//					userList.get(i).setWxOpenid(null);
//				}
//				super.saveOrUpdateAll(userList);
//			}
//			user.setWxOpenid(openId);
			super.update(user);
		}
		return null;
	}

	@Override
	public Users findReferrerUser(Users user) {
		Users returnUser=null;
//		returnUser=super.findUniqueByProperty("userId", user.getReferrerId());
//		if(returnUser!=null){
//			while(returnUser.getBuyCount()==null||"".equals(returnUser.getBuyCount())){
//				returnUser=super.findUniqueByProperty("userId", returnUser.getReferrerId());
//			}
//		}
		return returnUser;
	}

	@Override
	public Integer findMaxUser1() {
		String hql="SELECT max(userId) from users ";
		System.out.println(">>>>>>>>>>> 您好 ");
		List<Integer> noNum =super.findBySql(hql.toString(), null);
		if (null!=noNum) {
			return null==noNum.get(0)?1:noNum.get(0);
		}
		return null;
	}

	@Override
	public List countFriFamily(Integer userId,String viewLevel,String name) {
		List list = null;
		String sql=null;
		if(userId != null && !"".equals(userId)){ 
				if(viewLevel.equals("all")){
					sql="select count('1')level from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+"  )  and t2.wxOpenid is NOT NULL"+
						" union all "+
						"select count('2')level from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )  and t2.wxOpenid is NOT NULL" +
						" union all "+
						"select  COUNT('3')level from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+")) and t3.wxOpenid is NOT NULL";
				}else if(viewLevel.equals("1")){
					 //sql = "select  t2.userName,date_format(t2.appDate,'%Y-%m-%d %H:%i:%s') createDate,t2.phone,t2.wxOpenid,'' photourl,t2.userId,t2.level from users t1, users t2 where t1.userId=t2.parentId and (t1.userId= "+userId+"  )  and t2.wxOpenid is NOT NULL";
					 sql = "select  t2.userName,date_format(t2.appDate,'%Y-%m-%d %H:%i:%s') createDate,t2.phone,t2.wxOpenid,t2.headUrl,t2.userId,t2.level,t2.oneFlag,t2.twoFlag1,t2.twoFlag2,t2.threeFlag1,t2.threeFlag2 " +
					 		",t2.four1,t2.four2" +
					 		",t2.five1,t2.five2,t2.five3" +
					 		",t2.six1,t2.six2,t2.six3" +
					 		",t2.seven1,t2.seven2,t2.seven3,t2,seven4" +
					 		",t2.eight1,t2.eight2,t2.eight3,t2.eight4" +
					 		",t2.nine1,t2.nine2,t2.nine3,t2.nine4,t2.nine5 " +
					 		",t2.ten1,t2.ten2,t2.ten3,t2.ten4,t2.ten5" +
					 		",t2.eleven1,t2.eleven2,t2.eleven3,t2.eleven4,t2.eleven5" +
					 		",t2.twelve1,t2.twelve2,t2.twelve3,t2.twelve4,t2.twelve5,t2.twelve6" +
					 		",t2.thirteen1,t2.thirteen2,t2.thirteen3,t2.thirteen4,t2.thirteen5,t2.thirteen6" +
					 		",t2.fourteen1,t2.fourteen2,t2.fourteen3,t2.fourteen4,t2.fourteen5,t2.fourteen6,t2.fourteen7  "+
					 		",t2.fifteen1,t2.fifteen2,t2.fifteen3,t2.fifteen4,t2.fifteen5,t2.fifteen6,t2.fifteen7 "+
					 		"    from users t2 where  t2.referrerId= "+userId+" and t2.wxOpenid is NOT NULL";
				}else if(viewLevel.equals("2")){
					 sql = "select   t2.userName,date_format(t2.appDate,'%Y-%m-%d %H:%i:%s') createDate,t2.phone,t2.wxOpenid,t2.headUrl,t2.userId,t2.level,t2.oneFlag,t2.twoFlag1,t2.twoFlag2,t2.threeFlag1,t2.threeFlag2      from users t1, users t2 where t2.referrerId = t1.userId and (t1.referrerId= "+userId+"  )  and t2.wxOpenid is NOT NULL";
				}else{
					 sql = "select  t3.userName,date_format(t3.appDate,'%Y-%m-%d %H:%i:%s') createDate,t3.phone,t3.wxOpenid,t3.headUrl,t3.userId,t3.level,t3.oneFlag,t3.twoFlag1,t3.twoFlag2,t3.threeFlag1,t3.threeFlag2     from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )) and t3.wxOpenid is NOT NULL";
				}
			
			if(null != name && !"".equals(name)){
				if(viewLevel.equals("1") || viewLevel.equals("2")){
					sql += " and t2.userName like '%"+name+"%'";
				}else{
					sql += " and t3.userName like '%"+name+"%'";
				}
				 
			}
			
			 list=super.findBySql(sql, null);
		}else{
			list = new ArrayList();
		}
		


		return list;
	}

	@Override
	public List findReferrerPeo(Integer userId) {
		
		String sql = "select uu.wxOpenid from users uu where uu.userId =(SELECT u.referrerId FROM users u where u.userId = "+userId+")";
		 List list=super.findBySql(sql, null);
		return list;
	}

	@Override
	public Users findUserMessage(String wxopenid) {
		Users user = new Users();
		
		if(null != wxopenid && !"".equals(wxopenid)){

			String sql = "select u.userId,u.userName,(select u.wxOpenid from users u where u.userId =( select u.referrerId from users u where u.wxopenid='"+wxopenid+"')) refname from users u where u.wxopenid='"+wxopenid+"' ";
			List list =  super.findBySql(sql, null);
			if(list.size()>0){
					//推荐人信息
					Object[] obj = (Object[]) list.get(0);
					//当前关注人
					
					user.setUserId( (Integer) obj[0]);
					user.setUserName((String) obj[1]);
//					user.setBlock((String) obj[2]);
			}
		
		}
		
		return user;
	}

	@Override
	public List findCurrUserParent(Integer userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select u1.wxOpenid,u1.userName,'1' level from users u1  where u1.userId=(select u.referrerId from users u where u.userId = "+userId+")");
		sb.append(" union all ");
		sb.append("select uu.wxOpenid,uu.userName,'2' level from users uu where uu.userId = (select u1.referrerId from users u1  where u1.userId=(select u.referrerId from users u where u.userId = "+userId+"))");
		sb.append(" union all ");
		sb.append(" select u3.wxOpenid,u3.userName,'3' level from users u3 where u3.userId=( select uu.referrerId from users uu where uu.userId = (select u1.referrerId from users u1  where u1.userId=( ");
		sb.append("select u.referrerId from users u where u.userId = "+userId+")))");
		List list =  super.findBySql(sb.toString(), null);
		
		
		return list;
	}

	@Override
	public Object createQrCode(Users user) throws Exception{
		Usery usery = useryService.findbyUserId(user.getUserId());
		String userQrCode = "";
		 try { 
            ClientGlobal.init(this.getClass().getResource("/").getPath()+"/fdfs_client.conf");
 
            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;
 
            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
//            NameValuePair nvp [] = new NameValuePair[]{ 
//            		new NameValuePair("width", "750"),
//            	    new NameValuePair("heigth", "1850"),
//            	    new NameValuePair("author", "bai")
//            }; 
//            String wxOpenid = usery.getWxOpenid();
    		JSONObject jsonObject = autosendmsgService.processTicketByToken();
    		String modelPath = ServletActionContext.getServletContext().getRealPath("")+"/images/qrcodeImgs/";
//    		String userQrCode = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())+".png";
    		//chaoguo/src/main/webapp/images/qrcodeImgs
//    		String userImgPathOld = modelPath+userQrCode;
    		BufferedImage d = loadImageLocal(modelPath+"blank.png");//modelPath
    		BufferedImage b = loadImageUrl(user.getHeadUrl());//headImg
    		modifyImage(d,user.getUserName(),355,81);
    		modifyImagetogeter(b, d, 120,120, 110,30);
    		BufferedImage e= ERCodeUtil.createImage(jsonObject.getString("url"), user.getHeadUrl(),true);
            ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
            BufferedImage img = modifyImagetogeter(e, d,400,400, 160,615);
            ImageIO.write(img, "png", os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
            byte bs[]=os.toByteArray();
            String fileIds[]=storageClient.upload_file(bs, "png", null);
//	            String fileIds[] = storageClient.upload_file("c:/activity.jpg", "jpg", nvp);
            System.out.println(fileIds.length); 
            System.out.println("组名：" + fileIds[0]); 
            System.out.println("路径: " + fileIds[1]);
            userQrCode = fileIds[0]+"/"+fileIds[1];
            usery.setTicket(jsonObject.getString("ticket"));
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(new Date());
    		calendar.add(Calendar.DAY_OF_MONTH, 29);
//            usery.setQrCodeDate(calendar.getTime());
            os.close();
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (MyException e) { 
            e.printStackTrace(); 
        } 
		
//		writeImageLocal(userImgPathOld, modifyImagetogeter(e, d,400,400, 160,615));
		
		usery.setQrCode(userQrCode);
		useryService.update(usery);
		return userQrCode;
	}
	
	@Override
	public Integer createQrCodeAgain(Users user) throws Exception {
//		String modelPath = SystemMessage.NGINX_URL+"qrcodeImgs/";
//		JSONObject jsonObject = autosendmsgService.processTicketByToken(user.getWxOpenid());
//		//chaoguo/src/main/webapp/images/qrcodeImgs
//		String userImgPathOld = modelPath+user.getQrCode();
//		BufferedImage d = loadImageLocal(modelPath+"blank.png");//modelPath
//		BufferedImage b = loadImageUrl(user.getHeadUrl());//headImg
//		modifyImage(d,user.getUserName(),375,87);
//		modifyImagetogeter(b, d, 120,120, 110,30);
//		BufferedImage e= ERCodeUtil.createImage(jsonObject.getString("url"), user.getHeadUrl(),true);
//		writeImageLocal(userImgPathOld, modifyImagetogeter(e, d,400,400, 160,615));
//		user.setTicket(jsonObject.getString("ticket"));
		String userQrCode = "";
		try { 
           ClientGlobal.init(this.getClass().getResource("/").getPath()+"/fdfs_client.conf");

           TrackerClient tracker = new TrackerClient(); 
           TrackerServer trackerServer = tracker.getConnection(); 
           StorageServer storageServer = null;

           StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
//           String wxOpenid = user.getWxOpenid();
//           JSONObject jsonObject = autosendmsgService.processTicketByToken(wxOpenid);
//           String modelPath = ServletActionContext.getServletContext().getRealPath("")+"/images/qrcodeImgs/";
//           BufferedImage d = loadImageLocal(modelPath+"blank.png");//modelPath
//           BufferedImage b = loadImageUrl(user.getHeadUrl());//headImg
//           modifyImage(d,user.getUserName(),375,87);
//           modifyImagetogeter(b, d, 120,120, 110,30);
//           BufferedImage e= ERCodeUtil.createImage(jsonObject.getString("url"), user.getHeadUrl(),true);
//           ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
//           BufferedImage img = modifyImagetogeter(e, d,400,400, 160,615);
//           ImageIO.write(img, "png", os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
//           byte bs[]=os.toByteArray();
//           String fileIds[]=storageClient.upload_file(bs, "png", null);
//           System.out.println(fileIds.length); 
//           System.out.println("组名：" + fileIds[0]); 
//           System.out.println("路径: " + fileIds[1]);
//           userQrCode = fileIds[0]+"/"+fileIds[1];
//           user.setTicket(jsonObject.getString("ticket"));
//           os.close();
       } catch (FileNotFoundException e) { 
           e.printStackTrace(); 
       } catch (IOException e) { 
           e.printStackTrace(); 
       } catch (MyException e) { 
           e.printStackTrace(); 
       } 
		
//		user.setQrCode(userQrCode);
//		Integer num = user.getSubmitMoney()-1;
//		user.setSubmitMoney(num);
		update(user);
		return 0;
	}
	
	private Font font = new Font("黑体", Font.PLAIN, 22);// 添加字体的属性设置
	private Graphics2D g = null;
	private int fontsize = 0;
	private int x = 0;
	private int y = 0;
	
	/**
	 * 导入本地图片到缓冲区
	 */
	public BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 导入网络图片到缓冲区
	 */
	public BufferedImage loadImageUrl(String imgName) {
		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				
				ImageIO.write(img, "png", outputfile);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 设定文字的字体等
	 */
	public void setFont(String fontStyle, int fontSize) {
		this.fontsize = fontSize;
		this.font = new Font(fontStyle, Font.PLAIN, fontSize);
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 */
	public BufferedImage modifyImage(BufferedImage img, Object content, int x,
			int y) {

		try {
			//int w = img.getWidth();
			//int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.yellow);//设置字体颜色
			if (this.font != null)
				g.setFont(this.font);
			// 验证输出位置的纵坐标和横坐标
			/*if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}*/
			this.x = x;
			this.y = y;
			if (content != null) {
				g.drawString(content.toString(), this.x, this.y);
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出
	 */
	public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,
			int x, int y, boolean xory) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.BLACK);
			if (this.font != null)
				g.setFont(this.font);
			// 验证输出位置的纵坐标和横坐标
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (contentArr != null) {
				int arrlen = contentArr.length;
				if (xory) {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.x += contentArr[i].toString().length()
								* this.fontsize / 2 + 5;// 重新计算文本输出位置
					}
				} else {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.y += this.fontsize + 2;// 重新计算文本输出位置
					}
				}
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 * 
	 * 时间:2007-10-8
	 * 
	 * @param img
	 * @return
	 */
	public BufferedImage modifyImageYe(BufferedImage img) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.BLACK);//设置字体颜色
			if (this.font != null)
				g.setFont(this.font);
			g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d,int w,int h,int left,int top) {

		try {
			g = d.createGraphics();
			g.drawImage(b, left, top, w, h, null);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return d;
	}

	@Override
	public Map<Integer, Integer[]> findAllUserVOS() {
		List list = new ArrayList();
		list = super.findBySql("select u.userId,u.child1,u.child2,u.child3,u.submitMoney,u.realSubmitMoney from users u",null);
		Map<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();
		for (int i=0;i<list.size();i++) {
			Object[] object = (Object[])list.get(i);
			Integer key = null==object[0]?null:(Integer)object[0];
			if (null==key) {
				continue;
			}
			Integer value1 = null==object[1]?null:(Integer)object[1];
			Integer value2 = null==object[2]?null:(Integer)object[2];
			Integer value3 = null==object[3]?null:(Integer)object[3];
			Integer value4 = null==object[4]?null:(Integer)object[4];
			Integer value5 = null==object[5]?null:(Integer)object[5];
			map.put(key, new Integer[]{value1,value2,value3,value4,value5});
		}
		
		return map;
	}

	@Override
	public List<Users> findAllUserByNowDate() throws ParseException {
		List list = new ArrayList();
		
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(cal.getTime());
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String todayE = sf1.format(cal.getTime());
		list = super.findBySql("select u.userId,u.child1,u.child2,u.submitMoney,u.realSubmitMoney from users u",null);
		return null;
	}

	
	
	
	private String createBillNO(String ordersBH, int amount) {
		String ss="_";
		if(amount==9000){
			ss+=11;
		}else if(amount==18000){
			ss+=21;
		}else if(amount==20000){
			ss+=31;
		}else if(amount==7000){
			ss+=32;
		}
		ss+="_1";
		return ordersBH+ss;
	}

	@Override
	public void sendMsg(String openid, String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Users findbyUnionId(String unionId) {
		List<Users> users = findByProperty("unionid", unionId);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public List findYongin(Integer userId) {
		List list = this.findBySql("select if(sum(y.money) >0,sum(y.money),0),(select if(sum(t.amount) > 0,sum(t.amount),0) from tixianliu t where t.userId = "+userId+" and t.result = 1) from yongjin y where y.toUserId="+userId+" and y.status=1", null); 
		return list;
	}

	@Override
	public boolean checkUserJifen(Integer userId) {
		if(userId != null){
			Users user = this.findById(userId);
			if(user != null){
			}
		}
		return false;
	}

	@Override
	public Users updateMoney(Users user, Double totalMoney) {
		user = findById(user.getUserId());
//		user.setTotalMoney(totalMoney);
		update(user);
		return user;
	}

	@Override
	public JSONObject updateUserInfo(UserVo userVo, JSONObject json,HttpServletRequest request) {
		json.put("success", false);
		if(userVo.getUsery()!=null){
			Usery usery = useryService.findById(userVo.getUsery().getId());
			if(usery!=null){
				UserInfoUtil userInfo = autosendmsgService.processUserInfoObject(usery.getWxOpenid());
				if (userInfo!=null) {
					if(userInfo.getNickname()!=null&&!userInfo.getNickname().equals(""))
						usery.setUserName(userInfo.getNickname());
					if(userInfo.getHeadimgurl()!=null&&!userInfo.getHeadimgurl().equals(""))
						usery.setHeadUrl(userInfo.getHeadimgurl());
					if(userInfo.getSubscribe()!=null&&!userInfo.getSubscribe().equals("")){
						if(userInfo.getSubscribe().equals("1"))
							usery.setSubscribe(0);
						else{
							usery.setSubscribe(1);
						}
					}
					
				}else{
					usery.setSubscribe(1);
				}
			}
			usery.setCreateDate(new Date());
			useryService.update(usery);
			userVo.setUsery(usery);
			request.getSession().setAttribute("loginUser",userVo);
			json.put("success", true);
		}
		return json;
	}

	public void findUserLevelCountNew(List<Integer> userIds,int level,Users user) {
		List list1 = userDao.findAllUserIdByrefferIds(userIds,1,level,0);
		if (list1!=null) {
			int count = Integer.valueOf(list1.size()+"");
			if(level==1){
//				user.setChild1(count);
			}
			if(level==2){
//				user.setChild2(count);
			}
			if(level==3){
//				user.setChild3(count);
			}
		}
		if (level<3) {
			findUserLevelCountNew(list1,++level,user);
		}
	}

	@Override
	public Users updateUserMsg(Users user,List zongJifen) {
		if(user == null){
			return user;
		}
		boolean istrue = false;
		user = this.findById(user.getUserId());
		if(user.getCreateDate()==null){
			user.setCreateDate(new Date(new Date().getTime()-24*60*60*1000));
			//userService.update(user);
			istrue = true;
		}
		//查询星火微积分和已消费积分
		if(zongJifen.size()>0){
			Integer jifen = Integer.parseInt(zongJifen.get(0).toString())-Integer.parseInt(zongJifen.get(1).toString());
//			if(user.getBuyCount() == null || !jifen.equals(user.getBuyCount()) ){
//				user.setBuyCount(jifen);
//				//userService.update(user);
//				istrue = true;
//			}
		}
		
		if(istrue == true){
			this.update(user);
		}
		
		
		return user;
	}

	@Override
	public boolean updateCity(Integer cityId,Integer userId) {
		Users user = findById(userId);
//		if(user.getCity()!=null){
//			return false;
//		}else {
//			user.setCity(cityId);
//			update(user);
//			return true;
//		}
		return false;
	}

	@Override
	public Users findUserByLoginNameAndPassword(String loginName, String passWord) {
		String hql = "from Users where loginName = '"+loginName +"' and passWord ='"+passWord+"'";
		List<Users> users = userDao.findByHql(hql, null);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public String fahongbao(String wxOpenId, Integer userId, Integer fromUserId, Integer level, Integer amount,
			Integer flagCount) {
		// TODO Auto-generated method stub
		return null;
	}

}

