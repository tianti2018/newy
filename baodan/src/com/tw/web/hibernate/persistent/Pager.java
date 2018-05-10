package com.tw.web.hibernate.persistent;

import java.util.List;

/**
 * 封装分页记录的POJO类
 * 
 * @author rongxinhua
 * 
 * @param <T>
 *          范型，表示操纵的实体
 */
public class Pager<T> extends AbstractSymBasePO {

	private List<T> entityList; // 对象列表
	private int totalCounts; // 总记录数

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
	
	private int totalRows; //总行数
  private int pageSize = 25; //每页显示的行数
  private int currentPage; //当前页号
  private int totalPages; //总页数
  private int startRow; //当前页在数据库中的起始行
  
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
  
  public void localPage() {
  	//startRow = currentPage * pageSize;
  }
	
}
