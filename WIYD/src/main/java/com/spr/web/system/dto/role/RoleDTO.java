package com.spr.web.system.dto.role;

import com.fasterxml.jackson.annotation.JsonView;
import com.spr.core.common.dto.UUIDDTO;

public class RoleDTO extends UUIDDTO{
	public static interface SelectView {}
	

	private String roleName;			//角色名
    private String roleText;			//角色备注
    private String roleMemo;			//角色描述
    private Integer flag;				//角色标志
	private Boolean check = false;   	//选中记录
	private Short roleType;
    
	@JsonView(SelectView.class)
    public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@JsonView(SelectView.class)
	public String getRoleText() {
		return roleText;
	}
	public void setRoleText(String roleText) {
		this.roleText = roleText;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	@JsonView(SelectView.class)
	public String getRoleMemo() {
		return roleMemo;
	}
	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public Short getRoleType() {
		return roleType;
	}
	public void setRoleType(Short roleType) {
		this.roleType = roleType;
	}
	
	
    
    
    
}
