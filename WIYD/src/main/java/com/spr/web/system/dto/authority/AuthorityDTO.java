package com.spr.web.system.dto.authority;

import org.apache.commons.lang3.StringUtils;

import com.spr.core.common.dto.UUIDDTO;

public class AuthorityDTO extends UUIDDTO{

	private String authCode;
    private String authText;
    private String resourecesUrl;
    private Short authType;
    private Short flag;
    private String icon;
    private Integer authOrder;
    private String parentId;
    private String parentName;
	
	
	
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
		return StringUtils.isBlank(this.resourecesUrl)? null:this.resourecesUrl;
	}
	public void setResourecesUrl(String resourecesUrl) {
		this.resourecesUrl = resourecesUrl;
	}	
	public String getParentId() {
		return StringUtils.isBlank(this.parentId)? null:this.parentId;
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
