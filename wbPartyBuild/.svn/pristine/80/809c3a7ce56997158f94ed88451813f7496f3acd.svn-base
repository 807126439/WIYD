package com.wb.web.study.dao.impl;

import java.util.List;

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
import com.wb.web.study.dao.IExamScoreDao;
import com.wb.web.study.dto.examScore.ExamScoreDTO;
import com.wb.web.study.dto.examScore.ExamScoreQueryDTO;
import com.wb.web.study.dto.examScore.ExaminationCountDTO;
import com.wb.web.study.dto.examScore.ExaminationCountQueryDTO;
import com.wb.web.study.entity.ExamScore;

@Repository("examScoreDao")
public class ExamScoreDaoImpl extends BaseDaoImpl<Long, ExamScore> implements
		IExamScoreDao {

	/**
	 * 获取全部导出数据
	 */
	public List<ExaminationCountDTO> findAllForExport() {
		// 构造sql语句：参加测试次数（在线学习）统计
		// SELECT u.id AS id, u.user_name AS username, count(es.user_id) AS num,
		// max(es.last_operator_time) AS lastOperatorTime FROM c_user u LEFT
		// JOIN c_exam_score es ON u.id=es.user_id GROUP BY u.id ORDER BY num
		// DESC
		StringBuilder listBuilder = new StringBuilder();
		listBuilder
				.append("SELECT u.id AS id, u.user_name AS username, count(es.user_id) AS num, max(es.last_operator_time) AS lastOperatorTime ");
		listBuilder
				.append("FROM c_user u LEFT JOIN c_exam_score es ON u.id=es.user_id ");
		listBuilder.append("GROUP BY u.id ORDER BY num DESC");
		// 构造query查询条件，并映射成ExaminationCountDTO对象
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("num", IntegerType.INSTANCE)
				.addScalar("lastOperatorTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(ExaminationCountDTO.class));
		// 获取记录列表
		List<ExaminationCountDTO> list = queryList.list();
		// 设置序号
		int sort = 1;
		for (ExaminationCountDTO dto : list) {
			dto.setSort(sort++);
		}
		return list;
	}

	/**
	 * 分页统计测试次数
	 */
	@Override
	public Page<ExaminationCountDTO> countExaminationPage(
			ExaminationCountQueryDTO queryDTO) {
		// 构造sql语句：获取总用户数
		// SELECT COUNT(*) as num FROM (SELECT u.id FROM c_user u LEFT JOIN
		// c_exam_score es ON u.id=es.user_id LEFT JOIN c_job_depart_relation
		// jdr ON u.id=jdr.user_id WHERE is_del=0 and is_ignore_stat=0 GROUP BY
		// u.id) t_derived
		StringBuilder countBuilder = new StringBuilder();
		countBuilder
				.append("SELECT COUNT(*) as num FROM (SELECT u.id FROM c_user u ");
		countBuilder.append("LEFT JOIN c_exam_score es ON u.id=es.user_id ");
		countBuilder
				.append("LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id WHERE is_del=0 and is_ignore_stat=0 ");
		// 构造sql语句：参加测试次数（在线学习）统计
		// SELECT u.id AS id, u.user_name AS username, count(es.user_id) AS num,
		// max(es.last_operator_time) AS lastOperatorTime FROM c_user u LEFT
		// JOIN c_exam_score es ON u.id=es.user_id LEFT JOIN
		// c_job_depart_relation jdr ON u.id = jdr.user_id WHERE is_del=0 and
		// is_ignore_stat=0 GROUP BY u.id ORDER BY num DESC
		StringBuilder listBuilder = new StringBuilder();
		listBuilder
				.append("SELECT DISTINCT u.id AS id, u.user_name AS username, count(es.user_id) AS num, ");
		listBuilder
				.append("max(es.last_operator_time) AS lastOperatorTime FROM c_user u ");
		listBuilder.append("LEFT JOIN c_exam_score es ON u.id=es.user_id ");
		listBuilder
				.append("LEFT JOIN c_job_depart_relation jdr ON u.id = jdr.user_id WHERE is_del=0 and is_ignore_stat=0 ");
		// 根据用户名模糊搜索
		if (queryDTO.getUserName() != null
				&& !StringUtils.isBlank(queryDTO.getUserName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND u.user_name LIKE :name ");
			// countBuilder.append("AND user_name LIKE :name ");
			// listBuilder.append("AND u.user_name LIKE :name ");
		}
		// 指定起始时间
		if (queryDTO.getBeginTime() != null
				&& !StringUtils.isBlank(queryDTO.getBeginTime())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND UNIX_TIMESTAMP(es.last_operator_time) >= UNIX_TIMESTAMP(:l_begin) ");
			// listBuilder
			// .append("AND UNIX_TIMESTAMP(es.last_operator_time) >= UNIX_TIMESTAMP(:l_begin) ");
		}
		// // 指定终止时间
		if (queryDTO.getEndTime() != null
				&& !StringUtils.isBlank(queryDTO.getEndTime())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND UNIX_TIMESTAMP(es.last_operator_time) <= UNIX_TIMESTAMP(:l_end) ");
			// listBuilder
			// .append("AND UNIX_TIMESTAMP(es.last_operator_time) <= UNIX_TIMESTAMP(:l_end) ");
		}
		// 部门查询条件相关
		// 获取ID下自身以及后代部门ID
		if (queryDTO.getDepartmentIds() != null
				&& !StringUtils.isBlank(queryDTO.getDepartmentIds())) {
			String[] ids = queryDTO.getDepartmentIds().split(",");
			// List<String> allIds=new LinkedList<String>();
			// List<String>
			// departmentIdList=this.departmentDao.getSelfAndOffspringId(ids,allIds);
			StringBuilder departmentIds = new StringBuilder();
			for (String o : ids) {
				departmentIds.append("'" + o + "',");
			}
			departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND jdr.department_id IN (" + departmentIds + ") ");
		}
		countBuilder.append("GROUP BY u.id) t_derived ");
		listBuilder.append("GROUP BY u.id,jdr.department_id ORDER BY num DESC ");
		// TODO 按列排序方法
		// if (!StringUtils.isBlank(queryDTO.getSidx())
		// && !StringUtils.isBlank(queryDTO.getSord())) {
		//
		// String filedName = ReflectUtil.getFiledValueByAnnotation(
		// getInheritClass(), queryDTO.getSidx());
		// listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,
		// null));
		// } else {
		// listBuilder.append("ORDER BY num DESC ");
		// }
		// 构造query查询条件，并映射成ExaminationCountDTO对象
		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("num", IntegerType.INSTANCE)
				.addScalar("lastOperatorTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(ExaminationCountDTO.class));
		// 替换sql语句的参数
		if (queryDTO.getUserName() != null
				&& !StringUtils.isBlank(queryDTO.getUserName())) {
			this.setQueryParamsVal(queryCount, queryList, "name", "%"
					+ queryDTO.getUserName() + "%");
		}
		if (queryDTO.getBeginTime() != null
				&& !StringUtils.isBlank(queryDTO.getBeginTime())) {
			this.setQueryParamsVal(queryCount, queryList, "l_begin",
					queryDTO.getBeginTime());
			// queryList.setParameter("l_begin", queryDTO.getBeginTime());
		}
		if (queryDTO.getEndTime() != null
				&& !StringUtils.isBlank(queryDTO.getEndTime())) {
			this.setQueryParamsVal(queryCount, queryList, "l_end",
					queryDTO.getEndTime());
			// queryList.setParameter("l_end", queryDTO.getEndTime());
		}
		// 获取总记录数
		Long recTotal = (Long) queryCount.uniqueResult();
		// 设置分页查询参数
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		// 获取记录列表
		List<ExaminationCountDTO> list = queryList.list();
		// 设置序号
		int sort = 1;
		for (ExaminationCountDTO dto : list) {
			dto.setSort(sort++);
		}
		return new Page<ExaminationCountDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), list, recTotal);
	}

	@Override
	public Page<ExamScoreDTO> searchExamScorePage(ExamScoreQueryDTO queryDTO) {
		StringBuilder countBuilder = new StringBuilder(
				"SELECT COUNT(*) as num from c_exam_score a ");
		StringBuilder listBuilder = new StringBuilder(
				"SELECT a.id,c.task_name as taskName,b.paper_name as paperName,d.chinese_name as username,d.id as userId,a.score,a.spend_time as spendTime,a.last_operator_time as updateTime FROM c_exam_score a");

		this.bulidQueryStrItem(countBuilder, listBuilder,
				" INNER JOIN c_study_task c ON a.study_task_id = c.id");
		this.bulidQueryStrItem(countBuilder, listBuilder,
				" INNER JOIN c_user d ON a.user_id = d.id");
		this.bulidQueryStrItem(countBuilder, listBuilder,
				" INNER JOIN c_exam_paper b ON b.id = c.exam_paper_id");

		// if(!StringUtils.isBlank(queryDTO.getSidx()) &&
		// !StringUtils.isBlank(queryDTO.getSord())){
		// String filedName = null;
		// if(queryDTO.getSidx().equals("questionText")){
		// filedName = "question_text";
		// listBuilder.append(generateOrderSql(queryDTO.getSord(),
		// filedName,"a"));
		// }if(queryDTO.getSidx().equals("topicType")){
		// filedName = "topic_type";
		// listBuilder.append(generateOrderSql(queryDTO.getSord(),
		// filedName,"a"));
		// }if(queryDTO.getSidx().equals("categoryId")){
		// filedName = "study_category_id";
		// listBuilder.append(generateOrderSql(queryDTO.getSord(),
		// filedName,"a"));
		// }
		// }

		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("taskName", StringType.INSTANCE)
				.addScalar("paperName", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("userId", StringType.INSTANCE)
				.addScalar("score", IntegerType.INSTANCE)
				.addScalar("spendTime", LongType.INSTANCE)
				.addScalar("updateTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(ExamScoreDTO.class));

		//
		// if (queryDTO.getQuestionText() != null&& queryDTO.getQuestionText()
		// !="") {
		// this.setQueryParamsVal(queryCount, queryList, "questionText",
		// "%"+queryDTO.getQuestionText()+"%");
		// }
		// if (queryDTO.getTopicType() != null) {
		// this.setQueryParamsVal(queryCount,
		// queryList,"topicType",queryDTO.getTopicType());
		// }
		// if (queryDTO.getCategoryId()!=null) {
		// this.setQueryParamsVal(queryCount,
		// queryList,"categoryId",queryDTO.getCategoryId());
		// }

		Long recTotal = (Long) queryCount.uniqueResult();

		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		List<ExamScoreDTO> list = queryList.list();

		return new Page<ExamScoreDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), list, recTotal);
	}

	@Override
	public List<ExamScoreDTO> getExamScoreList() {
		StringBuilder listBuilder = new StringBuilder(
				"SELECT a.id,c.task_name as taskName,b.paper_name as paperName,d.chinese_name as username,d.id as userId,a.score,a.spend_time as spendTime,a.last_operator_time as updateTime FROM c_exam_score a");

		listBuilder
				.append(" INNER JOIN c_study_task c ON a.study_task_id = c.id");
		listBuilder.append(" INNER JOIN c_user d ON a.user_id = d.id");
		listBuilder
				.append(" INNER JOIN c_exam_paper b ON b.id = c.exam_paper_id");

		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("taskName", StringType.INSTANCE)
				.addScalar("paperName", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("userId", StringType.INSTANCE)
				.addScalar("score", IntegerType.INSTANCE)
				.addScalar("spendTime", LongType.INSTANCE)
				.addScalar("updateTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(ExamScoreDTO.class));

		List<ExamScoreDTO> list = queryList.list();

		return list;
	}

	@Override
	public ExamScore getES(Long taskId, String userid) {

		Criteria cr = this.getSession().createCriteria(this.getInheritClass());

		cr.add(Restrictions.eq("user.id", userid));
		cr.add(Restrictions.eq("studyTask.id", taskId));

		return (ExamScore) cr.uniqueResult();
	}

}
