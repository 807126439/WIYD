package com.wb.web.system.dto.user;

public class UserIDAndNameDTO {

	private String id;						// 用户id
	private String userName;			  	// 用户名
	private boolean check=false;			
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}

	
	
	
}
