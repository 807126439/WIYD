package com.spr.web.system.dto.user;

import java.util.Date;

import com.spr.core.common.dto.UUIDDTO;

public class UserDTO extends UUIDDTO {
  

	private String userName;
    private String chineseName;
    private String email;				//邮箱地址
    private Short userType; 			//用户类型 
    private String phone;				//联系电话/手机号码
    private Date gmtCreate;			//创建时间
    private String memo;				//备注
    private Boolean enabled;
    
	private String departName;
	private Integer score;
	private String scoreId;
	private String scoreName;
	private Boolean isIgnoreRank;
	private Long photoId;
	private String headImgUrl; //头像地址
	
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Short getUserType() {
		return userType;
	}
	public void setUserType(Short userType) {
		this.userType = userType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
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
	public String getScoreName() {
		return scoreName;
	}
	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}
	public Boolean getIsIgnoreRank() {
		return isIgnoreRank;
	}
	public void setIsIgnoreRank(Boolean isIgnoreRank) {
		this.isIgnoreRank = isIgnoreRank;
	}
	public Long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

   
    
 
	
    
    
    
    
    
    
    
    

}
