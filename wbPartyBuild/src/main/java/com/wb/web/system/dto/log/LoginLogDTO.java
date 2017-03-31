package com.wb.web.system.dto.log;

import java.util.Date;

import com.wb.core.common.dto.BaseDto;

public class LoginLogDTO extends BaseDto{

	private String username;     //用户名
    private Long loginIP;		 //登录ip 	
    private String convertIP;
	private Date loginDate;      //登录时间
	private String logDetails;   //登录细节描述
	
	public LoginLogDTO(){}

	
	public LoginLogDTO(String username, Long loginIP, Date loginDate,
			String logDetails) {
		super();
		this.username = username;
		this.loginIP = loginIP;
		this.loginDate = loginDate;
		this.logDetails = logDetails;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(Long loginIP) {
		this.loginIP = loginIP;
	}

	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
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
