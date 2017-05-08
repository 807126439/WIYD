package com.wb.core.spring.security.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@SuppressWarnings("serial")
public class MyUser implements UserDetails, CredentialsContainer{
	
  private String id;	
  private String password;
  private final String username;
  private String chineseName;	
  private Set<GrantedAuthority> authorities;
  private Integer limitOnceUpNum = 5; 	
  private Long limitOnceUpCapacity = 52428800l; 
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private String phone;
  private List<String> authCodes;
  private List<String> mainOrgIds;
  private List<String > roleIds;

  
  /**
   * 
   * @param username				 用户名
   * @param password				 密码
   * @param enabled					 是否可用
   * @param accountNonExpired		 用户是否无过期
   * @param credentialsNonExpired	 认证信息是否无过期
   * @param accountNonLocked		 用户是否锁
   * @param authorities				 角色权限集合
   * @param id					         用户id 
   * @param phone					 手机号码
   * @param limitOnceUpNum			 限制单次上传数量
   * @param limitOnceUpCapacity		 限制单次上传大小
   */
  public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
		    String id,String chineseName,String phone,Integer limitOnceUpNum, Long limitOnceUpCapacity,
		    List<String > roleIds,List<String> mainOrgIds){
   
	 if ((username == null) || ("".equals(username)) || (password == null)  ||  (id==null) ) {
          throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
      
	 }
	  
    this.id = id;
    this.chineseName = chineseName;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities)); 
    this.limitOnceUpNum = limitOnceUpNum == null? this.limitOnceUpNum : limitOnceUpNum;
    this.limitOnceUpCapacity = limitOnceUpCapacity == null ? this.limitOnceUpCapacity : limitOnceUpCapacity;
    this.phone  = phone;
    this.roleIds = roleIds;
    this.mainOrgIds = mainOrgIds;

  }

  public Collection<GrantedAuthority> getAuthorities(){
    return this.authorities;
  }


  public void setAuthorities( Collection<? extends GrantedAuthority> authorities) {
	this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
  }

  public String getPassword() {
    return this.password;
  }


  public String getChineseName() {
	return chineseName;
  }

  public String getUsername() {
    return this.username;
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  public void eraseCredentials() {
    this.password = null;
  }

  
@SuppressWarnings("unchecked")
 private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
    Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");

    @SuppressWarnings("rawtypes")
	SortedSet sortedAuthorities = new TreeSet(new AuthorityComparator());

    for (GrantedAuthority grantedAuthority : authorities) {
      Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
      sortedAuthorities.add(grantedAuthority);
    }

    return sortedAuthorities;
  }

  public boolean equals(Object rhs){
    if ((rhs instanceof MyUser)) {
      return this.username.equals(((MyUser)rhs).username);
    }
    return false;
  }

  public int hashCode(){
    return this.username.hashCode();
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append(": ");
    sb.append("Username: ").append(this.username).append("; ");
    sb.append("Password: [PROTECTED]; ");
    sb.append("Enabled: ").append(this.enabled).append("; ");
    sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
    sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
    sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
    boolean first;
    if (!this.authorities.isEmpty()) {
      sb.append("Granted Authorities: ");

      first = true;
      for (GrantedAuthority auth : this.authorities) {
        if (!first) {
          sb.append(",");
        }
        first = false;

        sb.append(auth);
      }
    } else {
      sb.append("Not granted any authorities");
    }

    return sb.toString();
  }

  private static class AuthorityComparator
    implements Comparator<GrantedAuthority>, Serializable{
    public int compare(GrantedAuthority g1, GrantedAuthority g2)
    {
      if (g2.getAuthority() == null) {
        return -1;
      }

      if (g1.getAuthority() == null) {
        return 1;
      }

      return g1.getAuthority().compareTo(g2.getAuthority());
    }
  }

	

  

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAuthCodes() {
		return authCodes;
	}

	public void setAuthCodes(List<String> authCodes) {
		this.authCodes = authCodes;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLimitOnceUpNum() {
		return limitOnceUpNum;
	}

	public void setLimitOnceUpNum(Integer limitOnceUpNum) {
		this.limitOnceUpNum = limitOnceUpNum;
	}

	public Long getLimitOnceUpCapacity() {
		return limitOnceUpCapacity;
	}

	public void setLimitOnceUpCapacity(Long limitOnceUpCapacity) {
		this.limitOnceUpCapacity = limitOnceUpCapacity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getMainOrgIds() {
		return mainOrgIds;
	}
	public void setMainOrgIds(List<String> mainOrgIds) {
		this.mainOrgIds = mainOrgIds;
	}

	/**
	 * 检查当前用户是否有传入的部门id（orgIds）
	 * @param orgIds
	 * @return
	 */
	public boolean checkEditerIsExist(String userIds){
		
		if(null!=userIds&&userIds.trim().length()>0){
			String[] userArray = userIds.split(",");
			for (String user : userArray) {
				if(this.id.contains(user)){
					return true;
				}
			}
			return false;
		}
		return true;
	}


	
	
	/**
	 * 检查当前用户是否有传入的部门id（orgIds）
	 * @param orgIds
	 * @return
	 */
	public boolean checkOrgsIsExist(String orgIds){
		
		if(!StringUtils.isBlank(orgIds)){
			String[] orgArray = orgIds.split(",");
			for (String org : orgArray) {
				if(this.mainOrgIds.contains(org)){
					return true;
				}
			}
			
		}
		return false;
	}
	

	
	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	
	

	
	
   
  
  
  
  
  
}
