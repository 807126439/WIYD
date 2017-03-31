package com.wb.web.portals.dto.ads;

public class IndexAdsDTO {

	private String title;
	private String pattern;
	private String bigPattern;
	private Long baseFileId;
	private Integer sortNum;
	private String linkUrl;
	private Short typeId;

	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Short getTypeId() {
		return typeId;
	}
	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}
	public Long getBaseFileId() {
		return baseFileId;
	}
	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	public String getBigPattern() {
		return bigPattern;
	}
	public void setBigPattern(String bigPattern) {
		this.bigPattern = bigPattern;
	}
	
	
}
