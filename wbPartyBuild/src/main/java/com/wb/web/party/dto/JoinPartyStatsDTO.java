package com.wb.web.party.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wb.web.workflow.dto.node.NodeDTO;
import com.wb.web.workflow.dto.node.ProcessData;


public class JoinPartyStatsDTO {

	private Long id;
	private String applyUserId;
	private String applyUserName;			//申请人
	private String chineseName;				//姓名
	private String department;				//部门
    private String currNode;
    
    private List<PartyFileDTO> applyFiles = new ArrayList<PartyFileDTO>();
    private List<PartyFileDTO> handleFiles = new ArrayList<PartyFileDTO>();
	
	private Long processInstanceId; 		//流程实例id

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

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	public List<PartyFileDTO> getApplyFiles() {
		return applyFiles;
	}

	public void setApplyFiles(List<PartyFileDTO> applyFiles) {
		this.applyFiles = applyFiles;
	}

	public List<PartyFileDTO> getHandleFiles() {
		return handleFiles;
	}

	public void setHandleFiles(List<PartyFileDTO> handleFiles) {
		this.handleFiles = handleFiles;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	
	
	
	
	
}
