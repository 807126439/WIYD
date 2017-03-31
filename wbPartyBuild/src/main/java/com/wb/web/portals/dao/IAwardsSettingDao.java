package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.awardWinningWorks.AwardDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingQueryDTO;
import com.wb.web.portals.entity.AwardsSetting;

public interface IAwardsSettingDao extends IBaseDao<Long,AwardsSetting>{

	public Page<AwardsSettingDTO> searchEntityByPage2(AwardsSettingQueryDTO dto);
	
	public List<AwardsSettingDTO> getAllAwardsSettingByActivityId(Long id);
	public List<AwardDTO> getAllAwardByActivityId(Long activityId);			
	public List<AwardsSettingDTO> delAssignedAwardsSettingByActivityId(List<AwardsSettingDTO> aslist,Long activityId);
	
	public void delASByActivityId(Long activityId);
		
}
