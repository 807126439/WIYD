package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.manuscript.ManuscriptDTO;
import com.wb.web.portals.dto.manuscript.ManuscriptQueryDTO;
import com.wb.web.portals.dto.manuscript.PhotoDTO;
import com.wb.web.portals.entity.Manuscript;

public interface IManuscriptDao extends IBaseDao<Long,Manuscript>{
	
	public Page<ManuscriptDTO> searchManuscriptByPage(ManuscriptQueryDTO queryDTO);
	public Page<PhotoDTO> queryPhotosByPage(ManuscriptQueryDTO queryDTO);
	public List<PhotoDTO> getCheckedPhotosByActivityId(Long activityId);
	public List<Manuscript> getAllManuscriptByActivityId(long activityId);
	public List<Manuscript> delAwardWinningManuscriptByActivityId(List<Manuscript> mlist,long activityId);	
	public void deleteVote(Long msId,String userId);
	public void deleteVote(Long msId);

}
