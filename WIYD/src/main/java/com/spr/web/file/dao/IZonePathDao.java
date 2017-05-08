package com.spr.web.file.dao;

import java.util.List;
import java.util.Map;

import com.spr.core.common.dao.IBaseDao;
import com.spr.web.file.entity.ZonePath;

public interface IZonePathDao extends IBaseDao<Long, ZonePath>{

       
    /**
     * 根据类型查询对象
     * @param type
     * @return
     */
    public List<ZonePath> selectListByType(String type);  
    
    public Long countByCondition(Map<String, Object> queryMap);
    
    public List<ZonePath> selectListByCondition(Map<String, Object> queryMap);
}