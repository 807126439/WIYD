package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.activityRule.ActivityRuleQueryDTO;
import com.wb.web.portals.entity.ActivityRule;

public interface IActivityRuleDao extends IBaseDao<Long,ActivityRule>{
	
	public Page<ActivityRule> searchActivityRuleByPage2(ActivityRuleQueryDTO queryDTO);
			
	public List<ActivityRuleDTO> getAllRulesByActivityId(Long activityId);
	
	public void delARByActivityId(Long activityId);
		
}
