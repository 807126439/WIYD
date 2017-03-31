package com.wb.web.party.dto;

import com.wb.core.common.dto.BaseQueryDTO;

public class JoinPartyQueryDTO extends BaseQueryDTO{
	
	private String userId;
	private String applyUserName;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	
	
	
	
	
}
