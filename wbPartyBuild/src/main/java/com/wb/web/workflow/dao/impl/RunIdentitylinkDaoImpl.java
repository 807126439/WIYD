package com.wb.web.workflow.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IRunIdentitylinkDao;
import com.wb.web.workflow.entity.RunIdentitylink;

@Repository("runIdentitylinkDao")
public class RunIdentitylinkDaoImpl extends BaseDaoImpl<Long, RunIdentitylink> implements IRunIdentitylinkDao{

	
	/**
	 * 根据流程实例id人员组关系
	 * @param procInstId
	 */
	public void deleteByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		String SQL = "DELETE FROM flow_run_identitylink WHERE proc_inst_id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, procInstId);
		query.executeUpdate();
		
	}

	@Override
	public void deleteByTaskId(Long taskId) {
		Assert.notNull(taskId, "taskId could not be null");
		String SQL = "DELETE FROM flow_run_identitylink WHERE task_id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, taskId);
		query.executeUpdate();
		
	}
	
}
