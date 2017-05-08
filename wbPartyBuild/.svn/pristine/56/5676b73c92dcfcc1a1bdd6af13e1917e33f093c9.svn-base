package com.wb.web.study.dao.impl;


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
import com.wb.web.study.dao.IStudyTaskDao;
import com.wb.web.study.dto.studyTask.StudyTaskDTO;
import com.wb.web.study.dto.studyTask.StudyTaskQueryDTO;
import com.wb.web.study.entity.StudyTask;

@Repository("studyTaskDao")
public class StudyTaskDaoImpl extends BaseDaoImpl<Long, StudyTask> implements IStudyTaskDao {

	@Override
	public Long getStudyTaskByIdExists(Long id) {
		StringBuilder countBuilder=new StringBuilder("select count(*)as num from c_study_task_data where data_id=:id");
		return (Long) this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE).setParameter("id",id).uniqueResult();
	}
	/**
	 * 分页查询学习任务
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<StudyTaskDTO> searchStudyTaskPage(StudyTaskQueryDTO queryDTO) {
		StringBuilder countBuilder=new StringBuilder("select count(*)as num from c_study_task cst LEFT JOIN c_exam_paper cep ")
		.append("on cst.exam_paper_id=cep.id  where 1=1 ");
		StringBuilder listBuilder=new StringBuilder("select cst.id as stId,cst.start_time as startTime,cst.end_time as endTime,cst.task_num as taskNum,cst.task_name as ")
		.append(" taskName,cst.`status` as status ,cep.paper_name as paperName from c_study_task cst ")
		.append(" LEFT JOIN c_exam_paper cep on cst.exam_paper_id=cep.id where 1=1 ");
		
		if (!StringUtils.isBlank(queryDTO.getTaskNum())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND task_num=:taskNum ");
		}
		if (!StringUtils.isBlank(queryDTO.getTaskName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND task_name=:taskName ");
		}
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("stId", LongType.INSTANCE)
				.addScalar("startTime", DateType.INSTANCE)
				.addScalar("endTime", DateType.INSTANCE)
				.addScalar("taskNum", StringType.INSTANCE)
				.addScalar("taskName", StringType.INSTANCE)
				.addScalar("status", IntegerType.INSTANCE)
				.addScalar("paperName", StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyTaskDTO.class));
		if (!StringUtils.isBlank(queryDTO.getTaskNum())) {
			this.setQueryParamsVal(queryCount, queryList, "taskNum", queryDTO.getTaskNum());
		}
		if (!StringUtils.isBlank(queryDTO.getTaskName())) {
			this.setQueryParamsVal(queryCount, queryList, "taskName", queryDTO.getTaskName());
		}
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		return new Page<StudyTaskDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(), (Long) queryCount.uniqueResult());
	}
	
	
	/**
	 * 根据id查找一条信息
	 * @param stId
	 * @return
	 */
	@Override
	public StudyTaskDTO getStudyTaskById(Long stId) {
		
		StringBuilder listBuilder=new StringBuilder("select cst.exam_paper_id as paperid, cst.id as stId,cst.start_time as startTime,cst.end_time as endTime,cst.task_num as taskNum,cst.task_name as ")
		.append(" taskName,cst.task_memo as taskMemo,cst.`status` as status ,cep.paper_name as paperName from c_study_task cst ")
		.append(" LEFT JOIN c_exam_paper cep on cst.exam_paper_id=cep.id where cst.id=:stId");
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("stId", LongType.INSTANCE)
				.addScalar("taskMemo",StringType.INSTANCE)
				.addScalar("startTime", DateType.INSTANCE)
				.addScalar("endTime", DateType.INSTANCE)
				.addScalar("taskNum", StringType.INSTANCE)
				.addScalar("taskName", StringType.INSTANCE)
				.addScalar("status", IntegerType.INSTANCE)
				.addScalar("paperName", StringType.INSTANCE)
				.addScalar("paperid",LongType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyTaskDTO.class));
		return (StudyTaskDTO) queryList.setParameter("stId", stId).uniqueResult();
	}
	@Override
	public boolean checkIfFinished(Long studyTaskId,String userId) {
		StringBuilder listBuilder=new StringBuilder("SELECT c.task_id as stId,c.user_id as userId from c_task_finish_user c where c.task_id=:studyTaskId AND c.user_id=:userId");
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("stId", LongType.INSTANCE)
				.addScalar("userId", StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyTaskDTO.class));
		queryList.setParameter("studyTaskId", studyTaskId);
		queryList.setParameter("userId", userId);
		if(!(null==queryList.uniqueResult())){
			return true;
		}
		return false;
	}
	 	
	
	
	@Override
	public Page<StudyTaskDTO> getAppPage(StudyTaskQueryDTO queryDTO) {
		StringBuilder countBuilder = new StringBuilder("SELECT count(*) as num FROM c_study_task a ");
		StringBuilder listBuilder = new StringBuilder("SELECT a.id as stId,a.task_name as taskName,a.task_memo as taskMemo,b.paper_name as paperName,a.start_time as startTime,a.end_time as endTime FROM c_study_task a ");
		
		
		this.bulidQueryStrItem(countBuilder, listBuilder, " LEFT JOIN c_exam_paper b ON a.exam_paper_id = b.id where 1=1");
		this.bulidQueryStrItem(countBuilder, listBuilder, " and a.status = 1");
		this.bulidQueryStrItem(countBuilder,listBuilder," and a.id not in (SELECT task_id from c_task_finish_user where user_id=:userId)");
		
		
		listBuilder.append(" order by a.create_time"); 
			
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE)
				.setParameter("userId", queryDTO.getUserId());
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("stId", LongType.INSTANCE).addScalar("taskName", StringType.INSTANCE)
						.addScalar("taskMemo", StringType.INSTANCE).addScalar("paperName", StringType.INSTANCE)	
						.addScalar("startTime", TimestampType.INSTANCE).addScalar("endTime", TimestampType.INSTANCE)
						.setParameter("userId", queryDTO.getUserId())
						.setResultTransformer(Transformers.aliasToBean(StudyTaskDTO.class));	
						
						

		
		Long recTotal = (Long) queryCount.uniqueResult();		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());		
		List<StudyTaskDTO> list = queryList.list();
		
		return new Page<StudyTaskDTO>(queryDTO.getCurrentPage(),queryDTO.getPageSize(), list, recTotal);

	}
	
	
	@Override
	public Integer countTotalSize(String userId) {
		StringBuffer sql = new StringBuffer("SELECT count(*) as num FROM c_study_task a");
		sql.append(" where a.status = 1");
		sql.append(" and a.id not in (SELECT task_id from c_task_finish_user where user_id =: userId)");
		
		Query queryList = this.getSession().createSQLQuery(sql.toString()).setParameter("userId", userId);		
		return (Integer)queryList.uniqueResult();
		
	}
	
	
	
	

	
}
