package com.wb.web.portals.dto.communication;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wb.core.common.dto.BaseQueryDTO;

public class CommunicationDTO  extends BaseQueryDTO{
	
	private Long id;
	private String title;       //议题名称
	private String content;     //议题简述	
	private String sponsor;     //发起人
	private Short status;       //状态    活跃-1 归档-2	
	private Date startDate;     //开始时间
	private Date endDate;       //开始时间
	private Long leftDays;      //剩余天数	
	private Short isLove = 1;      //是否点赞    0-是 1-否
	private Integer love;	        //点赞数
	private Integer commentNums;    //评论总数	
	private Short type;        //更新类型   1-修改状态  2-修改属性  3-点赞  4-取消点赞
	private Long videoId;
	private String uuid;
	private String videoName;
	private String videoPath;
	
	public Integer getCommentNums() {
		return commentNums;
	}
	public void setCommentNums(Integer commentNums) {
		this.commentNums = commentNums;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+08:00")  
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+08:00")  
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getLeftDays() {
		return leftDays;
	}
	public void setLeftDays(Long leftDays) {
		this.leftDays = leftDays;
	}
	public Short getIsLove() {
		return isLove;
	}
	public void setIsLove(Short isLove) {
		this.isLove = isLove;
	}
	public Integer getLove() {
		return love;
	}
	public void setLove(Integer love) {
		this.love = love;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	
		
}