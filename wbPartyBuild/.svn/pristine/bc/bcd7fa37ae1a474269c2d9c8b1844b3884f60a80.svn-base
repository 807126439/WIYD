package com.wb.web.portals.dto.themeActivity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wb.web.portals.dto.manuscript.PhotoDTO;

public class ThemeActivityDTO {
	
	private Long id;
	private String activityName; 	 //活动名称
	private String content;	 		//活动说明
	private Date startDate;			 //开始时间
	private Date endDate;			 //结束时间
	private Boolean isReward=false;	     //是否评奖
	private Boolean isOpenChunk=false;	  //是否通过版块进行投稿
	private Short activityType;		 //活动类型  1：月月精彩  2：城建人组稿  3：征文
	private String activityTypeName; //活动类型名称
	private Short status;			 //状态  0：失效  1：活跃 
	private List<PhotoDTO> manuscripts; //作品
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+08:00")  
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+08:00")  
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Boolean getIsReward() {
		return isReward;
	}
	public void setIsReward(Boolean isReward) {
		this.isReward = isReward;
	}
	public Short getActivityType() {
		return activityType;
	}
	public void setActivityType(Short activityType) {
		this.activityType = activityType;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getActivityTypeName() {
		return activityTypeName;
	}
	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}
	public Boolean getIsOpenChunk() {
		return isOpenChunk;
	}
	public void setIsOpenChunk(Boolean isOpenChunk) {
		this.isOpenChunk = isOpenChunk;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<PhotoDTO> getManuscripts() {
		return manuscripts;
	}
	public void setManuscripts(List<PhotoDTO> manuscripts) {
		this.manuscripts = manuscripts;
	}
	
	
	
}
