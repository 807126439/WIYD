package com.spr.web.system.entity;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.UUIDEntity;
import java.io.Serializable;

public class UserInfo extends UUIDEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String departName;

	private Integer score;

	private String scoreId;

	private Boolean isIgnoreRank;

	private Integer viewTime;

	private Integer beViewedTime;

	private Integer downTime;

	private Integer beDownedTime;

	private Integer recommendTime;

	private Integer beRecommendedTime;

	private Long photoId;

	private String updateBy;
	
	
	
	
	public UserInfo(){}
	
	
	
	public UserInfo(String userId, String departName,Integer score,
			String scoreId) {
		super();
		this.userId = userId;
		this.departName = departName;
		this.score = score;
		this.scoreId = scoreId;
		this.isIgnoreRank = false;
		this.viewTime = 0;
		this.beViewedTime = 0;
		this.downTime = 0;
		this.beDownedTime = 0;
		this.recommendTime = 0;
		this.beRecommendedTime = 0;

	}

	@DbField(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	@DbField(name = "depart_name")
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName == null ? null : departName.trim();
	}

	@DbField(name = "score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@DbField(name = "score_id")
	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	@DbField(name = "is_ignore_rank")
	public Boolean getIsIgnoreRank() {
		return isIgnoreRank;
	}

	public void setIsIgnoreRank(Boolean isIgnoreRank) {
		this.isIgnoreRank = isIgnoreRank;
	}
	

	
	@DbField(name = "view_time")
	public Integer getViewTime() {
		return viewTime;
	}



	public void setViewTime(Integer viewTime) {
		this.viewTime = viewTime;
	}

	@DbField(name = "be_viewed_time")
	public Integer getBeViewedTime() {
		return beViewedTime;
	}

	public void setBeViewedTime(Integer beViewedTime) {
		this.beViewedTime = beViewedTime;
	}

	@DbField(name = "down_time")
	public Integer getDownTime() {
		return downTime;
	}

	public void setDownTime(Integer downTime) {
		this.downTime = downTime;
	}

	@DbField(name = "be_downed_time")
	public Integer getBeDownedTime() {
		return beDownedTime;
	}

	public void setBeDownedTime(Integer beDownedTime) {
		this.beDownedTime = beDownedTime;
	}

	@DbField(name = "recommend_time")
	public Integer getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Integer recommendTime) {
		this.recommendTime = recommendTime;
	}

	@DbField(name = "be_recommended_time")
	public Integer getBeRecommendedTime() {
		return beRecommendedTime;
	}

	public void setBeRecommendedTime(Integer beRecommendedTime) {
		this.beRecommendedTime = beRecommendedTime;
	}

	@DbField(name = "photo_id")
	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	@DbField(name = "update_by")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(",Super = ").append(super.toString());
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append(", userId=").append(userId);
		sb.append(", departName=").append(departName);
		sb.append(", score=").append(score);
		sb.append(", isIgnoreRank=").append(isIgnoreRank);
		sb.append(", viewTime=").append(viewTime);
		sb.append(", beViewedTime=").append(beViewedTime);
		sb.append(", downTime=").append(downTime);
		sb.append(", beDownedTime=").append(beDownedTime);
		sb.append(", recommendTime=").append(recommendTime);
		sb.append(", beRecommendedTime=").append(beRecommendedTime);
		sb.append(", photoId=").append(photoId);
		sb.append(", updateBy=").append(updateBy);
		sb.append("]");
		return sb.toString();
	}
}