package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.ColumnMuDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.column.ColumnNavDTO;
import com.wb.web.portals.entity.ColumnMu;

public interface IColumnMuDao extends IBaseDao<Long, ColumnMu>{

	public List<ColumnMuDTO> findColumnMuDTOByLevel(Short level,Long exceptId);
	
	public ColumnMuDTO getColumnMuDTOById(Long id);
	
	public Long countTotalNum();
	
	public List<ColumnMu> getTopEntityWithLimit(Long parId,int maxSize);
	
	public void updateIsIndexByCondition(Boolean val,Long parId,Boolean isIndex);
	
	public boolean checkIsHasChildIndex(Long parId);
	
	public List<ColumnHead> searchIndexHead(Long parId);
	
	public List<ColumnHead> searchColumnHeadByParIds(List<Long> ids);
	
	public List<ColumnHead> searchIndexColum();
	
	public boolean checkIsHasChildByLevel(Integer level,Long parId);
	
	public void updateColumnLevelByCondition(short level,Long parId,boolean isCross);
	
	public List<Long> getTabColumnsIds(Long parId,Integer size);

	public List<ColumnMu> getAllBrotherColumnMu(Long id);

	public Page<ColumnHead> searchColumnHeadByCondition(ContentQueryDTO dq);
	
	public List<ColumnNavDTO> searchColumnNavDTO();
}
