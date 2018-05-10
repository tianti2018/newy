package com.tw.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.util.AlipayNotify;
import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.CardDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.User;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAlipay", 			value="/WEB-INF/jsp/products/fukuan.jsp"),
			@Result(name="getCard", 			value="/WEB-INF/jsp/products/getCard.jsp"),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="alipay", params = {"method", "initAlipay"})
		}
)
public class AlipayAction extends ExtJSONActionSuport {
	private String message;
	private CardDAO cardDAO;
	private FhRecordDAO fhRecordDAO;
	private Integer numData;
	
	public Integer getNumData() {
		return numData;
	}
	public void setNumData(Integer numData) {
		this.numData = numData;
	}
	public CardDAO getCardDAO() {
		return cardDAO;
	}
	@Autowired
	public void setCardDAO(CardDAO cardDAO) {
		this.cardDAO = cardDAO;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}
	public String  initAlipay() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		Double totalAllJiangjing = fhRecordDAO.findAllJiangjing(loginUser.getUserId());
		ServletActionContext.getRequest().setAttribute("totalAllJiangjing",totalAllJiangjing);
		return "initAlipay";
	}
	public String jifengoumaicard() {
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		Double totalAllJiangjing = fhRecordDAO.findAllJiangjing(loginUser.getUserId());
		if ((numData*660.0)>totalAllJiangjing) {
			ServletActionContext.getRequest().setAttribute("message","对不起，您的积分不足！！！");
			return "goBackList";
		}
		else {
			cardDAO.updateCardInCardNo(loginUser.getUserId(),numData,"1",loginUser.getUserId());
			FhRecord fhRecord = new FhRecord();
			fhRecord.setFhmoney(-numData*660.00);
			//fhRecord.setUserId(loginUser.getUserId());
			fhRecord.setCreateDate(new Date());
			fhRecord.setFhType("7");
			fhRecord.setUser(loginUser);
			fhRecordDAO.create(fhRecord);
			
			ServletActionContext.getRequest().setAttribute("message","恭喜您积分兑换成功！！！");
		}
		return "goBackList";
	}
	public String obtainCard() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		Card card = cardDAO.findCardByCardTypeAndCardStatus("1");
		if (null != card) {
			card.setUserId(user.getUserId());
			cardDAO.update(card);
		}
		
		request.setAttribute("card", card);
		
		return "getCard";
	}
	
	public String validatePay() throws UnsupportedEncodingException {
		Map<String,String> params = new HashMap<String,String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
			}
			
			//该页面可做页面美工编辑
			//out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			
			request.setAttribute("message", "支付成功");
			return "initAlipay";

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			request.setAttribute("message", "支付失败");
			return "initAlipay";
		}
	}
}
