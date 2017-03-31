package com.wb.web.portals.dto.column;

public class ColumnZtreeDTO {

	private String id;
	private String pid;
	private String name;
	private Boolean isParent;
	private Boolean open;
	
	public ColumnZtreeDTO(){}
	
	
	
	
	
	
	public ColumnZtreeDTO(String id, String pid, String name, Boolean isParent) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
		this.open = isParent;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	
	
	
}
