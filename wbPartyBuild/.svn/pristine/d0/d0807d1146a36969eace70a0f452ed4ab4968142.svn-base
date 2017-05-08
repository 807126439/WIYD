package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.awardWinningWorks.AwardDTO2;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksQueryDTO;
import com.wb.web.portals.entity.AwardWinningWorks;

public interface IAwardWinningWorksDao extends IBaseDao<Long, AwardWinningWorks>{

	/**
	 * 重写分页查询
	 */
	Page<AwardWinningWorks> searchAwardWinningWorksByPage(
			AwardWinningWorksQueryDTO queryDTO);
	
	public List<AwardWinningWorks> searchAwardWinningWorksByActivityId(Long id);
	
	public List<AwardDTO2> getAwardMessage(Long activityId,Long awardSettingId);
	
	public Integer checkIsAward(Long activityId);
		
	public void delAWWByActivityId(Long activityId);	
}
