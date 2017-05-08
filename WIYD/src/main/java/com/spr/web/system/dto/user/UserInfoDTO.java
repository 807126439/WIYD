package com.spr.web.system.dto.user;

import com.spr.core.common.dto.UUIDDTO;

public class UserInfoDTO extends UUIDDTO {

	private String userId;

	private String departName;

	private int score;

	private String scoreId;

	private Boolean isIgnoreRank;

	private int viewTime;

	private int beViewedTime;

	private int downTime;

	private int beDownedTime;

	private int recommendTime;

	private int beRecommendedTime;

	private Long photoId;

	private String updateBy;

	public UserInfoDTO() {
	}

	public UserInfoDTO(String userId, String departName, String scoreId,
			int score) {
		super();
		this.userId = userId;
		this.departName = departName;
		this.scoreId = scoreId;
		this.score = score;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [userId=" + userId + ", departName=" + departName
				+ ", score=" + score + ", scoreId=" + scoreId
				+ ", isIgnoreRank=" + isIgnoreRank + ", viewTime=" + viewTime
				+ ", beViewedTime=" + beViewedTime + ", downTime=" + downTime
				+ ", beDownedTime=" + beDownedTime + ", recommendTime="
				+ recommendTime + ", beRecommendedTime=" + beRecommendedTime
				+ ", photoId=" + photoId + ", updateBy=" + updateBy
				+ ", getId()=" + getId() + ", getGmtModified()="
				+ getGmtModified() + ", getSort()=" + getSort()
				+ ", getGmtCreate()=" + getGmtCreate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName == null ? null : departName.trim();
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public Boolean getIsIgnoreRank() {
		return isIgnoreRank == null ? false : isIgnoreRank;
	}

	public void setIsIgnoreRank(Boolean isIgnoreRank) {
		this.isIgnoreRank = isIgnoreRank;
	}

	public Integer getViewTime() {
		return viewTime;
	}

	public void setViewTime(Integer viewTime) {
		this.viewTime = viewTime;
	}

	public Integer getBeViewedTime() {
		return beViewedTime;
	}

	public void setBeViewedTime(Integer beViewedTime) {
		this.beViewedTime = beViewedTime;
	}

	public Integer getDownTime() {
		return downTime;
	}

	public void setDownTime(Integer downTime) {
		this.downTime = downTime;
	}

	public Integer getBeDownedTime() {
		return beDownedTime;
	}

	public void setBeDownedTime(Integer beDownedTime) {
		this.beDownedTime = beDownedTime;
	}

	public Integer getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Integer recommendTime) {
		this.recommendTime = recommendTime;
	}

	public Integer getBeRecommendedTime() {
		return beRecommendedTime;
	}

	public void setBeRecommendedTime(Integer beRecommendedTime) {
		this.beRecommendedTime = beRecommendedTime;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}
}