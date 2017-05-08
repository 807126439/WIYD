package com.wb.web.party.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.web.system.entity.User;

/**
 * 入党申请信息
 * @author spear
 *
 */
@Entity
@Table(name="p_join_party_info")
public class JoinPartyInfo extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 360852523175666610L;
	
	public static final short NO_START_STATUS = 0;
	public static final short RUNNING_STATUS = 1;
	public static final short END_STATUS = 2;
	
	private User applyUser;			//申请人
	private String department;		//部门
	private String memo;			//备注 
	private Date createTime;
	private Date startTime;			
	private Long processInstanceId; //流程实例id
	private Short status;			//状态 0：未启动  1：运行  2：结束
	
	
	public JoinPartyInfo(){}
	
	
	
	public JoinPartyInfo(User applyUser, String department, String memo,
			Short status) {
		super();
		this.applyUser = applyUser;
		this.department = department;
		this.memo = memo;
		this.status = status;
		this.createTime = new Date();
	}
	
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="apply_user_id",nullable=false,unique=true)
	public User getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(User applyUser) {
		this.applyUser = applyUser;
	}
	
	
	@Column(name="department",length=50)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name="memo",length=300)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	@Column(name="proc_inst_id")
	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	@Column(name="status",nullable=false)
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}

	
	
	
	
}
