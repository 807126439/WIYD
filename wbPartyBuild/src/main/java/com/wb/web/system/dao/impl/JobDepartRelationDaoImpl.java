package com.wb.web.system.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.system.dao.IJobDepartRelationDao;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.dto.department.JobDepartRelationQueryDTO;
import com.wb.web.system.entity.JobDepartRelation;

@Repository("jobDepartRelationDao")
public class JobDepartRelationDaoImpl extends BaseDaoImpl<String, JobDepartRelation> implements IJobDepartRelationDao{

	
	public Page<JobDepartRelationDTO> searchJobDepartRelationByPage(JobDepartRelationQueryDTO queryDTO){
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num FROM c_job_depart_relation r WHERE r.department_id=:depId ");
		
		StringBuilder listBuilder  = new StringBuilder();
		listBuilder.append("SELECT r.id as id,r.department_id as departId,r.job_duty_id as jobId,j.job_name as jobName,")
				   .append("r.user_id as userId,u.user_name as userName,r.memo as memo,r.status as status ")
				   .append("FROM c_job_depart_relation r ")
				   .append("LEFT JOIN c_job_duty j ON j.id=r.job_duty_id ")
				   .append("LEFT JOIN c_user u ON u.id=r.user_id WHERE r.department_id=:depId ");
		
		this.bulidQueryStrItem(countBuilder, listBuilder, "ORDER BY r.id DESC ");
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("id", StringType.INSTANCE).addScalar("departId", StringType.INSTANCE)
						.addScalar("jobId", StringType.INSTANCE).addScalar("jobName", StringType.INSTANCE)
						.addScalar("userId", StringType.INSTANCE).addScalar("userName", StringType.INSTANCE)
						.addScalar("memo", StringType.INSTANCE).addScalar("status", ShortType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(JobDepartRelationDTO.class));
		
		this.setQueryParamsVal(queryCount, queryList, "depId", queryDTO.getDepId());
		
		Long recTotal = (Long) queryCount.uniqueResult();
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		List<JobDepartRelationDTO> list = queryList.list();
		
		return new Page<JobDepartRelationDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
		
	}
	
	
	
	public JobDepartRelationDTO getJobDepartRelationById(String id){
		StringBuilder sb  = new StringBuilder();
		sb.append("SELECT r.id as id,r.department_id as departId,d.depart_name as departName,r.job_duty_id as jobId,j.job_name as jobName,")
				   .append("r.user_id as userId,u.user_name as userName,r.memo as memo,r.status as status,r.sort_num as sortNum ")
				   .append("FROM c_job_depart_relation r ")
				   .append("LEFT JOIN c_job_duty j ON j.id=r.job_duty_id ")
				   .append("LEFT JOIN c_department d ON d.id=r.department_id ")
				   .append("LEFT JOIN c_user u ON u.id=r.user_id WHERE r.id=:rid ");
		
		Query query = this.getSession().createSQLQuery(sb.toString())
				.addScalar("id", StringType.INSTANCE).addScalar("departId", StringType.INSTANCE)
				.addScalar("departName", StringType.INSTANCE)
				.addScalar("jobId", StringType.INSTANCE).addScalar("jobName", StringType.INSTANCE)
				.addScalar("userId", StringType.INSTANCE).addScalar("userName", StringType.INSTANCE)
				.addScalar("memo", StringType.INSTANCE).addScalar("status", ShortType.INSTANCE)
				.addScalar("sortNum", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(JobDepartRelationDTO.class));
		
		query.setParameter("rid", id);
		
		
		return (JobDepartRelationDTO) query.uniqueResult();
	}



	/**
	 * 获取用户的主部门
	 * @param userId
	 * @return
	 */
	public List<String> getUserMainDepart(String userId) {
		StringBuilder sb  = new StringBuilder();
		sb.append("SELECT r.department_id AS ids FROM c_job_depart_relation r ")
		  .append("INNER JOIN c_user u ON u.id=r.user_id  ")
		  .append("INNER JOIN c_department d ON d.id=r.department_id ")
		  .append("WHERE u.id=:userId AND d.is_main=:flag ");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addScalar("ids", StringType.INSTANCE);		
		query.setParameter("userId", userId);
		query.setParameter("flag", true);
	
		
		return query.list();
	}
	
	
	
}
