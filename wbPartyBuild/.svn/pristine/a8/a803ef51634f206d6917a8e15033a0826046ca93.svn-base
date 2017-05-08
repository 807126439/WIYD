package com.wb.web.study.dao;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.study.dto.studyTask.StudyTaskDTO;
import com.wb.web.study.dto.studyTask.StudyTaskQueryDTO;
import com.wb.web.study.entity.StudyTask;

public interface IStudyTaskDao extends IBaseDao<Long, StudyTask>{
	/**
	 * 根据编号查找study_category_id的数量
	 * @param id
	 * @return
	 */
	public Long getStudyTaskByIdExists(Long id);
	
	/**
	 * 分页查询学习任务
	 * @param queryDTO
	 * @return
	 */
	public Page<StudyTaskDTO> searchStudyTaskPage(StudyTaskQueryDTO queryDTO);
	/**
	 * 根据id查找一条信息
	 * @param stId
	 * @return
	 */
	public StudyTaskDTO getStudyTaskById(Long stId);
	
	
	public boolean checkIfFinished(Long studyTaskId,String userId);
	
	public Page<StudyTaskDTO> getAppPage(StudyTaskQueryDTO queryDTO);
	
	public Integer countTotalSize(String userId);
}
