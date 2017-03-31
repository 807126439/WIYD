package com.wb.web.portals.dto.themeActivity;

import com.wb.core.common.dto.BaseQueryDTO;

public class ThemeActivityQueryDTO extends BaseQueryDTO{
	private String activityName;
	private Short activityType;
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Short getActivityType() {
		return activityType;
	}
	public void setActivityType(Short activityType) {
		this.activityType = activityType;
	}	

}
