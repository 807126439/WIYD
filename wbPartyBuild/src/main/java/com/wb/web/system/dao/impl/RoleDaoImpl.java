package com.wb.web.system.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.web.system.dao.IRoleDao;
import com.wb.web.system.dto.role.RoleQueryDTO;
import com.wb.web.system.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Long, Role> implements IRoleDao {


	@SuppressWarnings("unchecked")
	public List<Role> getRolesByUserId(String id) {
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("userId must not be null");
		}
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.createAlias("users", "u");
		cr.add(Restrictions.eq("u.id", id));		
		
		return cr.list();
	}

	

	@SuppressWarnings("unchecked")
	public List<Role> searchListByCondition(RoleQueryDTO queryDTO) {
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		if(!StringUtils.isBlank(queryDTO.getUserId())){
			cr.createAlias("users", "u");
			cr.add(Restrictions.eq("u.id",queryDTO.getUserId()));
		}
		
		return cr.list();
	}

	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	public Page<Role> searchRoleByPage(RoleQueryDTO queryDTO){
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		if(queryDTO.getRoleName()!=null && !StringUtils.isBlank(queryDTO.getRoleName())){
			cr.add(Restrictions.like("roleName","%"+ queryDTO.getRoleName()+"%"));
		}
		
		if(queryDTO.getText()!=null && !StringUtils.isBlank(queryDTO.getText())){
			cr.add(Restrictions.like("roleText","%"+  queryDTO.getText()+"%"));
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
		List<Role> list = cr.list();
		
		
		return new Page<Role>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	}
	
	
	
	
	
	
	
	
	
}
