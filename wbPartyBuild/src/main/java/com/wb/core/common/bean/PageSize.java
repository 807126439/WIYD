package com.wb.core.common.bean;

public class PageSize {
  
	private Integer size;
	private String urlStr;
	
	public PageSize(){}
	
	public PageSize(Integer size,String urlStr){
		this.size=size;
		this.urlStr=urlStr;
	}
	
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	
	
}
