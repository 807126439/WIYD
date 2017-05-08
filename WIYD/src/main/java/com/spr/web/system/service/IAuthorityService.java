package com.spr.web.system.service;

import java.util.List;
import java.util.Set;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.web.system.dto.authority.AuthZtreeDto;
import com.spr.web.system.dto.authority.AuthorityDTO;

public interface IAuthorityService {

	public Page<AuthorityDTO> searchAuthByPage(DataQuery dq);
	
	public String getWholeZtreeWithCheck(String roleId);
		
	public List<AuthZtreeDto> searchAuthZtree(String parentId,Integer level);
	
	public AuthorityDTO getAuthById(String id);
	
	public String addAuth(AuthorityDTO dto);
	
	public void updateAuth(AuthorityDTO dto);
	
	public void deleteAuth(String[] ids);
	
	public List<String> getAuthControlItem(Set<String> roleIdList);
	
	public String getAuthMenu(Set<String> roleIdList,String contextName);
	
	public String getWholeZtreeStructure(String authId);

	

}
