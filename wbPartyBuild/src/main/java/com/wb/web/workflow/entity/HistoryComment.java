package com.wb.web.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.wb.core.common.entity.BaseEntity;

/**
 * 历史意见审批
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="flow_his_Comment")
public class HistoryComment extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5292168471285563195L;
	
	
	private String userId;		//填写人
	private String content;		//意见内容
	private Short type;			//类型
	private Date createTime;	//填写时间
	private Long processInstanceId;	 //流程实例id
	private Long taskId;			 //任务id
	
	
	
	public HistoryComment(){}
	
	

	
	public HistoryComment(String userId, String content, Short type,
			 Long processInstanceId, Long taskId) {
		super();
		this.userId = userId;
		this.content = content;
		this.type = type;
		this.createTime = new Date();
		this.processInstanceId = processInstanceId;
		this.taskId = taskId;
	}
	
	
	
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="content",length=500)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="type",length=20)
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="proc_inst_id")
	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
	@Column(name="task_id")
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	
	
	
	

}
