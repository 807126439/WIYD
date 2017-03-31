package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.EduDegree.EduDegreeDTO;
import com.wb.web.system.dto.EduDegree.EduDegreeQueryDTO;
import com.wb.web.system.entity.EduDegree;

public interface IEduDegreeDao extends IBaseDao<String, EduDegree>{
	
	/**
	 * 分页查询EduDegree的列表信息息
	 * @param queryDto
	 * @return
	 */
	 public Page<EduDegreeDTO> searchEduDegreeByPage(EduDegreeQueryDTO queryDto);

	public List<EduDegreeDTO> getAll();

}
