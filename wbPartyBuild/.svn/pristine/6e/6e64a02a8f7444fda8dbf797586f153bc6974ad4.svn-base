package com.wb.web.study.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyData.StudyDataQueryDTO;
import com.wb.web.study.entity.StudyData;

public interface IStudyDataDao extends IBaseDao<Long, StudyData> {

	/**
	 * 后台分页查询学习资料
	 * 
	 * @param queryDTO
	 * @return
	 */
	public Page<StudyDataDTO> searchStudyDataPage(StudyDataQueryDTO queryDTO);


	public Long getStuDataByIdFindCateName(Long id);

	/**
	 * 修改之前根据id查询备注、名称和编号
	 * 
	 * @param id
	 * @return
	 */
	public StudyDataDTO getStudyDataById(Long id);

	public List<StudyDataDTO> getStudydataByIdAndName(Long id);
	
	public StudyDataDTO getFileStudyDataById(Long id);
}
