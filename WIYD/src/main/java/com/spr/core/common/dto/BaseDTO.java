package com.spr.core.common.dto;

import java.util.Date;

import com.spr.core.annotations.DbField;

public class BaseDTO {
	private Long id;
	private Date gmtModified = new Date();
	private Date gmtCreate;
	
	private Long sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@DbField(name="gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	

	
}
