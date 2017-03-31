package com.wb.web.portals.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
/**
 * 访问统计记录
 * @author Java2
 *
 */
@Entity
@Table(name="c_access_record")
public class AccessRecord extends BaseEntity implements Serializable{

	private Long totalAccess;		//登录访问的累计次数
	private Long todayAccess;		//当天的累计次数
	
	
	public AccessRecord() {
		super();
	}
	public AccessRecord(Long totalAccess, Long todayAccess) {
		super();
		this.totalAccess=totalAccess;
		this.todayAccess=todayAccess;
		
	}
	@Column(name="total_access",length=50)
	public Long getTotalAccess() {
		return totalAccess;
	}
	public void setTotalAccess(Long totalAccess) {
		this.totalAccess = totalAccess;
	}
	@Column(name="today_access",length=50)
	public Long getTodayAccess() {
		return todayAccess;
	}
	public void setTodayAccess(Long todayAccess) {
		this.todayAccess = todayAccess;
	}
	
	
	
	
}
