package com.wb.web.system.dto.baseDict;

import com.wb.core.common.dto.BaseQueryDTO;

public class BaseDictQueryDTO extends BaseQueryDTO{

	private String dictType; 			//类别
	private String dictItem; 			//条目
	private Integer flag;
	
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictItem() {
		return dictItem;
	}
	public void setDictItem(String dictItem) {
		this.dictItem = dictItem;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
	
}
