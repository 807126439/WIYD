package com.wb.web.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

/**
 * 分区
 * @author zjr
 *
 */

@Entity
@Table(name="c_zone")
public class Zone extends UUIDEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7140432670036093986L;
	private String zoneName;			//区名 如C E F，通过此字段查找对应的ZonePath集合
	private String path;			//对应所在区的路径，用于获取对应区的容量使用
	private Long warnValue;				//警告值  当对应的区间的剩余空间容量低于警告值时则发送警告信息邮件给管理人
	
	
	@Column(name="zone_name",nullable=false,length=1)
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {		
		this.zoneName = zoneName;
	}
	
	@Column(name="path",nullable=false)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	@Column(name="warn_value",nullable=false)
	public Long getWarnValue() {
		return warnValue;
	}
	public void setWarnValue(Long warnValue) {
		this.warnValue = warnValue;
	}

	
	
	
	
	
}
