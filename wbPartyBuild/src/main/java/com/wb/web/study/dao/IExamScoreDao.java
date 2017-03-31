package com.wb.web.study.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.study.dto.examScore.ExamScoreDTO;
import com.wb.web.study.dto.examScore.ExamScoreQueryDTO;
import com.wb.web.study.dto.examScore.ExaminationCountDTO;
import com.wb.web.study.dto.examScore.ExaminationCountQueryDTO;
import com.wb.web.study.entity.ExamScore;

public interface IExamScoreDao extends IBaseDao<Long, ExamScore> {

	public List<ExaminationCountDTO> findAllForExport();

	public Page<ExaminationCountDTO> countExaminationPage(
			ExaminationCountQueryDTO queryDTO);

	public Page<ExamScoreDTO> searchExamScorePage(ExamScoreQueryDTO queryDTO);

	public ExamScore getES(Long taskId, String userid);

	public List<ExamScoreDTO> getExamScoreList();
}
