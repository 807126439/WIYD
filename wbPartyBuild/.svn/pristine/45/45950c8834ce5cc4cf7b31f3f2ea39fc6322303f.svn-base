package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

@Entity
@Table(name="c_job_depart_relation")
public class JobDepartRelation extends UUIDEntity implements Serializable{
			
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8402721004068287593L;
	public static final short ENROLLED_STATUS = 1;		//在册
	public static final short NOT_ENROLLED_STATUS = 0;  //不在册
	
	private Department department;		//部门
	private JobDuty jobDuty;			//职务
	private User user;					//用户
	private String memo;				//备注
	private Short status;				//状态
	private Date createTime;
	private String updateBy;
	private Integer sortNum;			//排序
	
	
	
	public JobDepartRelation(){}
	
	
	
	public JobDepartRelation(Department department, JobDuty jobDuty, User user,
			String memo, Short status, String updateBy,Integer sortNum) {
		super();
		this.department = department;
		this.jobDuty = jobDuty;
		this.user = user;
		this.memo = memo;
		this.status = status;
		this.createTime = new Date();
		this.updateBy = updateBy;
		this.sortNum=sortNum;
	}
	
	
	
	public void update(Department department, JobDuty jobDuty, User user,
			String memo, Short status, String updateBy) {
		
		this.department = department;
		this.jobDuty = jobDuty;
		this.user = user;
		this.memo = memo;
		this.status = status;
		this.updateBy = updateBy;
	}
	
	
	@ManyToOne(targetEntity=Department.class)
	@JoinColumn(name="department_id",nullable=false)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	@ManyToOne(targetEntity=JobDuty.class)
	@JoinColumn(name="job_duty_id",nullable=false)
	public JobDuty getJobDuty() {
		return jobDuty;
	}
	public void setJobDuty(JobDuty jobDuty) {
		this.jobDuty = jobDuty;
	}
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="memo",length=100)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Column(name="status")
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
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


	@Column(name="sort_num",length=11)
	public Integer getSortNum() {
		return sortNum;
	}



	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
	
	

}
