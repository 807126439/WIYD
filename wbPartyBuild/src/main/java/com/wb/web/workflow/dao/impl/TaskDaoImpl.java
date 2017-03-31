package com.wb.web.workflow.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.ITaskDao;
import com.wb.web.workflow.dto.task.TaskDetail;
import com.wb.web.workflow.dto.task.TaskQueryDTO;
import com.wb.web.workflow.entity.Task;

@Repository("taskDao")
public class TaskDaoImpl extends BaseDaoImpl<Long, Task> implements ITaskDao{

	/**
	 * 获取申请者最新的任务
	 * @param procExeId
	 * @param userId
	 * @return
	 */
	public Task getApplyTaskByCondition(Long procExeId,String nodeId,String owner){
		Assert.notNull(procExeId, "task must not be null");
		Assert.notNull(nodeId, "nodeId must not be null");
		Assert.notNull(owner, "owner must not be null");
		
		String SQL = "SELECT proc_inst_Id FROM flow_proc_execution WHERE id=?";
		Query queryInst = this.getSession().createSQLQuery(SQL).addScalar("proc_inst_Id", LongType.INSTANCE).setParameter(0, procExeId);
		Long procinstId = (Long) queryInst.uniqueResult();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.* FROM flow_task t INNER JOIN flow_proc_execution e ON t.proc_exe_id=e.id  ");
		sb.append("WHERE t.proc_inst_Id=:instId AND e.proc_node_Id=:nodeId ");
		sb.append("AND t.owner=:owner AND t.type=:t_type  AND t.status=:t_status ");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addEntity(Task.class);
		query.setParameter("instId", procinstId);
		query.setParameter("nodeId", nodeId);
		query.setParameter("owner", owner);
		query.setParameter("t_type", Task.SQZ_TYPE);
		query.setParameter("t_status", Task.UNFINISH_STATUS);
		
		return (Task) query.uniqueResult();
		
	}
	
	
	/**
	 * 查询流程执行点下的所有任务	
	 * @param procExeId
	 * @return
	 */
	public List<Task> getTasksByProcExeId(Long procExeId,Short type){
		Assert.notNull(procExeId, "procExeId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("processExecution.id", procExeId));
	
		if(type!=null){
			cr.add(Restrictions.eq("type", type));
		}
		
		return cr.list();
		
	}
	
	
	/**
	 * 通过流程执行点的上级执行点查询任务
	 * @param preExeId
	 * @param type
	 * @return
	 */
	public List<Task> getTasksByPreExeId(Long preExeId,Short type){
		Assert.notNull(preExeId, "preExeId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());

		cr.createAlias("processExecution", "e", JoinType.INNER_JOIN);
		cr.add(Restrictions.eq("e.preExecution.id", preExeId));
		
		if(type!=null){
			cr.add(Restrictions.eq("type", type));
		}
		
		return cr.list();
		
	}
	
	
	
	 /**
	  * 按条件分页查询任务表
	  * @param queryDTO
	  * @return
	  */
	 public Page<TaskDetail> searchTaskDetailtByCondition(TaskQueryDTO queryDTO){
		 StringBuilder countBuilder = new StringBuilder("SELECT DISTINCT COUNT(*) as num  FROM flow_task t ");			
		 StringBuilder listBuilder = new StringBuilder();
		 listBuilder.append("SELECT DISTINCT t.id,t.name,t.task_explain AS taskExplain,t.owner,t.create_time AS createTime,")
		   			.append("t.deal_time AS dealTime,t.status,t.proc_inst_id AS procInstId,st.create_time AS beginTime,lk.user_id AS handler,")
		   			.append("def.process_name AS processName,pn.node_name AS currNode ")
		   			.append("FROM flow_task t ");
	 
	    this.bulidQueryStrItem(countBuilder, listBuilder,"INNER JOIN flow_proc_definition def ON t.proc_defin_id = def.id ");
	    this.bulidQueryStrItem(countBuilder, listBuilder,"INNER JOIN flow_proc_execution st ON t.proc_inst_Id = st.id ");
	    this.bulidQueryStrItem(countBuilder, listBuilder,"INNER JOIN flow_proc_execution e ON t.proc_exe_Id=e.id ");
	    this.bulidQueryStrItem(countBuilder, listBuilder,"INNER JOIN flow_proc_node pn ON pn.id=e.proc_node_Id ");
	    this.bulidQueryStrItem(countBuilder, listBuilder,"LEFT JOIN flow_run_identitylink lk ON lk.task_id=t.id ");
	    this.bulidQueryStrItem(countBuilder, listBuilder,"WHERE 1=1 ");
		 
		 if(queryDTO.getStatus()!=null){
			 this.bulidQueryStrItem(countBuilder, listBuilder,"AND t.status=:status ");
		 }

		 
		 if(!StringUtils.isBlank(queryDTO.getUserName())){
			 
			 if(queryDTO.getGroupIds()!=null && !queryDTO.getGroupIds().isEmpty()){
				 this.bulidQueryStrItem(countBuilder, listBuilder,"AND (lk.user_id=:uname OR lk.group_id IN (:gids)) ");
			 }else{				
				 this.bulidQueryStrItem(countBuilder, listBuilder,"AND lk.user_id=:uname ");
			 }			 
			 
		 }
		 
		 if(!StringUtils.isBlank(queryDTO.getTaskName())){
			 this.bulidQueryStrItem(countBuilder, listBuilder,"AND t.name LIKE :tname ");
		 }
		 
		 listBuilder.append("ORDER BY t.id DESC ");
		 
		 Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		 Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				 	   .addScalar("id", LongType.INSTANCE).addScalar("name", StringType.INSTANCE)
				 	   .addScalar("taskExplain", StringType.INSTANCE).addScalar("owner", StringType.INSTANCE)
				 	   .addScalar("createTime", DateType.INSTANCE).addScalar("procInstId", LongType.INSTANCE)
				 	   .addScalar("dealTime", DateType.INSTANCE)
				 	   .addScalar("status", ShortType.INSTANCE).addScalar("beginTime", DateType.INSTANCE)
				 	   .addScalar("handler", StringType.INSTANCE).addScalar("processName", StringType.INSTANCE)
				 	   .addScalar("currNode", StringType.INSTANCE)
				 	   .setResultTransformer(Transformers.aliasToBean(TaskDetail.class));
		
		 if(queryDTO.getStatus()!=null){
			 this.setQueryParamsVal(queryCount, queryList, "status", queryDTO.getStatus());
		 }
		 
		 
		 if(!StringUtils.isBlank(queryDTO.getUserName())){
			 
			 this.setQueryParamsVal(queryCount, queryList, "uname", queryDTO.getUserName());
			 if(queryDTO.getGroupIds()!=null && !queryDTO.getGroupIds().isEmpty()){
				 this.setQueryParamsListVal(queryCount, queryList, "gids", queryDTO.getGroupIds());
			 }			 
			 
		 }
		 
		 if(!StringUtils.isBlank(queryDTO.getTaskName())){
			 this.setQueryParamsVal(queryCount, queryList, "tname", queryDTO.getTaskName());
		 }
		 
		 Long recTotal = (Long) queryCount.uniqueResult();
		 
		 queryList.setFirstResult(queryDTO.getStartQuery());
		 queryList.setMaxResults(queryDTO.getPageSize());
		 
		 List<TaskDetail> list = queryList.list();
			
		return new Page<TaskDetail>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	 }
   
	
	 
	 /**
	  * 获取任务详细内容
	  * @param taskId
	  * @param userName
	  * @param groupIds
	  * @return
	  */
	 public TaskDetail getTaskDetailByTaskId(Long taskId){
		 Assert.notNull("taskId", "taskId could not be null");
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("SELECT t.id,t.name,t.task_explain AS taskExplain,t.owner,t.create_time AS createTime,")
		   .append("t.deal_time AS dealTime,t.status,st.create_time AS beginTime,")
		   .append("def.process_name AS processName,pn.node_name AS currNode, ")
		   .append("pn.id AS nodeId,t.proc_inst_Id AS procInstId,t.proc_exe_id AS procExeId ")
		   .append("FROM flow_task t ") 
		   .append("INNER JOIN flow_proc_definition def ON t.proc_defin_id = def.id ")
		   .append("INNER JOIN flow_proc_execution st ON t.proc_inst_Id = st.id ")
		   .append("INNER JOIN flow_proc_execution e ON t.proc_exe_Id=e.id ")
		   .append("INNER JOIN flow_proc_node pn ON pn.id=e.proc_node_Id ")
		   .append("WHERE t.id=:taskId ");
		 
			 
		 Query query = this.getSession().createSQLQuery(sb.toString())
			 	   .addScalar("id", LongType.INSTANCE).addScalar("name", StringType.INSTANCE)
			 	   .addScalar("taskExplain", StringType.INSTANCE).addScalar("owner", StringType.INSTANCE)
			 	   .addScalar("createTime", DateType.INSTANCE).addScalar("dealTime", DateType.INSTANCE)
			 	   .addScalar("status", ShortType.INSTANCE).addScalar("beginTime", DateType.INSTANCE)
			 	   .addScalar("processName", StringType.INSTANCE).addScalar("currNode", StringType.INSTANCE)
			 	   .addScalar("nodeId", StringType.INSTANCE).addScalar("procInstId", LongType.INSTANCE)
			 	   .addScalar("procExeId", LongType.INSTANCE)
			 	   .setResultTransformer(Transformers.aliasToBean(TaskDetail.class));
		  
		 query.setParameter("taskId", taskId);
		 
		
		 return (TaskDetail) query.uniqueResult();
	 }
	 
	 
	 /**
	  * 根据用户和角色检查是否可以处理任务 
	  * @param taskId
	  * @param userName
	  * @param groupIds
	  * @return
	  */
	public boolean checkIsCanHandleTask(Long taskId,String userName,List<String> groupIds){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) AS num FROM flow_run_identitylink WHERE task_id=:taskId ")
		  .append("AND (user_id=:userId OR group_id IN (:gids)) ");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addScalar("num", LongType.INSTANCE);
		query.setParameter("taskId", taskId);
		query.setParameter("userId", userName);
		query.setParameterList("gids", groupIds);
		
		Long result = (Long) query.uniqueResult();
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
	 
	/**
	 * 根据流程实例id任务
	 * @param procInstId
	 */
	public void deleteByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		String SQL = "DELETE FROM flow_task WHERE proc_inst_id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, procInstId);
		query.executeUpdate();
		

		
	}
	
	
	public List<Long> getTaskIdByParentExeId(Long parentExeId){
		Assert.notNull(parentExeId,"parentExeId could not be null");
		
		String SQL = "SELECT t.id FROM flow_task t INNER JOIN flow_proc_execution e ON t.proc_exe_id=e.id WHERE e.pre_exe_id=?";
	
		Query query = this.getSession().createSQLQuery(SQL).addScalar("id", LongType.INSTANCE);
		query.setParameter(0, parentExeId);
		
		
		return query.list();
	}
	
	
	
	
}
