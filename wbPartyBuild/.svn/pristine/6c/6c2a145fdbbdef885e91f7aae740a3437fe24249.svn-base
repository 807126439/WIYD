package com.wb.web.study.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyData.StudyDataQueryDTO;

public interface IStudyDataService {
	/**
	 * 分页查询学习资料
	 * 
	 * @param queryDTO
	 * @return
	 */
	public Page<StudyDataDTO> searchStudyDataPage(StudyDataQueryDTO queryDTO);

	public Long getStuDataByIdFindCateName(Long id);

	/**
	 * 删除学习资料
	 * 
	 * @param sdId
	 * @return
	 */
	public AjaxJson deleteStuData(Long[] sdId);

	/**
	 * 添加学习资料
	 * 
	 * @param dto
	 */
	public void addStudyData(StudyDataDTO dto, CommonsMultipartFile uploadFile);

	/**
	 * 修改之前根据id查询备注、名称和编号
	 * 
	 * @param id
	 * @return
	 */
	public StudyDataDTO getStudyDataById(Long id);

	/**
	 * 修改学习资料
	 * 
	 * @param dto
	 */
	public void updateStudyData(StudyDataDTO dto);

	/**
	 * 修改学习资料
	 * 
	 * @param dto
	 */
	public void updateStudyData(StudyDataDTO dto,
			CommonsMultipartFile uploadFile);

	/**
	 * 下载学习资料
	 * 
	 * @param id
	 * @return
	 */
	public DownLoadDTO downloadStudyData(Long id);

	public List<StudyDataDTO> getStudydataByIdAndName(Long id);

	public StudyDataDTO getFileStudyDataById(Long sdId);

}
