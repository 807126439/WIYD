package com.wb.web.study.dto.examScore;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExamScoreDTO {
	
	private Long id ;
	private String taskName;
	private String paperName;
	private Long taskId;
	private Long paperId;
	private Long spendTime;    	  //所用时间（以秒为单位）
	private Integer score; 
	private String usetime;
	private String username;
	private Date updateTime;
	private String userId;
	
	
	private String deptName;
	private String parentDeptName;
	
	
	
	
	public ExamScoreDTO() {
		super();
	}
	public ExamScoreDTO(Long id, String taskName, String paperName,
			Long taskId, Long paperId, Long spendTime, Integer score,
			String usetime, String username, Date updateTime) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.paperName = paperName;
		this.taskId = taskId;
		this.paperId = paperId;
		this.spendTime = spendTime;
		this.score = score;
		this.usetime = usetime;
		this.username = username;
		this.updateTime = updateTime;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public Long getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(Long spendTime) {
		this.spendTime = spendTime;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getParentDeptName() {
		return parentDeptName;
	}
	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}
	
	
}
