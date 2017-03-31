package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.entity.ZonePath;

public interface IZonePathDao extends IBaseDao<Long, ZonePath>{

	public List<ZonePath> searchListByTypeWithMarkASC(String type);

}
