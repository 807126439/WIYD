package com.spr.web.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spr.core.common.bean.CommonParam;
import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.entity.Role;

public interface IRoleDao extends IBaseDao<String, Role>{
 
    List<Role> selectRolesByUserId(String uid);
    
    List<Role> selectAllRole();
    
    Long countByCondition(Map<String, Object> queryMap);
    
    List<Role> selectListByCondition(Map<String, Object> queryMap);
    
    Role getUniqueByProperty(CommonParam cp);
    
    Long checkUniqueIsExist(CommonParam cp);
    
    void bathInsertRoleAndAuth(Map<String, Object> queryMap);
    
    void deleteRoleAndAuthByRoleId(String roleId);
    
    List<Role> selectByRoleCodes(@Param("roleCodes")String[] roleCodes);
}