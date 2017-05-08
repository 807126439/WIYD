package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;


/**
 * 部门
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_department")
public class Department extends UUIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8290069162344956384L;
	
	public static final short ORG_OTHER_TYPR = 0; //其他 
	public static final short ORG_DW_TYPR = 1;	//党委
	public static final short ORG_DZB_TYPR = 2; //党支部
	public static final short ORG_DXZ_TYPR = 3; //党小组
	
	
	private String departName;		//部门名字
	private String orgCode;			//部门机构代码	
	private String description;		//部门描述
	private Short orgTypeId;		//类型  0:其他   1:党委   2：党支部  3：党小组
	private Boolean isMain;			//是否主部门
	private Short level;			//层级
	private Integer sortNum;		//排序
	private Date createTime; 	    //创建时间
	private Department parent;		//上级部门
	private Set<Department> children; //下级部门
	private String updateBy;
	private Set<JobDepartRelation> relations;
	

	
	public Department(){}
	
	public Department(String id){
		setId(id);
	}
	
	public Department(String departName, String orgCode, String description,
			Short orgTypeId, Short level, Integer sortNum,Department parent,
			String updateBy) {
		super();
		this.departName = departName;
		this.orgCode = orgCode;
		this.description = description;
		this.orgTypeId = orgTypeId == null? 0 :orgTypeId;
		this.level = level;
		this.sortNum = sortNum;
		this.createTime = new Date();
		this.parent = parent;
		this.updateBy = updateBy;
	}
	
	
	public void update(String departName, String orgCode, String description,
			Short orgTypeId, Integer sortNum,String updateBy) {
		
		this.departName = departName;
		this.orgCode = orgCode;
		this.description = description;
		this.orgTypeId = orgTypeId == null? 0 :orgTypeId;
		this.sortNum = sortNum;
		this.updateBy = updateBy;
	}
	
	
	
	@Column(name="depart_name",length=50,nullable=false)
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
			
	@Column(name="org_code",length=50)
	public String getOrgCode() {
		return orgCode;
	}	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(name="description",length=200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="org_type_id")
	public Short getOrgTypeId() {
		return orgTypeId;
	}
	public void setOrgTypeId(Short orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
	

	
	@Column(name="is_main")
	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}

	@Column(name="level")
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	
	@Column(name="sort_num")
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Column(name="create_time",nullable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ManyToOne(targetEntity=Department.class)
	@JoinColumn(name="parent_id")
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	
	@OneToMany(targetEntity=Department.class,cascade={CascadeType.REMOVE})
	@JoinColumn(name="parent_id")
	public Set<Department> getChildren() {
		return children;
	}
	public void setChildren(Set<Department> children) {
		this.children = children;
	}
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@OneToMany(targetEntity=JobDepartRelation.class,cascade={CascadeType.REMOVE})
	@JoinColumn(name="department_id")
	public Set<JobDepartRelation> getRelations() {
		return relations;
	}
	public void setRelations(Set<JobDepartRelation> relations) {
		this.relations = relations;
	}
	
	
	
	
	
	
}
