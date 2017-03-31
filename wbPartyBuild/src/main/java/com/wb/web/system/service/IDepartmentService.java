package com.wb.web.system.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.system.dto.department.DepartTreeDTO;
import com.wb.web.system.dto.department.DepartmentDTO;
import com.wb.web.system.dto.department.DepartmentDTO2;
import com.wb.web.system.dto.department.DepartmentQueryDTO;

public interface IDepartmentService {
	
	public Page<DepartmentDTO> searchDepartmentyByPage(DepartmentQueryDTO queryDTO);
	
	public DepartmentDTO2 getDepartmentById2(String id);
	
	public DepartmentDTO getDepartmentById(String id);
	
	public String addDepartment(DepartmentDTO dto);
	
	public void updateDepartment(DepartmentDTO dto);
	
	public void updateMoveDepartment(String departId,String parId);
	
	public AjaxJson deleteDepartment(String[] ids);
	
	public List<DepartTreeDTO> searchDepartmentZtree(DepartmentQueryDTO queryDTO);

	public String getZtreeNodesByContentId(String[] seeOrgIds);
	
}
