package com.spr.web.system.dao;

import java.util.List;
import java.util.Map;

import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.entity.LoginLog;

public interface ILoginLogDao extends IBaseDao<Long, LoginLog>{
	
 
    
    public Long countByCondition(Map<String,Object> map);
    
    public List<LoginLog> selectListByCondition(Map<String,Object> map);

}