package com.wb.web.portals.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.manuscript.ManuscriptDTO;
import com.wb.web.portals.dto.manuscript.ManuscriptQueryDTO;
import com.wb.web.portals.dto.manuscript.PhotoDTO;



public interface IManuscriptService {
		
	public void addManuscript(ManuscriptDTO dto,CommonsMultipartFile uploadFile);
	
	public void updateManuscript(ManuscriptDTO dto,CommonsMultipartFile uploadFile);
	
	public void deleteManuscript(Long[] ids);
	
	public void checkManuscript(ManuscriptDTO dto);

	public ManuscriptDTO getManuscriptById(Long id);
	
	public Page<ManuscriptDTO> searchManuscriptByPage(ManuscriptQueryDTO queryDTO);
	
	public Page<PhotoDTO> queryPhotosByPage(ManuscriptQueryDTO queryDTO);
	
	public List<PhotoDTO> getCheckedPhotosByActivityId(Long activityId);
	
	public List<ManuscriptDTO> getAllManuscriptByActivityId(long activityId);
	
	public void setVoteMessage(List<PhotoDTO> photos);
	
	public void setPhotoViewPath(List<PhotoDTO> photos);

}
