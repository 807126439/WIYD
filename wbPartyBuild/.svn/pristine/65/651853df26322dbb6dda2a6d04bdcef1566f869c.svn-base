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
 * 流程实例和流程执行信息
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="flow_proc_execution")
public class ProcessExecution extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7553408276909334737L;
	
	public static int RUNNING_EXE_STATUS = 1;
	public static int STOP_EXE_STATUS = 0;
	
	public static short ACTIVIT_STATUS = 1;
	public static short OVER_STATUS = 0;
	
	private ProcessDefinition processDefinition;	//所属流程定义
	private ProcessExecution processInstance;		//流程实例
	private ProcessExecution preExecution;			//上一个流程执行点
	private ProcessNode processNode;				//流程节点
	private Integer executeStatus;					//运行状态 1:运行 0：暂停
	private Boolean isMain;			//是否主线
	private String userId;			//发起人
	private Date createTime;		//创建时间
	private Short status;   		//0：历史  1：活跃
	
	
	public ProcessExecution(){}
	
	
	
	
	public ProcessExecution(ProcessDefinition processDefinition,
			ProcessExecution processInstance,ProcessExecution preExecution, ProcessNode processNode, 
			Boolean isMain,String userId) {
		super();
		this.processDefinition = processDefinition;
		this.processInstance = processInstance;
		this.preExecution = preExecution;
		this.processNode = processNode;
		this.executeStatus = RUNNING_EXE_STATUS;
		this.isMain = isMain;
		this.userId = userId;
		this.createTime = new Date();
		this.status = ACTIVIT_STATUS;
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
	@JoinColumn(name="pre_exe_id")
	public ProcessExecution getPreExecution() {
		return preExecution;
	}
	
	public void setPreExecution(ProcessExecution preExecution) {
		this.preExecution = preExecution;
	}




	@ManyToOne(targetEntity=ProcessNode.class)
	@JoinColumn(name="proc_node_id")
	public ProcessNode getProcessNode() {
		return processNode;
	}
	public void setProcessNode(ProcessNode processNode) {
		this.processNode = processNode;
	}
	
	@Column(name="exe_status")
	public Integer getExecuteStatus() {
		return executeStatus;
	}
	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus = executeStatus;
	}
	
	
	
	@Column(name="is_main")
	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}



	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	@Column(name="status")
	public Short getStatus() {
		return status;
	}




	public void setStatus(Short status) {
		this.status = status;
	}
	
	
	
	
	
	
}
