package com.wb.web.workflow.dto.definition;

import java.util.Date;

import com.wb.core.common.dto.BaseDto;
import com.wb.web.workflow.entity.ProcessDefinition;

public class ProcessDefinitionDTO extends BaseDto{

	private String processCode;		//流程标识符
	private String processName;     //流程名
	private String description;     //描述
	private Date createTime;		//创建时间
	private Integer status;
	private Integer version;
	
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status == null? ProcessDefinition.INVAILD_STATUS:status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
	
}
