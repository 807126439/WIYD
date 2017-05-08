package com.wb.web.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.wb.core.common.entity.BaseEntity;

/**
 * 附件信息
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="flow_attachment")
public class Attachment extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -92810722029218711L;
	public static final short status_open = 1;
	public static final short status_close = 0;
	
	private String userId;  			//用户id
	private String name;				//附件名称
	private String description; 		//描述
	private Short type;					//类型
	private Long processInstanceId;	 //流程实例id
	private Long taskId; 			 //任务id
	private Date createTime;		 //创建时间
	private Long baseFileId;		 //文件id
	private Short status;			 //状态 1：公开  0：不公开
	
	
	public Attachment(){}
	
	
	public Attachment(String userId, String name, String description,
			Short type, Long processInstanceId, Long taskId,
			Long baseFileId) {
		super();
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.type = type;
		this.processInstanceId = processInstanceId;
		this.taskId = taskId;
		this.createTime = new Date();
		this.baseFileId = baseFileId;
		this.status = status_open;
	}
	
	
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="name",length=255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description",length=500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="type",length=20)
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
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
	

	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="base_file_id")
	public Long getBaseFileId() {
		return baseFileId;
	}
	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}

	
	@Column(name="status")
	public Short getStatus() {
		return status;
	}


	public void setStatus(Short status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
