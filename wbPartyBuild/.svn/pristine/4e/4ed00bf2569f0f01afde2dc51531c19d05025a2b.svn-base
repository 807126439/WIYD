package com.wb.web.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 任务信息
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="flow_task")
public class Task extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4546901794404663980L;
	
	public static short UNFINISH_STATUS = 0;
	public static short FINISH_STATUS = 1;
	
	public static short SQZ_TYPE = 1;  //申请方
	public static short CLZ_TYPE = 0;  //处理方
	
	private ProcessDefinition processDefinition;	//所属流程定义
	private ProcessExecution processInstance;		//流程实例
	private ProcessExecution processExecution;		//执行实例id

	private String name;							//任务名称
	private String taskExplain;						//任务说明
	private Short type;								//任务类型
	private String owner;							//发起人
	private Date createTime;						//创建时间
	private Date dealTime;							//执行时间
	private String dealUser;						//执行用户
	private Short status;							//状态 0：未完成  1:已完成

	private String assignee;						//委托人

	
	
	
	
	
	public Task(){}
	
	
	
	
	
	public Task(ProcessDefinition processDefinition,ProcessExecution processInstance,
			ProcessExecution processExecution,String name,String taskExplain,
			Short type,String owner,String assignee) {
		super();
		this.processDefinition = processDefinition;
		this.processInstance = processInstance;
		this.processExecution = processExecution;
		this.name = name;
		this.taskExplain = taskExplain;
		this.type = type;
		this.owner = owner;
		this.createTime = new Date();
		this.status = UNFINISH_STATUS;
		this.assignee = assignee;
	
	}
	
	
	@ManyToOne(targetEntity=ProcessDefinition.class)
	@JoinColumn(name="proc_defin_id")
	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}
	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}
	
	@ManyToOne(targetEntity=ProcessExecution.class)
	@JoinColumn(name="proc_inst_id")
	public ProcessExecution getProcessInstance() {
		return processInstance;
	}
	public void setProcessInstance(ProcessExecution processInstance) {
		this.processInstance = processInstance;
	}
	
	@ManyToOne(targetEntity=ProcessExecution.class)
	@JoinColumn(name="proc_exe_id")
	public ProcessExecution getProcessExecution() {
		return this.processExecution;
	}
	public void setProcessExecution(ProcessExecution processExecution) {
		this.processExecution = processExecution;
	}

	@Column(name="name",length=1000)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Column(name="task_explain",length=2000)
	public String getTaskExplain() {
		return taskExplain;
	}

	public void setTaskExplain(String taskExplain) {
		this.taskExplain = taskExplain;
	}



	@Column(name="type",length=500)
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	
	@Column(name="owner",length=50)
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="deal_time")
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
	@Column(name="status")
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}

	
	
	


	@Column(name="deal_user",length=50)
	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}





	@Column(name="assignee",length=50)
	public String getAssignee() {
		return assignee;
	}


	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}




	
	
	
	
	
	
	
	
	
	

}
