package com.wb.web.study.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.study.dto.studyTask.StudyTaskDTO;
import com.wb.web.study.dto.studyTask.StudyTaskQueryDTO;

public interface IStudyTaskService {

	public Long getStudyTaskByIdExists(Long id);
	
	/**
	 * 分页查询学习任务
	 * @param queryDTO
	 * @return
	 */
	public Page<StudyTaskDTO> searchStudyTaskPage(StudyTaskQueryDTO queryDTO);
	
	/**
	 * 添加学习任务
	 * @param dto
	 */
	public void addStudyTask(StudyTaskDTO dto ,Long[] taskStu);
	
	/**
	 * 根据id查找一条学习任务信息
	 * @param stId
	 * @return
	 */
	public StudyTaskDTO getStudyTaskById(Long stId);
	
	/**
	 * 修改学习任务
	 * @param dto
	 */
	public void updateStudyTask(StudyTaskDTO dto,Long[] taskStu);
	
	
	/**
	 * 删除学习任务
	 * @param ids
	 * @return 
	 */
	public AjaxJson deleteStudyTask(Long[] ids);

	/**
	 * 判断用户有无完成学习任务
	 * @param list
	 * @param nowUser
	 */
	public void checkIfFinished(List<StudyTaskDTO> list, String userId);
	
	/**
	 * 使用baseDao中根据id查找一条信息的方法
	 * @param stId
	 * @return
	 */
	public StudyTaskDTO getStudyTaskByIdUseBaseDao(Long stId);
	
	public Page<StudyTaskDTO> getAppPage(StudyTaskQueryDTO queryDTO); 
	
	public Integer countTotalSize(String userId);
}
