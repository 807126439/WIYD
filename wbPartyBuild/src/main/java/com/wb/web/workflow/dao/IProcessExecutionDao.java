package com.wb.web.workflow.dao;

import java.util.List;

import com.wb.core.common.dao.IBaseDao;
import com.wb.web.workflow.dto.node.CurrNodeDTO;
import com.wb.web.workflow.entity.ProcessExecution;
import com.wb.web.workflow.entity.ProcessNode;

public interface IProcessExecutionDao extends IBaseDao<Long, ProcessExecution>{
	
	public ProcessExecution getCurrentExecutionByProcInstId(Long procInstId);
	
	public ProcessNode getCurrentProcessNodeByProcInstId(Long procInstId);
	
	public List<CurrNodeDTO> getCurrNodesByProcInstIds(List<Long> procInstIds);
	
	public void deleteByProcInstId(Long procInstId);
}
