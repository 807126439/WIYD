package com.wb.web.system.dto.user;

import com.wb.core.common.dto.BaseQueryDTO;

public class UserQueryDTO extends BaseQueryDTO{

	private String name;
	private String cnName;
	
	private String isIgnoreStat;

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getIsIgnoreStat() {
		return isIgnoreStat;
	}

	public void setIsIgnoreStat(String isIgnoreStat) {
		this.isIgnoreStat = isIgnoreStat;
	}

	


	
}
