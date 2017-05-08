package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;


/**
 * 广告
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="cy_ads")
public class Advertisement extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4179028590971183719L;
	public static final short TYPE_LUNBO = 0; 
	public static final short TYPE_SINGLETON = 1; 
	public static final short TYPE_FRIEND_LINK = 2;
	private String title;
	private String pattern;
	private Integer sortNum;
	private String linkUrl;
	private Date createTime;
	private Short typeId;
	private Long baseFileId;
	
	
	public Advertisement(){}
	
	
	public Advertisement(String title,Integer sortNum,
			String linkUrl, Date createTime, Short typeId) {
		super();
		this.title = title;
		this.sortNum = sortNum;
		this.linkUrl = linkUrl;
		this.createTime = createTime;
		this.typeId = typeId;
	}
	
	
	@Column(name="title",length=80)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="pattern",length=80)
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Column(name="sort_num",nullable=false)
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Column(name="link_url",length=500)
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="type_id",nullable=false)
	public Short getTypeId() {
		return typeId;
	}
	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

	@Column(name="base_file_id")
	public Long getBaseFileId() {
		return baseFileId;
	}


	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	
	
	
	
	
}
