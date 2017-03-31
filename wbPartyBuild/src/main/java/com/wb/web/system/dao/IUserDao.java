package com.wb.web.system.dao;

import java.util.List;
import java.util.Map;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.user.UserQueryDTO;
import com.wb.web.system.entity.User;

public interface IUserDao extends IBaseDao<Long,User> {

	public Page<User> searchUserByPage(UserQueryDTO queryDTO);	
	
	//public LoginUserDTO getLoginUserByName(String userName);
	
	public void deleteUserRoleRelation(Long roleId,List<Long> userIds);
	
	public void deleteUserRoleRelation(Long roleId,Long userId);

	public Map<String, Object> getStatistics();
	
	public void discardUser(String ids[]);
}
