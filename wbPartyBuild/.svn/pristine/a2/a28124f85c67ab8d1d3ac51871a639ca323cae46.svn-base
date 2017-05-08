package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.dto.themeActivity.ThemeActivityQueryDTO;
import com.wb.web.portals.entity.ThemeActivity;

public interface IThemeActivityDao extends IBaseDao<Long,ThemeActivity>{
		
	public Page<ThemeActivity> searchThemeActivityByPage(ThemeActivityQueryDTO queryDto);
	
	public Page<ThemeActivityDTO> searchThemeActivityByPgae2(Integer activityType,Integer curPage,Integer pageSize);
			
	
	public List<ThemeActivity> searchByFild(String fildName,Object Object);
	
	public ThemeActivityDTO getActivePhotoActivity();
	public ThemeActivityDTO getLastPhotoActivity(Long nowId);
	public Integer countListSize(Integer activityType,Integer status);
	public ThemeActivityDTO getBySql(Long id);
		
}
