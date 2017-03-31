package com.wb.web.portals.dto.activityVote;

public class ActivityVoteDTO {
	
	private Long id;     
	private Long activityId;
	private Long msId;        //稿件ID
	private String voter;	  //投票人
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getMsId() {
		return msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public String getVoter() {
		return voter;
	}
	public void setVoter(String voter) {
		this.voter = voter;
	}	

}
