package com.wb.web.portals.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IActivityRuleDao;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.activityRule.ActivityRuleQueryDTO;
import com.wb.web.portals.entity.ActivityRule;

@Repository("activityRuleDao")
public class ActivityRuleDaoImpl extends BaseDaoImpl<Long, ActivityRule> implements IActivityRuleDao{


	@Override
	public Page<ActivityRule> searchActivityRuleByPage2(
			ActivityRuleQueryDTO queryDTO) {

		Criteria cr = this.getSession().createCriteria(this.getInheritClass());	
		
		cr.add(Restrictions.eq("activity.id", queryDTO.getActivityId()));

		
		OrderBy orderBy = new OrderBy();				
		Order order = generateOrderCriterion(queryDTO.getSord(),queryDTO.getSidx());
		if (order != null) {
			orderBy.add(order);
			orderBy.build(cr);
		}	
		Long recTotal = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
	    cr.setProjection(null);
	    cr.setResultTransformer(Criteria.ROOT_ENTITY);   	    
	    cr.setFirstResult(queryDTO.getStartQuery());
		cr.setMaxResults(queryDTO.getPageSize());
		List<ActivityRule> list = cr.list();
		
		return new Page<ActivityRule>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	}
	
	

	@Override
	public List<ActivityRuleDTO> getAllRulesByActivityId(Long activityId){		
		StringBuffer sql = new StringBuffer();
		sql.append("select id,content,isMain from c_activity_rule where activity_id = "+activityId);
		sql.append(" ORDER BY num asc");
		
		Query query = this.getSession().createSQLQuery(sql.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("content",StringType.INSTANCE).addScalar("isMain",BooleanType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ActivityRuleDTO.class));	
		
		return query.list();
	}



	@Override
	public void delARByActivityId(Long activityId) {
		
		Assert.notNull(activityId, "activityId can not be null");
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from c_activity_rule where activity_id = ?");
		Query query = this.getSession().createSQLQuery(sql.toString()).setLong(0, activityId);
		query.executeUpdate();	
		
	}



	
	
}
