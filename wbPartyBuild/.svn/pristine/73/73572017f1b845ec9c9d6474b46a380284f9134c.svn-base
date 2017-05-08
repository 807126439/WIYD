package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

/**
 * 定时任务对象
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_timetask")
public class TimeTask extends UUIDEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 275468481925365674L;
    

		
	private String taskId;      	//任务ID	
	private String taskDescribe; 	//任务描述	
	private String cronExpression;  //cron表达式	
	private String isEffect;		//是否生效了0未生效,1生效了	
	private String isStart;			//是否运行0停止,1运行	
	private Date createDate;		//创建时间	
	private String createBy;		//创建人ID (memberId)	
	private String createName;		//创建人名称			
	
	
	
	public TimeTask(){}
	
	
	public TimeTask(String taskId, String taskDescribe, String cronExpression,
			String isEffect, String isStart, Date createDate, String createBy,
			String createName) {
		super();
		this.taskId = taskId;
		this.taskDescribe = taskDescribe;
		this.cronExpression = cronExpression;
		this.isEffect = isEffect;
		this.isStart = isStart;
		this.createDate = createDate;
		this.createBy = createBy;
		this.createName = createName;
	}
	
	
	
	@Column(name="task_id",nullable=false,unique=true)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Column(name="task_describe")
	public String getTaskDescribe() {
		return taskDescribe;
	}
	public void setTaskDescribe(String taskDescribe) {
		this.taskDescribe = taskDescribe;
	}
	
	@Column(name="cron_expression",nullable=false)
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	@Column(name="is_effect",nullable=false)
	public String getIsEffect() {
		return isEffect;
	}
	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}
	
	@Column(name="is_start",nullable=false)
	public String getIsStart() {
		return isStart;
	}
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}
	
	@Column(name="create_date",nullable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="create_by")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Column(name="create_name")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
	
	
	
	
	
	
	
	
	
}
