package com.wb.web.portals.dto.content;

import java.util.Date;

public class HistoryContentDTO {
	private Long id;
	private Integer sortNum;
	private String title;		//标题
	private String pattern;		//缩略图
	private String source;		//来源
	private String author;		//作者
	private Date createTime;
	private String content;		//内容
	private String historyColumn;  //历史栏目
	private Long baseFileId;
	private String viewPath;	//文件预览地址
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHistoryColumn() {
		return historyColumn;
	}
	public void setHistoryColumn(String historyColumn) {
		this.historyColumn = historyColumn;
	}
	public Long getBaseFileId() {
		return baseFileId;
	}
	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	public String getViewPath() {
		return viewPath;
	}
	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}
	
	
	
	
	
	
	
	
}
