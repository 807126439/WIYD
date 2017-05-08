package com.wb.web.portals.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IBanChunkDao;
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.banChunk.BanChunkQueryDTO;
import com.wb.web.portals.dto.banChunk.BanChunkTreeDTO;
import com.wb.web.portals.entity.BanChunk;
import com.wb.web.portals.entity.ThemeActivity;

@Repository("banChunkDao")
public class BanChunkDaoImpl extends BaseDaoImpl<Long,BanChunk> implements IBanChunkDao{

	  public Page<BanChunk> searchBanChunkByPage(BanChunkQueryDTO queryDto) {
	        Criteria cr = this.getSession().createCriteria(getInheritClass());
	        if(queryDto.getModel()==1){
	        	cr.add(Restrictions.eq("isLeaf", false));
	        }
	        if(queryDto.getLevel()==0){
	        	cr.add(Restrictions.isNull("activity.id"));
	        }else if(queryDto.getLevel()==1){
	        	cr.add(Restrictions.and(Restrictions.eq("activity.id", queryDto.getTreeParId()), Restrictions.isNull("parent")));
	        }else{
//	        	列表中是否也显示父节点自身
//	        	cr.add( Restrictions.or(Restrictions.eq("parent.id", queryDto.getTreeParId()),Restrictions.eq("id", queryDto.getTreeParId())));
	        	cr.add(Restrictions.eq("parent.id", queryDto.getTreeParId()));
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
			
			List<BanChunk> list = cr.list();		
			
			return new Page<BanChunk>(queryDto.getCurrentPage(),queryDto.getPageSize(),list,rectoal);
		
		}
	
	
	
	
	
	public List<BanChunk> searchTreeByCondition(BanChunkQueryDTO queryDTO){
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		//板块编辑模式不显示叶子节点
		if(queryDTO.getModel()==1){
			cr.add(Restrictions.eq("isLeaf", false));
		}
		
		if(queryDTO.getLevel() == 0){
			cr.add(Restrictions.and(Restrictions.eq("activity.id",queryDTO.getTreeParId()),Restrictions.isNull("parent.id")));
		}else{
			cr.add(Restrictions.eq("parent.id", queryDTO.getTreeParId()));
		}
		
	
		cr.addOrder(Order.asc("sortNum"));
		
		return cr.list();
	}

	/**
	 * 依据ID判断是否为叶节点
	 */
	public boolean isLeaf(long id){
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
		cr.add(Restrictions.eq("parent.id", id));
		if(cr.list()==null){
			return true;
		}
		return false;
	}





	@Override
	public List<BanChunk> getSolicitationBanChunk(Long themeActivityId) {
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.add(Restrictions.eq("activity.id",themeActivityId));
		cr.add(Restrictions.eq("status",(short)1));
		cr.add(Restrictions.isNull("parent.id"));
		
		
		return cr.list();
	}





	@Override
	public List<BanChunk> getPublishedBanChunk(Long themeActivityId) {
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		cr.add(Restrictions.eq("activity.id",themeActivityId));
		cr.add(Restrictions.isNull("parent.id"));
		return cr.list();
	}





	@Override
	public void delBanChunkByActivityId(Long activityId) {
		Assert.notNull(activityId, "activityId can not be null");
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from c_ban_chunk where activity_id = ?");
		Query query = this.getSession().createSQLQuery(sql.toString()).setLong(0, activityId);
		query.executeUpdate();
		
	}








}
