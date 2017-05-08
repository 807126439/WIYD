package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.entity.BaseDict;

public interface IBaseDictDao extends IBaseDao<Long, BaseDict>{

	public Page<BaseDict> searchBaseDictByPage(BaseDictQueryDTO queryDTO);
	
	public List<BaseDict> searcheDcitListByCondition(BaseDictQueryDTO queryDTO);
	
	public List<BaseDict> searcheDcitListByCondition(String dictType,String dictItem,Integer dictFlag);
	
	public BaseDict searchOneDictByCondition(String dictType,String dictItem,Integer dictFlag);
	
	public BaseDict searchOneDictByCondition2(String dictType,String dictValue,Integer dictFlag);
	
	public String getDictVal(String dictType,String dictItem,Integer dictFlag);
}
