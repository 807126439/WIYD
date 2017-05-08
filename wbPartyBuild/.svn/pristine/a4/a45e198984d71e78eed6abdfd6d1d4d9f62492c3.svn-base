package com.wb.web.portals.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.IFeelingRecordDao;
import com.wb.web.portals.dto.comment.CountCommentDTO;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.dto.opinionfeedback.OpinionCountDto;
import com.wb.web.portals.entity.FeelingRecord;
@Repository("feelingRecordDao")
public class FeelingRecordDaoImpl  extends BaseDaoImpl<Long, FeelingRecord> implements IFeelingRecordDao {

	/**
	 * 分页查询心得体会
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<FeelingRecordDTO> searchFeelingRecordByPage(FeelingRecordQueryDTO queryDTO) {
		StringBuilder countBuilder=new StringBuilder("select count(*) as num from c_feeling_record cfr where 1=1 ");
		StringBuilder listBuilder =new StringBuilder("select cfr.id as id,cfr.title as title,cfr.content as content,cfr.create_time as createTime,u.chinese_name as ownerName " +
					"from c_feeling_record cfr LEFT JOIN c_user u ON u.id=cfr.owner_id where 1=1");
		if (!StringUtils.isBlank(queryDTO.getOwnerId())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND cfr.owner_id=:ownerId");
		}
		if (!StringUtils.isBlank(queryDTO.getTitle())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND cfr.title like:title");
		}
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id",StringType.INSTANCE)
				.addScalar("title", StringType.INSTANCE)
				.addScalar("content", StringType.INSTANCE)
				.addScalar("createTime", DateType.INSTANCE)
				.addScalar("ownerName", StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(FeelingRecordDTO.class));
		if (!StringUtils.isBlank(queryDTO.getOwnerId())) {
			this.setQueryParamsVal(queryCount, queryList, "ownerId", queryDTO.getOwnerId());
		}
		if (!StringUtils.isBlank(queryDTO.getTitle())) {
			this.setQueryParamsVal(queryCount, queryList, "title", "%"+ queryDTO.getTitle()+"%");
		}
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		return new Page<FeelingRecordDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}

	
	/**
	 * 统计
	 * 导出
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<FeelingRecordDTO> getByCount(FeelingRecordQueryDTO queryDTO) {
		
		StringBuilder countBuilder=new StringBuilder("select count(*) as num from (select c.owner_id,count(c.owner_id) as num from " +
													"c_user u left join c_feeling_record c on u.id=c.owner_id " +
													"LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id where 1=1 and is_del=0 and is_ignore_stat=0 ");
		
		StringBuilder listBuilder =new StringBuilder("select DISTINCT u.user_name as name,count(c.owner_id) as number,max(c.create_time) as createTime from " +
													"c_feeling_record c right join c_user u on c.owner_id = u.id " +
													"LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id where 1=1 and is_del=0 and is_ignore_stat=0 ");
		
		if(queryDTO.getUserName()!=null && !StringUtils.isBlank(queryDTO.getUserName())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND u.user_name LIKE :name ");		
		}
		if(queryDTO.getStartTime()!=null && !StringUtils.isBlank(queryDTO.getStartTime())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND UNIX_TIMESTAMP(c.create_time) >= UNIX_TIMESTAMP(:l_begin) ");	
		}
		
		if(queryDTO.getEndTime()!=null && !StringUtils.isBlank(queryDTO.getEndTime())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND UNIX_TIMESTAMP(c.create_time) <= UNIX_TIMESTAMP(:l_end)");	
		}
		
		//部门查询条件相关
				//获取ID下自身以及后代部门ID
				if(queryDTO.getDepartmentIds()!=null && !StringUtils.isBlank(queryDTO.getDepartmentIds())){
					String[] ids=queryDTO.getDepartmentIds().split(",");
					StringBuilder departmentIds=new StringBuilder();
					for(String o:ids){
						departmentIds.append("'"+o+"',");
					}
					departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
					this.bulidQueryStrItem(countBuilder, listBuilder, "AND jdr.department_id IN ("+departmentIds+") ");
				
				}
				
		
		countBuilder.append(" GROUP BY u.id)a");
		listBuilder.append(" GROUP BY u.id,jdr.department_id  ORDER BY number DESC");
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("name",StringType.INSTANCE)
				.addScalar("createTime", TimestampType.INSTANCE)
				.addScalar("number", IntegerType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(FeelingRecordDTO.class));
		if(queryDTO.getUserName()!=null && !StringUtils.isBlank(queryDTO.getUserName())){
			this.setQueryParamsVal(queryCount, queryList, "name", "%"+queryDTO.getUserName()+"%");		
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
		
		List<FeelingRecordDTO> list = queryList.list();
		long sort=1;
		for(FeelingRecordDTO o:list){
			o.setSort(sort);
			sort++;
		}

		return new Page<FeelingRecordDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list,recTotal);
	}


}
