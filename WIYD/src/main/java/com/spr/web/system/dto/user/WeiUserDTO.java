package com.spr.web.system.dto.user;

public class WeiUserDTO {

	private String id;
	private String userName;
	private String chineseName;
	private String departId;

	private int score; // 积分
	private String gradeName; // 等级名称
	private String loginTimes; // 连续登陆次数
	private String loginGrade; // 连续登陆等级
	private String gradeIcon; // 连续登陆等级
	private int rank; // 用户排名

	private Long photoId;
	private String headImgUrl; // 头像地址

	@Override
	public String toString() {
		return "WeiUserDTO [id=" + id + ", userName=" + userName
				+ ", chineseName=" + chineseName + ", departId=" + departId
				+ ", score=" + score + ", gradeName=" + gradeName
				+ ", loginTimes=" + loginTimes + ", loginGrade=" + loginGrade
				+ ", gradeIcon=" + gradeIcon + ", rank=" + rank + ", photoId="
				+ photoId + ", headImgUrl=" + headImgUrl + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public String getGradeIcon() {
		return gradeIcon;
	}

	public void setGradeIcon(String gradeIcon) {
		this.gradeIcon = gradeIcon;
	}

	public String getLoginGrade() {
		return loginGrade;
	}

	public void setLoginGrade(String loginGrade) {
		this.loginGrade = loginGrade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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
