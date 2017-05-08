package com.spr.web.system.entity;

import java.io.Serializable;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.UUIDEntity;

/**
 * 1.只有authType为auth_acess或auth_button的数据才会纳入权限管理
 *   auth_menu的不会纳入权限管理，充当一个链接的作用
 * 2.flag=1的记录开启对页面的元素的权限控制
 * 
 * @author wb_java_zjr
 *
 */
public class Authority extends UUIDEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2035792886208959436L;
	
	public final static short flag_openControl = 1;
	public final static short flag_normal = 0;
	
	public final static short auth_menu = 0;
	public final static short auth_acess = 1;
	public final static short auth_button = 2;	
		

    private String authCode;		
    private String authText;	
    private String resourecesUrl;
    private Short authType;
    private Short flag;
    private String icon;
    private Integer authOrder;
    private String parentId;
  

    
    
    public Authority(){}
    
 

    public Authority(String authCode, String authText, String resourecesUrl,
			Short authType, Short flag, String icon, Integer authOrder,
			String parentId) {
		super();
		this.authCode = authCode;
		this.authText = authText;
		this.resourecesUrl = resourecesUrl;
		this.authType = authType;
		this.flag = flag;
		this.icon = icon;
		this.authOrder = authOrder;
		this.parentId = parentId;
	}

	@DbField(name="auth_code")
	public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }
    
	@DbField(name="auth_text")
    public String getAuthText() {
        return authText;
    }

    public void setAuthText(String authText) {
        this.authText = authText == null ? null : authText.trim();
    }

    @DbField(name="resoureces_url")
    public String getResourecesUrl() {
        return resourecesUrl;
    }

    public void setResourecesUrl(String resourecesUrl) {
        this.resourecesUrl = resourecesUrl == null ? null : resourecesUrl.trim();
    }

    @DbField(name="auth_type")
    public Short getAuthType() {
        return authType;
    }

    public void setAuthType(Short authType) {
        this.authType = authType;
    }

    @DbField(name="flag")
    public Short getFlag() {
        return flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    @DbField(name="icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    @DbField(name="auth_order")
    public Integer getAuthOrder() {
        return authOrder;
    }

    public void setAuthOrder(Integer authOrder) {
        this.authOrder = authOrder;
    }

    @DbField(name="parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

   
    
    
    
    
    
}