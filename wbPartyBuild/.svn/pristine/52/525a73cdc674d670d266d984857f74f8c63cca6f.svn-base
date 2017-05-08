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
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.entity.BaseDict;

import freemarker.template.utility.NullArgumentException;

@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<Long, BaseDict> implements IBaseDictDao{

	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<BaseDict> searchBaseDictByPage(BaseDictQueryDTO queryDTO){
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		if(queryDTO.getDictType()!=null && !StringUtils.isBlank(queryDTO.getDictType())){
			cr.add(Restrictions.eq("dictType", queryDTO.getDictType()));
		}
		
		if(queryDTO.getDictItem()!=null && !StringUtils.isBlank(queryDTO.getDictItem())){
			cr.add(Restrictions.eq("dictItem", queryDTO.getDictItem()));
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
		List<BaseDict> list = cr.list();
		
		
		return new Page<BaseDict>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 根据条件查询记录
	 * @param dictType 字典类型
	 * @param dictItem 字典条目
	 * @param dictFlag 字典状态
	 */
	@SuppressWarnings("unchecked")
	public List<BaseDict> searcheDcitListByCondition(BaseDictQueryDTO queryDTO) {		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		if(queryDTO.getDictType()!=null){
			cr.add(Restrictions.eq("dictType", queryDTO.getDictType()));
		}
		if(queryDTO.getDictItem()!=null){
			cr.add(Restrictions.eq("dictItem", queryDTO.getDictItem()));
		}	
		if(queryDTO.getFlag()!=null){
			cr.add(Restrictions.eq("dictFlag", queryDTO.getFlag()));
		}
	
		return cr.list();
	}
	
	
	
	
	/**
	 * 根据条件查询记录
	 * @param dictType 字典类型
	 * @param dictItem 字典条目
	 * @param dictFlag 字典状态
	 */
	@SuppressWarnings("unchecked")
	public List<BaseDict> searcheDcitListByCondition(String dictType,String dictItem,Integer dictFlag) {		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		if(dictType!=null){
			cr.add(Restrictions.eq("dictType", dictType));
		}
		if(dictItem!=null){
			cr.add(Restrictions.eq("dictItem", dictItem));
		}	
		if(dictFlag!=null){
			cr.add(Restrictions.eq("dictFlag", dictFlag));
		}
	
		return cr.list();
	}
	
	
	/**
	 * 根据条件查询唯一的记录
	 * @param dictType 字典类型
	 * @param dictItem 字典条目
	 * @param dictFlag 字典状态
	 */
	public BaseDict searchOneDictByCondition(String dictType,String dictItem,Integer dictFlag) {
		if(dictType == null || dictType.isEmpty() || dictItem == null || dictItem.isEmpty() ||
				 dictFlag == null ){
			throw new NullArgumentException("params has null");
		}
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("dictType", dictType));
		cr.add(Restrictions.eq("dictItem", dictItem));
		cr.add(Restrictions.eq("dictFlag", dictFlag));
		
		return (BaseDict) cr.uniqueResult();
	}
	
	
	/**
	 * 获取字典值
	 * @param dictType	字典类型
	 * @param dictItem	字典条目
	 * @param dictFlag  是否可用
	 * @return
	 */
	public String getDictVal(String dictType,String dictItem,Integer dictFlag) {
		if(dictType == null || dictType.isEmpty() || dictItem == null || dictItem.isEmpty() ||
				 dictFlag == null ){
			throw new NullArgumentException("params has null");
		}
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("dictType", dictType));
		cr.add(Restrictions.eq("dictItem", dictItem));
		cr.add(Restrictions.eq("dictFlag", dictFlag));
		 
		cr.setProjection(Projections.property("dictValue"));
		
		return (String) cr.uniqueResult();
	}









	@Override
	public BaseDict searchOneDictByCondition2(String dictType,
			String dictValue, Integer dictFlag) {
		
		if(dictType == null || dictType.isEmpty() || dictValue == null || dictValue.isEmpty() ||
				 dictFlag == null ){
			throw new NullArgumentException("params has null");
		}
		
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.eq("dictType", dictType));
		cr.add(Restrictions.eq("dictValue", dictValue));
		cr.add(Restrictions.eq("dictFlag", dictFlag));
		 
		//cr.setProjection(Projections.property("dictItem"));
		
		return (BaseDict) cr.uniqueResult();
	}
	
	
}
