package com.wb.web.system.dao.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.system.dao.IEduDegreeDao;
import com.wb.web.system.dto.EduDegree.EduDegreeDTO;
import com.wb.web.system.dto.EduDegree.EduDegreeQueryDTO;
import com.wb.web.system.entity.EduDegree;

@Repository("eduDegreeDao")
public class EduDegreeDaoImpl extends BaseDaoImpl<String, EduDegree> implements IEduDegreeDao{

	@SuppressWarnings("unchecked")
	public Page<EduDegreeDTO> searchEduDegreeByPage(EduDegreeQueryDTO queryDto) {
		StringBuilder countBuilder = new StringBuilder("SELECT count(*) as num FROM c_edu_degree ced WHERE 1=1 ");
		StringBuilder listBuilder  = new StringBuilder("select ced.id as id,ced.edu_name as eduName,ced.edu_text as eduText, ced.sort as sort from c_edu_degree ced where 1=1 ");
		if(queryDto.getEduName()!=null && !StringUtils.isBlank(queryDto.getEduName())){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND ced.edu_name LIKE :eduName ");		
		}
		this.bulidQueryStrItem(countBuilder, listBuilder, "ORDER BY ced.sort  ");		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("id", StringType.INSTANCE)
						.addScalar("eduName", StringType.INSTANCE)
						.addScalar("eduText", StringType.INSTANCE)
						.addScalar("sort", IntegerType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(EduDegreeDTO.class));
		if(queryDto.getEduName()!=null && !StringUtils.isBlank(queryDto.getEduName())){
			this.setQueryParamsVal(queryCount, queryList, "eduName", "%"+queryDto.getEduName()+"%");
		}				
		Long recTotal = (Long) queryCount.uniqueResult();
		queryList.setFirstResult(queryDto.getStartQuery());
		queryList.setMaxResults(queryDto.getPageSize());
		List<EduDegreeDTO> list = queryList.list();
		
		return new Page<EduDegreeDTO>(queryDto.getCurrentPage(), queryDto.getPageSize(), list, recTotal);
	}

	@Override
	public List<EduDegreeDTO> getAll() {
		StringBuilder listBuilder = new StringBuilder("SELECT ced.id as id,ced.edu_name as eduName,ced.edu_text as eduText, ced.sort as sort FROM c_edu_degree ced where 1=1");
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", StringType.INSTANCE)
				.addScalar("eduName", StringType.INSTANCE)
				.addScalar("eduText", StringType.INSTANCE)
				.addScalar("sort", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(EduDegreeDTO.class));
		List<EduDegreeDTO> list = queryList.list();
		return list;
	}

}
