package com.wb.web.system.service;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.web.system.dto.user.UserDTO;
import com.wb.web.system.dto.user.UserQueryDTO;

public interface IUserService {
	
	public Page<UserDTO> searchUserByPage(UserQueryDTO queryDTO);
	
	public UserDTO getUserById(String id);

	public void addUser(UserDTO dto,String password,String password2, String[] roleIds);
	
	public void updateUser(UserDTO dto,String[] roleIds);
	
	public void changePassWordByAdmin(String userId,String newPwd, String newPwd2);
	
	public void updatePassWord(String oldPwd,String newPwd, String newPwd2);
	
    public void updateImportUser(String jsonData);

	public Map<String, Object> getStatistics();
	
	public void deleteUserByChangeVal(String ids[]);
	
	public void importByExcel(CommonsMultipartFile file);
}
