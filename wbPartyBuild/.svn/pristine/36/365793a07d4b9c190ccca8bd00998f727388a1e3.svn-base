package com.wb.web.workflow.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IAttachmentDao;
import com.wb.web.workflow.dto.attachment.AttachmentQueryDTO;
import com.wb.web.workflow.entity.Attachment;

@Repository("attachmentDao")
public class AttachmentDaoImpl extends BaseDaoImpl<Long, Attachment> implements IAttachmentDao{

	
	public List<Attachment> getAttachmentByProcInstId(List<Long> procInstIds){
		Assert.notEmpty(procInstIds, "procInstId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.in("processInstanceId", procInstIds));
		cr.add(Restrictions.eq("status", Attachment.status_open));
		
		return cr.list();
		
	}
	
	public List<Attachment> getAttachmentByTaskId(Long taskId){
		Assert.notNull(taskId, "taskId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("taskId", taskId));
		
		
		return cr.list();
		
	}
	
	
	
	public Page<Attachment> searchAttachmentByPage(AttachmentQueryDTO queryDTO) {	
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num  FROM flow_attachment WHERE 1=1 ");
		
		StringBuilder listBuilder  = new StringBuilder();
		listBuilder.append("SELECT * FROM flow_attachment WHERE 1=1 ");
		
		if(queryDTO.getTaskId()!=null){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND task_id=:taskId ");	
			
		}else if(queryDTO.getProcInstId()!=null){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND proc_inst_id=:procInstId ");	
        }
    			
        if(queryDTO.getType()!=null){
        	this.bulidQueryStrItem(countBuilder, listBuilder, "AND type=:type ");	
        }
        
        if(!StringUtils.isBlank(queryDTO.getAttaName())){
        	this.bulidQueryStrItem(countBuilder, listBuilder, "AND name=:aname ");	
        }
        
        
		if(!StringUtils.isBlank(queryDTO.getTaskIds())){
			listBuilder.append("ORDER BY task_id NOT IN (:tids) ");	
		}
		
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString()).addEntity(getInheritClass());
		
		
		if(queryDTO.getTaskId()!=null){
			this.setQueryParamsVal(queryCount, queryList, "taskId", queryDTO.getTaskId());
			
		}else if(queryDTO.getProcInstId()!=null){
			this.setQueryParamsVal(queryCount, queryList, "procInstId", queryDTO.getProcInstId());

        }
    			
        if(queryDTO.getType()!=null){
        	this.setQueryParamsVal(queryCount, queryList, "type", queryDTO.getType());	
        }
        
        if(!StringUtils.isBlank(queryDTO.getAttaName())){
        	this.setQueryParamsVal(queryCount, queryList, "aname", queryDTO.getAttaName());
        }
        
		if(!StringUtils.isBlank(queryDTO.getTaskIds())){
			String[] tids = queryDTO.getTaskIds().split(",");
			queryList.setParameterList("tids",tids);

		}
		
		
		
		Long recTotal = (Long) queryCount.uniqueResult();
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		List<Attachment> list = queryList.list();
		
		
		return new Page<Attachment>(queryDTO.getCurrentPage(),queryDTO.getPageSize(),list,recTotal);
	
	}
	
	/**
	 * 根据流程实例id删除附件
	 * @param procInstId
	 */
	public void deleteByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		String SQL = "DELETE FROM flow_attachment WHERE proc_inst_Id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).setParameter(0, procInstId);
		query.executeUpdate();
		
	}
	
	
	/**
	 * 批量修改附件状态
	 * @param ids
	 * @param status
	 */
	public void updateStats(Long[] ids,Short status){
		Assert.notNull(status, "status could not be null");
		if(ids == null || ids.length == 0){
			return;
		}
		String SQL = "UPDATE flow_attachment SET status=:st WHERE id in (:ids)";
		Query query = this.getSession().createSQLQuery(SQL)
					 .setParameter("st", status)
				     .setParameterList("ids", ids);
		
		query.executeUpdate();
		
	}
	
}
