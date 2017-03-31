package com.wb.web.portals.dto.content;

import java.util.Date;

public class ShowContentDTO {

	private Long id;				//文章id
	private String ctTitlePrefix;	//标题前缀
	private String ctTitle;			//文章标题
	private Short ctIndexFlag;
	private String pattern;
	private Date createTime;		//发布时间	
	private String changgTime;		//发布时间	
	private Long clId;				//栏目id
	private String clTitle;			//栏目名称
	private Integer clIndexNum;		//栏目首页序号
	private Integer clSortNum;		//栏目排序号
	private Boolean clIsIndex;		//栏目是否置于首页
	private Long clParId;			//父栏目id
	private String clParITitle;		//父栏目名称
	private Integer clParIIndexNum; //父栏目首页序号
	private Short showType;			//父栏目显示属性
	
	private Boolean isNew = false;  //是否新的
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCtTitlePrefix() {
		return ctTitlePrefix;
	}
	public void setCtTitlePrefix(String ctTitlePrefix) {
		this.ctTitlePrefix = ctTitlePrefix;
	}
	public String getCtTitle() {
		return ctTitle;
	}
	public void setCtTitle(String ctTitle) {
		this.ctTitle = ctTitle;
	}
		
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Short getCtIndexFlag() {
		return ctIndexFlag;
	}
	public void setCtIndexFlag(Short ctIndexFlag) {
		this.ctIndexFlag = ctIndexFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
		
	public String getChanggTime() {
		return changgTime;
	}
	public void setChanggTime(String changgTime) {
		this.changgTime = changgTime;
	}
	public Long getClId() {
		return clId;
	}
	public void setClId(Long clId) {
		this.clId = clId;
	}
	public String getClTitle() {
		return clTitle;
	}
	public void setClTitle(String clTitle) {
		this.clTitle = clTitle;
	}
	public Integer getClIndexNum() {
		return clIndexNum;
	}
	public void setClIndexNum(Integer clIndexNum) {
		this.clIndexNum = clIndexNum;
	}

	public Boolean getClIsIndex() {
		return clIsIndex;
	}
	public void setClIsIndex(Boolean clIsIndex) {
		this.clIsIndex = clIsIndex;
	}
	public Integer getClSortNum() {
		return clSortNum;
	}
	public void setClSortNum(Integer clSortNum) {
		this.clSortNum = clSortNum;
	}
	public Long getClParId() {
		return clParId;
	}
	public void setClParId(Long clParId) {
		this.clParId = clParId;
	}
	public String getClParITitle() {
		return clParITitle;
	}
	public void setClParITitle(String clParITitle) {
		this.clParITitle = clParITitle;
	}
	public Integer getClParIIndexNum() {
		return clParIIndexNum;
	}
	public void setClParIIndexNum(Integer clParIIndexNum) {
		this.clParIIndexNum = clParIIndexNum;
	}
	public Short getShowType() {
		return showType;
	}
	public void setShowType(Short showType) {
		this.showType = showType;
	}
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	
	
	
	
	
}
