package com.spr.web.system.dao;

import java.util.List;
import java.util.Map;

import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.entity.BaseDict;


public interface IBaseDictDao extends IBaseDao<String, BaseDict>{



      
    public Long countByCondition(Map<String, Object> queryMap);
    
    public List<BaseDict> selectListByCondition(Map<String, Object> queryMap);
}