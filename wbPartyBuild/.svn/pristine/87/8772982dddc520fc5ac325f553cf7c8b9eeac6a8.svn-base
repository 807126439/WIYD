package com.wb.web.system.service;

import java.util.Date;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.system.dto.log.LoginCountDTO;
import com.wb.web.system.dto.log.LoginLogDTO;
import com.wb.web.system.dto.log.LoginLogQueryDTO;

public interface ILoginLogService {

	public void saveLog(LoginLogDTO dto);

	public Page<LoginCountDTO> countLoginFrequencyByPage(
			LoginLogQueryDTO queryDTO);

	public Page<LoginLogDTO> searchLoginLogByPage(LoginLogQueryDTO queryDTO);

	public void deleteLogs(Long[] ids);

	public Long searchCountsAfterDate(Date Date);

	public DownLoadDTO exportExcel();
}
