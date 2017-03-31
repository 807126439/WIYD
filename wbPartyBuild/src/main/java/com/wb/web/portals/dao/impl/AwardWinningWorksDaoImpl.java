package com.wb.web.portals.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IAwardWinningWorksDao;
import com.wb.web.portals.dto.awardWinningWorks.AwardDTO2;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksQueryDTO;
import com.wb.web.portals.entity.AwardWinningWorks;

@Repository("awardWinningWorksDao")
public class AwardWinningWorksDaoImpl extends BaseDaoImpl<Long, AwardWinningWorks> implements IAwardWinningWorksDao{

	@Override
	public Page<AwardWinningWorks> searchAwardWinningWorksByPage(
			AwardWinningWorksQueryDTO queryDTO) {
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
        
        cr.add(Restrictions.eq("activity.id",queryDTO.getActivityId()));
        
		
//		OrderBy orderBy = new OrderBy();
//		Order order = generateOrderCriterion(queryDTO.getSord(),queryDTO.getSidx());
//		if (order != null) {
//			orderBy.add(order);
//			orderBy.build(cr);
//		}
		
		Long rectoal = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
	    cr.setProjection(null);
	    cr.setResultTransformer(Criteria.ROOT_ENTITY);
	    	    
	    cr.setFirstResult(queryDTO.getStartQuery());
		cr.setMaxResults(queryDTO.getPageSize());
		
		List<AwardWinningWorks> list = cr.list();		
		
		return new Page<AwardWinningWorks>(queryDTO.getCurrentPage(),queryDTO.getPageSize(),list,rectoal);
	}
	public List<AwardWinningWorks> searchAwardWinningWorksByActivityId(Long id) {
		Criteria cr = this.getSession().createCriteria(getInheritClass());
        cr.add(Restrictions.eq("activity.id",id));
        List<AwardWinningWorks> list = cr.list();
        
        return list;
	}
	
	
	@Override
	public List<AwardDTO2> getAwardMessage(Long activityId,Long awardSettingId) {																								  
		StringBuilder listBuilder = new StringBuilder("SELECT b.id,b.title,b.author,b.description,c.pattern,a.comment ");
		listBuilder.append("from c_award_winning_works a ");
		listBuilder.append("INNER JOIN c_manuscript b on a.manuscript_id = b.id ");
		listBuilder.append("INNER JOIN m_base_file c on b.base_file_id = c.id ");
		listBuilder.append("where a.activity_id=? and a.awards_setting_id = ? ");   
		

		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
				.addScalar("author", StringType.INSTANCE).addScalar("description", StringType.INSTANCE)
				.addScalar("pattern", StringType.INSTANCE).addScalar("comment", StringType.INSTANCE)		
				.setResultTransformer(Transformers.aliasToBean(AwardDTO2.class))
				.setParameter(0, activityId).setParameter(1, awardSettingId);
		 

		return queryList.list();
	}
	

	@Override
	public Integer checkIsAward(Long activityId) {
		StringBuffer sql = new StringBuffer("select count(*) as num  from c_award_winning_works where activity_id=?");
		Query queryList = this.getSession().createSQLQuery(sql.toString()).addScalar("num", IntegerType.INSTANCE).setParameter(0, activityId);
		return (Integer) queryList.uniqueResult();
	}
	
	
	
	@Override
	public void delAWWByActivityId(Long activityId) {
		
		Assert.notNull(activityId, "activityId can not be null");
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from c_award_winning_works where activity_id = ?");
		Query query = this.getSession().createSQLQuery(sql.toString()).setLong(0, activityId);
		query.executeUpdate();	
		
	}
	
}
