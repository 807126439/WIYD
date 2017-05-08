package com.wb.web.system.dto.authority;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;



public class AuthTreeDTO {

	private String id;
	private String authText;
	private Set<AuthTreeDTO> children = new HashSet<AuthTreeDTO>();
	private Integer checkState = 0;
	private Boolean isExpand = false;
	private Boolean isParent ;
	
	public AuthTreeDTO(){}
	
	
	
	public AuthTreeDTO(String id, String authText,Boolean isParent) {
		super();
		this.id = id;
		this.authText = authText;
		this.isParent = isParent;
	}

	@JsonProperty(value="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@JsonProperty(value="text")
	public String getAuthText() {
		return authText;
	}

	

	public void setAuthText(String authText) {
		this.authText = authText;
	}
   

	

	@JsonProperty(value="ChildNodes")
	public Set<AuthTreeDTO> getChildren() {
		return children;
	}

	public void setChildren(Set<AuthTreeDTO> children) {
		this.children = children;
	}

	@JsonProperty(value="checkstate")
	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}
	

	@JsonProperty(value="isexpand")
	public Boolean getIsExpand() {
		return isExpand;
	}

	public void setIsExpand(Boolean isExpand) {
		this.isExpand = isExpand;
	}
	

	@JsonProperty(value="showcheck")
	public Boolean getShowCheck(){
		return true;
	}
	

	@JsonProperty(value="hasChildren")
	public Boolean getHasChildren(){
		if(this.children.size() >0){
			return true;
		
		}else if(isParent!=null){
			return isParent;
		}
		
		return  false;
	}
	
	
	
}
