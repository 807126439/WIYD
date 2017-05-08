package com.wb.web.workflow.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.wb.core.common.entity.BaseEntity;


/**
 * 运行时用户关系信息
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="flow_run_identitylink")
public class RunIdentitylink extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012766044402030306L;
	

	
	private String groupId;
	private String userId;
	private Task task;
	private ProcessExecution processInstance;
	private ProcessDefinition processDefinition;
	private String type;
	
	
	
	
	
	
	public RunIdentitylink(){}
	
	
	
	
	public RunIdentitylink(String groupId, String userId, Task task,
			ProcessExecution processInstance,
			ProcessDefinition processDefinition, String type) {
		super();
		this.groupId = StringUtils.isBlank(groupId)? null: groupId;
		this.userId = StringUtils.isBlank(userId)? null: userId;
		this.task = task;
		this.processInstance = processInstance;
		this.processDefinition = processDefinition;
		this.type = type;
	}
	
	
	
	@Column(name="group_id",length=50)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Column(name="user_id",length=50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@ManyToOne(targetEntity=Task.class)
	@JoinColumn(name="task_id")
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	@ManyToOne(targetEntity=ProcessExecution.class)
	@JoinColumn(name="proc_inst_id")
	public ProcessExecution getProcessInstance() {
		return processInstance;
	}
	public void setProcessInstance(ProcessExecution processInstance) {
		this.processInstance = processInstance;
	}
	
	
	@ManyToOne(targetEntity=ProcessDefinition.class)
	@JoinColumn(name="proc_defin_id")
	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}
	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}
	
	@Column(name="type",length=50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
