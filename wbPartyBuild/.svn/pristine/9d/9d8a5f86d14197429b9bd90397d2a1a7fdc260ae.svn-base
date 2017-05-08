package com.wb.web.portals.service;


import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingQueryDTO;



public interface IAwardsSettingService {
	
	
	public Page<AwardsSettingDTO> searchAwardsSettingByPage(AwardsSettingQueryDTO queryDTO);
	
	public AwardsSettingDTO getAwardsSettingById(Long id);
	
	public void addAwardsSetting(AwardsSettingDTO dto,CommonsMultipartFile file);
	
	public void updateAwardsSetting(AwardsSettingDTO dto,CommonsMultipartFile file);
	
	public void deleteAwardsSetting(Long[] ids);
	
	
	public Page<AwardsSettingDTO> searchEntityByPage(AwardsSettingQueryDTO queryDTO);
	
	public List<AwardsSettingDTO> getAwardsSettingsByActivityId(Long id);
}
