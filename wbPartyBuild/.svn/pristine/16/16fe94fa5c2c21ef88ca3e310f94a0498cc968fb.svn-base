package com.wb.web.portals.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.exception.MyException;
import com.wb.web.portals.dao.IColumnMuDao;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.ColumnMuDTO;
import com.wb.web.portals.dto.column.ColumnNavDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.entity.ColumnMu;

@Repository("columnMuDao")
public class ColumnMuDaoImpl extends BaseDaoImpl<Long, ColumnMu> implements IColumnMuDao{

	
	private String ColumnMuDTO_SQL = "SELECT id as id,title as title,alias as alias,link_url as linkUrl,type_id as typeId,sort_num as sortNum,"+
									 "level as level,is_index_nav as isIndexNav,is_index as isIndex,index_num as indexNum,is_ignore_pre as isIgnorePre,"+
									 "max_content_size as maxContentSize,show_type as showType,parent_id as parentId,allow_user_id as allowUserId FROM cy_columnmu ";
	

	/**
	 * 根据条件查询对象到DTO里
	 * @param isQueryPar
	 * @param exceptId  排除id 为null则不设为查询条件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ColumnMuDTO> findColumnMuDTOByLevel(Short level,Long exceptId){
		StringBuilder sb = new StringBuilder(ColumnMuDTO_SQL);			
		sb.append("WHERE level=:le ");
	
		if(exceptId!=null){
			sb.append("AND id<>:id ");
		}
						
		sb.append("ORDER BY sort_num ASC ");
		
		Query query = initQueryColumnMuDTO(sb.toString());
		query.setParameter("le", level);
		if(exceptId!=null){
			query.setParameter("id", exceptId);
		}
		
		return query.list();
	}
	
	
	
	private Query initQueryColumnMuDTO(String SQL){
			
		 return this.getSession().createSQLQuery(SQL)
				.addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
				.addScalar("alias", StringType.INSTANCE).addScalar("linkUrl", StringType.INSTANCE)
				.addScalar("typeId", ShortType.INSTANCE).addScalar("sortNum", IntegerType.INSTANCE)
				.addScalar("level", ShortType.INSTANCE).addScalar("isIndexNav", BooleanType.INSTANCE)
				.addScalar("isIndex", BooleanType.INSTANCE).addScalar("indexNum", IntegerType.INSTANCE)
				.addScalar("showType", ShortType.INSTANCE).addScalar("isIgnorePre", BooleanType.INSTANCE)
				.addScalar("maxContentSize", ShortType.INSTANCE).addScalar("parentId", LongType.INSTANCE)
				.addScalar("allowUserId", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ColumnMuDTO.class));
		 		 
	}

	
	
	public List<ColumnHead> searchIndexHead(Long parId){
		StringBuilder sb = new StringBuilder("SELECT id as colId,title,index_num as indexNum,link_url as linkUrl FROM cy_columnmu ")
						  .append("WHERE 1=1 ");
		   
		if(parId!=null){
		   sb.append("AND parent_id=:par ");
	    
		}else{//查询首页导航条
	    	sb.append("AND is_index_nav=true  ");
	    }
		
		sb.append("ORDER BY sort_num ASC ");
				
		Query query = this.getSession().createSQLQuery(sb.toString())
						 .addScalar("colId", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
						 .addScalar("indexNum", IntegerType.INSTANCE).addScalar("linkUrl", StringType.INSTANCE)
						 .setResultTransformer(Transformers.aliasToBean(ColumnHead.class));
		if(parId!=null){
			query.setParameter("par", parId);
		} 
		
		return query.list();
	}
	
	
	public List<ColumnHead> searchColumnHeadByParIds(List<Long> ids){
		StringBuilder sb = new StringBuilder("SELECT id as colId,title,index_num as indexNum,link_url as linkUrl,parent_id as parId  ")
						  .append("FROM cy_columnmu WHERE parent_id IN(:ids) ")
						  .append("ORDER BY sort_num ASC ");
		   	

		Query query = this.getSession().createSQLQuery(sb.toString())
						 .addScalar("colId", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
						 .addScalar("indexNum", IntegerType.INSTANCE).addScalar("linkUrl", StringType.INSTANCE)
						 .addScalar("parId", LongType.INSTANCE)
						 .setResultTransformer(Transformers.aliasToBean(ColumnHead.class))
						 .setParameterList("ids", ids);

		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ColumnHead> searchIndexColum(){
		StringBuilder sb = new StringBuilder("SELECT id as colId,title,index_num as indexNum,show_type as showType, ")
						  .append("type_id as typeId FROM cy_columnmu WHERE is_index=1 ORDER BY index_num asc ");		   
				
		Query query = this.getSession().createSQLQuery(sb.toString())
						 .addScalar("colId", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
						 .addScalar("indexNum", IntegerType.INSTANCE).addScalar("showType", ShortType.INSTANCE)
						 .addScalar("typeId", ShortType.INSTANCE)
						 .setResultTransformer(Transformers.aliasToBean(ColumnHead.class));
	
		return query.list();
	}
	
	
	
	
	
	
	
	
	/**
	 * 根据id查询对象到DTO里
	 * @param id
	 * @return
	 */
	public ColumnMuDTO getColumnMuDTOById(Long id){
				
		return (ColumnMuDTO) initQueryColumnMuDTO(ColumnMuDTO_SQL + "WHERE id=? ").setParameter(0, id).uniqueResult();
		
	}
	
	
	/**
	 * 获取指定数量的最前的对象
	 * @param parId
	 * @param maxSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ColumnMu> getTopEntityWithLimit(Long parId,int maxSize){
		return this.getCriteria()
					.add(Restrictions.eq("parent.id", parId))
					.addOrder(Order.asc("sortNum"))
					.setMaxResults(maxSize).list();

		
	}
	
	
	
	/**
	 * 根据条件更新IsIndex属性
	 * @param val		更改值
	 * @param parId		父栏目id
	 * @param isIndex	查询条件值
	 */
	public void updateIsIndexByCondition(Boolean val,Long parId,Boolean isIndex){
		
		this.executeSql("UPDATE cy_columnmu SET is_index=? WHERE parent_id=? AND is_index=?",
						val,parId,isIndex);
	}
	
	
	
	/**
	 * 检查是否有子栏目是置顶的
	 */
	public boolean checkIsHasChildIndex(Long parId){
		Long num = 	(Long) this.getCriteria().add(Restrictions.eq("parent.id", parId))
				.add(Restrictions.eq("isIndex", true))
				.setProjection(Projections.rowCount()).uniqueResult();
	
		if(num>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 根据层级检查是否有子节点
	 * @param level
	 * @param parId
	 * @return
	 */
	public boolean checkIsHasChildByLevel(Integer level,Long parId){
		StringBuilder sb = new StringBuilder();
		if(level == 2){
			sb.append("SELECT COUNT(*) as count FROM cy_columnmu c INNER JOIN cy_columnmu p1 ON p1.id=c.parent_id ");
			sb.append("WHERE p1.id=:parId ");
			
		}else if(level == 3){
			sb.append("SELECT COUNT(*) as count FROM cy_columnmu c INNER JOIN cy_columnmu p1 ON p1.id=c.parent_id ");
			sb.append("INNER JOIN cy_columnmu p2 ON p1.parent_id=p2.id ");
			sb.append("WHERE p2.id=:parId ");
			
		}else{
			throw new MyException("无法识别参数！");
		}
		
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addScalar("count", LongType.INSTANCE)
					  .setParameter("parId", parId);
		
		long result = (long) query.uniqueResult();
		if(result>0){
			return true;
			
		}else{
			return false;
		}
	
		
	}
		
	/**
	 * 根据条件更新栏目的层级
	 * @param level		更新值
	 * @param parId	       上级栏目id
	 * @param isCross	是否跨上一级栏目查询
	 */
	public void updateColumnLevelByCondition(short level,Long parId,boolean isCross){
		StringBuilder sb = new StringBuilder();
		
		if(!isCross){
			sb.append("UPDATE cy_columnmu SET level=?  WHERE parent_id=?");
			this.executeSql(sb.toString(), level,parId);
			
		}else{
			
			sb.append("UPDATE cy_columnmu c INNER JOIN cy_columnmu p ON c.parent_id=p.id SET c.level=?  WHERE p.parent_id=?  ");
					
			this.executeSql(sb.toString(), level,parId);

			
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getTabColumnsIds(Long parId,Integer size){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id FROM cy_columnmu  WHERE parent_id=? AND is_ignore_pre =? ");
		sb.append("ORDER BY sort_num ASC LIMIT 0 ,? ");
		
		Query query = this.getSession().createSQLQuery(sb.toString()).addScalar("id", LongType.INSTANCE)
					  .setParameter(0, parId).setParameter(1, false).setParameter(2, size);
		
		
		return query.list();
	}

	/**
	 * 根据ID查找所有同级的栏目
	 */

	@Override
	public List<ColumnMu> getAllBrotherColumnMu(Long id) {
		ColumnMu dt=this.getById(id);
		if(null!=dt.getParent()){
			ColumnMu fdt=dt.getParent();
			Set<ColumnMu> children=fdt.getChildren();
			List<ColumnMu> dtList=new ArrayList<ColumnMu>(children);
			Collections.sort(dtList,new Comparator<ColumnMu>(){
				public int compare(ColumnMu o1, ColumnMu o2) {
					if(null==o1.getSortNum()){
						return 1;
					}else if(null==o2.getSortNum()){
						return -1;
					}else{
						return o1.getSortNum()-o2.getSortNum();
					}
				}
			});
			return dtList;
		}
		List<ColumnMu> dtList=this.getCriteria().add(Restrictions.eq("parent.id", null)).list();
		return dtList;
	}



	@Override
	public Page<ColumnHead> searchColumnHeadByCondition(ContentQueryDTO dq) {
		StringBuilder countBuilder = new StringBuilder("SELECT count(*) as num FROM cy_columnmu WHERE 1=1 ");
		StringBuilder listBuilder = new StringBuilder("SELECT id as colId,title,link_url as linkUrl FROM cy_columnmu ")
		  .append("WHERE 1=1 ");

		
		if(!StringUtils.isBlank(dq.getTitle())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND title LIKE:title ");
		}
		
		
		
		this.bulidQueryStrItem(countBuilder, listBuilder, "ORDER BY id DESC ");
		
	    Query queryCount =  this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
	    
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
									.addScalar("colId", LongType.INSTANCE)
									.addScalar("title", StringType.INSTANCE)
									.addScalar("linkUrl", StringType.INSTANCE)
									.setResultTransformer(Transformers.aliasToBean(ColumnHead.class))									
									.setFirstResult( (dq.getCurrentPage()-1) * dq.getPageSize() )
									.setMaxResults(dq.getPageSize());
		
		if(!StringUtils.isBlank(dq.getTitle())){
			this.setQueryParamsVal(queryCount, queryList, "title", "%"+ dq.getTitle() +"%");
		}
		
		
		
		return new Page<ColumnHead>(dq.getCurrentPage(), dq.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}
	
	
	
	/**
	 * 查询所有的栏目用于导航
	 * @return
	 */
	public List<ColumnNavDTO> searchColumnNavDTO(){
		StringBuilder sb = new StringBuilder("SELECT id,title,link_url as linkUrl,parent_id as parentId  ")
						  			 .append("FROM cy_columnmu ORDER BY sort_num ASC ");
					

		Query query = this.getSession().createSQLQuery(sb.toString())
						 .addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE)
						 .addScalar("linkUrl", StringType.INSTANCE).addScalar("parentId", LongType.INSTANCE)
						 .setResultTransformer(Transformers.aliasToBean(ColumnNavDTO.class));

		
		return query.list();
	}
	
	
	
}
