package com.wb.web.portals.dto.awardsSetting;


public class AwardsSettingDTO {
	
	private Long asId;
	private Long activityId;
	private String awardsName;		//奖项名称	
	private String memo;
	private String prize;
	private Integer amount;			//名额
	private Long baseFileId;
	private String pattern;			//图片地址
	private Long zonePathId=1L;
	private Integer amountleft;		//剩余名额
	private int sortNum;			//排序号
	
	public Long getAsId() {
		return asId;
	}
	public void setAsId(Long asId) {
		this.asId = asId;                                                    
	}

	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public Long getBaseFileId() {
		return baseFileId;
	}
	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Long getZonePathId() {
		return zonePathId;
	}
	public void setZonePathId(Long zonePathId) {
		this.zonePathId = zonePathId;
	}
	public Integer getAmountleft() {
		return amountleft;
	}
	public void setAmountleft(Integer amountleft) {
		this.amountleft = amountleft;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
		
}
