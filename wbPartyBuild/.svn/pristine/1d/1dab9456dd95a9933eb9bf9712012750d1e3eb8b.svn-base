package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

@Entity
@Table(name="c_authority")
public class Authority extends UUIDEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 301692903470652043L;
	public final static short flag_openControl = 1;
	public final static short flag_normal = 0;
	public final static short auth_acess = 1;
	public final static short auth_menu = 0;
	public final static short auth_button = 2;	
	
	private String authText;			//权限名
	private String authCode;		    //权限码	
	private String resourecesUrl;		//资源地址
	private Authority parent;			//父权限
	private Set<Authority> children = new HashSet<Authority>();     //子权限
	private Set<Role> roles = new HashSet<Role>();					//对应的角色（多对多）
	private Short flag; 				//状态         0：普通状态  1：开启前台控制显示
	private Short authType; 			//权限类型  0：菜单  1：访问 2 :按钮（菜单用于控制后台菜单）
	private Integer authOrder; 			//排序
	private String icon;            	//图案

	
	
	
	public Authority() {}
	
	public Authority(String id) {
		setId(id);
	}
	

	@Column(name="auth_text",length=50,nullable=false,unique=true)
	public String getAuthText() {
		return authText;
	}
	public void setAuthText(String authText) {
		this.authText = authText;
	}
	
	@Column(name="auth_code",length=50,nullable=false,unique=true)
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	@Column(name="resoureces_url")
	public String getResourecesUrl() {
		return resourecesUrl;
	}
	public void setResourecesUrl(String resourecesUrl) {
		this.resourecesUrl = resourecesUrl;
	}
	
	@ManyToOne(targetEntity=Authority.class)
	@JoinColumn(name="parent_id")    
	//@Fetch(FetchMode.JOIN)
	public Authority getParent() {
		return parent;
	}
	
	 
	public void setParent(Authority parent) {
		this.parent = parent;
	}
	
	@OneToMany(targetEntity=Authority.class)
	@JoinColumn(name="parent_id")  
	public Set<Authority> getChildren() {
		return children;
	}
	public void setChildren(Set<Authority> children) {
		this.children = children;
	}
	
	@ManyToMany(targetEntity=Role.class)
	@JoinTable(name="c_authority_role",joinColumns={@JoinColumn(name="auth_id")},/*联接表里代表自己的数据库字段名*/
	   inverseJoinColumns={@JoinColumn(name="role_id")})/*联接表里代表对方的数据库字段名*/
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Column(name="flag")
	public Short getFlag() {
		return flag;
	}
	public void setFlag(Short flag) {
		this.flag = flag;
	}
	
	@Column(name="auth_type")
	public Short getAuthType() {
		return authType;
	}
	public void setAuthType(Short authType) {
		this.authType = authType;
	}
	
	@Column(name="auth_order")
	public Integer getAuthOrder() {
		return authOrder;
	}
	public void setAuthOrder(Integer authOrder) {
		this.authOrder = authOrder;
	}
	
	@Column(name="icon")
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	
	
	
	
	
	
}
