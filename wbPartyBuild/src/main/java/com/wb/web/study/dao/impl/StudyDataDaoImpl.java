package com.wb.web.study.dao.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.base.entity.BaseFile;
import com.wb.web.study.dao.IStudyDataDao;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyData.StudyDataQueryDTO;
import com.wb.web.study.entity.StudyData;

@Repository("studyDataDao")
public class StudyDataDaoImpl extends BaseDaoImpl<Long, StudyData> implements
		IStudyDataDao {

	/**
	 * 分页查询学习资料（资料类别管理）
	 * 
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<StudyDataDTO> searchStudyDataPage(StudyDataQueryDTO queryDTO) {
		StringBuilder countBuilder = new StringBuilder(
				"select count(*)as num from c_study_data csd left JOIN c_study_category ")
				.append(" csc ON csd.study_category_id=csc.id left JOIN m_base_file mbf on csd.base_file_id=mbf.id where 1=1 ");

		StringBuilder listBuilder = new StringBuilder(
				"select csd.create_time as createTime,csd.data_num as dataNum, csd.study_category_id as cateId,mbf.file_kind as fileKind,csd.id as sdId, csd.data_name as dataName,csd.data_memo as dataMemo,csc.cate_name ")
				.append(" as cateName,mbf.pattern as pattern from c_study_data csd left JOIN c_study_category csc ON csd.study_category_id=csc.id ")
				.append(" left JOIN m_base_file mbf on csd.base_file_id=mbf.id where 1=1 ");
		if (!StringUtils.isBlank(queryDTO.getDataName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					" AND data_name=:dataName ");
		}
		if (!StringUtils.isBlank(queryDTO.getCateName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					" AND cate_name=:cateName ");
		}
		if (queryDTO.getIsVideo() != null) {
			if (queryDTO.getIsVideo().booleanValue()) {
				this.bulidQueryStrItem(countBuilder, listBuilder,
						" AND file_kind=:baseFileId ");
			} else
				this.bulidQueryStrItem(countBuilder, listBuilder,
						" AND file_kind<>:baseFileId ");
		}

		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("dataNum", StringType.INSTANCE)
				.addScalar("fileKind", ShortType.INSTANCE)
				.addScalar("sdId", LongType.INSTANCE)
				.addScalar("dataName", StringType.INSTANCE)
				.addScalar("cateId", LongType.INSTANCE)
				.addScalar("dataMemo", StringType.INSTANCE)
				.addScalar("cateName", StringType.INSTANCE)
				.addScalar("createTime", DateType.INSTANCE)
				.addScalar("pattern", StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyDataDTO.class));
		if (!StringUtils.isBlank(queryDTO.getDataName())) {
			this.setQueryParamsVal(queryCount, queryList, "dataName",
					queryDTO.getDataName());
		}
		if (!StringUtils.isBlank(queryDTO.getCateName())) {
			this.setQueryParamsVal(queryCount, queryList, "cateName",
					queryDTO.getCateName());
		}

		if (queryDTO.getIsVideo() != null) {
			if (queryDTO.getIsVideo().booleanValue()) {
				this.setQueryParamsVal(queryCount, queryList, "baseFileId",
						BaseFile.FILE_KIND_VIDEO);
			} else {
				this.setQueryParamsVal(queryCount, queryList, "baseFileId",
						BaseFile.FILE_KIND_VIDEO);
			}

		}

		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		return new Page<StudyDataDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), queryList.list(),
				(Long) queryCount.uniqueResult());
	}

	@Override
	public Long getStuDataByIdFindCateName(Long sdId) {
		StringBuilder countBuilder = new StringBuilder(
				"select count(*)as num from c_study_data csd where csd.study_category_id in(select csc.id  from c_study_category csc where csd.study_category_id=:sdId)");
		return (Long) this.getSession().createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE).setParameter("sdId", sdId)
				.uniqueResult();
	}

	@Override
	public StudyDataDTO getStudyDataById(Long sdId) {
		// StringBuilder studydatasBuilder = new StringBuilder(
		// "select csd.id as sdId,csd.data_num as dataNum,csd.data_name as dataName,csd.data_memo as dataMemo,csd.study_category_id as cateId from c_study_data csd where csd.id=:sdId");
		StringBuilder studydatasBuilder = new StringBuilder(
				"select csd.id as sdId,csd.data_num as dataNum,csd.data_name as dataName,csd.data_memo as dataMemo,csd.study_category_id as cateId, mbf.id as baseFileId, mbf.file_name as filename from c_study_data csd left join m_base_file mbf on csd.base_file_id=mbf.id where csd.id=:sdId");
		Query queryList = this
				.getSession()
				.createSQLQuery(studydatasBuilder.toString())
				.addScalar("sdId", LongType.INSTANCE)
				.addScalar("cateId", LongType.INSTANCE)
				.addScalar("dataNum", StringType.INSTANCE)
				.addScalar("dataName", StringType.INSTANCE)
				.addScalar("dataMemo", StringType.INSTANCE)
				.addScalar("baseFileId", LongType.INSTANCE)
				.addScalar("filename", StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyDataDTO.class))
				.setParameter("sdId", sdId);

		return (StudyDataDTO) queryList.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudyDataDTO> getStudydataByIdAndName(Long id) {
		StringBuilder listBuilder = new StringBuilder(
				"select  c.data_id as sdId,s.data_name as dataName from c_study_task_data c join c_study_data s on c.data_id=s.id where c.task_id =:id");
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("sdId", LongType.INSTANCE)
				.addScalar("dataName", StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyDataDTO.class))
				.setParameter("id", id);
		return queryList.list();
	}

	@Override
	public StudyDataDTO getFileStudyDataById(Long sdId) {
		StringBuilder studyDataBuilder = new StringBuilder(
				"select mbf.file_kind as fileKind,csd.id as sdId,csd.data_num as dataNum,csd.data_name as dataName,csd.data_memo as dataMemo,csd.create_time as createTime,csc.cate_name as cateName,mbf.file_name as filename,mbf.save_path as pattern,mbf.zone_path_id as zonePathId from ")
				.append(" c_study_data csd LEFT JOIN  m_base_file mbf on csd.base_file_id=mbf.id join c_study_category csc on csd.study_category_id=csc.id where csd.id=:sdId");
		Query queryList = this
				.getSession()
				.createSQLQuery(studyDataBuilder.toString())
				.addScalar("sdId", LongType.INSTANCE)
				.addScalar("dataNum", StringType.INSTANCE)
				.addScalar("dataName", StringType.INSTANCE)
				.addScalar("dataMemo", StringType.INSTANCE)
				.addScalar("createTime", DateType.INSTANCE)
				.addScalar("cateName", StringType.INSTANCE)
				.addScalar("filename", StringType.INSTANCE)
				.addScalar("fileKind", ShortType.INSTANCE)
				.addScalar("pattern", StringType.INSTANCE)
				.addScalar("zonePathId", LongType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(StudyDataDTO.class))
				.setParameter("sdId", sdId);
		return (StudyDataDTO) queryList.uniqueResult();

	}

}
