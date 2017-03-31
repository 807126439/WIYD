package com.wb.web.portals.service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.portals.dto.generalStat.GeneralStatDTO;
import com.wb.web.portals.dto.generalStat.GeneralStatQueryDTO;

public interface IGeneralStatService {

	public Page<GeneralStatDTO> searchStatByPage(GeneralStatQueryDTO queryDTO);

	public DownLoadDTO exportExcel(GeneralStatQueryDTO queryDTO);

}
