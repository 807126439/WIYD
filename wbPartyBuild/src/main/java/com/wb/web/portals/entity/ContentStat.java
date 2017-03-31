package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;


/**
 * 阅读文章统计表
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="cy_content_stat")
public class ContentStat extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2498307323005851586L;
	
	private String userId; 		//用户
	private Date readDate;     	//阅读日期
	private Long contentId;		//阅读的文章id	
	private Long spendTime;		//花费时间（以秒为单位）
	private String memo;  		//备注
	private Integer readTimes;	//阅读次数
	
	
	
	@Column(name="user_id",nullable=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="read_date",nullable=false)
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
	@Column(name="content_id",nullable=false)
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	
	@Column(name="spend_time")
	public Long getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(Long spendTime) {
		this.spendTime = spendTime;
	}
	
	@Column(name="memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name="readTimes")
	public Integer getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
	
	
	
	

}
