package com.wb.web.study.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.study.dao.IStudyCategoryDao;
import com.wb.web.study.dto.studycategory.StudyCategoryDTO;
import com.wb.web.study.dto.studycategory.StudyCategoryQueryDTO;
import com.wb.web.study.entity.StudyCategory;


@Repository("studyCategoryDao")
public class StudyCategoryDaoImpl extends BaseDaoImpl<Long, StudyCategory> implements IStudyCategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<StudyCategoryDTO> searchStudyCategoryPage(
			StudyCategoryQueryDTO queryDTO) {
			
		StringBuilder countBuilder=new StringBuilder("SELECT COUNT(*) as num FROM c_study_category WHERE 1=1 ");
		StringBuilder listBuilder =new StringBuilder("SELECT cyc.id as id,cyc.cate_name as cateName,cyc.cate_num as cateNum,")
		.append("STR_TO_DATE(cyc.create_time,'%Y-%m-%d') as createTime FROM c_study_category cyc WHERE 1=1 ");
		if (queryDTO.getCateName()!="" ) {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND cate_name=:cateName ");
		}
		if (queryDTO.getCateNum()!="") {
			this.bulidQueryStrItem(countBuilder, listBuilder, " AND cate_num=:cateNum ");
		}
		Query queryCount=this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num",LongType.INSTANCE);
		Query queryList=this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("cateName",StringType.INSTANCE)
				.addScalar("cateNum",StringType.INSTANCE).addScalar("createTime",DateType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(StudyCategoryDTO.class));
		if (queryDTO.getCateName()!="") {
			this.setQueryParamsVal(queryCount, queryList, "cateName", queryDTO.getCateName());
		}
		if (queryDTO.getCateNum()!="") {
			this.setQueryParamsVal(queryCount, queryList, "cateNum", queryDTO.getCateNum());
		}
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		return new Page<StudyCategoryDTO>(queryDTO.getCurrentPage(),queryDTO.getPageSize(),queryList.list(),(Long)queryCount.uniqueResult());
	}

	@Override
	public StudyCategoryDTO getStudyCategoryById(Long id) {
		StringBuilder listBuilder= new StringBuilder("SELECT cyc.id as id,cyc.cate_name as cateName,cyc.cate_num as cateNum,STR_TO_DATE(cyc.create_time,'%Y-%m-%d') as createTime FROM c_study_category cyc WHERE id=:id");
		Query queryList=this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("cateName",StringType.INSTANCE)
				.addScalar("cateNum",StringType.INSTANCE).addScalar("createTime",DateType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(StudyCategoryDTO.class)).setParameter("id", id);
		return (StudyCategoryDTO) queryList.uniqueResult();
	}
	/**
	 * 查询类别ID、类别编号与类别名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StudyCategory> getStudyCategoriesAndIdAndName() {
		StringBuilder listBuilder=new StringBuilder("select csc.id as id,csc.cate_num as cateNum,csc.cate_name as cateName from c_study_category csc");
		Query queryList=this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id",LongType.INSTANCE).addScalar("cateNum",StringType.INSTANCE)
				.addScalar("cateName",StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(StudyCategoryDTO.class));
		return queryList.list();
	}

}
