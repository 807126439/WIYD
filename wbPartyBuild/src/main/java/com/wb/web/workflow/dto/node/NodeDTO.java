package com.wb.web.workflow.dto.node;


public class NodeDTO {
	public static int NO_DONE = 0;
	public static int HAS_DONE = 2;
	public static int IS_CURR = 1;
	
	private String id;
	private String nodeName;
	private String nodeType;
   	private String preNodeId;
   	private Integer status;
   	private NodeDTO ownerNode;

   	
   	public NodeDTO(){}
   	
   	
   	
	public NodeDTO(String id, String nodeName, String nodeType,
			String preNodeId,Integer status) {
		super();
		this.id = id;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.preNodeId = preNodeId;
		this.status = status;
		

	}
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
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	public String getPreNodeId() {
		return preNodeId;
	}
	public void setPreNodeId(String preNodeId) {
		this.preNodeId = preNodeId;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public NodeDTO getOwnerNode() {
		return ownerNode;
	}

	public void setOwnerNode(NodeDTO ownerNode) {
		this.ownerNode = ownerNode;
	}



   	
   	
   	
   	
	
}
