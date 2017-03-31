package com.wb.web.system.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.utils.Assert;
import com.wb.web.system.dto.EduDegree.EduDegreeDTO;
import com.wb.web.system.dto.EduDegree.EduDegreeQueryDTO;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.entity.EduDegree;

public interface IEduDegreeService {
	/**
	 * 分页查询EduDegree的列表信息息
	 * @param queryDto
	 * @return
	 */
	 public Page<EduDegreeDTO> searchEduDegreeByPage(EduDegreeQueryDTO queryDto);
	 /**
	  * 添加教育学历等级
	  * @param dto
	  */
	 public void addEduDegree(EduDegreeDTO dto);
	 /**
	  * 编辑教育学历等级
	  * @param dto
	  */
	 public void updateEduDegree(EduDegreeDTO dto);
	 /**
	  * 删除《批量删除》教育学历等级
	  * @param ids
	  */
	 public void deleteEduDegree(String[] ids);
	 
	/***
	 * 根据编号查询单条信息
	 * @param id
	 * @return
	 */
	public EduDegree getEduDegreeById(String id);
	/**
	 * 获取所有教育学历等级
	 * @return
	 */
	public List<EduDegreeDTO> getAll();
	

	
}
