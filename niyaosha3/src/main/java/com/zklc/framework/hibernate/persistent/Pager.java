package com.zklc.framework.hibernate.persistent;

import java.util.List;

import org.apache.solr.client.solrj.response.FacetField;


/**
 * <p>
 * 功能 分页对象
 * </p>
 * <p>
 * Copyright 北京中科联诚软件有限公司 2013 All right reserved.
 * </p>
 * 
 * @author Administrator 时间 2013-5-30 上午9:54:33
 * @version 1.0 </br> 最后修改人 无
 * @param <T>
 */
public class Pager<T> extends AbstractSymBasePO {

    private String sortField;//排序字段
	private List<T> entityList; // 数据集合
	private int totalCounts;
	private List<FacetField> facetFields;
    public String getSortField(){
    
        return sortField;
    }

    
    public void setSortField(String sortField){
    
        this.sortField = sortField;
    }

    public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8953076928103245224L;
	
	private int totalRows; 
  private int pageSize = 2; 
  private int currentPage; 
  private int totalPages; 
  private int startRow; 
  
  public Pager() {
  }
  
  public Pager(int _totalRows) {
    totalRows = _totalRows;
    totalPages=totalRows/pageSize;
    int mod=totalRows%pageSize;
    if(mod>0){
      totalPages++;
    }
    currentPage = 1;
    startRow = 0;
  }
  
  /**
	 * @return the hasNext
	 */
	public boolean isNext() {

		return this.getCurrentPage() + 1 <= this.getTotalPages();
	}

	/**
	 * @return the hasPrevious
	 */
	public boolean isPrevious() {

		return this.getCurrentPage() - 1 > 0;
	}
  
  public int getStartRow() {
      return startRow;
  }
  public int getTotalPages() {
      return totalPages;
  }
  public int getCurrentPage() {
      return currentPage;
  }
  public int getPageSize() {
      return pageSize;
  }
  public void setTotalRows(int totalRows) {
      this.totalRows = totalRows;
  }
  public void setStartRow(int startRow) {
      this.startRow = startRow;
  }
  public void setTotalPages(int totalPages) {
      this.totalPages = totalPages;
  }
  public void setCurrentPage(int currentPage) {
      this.currentPage = currentPage;
  }
  public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
  }
  public int getTotalRows() {
      return totalRows;
  }
  public void first() {
      currentPage = 1;
      startRow = 0;
  }
  public void previous() {
      if (currentPage == 1) {
          return;
      }
      currentPage--;
      startRow = (currentPage - 1) * pageSize;
  }
  public void next() {
      if (currentPage < totalPages) {
          currentPage++;
      }
      startRow = (currentPage - 1) * pageSize;
  }
  public void last() {
      currentPage = totalPages;
      startRow = (currentPage - 1) * pageSize;
  }
  public void refresh(int _currentPage) {
      currentPage = _currentPage;
      if (currentPage > totalPages) {
          last();
      }
  }
  
  
public List<FacetField> getFacetFields(){

    return facetFields;
}



public void setFacetFields(List<FacetField> facetFields){

    this.facetFields = facetFields;
}


public void localPage() {
  	//startRow = currentPage * pageSize;
  }
	
}
