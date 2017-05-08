package com.wb.web.portals.service;

import com.wb.web.portals.dto.accessRecord.AccessRecordDTO;

public interface IAccessRecordService {
	
	//public AccessRecordDTO getAccessRecord();
	/**
	 * 访问递增1，包括累计访问量与当天访问量，自行判断为新一天清除昨天记录
	 */
	public void accessIncreas();
	
	public AccessRecordDTO getLastRecord();
}
