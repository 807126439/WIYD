package com.wb.web.workflow.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IProcessDefinitionDao;
import com.wb.web.workflow.entity.ProcessDefinition;

@Repository("processDefinitionDao")
public class ProcessDefinitionDaoImpl extends BaseDaoImpl<Long, ProcessDefinition> implements IProcessDefinitionDao{

	
	/**
	 * 获取最后版本的流程定义
	 * @param processCode 流程标识符
	 * @return
	 */
	public ProcessDefinition getLastVersionDefinition(String processCode){
		Assert.hasText(processCode, "processCode could not be null");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM flow_proc_definition ");
		sb.append("WHERE id=(SELECT MAX(id) FROM flow_proc_definition WHERE process_code=:code)");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addEntity(this.getInheritClass());
		
		query.setParameter("code", processCode);
		
		
		return (ProcessDefinition) query.uniqueResult();
	}
	
	
	
	public Integer getMaxVersion(String processCode){
		Assert.hasText(processCode, "processCode could not be null");
		String SQL = "SELECT MAX(version) AS vs FROM flow_proc_definition WHERE process_code=:code";
		
		Query query = this.getSession().createSQLQuery(SQL).addScalar("vs", IntegerType.INSTANCE);
		query.setParameter("code", processCode);
		
		Integer result = (Integer) query.uniqueResult();
		if(result == null){
			result = 0;
		}
		return result;
		
	}
}
