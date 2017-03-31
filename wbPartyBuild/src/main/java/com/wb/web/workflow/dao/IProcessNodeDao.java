package com.wb.web.workflow.dao;

import java.util.List;
import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.workflow.dto.node.ChoiceNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeQueryDTO;
import com.wb.web.workflow.entity.ProcessNode;

public interface IProcessNodeDao extends IBaseDao<String, ProcessNode>{

	public List<ProcessNode> getNodesByProcDefinId(Long procDefinId);
	
	public ProcessNode getStartNodesByProcDefinId(Long procDefinId);
	
	public List<ProcessNode> getOwnerTaskByPreNode(String preNodeId);
	
	public void deleteByDefiniId(Long definiId);
	
	public List<ChoiceNodeDTO> getNextNodes(String nodeId);
	
	public Page<ProcessNodeDTO> searchProcessNodeByCondition(ProcessNodeQueryDTO queryDTO);
	
	public boolean isEndProcessNode(String nodeId);
	
}
