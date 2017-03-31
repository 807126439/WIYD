package com.wb.web.workflow.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IHistoryCommentDao;
import com.wb.web.workflow.entity.HistoryComment;

@Repository("historyCommentDao")
public class HistoryCommentDaoImpl extends BaseDaoImpl<Long, HistoryComment> implements IHistoryCommentDao{

	
	/**
	 * 通过任务id删除意见
	 * @param taskId
	 */
	public void deleteByTaskId(Long taskId){
		Assert.notNull(taskId, "taskId could not be null");
		String SQL = "DELETE FROM flow_his_comment WHERE task_id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, taskId);
		query.executeUpdate();
		
	}
	
	
	/**
	 * 根据流程实例id删除意见
	 * @param procInstId
	 */
	public void deleteByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		String SQL = "DELETE FROM flow_his_comment WHERE proc_inst_Id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, procInstId);
		query.executeUpdate();
		
	}
	
	
	
	
}
