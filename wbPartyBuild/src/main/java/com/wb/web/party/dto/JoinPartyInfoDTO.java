package com.wb.web.party.dto;

import java.util.Date;
import java.util.List;

import com.wb.web.workflow.dto.node.NodeDTO;
import com.wb.web.workflow.dto.node.ProcessData;


public class JoinPartyInfoDTO {

	private Long id;
	private String applyUserId;
	private String applyUserName;			//申请人
	private String chineseName;				//姓名
	private String department;				//部门
	private String memo;					//备注 
	private Date createTime;
	private Date startTime;		
	private Long processInstanceId; 		//流程实例id
	private Short status;					//状态 0：未启动  1：运行  2：结束
	
	private ProcessData processData;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public ProcessData getProcessData() {
		return processData;
	}
	public void setProcessData(ProcessData processData) {
		this.processData = processData;
	}
	
	
	
	
	
}
