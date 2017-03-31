package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.role.RoleQueryDTO;
import com.wb.web.system.entity.Role;

public interface IRoleDao extends IBaseDao<Long, Role>{

	public List<Role> getRolesByUserId(String id);
	
	public List<Role> searchListByCondition(RoleQueryDTO queryDTO);
	
	public Page<Role> searchRoleByPage(RoleQueryDTO queryDTO);
}
