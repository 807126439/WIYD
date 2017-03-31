package com.wb.web.workflow.dto.node;

public class CurrNodeDTO {

	private String id;
	private String nodeName;
	private String nodeCode;
	private Long procInstId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public Long getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(Long procInstId) {
		this.procInstId = procInstId;
	}
	
	
	
	
}
