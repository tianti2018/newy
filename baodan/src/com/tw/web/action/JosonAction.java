package com.tw.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.hibernate.transaction.JOnASTransactionManagerLookup;
import org.springframework.beans.factory.annotation.Autowired;

import com.tw.web.dao.CardDAO;
import com.tw.web.dao.TAreaDao;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;


@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="joson", value="/resource/json_struts2.jsp")
		}
)
public class JosonAction extends ExtJSONActionSuport {
	
	private CardDAO cardDAO;
	private String cardType;
	private TAreaDao tAreaDao;
	private Integer areaId;
	
	private String pvalue;
	private Integer tflx_id;
	private Integer j_batchparaid;
	
	private String lsh_tf;
	private String j_c_flag;

	
	private String starttime;
	
	private String endtime;
	
	private String dstarttime;
	
	private String dendtime;
	
	private String runtime;
	
	private int batchparaId;
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}

	private String areaCode;
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public CardDAO getCardDAO() {
		return cardDAO;
	}
	@Autowired
	public void setCardDAO(CardDAO cardDAO) {
		this.cardDAO = cardDAO;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
	
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDstarttime() {
		return dstarttime;
	}

	public void setDstarttime(String dstarttime) {
		this.dstarttime = dstarttime;
	}

	public String getDendtime() {
		return dendtime;
	}

	public void setDendtime(String dendtime) {
		this.dendtime = dendtime;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public int getBatchparaId() {
		return batchparaId;
	}

	public void setBatchparaId(int batchparaId) {
		this.batchparaId = batchparaId;
	}

	public Integer getTflx_id() {
		return tflx_id;
	}

	public void setTflx_id(Integer tflx_id) {
		this.tflx_id = tflx_id;
	}		
	
	public Integer getJ_batchparaid() {
		return j_batchparaid;
	}

	public void setJ_batchparaid(Integer j_batchparaid) {
		this.j_batchparaid = j_batchparaid;
	}
		
	public String getLsh_tf() {
		return lsh_tf;
	}

	public void setLsh_tf(String lsh_tf) {
		this.lsh_tf = lsh_tf;
	}		
	
	public String getJ_c_flag() {
		return j_c_flag;
	}
	
	public void setJ_c_flag(String jCFlag) {
		j_c_flag = jCFlag;
	}
	

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public String createOption () throws Exception {
		
		System.out.println("pvalue >>>>>>>>>>>>>  "+pvalue);
		
		// 根据中心查询的所有收费站		
		if ((this.pvalue!=null && StringUtils.isNotEmpty(pvalue))&&(!StringUtils.equals(this.pvalue, "000000"))) {
			List<Zhan> listRKCZhan = searchService.findAllZhanByProperty("parentdm", this.pvalue);
			
			try {				
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String []{"parent","zan"}); // 装换成joson数据时排除掉 不进入死循环的属性(因为这两个属性都是关联自己本身)
			   config.setJsonPropertyFilter(new PropertyFilter(){
			    public boolean apply(Object source, String name, Object value) {
			     if(name.equals("parent") || name.equals("zan")) {
			      return true;
			     } else {
			      return false;
			     }
			    }
			   });

				
				JSONArray jsonArray = JSONArray.fromObject(listRKCZhan,config);			
				this.setJsonString("{success:true,list:" + jsonArray + "}");							
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			
			//this.setJsonString("{success:true,list:" + jsonString(listRKCZhan) + "}");
			return "joson";
		}
		
		// 根据逃费类型得到的所匹配的所有的批次参数
		if (this.tflx_id != null) {
			this.setJsonString("{success:true,list:" + jsonBatchpara(tflx_id) + "}");
			return "joson";
		}			
									
		this.setJsonString("{success:true}");
		
		return "joson";
	}
	
	public String findParamByBatchparaId () throws Exception {
		
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();		
		
		if ("1".equals(j_c_flag)) {
			Batchpara batchpara = searchService.findBatchparaByTflxAndflag(String.valueOf(tflx_id));
			this.j_batchparaid = batchpara==null ? 0: batchpara.getBatchpara_id();
		}
		
		System.out.println(" >>>>>>>>>>>>> j_batchparaid  "+j_batchparaid);
		System.out.println(" >>>>>>>>>>>>> tflx_id  "+tflx_id);
		
		List<Param> litParam = digService.findAllParamByBatchparaId(this.j_batchparaid);				
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String []{"batchpara"});
		for (Param param:litParam) {
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("para_desc", param.getPara_desc());
			paramMap.put("tj_type", param.getTj_type());
			listMap.add(paramMap);					
		}
		
		JSONArray array = JSONArray.fromObject(litParam,config);		
		
		System.out.println(" array.toString  "+array.toString());
		
		this.setJsonString("{success:true,list:" + array.toString() + "}");
		return "joson";
	}
		
	*//**
	 * 
	 *//*
	
	*//**
	 * 
	 * @param listRKCZhan
	 * @return
	 * @throws Exception
	 *//*
	public String jsonString (List<Zhan> listRKCZhan) throws Exception { 		
		try {
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
			for (Zhan zhan:listRKCZhan) {
				Map<String, Object> tree = new HashMap<String, Object>();
				tree.put("zdm", zhan.getZdm());
				tree.put("zmc", zhan.getZmc());
				
				listMap.add(tree);
			}
									
			JSONArray array = JSONArray.fromObject(listMap);
			
			return array.toString();
		}
		catch (Exception ee) {
			ee.getStackTrace();						
		}
		
		return null;
	}
			
	// 根据逃费类型得到的所匹配的所有的批次参数
	public String jsonBatchpara(Integer tflx_idOne) throws Exception { 	
		try {			
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();			
			List<Batchpara> litBatch = digService.findAllBatchparaByTffTypeId("tflx_id", tflx_idOne);
			for (Batchpara batchpara:litBatch) {
				Map<String, Object> batchMap = new HashMap<String, Object>();
				batchMap.put("batchpara_id", batchpara.getBatchpara_id());
				batchMap.put("batchpara_name", batchpara.getBatchpara_name());
				listMap.add(batchMap);
			}
			
			JSONArray array = JSONArray.fromObject(listMap);
			return array.toString();
		} 
		catch (Exception e) {
			e.getStackTrace();					
		}
		return null;
	}
	
	//根据起止日期得批次参数得到当前任务
	public String getTask() throws Exception{
		int flag = 0;
		System.out.println("this.batchparaId" +this.batchparaId);
		Batchpara batchpara = taskService.findBatchparaById(this.batchparaId);
		System.out.println(".............dsadsadsa..");
		FleeFeeType ffty = batchpara.getFleeFeeType();
		Task task = taskService.findtaskBycontion(ffty.getTflx_id(),batchparaId,this.starttime,this.endtime);
		
		if(task != null){
			flag = 1;
			this.setJsonString("{success:true,flag:" + flag + ",taskId:"+task.getTaskid()+"}");
		}else{
		this.setJsonString("{success:true,flag:"+flag+"}");
		}
		return "joson";
	}
	
	public static void main(String args[]){
		
		List<String[]> a = new ArrayList<String[]>();
		a.add(new String[]{"1","2"});
		a.add(new String[]{"3","4"});
		
		
		JSONArray array = JSONArray.fromObject(a);
		System.out.println(array.toString());
		
		
	}
	*/
	
	public String getCardByUserId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Card card = cardDAO.findCardByUserId(cardType,loginUser.getUserId());
		if (null!=card) {
			String p = card.getCardPassword().toString();
			this.setJsonString("{success:true,cardNo:" + card.getCardNo() + ",cardPassword:'"+p+"'}");
		}
		else {
			this.setJsonString("{success:false}");
		}
		return "joson";
	}
	
	public String getAreaListByAreaCode() {
		TArea tArea = (TArea)tAreaDao.findById(areaId);
		List<TArea> tAreas = tAreaDao.findEntityByPropertiName("parentCode", tArea.getAreaCode());
		if (null!=tAreas) {
			JSONArray array = JSONArray.fromObject(tAreas);
			this.setJsonString("{success:true,list:" + array.toString() + "}");
			//return array.toString();
		}
		else {
			this.setJsonString("{success:false}");
		}
		return "joson";
	}
	
	
	public static void main(String args[]){
		List<String[]> a = new ArrayList<String[]>();
		a.add(new String[]{"1","2"});
		a.add(new String[]{"3","4"});
		JSONArray array = JSONArray.fromObject(a);
		System.out.println(array.toString());
	}
	
}
