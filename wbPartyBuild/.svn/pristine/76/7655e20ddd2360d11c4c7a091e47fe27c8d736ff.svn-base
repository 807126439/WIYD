package com.wb.web.study.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.study.dao.ITopicDao;
import com.wb.web.study.dto.topic.TopicDTO;
import com.wb.web.study.dto.topic.TopicQueryDTO;
import com.wb.web.study.entity.Topic;

@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Long, Topic> implements ITopicDao {

	@Override
	public Long getTopicByIdFindcateName(Long id) {
		StringBuilder countBuilder=new StringBuilder("select count(*)as num from c_topic ct where ct.study_category_id in(select csc.id  from c_study_category csc where ct.study_category_id=:id)");
		return (Long) this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE).setParameter("id",id).uniqueResult();
	}

	@Override
	public Page<TopicDTO> searchTopicPage(TopicQueryDTO queryDTO) {
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num from c_topic a ");
		StringBuilder listBuilder = new StringBuilder("SELECT a.id as topicId,b.cate_name as categoryName,a.topic_type as topicType,a.question_text as questionText,a.score from c_topic a ");

		
		this.bulidQueryStrItem(countBuilder, listBuilder, "INNER JOIN c_study_category b on a.study_category_id = b.id where 1=1");
		
		if (queryDTO.getQuestionText() != null && queryDTO.getQuestionText() !="") {
			this.bulidQueryStrItem(countBuilder, listBuilder," AND a.question_text like:questionText ");				
		}
		if (queryDTO.getTopicType() != null) {
			this.bulidQueryStrItem(countBuilder, listBuilder," AND a.topic_type =:topicType ");				
		}
		if (queryDTO.getCategoryId()!=null) {
			this.bulidQueryStrItem(countBuilder, listBuilder," AND a.study_category_id=:categoryId ");					
		}
		
		
		

		if(!StringUtils.isBlank(queryDTO.getSidx()) && !StringUtils.isBlank(queryDTO.getSord())){		
			String filedName = null;
			if(queryDTO.getSidx().equals("questionText")){
				filedName = "question_text";
				listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,"a"));				
			}if(queryDTO.getSidx().equals("topicType")){
				filedName = "topic_type";
				listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,"a"));				
			}if(queryDTO.getSidx().equals("categoryId")){
				filedName = "study_category_id";
				listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,"a"));				
			}			
		}
		
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("topicId", LongType.INSTANCE).addScalar("categoryName", StringType.INSTANCE)
						.addScalar("topicType", IntegerType.INSTANCE)
						.addScalar("questionText", StringType.INSTANCE).addScalar("score", IntegerType.INSTANCE)
						
						.setResultTransformer(Transformers.aliasToBean(TopicDTO.class));	
		
		
		if (queryDTO.getQuestionText() != null&& queryDTO.getQuestionText() !="") {
			this.setQueryParamsVal(queryCount, queryList, "questionText", "%"+queryDTO.getQuestionText()+"%");	
		}
		if (queryDTO.getTopicType() != null) {
			this.setQueryParamsVal(queryCount, queryList,"topicType",queryDTO.getTopicType());				
		}
		if (queryDTO.getCategoryId()!=null) {
			this.setQueryParamsVal(queryCount, queryList,"categoryId",queryDTO.getCategoryId());					
		}
		
		
		Long recTotal = (Long) queryCount.uniqueResult();
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());		
		List<TopicDTO> list = queryList.list();
		
		return new Page<TopicDTO>(queryDTO.getCurrentPage(),queryDTO.getPageSize(), list, recTotal);

	}

	@Override
	public List<TopicDTO> getTopicsRandom(Long categoryType,Integer topicType,Integer num) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id as topicId,a.topic_type as topicType,a.score,a.question_text AS questionText,a.answer,a.option_A AS optionA,");
		sql.append("a.option_B AS optionB,a.option_C AS optionC,a.option_D AS optionD,a.option_E AS optionE,a.option_F AS optionF,");
		sql.append("a.option_g AS optionG,a.explain_a AS explainA,a.explain_b AS explainB,a.explain_c AS explainC,");
		sql.append("a.explain_d AS explainD,a.explain_e AS explainE,a.explain_f AS explainF,a.explain_g AS explainG");
		sql.append(" from c_topic a where a.topic_type =:topicType and a.study_category_id =:categoryType ");

		sql.append(" ORDER BY rand() LIMIT :num");
				
		Query query = this.getSession().createSQLQuery(sql.toString())
				.addScalar("topicId",LongType.INSTANCE)
				.addScalar("topicType",IntegerType.INSTANCE)
				.addScalar("score",IntegerType.INSTANCE)
				.addScalar("questionText",StringType.INSTANCE)
				.addScalar("answer",StringType.INSTANCE)
				.addScalar("optionA",StringType.INSTANCE).addScalar("optionB",StringType.INSTANCE)
				.addScalar("optionC",StringType.INSTANCE).addScalar("optionD",StringType.INSTANCE)
				.addScalar("optionE",StringType.INSTANCE).addScalar("optionF",StringType.INSTANCE)
				.addScalar("optionG",StringType.INSTANCE)
				.addScalar("explainA",StringType.INSTANCE).addScalar("explainB",StringType.INSTANCE)
				.addScalar("explainC",StringType.INSTANCE).addScalar("explainD",StringType.INSTANCE)
				.addScalar("explainE",StringType.INSTANCE).addScalar("explainF",StringType.INSTANCE)
				.addScalar("explainG",StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(TopicDTO.class))
				.setParameter("topicType", topicType).setParameter("categoryType", categoryType).setParameter("num", num);
		
		return query.list();
	}


}
