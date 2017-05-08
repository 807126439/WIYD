package com.wb.web.workflow.service;

import com.wb.core.common.bean.Page;
import com.wb.web.workflow.dto.definition.ProcessDefinitionDTO;
import com.wb.web.workflow.dto.definition.ProcessDefinitionQueryDTO;
import com.wb.web.workflow.entity.ProcessDefinition;

public interface IProcessDefinitionService {

	public Page<ProcessDefinitionDTO> searchByPage(ProcessDefinitionQueryDTO queryDTO);
	
	public ProcessDefinitionDTO getById(Long id);
	
	public void addProcessDefinition(ProcessDefinitionDTO dto);
	
	public void updateProcessDefinition(ProcessDefinitionDTO dto);
	
	public void deleteProcessDefinition(Long id);
	
	public ProcessDefinition addSameCodeDefinition(Long definId);
	
	public Long getProcessDefiniIdByProcInstId(Long procInstId);
}
