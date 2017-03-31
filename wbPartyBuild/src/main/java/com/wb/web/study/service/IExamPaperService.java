package com.wb.web.study.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.study.dto.examPaper.ExamPaperDTO;
import com.wb.web.study.dto.examPaper.ExamPaperQueryDTO;
import com.wb.web.study.dto.examPaper.OnlineTestDTO;

public interface IExamPaperService {
	
	
	public Page<ExamPaperDTO> searchExamPaperPage(ExamPaperQueryDTO queryDTO);
	
	public void deleteExamPaper(Long[] ids);
	
	public void addExamPaper(ExamPaperDTO dto);
	
	public void editExamPaper(ExamPaperDTO dto);
	
	public ExamPaperDTO getExamPaperDTOById(Long id);

	
	public OnlineTestDTO getOnlinePaperById(Long id);

	
	public List<ExamPaperDTO> listExamPaper();


}
