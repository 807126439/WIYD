package com.wb.web.portals.dto.awardWinningWorks;


public class AwardWinningWorksDTO {

	private Long awwId;				//获奖作品ID
	
	private Long activityId;		//活动ID
	private String activityName;	//活动名称
	
	private Long awardsSettingId;	//奖品ID
	private String awardsName;		//奖品名称
	private String prize; 			//奖品价格	
	private Integer amount;			//名额
	private String awardsMemo;		//奖项备注
	
	
	private Long manuscriptId;		//稿件ID
	private String manuscriptTitle;	//稿件标题
	private String manuscriptUsername;	//稿件作者名字
	
	private String comment; 
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getAwwId() {
		return awwId;
	}

	public void setAwwId(Long awwId) {
		this.awwId = awwId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Long getAwardsSettingId() {
		return awardsSettingId;
	}

	public void setAwardsSettingId(Long awardsSettingId) {
		this.awardsSettingId = awardsSettingId;
	}

	public String getAwardsName() {
		return awardsName;
	}

	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getAwardsMemo() {
		return awardsMemo;
	}

	public void setAwardsMemo(String awardsMemo) {
		this.awardsMemo = awardsMemo;
	}

	public Long getManuscriptId() {
		return manuscriptId;
	}

	public void setManuscriptId(Long manuscriptId) {
		this.manuscriptId = manuscriptId;
	}

	public String getManuscriptTitle() {
		return manuscriptTitle;
	}

	public void setManuscriptTitle(String manuscriptTitle) {
		this.manuscriptTitle = manuscriptTitle;
	}

	public String getManuscriptUsername() {
		return manuscriptUsername;
	}

	public void setManuscriptUsername(String manuscriptUsername) {
		this.manuscriptUsername = manuscriptUsername;
	}
	
	
	
	
}
