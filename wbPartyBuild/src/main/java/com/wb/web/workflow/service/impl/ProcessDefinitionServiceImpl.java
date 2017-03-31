package com.wb.web.workflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IProcessDefinitionDao;
import com.wb.web.workflow.dao.IProcessExecutionDao;
import com.wb.web.workflow.dto.definition.ProcessDefinitionDTO;
import com.wb.web.workflow.dto.definition.ProcessDefinitionQueryDTO;
import com.wb.web.workflow.entity.ProcessDefinition;
import com.wb.web.workflow.entity.ProcessExecution;
import com.wb.web.workflow.service.IProcessDefinitionService;

@Service("processDefinitionService")
@Transactional
public class ProcessDefinitionServiceImpl extends BaseService implements IProcessDefinitionService{
	
	@Resource
	private IProcessDefinitionDao processDefinitionDao;
	@Resource
	private IProcessExecutionDao processExecutionDao;
	
	public Page<ProcessDefinitionDTO> searchByPage(ProcessDefinitionQueryDTO queryDTO){
		
		Page<ProcessDefinition> pageResult = this.processDefinitionDao.searchEntityByPage(queryDTO);
		
		List<ProcessDefinitionDTO> dtoList = new ArrayList<ProcessDefinitionDTO>();
		
		for (ProcessDefinition def : pageResult.getList()) {
			ProcessDefinitionDTO dto = new ProcessDefinitionDTO();
			this.getMapper().map(def, dto);
		
			dtoList.add(dto);
		}
		
		
		return new Page<ProcessDefinitionDTO>(pageResult.getCurrentPage(), pageResult.getPageSize(), dtoList, pageResult.getRecTotal());
	}
	
	public ProcessDefinitionDTO getById(Long id){
		Assert.notNull(id, "id must not be null");
		
		ProcessDefinition processDefinition = this.processDefinitionDao.getById(id);
		ProcessDefinitionDTO dto = new ProcessDefinitionDTO();
		this.getMapper().map(processDefinition, dto);
		
		
		return dto;
	}
	
	
	
	
	/**
	 * 添加
	 * @param dto
	 */
	public void addProcessDefinition(ProcessDefinitionDTO dto){
		Assert.hasText(dto.getProcessCode(), "流程标识符不能为空！");
		Assert.hasText(dto.getProcessName(), "流程名称不能为空！");
		
		if(this.processDefinitionDao.checkIsExistByProperty("processCode", dto.getProcessCode(), null)){
			
			throw new MyException("流程标识符已存在!");
		}
		
		ProcessDefinition processDefinition = new ProcessDefinition(
						dto.getProcessCode(), dto.getProcessName(), 
						dto.getDescription(), getNowUser().getUsername(), 
						ProcessDefinition.NORMAL_STATUS,0);
		
		this.processDefinitionDao.save(processDefinition);
		
	}
	
	
	 
	public ProcessDefinition addSameCodeDefinition(Long definId){
		Assert.notNull(definId, "definId could not be null！");

		ProcessDefinition past = this.processDefinitionDao.getById(definId);
		if(past!=null){
			ProcessDefinition processDefinition = new ProcessDefinition(
					past.getProcessCode(), past.getProcessName(), 
					past.getDescription(), getNowUser().getUsername(), 
					ProcessDefinition.NORMAL_STATUS,
					1+this.processDefinitionDao.getMaxVersion(past.getProcessCode()));
	
			this.processDefinitionDao.save(processDefinition);
			
			
			return processDefinition;
		
		}else{
			
			throw new MyException("Could not find the record!");
		}
	
		
	}
	
	
	/**
	 * 修改
	 * @param dto
	 */
	public void updateProcessDefinition(ProcessDefinitionDTO dto){
		Assert.notNull(dto.getId(), "id must not be null");
		Assert.hasText(dto.getProcessCode(), "流程标识符不能为空！");
		Assert.hasText(dto.getProcessName(), "流程名称不能为空！");
		
	
		
		ProcessDefinition processDefinition = this.processDefinitionDao.getById(dto.getId());
		processDefinition.setProcessCode(dto.getProcessCode());
		processDefinition.setProcessName(dto.getProcessName());
		processDefinition.setDescription(dto.getDescription());
		processDefinition.setUpdateBy(getNowUser().getUsername());
		processDefinition.setStatus(dto.getStatus());
		processDefinition.setLastOperatorTime(new Date()); 
				
				
		
		this.processDefinitionDao.update(processDefinition);
		
	}
	
	

	/**
	 * 删除
	 * @param id
	 */
	public void deleteProcessDefinition(Long id){
		Assert.notNull(id, "id must not be null");
		ProcessDefinition processDefinition = this.processDefinitionDao.getById(id);
		if(processDefinition!=null){
			this.processDefinitionDao.delete(processDefinition);
		}
		
	}
	
	
	/**
	 * 通过流程实例id获取流程定义id
	 * @param procInstId
	 * @return
	 */
	public Long getProcessDefiniIdByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		
		ProcessExecution execution = this.processExecutionDao.getById(procInstId);
		Assert.notNull(execution, " could not find the record");
		
		return execution.getProcessDefinition().getId();
		
	}
	
	
	
	
	
	
}
