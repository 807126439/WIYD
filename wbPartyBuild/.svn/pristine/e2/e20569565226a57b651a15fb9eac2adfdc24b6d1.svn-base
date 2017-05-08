package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 登录日志对象
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_login_log")
public class LoginLog extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1800084416632731833L;
     
	private String userName;     //用户名
    private Long loginIP;		 //登录ip 	
	private Date loginDate;      //登录时间
	private String logDetails;   //登录细节描述
	
	
	public LoginLog(){}
	
	public LoginLog(String userName,Long loginIP,String logDetails){
		this.userName = userName;
		this.loginIP = loginIP;
		this.loginDate = new Date();
		this.logDetails = logDetails;
		
	}
	
	@Column(name="user_name",nullable=false,length=50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="login_ip",nullable=false,length=20)
	public Long getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(Long loginIP) {
		this.loginIP = loginIP;
	}

	@Column(name="login_date")
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Column(name="log_details")
	public String getLogDetails() {
		return logDetails;
	}
	public void setLogDetails(String logDetails) {
		this.logDetails = logDetails;
	}

	
	
}
