package com.wb.web.study.dto.studycategory;

import com.wb.core.common.dto.BaseQueryDTO;

public class StudyCategoryQueryDTO extends BaseQueryDTO{

	private String cateName;   //类别名称
	private String cateNum;	   //类别编号 	
	
	
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
	
	
}
