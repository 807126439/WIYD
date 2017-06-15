package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.communication.CommunicationDTO;
import com.wb.web.portals.dto.communication.CommunicationQueryDTO;
import com.wb.web.portals.entity.Communication;

public interface ICommunicationDao extends IBaseDao<Long, Communication>{
	
		public Page<CommunicationDTO> searchCommunicationByPage(CommunicationQueryDTO queryDTO);	
		public List<CommunicationDTO> getPreAndNextFiledById(Long id);
		public List<CommunicationDTO> getListByCondition(CommunicationQueryDTO queryDTO);
		public void deleteVote(Long comId,String userId);
		public int countTotalSize();	
		public CommunicationDTO getCommunicationBySql(Long id);
}
