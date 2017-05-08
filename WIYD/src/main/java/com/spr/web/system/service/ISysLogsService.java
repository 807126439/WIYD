package com.spr.web.system.service;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.web.system.dto.log.SysLogsDTO;

public interface ISysLogsService {

	 Page<SysLogsDTO> searchByPage(DataQuery dq);
	
	 SysLogsDTO getDetailById(Long id);
	
	 void deleteSysLogss(Long[] ids);

}
