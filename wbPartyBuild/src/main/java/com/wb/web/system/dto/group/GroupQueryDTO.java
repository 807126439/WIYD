package com.wb.web.system.dto.group;

import com.wb.core.common.dto.BaseQueryDTO;

public class GroupQueryDTO extends BaseQueryDTO{

	private String fondId;

	public GroupQueryDTO(){}
	
	
	public GroupQueryDTO(String fondId) {
		super();
		this.fondId = fondId;
	}

	public String getFondId() {
		return fondId;
	}

	public void setFondId(String fondId) {
		this.fondId = fondId;
	}
	
	
	
}
