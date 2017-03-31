package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.banChunk.BanChunkQueryDTO;
import com.wb.web.portals.dto.banChunk.BanChunkTreeDTO;
import com.wb.web.portals.entity.BanChunk;
import com.wb.web.portals.entity.ThemeActivity;

public interface IBanChunkDao extends IBaseDao<Long, BanChunk>{

	public List<BanChunk> searchTreeByCondition(BanChunkQueryDTO queryDTO);
	
	public Page<BanChunk> searchBanChunkByPage(BanChunkQueryDTO queryDTO);

	public boolean isLeaf(long id);

	public List<BanChunk> getSolicitationBanChunk(Long themeActivityId);

	public List<BanChunk> getPublishedBanChunk(Long themeActivityId);
	
	public void delBanChunkByActivityId(Long id);

}
