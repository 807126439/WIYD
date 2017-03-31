package com.wb.web.party.service;

import com.wb.core.common.bean.Page;
import com.wb.web.party.dto.JoinPartyInfoDTO;
import com.wb.web.party.dto.JoinPartyQueryDTO;
import com.wb.web.party.dto.JoinPartyStatsDTO;

public interface IJoinPartyInfoService {
	
	public Page<JoinPartyInfoDTO> searchByPage(JoinPartyQueryDTO queryDTO);
	
	public Page<JoinPartyStatsDTO> searchStatsByPage(JoinPartyQueryDTO queryDTO);
	
	public JoinPartyInfoDTO getDetailById(Long id);
	
	public void addJoinPartyInfo(JoinPartyInfoDTO dto);
	
	public void updateJoinPartyInfo(JoinPartyInfoDTO dto);
	
	public void startJoinPartyApply(Long id);
	
	public void deleteJoinPartyInfo(Long[] ids);
	
	public JoinPartyInfoDTO getJoinProcessByUserId(String userId);
	
	
}
