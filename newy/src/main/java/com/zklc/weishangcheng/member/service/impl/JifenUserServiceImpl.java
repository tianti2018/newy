package com.zklc.weishangcheng.member.service.impl;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.util.ERCodeUtil;
import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.FhRecordDao;
import com.zklc.weishangcheng.member.dao.HongBaoDao;
import com.zklc.weishangcheng.member.dao.JifenUserDao;
import com.zklc.weishangcheng.member.hibernate.persistent.ChengFaUser;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.Hongbao;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.TixianLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.ChengFaUserService;
import com.zklc.weishangcheng.member.service.FhrecordService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.OrderService;
import com.zklc.weishangcheng.member.service.TixianLiuService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.XingHuoQuanRecordService;
import com.zklc.weishangcheng.member.util.HongBaoUtil;
import com.zklc.weixin.util.UserInfoUtil;
@Service
public class JifenUserServiceImpl extends BaseServiceImp<JifenUser, Integer> implements JifenUserService {
	
	@Autowired
	private JifenUserDao jifenUserDao;
	@Autowired
	private JiFenRecordService jfrecordService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	@Autowired
	private FhrecordService fhrecordService;
	
	@Autowired
	private FhRecordDao fhRecordDao;
	@Autowired
	private ChengFaUserService chengfaService;
	
	@Autowired
	private HongBaoDao hongBaoDao;
	@Autowired
	private UseryService useryService;
	@Autowired
	private TixianLiuService tixianLiuService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private XingHuoQuanRecordService xingHuoQuanRecordService;
	
	@Override
	public JifenUser findUserByLoginName(String loginName) {
		JifenUser returnUser=null;
		StringBuffer hql = new StringBuffer("from JifenUser t where 1=1 ");
		if(loginName!=null&&!"".equals(loginName)){
			hql.append(" and t.loginName='"+loginName+"'");
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnUser=(JifenUser) list.get(0);
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
	public JifenUser findUserByOpenId(String openId) {
		JifenUser returnUser=null;
		StringBuffer hql = new StringBuffer("from JifenUser t where 1=1 ");
		if(openId!=null&&!"".equals(openId)){
			hql.append(" and t.wxOpenid='"+openId+"'");
			System.out.println("weixinOpenId======="+hql);
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnUser=(JifenUser) list.get(0);
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
	public String bindWxUser(JifenUser user, String openId) {
		if(openId!=null){
			List<JifenUser> userList=super.findByProperty("wxOpenid", openId);
			if(userList!=null){
				for (int i = 0; i < userList.size(); i++) {
					userList.get(i).setWxOpenid(null);
				}
				super.saveOrUpdateAll(userList);
			}
			user.setWxOpenid(openId);
			super.update(user);
		}
		return null;
	}

	@Override
	public JifenUser findReferrerUser(JifenUser user) {
		JifenUser returnUser=null;
		returnUser=super.findUniqueByProperty("userId", user.getReferrerId());
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
	public JifenUser findUserMessage(String wxopenid) {
		JifenUser user = new JifenUser();
		
		if(null != wxopenid && !"".equals(wxopenid)){

			String sql = "select u.userId,u.userName,(select u.wxOpenid from users u where u.userId =( select u.referrerId from users u where u.wxopenid='"+wxopenid+"')) refname from users u where u.wxopenid='"+wxopenid+"' ";
			List list =  super.findBySql(sql, null);
			if(list.size()>0){
					//推荐人信息
					Object[] obj = (Object[]) list.get(0);
					//当前关注人
					
					user.setUserId( (Integer) obj[0]);
					user.setUserName((String) obj[1]);
					user.setBlock((String) obj[2]);
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
	public Object createQrCode(JifenUser user) throws Exception{
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
            usery.setQrCodeDate(calendar.getTime());
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
	public Integer createQrCodeAgain(JifenUser user) throws Exception {
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
           String wxOpenid = user.getWxOpenid();
           JSONObject jsonObject = autosendmsgService.processTicketByToken(wxOpenid);
           String modelPath = ServletActionContext.getServletContext().getRealPath("")+"/images/qrcodeImgs/";
           BufferedImage d = loadImageLocal(modelPath+"blank.png");//modelPath
           BufferedImage b = loadImageUrl(user.getHeadUrl());//headImg
           modifyImage(d,user.getUserName(),375,87);
           modifyImagetogeter(b, d, 120,120, 110,30);
           BufferedImage e= ERCodeUtil.createImage(jsonObject.getString("url"), user.getHeadUrl(),true);
           ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
           BufferedImage img = modifyImagetogeter(e, d,400,400, 160,615);
           ImageIO.write(img, "png", os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
           byte bs[]=os.toByteArray();
           String fileIds[]=storageClient.upload_file(bs, "png", null);
           System.out.println(fileIds.length); 
           System.out.println("组名：" + fileIds[0]); 
           System.out.println("路径: " + fileIds[1]);
           userQrCode = fileIds[0]+"/"+fileIds[1];
           user.setTicket(jsonObject.getString("ticket"));
           os.close();
       } catch (FileNotFoundException e) { 
           e.printStackTrace(); 
       } catch (IOException e) { 
           e.printStackTrace(); 
       } catch (MyException e) { 
           e.printStackTrace(); 
       } 
		
		user.setQrCode(userQrCode);
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
	public List<JifenUser> findAllUserByNowDate() throws ParseException {
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

	public String fahongbao(String wxOpenId,Integer userId,Integer fromUserId,Integer level,Integer amount,Integer flagCount) {
		String message = "";
		
		//查找orderId并且为已经支付的订单
		
		
		//更新状态
		FhRecord fhRecord = fhRecordDao.findFhRecordBytoUserId(userId,fromUserId,amount*1.0,"1",null);
		if (null!=fhRecord) {
			String hql = "from ChengFaUser cf where cf.userId = "+userId+" and money = "+amount*1.0+" and status = 0";
			List<ChengFaUser> chengfas = chengfaService.findByHql(hql, null);
			ChengFaUser chengfa = null;
			if(chengfas.size()>0){
				chengfa = chengfas.get(0);
			}else{
				//发送红包
				message =fahongbaozhu(wxOpenId,userId,amount*100,fhRecord);
			}
			
			if ("发放成功".equals(message)||chengfa!=null) {
				fhRecord.setFlag("2");
				fhRecord.setUpdaDate(new Date());
				fhRecordDao.update(fhRecord);
				
				JifenUser jifenUser = this.findById(fromUserId); //记录卡的发放个数
				switch (flagCount.intValue()) {
					case 1:jifenUser.setOneFlag(1);break;
					case 2:jifenUser.setTwoFlag1(1);break;
					//case 31:jifenUser.setTwoFlag2(1);break;
					case 31:jifenUser.setThreeFlag1(1);break;
					case 32:jifenUser.setThreeFlag2(1);break;
					
//					case 41:jifenUser.setFour1(1);break;
//					case 42:jifenUser.setFour2(1);break;
//					
//					case 51:jifenUser.setFive1(1);break;
//					case 52:jifenUser.setFive2(1);break;
//					case 53:jifenUser.setFive3(1);break;
//					
//					case 61:jifenUser.setSix1(1);break;
//					case 62:jifenUser.setSix2(1);break;
//					case 63:jifenUser.setSix3(1);break;
//					
//					case 71:jifenUser.setSeven1(1);break;
//					case 72:jifenUser.setSeven2(1);break;
//					case 73:jifenUser.setSeven3(1);break;
//					case 74:jifenUser.setSeven4(1);break;
//					
//					case 81:jifenUser.setEight1(1);break;
//					case 82:jifenUser.setEight2(1);break;
//					case 83:jifenUser.setEight3(1);break;
//					case 84:jifenUser.setEight4(1);break;
//					
//					
//					case 91:jifenUser.setNine1(1);break;
//					case 92:jifenUser.setNine2(1);break;
//					case 93:jifenUser.setNine3(1);break;
//					case 94:jifenUser.setNine4(1);break;
//					case 95:jifenUser.setNine5(1);break;
//					
//					case 101:jifenUser.setTen1(1);break;
//					case 102:jifenUser.setTen2(1);break;
//					case 103:jifenUser.setTen3(1);break;
//					case 104:jifenUser.setTen4(1);break;
//					case 105:jifenUser.setTen5(1);break;
//					
//					case 111:jifenUser.setEleven1(1);break;
//					case 112:jifenUser.setEleven2(1);break;
//					case 113:jifenUser.setEleven3(1);break;
//					case 114:jifenUser.setEleven4(1);break;
//					case 115:jifenUser.setEleven5(1);break;
//					
//					case 121:jifenUser.setTwelve1(1);break;
//					case 122:jifenUser.setTwelve2(1);break;
//					case 123:jifenUser.setTwelve3(1);break;
//					case 124:jifenUser.setTwelve4(1);break;
//					case 125:jifenUser.setTwelve5(1);break;
//					case 126:jifenUser.setTwelve6(1);break;
//					
//					case 131:jifenUser.setThirteen1(1);break;
//					case 132:jifenUser.setThirteen2(1);break;
//					case 133:jifenUser.setThirteen3(1);break;
//					case 134:jifenUser.setThirteen4(1);break;
//					case 135:jifenUser.setThirteen5(1);break;
//					case 136:jifenUser.setThirteen6(1);break;
//					
//					case 141:jifenUser.setFourteen1(1);break;
//					case 142:jifenUser.setFourteen2(1);break;
//					case 143:jifenUser.setFourteen3(1);break;
//					case 144:jifenUser.setFourteen4(1);break;
//					case 145:jifenUser.setFourteen5(1);break;
//					case 146:jifenUser.setFourteen6(1);break;
//					case 147:jifenUser.setFourteen7(1);break;
//					
//					case 151:jifenUser.setFifteen1(1);break;
//					case 152:jifenUser.setFifteen2(1);break;
//					case 153:jifenUser.setFifteen3(1);break;
//					case 154:jifenUser.setFifteen4(1);break;
//					case 155:jifenUser.setFifteen5(1);break;
//					case 156:jifenUser.setFifteen6(1);break;
//					case 157:jifenUser.setFifteen7(1);break;
				}
				
				//激活对应级别的用户
				switch (level.intValue()) {
					case 1:jifenUser.setActiviti1(1);break;
					case 2:jifenUser.setActiviti2(1);break;
					case 3:jifenUser.setActiviti3(1);break;
//					case 4:jifenUser.setActiviti4(1);break;
//					case 5:jifenUser.setActiviti5(1);break;
//					case 6:jifenUser.setActiviti6(1);break;
//					case 7:jifenUser.setActiviti7(1);break;
//					case 8:jifenUser.setActiviti8(1);break;
//					case 9:jifenUser.setActiviti9(1);break;
//					case 10:jifenUser.setActiviti10(1);break;
//					case 11:jifenUser.setActiviti11(1);break;
//					case 12:jifenUser.setActiviti12(1);break;
//					case 13:jifenUser.setActiviti13(1);break;
//					case 14:jifenUser.setActiviti14(1);break;
//					case 15:jifenUser.setActiviti15(1);break;
				}
				this.update(jifenUser);
				if(chengfa!=null){
					chengfa.setStatus(1);//更改惩罚为已惩罚
					chengfaService.update(chengfa);
					message = "由于:"+chengfa.getMemo()+",本红包被系统收回!";
				}
			}
		}
		else {
			message = "您已发货或者您正在进行重复提交，请等待服务器的反应...";
		}
		return message;
	}
	@Override
	public String fahongbaoLiu(String wxOpenId,Integer userId,int amount,TixianLiu tx) {
		if(tx == null){
			return "保存失败了";
		}
		//tixianLiuService.save(tx);
		String message="";
		String billNo = HongBaoUtil.createBillNo(String.valueOf(userId));
		SortedMap<String, String> map = HongBaoUtil.createMap(billNo, wxOpenId, String.valueOf(userId),amount);  
		HongBaoUtil.sign(map);  
		String requestXML = HongBaoUtil.getRequestXml(map);  
		try {
			String path="/yunwei8/caoyuan.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/yifei.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = HongBaoUtil.post(requestXML,instream);
			 
			 Document document;
			 document = DocumentHelper.parseText(responseXML);
			 Element root = document.getRootElement();
			 List<Element> elements = root.elements();
			 Element element = elements.get(0);
			 Element element2 = elements.get(1);
			 message=element2.getTextTrim();
			 
			 TixianLiu hongbao = new TixianLiu();
			 hongbao.setAddTime(new Date());
			 hongbao.setOpenid(wxOpenId);
			 hongbao.setAmount(amount/100);
			 hongbao.setBillNo(billNo);
			 hongbao.setUserId(userId);
			 hongbao.setRemark(responseXML);
			 
			 String return_msg = "";
			 String return_code = "";
			 String result_code = "";
			 String send_listid = "";
			 for(Element el:elements){
				 if(el.getName().trim().equals("return_code")){
					 return_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("return_msg")){
					 return_msg = el.getTextTrim();
					 message=return_msg;
				 }
				 if(el.getName().trim().equals("result_code")){
					 result_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("send_listid")){
					 send_listid = el.getTextTrim();
				 }
			 }
			 if (send_listid!="") {
				 hongbao.setResult(1);
				 message="发放成功";
			 }else if(responseXML.contains("请求已受理")){
				 hongbao.setResult(1);
				 message="发放成功";
			 }else{
				hongbao.setResult(0);
			 }
			 tixianLiuService.save(hongbao);
		} 
		catch (KeyManagementException e) {
			e.printStackTrace();
		} 
		catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (CertificateException e) {
			e.printStackTrace();
		} 
		catch (KeyStoreException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return message;
	}

	
	public String fahongbaozhu(String wxOpenId,Integer userId,int amount, FhRecord fhRecord) {
		String message="";
		String billNo = HongBaoUtil.createBillNo();
		SortedMap<String, String> map = HongBaoUtil.createMap(billNo, wxOpenId, String.valueOf(userId),amount);  
		HongBaoUtil.sign(map);  
		String requestXML = HongBaoUtil.getRequestXml(map);  
		try {
			String path="/yunwei8/caoyuan.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/yifei.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = HongBaoUtil.post(requestXML,instream);
			 
			 Document document;
			 document = DocumentHelper.parseText(responseXML);
			 Element root = document.getRootElement();
			 List<Element> elements = root.elements();
			 
			 Hongbao hongbao = new Hongbao();
			 hongbao.setAddTime(new Date());
			 hongbao.setOpenid(wxOpenId);
			 hongbao.setAmount(amount/100);
			 hongbao.setBillNo(billNo);
			 hongbao.setRemark(responseXML);
			 hongbao.setFhrecordId(fhRecord.getFhId());
			 hongbao.setUserId(fhRecord.getUserId());
			 hongbao.setFromUserId(fhRecord.getFromUserId());
			 String return_msg = "";
			 String return_code = "";
			 String result_code = "";
			 String send_listid = "";
			 for(Element el:elements){
				 if(el.getName().trim().equals("return_code")){
					 return_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("return_msg")){
					 return_msg = el.getTextTrim();
					 message=return_msg;
				 }
				 if(el.getName().trim().equals("result_code")){
					 result_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("send_listid")){
					 send_listid = el.getTextTrim();
					 hongbao.setWxBillNo(send_listid);
				 }
			 }
			 if (send_listid!="") {
				 hongbao.setResult(1);
				 message="发放成功";
			 }else if(responseXML.contains("请求已受理")){
				 hongbao.setResult(1);
				 message="发放成功";
			 }else{
				hongbao.setResult(0);
			 }
			 hongBaoDao.save(hongbao);
		} 
		catch (KeyManagementException e) {
			e.printStackTrace();
		} 
		catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (CertificateException e) {
			e.printStackTrace();
		} 
		catch (KeyStoreException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return message;
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
	public JifenUser findbyUnionId(String unionId) {
		List<JifenUser> users = findByProperty("unionid", unionId);
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
			JifenUser user = this.findById(userId);
			if(user != null){
				if(user.getBuyCount() == null || user.getBuyCount() < 200){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public JifenUser updateMoney(JifenUser user, Double totalMoney) {
		user = findById(user.getUserId());
		user.setTotalMoney(totalMoney);
		update(user);
		return user;
	}

	@Override
	public JSONObject updateUserInfo(JifenUser user, JSONObject json,HttpServletRequest request) {
		Usery usery = useryService.findbyUserId(user.getUserId());
		if(usery!=null){
			UserInfoUtil userInfo = autosendmsgService.processUserInfoObject(usery.getWxOpenid());
			if (userInfo!=null) {
				user.setUserName(userInfo.getNickname());
				user.setHeadUrl(userInfo.getHeadimgurl());
				user.setSubscribe(0);
			}else{
				user.setSubscribe(1);
			}
		}
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(usery.getUserId());
		findUserLevelCountNew(userIds,1,user);
		user.setCreateDate(new Date());
		if(user.getLevel()!=null){
			if(user.getLevel()==3){
				if(user.getSubmitMoney()==null||user.getSubmitMoney()<660){
					user.setSubmitMoney(660);
				}
			}else if(user.getLevel()==2){
				if(user.getSubmitMoney()==null||user.getSubmitMoney()<330){
					user.setSubmitMoney(330);
				}
			}else if(user.getLevel()==1){
				if(user.getSubmitMoney()==null||user.getSubmitMoney()<110){
					user.setSubmitMoney(110);
				}
			}
		}
		Integer allxinghuoquan = xingHuoQuanRecordService.findAllMoneyByUserIdAndStatus(user.getUserId(),1);
		Integer xfxinghuoquan = xingHuoQuanRecordService.findAllMoneyByUserIdAndStatus(user.getUserId(),2);
		if(user.getRealSubmitMoney()==null||user.getRealSubmitMoney()!=(allxinghuoquan-xfxinghuoquan)){
			user.setRealSubmitMoney(allxinghuoquan-xfxinghuoquan);
		}
		user.setShappDate(usery.getAppDate());
		Integer totalXfMoney=orderService.findTotalMoneyByUserId(user.getUserId());
		System.out.println(totalXfMoney);
		user.setActiviti14(totalXfMoney);
		update(user);
		request.getSession().setAttribute("loginUser",user);
		json.put("success", true);
		json.put("userName", user.getUserName());
		json.put("headUrl", user.getHeadUrl());
		json.put("child1", user.getChild1());
		json.put("child2", user.getChild2());
		json.put("child3", user.getChild3());
		json.put("xinghuoquan", (allxinghuoquan-xfxinghuoquan));
		return json;
	}

	public void findUserLevelCountNew(List<Integer> userIds,int level,JifenUser user) {
		List list1 = jifenUserDao.findAllUserIdByrefferIds(userIds,1,level,0);
		if (list1!=null) {
			int count = Integer.valueOf(list1.size()+"");
			if(level==1){
				user.setChild1(count);
			}
			if(level==2){
				user.setChild2(count);
			}
			if(level==3){
				user.setChild3(count);
			}
		}
		if (level<3) {
			findUserLevelCountNew(list1,++level,user);
		}
	}

	@Override
	public JifenUser updateUserMsg(JifenUser user,List zongJifen) {
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
			if(user.getBuyCount() == null || !jifen.equals(user.getBuyCount()) ){
				user.setBuyCount(jifen);
				//userService.update(user);
				istrue = true;
			}
		}
		
		if(istrue == true){
			this.update(user);
		}
		
		
		return user;
	}

	@Override
	public boolean updateCity(Integer cityId,Integer userId) {
		JifenUser user = findById(userId);
		if(user.getCity()!=null){
			return false;
		}else {
			user.setCity(cityId);
			update(user);
			return true;
		}
	}

}

