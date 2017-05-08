package com.wb.web.study.service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.study.dto.examScore.ExamScoreDTO;
import com.wb.web.study.dto.examScore.ExamScoreQueryDTO;
import com.wb.web.study.dto.examScore.ExaminationCountDTO;
import com.wb.web.study.dto.examScore.ExaminationCountQueryDTO;

public interface IExamScoreService {

	public Page<ExaminationCountDTO> countExaminationPage(
			ExaminationCountQueryDTO queryDTO);

	public Page<ExamScoreDTO> searchExamScorePage(ExamScoreQueryDTO queryDTO);

	public void deleteExamScore(Long[] ids);

	public void addExamScore(ExamScoreDTO dto);

	public void editExamScore(ExamScoreDTO dto);

	public ExamScoreDTO getExamScoreDTOById(Long id);

	public Boolean checkIsExist(Long taskId);

	public DownLoadDTO createXlsFile();

	public DownLoadDTO exportExcel();
}
