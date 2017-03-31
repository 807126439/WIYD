package com.wb.web.portals.dao;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.generalStat.GeneralStatDTO;
import com.wb.web.portals.dto.generalStat.GeneralStatQueryDTO;

public interface IGeneralStatDao {

	public Page<GeneralStatDTO> searchStatByPage(GeneralStatQueryDTO queryDTO);

}
