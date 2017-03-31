package com.wb.web.system.dto.baseDict;

import com.wb.core.common.dto.UUIDDto;

public class BaseDictDTO extends UUIDDto{

	private String dictType; 			//类别
	private String dictItem; 			//条目
	private String dictValue;			//值
	private Integer dictFlag;		//有效性  1：有效   0：无效
	
	
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
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public Integer getDictFlag() {
		return dictFlag;
	}
	public void setDictFlag(Integer dictFlag) {
		this.dictFlag = dictFlag;
	}
	
	
	
}
