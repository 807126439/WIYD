package com.wb.web.workflow.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IProcessExecutionDao;
import com.wb.web.workflow.dto.node.CurrNodeDTO;
import com.wb.web.workflow.entity.ProcessExecution;
import com.wb.web.workflow.entity.ProcessNode;

@Repository("processExecutionDao")
public class ProcessExecutionDaoImpl extends BaseDaoImpl<Long, ProcessExecution> implements IProcessExecutionDao{

	public ProcessExecution getCurrentExecutionByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM flow_proc_execution WHERE id=");
		sb.append("(SELECT MAX(id) FROM flow_proc_execution WHERE proc_inst_id=:instId AND is_main=1 )");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addEntity(getInheritClass());
		query.setParameter("instId", procInstId);
		
		
		return (ProcessExecution) query.uniqueResult();
		
	}
	
	
	public ProcessNode getCurrentProcessNodeByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT p.* FROM flow_proc_execution e INNER JOIN flow_proc_node p ON e.proc_node_id=p.id ");
		sb.append("WHERE e.id=(SELECT MAX(id) FROM flow_proc_execution WHERE proc_inst_id=:instId AND is_main=1)");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addEntity(ProcessNode.class);
		query.setParameter("instId", procInstId);
		
		
		return (ProcessNode) query.uniqueResult();
		
	}
	
	
	
	public List<CurrNodeDTO> getCurrNodesByProcInstIds(List<Long> procInstIds){
		Assert.notEmpty(procInstIds, "procInstIds could not be empty");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT p.id AS id,p.node_name AS nodeName,p.node_code AS nodeCode,e.proc_inst_id AS procInstId ")
		  .append("FROM flow_proc_execution e INNER JOIN flow_proc_node p ON e.proc_node_id=p.id ")
		  .append("WHERE e.id IN ((SELECT MAX(id) FROM flow_proc_execution WHERE proc_inst_id IN (:instIds) AND is_main=1 GROUP BY proc_node_id))");
		
		Query query = this.getSession().createSQLQuery(sb.toString())
						  .addScalar("id", StringType.INSTANCE).addScalar("nodeName", StringType.INSTANCE)
						  .addScalar("nodeCode", StringType.INSTANCE).addScalar("procInstId", LongType.INSTANCE)
						  .setResultTransformer(Transformers.aliasToBean(CurrNodeDTO.class));
		query.setParameterList("instIds", procInstIds);
		
		
		return  query.list();
		
	}
	
	
	
	
	/**
	 * 根据流程实例id执行点
	 * @param procInstId
	 */
	public void deleteByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		String SQL = "DELETE FROM flow_proc_execution WHERE proc_inst_id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, procInstId);
		query.executeUpdate();
		
	}
}
