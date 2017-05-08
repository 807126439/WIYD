package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.department.DepartmentDTO3;
import com.wb.web.system.dto.department.DepartmentQueryDTO;
import com.wb.web.system.entity.Department;

public interface IDepartmentDao extends IBaseDao<String, Department>{
	
	 public Page<Department> searchDepartmentByPage(DepartmentQueryDTO queryDto);
	
	public List<Department> searchTreeByCondition(DepartmentQueryDTO queryDTO);

	public List<Department> getAllMainDepartment();
	
	public DepartmentDTO3 getDangInfo(String usreId);


	public List<String> getSelfAndOffspringId(String[] ids, List<String> allIds);
}
