package com.spr.web.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spr.web.system.dto.user.UserInfoDTO;
import com.spr.web.system.dto.user.WeiUserDTO;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;

public interface IUserInfoService {


	UserInfoDTO getDetailByCondition(DataQuery dq);

	Page<UserInfoDTO> searchByPage(DataQuery dq);

	UserInfoDTO getDetailById(String id);

	void addUserInfo(UserInfoDTO dto);

	void updateUserInfo(UserInfoDTO dto);

	void deleteUserInfos(String[] ids);

	public List<WeiUserDTO> countByQueryMap(Map<String, Object> queryMap,boolean queryHeadImg);


	void changeHeadImg(String userId, CommonsMultipartFile file);

}
