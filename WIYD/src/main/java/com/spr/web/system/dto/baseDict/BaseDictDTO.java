package com.spr.web.system.dto.baseDict;

import com.spr.core.common.dto.UUIDDTO;

public class BaseDictDTO extends UUIDDTO{

	private String dictType; 			//类别
	private String dictItem; 			//条目
	private String dictValue;			//值
	private Boolean dictFlag;		    //有效性  1：有效   0：无效

	
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
	public Boolean getDictFlag() {
		return dictFlag == null ? false:dictFlag;
	}
	public void setDictFlag(Boolean dictFlag) {
		this.dictFlag = dictFlag;
	}
	
	
	
}
