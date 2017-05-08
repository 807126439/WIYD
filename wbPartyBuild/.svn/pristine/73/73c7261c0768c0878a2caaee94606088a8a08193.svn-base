package com.wb.web.portals.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.ColumnMuDTO;
import com.wb.web.portals.dto.column.ColumnNavDTO;
import com.wb.web.portals.dto.column.ColumnZtreeDTO;
import com.wb.web.portals.dto.column.InnnerColumnDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.system.dto.user.UserDTO;

public interface IColumnMuService {
	public List<ColumnHead> searchIndexHead();

	public List<ColumnMuDTO> getAllParentEntity(Long exceptId);
	
	public List<ColumnMuDTO> getAllColumnByLevel();
	
	public ColumnMuDTO getColumnMuDTOById(Long id);
	
	public void addColmnMu(ColumnMuDTO dto);
	
	public void updateParentColumn(Long colId,Long parId);
	
	public void updateColmnMu(ColumnMuDTO dto);
	
	public void deleteColmnMu(Long[] ids);
	
	public Long countTotalNum();
	
	public InnnerColumnDTO searchForInnerShow(Long columId,Integer curPage);	
	
	public List<ColumnZtreeDTO> getAllEntityTransformTree();
	
	public List<ColumnHead> getAllBrotherColumnMu(Long id);

	public List<UserDTO> getAllEditer(Long id);

	public void saveAllowUserId(Long id, String[] userId);

	public Page<ColumnHead> searchColumnHeadByCondition(ContentQueryDTO dq);
	
	public List<ColumnNavDTO> getAllColumnNav();
}
