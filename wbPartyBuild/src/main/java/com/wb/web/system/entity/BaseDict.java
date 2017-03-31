package com.wb.web.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

/**
 * 基础数字字典对象
 * @author zjr
 *
 */

@Entity
@Table(name="c_basedict")
public class BaseDict extends UUIDEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8685217038042454087L;
	public static final int useable = 1;
	public static final int unuseable = 0;
	public static final String USER_DICT_TYPE = "user_dict";
	public static final String USER_DICT_ITEM_UP_NUM = "limit_upnum";
	public static final String USER_DICT_ITEM_UP_SIZE = "limit_upsize";
	public static final String AUTH_FLAG_DICT_TYPE = "auth_flag";
	public static final String AUTH_TYPE_DICT_TYPE = "auth_type";
	public static final String ROLE_TYPE_DICT_TYPE = "role_flag";
	public static final String CARRIER_DICT_TYPE = "file_carrier";
	public static final String CATEGORY_DICT_TYPE = "file_category";
	public static final String DEADLINE_DICT_TYPE = "file_deadline";
	public static final String LOCATE_DICT_TYPE = "file_locate";
	public static final String YEAR_TYPE = "year";
	public static final String ZONE_PATH_TYPE = "zone_path";
	public static final String FILE_TYPE_TYPE = "file_type";
	public static final String CUSTOM_MENU_LEVEL_TYPE = "menu_level";
	public static final String WATERMARK_POSITION_TYPE = "mark_position";
	public static final String CUSTOM_FIELD_TYPE = "custom_field";
	public static final String LANMU_TYPE = "lanmu";
	public static final String CONTENT_MAX_SIZE_TYPE = "content_size";
	public static final String ADS_TYPE = "ads_type";					//广告类型
	public static final String DEP_JOb_PERSON_TYPE = "djp_type";		//部门职务人员状态
	public static final String ACTIVITY_TYPE = "activity_type";  		//主题活动类型	
	public static final String OPINION_FEEDBACK_TYPE = "opinion_feedback_type";  		//意见反馈类型
	public static final String COLUMN_SHOW_TYPE = "column_show_type";  		//主题活动类型	
	public static final String ORG_TYPE = "org_type";  //部门类型
	
	
	private String dictType; 			//类别
	private String dictItem; 			//条目
	private String dictValue;			//值
	private Integer dictFlag = 1;		//有效性  1：有效   0：无效
	
	
	public BaseDict(){}
	
	public BaseDict(String dictType,String dictItem,String dictValue){
		this.dictType = dictType;
		this.dictItem = dictItem;
		this.dictValue = dictValue;
		this.dictFlag = useable;
	}
	
	@Column(name="dict_type",nullable=false)
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	
	@Column(name="dict_item",nullable=false)
	public String getDictItem() {
		return dictItem;
	}
	public void setDictItem(String dictItem) {
		this.dictItem = dictItem;
	}
	
	@Column(name="dict_value",nullable=false)
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	
	@Column(name="dict_flag",nullable=false)
	public Integer getDictFlag() {
		return dictFlag;
	}
	public void setDictFlag(Integer dictFlag) {
		this.dictFlag = dictFlag;
	}

	
	
}
