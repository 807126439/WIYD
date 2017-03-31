package com.wb.web.portals.service;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.awardWinningWorks.AwardMessage;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksDTO;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksQueryDTO;



public interface IAwardWinningWorksService {
	
	public AwardWinningWorksDTO getAwardWinningWorksById(Long id);
	
	public void addAwardWinningWorks(AwardWinningWorksDTO dto);
	
	public void updateAwardWinningWorks(AwardWinningWorksDTO dto);
	
	public void deleteAwardWinningWorks(Long[] ids);

	public Page<AwardWinningWorksDTO> searchAwardWinningWorksByPage(AwardWinningWorksQueryDTO queryDTO);

	public AwardMessage getAwardMessage(Long activityId);
	public Integer checkIsAward(Long activityId);
	
};
