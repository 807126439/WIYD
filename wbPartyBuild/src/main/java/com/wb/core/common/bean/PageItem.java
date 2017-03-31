package com.wb.core.common.bean;

public class PageItem {

	
	private String name;
	private String urlStr;
	private String on;
	private String other;
	
	public PageItem(){}
	
	public PageItem(String name,String urlStr){
		this.name=name;
		this.urlStr=urlStr;
		
	}
	
	public PageItem(String name, String urlStr, String on) {
		super();
		this.name = name;
		this.urlStr = urlStr;
		this.on = on;
	}
	
	public PageItem(String name, String urlStr, String on,String other) {
		super();
		this.name = name;
		this.urlStr = urlStr;
		this.on = on;
		this.other = other;
		
	}


	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getOn() {
		return on;
	}
	public void setOn(String on) {
		this.on = on;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	
	
}
