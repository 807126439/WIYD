package com.wb.web.system.dao;

import java.util.Date;
import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.log.LoginCountDTO;
import com.wb.web.system.dto.log.LoginLogDTO;
import com.wb.web.system.dto.log.LoginLogQueryDTO;
import com.wb.web.system.entity.LoginLog;

public interface ILoginLogDao extends IBaseDao<Long, LoginLog> {

	public List<LoginCountDTO> findAllForExport();

	public Page<LoginCountDTO> countLoginFrequencyByPage(
			LoginLogQueryDTO queryDTO);

	public Page<LoginLogDTO> searchLoginLogByPage(LoginLogQueryDTO queryDTO);

	public Long searchCountsAfterDate(Date date);
}
