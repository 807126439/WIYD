package com.wb.web.portals.dto.ads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdvertisementDTO {
	
	private Long adsId;
	private String title;
	private String pattern;
	private Integer sortNum;
	private String linkUrl;
	private Date createTime;
	private Short typeId;
	private String typeName;

	
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


	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+08:00")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Short getTypeId() {
		return typeId;
	}
	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getAdsId() {
		return adsId;
	}
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}
	
	
	
	
	
}
