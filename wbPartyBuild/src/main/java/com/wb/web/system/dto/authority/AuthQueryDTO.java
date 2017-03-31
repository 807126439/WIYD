package com.wb.web.system.dto.authority;

import com.wb.core.common.dto.BaseQueryDTO;

public class AuthQueryDTO extends BaseQueryDTO{

	private String authName;
	private String authCode;
	private String parAuthName;
	private String parId;
	private Short flag; 				
	private Short authType; 

	
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}	
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getParId() {
		return parId;
	}
	public void setParId(String parId) {
		this.parId = parId;
	}
	public String getParAuthName() {
		return parAuthName;
	}
	public void setParAuthName(String parAuthName) {
		this.parAuthName = parAuthName;
	}
	public Short getFlag() {
		return flag;
	}
	public void setFlag(Short flag) {
		this.flag = flag;
	}
	public Short getAuthType() {
		return authType;
	}
	public void setAuthType(Short authType) {
		this.authType = authType;
	}

	
	
	
	
	
}
