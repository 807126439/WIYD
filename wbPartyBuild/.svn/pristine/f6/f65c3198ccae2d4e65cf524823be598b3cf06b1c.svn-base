package com.wb.web.workflow.dao;

import com.wb.core.common.dao.IBaseDao;
import com.wb.web.workflow.entity.ProcessDefinition;

public interface IProcessDefinitionDao extends IBaseDao<Long, ProcessDefinition>{
	
	public ProcessDefinition getLastVersionDefinition(String processCode);
	
	public Integer getMaxVersion(String processCode);
}
