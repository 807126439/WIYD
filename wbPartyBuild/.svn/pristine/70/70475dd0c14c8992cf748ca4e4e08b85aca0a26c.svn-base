package com.wb.web.portals.dao;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.contentStat.ContentStatQueryDTO;
import com.wb.web.portals.entity.ContentStat;

public interface IContentStatDao extends IBaseDao<Long, ContentStat>{

	public ContentStat getByUserIdAndContentId(String userId, Long contentId);

	public Page<ContentStatDTO> searchContentStatByPage(
			ContentStatQueryDTO queryDTO);

}
