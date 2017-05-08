package com.wb.web.portals.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.IContentStatDao;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.contentStat.ContentStatQueryDTO;
import com.wb.web.portals.entity.ContentStat;
import com.wb.web.system.dao.IDepartmentDao;

@Repository("contentStatDao")
public class ContentStatDaoImpl extends BaseDaoImpl<Long, ContentStat> implements IContentStatDao{
	
	@Resource
	private IDepartmentDao departmentDao;

	@Override
	public ContentStat getByUserIdAndContentId(String userId, Long contentId) {
		
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());
		
			cr.add(Restrictions.and(Restrictions.eq("userId",userId),Restrictions.eq("contentId",contentId)));
		
		return null!=cr.uniqueResult()?(ContentStat)cr.uniqueResult():null;
	}

	@Override
	public Page<ContentStatDTO> searchContentStatByPage(ContentStatQueryDTO queryDTO) {
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) AS num FROM (SELECT cs.user_id,count(cs.user_id) AS  num " +
														"FROM c_user u " +
														"LEFT JOIN cy_content_stat cs ON u.id=cs.user_id  " +
														"LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id where 1=1 AND u.is_del=0 AND u.is_ignore_stat=0 ");
		
		StringBuilder listBuilder  = new StringBuilder();
		listBuilder.append("SELECT DISTINCT u.user_name AS userName ,cs.user_id AS userId,count(cs.user_id) AS  articleNum,MIN(cs.read_date) AS readDate " +
				"FROM c_user u " +
				"LEFT JOIN cy_content_stat cs ON u.id=cs.user_id " +
				"LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id where 1=1 AND u.is_del=0 AND u.is_ignore_stat=0  ");
		
		if(queryDTO.getUsername()!=null && !StringUtils.isBlank(queryDTO.getUsername())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND u.user_name LIKE :name ");		
		}
		
		if(queryDTO.getStartTime()!=null && !StringUtils.isBlank(queryDTO.getStartTime())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND UNIX_TIMESTAMP(cs.read_date) >= UNIX_TIMESTAMP(:l_begin) ");	
		}
		
		if(queryDTO.getEndTime()!=null && !StringUtils.isBlank(queryDTO.getEndTime())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND UNIX_TIMESTAMP(cs.read_date) <= UNIX_TIMESTAMP(:l_end) ");	
		}
		
		//部门查询条件相关
		//获取ID下自身以及后代部门ID
		if(queryDTO.getDepartmentIds()!=null && !StringUtils.isBlank(queryDTO.getDepartmentIds())){
			String[] ids=queryDTO.getDepartmentIds().split(",");
//			List<String> allIds=new LinkedList<String>();
//			List<String> departmentIdList=this.departmentDao.getSelfAndOffspringId(ids,allIds);
			StringBuilder departmentIds=new StringBuilder();
			for(String o:ids){
				departmentIds.append("'"+o+"',");
			}
			departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND jdr.department_id IN ("+departmentIds+") ");
		}
		
		
		countBuilder.append("GROUP BY u.id)a ");
		listBuilder.append("GROUP BY u.id,jdr.department_id ORDER BY articleNum DESC");
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("userName", StringType.INSTANCE)
						.addScalar("userId", StringType.INSTANCE).addScalar("articleNum", IntegerType.INSTANCE)
						.addScalar("readDate", TimestampType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(ContentStatDTO.class));	
		
		if(queryDTO.getUsername()!=null && !StringUtils.isBlank(queryDTO.getUsername())){
			this.setQueryParamsVal(queryCount, queryList, "name", "%"+queryDTO.getUsername()+"%");		
		}
		
		if(queryDTO.getStartTime()!=null && !StringUtils.isBlank(queryDTO.getStartTime())){
			this.setQueryParamsVal(queryCount, queryList, "l_begin",queryDTO.getStartTime());	
		}
		
		if(queryDTO.getEndTime()!=null && !StringUtils.isBlank(queryDTO.getEndTime())){
			this.setQueryParamsVal(queryCount, queryList, "l_end",queryDTO.getEndTime());	
		}
		
		Long recTotal = (Long) queryCount.uniqueResult();
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		if(null!=queryDTO.getPageSize()){
			queryList.setMaxResults(queryDTO.getPageSize());
		}
		
		
		List<ContentStatDTO> list = queryList.list();
		int sort=1;
		for(ContentStatDTO o:list){
			o.setSort(sort);
			sort++;
		}
		
		return new Page<ContentStatDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
		
		
	}

}
