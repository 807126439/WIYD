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
import com.wb.web.study.dao.IExamPaperDao;
import com.wb.web.study.dto.examPaper.ExamPaperDTO;
import com.wb.web.study.dto.examPaper.ExamPaperQueryDTO;
import com.wb.web.study.entity.ExamPaper;

@Repository("examPaperDao")
public class ExamPaperDaoImpl extends BaseDaoImpl<Long, ExamPaper> implements IExamPaperDao{

	@Override
	public Page<ExamPaperDTO> searchExamPaperPage(ExamPaperQueryDTO queryDTO) {
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num from c_exam_paper a ");
		StringBuilder listBuilder = new StringBuilder("SELECT a.id,a.paper_name as paperName,a.exam_minute as examMinute,a.paper_type as paperType from c_exam_paper a ");

		
		this.bulidQueryStrItem(countBuilder, listBuilder, "where 1=1 ");
		
		if (queryDTO.getPaperName()!= null&&queryDTO.getPaperName()!="") {
			this.bulidQueryStrItem(countBuilder, listBuilder,"AND a.paper_name like:paperName ");				
		}if (queryDTO.getPaperType()!= null) {
			this.bulidQueryStrItem(countBuilder, listBuilder,"AND a.paper_type =:paperType ");				
		}
		
		

		if(!StringUtils.isBlank(queryDTO.getSidx()) && !StringUtils.isBlank(queryDTO.getSord())){		
			String filedName = null;
			if(queryDTO.getSidx().equals("paperName")){
				filedName = "paper_name";
				listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,"a"));				
			}if(queryDTO.getSidx().equals("paperType")){
				filedName = "paper_type";
				listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,"a"));				
			}if(queryDTO.getSidx().equals("id")){
				filedName = "create_time";
				listBuilder.append(generateOrderSql("desc", filedName,"a"));				
			}		
		}
		
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("id", LongType.INSTANCE)
						.addScalar("paperName", StringType.INSTANCE)
						.addScalar("examMinute", IntegerType.INSTANCE)							
						.addScalar("paperType", IntegerType.INSTANCE)						
						.setResultTransformer(Transformers.aliasToBean(ExamPaperDTO.class));	
		
		
		if (queryDTO.getPaperName()!= null&&queryDTO.getPaperName()!="") {
			this.setQueryParamsVal(queryCount, queryList, "paperName", queryDTO.getPaperName());	
		}if (queryDTO.getPaperType() != null) {
			this.setQueryParamsVal(queryCount, queryList, "paperType", queryDTO.getPaperType());	
		}
		
		
		
		Long recTotal = (Long) queryCount.uniqueResult();
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());		
		List<ExamPaperDTO> list = queryList.list();
		
		return new Page<ExamPaperDTO>(queryDTO.getCurrentPage(),queryDTO.getPageSize(), list, recTotal);
	}


	@Override
	public List<ExamPaperDTO> listExamPaper() {
		StringBuilder listBuilder=new StringBuilder("SELECT a.id,a.paper_name as paperName from c_exam_paper a");
		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("paperName", StringType.INSTANCE)	
				.setResultTransformer(Transformers.aliasToBean(ExamPaperDTO.class));
		return queryList.list();
	}

}
