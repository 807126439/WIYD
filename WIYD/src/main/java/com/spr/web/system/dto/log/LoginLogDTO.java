package com.spr.web.system.dto.log;

import java.util.Date;

import com.spr.core.common.dto.BaseDTO;

public class LoginLogDTO extends BaseDTO{

	private String userName;     //用户名
    private Long loginIP;		 //登录ip 	
    private String convertIP;
	private String logDetails;   //登录细节描述
	
	public LoginLogDTO(){}

	
	public LoginLogDTO(String userName, Long loginIP, Date loginDate,
			String logDetails) {
		super();
		this.userName = userName;
		this.loginIP = loginIP;
		this.logDetails = logDetails;
	}
	
	
	

	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Long getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(Long loginIP) {
		this.loginIP = loginIP;
	}

	public String getLogDetails() {
		return logDetails;
	}
	public void setLogDetails(String logDetails) {
		this.logDetails = logDetails;
	}
	public String getConvertIP() {
		return convertIP;
	}


	public void setConvertIP(String convertIP) {
		this.convertIP = convertIP;
	}
	
	
	
	
	
}
