package com.wb.web.system.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.system.dto.authority.AuthQueryDTO;
import com.wb.web.system.dto.authority.AuthTreeQueryDTO;
import com.wb.web.system.dto.authority.AuthZtreeDto;
import com.wb.web.system.dto.authority.AuthorityDTO;
import com.wb.web.system.entity.Authority;

public interface IAuthorityService {

	public Page<AuthorityDTO> searchAuthByPage(AuthQueryDTO queryDTO);
	
	public String getWholeZtreeWithCheck(String roleId);
		
	public List<AuthZtreeDto> searchAuthZtree(AuthTreeQueryDTO queryDTO);
	
	public AuthorityDTO getAuthById(String id);
	
	public void addAuth(AuthorityDTO dto);
	
	public void updateAuth(AuthorityDTO dto);
	
	public void deleteAuth(String[] ids);
	
	public List<String> getAuthControlItem(String roleList);
	
	public String getAuthMenu(String roleList,String contextName);
	
	public List<Authority> searchAuthoritiesByRole(String roleId, Short flag, Short... authTypes);
	

}
