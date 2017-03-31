package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.authority.AuthLightDto;
import com.wb.web.system.dto.authority.AuthLightDto2;
import com.wb.web.system.dto.authority.AuthQueryDTO;
import com.wb.web.system.dto.authority.AuthTreeQueryDTO;
import com.wb.web.system.entity.Authority;

public interface IAuthorityDao extends IBaseDao<Long, Authority>{
	
	public Page<Authority> searchAuthorityByPage(AuthQueryDTO queryDTO);
	
	public List<Authority> searchTreeByCondition(AuthTreeQueryDTO queryDTO);
	
	public List<Authority> searchAuthoritiesByRole(String roleId,Short flag, Short... authTypes);
	
	public Long getAuthByRoleAndText(String roleList,String text);
	
	public List<AuthLightDto> getAuthsByRoleAndParent(String roleList,String parent,Short flag,Short authType);
	
	public List<String> getAuthControlItem(String roleList);
	
	public List<AuthLightDto2> findAllAuthLightDto2(Short flag);
	

}
