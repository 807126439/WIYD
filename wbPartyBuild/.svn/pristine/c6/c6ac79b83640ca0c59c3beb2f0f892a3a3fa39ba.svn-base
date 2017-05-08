package com.wb.web.system.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.web.system.dao.IAuthorityDao;
import com.wb.web.system.dto.authority.AuthLightDto;
import com.wb.web.system.dto.authority.AuthLightDto2;
import com.wb.web.system.dto.authority.AuthQueryDTO;
import com.wb.web.system.dto.authority.AuthTreeQueryDTO;
import com.wb.web.system.entity.Authority;

@Repository("authorityDao")
public class AuthorityDaoImpl extends BaseDaoImpl<Long, Authority> implements IAuthorityDao{

	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<Authority> searchAuthorityByPage(AuthQueryDTO queryDTO){
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.createAlias("parent", "par",JoinType.LEFT_OUTER_JOIN);
		
		if(!StringUtils.isBlank(queryDTO.getParAuthName())){
			if(queryDTO.getParAuthName().equals("0")){
				cr.add(Restrictions.isNull("parent"));
			}else{			
				cr.add(Restrictions.like("par.authText","%"+ queryDTO.getParAuthName()+"%"));
			}
					
		}
		
		
		if(!StringUtils.isBlank(queryDTO.getAuthName())){
			cr.add(Restrictions.like("authText", "%"+queryDTO.getAuthName().trim()+"%"));
		}
		
		if(!StringUtils.isBlank(queryDTO.getAuthCode())){
			cr.add(Restrictions.like("authCode", "%"+queryDTO.getAuthCode().trim()+"%"));
		}
		
		if(queryDTO.getAuthType()!=null){
			cr.add(Restrictions.eq("authType",queryDTO.getAuthType()));
		}
		
		if(queryDTO.getFlag()!=null){
			cr.add(Restrictions.eq("flag",queryDTO.getFlag()));
		}
	
		OrderBy orderBy = new OrderBy();
		
		if(queryDTO.getSidx()!=null && queryDTO.getSidx().equals("parentName")){
			queryDTO.setSidx("parent.id");
		}
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
		List<Authority> list = cr.list();
		
		
		return new Page<Authority>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	}
	
	
	/**
	 * 查询树结构
	 * @param queryDTO
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> searchTreeByCondition(AuthTreeQueryDTO queryDTO){
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		if(!StringUtils.isBlank(queryDTO.getPid())){
			cr.add(Restrictions.eq("parent.id", queryDTO.getPid()));
		}else{
			cr.add(Restrictions.isNull("parent"));
		}
		
		cr.addOrder(Order.asc("authOrder"));
		
		return cr.list();
	}
	
	
	
	
	
	
	
	/**
	 * 通过角色查询权限
	 * @param id
	 * @param flag
	 * @param authTypes
	 * @param <code>false</code>
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> searchAuthoritiesByRole(String roleId,Short flag, Short... authTypes) {
		if(StringUtils.isBlank(roleId)){
			throw new NullPointerException("id");
		}
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.createAlias("roles", "r");
		cr.add(Restrictions.eq("r.id", roleId));
		
		if(authTypes!= null && authTypes.length>0){
			cr.add(Restrictions.in("authType", authTypes));			
			
			for (int i = 0; i < authTypes.length; i++) {
				if(authTypes[i] == Authority.auth_acess){
					cr.add(Restrictions.isNotNull("resourecesUrl"));
				}
			}
			
		}
		
		if(flag != null){
			cr.add(Restrictions.eq("flag", flag));
		}
		
		return cr.list();
	}

	
	
	/**
	 * 根据权限备注和角色列表查找权限，并返回权限的id
	 * @param roleList
	 * @param authId
	 * @return
	 */
	public Long getAuthByRoleAndText(String roleList,String text){
		if(roleList == null || roleList.isEmpty() || text==null || text.isEmpty()){
			throw new IllegalArgumentException("Cannot param is null");
		}
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT a.id as id FROM c_authority a ");
		sb.append("LEFT JOIN c_authority_role z ON a.id=z.auth_id ");
		sb.append("LEFT JOIN c_role r ON z.role_id=r.id  ");
		sb.append("WHERE FIND_IN_SET(r.role_name,:name) and a.auth_text=:text ");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addScalar("id", LongType.INSTANCE);
		query.setParameter("name", roleList);
		query.setParameter("text", text);
		
		return (Long) query.uniqueResult();
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AuthLightDto> getAuthsByRoleAndParent(String roleList,String parent,Short flag,Short authType){
		if(roleList == null || roleList.isEmpty()){
			throw new IllegalArgumentException("Cannot param roleList is null");
		}		
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT a.id as id,a.auth_text as text,");
	    sb.append("a.resoureces_url as resourecesUrl,a.icon as icon FROM c_authority a ");
		sb.append("LEFT JOIN c_authority_role z ON a.id=z.auth_id ");
	    sb.append("LEFT JOIN c_role r ON z.role_id=r.id "); 
	    sb.append("WHERE  FIND_IN_SET(r.role_name,'"+roleList+"') "); 
	    
	    if(parent!=null){
	    	sb.append("and a.parent_id =:parent ");
	    }else{
	    	sb.append("and a.parent_id is null ");
	    }
	    
	    if(flag!=null){
	    	sb.append("and a.flag = :flag ");
	    }
	    if(authType!=null){
	    	sb.append("and a.auth_type = :authType ");
	    }			    
	    
	    sb.append("ORDER BY a.auth_order ASC ");
		
		Query query = this.getSession().createSQLQuery(sb.toString())
					.addScalar("id", StringType.INSTANCE).addScalar("text", StringType.INSTANCE)
					.addScalar("resourecesUrl", StringType.INSTANCE).addScalar("icon", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(AuthLightDto.class));	
		
		if(parent!=null){
			query.setParameter("parent", parent);
	    }
	    if(flag!=null){
	    	query.setParameter("flag", flag);
	    }
	    if(authType!=null){
	    	query.setParameter("authType", authType);
	    }	
		
		return query.list();
		
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> getAuthControlItem(final String roleList){
		if(StringUtils.isBlank(roleList)){
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT auth_code FROM c_authority a ");
		sb.append("INNER JOIN c_authority_role z ON z.auth_id=a.id ");
		sb.append("INNER JOIN c_role r ON r.id=z.role_id ");
		sb.append("WHERE  FIND_IN_SET(r.role_name,'"+roleList+"') ");
		sb.append("AND a.flag=:flag AND (a.auth_type=:t1 OR a.auth_type=:t2 ) ");
		
		Query query = this.getSession().createSQLQuery(sb.toString());
		query.setParameter("flag", Authority.flag_openControl);
		query.setParameter("t1", Authority.auth_acess); 
		query.setParameter("t2", Authority.auth_button);
		
		return query.list();
		

	}
	
	
	@SuppressWarnings("unchecked")
	public List<AuthLightDto2> findAllAuthLightDto2(Short flag){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT auth_code as authCode,oper_item_id as operItemId ");
		sb.append("FROM c_authority WHERE flag=:flag AND oper_item_id IS NOT NULL ");
		
		Query query = this.getSession().createSQLQuery(sb.toString())
				      .addScalar("authCode", StringType.INSTANCE).addScalar("operItemId", LongType.INSTANCE)
				  	  .setResultTransformer(Transformers.aliasToBean(AuthLightDto2.class));	
		query.setParameter("flag", flag);
		
		return query.list();
	}
	
	
	
	
}
