package com.wb.web.workflow.dao.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IProcessNodeDao;
import com.wb.web.workflow.dto.node.ChoiceNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeQueryDTO;
import com.wb.web.workflow.entity.ProcessNode;

@Repository("processNodeDao")
public class ProcessNodeDaoImpl extends BaseDaoImpl<String, ProcessNode> implements IProcessNodeDao{

	
	@SuppressWarnings("unchecked")
	public List<ProcessNode> getNodesByProcDefinId(Long procDefinId){
		Assert.notNull(procDefinId, "procDefinId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("definition.id", procDefinId));
		
		cr.addOrder(Order.asc("preNode.id"));
		
		return cr.list();
	}
	
	
	
	public ProcessNode getStartNodesByProcDefinId(Long procDefinId){
		Assert.notNull(procDefinId, "procDefinId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("definition.id", procDefinId));
		//cr.add(Restrictions.eq("nodeType", ProcessNode.START_TYPE));
		cr.add(Restrictions.isNull("preNode"));
		
		return (ProcessNode) cr.uniqueResult();
	}
	
	/**
	 * 根据当前节点获取下一级发起人的任务节点
	 * @param preNodeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List<ProcessNode> getOwnerTaskByPreNode(String preNodeId){
		Assert.hasText(preNodeId, "preNodeId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("preNode.id", preNodeId));
		cr.add(Restrictions.eq("nodeType", ProcessNode.CHAT_TYPE));
		
		return cr.list();
		
	}
	
	
	/**
	 * 根据流程定义删除流程节点
	 * @param definiId
	 */
	public void deleteByDefiniId(Long definiId){
		Assert.notNull(definiId, "definiId could not be null");
		String SQL = "DELETE FROM flow_proc_node WHERE proc_defin_id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, definiId);
		query.executeUpdate();
		
	}
	
	/**
	 * 查找节点的所有下级节点信息（不包括type为chat）
	 * @param nodeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ChoiceNodeDTO> getNextNodes(String nodeId){
		Assert.hasText(nodeId, "nodeId could not be null");
		
		String SQL = "SELECT id,node_name AS nodeName FROM flow_proc_node WHERE pre_node_id=:nid AND node_type<>'"+ProcessNode.CHAT_TYPE+"' ";
		
		Query query = this.getSession().createSQLQuery(SQL)
					 .addScalar("id", StringType.INSTANCE).addScalar("nodeName", StringType.INSTANCE)	
					 .setResultTransformer(Transformers.aliasToBean(ChoiceNodeDTO.class))
					 .setParameter("nid", nodeId);
		
		
		return query.list();
	}



	@SuppressWarnings("unchecked")
	@Override
	public Page<ProcessNodeDTO> searchProcessNodeByCondition(
			ProcessNodeQueryDTO queryDTO) {
		StringBuilder countBuilder =new StringBuilder("select count(*) as num from  flow_proc_definition fpd RIGHT   JOIN flow_proc_node fpn ON fpd.id=fpn.proc_defin_id where 1=1 "); 
		StringBuilder listBuilder =new StringBuilder("select fpn.id as id,fpn.node_code as nodeCode,fpn.node_name as nodeName,fpn.node_type as nodeType,fpn.description as description from flow_proc_definition fpd RIGHT JOIN flow_proc_node fpn ON fpd.id=fpn.proc_defin_id where 1=1 ");
		if (queryDTO.getId()!=null && queryDTO.getId()>0) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND fpd.id=:id ");
		}
		if (!StringUtils.isBlank(queryDTO.getNodeName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND fpn.node_name=:nodeName ");
		}
		if (!StringUtils.isBlank(queryDTO.getNodeCode())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND fpn.node_code=:nodeCode ");
		}
		if (!StringUtils.isBlank(queryDTO.getNodeType())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND fpn.node_type=:nodeType ");
		}
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", StringType.INSTANCE)
				.addScalar("nodeCode", StringType.INSTANCE)
				.addScalar("nodeName", StringType.INSTANCE)
				.addScalar("nodeType", StringType.INSTANCE)
				.addScalar("description", StringType.INSTANCE)
				
				.setResultTransformer(
						Transformers.aliasToBean(ProcessNodeDTO.class));
		if (queryDTO.getId()!=null && queryDTO.getId()>0) {
			this.setQueryParamsVal(queryCount, queryList, "id", queryDTO.getId());
		}
		if (!StringUtils.isBlank(queryDTO.getNodeName())) {
			this.setQueryParamsVal(queryCount, queryList, "nodeName", queryDTO.getNodeName());
		}
		if (!StringUtils.isBlank(queryDTO.getNodeCode())) {
			this.setQueryParamsVal(queryCount, queryList, "nodeCode", queryDTO.getNodeCode());
		}
		if (!StringUtils.isBlank(queryDTO.getNodeType())) {
			this.setQueryParamsVal(queryCount, queryList, "nodeType", queryDTO.getNodeType());
		}
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		return new Page<ProcessNodeDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}
	
	
	
	public boolean isEndProcessNode(String nodeId){
		Assert.hasText(nodeId, "nodeId could not be null");
		String SQl = "SELECT COUNT(*) AS num FROM flow_proc_node WHERE pre_node_id=:nodeId AND node_type<>:type";
		
		Query query = this.getSession().createSQLQuery(SQl).addScalar("num", LongType.INSTANCE)
					.setParameter("nodeId", nodeId).setParameter("type", ProcessNode.CHAT_TYPE);
		
		Long result = (Long) query.uniqueResult();
		if(result>0){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	
	
}
