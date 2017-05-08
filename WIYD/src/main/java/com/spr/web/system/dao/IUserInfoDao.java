package com.spr.web.system.dao;

import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.dto.user.UserInfoDTO;
import com.spr.web.system.dto.user.WeiUserDTO;
import com.spr.web.system.entity.UserInfo;
import java.util.List;
import java.util.Map;

public interface IUserInfoDao extends IBaseDao<String, UserInfo> {

	UserInfoDTO getDetailByCondition(Map<String, Object> queryMap);

	Long countByCondition(Map<String, Object> queryMap);

	List<UserInfoDTO> selectListByCondition(Map<String, Object> queryMap);

	UserInfoDTO getDetailById(String id);

	List<WeiUserDTO> countByQueryMap(Map<String, Object> queryMap);
	
	UserInfo getByUserId(String userId);
}