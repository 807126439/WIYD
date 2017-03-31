package com.wb.web.portals.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.themeActivity.PhotoActivity;
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.dto.themeActivity.ThemeActivityQueryDTO;

public interface IThemeActivityService {
	
	public Page<ThemeActivityDTO> searchThemeActivityByPage(ThemeActivityQueryDTO queryDTO);
	public Page<ThemeActivityDTO> searchThemeActivityByPgae2(Integer activityType,Integer curPage,Integer pageSize);
	
	
	public ThemeActivityDTO getThemeActivityById(Long id);	
	public void addThemeActivity(ThemeActivityDTO dto);	
	public void updateThemeActivity(ThemeActivityDTO dto);	
	public void deleteThemeActivity(Long[] ids);	
	public void activateThemeActivity(Long id);
	
	public PhotoActivity getNowPhotoActivity();
	public PhotoActivity getLastPhotoActivity(Long nowActivityId);
	public PhotoActivity getRelevantMessage(Long activityId);

	public List<ThemeActivityDTO> getThemeActivityByType(Short activityType);
	public Integer countListSize(Integer activityType,Integer status);

	public ThemeActivityDTO getNowCityBuilderActivity();
	public ThemeActivityDTO getBySql(Long id);


}
