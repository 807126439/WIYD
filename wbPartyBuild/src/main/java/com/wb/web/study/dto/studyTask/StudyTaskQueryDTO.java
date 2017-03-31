package com.wb.web.study.dto.studyTask;

import com.wb.core.common.dto.BaseQueryDTO;

public class StudyTaskQueryDTO extends BaseQueryDTO{
	
	private String taskNum;	   //任务编号
	private String taskName;   //任务名称
	private String userId;     //用户Id
	
	
	public String getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
