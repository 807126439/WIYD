package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

@Entity
@Table(name="c_role")
public class Role extends UUIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3140837280203284278L;
    private String roleName;				//角色码(ROLE_XXX)
    private String roleText;				//角色名
    private String roleMemo;				//角色描述
    private Integer flag;					//角色标志
    private Set<User> users = new HashSet<User>();//关联的用户
    private Set<Authority> authorities = new HashSet<Authority>();//关联的权限
    
    
    public Role() {}
    
    public Role(String id) {
    	setId(id);
    }
    
	@Column(name="role_name",length=50,nullable=false,unique=true)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name="role_text",length=50,nullable=false,unique=true)
	public String getRoleText() {
		return roleText;
	}
	public void setRoleText(String roleText) {
		this.roleText = roleText;
	}
	
    @Column(name="role_memo")
	public String getRoleMemo() {
		return roleMemo;
	}
	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	
	@Column(name="flag")
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="c_user_role",joinColumns={@JoinColumn(name="role_id")},
	   inverseJoinColumns={@JoinColumn(name="user_id")})
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


	
	@ManyToMany(targetEntity=Authority.class)
	@JoinTable(name="c_authority_role",joinColumns={@JoinColumn(name="role_id")},
	   inverseJoinColumns={@JoinColumn(name="auth_id")})
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleText=" + roleText
				+ ", roleMemo=" + roleMemo + ", flag=" + flag + ", users="
				+ users + ", authorities=" + authorities + "]";
	}


    
    
    
    
    
    
    
    
    

}
