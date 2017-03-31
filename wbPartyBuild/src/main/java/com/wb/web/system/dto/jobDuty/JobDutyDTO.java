package com.wb.web.system.dto.jobDuty;

import java.util.Date;

import com.wb.core.common.dto.UUIDDto;

public class JobDutyDTO extends UUIDDto{

	private String jobName;  //职务名称
	private String jobCode; 
	private String jobMemo;  //职务备注
	private Short level;	 //职务级别
	private String jobNum;   //职务编号
	private Date createTime;  //创建时间
	private String updateBy;
	
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobMemo() {
		return jobMemo;
	}
	public void setJobMemo(String jobMemo) {
		this.jobMemo = jobMemo;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
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
	
	
	
	
}
