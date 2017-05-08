package com.wb.web.portals.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.communication.CommunicationDTO;
import com.wb.web.portals.dto.communication.CommunicationQueryDTO;
import com.wb.web.portals.dto.communication.FiledDTO;

public interface ICommunicationService {
	
	public Page<CommunicationDTO> searchCommunicationByPage(CommunicationQueryDTO queryDTO);
	
	public CommunicationDTO getCommunicationById(Long id);
	
	public void addCommunication(CommunicationDTO dto);
	
	public void updateCommunication(CommunicationDTO dto);
	
	public void deleteCommunication(Long[] ids);
		
	public CommunicationDTO getCurent(Long id);
	
	public List<CommunicationDTO> getListByCondition(CommunicationQueryDTO queryDTO);
	
	public FiledDTO getFiledByid(Long id);
	
	public int countTotalSize();
	
	public Page<CommunicationDTO> getAppPage(CommunicationQueryDTO queryDTO);
	
	

	
	
}
