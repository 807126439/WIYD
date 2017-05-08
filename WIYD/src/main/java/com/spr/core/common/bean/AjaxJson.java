package com.spr.core.common.bean;

import java.io.Serializable;

public class AjaxJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583127520942070598L;
	public static final String success = "y";
	public static final String error = "n";
	public static final String not_login = "l";
	private String info;
	private String status;
	private Object data;

	
	public AjaxJson(){}
	
	
	public AjaxJson(String info, String status) {
		super();
		this.info = info;
		this.status = status;
	}

	public AjaxJson(String info, String status, Object data) {
		super();
		this.info = info;
		this.status = status;
		this.data = data;
	}



	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	

	
	
	
	
}
