package com.wb.web.portals.dto.awardWinningWorks;


public class AwardDTO2 {
	
	private Long id;
	private String title;			//标题
	private String author;
	private String description;		//描述
	private String pattern;
	private String comment;
	private Long zonePathId=1L;
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getZonePathId() {
		return zonePathId;
	}
	public void setZonePathId(Long zonePathId) {
		this.zonePathId = zonePathId;
	}
	
	
}
