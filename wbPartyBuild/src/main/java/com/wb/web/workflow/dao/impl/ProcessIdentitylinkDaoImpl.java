package com.wb.web.workflow.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IProcessIdentitylinkDao;
import com.wb.web.workflow.dto.node.ProcessNodeDTO;
import com.wb.web.workflow.entity.ProcessIdentitylink;

@Repository("processIdentitylinkDao")
public class ProcessIdentitylinkDaoImpl extends BaseDaoImpl<Long, ProcessIdentitylink> implements IProcessIdentitylinkDao{

	
	
	public List<ProcessIdentitylink> getProcessIdentitylinkByNodeId(String nodeId,Boolean isGetUser){
		Assert.hasText(nodeId, "nodeId could not be null");
		
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("processNode.id", nodeId));
		
		if(isGetUser!=null){
			if(isGetUser){
				cr.add(Restrictions.isNotNull("userId"));
			}else{
				cr.add(Restrictions.isNotNull("groupId"));
			}
		}
		
		
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessIdentitylink> getProcessIdentitylinksById(String nodeId) {
		Assert.hasText(nodeId, "nodeId could not be null");
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("processNode.id", nodeId));
		return cr.list();
	}
	
	
}
