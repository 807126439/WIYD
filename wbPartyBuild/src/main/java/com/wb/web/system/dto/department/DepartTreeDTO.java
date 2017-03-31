package com.wb.web.system.dto.department;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartTreeDTO {
	
	private String id;
	private String menuName;
	private Boolean isParent = true;
	private String parentId;
	private Integer level;
	

	public DepartTreeDTO(){}
	
	
	
	public DepartTreeDTO(String id, String menuName, Boolean isParent,
			String parentId, Integer level) {
		super();
		this.id = id;
		this.menuName = menuName;
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
	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
