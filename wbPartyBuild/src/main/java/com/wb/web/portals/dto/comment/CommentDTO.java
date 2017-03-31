package com.wb.web.portals.dto.comment;

import java.util.Date;
import java.util.List;

import com.wb.core.utils.DateUtil;

public class CommentDTO {
	
	
	private Long comId; //议题ID
	private Long id;
	private Date commentTime;
	private String content;
	private String username;
	private Date updateTime;
	private Boolean isHaveChild;
	private Integer love;
	private Long parentId;
	private List<CommentDTO> childComment;
	private String targetId;
	private String userId;
	private String targetUsername;
	private Boolean loving = false;   //0-已经点赞  1-未点赞
	private Integer childCommentNums;
	private Short type;  //1-修改属性 2-点赞 3-取消点赞
	
	
	//统计评论数用

	private Date lastCommentTime;   //最后一次评论时间
	private Integer num;   //总评论数
	
	public Boolean getLoving() {
		return loving;
	}
	public void setLoving(Boolean loving) {
		this.loving = loving;
	}
	public List<CommentDTO> getChildComment() {
		return childComment;
	}
	public void setChildComment(List<CommentDTO> childComment) {
		this.childComment = childComment;
	}
	public Long getComId() {
		return comId;
	}
	public void setComId(Long comId) {
		this.comId = comId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentTimeStr() {
		return DateUtil.DateToStrByPattern(this.commentTime, "yyyy-MM-dd HH:mm");
	}
	
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateTimeStr() {
		return DateUtil.DateToStrByPattern(this.updateTime, "yyyy-MM-dd HH:mm");
	}
	
	
	
	
	public Boolean getIsHaveChild() {
		return isHaveChild;
	}
	public void setIsHaveChild(Boolean isHaveChild) {
		this.isHaveChild = isHaveChild;
	}
	public Integer getLove() {
		return love;
	}
	public void setLove(Integer love) {
		this.love = love;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTargetUsername() {
		return targetUsername;
	}
	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}
	public Integer getChildCommentNums() {
		return childCommentNums;
	}
	public void setChildCommentNums(Integer childCommentNums) {
		this.childCommentNums = childCommentNums;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Date getLastCommentTime() {
		return lastCommentTime;
	}
	public void setLastCommentTime(Date lastCommentTime) {
		this.lastCommentTime = lastCommentTime;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
		
	
	
}
