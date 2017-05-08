package com.wb.web.system.dto.zone;

import java.io.File;

import com.wb.core.common.dto.BaseDto;
import com.wb.web.system.entity.ZonePath;

public class ZonePathDTO extends BaseDto{

	private String zoneName;			  //区名	
	private Integer mark;                 //优先级
	private String type;				  //类型	
	private String path;				  //路径	
	private String virtualPath;			  //虚拟路径	
	private Long warmValue;				  //警告值	
	private String useCapacity;
	
	
	
	public ZonePathDTO(){}
	
	
	public ZonePathDTO(Long id,String zoneName, Integer mark, String path,
			String virtualPath) {
		setId(id);
		this.zoneName = zoneName;
		this.mark = mark;
		this.path = path;
		this.virtualPath = virtualPath;

	}
	
	
	
	public ZonePathDTO(String zoneName, Integer mark, String type, String path,
			String virtualPath, Long warmValue) {
		super();
		this.zoneName = zoneName;
		this.mark = mark;
		this.type = type;
		this.path = path;
		this.virtualPath = virtualPath;
		this.warmValue = warmValue;
	}
	
	
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVirtualPath() {
		return virtualPath;
	}
	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	public Long getWarmValue() {
		return warmValue;
	}
	public void setWarmValue(Long warmValue) {
		this.warmValue = warmValue;
	}
	
	
	public String getUseCapacity() {
		return useCapacity;
	}
	public void setUseCapacity(String useCapacity) {
		this.useCapacity = useCapacity;
	}
	/**
	 * 下载路径
	 * @return
	 */
	public String obtainDownLoadHead(){
		return this.path + File.separator;
	}
	
	/**
	 * swf或图片压缩路径
	 * @return
	 */
	public String obtainCompressPath(){
		return this.path +ZonePath.Compress + File.separator;
	}
	
	/**
	 * 压缩图片预览路径
	 * @return
	 */
	public String obtainViewHead(){
		return this.virtualPath;
	}
	
}
