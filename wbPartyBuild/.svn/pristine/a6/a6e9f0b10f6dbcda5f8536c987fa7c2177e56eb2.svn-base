package com.wb.web.portals.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.web.portals.dao.IThemeActivityDao;
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.dto.themeActivity.ThemeActivityQueryDTO;
import com.wb.web.portals.entity.ThemeActivity;



@Repository("themeActivityDao")
public class ThemeActivityDaoImpl extends BaseDaoImpl<Long, ThemeActivity> implements IThemeActivityDao{


	
	@Override
	public ThemeActivityDTO getActivePhotoActivity() {
	
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.activity_name as activityName,a.content,a.start_date as startDate,a.end_date as endDate");
		sql.append(" from  c_theme_activity a where a.activityType = 1 and a.status = 1 ");		
		Query query = this.getSession().createSQLQuery(sql.toString())
						.addScalar("id",LongType.INSTANCE).addScalar("activityName",StringType.INSTANCE)
						.addScalar("content",StringType.INSTANCE).addScalar("startDate",DateType.INSTANCE)
						.addScalar("endDate",DateType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(ThemeActivityDTO.class));			
		return (ThemeActivityDTO) query.uniqueResult();
	}
	
	
	

	public Page<ThemeActivity> searchThemeActivityByPage(ThemeActivityQueryDTO queryDto) {
        Criteria cr = this.getSession().createCriteria(getInheritClass());        
       
        if(queryDto.getActivityName()!=""&queryDto.getActivityName()!=null){
        	cr.add(Restrictions.ilike("activityName",queryDto.getActivityName(), MatchMode.ANYWHERE));        	 
        } if(queryDto.getActivityType()!=null){
        	cr.add(Restrictions.eq("activityType",(short)queryDto.getActivityType()));         	 
        }
        
        queryDto.setSidx("status");
        queryDto.setSord("desc");
 		OrderBy orderBy = new OrderBy();
		Order order = generateOrderCriterion(queryDto.getSord(),queryDto.getSidx());
		if (order != null) {
			orderBy.add(order);
			orderBy.build(cr);
		}		
		Long rectoal = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
	    cr.setProjection(null);
	    cr.setResultTransformer(Criteria.ROOT_ENTITY);	    	    
	    cr.setFirstResult(queryDto.getStartQuery());
		cr.setMaxResults(queryDto.getPageSize());		
		List<ThemeActivity> list = cr.list();				
		return new Page<ThemeActivity>(queryDto.getCurrentPage(),queryDto.getPageSize(),list,rectoal);
	
	}
	
	
	
	@Override
	public List<ThemeActivity> searchByFild(String fildName, Object Object) {
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq(fildName,Object));
		List<ThemeActivity> list = cr.list();
		return list;
	}
	
	
	@Override
	public ThemeActivityDTO getLastPhotoActivity(Long nowId) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.activity_name as activityName,a.content,a.start_da" +
				"te as startDate,a.end_date as endDate");
		sql.append(" from c_theme_activity a");
		sql.append(" where a.id = (SELECT MAX(id) FROM c_theme_activity WHERE activityType = 1 and id <:id)");
		
		Query query = this.getSession().createSQLQuery(sql.toString())
						.addScalar("id",LongType.INSTANCE).addScalar("activityName",StringType.INSTANCE)
						.addScalar("content",StringType.INSTANCE).addScalar("startDate",DateType.INSTANCE)
						.addScalar("endDate",DateType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(ThemeActivityDTO.class)).setParameter("id", nowId);
		
		
		return (ThemeActivityDTO) query.uniqueResult();
	}




	@Override
	public Page<ThemeActivityDTO> searchThemeActivityByPgae2(Integer activityType,Integer curPage,
			Integer pageSize) {
		
		
		StringBuilder countBuilder = new StringBuilder("SELECT count(*) as num from c_theme_activity a ");				
			
		StringBuilder listBuilder = new StringBuilder("SELECT a.id,a.activity_name as activityName,a.start_date as startDate  from c_theme_activity a ");				
		
		
		this.bulidQueryStrItem(countBuilder, listBuilder, "where a.`status` = 1 and  a.activityType =:activityType ");		
		

		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("activityName", StringType.INSTANCE)
				.addScalar("startDate", TimestampType.INSTANCE)	
				.setResultTransformer(Transformers.aliasToBean(ThemeActivityDTO.class))
				.setFirstResult((curPage - 1) * pageSize)
				.setMaxResults(pageSize);	
		
		this.setQueryParamsVal(queryCount, queryList, "activityType", activityType);
		
		return new Page<ThemeActivityDTO>(curPage, pageSize, queryList.list(),(Long) queryCount.uniqueResult());	
	}




	@Override
	public Integer countListSize(Integer activityType, Integer status) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as num from c_theme_activity a");
		sql.append(" where a.`status` =:status");
		sql.append(" and a.`activityType` =:activityType");
		
		Query query = this.getSession().createSQLQuery(sql.toString())
				.addScalar("num",IntegerType.INSTANCE)
				.setParameter("status", status).setParameter("activityType", activityType);
		
		return (Integer)query.uniqueResult();
	}




	@Override
	public ThemeActivityDTO getBySql(Long id) {
		String sql = "SELECT a.id,a.activity_name as activityName,a.content,a.start_date as startDate,a.end_date as endDate from c_theme_activity a where a.id=:id";
		Query query = this.getSession().createSQLQuery(sql.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("activityName",StringType.INSTANCE)
				.addScalar("content",StringType.INSTANCE)
				.addScalar("startDate",TimestampType.INSTANCE).addScalar("endDate",TimestampType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ThemeActivityDTO.class))
				.setParameter("id", id);
		return (ThemeActivityDTO) query.uniqueResult();
	}
	
}
