package com.spr.web.system.entity;

import java.io.Serializable;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.UUIDEntity;

/**
 * 基础数字字典
 * @author wb_java_zjr
 *
 */
public class BaseDict extends UUIDEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1390712608278314943L;
	public static final int useable = 1;
	public static final int unuseable = 0;
	public static final String USER_DICT_TYPE = "user_dict";
	public static final String USER_DICT_ITEM_UP_NUM = "limit_upnum";
	public static final String USER_DICT_ITEM_UP_SIZE = "limit_upsize";
	public static final String AUTH_FLAG_DICT_TYPE = "auth_flag";
	public static final String AUTH_TYPE_DICT_TYPE = "auth_type";
	public static final String ROLE_TYPE_DICT_TYPE = "role_flag";	
	public static final String FILE_TYPE = "file_type"; //文件类型
	public static final String USER_FRONT_TYPE = "user_front_type"; //用户类型
	public static final String USER_BACK_TYPE = "user_back_type"; //用户类型
	public static final String ROLE_TYPE = "role_type"; //用户类型
	public static final String SYS_CONFIG_TYPE = "sys_config";
	

	
    private String dictItem;
    private String dictType;
    private String dictValue;
    private Boolean dictFlag;


    public BaseDict(){}

    public BaseDict(String dictItem, String dictType, String dictValue,
			Boolean dictFlag) {
		super();
		this.dictItem = dictItem;
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.dictFlag = dictFlag;
	}

    @DbField(name="dict_item")
	public String getDictItem() {
        return dictItem;
    }

    public void setDictItem(String dictItem) {
        this.dictItem = dictItem == null ? null : dictItem.trim();
    }

    @DbField(name="dict_type")
    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    @DbField(name="dict_value")
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    @DbField(name="dict_flag")
    public Boolean getDictFlag() {
        return dictFlag;
    }

    public void setDictFlag(Boolean dictFlag) {
        this.dictFlag = dictFlag;
    }

  
}