package com.wb.web.portals.dto.banChunk;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wb.web.portals.entity.BanChunk;


public class BanChunkDTO {
	
	private Long id;
	private String chunkName;  		//版块名称
	private String chunkMemo;  		//版块备注
	private Short status = BanChunk.CLOSE_STATUS;  	   		//状态  0：不公开  1：公开
	private Boolean isLeaf = false;	   		//是否为叶子节点	
	private Long parentId;
	private String updateBy;
	private Date lastOperatorTime = new Date();
	private Short level;					//级别:所属活动定义为0,板块为1,子版块为2
	private Integer sortNum;	   //排序号	
	private Long mainContentId;   //所属文章（父节点）
	private Long linkContentId;       //关联的文章id
	private Long activityId;
	private List<BanChunkDTO> children;
	
	
	
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getMainContentId() {
		return mainContentId;
	}
	public void setMainContentId(Long mainContentId) {
		this.mainContentId = mainContentId;
	}
	public Long getLinkContentId() {
		return linkContentId;
	}
	public void setLinkContentId(Long linkContentId) {
		this.linkContentId = linkContentId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	public Date getLastOperatorTime() {
		return lastOperatorTime;
	}
	public void setLastOperatorTime(Date lastOperatorTime) {
		this.lastOperatorTime = lastOperatorTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChunkName() {
		return chunkName;
	}
	public void setChunkName(String chunkName) {
		this.chunkName = chunkName;
	}
	public String getChunkMemo() {
		return chunkMemo;
	}
	public void setChunkMemo(String chunkMemo) {
		this.chunkMemo = chunkMemo;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public List<BanChunkDTO> getChildren() {
		return children;
	}
	public void setChildren(List<BanChunkDTO> children) {
		this.children = children;
	}
	
	
	

}
