package com.wb.web.portals.dto.manuscript;

import java.util.Date;


public class PhotoDTO {
	
	private Long id;
	private Short status;
	private String title;		
	private String description;
	private String username;
	private String pattern;
	private String bigPattern;
	private Integer width;
	private Integer height;
	private Integer love = 0;
	private Boolean loveing =false;
	private String author;	
	private Long zonePathId=1L;
	private Date uploadTime;
	
	
	public String getAuthor() {
		return author;
	}	
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBigPattern() {
		return bigPattern;
	}
	public void setBigPattern(String bigPattern) {
		this.bigPattern = bigPattern;
	}
	public Long getId() {
		return id;
	}
	public Long getZonePathId() {
		return zonePathId;
	}
	public void setZonePathId(Long zonePathId) {
		this.zonePathId = zonePathId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getLove() {
		return love;
	}
	public void setLove(Integer love) {
		this.love = love;
	}
	public Boolean getLoveing() {
		return loveing;
	}
	public void setLoveing(Boolean loveing) {
		this.loveing = loveing;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	

}
