package com.wb.web.workflow.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.wb.core.common.bean.Page;
import com.wb.web.workflow.dto.node.ChoiceNodeDTO;
import com.wb.web.workflow.dto.node.ProcessData;
import com.wb.web.workflow.dto.node.ProcessNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeQueryDTO;
import com.wb.web.workflow.dto.node.UpdateProcessNodeDTO;
import com.wb.web.workflow.entity.ProcessNode;

public interface IProcessNodeService {
	
	public String getCurrExeNodeCode(Long procInstId);

	public void addProcess(Long procDefinId,String data);
	
	public JSONObject getProcessData(Long procDefinId);
	
	public Long startProcess(String processCode,String customerId);
	
	public void goNextStep(Long currExeId,String choiceNodeId);
	
	public void endProcess(Long currExeId);
	
	public ProcessData geSerialProcess(Long procInstId,String applyUserId);
	
	public List<ChoiceNodeDTO> getNextNodes(String nodeId);
	/**
	 * 分页查询列表
	 * @param queryDTO
	 * @return
	 */
	public Page<ProcessNodeDTO> searchProcessNodeByCondition(ProcessNodeQueryDTO queryDTO);
	/**
	 * 根据编号查找单条记录
	 * @param id
	 * @return
	 */
	public ProcessNode getNodesById(String id);
	/**
	 * 修改流程图信息
	 * @param nodeId 	    流程图ID
	 * @param roleIds	    角色表ID的集合
	 * @param userId	    用户表ID的集合
	 */
	public void updateProcessNode(UpdateProcessNodeDTO dto,String[] roleIds,String[] userId);
	
}
