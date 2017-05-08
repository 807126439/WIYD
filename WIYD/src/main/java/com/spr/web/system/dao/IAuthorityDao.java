package com.spr.web.system.dao;

import java.util.List;
import java.util.Map;

import com.spr.core.common.bean.CommonParam;
import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.dto.authority.AuthorityDTO;
import com.spr.web.system.entity.Authority;

public interface IAuthorityDao extends IBaseDao<String, Authority>{
	
    
    public AuthorityDTO selectWithLeftParentById(String id);
    
    public Long countByCondition(Map<String, Object> map);
    
    public List<AuthorityDTO> selectListByCondition(Map<String, Object> map);
       
    public List<Authority> selectAuthByCondition(Map<String, Object> map);
    
    public List<Authority> selectAuthByCondition2(Map<String, Object> map);
    
    public List<Authority> selectAuthByCondition3(String roleId);
    
    public List<Authority> selectAuthByCondition4(String parentId);
    
    public List<String> getAuthControlItem(Map<String, Object> map);
    
    public Long checkUniqueIsExist(CommonParam cp);
    
 
}