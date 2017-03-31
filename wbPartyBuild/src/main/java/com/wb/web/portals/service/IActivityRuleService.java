package com.wb.web.portals.service;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.activityRule.ActivityRuleQueryDTO;



public interface IActivityRuleService {
	
	
	
	public ActivityRuleDTO getActivityRuleById(Long id);
	
	public void addActivityRule(ActivityRuleDTO dto);
	
	public void updateActivityRule(ActivityRuleDTO dto);
	
	public void deleteActivityRule(Long[] ids);
	
	public Page<ActivityRuleDTO> searchActivityRuleByPage(ActivityRuleQueryDTO queryDTO);
 
}
