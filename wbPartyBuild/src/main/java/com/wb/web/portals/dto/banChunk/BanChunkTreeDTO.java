package com.wb.web.portals.dto.banChunk;

import com.fasterxml.jackson.annotation.JsonProperty;



public class BanChunkTreeDTO {
	
	private String id;
	private String menuName;
	private Boolean isParent = true;
	private String parentId;
	private Integer level;
	private String activityId;
	private Long linkContentId = 0L;
	

	public BanChunkTreeDTO(){}
	
	
	
	public BanChunkTreeDTO(String id, String menuName, Boolean isParent,
			String parentId, Integer level,String activityId) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.isParent = isParent;
		this.parentId = parentId;
		this.level = level;
		this.activityId = activityId;
	}
	public BanChunkTreeDTO(String id, String menuName, Boolean isParent,
			String parentId, Integer level,String activityId,Long linkContentId) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.isParent = isParent;
		this.parentId = parentId;
		this.level = level;
		this.activityId = activityId;
		this.linkContentId = linkContentId;
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



	public String getActivityId() {
		return activityId;
	}



	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}



	public Long getLinkContentId() {
		return linkContentId;
	}



	public void setLinkContentId(Long linkContentId) {
		this.linkContentId = linkContentId;
	}
	
}
