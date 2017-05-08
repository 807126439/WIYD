package com.spr.web.system.entity;

import java.io.Serializable;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.BaseEntity;



public class LoginLog extends BaseEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5859819578779399060L;
	
	private String userId;

    private String userName;

    private String logDetails;

    private Long loginIp;
  
    
    public LoginLog(){}
    
    
    
    public LoginLog(Long id){
    	this.id = id;
    }

 
    public LoginLog(String userName, String logDetails,
			Long loginIp) {
		super();
		this.userName = userName;
		this.logDetails = logDetails;
		this.loginIp = loginIp;
	}
    
    
    
    @DbField(name="user_id")
    public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@DbField(name="user_name")
	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @DbField(name="log_details")
    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails == null ? null : logDetails.trim();
    }

    @DbField(name="login_ip")
    public Long getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(Long loginIp) {
        this.loginIp = loginIp;
    }

   
}