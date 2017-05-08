package com.wb.web.system.service;

import java.util.Map;

import com.wb.core.common.bean.Page;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.dto.zone.ZonePathQueryDTO;
import com.wb.web.system.entity.ZonePath;

public interface IZonePathService {

	public ZonePathDTO getZonePathById(Long id);
	
	public ZonePathDTO getUseableZone(String type);
	public ZonePath getUseableZone2(String type);
	
	public Page<ZonePathDTO> searchZonePathByPagging(ZonePathQueryDTO queryDTO);
	
	public void saveZonePath(ZonePathDTO dto,ZonePathQueryDTO queryDTO);
	
	public void updateZonePath(ZonePathDTO dto);
	
	public void deleteZonePath(ZonePathDTO dto);
	
	public String getTempFileWholePath(String fileType);
	
	public String viewCache(Long zpId,Map<Long, String> cache);
	
	
}
