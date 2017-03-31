package com.wb.web.portals.dto.opinionfeedback;

import java.util.Date;
import com.wb.core.common.dto.BaseQueryDTO;

public class OpinionFeedbackQueryDto extends BaseQueryDTO{


	private Date beginTime;			//开始时间	
	private Date endTime;			//结束时间
	private int typeid;				//类别
	private String departmentIds;   //所属部门ID
	private String userName;		//用户名
	
	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
	
	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OpinionFeedbackQueryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	



	
}
