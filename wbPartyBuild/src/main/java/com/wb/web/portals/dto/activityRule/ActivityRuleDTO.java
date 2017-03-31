package com.wb.web.portals.dto.activityRule;


public class ActivityRuleDTO {
	
	private Long id;
	private Long activityId;	//活动	
	private Integer num;   			//序号
	private Boolean isMain=false;         //是否未主要规则
	private String content;			//内容
	
	
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Boolean getIsMain() {
		return isMain;
	}
	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}

