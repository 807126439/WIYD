package com.wb.web.portals.dto.generalStat;

import java.util.Date;

public class GeneralStatDTO {
	
	private Integer sort;       			//序号
	
	private String userId; 					//用户
	
	private String userName;				//用户名
	
	private Integer loginNum; 				// 登录次数
	
	private Date loginDate; 				// 最近一次登录时间
	
	private Integer t1feedbackNum;			//合理化意见次数
	
	private Integer t2feedbackNum;			//智慧党建意见次数
	
	private Short feedbackTypeId = 0;		//评论类型   1：合理化建议征集 2：对智慧党建版块意见
	
	private Integer articleNum; 			//规定时间段内阅读了的文章数量
	
	private Integer examNum;				//测试次数
	
	private Integer communicationNum;		//评论次数
	
	private Integer feelingNum;				//心得体会次数

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}


	public Short getFeedbackTypeId() {
		return feedbackTypeId;
	}

	public void setFeedbackTypeId(Short feedbackTypeId) {
		this.feedbackTypeId = feedbackTypeId;
	}

	public Integer getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}

	public Integer getExamNum() {
		return examNum;
	}

	public void setExamNum(Integer examNum) {
		this.examNum = examNum;
	}

	public Integer getCommunicationNum() {
		return communicationNum;
	}

	public void setCommunicationNum(Integer communicationNum) {
		this.communicationNum = communicationNum;
	}
	
	

	public Integer getFeelingNum() {
		return feelingNum;
	}

	public void setFeelingNum(Integer feelingNum) {
		this.feelingNum = feelingNum;
	}

	public Integer getT1feedbackNum() {
		return t1feedbackNum;
	}

	public void setT1feedbackNum(Integer t1feedbackNum) {
		this.t1feedbackNum = t1feedbackNum;
	}

	public Integer getT2feedbackNum() {
		return t2feedbackNum;
	}

	public void setT2feedbackNum(Integer t2feedbackNum) {
		this.t2feedbackNum = t2feedbackNum;
	}
	
	

}
