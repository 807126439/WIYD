package com.wb.web.system.dto.role;

import com.wb.core.common.dto.UUIDDto;

public class RoleDTO extends UUIDDto{

	private String roleName;			//角色名
    private String roleText;			//角色备注
    private String roleMemo;			//角色描述
    private Integer flag;				//角色标志
	private Boolean check;   //选中记录
    
    public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
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
	
	
    
    
    
}
