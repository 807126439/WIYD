package com.spr.core.common.bean;

import net.sf.json.JSONArray;

public class Page2 {

	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private JSONArray list = new JSONArray();
	private Long recTotal = 0L;
	private Long loadedNum;
	
	
	public Page2() {
		super();
	}

	public Page2(Integer currentPage, Integer pageSize,JSONArray list,
			Long recTotal) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.list = list;
		this.recTotal = (recTotal == null? 0l:recTotal);
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	

	public JSONArray getList() {
		return list;
	}

	public void setList(JSONArray list) {
		this.list = list;
	}

	public Long getRecTotal() {
		return recTotal;
	}

	public void setRecTotal(Long recTotal) {
		this.recTotal = recTotal;
	}

	public Long getTotalPage(){
		if(this.recTotal % this.pageSize == 0){
			return this.recTotal / this.pageSize; 
		}else{
			return this.recTotal / this.pageSize + 1;
		}
	}
	
  public Long getLoadedNum(){
		
		return (long) ((this.currentPage-1)*this.pageSize+this.list.size());
	}
	
}
