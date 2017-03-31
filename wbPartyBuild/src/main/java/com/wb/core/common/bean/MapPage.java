package com.wb.core.common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPage<T> {

	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private Map<String, List<T>> mapList = new HashMap<String, List<T>>();
	private Long recTotal = 0L;
	
	
	
	public MapPage() {
		super();
	}

	public MapPage(Integer currentPage, Integer pageSize, Map<String, List<T>> mapList,
			Long recTotal) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.mapList = mapList;
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
	
	public Map<String, List<T>> getMapList() {
		return mapList;
	}

	public void setMapList(Map<String, List<T>> mapList) {
		this.mapList = mapList;
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
	

	
}
