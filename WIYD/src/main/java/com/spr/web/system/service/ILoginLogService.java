package com.spr.web.system.service;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.web.system.dto.log.LoginLogDTO;

public interface ILoginLogService {

	public void saveLog(LoginLogDTO dto);
	
	public Page<LoginLogDTO> searchLoginLogByPage(DataQuery dq);
	
	public void deleteLogs(Long[] ids);
}
