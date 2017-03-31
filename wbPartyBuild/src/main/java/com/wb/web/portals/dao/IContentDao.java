package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.content.ContentDTO;
import com.wb.web.portals.dto.content.ContentItem;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.content.HistoryContentDTO;
import com.wb.web.portals.dto.content.InnerContentDetailDTO;
import com.wb.web.portals.dto.content.InnerShowContentDTO;
import com.wb.web.portals.dto.content.ShowContentDTO;
import com.wb.web.portals.entity.Content;

public interface IContentDao extends IBaseDao<Long, Content>{

	public Page<ContentDTO> searchEntityByPage(ContentQueryDTO queryDTO);
	
	public ContentDTO getContentDTOById(Long id);
	
	public ContentDTO getContentDTOByColumnId(Long columnId);
	
	public List<ShowContentDTO> searchContentDTOForIndex();
	
	public List<Content> getIndexContentInColumMu(Long colId,Short indexFlag);
	
	public List<Content> getIndexContentByColum(Long colId);
	
	public Long getIndexContentNumByColum(Long colId);
	
	public void updateIndexFlagByCondition(Short val,Long columId,Short range,boolean isCrossTop);
	
	public void updateIndexFlagForNewestContent(Short val,Long columId,int maxSize);
	
	public void updateIndexFlagForNewestContentInPreColumn(Short val,Long parColumId,int newestMaxSize);
	
	public void updateIndexFlagForNewestContentInPreColumn(Short val,Long parColumId,int topMaxSize,int newestMaxSize);
	
	public boolean isHasRecordInColumn(Long columnId);
	
	public Page<InnerShowContentDTO> searchEntityByCondtion(Long columnId,String title,Integer curPage,Integer pageSize, Integer days);
	
	public InnerShowContentDTO getFirstContentInColumn(Long columnId);
	
	public InnerContentDetailDTO getContentDetailById(Long id);
	
	public List<ContentItem> getPreAndNextContent(Long id,Long columnId);
	
	public List<ContentItem> getContentItemByCondition(Long columnId,Long parId);
	
	public Page<HistoryContentDTO> searchHistoryContenByPage(ContentQueryDTO queryDTO);
	
	public List<Content> getListShowTypeContent(Long topId,Short indexFlag);

	public Page<ContentDTO> searchAppContent(ContentQueryDTO queryDTO);
	
	public void delContentByActivityId(Long id);
		
}
