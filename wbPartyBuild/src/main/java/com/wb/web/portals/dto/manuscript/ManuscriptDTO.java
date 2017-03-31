package com.wb.web.portals.dto.manuscript;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ManuscriptDTO {
	
	private Long msId;
	private String title;			//标题
	private String description;		//描述
	private Short status;			//状态  0:审核不通过  1：审核通过 2：预审 3：正常(不需审核)
	private Long baseFileId;		//文件
	private Date createTime;   		//投稿时间
	private String username ;		//投稿人
	private Long banChunkId;      //所属版块
	private String banChunkName;	//所属板块名称
	private Long activityId;	//所属主题活动
	private String updateBy;
	private String pattern;
	private String author;		//作者
	private Long[] ids;
	private String filePath;	//下载地址
	private String filename;	//文件原名称
	private Long zonePathId=1L; 
	
	private Boolean loving = false;   //0-已经点赞  1-未点赞
	private Short operateType;  //1-修改属性 2-点赞 3-取消点赞
	

	
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Long getMsId() {
		return msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
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
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Long getBaseFileId() {
		return baseFileId;
	}
	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getBanChunkId() {
		return banChunkId;
	}
	public void setBanChunkId(Long banChunkId) {
		this.banChunkId = banChunkId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getZonePathId() {
		return zonePathId;
	}
	public void setZonePathId(Long zonePathId) {
		this.zonePathId = zonePathId;
	}
	public String getBanChunkName() {
		return banChunkName;
	}
	public void setBanChunkName(String banChunkName) {
		this.banChunkName = banChunkName;
	}
	public Boolean getLoving() {
		return loving;
	}
	public void setLoving(Boolean loving) {
		this.loving = loving;
	}
	public Short getOperateType() {
		return operateType;
	}
	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}
	
	
	

}
