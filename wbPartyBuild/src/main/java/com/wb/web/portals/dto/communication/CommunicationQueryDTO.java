package com.wb.web.portals.dto.communication;

import com.wb.core.common.dto.BaseQueryDTO;

public class CommunicationQueryDTO extends BaseQueryDTO{
	
	
	private Boolean isActive;
	private Boolean isFiled;
	private String 	title;
	private String sponsor;
	private Short status;   // 活跃-1 归档-2	

	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsFiled() {
		return isFiled;
	}
	public void setIsFiled(Boolean isFiled) {
		this.isFiled = isFiled;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}


}
