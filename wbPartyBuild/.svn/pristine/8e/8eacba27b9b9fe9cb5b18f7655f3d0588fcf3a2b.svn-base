package com.wb.web.portals.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.ICommunicationDao;
import com.wb.web.portals.dto.communication.CommunicationDTO;
import com.wb.web.portals.dto.communication.CommunicationQueryDTO;
import com.wb.web.portals.entity.Communication;

/**
 * @author wb_java_lzx
 *
 */
@Repository("communicationDao")
public class CommunicationDaoImpl extends BaseDaoImpl<Long, Communication> implements ICommunicationDao{

	public CommunicationDTO getCurrentDto(Long id){
		StringBuilder sb = new StringBuilder();				
		sb.append("select id,title,content,sponsor,start_date as startDate,end_date as endDate,love")
		  .append(" from cy_communication")
		  .append(" where id = :id");

		
		Query query = this.getSession().createSQLQuery(sb.toString())
					  .addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
					  .addScalar("content", StringType.INSTANCE)
					  .addScalar("sponsor", StringType.INSTANCE)
					  .addScalar("startDate", DateType.INSTANCE)
					  .addScalar("endDate", DateType.INSTANCE)
					  .addScalar("love", IntegerType.INSTANCE)
					  .setResultTransformer(Transformers.aliasToBean(CommunicationDTO.class))
					  .setParameter("id", id);
			
		return (CommunicationDTO) query.uniqueResult();
	}


	@Override
	public List<CommunicationDTO> getListByCondition(CommunicationQueryDTO queryDTO) {
		StringBuilder sql = new StringBuilder();				
		sql.append("select id,title,sponsor,start_date as startDate");
		sql.append(" from cy_communication");
	    sql.append(" where 1=1 ");
		if(queryDTO.getStatus()!=null){
			 sql.append(" and status ="+queryDTO.getStatus());
		}		
	    sql.append(" order by start_date desc");
		
		Query query = this.getSession().createSQLQuery(sql.toString())
				     .addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
				     .addScalar("sponsor", StringType.INSTANCE).addScalar("startDate", DateType.INSTANCE)
				     .setResultTransformer(Transformers.aliasToBean(CommunicationDTO.class));
			
		List<CommunicationDTO> resultList = query.list();
		return resultList;	
	}



	@Override
	public List<CommunicationDTO> getPreAndNextFiledById(Long id) {		
		StringBuilder sb = new StringBuilder();				
		sb.append("select a.id,a.title,a.sponsor,a.start_date as startDate,a.content")
		.append(" from cy_communication a")
		.append(" where a.id =(")
	    .append(" select MAX(id) from cy_communication where id<:id")
		.append(" )or a.id =(")
		.append(" select MIN(id) from cy_communication where id>:id)")
		.append(" and a.status = 2");
				
		Query query = this.getSession().createSQLQuery(sb.toString())
				  .addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
				  .addScalar("sponsor", StringType.INSTANCE).addScalar("startDate", DateType.INSTANCE)
				  .addScalar("content", StringType.INSTANCE)
				  .setResultTransformer(Transformers.aliasToBean(CommunicationDTO.class))
				  .setParameter("id", id);
		
		List<CommunicationDTO> resultList = query.list();
		return resultList;
	}


	@Override
	public Page<CommunicationDTO> searchCommunicationByPage(CommunicationQueryDTO queryDTO){
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num from cy_communication a where 1=1 ");
		StringBuilder listBuilder = new StringBuilder("SELECT a.id,a.status,a.title,a.content,a.sponsor,a.start_date as startDate,a.end_date as endDate FROM cy_communication a where 1=1 ");

		
		if (queryDTO.getTitle() != null && queryDTO.getTitle() !="") {
			this.bulidQueryStrItem(countBuilder, listBuilder," AND a.title like:title ");				
		}if (queryDTO.getSponsor() != null && queryDTO.getSponsor() !="") {
			this.bulidQueryStrItem(countBuilder, listBuilder," AND a.sponsor like:sponsor ");				
		}
		if (queryDTO.getStatus() != null) {
			this.bulidQueryStrItem(countBuilder, listBuilder," AND a.status =:status ");				
		}

		
			listBuilder.append("order by a.status asc,a.last_operator_time DESC");
		

		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("id", LongType.INSTANCE).addScalar("status", ShortType.INSTANCE)
						.addScalar("title", StringType.INSTANCE).addScalar("content", StringType.INSTANCE)	
						.addScalar("sponsor", StringType.INSTANCE).addScalar("startDate", TimestampType.INSTANCE)				
						.addScalar("endDate", TimestampType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(CommunicationDTO.class));	
						
						

		
		
		if (queryDTO.getTitle() != null&& queryDTO.getTitle() !="") {
			this.setQueryParamsVal(queryCount, queryList, "title", "%"+queryDTO.getTitle()+"%");	
		}
		if (queryDTO.getSponsor() != null&& queryDTO.getSponsor() !="") {
			this.setQueryParamsVal(queryCount, queryList, "sponsor", "%"+queryDTO.getSponsor()+"%");	
		}
		if (queryDTO.getStatus() != null) {
			this.setQueryParamsVal(queryCount, queryList,"status",queryDTO.getStatus());				
		}
		
		
		Long recTotal = (Long) queryCount.uniqueResult();		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());		
		List<CommunicationDTO> list = queryList.list();
		
		return new Page<CommunicationDTO>(queryDTO.getCurrentPage(),queryDTO.getPageSize(), list, recTotal);
	}
	
	
	public int countTotalSize(){		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num from cy_communication a where a.status =1");	   
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Long num  = (Long) queryCount.uniqueResult();		
		return Integer.valueOf(num.toString());
	}


	@Override
	public void deleteVote(Long comId, String userId) {
		String sql = "delete from cy_communication_voter where communication_id = :comId and user_id =:userId";		
		Query query = this.getSession().createSQLQuery(sql).setParameter("comId", comId).setParameter("userId",userId);	
		query.executeUpdate();
	
	}

}
