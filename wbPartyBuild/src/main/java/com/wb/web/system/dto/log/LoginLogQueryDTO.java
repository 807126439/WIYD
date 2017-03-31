package com.wb.web.system.dto.log;

import com.wb.core.common.dto.BaseQueryDTO;

public class LoginLogQueryDTO extends BaseQueryDTO {

	private String userName;// 用户名
	private String beginTime;// 起始时间
	private String endTime;// 终止时间
	private String departmentIds;// 所属部门ID

	@Override
	public String toString() {
		return "LoginLogQueryDTO [userName=" + userName + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", departmentIds="
				+ departmentIds + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

}
