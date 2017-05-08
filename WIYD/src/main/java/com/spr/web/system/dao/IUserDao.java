package com.spr.web.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spr.core.common.bean.CommonParam;
import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.dto.user.LoginUserDTO;
import com.spr.web.system.dto.user.UserDTO;
import com.spr.web.system.dto.user.WeiUserDTO;
import com.spr.web.system.entity.User;


public interface IUserDao extends IBaseDao<String, User>{
 
    
    List<UserDTO> selectListByCondition(Map<String, Object> queryMap);
    
    Long countByCondition(Map<String, Object> queryMap);
    
    User getUniqueByProperty(CommonParam param);
    
    Long checkUniqueIsExist(CommonParam cp);
    
    void bathInsertUserAndRole(Map<String, Object> queryMap);
    
    void deleteUserAndRoleByUserId(String userId);
    
    void changePassword(Map<String, Object> values);
    
    LoginUserDTO selectLoginUserByName(String userName);
    
    List<WeiUserDTO> selectWeiUser(Map<String, Object> map);   

	UserDTO getDetailById(@Param("id")String id);
	
	void deleteUserAndRoleRelation(@Param("userId")String userId,@Param("roleIds")List<String> roleIds);
    



}