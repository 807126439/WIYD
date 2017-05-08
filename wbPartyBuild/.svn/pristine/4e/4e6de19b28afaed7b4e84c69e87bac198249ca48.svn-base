package com.wb.web.portals.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dao.IGeneralStatDao;
import com.wb.web.portals.dto.generalStat.GeneralStatDTO;
import com.wb.web.portals.dto.generalStat.GeneralStatQueryDTO;


@Repository("generalStatDao")
public class GeneralStatDaoImpl implements IGeneralStatDao{
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public Page<GeneralStatDTO> searchStatByPage(GeneralStatQueryDTO queryDTO) {
		
		//合并的子查询表
		StringBuilder loginTable = new StringBuilder("	SELECT l.user_name AS loginName, " +
				"count(DISTINCT DATE_FORMAT(l.login_date, '%Y-%m-%d')) AS loginNum FROM c_login_log l " +
				"WHERE 1 = 1 GROUP BY l.user_name");
		StringBuilder contentStatTable = new StringBuilder("SELECT cs.user_id AS userId, count(cs.user_id) AS articleNum " +
				"FROM cy_content_stat cs " +
				"WHERE 1 = 1 GROUP BY cs.user_id");
		StringBuilder commentTable = new StringBuilder("	SELECT b.user_id AS user_id, count(DISTINCT b.comment_time) AS commentNum " +
				"FROM cy_comment b WHERE 1 = 1 GROUP BY b.user_id");
		StringBuilder examTable = new StringBuilder("	SELECT es.user_id AS user_id, count(es.user_id) AS examNum " +
				"FROM c_exam_score es WHERE 1 = 1 GROUP BY es.user_id");
		StringBuilder t1feedbackTable = new StringBuilder("	SELECT o.feedback_user AS user_name, count(o.feedback_user) AS feedbackNum" +
				" FROM c_opinion_feedback o WHERE 1 = 1 AND o.type_id = 1 GROUP BY o.feedback_user");
		StringBuilder t2feedbackTable = new StringBuilder("	SELECT o.feedback_user AS user_name, count(o.feedback_user) AS feedbackNum" +
				" FROM c_opinion_feedback o WHERE 1 = 1 AND o.type_id = 2 GROUP BY o.feedback_user");
		StringBuilder feelingTable = new StringBuilder("	SELECT c.owner_id AS user_id, count(c.owner_id) AS feelingNum " +
				"FROM c_feeling_record c WHERE 1 = 1 GROUP BY c.owner_id");
		
		if(queryDTO.getStartTime()!=null && !StringUtils.isBlank(queryDTO.getStartTime())){
			loginTable.insert(loginTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(l.login_date) >= UNIX_TIMESTAMP(:l_begin) ");
			contentStatTable.insert(contentStatTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(cs.read_date) >= UNIX_TIMESTAMP(:l_begin) ");
			commentTable.insert(commentTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(b.comment_time) >= UNIX_TIMESTAMP(:l_begin) ");
			examTable.insert(examTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(es.last_operator_time) >= UNIX_TIMESTAMP(:l_begin) ");
			t1feedbackTable.insert(t1feedbackTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(o.feedback_time) >= UNIX_TIMESTAMP(:l_begin) ");
			t2feedbackTable.insert(t2feedbackTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(o.feedback_time) >= UNIX_TIMESTAMP(:l_begin) ");
			feelingTable.insert(feelingTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(c.create_time) >= UNIX_TIMESTAMP(:l_begin) ");
		}
		if(queryDTO.getEndTime()!=null && !StringUtils.isBlank(queryDTO.getEndTime())){
			loginTable.insert(loginTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(l.login_date) <= UNIX_TIMESTAMP(:l_end) ");
			contentStatTable.insert(contentStatTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(cs.read_date) <= UNIX_TIMESTAMP(:l_end) ");
			commentTable.insert(commentTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(b.comment_time) <= UNIX_TIMESTAMP(:l_end) ");
			examTable.insert(examTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(es.last_operator_time) <= UNIX_TIMESTAMP(:l_end) ");
			t1feedbackTable.insert(t1feedbackTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(o.feedback_time) <= UNIX_TIMESTAMP(:l_end) ");
			t2feedbackTable.insert(t2feedbackTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(o.feedback_time) <= UNIX_TIMESTAMP(:l_end) ");
			feelingTable.insert(feelingTable.lastIndexOf("GROUP"), " AND UNIX_TIMESTAMP(c.create_time) <= UNIX_TIMESTAMP(:l_end) ");
		}
		
		//主查询语句
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(DISTINCT u.id) AS  num FROM c_user u  " +
				"LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id  where 1=1 AND u.is_del=0 AND u.is_ignore_stat=0 ");
		StringBuilder listBuilder  = new StringBuilder("SELECT DISTINCT u.id AS id,u.user_name AS userName, cs2.articleNum AS articleNum, l2.loginNum AS loginNum, b2.commentNum AS communicationNum," +
				" es2.examNum AS examNum, o2t1.feedbackNum AS t1feedbackNum, o2t2.feedbackNum AS t2feedbackNum, c2.feelingNum AS feelingNum" +
				" FROM c_user u " +
				" LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id " +
				"LEFT JOIN (" +contentStatTable.toString()+" ) cs2 ON cs2.userId = u.id " +
				"LEFT JOIN (" +loginTable.toString()+" ) l2 ON l2.loginName = u.user_name " +
				"LEFT JOIN (" +commentTable.toString()+" ) b2 ON b2.user_id = u.id " +
				"LEFT JOIN (" +examTable.toString()+" ) es2 ON es2.user_id = u.id " +
				"LEFT JOIN (" +t1feedbackTable.toString()+" ) o2t1 ON o2t1.user_name = u.user_name " +
				"LEFT JOIN (" +t2feedbackTable.toString()+" ) o2t2 ON o2t2.user_name = u.user_name " +
				"LEFT JOIN (" +feelingTable.toString()+" ) c2 ON c2.user_id = u.id WHERE 1 = 1 AND u.is_del=0 AND u.is_ignore_stat=0 ");
		
		
		if(queryDTO.getUsername()!=null && !StringUtils.isBlank(queryDTO.getUsername())){
			countBuilder.append("AND u.user_name LIKE :name ");
			listBuilder.append("AND u.user_name LIKE :name ");
		}
		
		//部门查询条件相关
		if(queryDTO.getDepartmentIds()!=null && !StringUtils.isBlank(queryDTO.getDepartmentIds())){
			String[] ids=queryDTO.getDepartmentIds().split(",");
			StringBuilder departmentIds=new StringBuilder();
			for(String o:ids){
				departmentIds.append("'"+o+"',");
			}
			departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
			countBuilder.append("AND jdr.department_id IN ("+departmentIds+") ");
			listBuilder.append("AND jdr.department_id IN ("+departmentIds+") ");
		}
		
		if(queryDTO.getSidx()!=null && !StringUtils.isBlank(queryDTO.getSidx())){
			listBuilder.append(" ORDER BY  "+queryDTO.getSidx()+" "+queryDTO.getSord());
		}
		
		Query queryCount = this.sessionFactory.getCurrentSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.sessionFactory.getCurrentSession().createSQLQuery(listBuilder.toString())
				.addScalar("userName", StringType.INSTANCE)
				.addScalar("articleNum", IntegerType.INSTANCE)
				.addScalar("loginNum", IntegerType.INSTANCE)
				.addScalar("communicationNum", IntegerType.INSTANCE)
				.addScalar("examNum", IntegerType.INSTANCE)
				.addScalar("t1feedbackNum", IntegerType.INSTANCE)
				.addScalar("t2feedbackNum", IntegerType.INSTANCE)
				.addScalar("feelingNum", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(GeneralStatDTO.class));
		
		if(queryDTO.getUsername()!=null && !StringUtils.isBlank(queryDTO.getUsername())){
			queryCount.setParameter("name", "%"+queryDTO.getUsername()+"%");
			queryList.setParameter("name", "%"+queryDTO.getUsername()+"%");
		}
		if(queryDTO.getStartTime()!=null && !StringUtils.isBlank(queryDTO.getStartTime())){
			queryList.setParameter("l_begin", queryDTO.getStartTime());
		}
		if(queryDTO.getEndTime()!=null && !StringUtils.isBlank(queryDTO.getEndTime())){
			queryList.setParameter("l_end",queryDTO.getEndTime());
		}
		
		Long recTotal = (Long) queryCount.uniqueResult();
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		if(null!=queryDTO.getPageSize()){
			queryList.setMaxResults(queryDTO.getPageSize());
		}
		
		
		List<GeneralStatDTO> list = queryList.list();
		int sort=1;
		for(GeneralStatDTO o:list){
			o.setSort(sort);
			sort++;
		}
		
		return new Page<GeneralStatDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), list, recTotal);
	}
	
	public List<StringBuilder> addString(List<StringBuilder> allBuilder,String insertString,String LastIndexOf){
		if(!StringUtils.isNotEmpty(LastIndexOf)){
			for(StringBuilder o:allBuilder){
				o.append(insertString);
			}
		}else{
			for(StringBuilder o:allBuilder){
				o.insert(o.lastIndexOf(LastIndexOf), insertString);
			}
		}
		return allBuilder;
	}

}
