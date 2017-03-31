package com.wb.web.workflow.dto.attachment;

import com.wb.core.common.dto.BaseQueryDTO;

public class AttachmentQueryDTO extends BaseQueryDTO{

	
	private Long taskId;
	private Long procInstId;
	private Short type;
	private String kind;
	private String taskIds;
	private String attaName;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(Long procInstId) {
		this.procInstId = procInstId;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}

	public String getAttaName() {
		return attaName;
	}

	public void setAttaName(String attaName) {
		this.attaName = attaName;
	}

	
	
	
	
	
	
	
}
