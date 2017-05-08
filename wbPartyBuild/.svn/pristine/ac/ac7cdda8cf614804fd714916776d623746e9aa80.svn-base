package com.wb.web.system.dto.zone;

import java.util.Date;

public class ZoneDTO{
	protected String id;
	private Date lastOperatorTime = new Date();
	private String zoneName;			//区名 如C E F，通过此字段查找对应的ZonePath集合
	private String zonePath;			//对应所在区的路径，用于获取对应区的容量使用
	private Long warnValue;				//警告值  当对应的区间的剩余空间容量低于警告值时则发送警告信息邮件给管理人
	private String totalCapacity;
	private String freeCapacity;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLastOperatorTime() {
		return lastOperatorTime;
	}
	public void setLastOperatorTime(Date lastOperatorTime) {
		this.lastOperatorTime = lastOperatorTime;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getZonePath() {
		return zonePath;
	}
	public void setZonePath(String zonePath) {
		this.zonePath = zonePath;
	}
	public Long getWarnValue() {
		return warnValue;
	}
	public void setWarnValue(Long warnValue) {
		this.warnValue = warnValue;
	}
	public String getTotalCapacity() {
		return totalCapacity;
	}
	public void setTotalCapacity(String totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	public String getFreeCapacity() {
		return freeCapacity;
	}
	public void setFreeCapacity(String freeCapacity) {
		this.freeCapacity = freeCapacity;
	}
	
	
	
	
	
}
