package com.wb.web.system.dto.authority;

public class AuthTreeQueryDTO {

	private String pid;
	private Integer level;
	private String rid;
	
	
	public AuthTreeQueryDTO(){}
	
	public AuthTreeQueryDTO(String rid,String pid){
		this.rid = rid;
		this.pid = pid;
	}
	
	
	public AuthTreeQueryDTO(String pid, Integer level) {
		super();
		this.pid = pid;
		this.level = level;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
	
	
	
	
	
	
}