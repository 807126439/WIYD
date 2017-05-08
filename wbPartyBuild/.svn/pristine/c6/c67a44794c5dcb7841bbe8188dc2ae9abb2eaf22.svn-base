package com.wb.web.system.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;

public interface IBaseDictService {

	public List<BaseDictDTO> searchListByCondition(BaseDictQueryDTO queryDTO);
	
	public Page<BaseDictDTO> searchBaseDictByPage(BaseDictQueryDTO queryDTO);
	
	public BaseDictDTO getDictById(String id);
	
	public void addDict(BaseDictDTO dto);
	
    public void updateDict(BaseDictDTO dto);
    
    public void deleteDicts(String[] ids);
}
