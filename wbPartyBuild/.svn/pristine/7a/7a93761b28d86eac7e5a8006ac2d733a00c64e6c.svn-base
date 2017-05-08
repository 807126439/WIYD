package com.wb.web.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

@Entity
@Table(name="c_edu_degree")
public class EduDegree extends UUIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7756205366573805046L;
	
	
	private String eduName;  //教育程度名
	private String eduText;  //描述	
	private Integer sort;
	
	
	
	public EduDegree(){};
	
	
	public EduDegree(String eduName, String eduText, Integer sort) {
		super();
		this.eduName = eduName;
		this.eduText = eduText;
		this.sort = sort;
	}


	@Column(name="edu_name",length=50)
	public String getEduName() {
		return eduName;
	}
	public void setEduName(String eduName) {
		this.eduName = eduName;
	}
	
	@Column(name="edu_text",length=255)
	public String getEduText() {
		return eduText;
	}
	public void setEduText(String eduText) {
		this.eduText = eduText;
	}
	
	@Column(name="sort")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
