package com.zklc.framework.hibernate.persistent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>
 * 功能 封装的JQGRID PAGE对象 包括分页、查询、排序信息
 * </p>
 * <p>
 * Copyright 北京中科联城软件有限公司2013 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2013-4-11 下午5:02:15
 * @version 1.0 </br> 最后修改人 无
 */
public class JqgridPager{
	
	private static final int OPERTOR_EQ = 0;
	private static final int OPERTOR_NOT_EQ = 1;
	private static final int OPERTOR_LIKE = 2;
	private static final int OPERTOR_NOT_LIKE = 3;		
	private static final int OPERTOR_IN = 4;
	private static final int OPERTOR_NOT_IN = 5;
	private static final int OPERTOR_GT = 6;
	private static final int OPERTOR_LT = 7;
	private static final int OPERTOR_GE = 8;
	private static final int OPERTOR_LE = 9;
	private static final int OPERTOR_BETWEEN = 10;
	private static final int OPERTOR_NOT_BETWEEN = 11;
	
	
	private String page="1";//当前页
    private Integer total=1;//总页数
    private String oper;
    private Integer rows=10;//页显示行数
    private Integer records;
    private String sidx;
	private String sord;
    
    private String filters;
    private String searchField;//查询字段
	private String searchString;//查询字段值
	private String searchOper;//查询条件
	private String rules;
	private boolean _search; //是否查询
	private Map queryMap ;//多条件查询MAP
	private Map opertorMap ;//多条件查询条件集
	private List datalist ;//数据集合
	private Map paramsMap;//传递参数集
	public Map getParamsMap() {
		return paramsMap;
	}
	public void setParamsMap(Map paramsMap) {
		this.paramsMap = paramsMap;
	}
	public List getDatalist() {
		return datalist;
	}
	public void setDatalist(List datalist) {
		this.datalist = datalist;
	}
	
	
	public Map getQueryMap() {
		return queryMap;
	}
	public void setQueryMap(Map queryMap) {
		this.queryMap = queryMap;
	}
	public Map getOpertorMap() {
		return opertorMap;
	}
	public void setOpertorMap(Map opertorMap) {
		this.opertorMap = opertorMap;
	}

	
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public boolean is_search() {
		return _search;
	}
	public void set_search(boolean _search) {
		this._search = _search;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Integer getTotal() {	
		
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	
	public JqgridPager(){
		
	}
	public JqgridPager(String page, Integer total, String oper,String sidx,
			String sord, Integer rows, Integer records,
			String filters, String searchField, String searchString,
			String searchOper, String rules, boolean _search) {
		this.page=page;
		this.total=total;
		this.oper=oper;
		this.sidx=sidx;
		this.sord=sord;
		this.rows=rows;
		this.records=records;
		this.filters=filters;
		this.searchField=searchField;
		this.searchString=searchString;
		this.searchOper=searchOper;
		this.rules=rules;
		this._search=_search;
		Map queryMap = new HashMap();
        Map opertorMap = new HashMap();
        String searchFieldx = "";
        if(_search == true && searchString != null && !"".equals(searchString) && (filters == null || "".equals(filters))){ // 单条件查询
            queryMap.put(searchField, searchString);
            opertorMap.put(searchField, returnOperValue(searchOper));
        }else if(!StringUtils.isEmpty(filters)){ // 多条件查询
        	JSONObject filt = JSONObject.fromObject(filters);
            JSONArray _rules = filt.getJSONArray("rules");
            for(int i = 0; i < _rules.size(); i++){
                JSONObject rule_ = (JSONObject)_rules.get(i);
                // 字段
                searchFieldx = (String)rule_.get("field");
                // 数值
                queryMap.put(searchFieldx, rule_.get("data"));
                // 判断符号
                opertorMap.put(searchFieldx, returnOperValue((String)rule_.get("op")));
            }        
        }
		this.opertorMap=opertorMap;
		this.queryMap=queryMap;
	}

	
	public static Integer returnOperValue(String searchOper){
		
		if ("eq".equals(searchOper)) {  //等于
            return OPERTOR_EQ;
        } else if ("ne".equals(searchOper)) {    //不等于 
        	return OPERTOR_NOT_EQ;
        }else if ("cn".equals(searchOper)) {  //包括
        	return OPERTOR_LIKE;
        } else if ("nc".equals(searchOper)) {  //不包括
        	return OPERTOR_NOT_LIKE;
        }else if ("in".equals(searchOper)) {  //在内
        	return OPERTOR_IN;
        } else if ("ni".equals(searchOper)) {  // 不在内
        	return OPERTOR_NOT_IN;
        } else if ("gt".equals(searchOper)) {   //大于
        	return OPERTOR_GT;
        }else if ("lt".equals(searchOper)) {   //小于   
        	return OPERTOR_LT;
        }  else if ("ge".equals(searchOper)) {   //大于等于  
        	return OPERTOR_GE;
        }  else if ("le".equals(searchOper)) {  //小于等于  
        	return OPERTOR_LE;
        } else if ("bw".equals(searchOper)) {  //以...开始  
        	return OPERTOR_BETWEEN;
        } else if ("bn".equals(searchOper)) {  //不以...开始  
        	return OPERTOR_NOT_BETWEEN;
        } 
		return null;
	}
	public int getFirstResult(){
		return getRows() * (Integer.parseInt(getPage()) - 1);
	}
}
