package com.spr.web.system.service;

import java.util.List;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.dto.DownLoadDTO;
import com.spr.web.system.dto.user.UserDTO;
import com.spr.web.system.dto.user.WeiUserDTO;

public interface IUserService {

	Page<UserDTO> searchUserByPage(DataQuery dq);
	
	UserDTO getUserById(String id);

	void addUser(UserDTO dto,String password,String password2, String[] roleIds);
	
	void updateUser(UserDTO dto,String[] roleIds);
	
	void changePassWordByAdmin(String userId,String newPwd, String newPwd2);
	
	void updatePassWord(String oldPwd,String newPwd, String newPwd2);
		
	List<WeiUserDTO> selectWeiUser(DataQuery dq);	
	
	void deleteUser(String[] ids);
	
	void updateNowUserInfo(String chineseName,String phone,String email,String newPwd,String newPwd2);
	
	void registerUser(UserDTO dto,String password,String password2,Short registerType,String ucode);
    
    DownLoadDTO exportExcel(DataQuery dq,String dataType);
    
    void changeAccountEnable(String userId);
    
    void addRolesToUser(String userId,String[] roleCodes);
    
    void deleteRolesToUser(String userId,String[] roleCodes);

  
}
