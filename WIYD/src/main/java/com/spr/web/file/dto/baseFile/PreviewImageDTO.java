package com.spr.web.file.dto.baseFile;

import com.spr.core.common.dto.BaseDTO;

public class PreviewImageDTO extends BaseDTO{
	private String title;
	
	private String pattern;//预览图片路径

	private Long zonePathId;
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	
	
	
	
}
