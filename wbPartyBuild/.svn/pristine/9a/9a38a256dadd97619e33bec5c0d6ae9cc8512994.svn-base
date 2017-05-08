package com.wb.web.system.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.system.dto.role.RoleDTO;
import com.wb.web.system.dto.role.RoleQueryDTO;

public interface IRoleService {
	
	 public List<RoleDTO> loadAllRole();
	
	public List<RoleDTO> searchListByCondition(RoleQueryDTO queryDTO);
	
	public Page<RoleDTO> searchRoleByPage(RoleQueryDTO queryDTO);
	
	public RoleDTO getRoleById(String id);
	
	public void addRole(RoleDTO dto,String[] auths);
	
	public void updateRole(RoleDTO dto,String[]  auths);
	
	public void deleteRoles(String[] ids);	
	
	public void reloadAuthMap();

	
}
