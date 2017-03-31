package com.wb.web.portals.dto.themeActivity;

import java.util.List;

import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;

public class PhotoActivity {
	
	private ThemeActivityDTO activity;					//活动信息
	private List<ActivityRuleDTO> activityRuleList;		//活动规则
	private List<AwardsSettingDTO> awardsSettingList;	//活动奖项设置
	private ThemeActivityDTO lastActivity;				//上期活动
	private Integer isAwarded;
	
	
	
	public Integer getIsAwarded() {
		return isAwarded;
	}
	public void setIsAwarded(Integer isAwarded) {
		this.isAwarded = isAwarded;
	}		
	public ThemeActivityDTO getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivityDTO activity) {
		this.activity = activity;
	}
	public List<ActivityRuleDTO> getActivityRuleList() {
		return activityRuleList;
	}
	public void setActivityRuleList(List<ActivityRuleDTO> activityRuleList) {
		this.activityRuleList = activityRuleList;
	}
	public List<AwardsSettingDTO> getAwardsSettingList() {
		return awardsSettingList;
	}
	public void setAwardsSettingList(List<AwardsSettingDTO> awardsSettingList) {
		this.awardsSettingList = awardsSettingList;
	}
	public ThemeActivityDTO getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(ThemeActivityDTO lastActivity) {
		this.lastActivity = lastActivity;
	}
	
	

}
