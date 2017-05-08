package com.spr.web.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.dto.DownLoadDTO;
import com.spr.core.common.service.BaseService;
import com.spr.core.common.utils.Assert;
import com.spr.core.gobal.GobalVal;
import com.spr.web.file.dto.baseFile.SaveResult;
import com.spr.web.file.entity.ZonePath;
import com.spr.web.file.service.IBaseFileService;
import com.spr.web.system.dao.IUserInfoDao;
import com.spr.web.system.dto.user.UserInfoDTO;
import com.spr.web.system.dto.user.WeiUserDTO;
import com.spr.web.system.entity.UserInfo;
import com.spr.web.system.service.IUserInfoService;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends BaseService implements
		IUserInfoService {

	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private IBaseFileService baseFileService;


	/**
	 * 查询详细
	 * 
	 * @param dq
	 */
	@Override
	public UserInfoDTO getDetailByCondition(DataQuery dq) {
		UserInfoDTO result = this.userInfoDao.getDetailByCondition(dq
				.getQueryMap());
		return result;
	}

	// ==================================================

	/**
	 * 分页查询
	 * 
	 * @param dq
	 */
	@Override
	public Page<UserInfoDTO> searchByPage(DataQuery dq) {

		Long recTotal = this.userInfoDao.countByCondition(dq
				.assemblePageOffset().getQueryMap());
		dq.assembleOrderInfo(UserInfo.class, null);
		List<UserInfoDTO> resultlist = this.userInfoDao
				.selectListByCondition(dq.getQueryMap());

		return new Page<UserInfoDTO>(dq.getCurrentPage(), dq.getPageSize(),
				resultlist, recTotal);
	}

	/**
	 * 查询详细
	 * 
	 * @param dq
	 */
	@Override
	public UserInfoDTO getDetailById(String id) {
		Assert.hasText(id, Assert.NULL_PARAM_STR("id"));

		UserInfoDTO result = this.userInfoDao.getDetailById(id);
		Assert.notNull(result, Assert.EMPTY_REOCRD_STR);

		return result;
	}

	/**
	 * 添加用户信息
	 * 
	 * @param dto
	 */
	@Override
	public void addUserInfo(UserInfoDTO dto) {
		UserInfo model = new UserInfo(dto.getUserId(), dto.getDepartName(),
				dto.getScore(), dto.getScoreId());

		this.userInfoDao.insert(model);
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Add: " + model.toString());
		}
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 */
	@Override
	public void updateUserInfo(UserInfoDTO dto) {
		Assert.hasText(dto.getId(), Assert.NULL_PARAM_STR("id"));
		UserInfo model = this.userInfoDao.getById(dto.getId());
		Assert.notNull(model, Assert.EMPTY_REOCRD_STR);

		model.setUserId(dto.getUserId());
		model.setDepartName(dto.getDepartName());
		model.setScore(dto.getScore());
		model.setScoreId(dto.getScoreId());
		model.setIsIgnoreRank(dto.getIsIgnoreRank());

		model.setPhotoId(dto.getPhotoId());
		model.setUpdateBy(getNowUser().getUsername());
		model.setGmtModified(new Date());

		this.userInfoDao.update(model);
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Update: " + model.toString());
		}
	}


	/**
	 * 修改用户头像
	 * 
	 * @param userId
	 * @param file
	 */
	@Override
	public void changeHeadImg(String userId, CommonsMultipartFile file) {
		Assert.notNull(file, "上传文件不能为空！");
		Assert.hasText(userId, Assert.NULL_PARAM_STR("userId"));

		UserInfo model = this.userInfoDao.getByUserId(userId);

		SaveResult as = this.baseFileService.addBaseFile(file,
				ZonePath.COMMON_FILE);
		if (model.getPhotoId() != null) {
			this.baseFileService.realDeteleFile(model.getPhotoId());
		}

		model.setPhotoId(as.getId());

		this.userInfoDao.updateSelective(model);
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Update: " + model.toString());
		}

	}

	/**
	 * 删除
	 * 
	 * @param dto
	 */
	@Override
	public void deleteUserInfos(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			this.userInfoDao.deleteById(ids[i]);

			if (this.logger.isInfoEnabled()) {
				this.logger.info("Delete UserInfo id=" + ids[i]);
			}
		}
	}


	@Override
	public List<WeiUserDTO> countByQueryMap(Map<String, Object> queryMap,boolean queryHeadImg) {

		List<WeiUserDTO> result = this.userInfoDao.countByQueryMap(queryMap);
		if(queryHeadImg){
			for (WeiUserDTO user : result) {											
				if(user.getPhotoId()!=null){
					DownLoadDTO  fileInfo = this.baseFileService.getViewFileInfo(user.getPhotoId());
					user.setHeadImgUrl(fileInfo.getFilePath());
				}
			}
		}
		
		return this.userInfoDao.countByQueryMap(queryMap);
	}


}
