package com.wb.web.system.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.web.study.dto.examPaper.ExamPaperDTO;
import com.wb.web.system.dao.IDepartmentDao;
import com.wb.web.system.dto.department.DepartmentDTO3;
import com.wb.web.system.dto.department.DepartmentQueryDTO;
import com.wb.web.system.entity.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<String, Department> implements IDepartmentDao{
	
	
	  public Page<Department> searchDepartmentByPage(DepartmentQueryDTO queryDto) {
	        Criteria cr = this.getSession().createCriteria(getInheritClass());
				    	
	        if(StringUtils.isBlank(queryDto.getParId())){
	        	cr.add(Restrictions.isNull("parent"));
	       
	        }else{
	        	cr.add(Restrictions.or(Restrictions.eq("id", queryDto.getParId()), Restrictions.eq("parent.id", queryDto.getParId())));
	        }	        
	        
			
			OrderBy orderBy = new OrderBy();
			Order order = generateOrderCriterion(queryDto.getSord(),queryDto.getSidx());
			if (order != null) {
				orderBy.add(order);
				orderBy.build(cr);
			}else{
				cr.addOrder(Order.asc("sortNum"));
			}
			
			Long rectoal = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		    cr.setProjection(null);
		    cr.setResultTransformer(Criteria.ROOT_ENTITY);
		    	    
		    cr.setFirstResult(queryDto.getStartQuery());
			cr.setMaxResults(queryDto.getPageSize());
			
			List<Department> list = cr.list();		
			
			return new Page<Department>(queryDto.getCurrentPage(),queryDto.getPageSize(),list,rectoal);
		
		}
	
	
	
	
	
	public List<Department> searchTreeByCondition(DepartmentQueryDTO queryDTO){
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		if(queryDTO.getParId()!=null){
			cr.add(Restrictions.eq("parent.id", queryDTO.getParId()));
		}else{
			cr.add(Restrictions.isNull("parent"));
		}
		
	
		cr.addOrder(Order.asc("sortNum"));
		
		return cr.list();
	}




	/**
	 * 查询所有为主部门的部门
	 */
	@Override
	public List<Department> getAllMainDepartment() {
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.add(Restrictions.eq("isMain", true));
		
		return cr.list();
	}


	


	@Override
	public DepartmentDTO3 getDangInfo(String usreId) {
	    StringBuilder listBuilder=new StringBuilder("SELECT b.chinese_name as userName,a.depart_name as deptName,a.parent_id,d.depart_name as parentDept ");
	    listBuilder.append("FROM c_department a ");
	    listBuilder.append("inner JOIN c_job_depart_relation c ON a.id = c.department_id ");
	    listBuilder.append("inner JOIN c_user b on b.id = c.user_id ");
	    listBuilder.append("inner join c_department d on a.parent_id = d.id ");
	    listBuilder.append("where a.org_type_id = 3 ");
	    listBuilder.append("and c.user_id=:userId");	    
	    
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("userName", StringType.INSTANCE)
				.addScalar("deptName", StringType.INSTANCE)	
				.addScalar("parentDept", StringType.INSTANCE)				
				.setResultTransformer(Transformers.aliasToBean(DepartmentDTO3.class)).setParameter("userId", usreId);
		return (DepartmentDTO3) queryList.uniqueResult();
	}




	/**
	 * 获取自身及后代部门的ID
	 */
	@Override
	public List<String> getSelfAndOffspringId(String[] ids,List<String> allIds) {
		for(String id:ids){
			Department dt=this.getById(id);
			allIds.add(dt.getId());
			if(null!=dt.getChildren()){
				for(Department dt2:dt.getChildren()){
					this.getSelfAndOffspringId(new String[]{dt2.getId()},allIds);
				}
			}
		}
		return allIds;
	}
	

}
