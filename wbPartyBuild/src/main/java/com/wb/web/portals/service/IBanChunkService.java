package com.wb.web.portals.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.banChunk.BanChunkQueryDTO;
import com.wb.web.portals.dto.banChunk.BanChunkTreeDTO;

public interface IBanChunkService {
	
	
	public Page<BanChunkDTO> searchBanChunkByPage(BanChunkQueryDTO queryDTO);
	
	public BanChunkDTO getBanChunkById(String id);
	
	public long addBanChunk(BanChunkDTO dto);
	
	public void updateBanChunk(BanChunkDTO dto);
	
	public void updateMoveBanChunk(String departId,String parId);
	
	public AjaxJson deleteBanChunk(String[] ids);
	
	public List<BanChunkTreeDTO> searchBanChunkZtree(BanChunkQueryDTO queryDTO);

	public List<BanChunkDTO> getSolicitationBanChunk(Long ThemeActivityId);
	
	public List<BanChunkDTO> getPublishedBanChunk(Long ThemeActivityId);


}
