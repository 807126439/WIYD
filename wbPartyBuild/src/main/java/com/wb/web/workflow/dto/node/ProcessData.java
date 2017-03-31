package com.wb.web.workflow.dto.node;

import java.util.List;

public class ProcessData {

	private Long currExeId;
	private List<NodeDTO> nodes;
	
	
	public ProcessData(){}
	
	
	public ProcessData(Long currExeId, List<NodeDTO> nodes) {
		super();
		this.currExeId = currExeId;
		this.nodes = nodes;
	}
	
	public Long getCurrExeId() {
		return currExeId;
	}
	public void setCurrExeId(Long currExeId) {
		this.currExeId = currExeId;
	}
	public List<NodeDTO> getNodes() {
		return nodes;
	}
	public void setNodes(List<NodeDTO> nodes) {
		this.nodes = nodes;
	}
	
	
}
