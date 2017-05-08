package com.wb.web.party.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.ReflectUtil;
import com.wb.web.party.dao.IJoinPartyInfoDao;
import com.wb.web.party.dto.JoinPartyInfoDTO;
import com.wb.web.party.dto.JoinPartyQueryDTO;
import com.wb.web.party.dto.JoinPartyStatsDTO;
import com.wb.web.party.entity.JoinPartyInfo;

@Repository("joinPartyInfoDao")
public class JoinPartyInfoDaoImpl extends BaseDaoImpl<Long, JoinPartyInfo> implements IJoinPartyInfoDao{

	
	public Page<JoinPartyInfoDTO> searchInfoByPage(JoinPartyQueryDTO queryDTO){
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num FROM p_join_party_info j INNER JOIN c_user u ON j.apply_user_id=u.id WHERE 1=1 ");
		StringBuilder listBuilder = new StringBuilder("SELECT j.id,j.apply_user_id AS applyUserId,u.user_name AS applyUserName,u.chinese_name AS chineseName,")
											  .append("j.department,j.memo,j.create_time AS createTime,j.start_time AS startTime,j.proc_inst_id AS processInstanceId,j.`status` ")
											  .append("FROM p_join_party_info j INNER JOIN c_user u ON j.apply_user_id=u.id WHERE 1=1 ");
		
		if(!StringUtils.isBlank(queryDTO.getApplyUserName())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND u.user_name LIKE :userName ");
	
		}
		
		
		
		if(!StringUtils.isBlank(queryDTO.getSidx()) && !StringUtils.isBlank(queryDTO.getSord())){
			
			String filedName = ReflectUtil.getFiledValueByAnnotation(getInheritClass(),queryDTO.getSidx());	
			listBuilder.append(generateOrderSql(queryDTO.getSord(),filedName,null));
		}else{
			listBuilder.append("ORDER BY j.id ASC ");
		}
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
								.addScalar("id", LongType.INSTANCE).addScalar("applyUserId", StringType.INSTANCE)
								.addScalar("applyUserName", StringType.INSTANCE).addScalar("chineseName", StringType.INSTANCE)
								.addScalar("department", StringType.INSTANCE).addScalar("memo", StringType.INSTANCE)
								.addScalar("createTime",DateType.INSTANCE).addScalar("startTime",DateType.INSTANCE)
								.addScalar("processInstanceId", LongType.INSTANCE).addScalar("status", ShortType.INSTANCE)
								.setResultTransformer(Transformers.aliasToBean(JoinPartyInfoDTO.class));
		
		if(!StringUtils.isBlank(queryDTO.getApplyUserName())){
			this.setQueryParamsVal(queryCount, queryList, "userName", "%"+queryDTO.getApplyUserName()+"%");
			
		}
			
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		return new Page<JoinPartyInfoDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}
	
	
	public JoinPartyInfoDTO getDetailById(Long id){
		Assert.notNull(id, "id must not be null");
		
		StringBuilder sb = new StringBuilder("SELECT j.id,j.apply_user_id AS applyUserId,u.user_name AS applyUserName,u.chinese_name AS chineseName,")
		  .append("j.department,j.memo,j.proc_inst_id AS processInstanceId,j.`status` ")
		  .append("FROM p_join_party_info j INNER JOIN c_user u ON j.apply_user_id=u.id WHERE j.id=:jid ");
		
		
		Query query = this.getSession().createSQLQuery(sb.toString())
				.addScalar("id", LongType.INSTANCE).addScalar("applyUserId", StringType.INSTANCE)
				.addScalar("applyUserName", StringType.INSTANCE).addScalar("chineseName", StringType.INSTANCE)
				.addScalar("department", StringType.INSTANCE).addScalar("memo", StringType.INSTANCE)
				.addScalar("processInstanceId", LongType.INSTANCE).addScalar("status", ShortType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(JoinPartyInfoDTO.class));
		
		query.setParameter("jid", id);
		
		
		return (JoinPartyInfoDTO) query.uniqueResult();
	}
	
	public JoinPartyInfoDTO getDetailByApplyUserId(String userId){
		Assert.notNull(userId, "userId must not be null");
		
		StringBuilder sb = new StringBuilder("SELECT j.id,j.apply_user_id AS applyUserId,u.user_name AS applyUserName,u.chinese_name AS chineseName,")
		  .append("j.department,j.memo,j.proc_inst_id AS processInstanceId,j.`status` ")
		  .append("FROM p_join_party_info j INNER JOIN c_user u ON j.apply_user_id=u.id WHERE j.apply_user_id=:uid ");
		
		
		Query query = this.getSession().createSQLQuery(sb.toString())
				.addScalar("id", LongType.INSTANCE).addScalar("applyUserId", StringType.INSTANCE)
				.addScalar("applyUserName", StringType.INSTANCE).addScalar("chineseName", StringType.INSTANCE)
				.addScalar("department", StringType.INSTANCE).addScalar("memo", StringType.INSTANCE)
				.addScalar("processInstanceId", LongType.INSTANCE).addScalar("status", ShortType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(JoinPartyInfoDTO.class));
		
		query.setParameter("uid", userId);
		
		
		return (JoinPartyInfoDTO) query.uniqueResult();
	}
	
	
	
	public Page<JoinPartyStatsDTO> searchStatsByPage(JoinPartyQueryDTO queryDTO){
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num FROM p_join_party_info j WHERE 1=1 ");
		StringBuilder listBuilder = new StringBuilder("SELECT j.id,j.apply_user_id AS applyUserId,u.user_name AS applyUserName,")
											  .append("u.chinese_name AS chineseName,j.department,j.proc_inst_id AS processInstanceId ")
											  .append("FROM p_join_party_info j INNER JOIN c_user u ON j.apply_user_id=u.id WHERE 1=1 ");
		this.bulidQueryStrItem(countBuilder, listBuilder, "AND j.proc_inst_id IS NOT NULL ");

		listBuilder.append("ORDER BY j.id DESC ");
		
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
								.addScalar("id", LongType.INSTANCE).addScalar("applyUserId", StringType.INSTANCE)
								.addScalar("applyUserName", StringType.INSTANCE).addScalar("chineseName", StringType.INSTANCE)
								.addScalar("department", StringType.INSTANCE).addScalar("processInstanceId", LongType.INSTANCE)
								.setResultTransformer(Transformers.aliasToBean(JoinPartyStatsDTO.class));
		
		
		queryList.setFirstResult((queryDTO.getCurrentPage()-1)*queryDTO.getPageSize());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		return new Page<JoinPartyStatsDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}
	
	/**
	 * 根据流程实例id获取对象
	 * @param procInstId
	 * @return
	 */
	public JoinPartyInfo getByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("processInstanceId", procInstId));
		
		return (JoinPartyInfo) cr.uniqueResult();
	}
	
	
}
