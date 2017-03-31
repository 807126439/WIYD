package com.wb.web.base.dao;

import com.wb.core.common.dao.IBaseDao;
import com.wb.web.base.entity.BaseFile;

public interface IBaseFileDao extends IBaseDao<Long, BaseFile>{

	public String getBaseFilePath(Long id);
	
	public String getFileViewPath(Long id);
}