package com.wb.web.portals.dto.contentStat;

import java.util.Date;

public class ContentStatDTO {
	
	private Integer sort;       //序号
	
	private String userId; 		//用户
	private Date readDate;     	//阅读日期
	private Long contentId;		//阅读的文章id	
	private Long spendTime;		//花费时间（以秒为单位）
	private String memo;  		//备注
	
	private String userName;	//用户名
	
	private Integer articleNum; //规定时间段内阅读了的文章数量
	
	
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
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public Long getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(Long spendTime) {
		this.spendTime = spendTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
