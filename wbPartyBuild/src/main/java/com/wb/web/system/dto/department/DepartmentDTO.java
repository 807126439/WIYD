package com.wb.web.system.dto.department;

import java.util.Date;

import com.wb.core.common.dto.UUIDDto;

public class DepartmentDTO extends UUIDDto{
	
	private String departName;		//部门名字
	private String orgCode;			//部门机构代码	
	private String description;		//部门描述
	private Short orgTypeId;		//类型
	private Short level;			//层级
	private Integer sortNum;		//排序
	private Date createTime; 	    //创建时间
	private String updateBy;
	private String parentId;
	private String parentName;
	private Boolean isMain=false;			//是否主部门
	
	
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Short getOrgTypeId() {
		return orgTypeId;
	}
	public void setOrgTypeId(Short orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Boolean getIsMain() {
		return isMain;
	}
	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}
	
	
	
	
}
