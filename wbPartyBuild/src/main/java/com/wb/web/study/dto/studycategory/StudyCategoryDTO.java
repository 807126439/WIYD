package com.wb.web.study.dto.studycategory;

import java.util.Date;

public class StudyCategoryDTO {
	
	private Long id;
	private Date lastOperatorTime = new Date(); //上次操作时间
	private String cateName;   //类别名称
	private String cateNum;	   //类别编号 	
	private Date createTime;   //创建时间	

	
	
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getCateNum() {
		return cateNum;
	}
	public void setCateNum(String cateNum) {
		this.cateNum = cateNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getLastOperatorTime() {
		return lastOperatorTime;
	}
	public void setLastOperatorTime(Date lastOperatorTime) {
		this.lastOperatorTime = lastOperatorTime;
	}
	
	
}
