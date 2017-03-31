package com.wb.web.portals.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.column.IndexItem;
import com.wb.web.portals.dto.content.ContentDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.content.HistoryContentDTO;
import com.wb.web.portals.dto.content.InnerContentDetailDTO;
import com.wb.web.portals.dto.content.InnerShowContentDTO;

public interface IContentService {
	
	public Page<ContentDTO> searchEntityByPage(ContentQueryDTO queryDTO);
	
	public ContentDTO getContentDTOById(Long id,boolean isFormColumn);
	
	public void addMagazineContent(Long parContentId,CommonsMultipartFile uploadFile);
	
	public void addImgContent(Long columnId,CommonsMultipartFile uploadFile);
	
	public void addContent(ContentDTO dto,CommonsMultipartFile uploadFile);
	
	public void updateContentSortNum(Long id,Integer sortNum);
	
	public void updateContent(ContentDTO dto,CommonsMultipartFile uploadFile);
	
	public void deleteContent(Long[] ids);
	
	public Long countTotalNum();
		
	public List<IndexItem> searchContentForIndex();
		
	public InnerContentDetailDTO getInnerContentDetailById(Long id);
	
	public void updateContentColumn(Long[] ids,Long colId);
	
	public Page<HistoryContentDTO> searchHistoryContentByPage(ContentQueryDTO queryDTO);
	
	public void updateRecoverContent(Long[] ids,Long columId);
	
	public void deleteContentWithShift(Long[] ids);
	
	public Page<InnerShowContentDTO> searchContentIndex(Long columnId,String title,Integer curPage,Integer pageSize, Integer days);

	public void saveContent(Long banChunkId, Long contentId);

	public void cutContentRelationship(Long banChunkId);

	public String getSeeOrgName(Long cid);

	public Integer getAPP_CONTENT_SIZE();

	public void saveAppContentNum(Integer contentNum);

	public void sumbitToTop(Long[] ids);

	public List<ContentDTO> getAppContent(ContentQueryDTO queryDTO);

	public Page<ContentDTO> searchAppEntityByPage(ContentQueryDTO queryDTO);

	public void cancleToTop(Long[] ids);
	
	
}
