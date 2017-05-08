package com.spr.web.system.entity;

import java.io.Serializable;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.UUIDEntity;

public class Role extends UUIDEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 285113260417583942L;
	
	public static final short  TYPE_FRONT = 1;
	public static final short  TYPE_BACK = 0;
	
	private String roleName;

    private String roleText; 

    private String roleMemo;

    private Short flag;

    private Short roleType;
    
    public Role(){}

    public Role(String roleName, String roleText, String roleMemo,Short roleType) {
		super();
		this.roleName = roleName;
		this.roleText = roleText;
		this.roleMemo = roleMemo;
		this.flag = 0;
		this.roleType = roleType;
	}

    @DbField(name="role_name")
	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @DbField(name="role_text")
    public String getRoleText() {
        return roleText;
    }

    public void setRoleText(String roleText) {
        this.roleText = roleText == null ? null : roleText.trim();
    }

    @DbField(name="role_memo")
    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo == null ? null : roleMemo.trim();
    }

    @DbField(name="flag")
    public Short getFlag() {
        return flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }
    
    @DbField(name="role_type")
	public Short getRoleType() {
		return roleType;
	}

	public void setRoleType(Short roleType) {
		this.roleType = roleType;
	}

  
    
    
}