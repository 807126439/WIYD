package com.spr.core.common.dto;

import java.util.Date;

import com.spr.core.annotations.DbField;

public class UUIDDTO {

	private String id; // 主键
	private Date gmtModified = new Date();
	private Long sort; // 排序id
	private Date gmtCreate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DbField(name = "gmt_modified")
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
