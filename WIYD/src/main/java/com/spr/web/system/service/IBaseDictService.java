package com.spr.web.system.service;

import java.util.List;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.web.system.dto.baseDict.BaseDictDTO;

public interface IBaseDictService {

	public List<BaseDictDTO> searchListByCondition(DataQuery dq);
	
	public BaseDictDTO getByCondition(DataQuery dq);
	
	public Page<BaseDictDTO> searchBaseDictByPage(DataQuery dq);
	
	public BaseDictDTO getDictById(String id);
	
	public void addDict(BaseDictDTO dto);
	
    public void updateDict(BaseDictDTO dto);
    
    public void deleteDicts(String[] ids);
}
