package com.wb.web.workflow.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 流程节点配置人员和组信息
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="flow_proc_identitylink")
public class ProcessIdentitylink extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 378740538924276829L;
	
	public static final String assignee = "assignee";
	public static final String owner = "owner";

	private ProcessNode processNode;
	private String userId;
	private String groupId;
	private String type;
	
	
	public ProcessIdentitylink(){}
	
	
	public ProcessIdentitylink(ProcessNode processNode, String userId,
			String groupId, String type) {
		super();
		this.processNode = processNode;
		this.userId = userId;
		this.groupId = groupId;
		this.type = type;
	}
	
	
	
	@ManyToOne(targetEntity=ProcessNode.class)
	@JoinColumn(name="process_node_id")
	public ProcessNode getProcessNode() {
		return processNode;
	}
	public void setProcessNode(ProcessNode processNode) {
		this.processNode = processNode;
	}
	
	@Column(name="user_id",length=50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="group_id",length=50)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	@Column(name="type",length=50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
