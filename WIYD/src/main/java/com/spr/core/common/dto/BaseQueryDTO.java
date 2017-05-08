package com.spr.core.common.dto;

import java.util.Map;

import net.sf.json.JSONArray;

public  class BaseQueryDTO {

	private Integer startQuery = 0;	//查询起始位置
	private Integer pageSize = 10;
	private Integer currentPage = 1;	
	private JSONArray rules; //jqgid传到后台的查询信息
	private String groupOp; //AND 或者 OR
	private String sidx;   //排序字段
	private String sord;   //排序方式
	protected Map<String, Object> queryMap;
	private String sEcho;
	

	
	public Integer getStartQuery() {
		return startQuery;
	}
	public void setStartQuery(Integer startQuery) {
		this.startQuery = startQuery;
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
	public JSONArray getRules() {
		return rules;
	}
	public void setRules(JSONArray rules) {
		this.rules = rules;
	}
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public Map<String, Object> getQueryMap() {
		return queryMap;
	}
	
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	/**
	 * 把当前页转化为开始查询的位置
	 */
	public void initStartQueryFromCurPage(){
        startQuery = (currentPage-1) * pageSize;	
	}
	
}
