package com.zklc.weishangcheng.member.service;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;

import net.sf.json.JSONObject;
public interface UserService extends IBaseService<Users, Integer> {
	public Users findUserByLoginName(String loginName);    
	/**
	 * 找到最后一个序号
	 * @param orgflag
	 * @return
	 */
	public BigInteger findFinalOrder(String orgflag);
	/**
	 * 查询当前推荐人共推荐了多少用户
	 * @param userId
	 * @return
	 */
	public BigInteger findCountReferrerId(Integer userId);
	/**
	 * 绑定微信用户
	 * @param user 用户
	 * @param openId 微信标识符
	 * @return
	 */
	public String bindWxUser(Users user,String openId);
	
	/**
	 * 迭代查询用户的有效推荐人(支付过才视为有效)
	 * @param user 有效推荐人
	 * @return
	 */
	public Users findReferrerUser(Users user);
	
	

	/**
	 * 统计制定级别人员
	 * @param userId
	 * @param viewLevel 统计几级人员（1-2-3级）
	 * @param name
	 * @return
	 */
	public List countFriFamily(Integer userId,String viewLevel,String name);
	
	
	
	public List findReferrerPeo(Integer userId);
	public Integer findMaxUser1();
	/**
	 * 查询当前用户信息
	 * @param wxopenid
	 * @return
	 */
	public Users findUserMessage(String wxopenid);
	
	public Users findUserByOpenId(String openId);
	
	/**
	 * 查询当前用户前三级用户
	 * @param userId
	 * @return
	 */
	public List findCurrUserParent(Integer userId);
	/**
	 * 创建二维码
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public Object createQrCode(Users user) throws Exception;
	
	public Map<Integer,Integer[]> findAllUserVOS();
	
	public List<Users> findAllUserByNowDate() throws ParseException;
	
	public String fahongbao(String wxOpenId,Integer userId,Integer fromUserId,Integer level,Integer amount,Integer flagCount);
	
	public void sendMsg(String openid,String content);
	/**
	 * 重新生成二维码 返回次数
	 * @param user
	 * @return
	 */
	public Integer createQrCodeAgain(Users user) throws Exception;
	
	Users findbyUnionId(String unionId);
	
	public List findYongin(Integer userId);
	
	/**
	 * 查询用户积分是否超过200，超过后新用户关注不在赠送有效积分
	 * @param userId
	 * @return
	 */
	public boolean checkUserJifen(Integer userId);
	
	/**
	 * 更新用户money
	 * @param user
	 * @param totalMoney 
	 * @return
	 */
	public Users updateMoney(Users user, Double totalMoney);
	/**
	 * 更新用户信息
	 * @param user
	 * @param json
	 * @param request 
	 * @return
	 */
	public JSONObject updateUserInfo(Users user, JSONObject json, HttpServletRequest request);
	
	/**
	 * 更新user数据
	 * @param user
	 * @return
	 */
	public Users updateUserMsg(Users user,List zongJifen);
	
	/**
	 * 更新城市
	 * @param id
	 * @param userId 
	 * @return
	 */
	public boolean updateCity(Integer id, Integer userId);
	
	
	/**
	 * 账号密码登录
	 * @param loginName
	 * @param passWord
	 * @return
	 */
	public Users findUserByLoginNameAndPassword(String loginName, String passWord);
	
}