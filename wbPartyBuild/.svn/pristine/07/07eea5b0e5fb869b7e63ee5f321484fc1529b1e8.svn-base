package com.wb.web.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.dto.user.UserQueryDTO;
import com.wb.web.system.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Long, User> implements IUserDao {

	@SuppressWarnings("unchecked")
	public Page<User> searchUserByPage(UserQueryDTO queryDTO){
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.add(Restrictions.eq("isDel", false));
	
		if(!StringUtils.isBlank(queryDTO.getName())){			
			cr.add(Restrictions.like("userName","%"+ queryDTO.getName()+"%"));						
		}
		
		if(!StringUtils.isBlank(queryDTO.getCnName())){			
			cr.add(Restrictions.eq("chineseName", queryDTO.getCnName()));						
		}
		
	
		
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
		List<User> list = cr.list();
		
		
		return new Page<User>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	}
	
	

	
	
	
	/**
	 * 根据用户名查询用户
	 * @param userName 用户名 	
	 * @return
	 * @author wb_java_zjr
	
	public LoginUserDTO getLoginUserByName(final String userName){		
		if(userName == null){
			throw new MyException("userName is null");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u.id as id,u.user_name as userName,u.pass_word as password,u.phone as phone,");
		sb.append("u.limit_once_up_num as limitOnceUpNum,u.limit_once_up_capacity as limitOnceUpCapacity,");
		sb.append("u.account_non_expired as accountNonExpired,u.account_non_expired as accountNonLocked,");
		sb.append("u.credentials_non_expired as credentialsNonExpired,u.enabled as enabled,f.family_id as familyKey, ");
		sb.append("f.photo_path as photoPath,f.zone_path_id as zonePathId ");
		sb.append("FROM c_user u ");
		sb.append("LEFT  JOIN c_family_member f ON f.id=u.id ");
		sb.append("WHERE u.user_name=:name OR  u.phone=:phone ");
		
		
				
		Query query = session.createSQLQuery(sb.toString())
				.addScalar("id", Hibernate.LONG).addScalar("userName", Hibernate.STRING)
				.addScalar("password", Hibernate.STRING).addScalar("phone", Hibernate.STRING)
				.addScalar("limitOnceUpNum", Hibernate.INTEGER)
				.addScalar("limitOnceUpCapacity", Hibernate.LONG).addScalar("accountNonExpired", Hibernate.BOOLEAN)
				.addScalar("accountNonLocked", Hibernate.BOOLEAN).addScalar("credentialsNonExpired", Hibernate.BOOLEAN)
				.addScalar("enabled", Hibernate.BOOLEAN).addScalar("familyKey", Hibernate.LONG)
				.addScalar("photoPath", Hibernate.STRING).addScalar("zonePathId", Hibernate.LONG)
				
				.setResultTransformer(Transformers.aliasToBean(LoginUserDTO.class));
		
		
			query.setParameter("name", userName);
			query.setParameter("phone", userName);
		
		
		
		return (LoginUserDTO) query.uniqueResult();
		
	} */
	
	
	
	/**
	 * 删除用户角色关联记录
	 * @param roleId	角色id
	 * @param userIds	用户id集合
	 */
	public void deleteUserRoleRelation(Long roleId,List<Long> userIds){
		if(roleId == null || userIds.isEmpty()){
			return;
		}
		String SQl = "DELETE FROM c_user_role WHERE user_id in (:uids) AND role_id=:rid ";
		Query query = this.getSession().createSQLQuery(SQl)
						.setParameterList("uids", userIds)
						.setParameter("rid", roleId);
		
		query.executeUpdate();
		
	}
	
	/**
	 * 删除用户角色关联记录
	 * @param roleId	角色id
	 * @param userId	用户id
	 */
	public void deleteUserRoleRelation(Long roleId,Long userId){
		if(roleId == null || userId == null){
			return;
		}
		String SQl = "DELETE FROM c_user_role WHERE user_id =:uid AND role_id=:rid ";
		Query query = this.getSession().createSQLQuery(SQl)
						.setParameter("uid", userId)
						.setParameter("rid", roleId);
		
		query.executeUpdate();
		
	}


	@Override
	public Map<String, Object> getStatistics() {
		Map<String, Object> resultList=new HashMap<String, Object>();
		//统计性别比例
		String SQl = "SELECT COUNT(*) AS value,sex AS name FROM c_user WHERE is_party = true GROUP BY sex ";
		Query query = this.getSession().createSQLQuery(SQl);
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		for(Map<String,Object> o:list){
			if(null==o.get("name")||o.get("name").toString().isEmpty()){
				o.put("name", "未知");
			};
		}
		resultList.put("totalSex", list);
		//统计年龄比例
		String SQl2 ="select "+
					"SUM(case when age<=30  then 1 ELSE 0 end) AS '1', "+
					"SUM(case when age>30 and age <=40 then 1 ELSE 0 end)AS '2', "+
					"SUM(case when age>40 and age <=50 then 1 ELSE 0 end)AS '3', "+
					"SUM(case when age>50 then 1 ELSE 0 end)AS '4' "+
					"from c_user WHERE is_party = true";
		Query query2 = this.getSession().createSQLQuery(SQl2);
		query2.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> agelist=agelist=query2.list();
		resultList.put("totalAge", agelist);
		//统计在册情况比例
		String partySQl = "SELECT COUNT(*) AS 'value', b.dict_item AS 'name' FROM 	c_user u LEFT JOIN c_basedict b ON b.dict_type = 'djp_type' AND u.party_status = b.dict_value WHERE 	is_party = TRUE GROUP BY 	party_status";
		Query partyQuery = this.getSession().createSQLQuery(partySQl);
		partyQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> partyList=partyQuery.list();
		resultList.put("totalParty", partyList);
		//统计学历比例
		String degreeSQl = "SELECT 	COUNT(*) AS 'value', edu_degree_id, edu.edu_name AS 'name', edu.sort FROM c_user u LEFT JOIN c_edu_degree edu ON u.edu_degree_id = edu.id WHERE is_party = TRUE GROUP BY 	edu_degree_id  ORDER BY edu.sort ";
		Query degreeQuery = this.getSession().createSQLQuery(degreeSQl);
		degreeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> degreeList=degreeQuery.list();
		for(Map<String,Object> o:degreeList){
			if(null==o.get("name")||o.get("name").toString().isEmpty()){
				o.put("name", "未知");
			};
		}
		resultList.put("totalDegree", degreeList);

		return resultList;
	}
	
	
	/**
	 * 废弃用户
	 * @param status
	 * @param ids
	 */
	public void discardUser(String ids[]){
		Assert.notEmpty(ids);
		String SQL = "update c_user set is_del=1,enabled=0,credentials_non_expired=0,account_non_locked=0,account_non_expired=0 where id in (:uids)";
		Query query = this.getSession().createSQLQuery(SQL)				      
				      .setParameterList("uids", ids);
		
		query.executeUpdate();
	}
	
	
	
}
