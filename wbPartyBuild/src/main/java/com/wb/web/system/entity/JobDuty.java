package com.wb.web.system.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;


/**
 * 职务
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_job_duty")
public class JobDuty extends UUIDEntity{

	private String jobName;  //职务名称
	private String jobMemo;  //职务备注
	private String jobCode;  //职务码
	private Short level;	 //职务级别
	private String jobNum;   //职务编号
	private Date createTime;  //创建时间
	private String updateBy;
	private Set<JobDepartRelation> relations;
	
	
	public JobDuty(){}
	
	public JobDuty(String id){
		setId(id);
	}
	
	
	public JobDuty(String jobName,String jobCode,String jobMemo, Short level, String jobNum,
			String updateBy) {
		super();
		this.jobName = jobName;
		this.jobCode = jobCode;
		this.jobMemo = jobMemo;
		this.level = level;
		this.jobNum = jobNum;
		this.createTime = new Date();
		this.updateBy = updateBy;
	}
	
	
	public void update(String jobName,String jobCode, String jobMemo, Short level, String jobNum,
			String updateBy) {

		this.jobName = jobName;
		this.jobCode = jobCode;
		this.jobMemo = jobMemo;
		this.level = level;
		this.jobNum = jobNum;
		this.updateBy = updateBy;
		setLastOperatorTime(new Date());
	}
	
	@Column(name="job_name",length=30,nullable=false)
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Column(name="job_memo",length=200)
	public String getJobMemo() {
		return jobMemo;
	}
	public void setJobMemo(String jobMemo) {
		this.jobMemo = jobMemo;
	}
	

	
	@Column(name="job_code",length=30,nullable=false)
	public String getJobCode() {
		return jobCode;
	}


	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	@Column(name="level")
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	
	@Column(name="job_num",nullable=false,length=30)
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	
	@Column(name="create_time",nullable=false)
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
	
	@OneToMany(targetEntity=JobDepartRelation.class)
	@JoinColumn(name="job_duty_id")
	public Set<JobDepartRelation> getRelations() {
		return relations;
	}
	public void setRelations(Set<JobDepartRelation> relations) {
		this.relations = relations;
	}
	
	
	
}
