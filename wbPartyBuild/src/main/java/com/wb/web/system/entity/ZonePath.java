package com.wb.web.system.entity;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.core.utils.FileOperation;

/**
 * 分区路径
 * @author zjr
 *
 */

@Entity
@Table(name="c_zone_path")
public class ZonePath extends BaseEntity implements Serializable{
  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1324555665542332891L;	
	public static final String Compress = "Compress";
	public static final String COMMON_FILE = "common-file";
	public static final String PUBLIC_FILE = "public";
	public static final String TEMP_FILE = "temp";
	
	private String zoneName;			  //区名	
	private Integer mark;                 //优先级
	private String type;				  //类型	
	private String path;				  //路径	
	private String virtualPath;			  //虚拟路径	
	private Long warmValue;				  //警告值	
	
	
	@Column(name="zone_name",nullable=false,length=1)
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	@Column(name="mark",nullable=false)
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
	@Column(name="path",nullable=false)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name="type",nullable=false,length=20)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	@Column(name="warm_value")
	public Long getWarmValue() {
		return warmValue;
	}
	public void setWarmValue(Long warmValue) {
		this.warmValue = warmValue;
	}
	
	@Column(name="virtual_path")
	public String getVirtualPath() {
		return virtualPath;
	}
	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	

	
	/**
	 * 下载路径
	 * @return
	 */
	public String obtainDownLoadHead(){
		return FileOperation.filterPath(this.path);
	}
	
	/**
	 * swf或图片压缩路径
	 * @return
	 */
	public String obtainCompressPath(){
		return this.path + Compress + File.separator;
	}
	
	/**
	 * 压缩图片预览路径
	 * @return
	 */
	public String obtainViewHead(){
		return this.virtualPath;
	}
	
	
	
}
