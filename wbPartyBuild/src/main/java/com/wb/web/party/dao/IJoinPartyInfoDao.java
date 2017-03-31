package com.wb.web.party.dao;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.party.dto.JoinPartyInfoDTO;
import com.wb.web.party.dto.JoinPartyQueryDTO;
import com.wb.web.party.dto.JoinPartyStatsDTO;
import com.wb.web.party.entity.JoinPartyInfo;

public interface IJoinPartyInfoDao extends IBaseDao<Long, JoinPartyInfo>{
	
	public  Page<JoinPartyInfoDTO> searchInfoByPage(JoinPartyQueryDTO queryDTO);
	
	public JoinPartyInfoDTO getDetailById(Long id);
	
	public JoinPartyInfoDTO getDetailByApplyUserId(String userId);
	
	public Page<JoinPartyStatsDTO> searchStatsByPage(JoinPartyQueryDTO queryDTO);
	
	public JoinPartyInfo getByProcInstId(Long procInstId);
}
