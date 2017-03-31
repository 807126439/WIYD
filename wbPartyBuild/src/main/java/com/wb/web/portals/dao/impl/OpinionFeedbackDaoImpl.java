package com.wb.web.portals.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;
import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.IOpinionFeedbackDao;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.opinionfeedback.OpinionCountDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackQueryDto;
import com.wb.web.portals.entity.OpinionFeedback;
@Repository("OpinionFeedbackDao")
public class OpinionFeedbackDaoImpl extends BaseDaoImpl<Long, OpinionFeedback> implements IOpinionFeedbackDao {

	/***
	 * 按时间进行意见反馈的分页查询
	 */
	@SuppressWarnings("unchecked")
	public Page<OpinionFeedbackDto> searchOpinionFeedbackByPage(OpinionFeedbackQueryDto queryDTO) {
		//查询总数hql
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num FROM c_opinion_feedback WHERE 1=1 ");
		//查询数据hql
		StringBuilder listBuilder = new StringBuilder("SELECT id as id,last_operator_time as lastOperatorTime,content,")
		  .append("str_to_date(feedback_time,'%Y-%m-%d') as feedbackTime, feedback_user as feedbackUser,type_id as typeid,chinese_name as chineseName,department,opinion_num as opinionNum,post,theme,forecast FROM c_opinion_feedback  WHERE 1=1 ");
		//拼接条件
		if (queryDTO.getEndTime()==null) {
			if (queryDTO.getBeginTime()!=null) {
				this.bulidQueryStrItem(countBuilder, listBuilder, " AND str_to_date(feedback_time,'%Y-%m-%d')=:BeginTime");
			}
		}
		
		if (queryDTO.getBeginTime()==null) {
			if (queryDTO.getEndTime()!=null) { 
				this.bulidQueryStrItem(countBuilder, listBuilder, " AND str_to_date(feedback_time,'%Y-%m-%d')=:Endtime");
			}
		}
		
		if(queryDTO.getBeginTime()!=null && queryDTO.getEndTime()!=null){
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND str_to_date(feedback_time,'%Y-%m-%d') between :BeginTime and :Endtime");
		}
		if (queryDTO.getTypeid()==1 || queryDTO.getTypeid()==2) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND type_id=:typeid");
		}
		//返回查询的总数
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		//返回查询的集合
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("lastOperatorTime",DateType.INSTANCE)
				.addScalar("content",StringType.INSTANCE).addScalar("feedbackTime",DateType.INSTANCE)
				.addScalar("typeId",ShortType.INSTANCE).addScalar("theme",StringType.INSTANCE)
				.addScalar("feedbackUser",StringType.INSTANCE).addScalar("chineseName",StringType.INSTANCE)
				.addScalar("department",StringType.INSTANCE).addScalar("opinionNum",StringType.INSTANCE)
				.addScalar("post",StringType.INSTANCE).addScalar("forecast",StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(OpinionFeedbackDto.class));
		if (queryDTO.getEndTime()==null) {
			if (queryDTO.getBeginTime()!=null) {
				this.setQueryParamsVal(queryCount, queryList, "BeginTime", queryDTO.getBeginTime());
			}
		}
		
		if (queryDTO.getBeginTime()==null) {
			if (queryDTO.getEndTime()!=null) {
				this.setQueryParamsVal(queryCount, queryList, "Endtime", queryDTO.getEndTime());
			}
		}
		
		if(queryDTO.getBeginTime()!=null&&queryDTO.getEndTime()!=null){
			this.setQueryParamsVal(queryCount, queryList, "BeginTime", queryDTO.getBeginTime());
			this.setQueryParamsVal(queryCount, queryList, "Endtime", queryDTO.getEndTime());
		}
		if (queryDTO.getTypeid()==1 || queryDTO.getTypeid()==2) {
			this.setQueryParamsVal(queryCount, queryList, "typeid", queryDTO.getTypeid());
		}
		//获取初始拿到的数据0-10
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		return new Page<OpinionFeedbackDto>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}
	/***
	 * 按编号查询反馈信息
	 * @return
	 */
	@Override
	public OpinionFeedbackDto findById(Long id ) {
		//查询数据hql
		StringBuilder listBuilder = new StringBuilder("SELECT id as id,last_operator_time as lastOperatorTime,content,")
			.append("feedback_time as feedbackTime, feedback_user as feedbackUser,type_id as typeid,chinese_name as chineseName,department,opinion_num as opinionNum,post,theme,forecast FROM c_opinion_feedback where  id=:id");
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("lastOperatorTime",DateType.INSTANCE)
				.addScalar("content",StringType.INSTANCE).addScalar("feedbackTime",DateType.INSTANCE)
				.addScalar("typeId",ShortType.INSTANCE).addScalar("theme",StringType.INSTANCE)
				.addScalar("feedbackUser",StringType.INSTANCE).addScalar("chineseName",StringType.INSTANCE)
				.addScalar("department",StringType.INSTANCE).addScalar("opinionNum",StringType.INSTANCE)
				.addScalar("post",StringType.INSTANCE).addScalar("forecast",StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(OpinionFeedbackDto.class)).setParameter("id", id);
				return (OpinionFeedbackDto) queryList.uniqueResult();
	}
	
	/**
	 * 统计
	 * 导出
	 */
	@Override
	public Page<OpinionCountDto> opinionCount(
			OpinionFeedbackQueryDto queryDTO) {
		//查询总数hql
				StringBuilder countBuilder = new StringBuilder("SELECT count(*) AS num FROM ( SELECT o.feedback_user, count(u.user_name) AS num FROM " +
						" c_user u LEFT JOIN c_opinion_feedback o ON u.user_name = o.feedback_user" +
						" LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id where 1=1 and is_del=0 and is_ignore_stat=0 ");
				//查询数据hql
				StringBuilder listBuilder = new StringBuilder("select DISTINCT u.user_name as feedbackUser ,count(o.feedback_user) as num,o.feedback_time as feedbackTime from" +
						" c_user u LEFT JOIN c_opinion_feedback o on  o.feedback_user = u.user_name " +
						"LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id where 1=1 and is_del=0 and is_ignore_stat=0 ");
				//拼接条件
				if(queryDTO.getUserName()!=null && !StringUtils.isBlank(queryDTO.getUserName())){
					this.bulidQueryStrItem(countBuilder, listBuilder, "AND u.user_name LIKE :name ");		
				}
				if (queryDTO.getEndTime()==null) {
					if (queryDTO.getBeginTime()!=null) {
						this.bulidQueryStrItem(countBuilder, listBuilder, " AND UNIX_TIMESTAMP(o.feedback_time) >= UNIX_TIMESTAMP(:l_begin)");
					}
				}
				
				if (queryDTO.getBeginTime()==null) {
					if (queryDTO.getEndTime()!=null) { 
						this.bulidQueryStrItem(countBuilder, listBuilder, " AND UNIX_TIMESTAMP(o.feedback_time) <= UNIX_TIMESTAMP(:l_end)");
					}
				}
				
				if (queryDTO.getBeginTime()!=null && queryDTO.getEndTime()!=null) {
					this.bulidQueryStrItem(countBuilder, listBuilder, " AND UNIX_TIMESTAMP(o.feedback_time) >= UNIX_TIMESTAMP(:l_begin)");
					this.bulidQueryStrItem(countBuilder, listBuilder, " AND UNIX_TIMESTAMP(o.feedback_time) <= UNIX_TIMESTAMP(:l_end)");
					
				}
				
				if (queryDTO.getTypeid()==1 || queryDTO.getTypeid()==2) {
					this.bulidQueryStrItem(countBuilder, listBuilder, " AND (o.type_id=:typeid  OR  ISNULL(o.type_id))");
				}
				
				//部门查询条件相关
				//获取ID下自身以及后代部门ID
				if(queryDTO.getDepartmentIds()!=null && !StringUtils.isBlank(queryDTO.getDepartmentIds())){
					String[] ids=queryDTO.getDepartmentIds().split(",");
//					List<String> allIds=new LinkedList<String>();
//					List<String> departmentIdList=this.departmentDao.getSelfAndOffspringId(ids,allIds);
					StringBuilder departmentIds=new StringBuilder();
					for(String o:ids){
						departmentIds.append("'"+o+"',");
					}
					departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
					this.bulidQueryStrItem(countBuilder, listBuilder, "AND jdr.department_id IN ("+departmentIds+") ");
				}
				
				countBuilder.append(" GROUP BY u.user_name) a ");
				listBuilder.append(" GROUP BY u.user_name,jdr.department_id ORDER BY num DESC");
				//返回查询的总数
				Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
				//返回查询的集合
				Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("feedbackUser",StringType.INSTANCE)
						.addScalar("num",IntegerType.INSTANCE)
						.addScalar("feedbackTime",TimestampType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(OpinionCountDto.class));
				
				if(queryDTO.getUserName()!=null && !StringUtils.isBlank(queryDTO.getUserName())){
					this.setQueryParamsVal(queryCount, queryList, "name", "%"+queryDTO.getUserName()+"%");		
				}
				if (queryDTO.getEndTime()==null) {
					if (queryDTO.getBeginTime()!=null) {
						this.setQueryParamsVal(queryCount, queryList, "l_begin", queryDTO.getBeginTime());
					}
				}
				
				if (queryDTO.getBeginTime()==null) {
					if (queryDTO.getEndTime()!=null) {
						this.setQueryParamsVal(queryCount, queryList, "l_end", queryDTO.getEndTime());
					}
				}
				if (queryDTO.getBeginTime()!=null && queryDTO.getEndTime()!=null) {
					this.setQueryParamsVal(queryCount, queryList, "l_begin", queryDTO.getBeginTime());
					this.setQueryParamsVal(queryCount, queryList, "l_end", queryDTO.getEndTime());
				}
				
				
				if (queryDTO.getTypeid()==1 || queryDTO.getTypeid()==2) {
					this.setQueryParamsVal(queryCount, queryList, "typeid", queryDTO.getTypeid());
				}
				
				Long recTotal = (Long) queryCount.uniqueResult();
				//获取初始拿到的数据0-10
				queryList.setFirstResult(queryDTO.getStartQuery());
				if(null!=queryDTO.getPageSize()){
					queryList.setMaxResults(queryDTO.getPageSize());
				}
				
				List<OpinionCountDto> list = queryList.list();
				long sort=1;
				for(OpinionCountDto o:list){
					o.setSort(sort);
					sort++;
				}
				
				return new Page<OpinionCountDto>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list,recTotal );
	}
	

	

	
}
