package com.wb.web.study.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;


/**
 * 学习类别、试卷类别
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_study_category")
public class StudyCategory extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4305867967923130500L;
	
	private String cateName;   //类别名称
	private String cateNum;	   //类别编号
	private Date createTime;   //创建时间
	private String updateBy;   //修改者
	
	public StudyCategory (){}
	
	public StudyCategory(Long id){
		setId(id);
	}
	
	
	public StudyCategory(String cateName, String cateNum, 
			String updateBy) {
		super();
		this.cateName = cateName;
		this.cateNum = cateNum;
		this.createTime = new Date();
		this.updateBy = updateBy;
	}
	
	@Column(name="cate_name",nullable=false,length=50)
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	@Column(name="cate_num",nullable=false,length=20)
	public String getCateNum() {
		return cateNum;
	}
	public void setCateNum(String cateNum) {
		this.cateNum = cateNum;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	
	
	
}
