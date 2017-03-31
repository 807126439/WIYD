package com.wb.web.study.dto.studyTask;

import java.util.Date;
import java.util.List;

import com.wb.web.study.dto.studyData.StudyDataDTO;


public class StudyTaskDTO {
	
	private Long stId;
	private Date lastOperatorTime = new Date();
	
	private String taskNum;	   //任务编号
	private String taskName;   //任务名称
	private String taskMemo;   //任务说明
	private Date startTime;    //开始时间
	private Date endTime;	   //结束时间
	private Integer status;    //任务状态  0：无效  1：有效
	private String paperName;  //试卷名称
	private Long paperid;      //试卷id
	private Long dataId;
	
	private List<StudyDataDTO> studyData;//学习资料集合
	
	private Boolean finish;    //当前用户是否已完成任务
	private String userId;
	private String dataName;
	
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/*	@JsonIgnore   可忽略字段*/
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Long getStId() {
		return stId;
	}
	public void setStId(Long stId) {
		this.stId = stId;
	}
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
	public String getTaskMemo() {
		return taskMemo;
	}
	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPaperid() {
		return paperid;
	}
	public void setPaperid(Long paperid) {
		this.paperid = paperid;
	}
	public Date getLastOperatorTime() {
		return lastOperatorTime;
	}
	public void setLastOperatorTime(Date lastOperatorTime) {
		this.lastOperatorTime = lastOperatorTime;
	}
	public Long getDataId() {
		return dataId;
	}
	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	public Boolean getFinish() {
		return finish;
	}
	public void setFinish(Boolean finish) {
		this.finish = finish;
	}
	public List<StudyDataDTO> getStudyData() {
		return studyData;
	}
	public void setStudyData(List<StudyDataDTO> studyData) {
		this.studyData = studyData;
	}
	

	
	
	
}
