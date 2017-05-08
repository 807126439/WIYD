package com.wb.web.system.dto.zone;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.wb.core.common.dto.BaseQueryDTO;

public class ZonePathQueryDTO extends BaseQueryDTO{

	private Long zoneId;
	private String zoneName;
	
	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	
	public ZonePathQueryDTO wrapQueryVal(){
		this.queryMap = new HashMap<String, Object>();
		
		if(!StringUtils.isBlank(zoneName)){
			this.queryMap.put("zoneName", zoneName);
		}
		
	
		
		return this;
	}
	
}
