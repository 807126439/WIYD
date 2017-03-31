package com.wb.web.system.dto.authority;

import com.wb.core.common.dto.UUIDDto;

public class AuthorityDTO extends UUIDDto{

	private String authText;			//权限名
	private String authCode;		    //权限码	
	private String resourecesUrl;		//资源地址
	private String parentId;			//父权限
	private String parentName;			//父权限名称
	private Short flag; 				//状态          0：普通状态  1：开启前台控制
	private Short authType; 			//权限类型  0：菜单  1：访问 （菜单用于控制后台菜单，security只加载访问类型的权限）
	private Integer authOrder; 			//排序
	private String icon;            	//图案

	
	
	
	public String getAuthText() {
		return authText;
	}
	public void setAuthText(String authText) {
		this.authText = authText;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getResourecesUrl() {
		return resourecesUrl;
	}
	public void setResourecesUrl(String resourecesUrl) {
		this.resourecesUrl = resourecesUrl;
	}	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	public Integer getAuthOrder() {
		return authOrder;
	}
	public void setAuthOrder(Integer authOrder) {
		this.authOrder = authOrder;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}	

	
	
	
	
}
