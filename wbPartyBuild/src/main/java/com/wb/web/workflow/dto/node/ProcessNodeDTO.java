package com.wb.web.workflow.dto.node;

import com.wb.core.common.dto.UUIDDto;

public class ProcessNodeDTO extends UUIDDto{
	

	private String nodeCode;		//节点标识符
	private String nodeName;       //节点名称
	private String description;     //描述
	private String nodeType;		//节点类型
	
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	

}
