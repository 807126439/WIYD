package com.wb.web.study.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.study.dto.studycategory.StudyCategoryDTO;
import com.wb.web.study.dto.studycategory.StudyCategoryQueryDTO;
import com.wb.web.study.entity.StudyCategory;

public interface IStudyCategoryDao extends IBaseDao<Long, StudyCategory>{

	/**
	 * 分页查询试卷类别
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
	 * 查询类别ID与类别名称
	 * @return
	 */
	public List<StudyCategory> getStudyCategoriesAndIdAndName();
		
	
	

}
