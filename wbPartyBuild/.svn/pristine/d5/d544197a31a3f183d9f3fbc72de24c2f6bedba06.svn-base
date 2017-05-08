package com.wb.web.study.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.study.dto.studycategory.StudyCategoryDTO;
import com.wb.web.study.dto.studycategory.StudyCategoryQueryDTO;
import com.wb.web.study.entity.StudyCategory;


public interface IStudyCategoryService {

	/**
	 * 查询
	 * @param queryDTO
	 * @return
	 */
	public Page<StudyCategoryDTO> searchStudyCategoryPage(StudyCategoryQueryDTO queryDTO);
	/**
	 * 根据编号查询试卷类别
	 * @param id
	 * @return
	 */
	public StudyCategoryDTO getStudyCategoryById(Long id);
	
	/**
	 * 添加
	 * @param dto
	 */
	public void addStudyCategory(StudyCategoryDTO dto);
	/**
	 * 修改
	 * @param dto
	 */
	public void updateStudyCategory(StudyCategoryDTO dto);
	
	/**
	 * 删除
	 * @param ids
	 * @return 
	 */
	public AjaxJson deleteStudyCategory(Long[] ids);
	
	/**
	 * 查询类别ID与类别名称
	 * @return
	 */
	public List<StudyCategory> getStudyCategoriesAndIdAndName();
}
