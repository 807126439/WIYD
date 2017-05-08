package com.spr.core.common.bean;

public class CommonParam {

	private String filedName;
	private Object value;
	private Object excludeId;
	
	public CommonParam(){}
	
	
	public CommonParam(String filedName, Object value) {
	
		this.filedName = filedName;
		this.value = value;
	}

	public CommonParam(String filedName, Object value, Object excludeId) {
		super();
		this.filedName = filedName;
		this.value = value;
		this.excludeId = excludeId;
	}


	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Object getExcludeId() {
		return excludeId;
	}

	public void setExcludeId(Object excludeId) {
		this.excludeId = excludeId;
	}
	
	
	
	
	
}
