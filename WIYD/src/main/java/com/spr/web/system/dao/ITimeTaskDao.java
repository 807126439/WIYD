package com.spr.web.system.dao;

import com.spr.core.common.bean.CommonParam;
import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.entity.TimeTask;

public interface ITimeTaskDao extends IBaseDao<String, TimeTask>{
   
	 public TimeTask getUniqueByProperty(CommonParam cp);
}