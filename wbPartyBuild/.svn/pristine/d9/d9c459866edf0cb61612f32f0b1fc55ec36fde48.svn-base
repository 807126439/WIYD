package com.wb.web.system.dto.authority;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthZtreeDto {

	private String id;
	private String authName;
	private Boolean isParent = true;
	private String parentId;
	private Integer level;
	

	public AuthZtreeDto(){}
	
	
	
	public AuthZtreeDto(String id, String authName, Boolean isParent,
			String parentId, Integer level) {
		super();
		this.id = id;
		this.authName = authName;
		this.isParent = isParent;
		this.parentId = parentId;
		this.level = level;
	}
	
	
	
	//@JsonIgnore
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	
	
	@JsonProperty(value="name")
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	@JsonProperty(value="pid")	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	
	
	
	
}
