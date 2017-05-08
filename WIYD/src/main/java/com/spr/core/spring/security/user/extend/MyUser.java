package com.spr.core.spring.security.user.extend;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;


public class MyUser implements UserDetails, CredentialsContainer{
	
  /**
	 * 
	 */
  private static final long serialVersionUID = -3354399068329198889L;
  
  private String id;	
  private String password;
  private final String username;
  private Set<GrantedAuthority> authorities;
  private Set<String> roleIds;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private Short userType;
  private String chineseName;

  private List<String> authCodes;


  
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
   */
  public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
		  Set<String> roleIds,String id,Short userType,String chineseName){
   
	 if ((username == null) || ("".equals(username)) || (password == null)  ||  (id==null) ) {
          throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
      
	 }
	  
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities)); 
    this.roleIds = roleIds;
    this.userType = userType;
    this.chineseName = chineseName;


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

	

  


	public List<String> getAuthCodes() {
		return authCodes;
	}

	public void setAuthCodes(List<String> authCodes) {
		this.authCodes = authCodes;
	}


	public Set<String> getRoleIds() {
		return roleIds;
	}

	public String getId() {
		return id;
	}

	public Short getUserType() {
		return userType;
	}

	public String getChineseName() {
		return chineseName;
	}


	
	
	
	


	

	
	

	
	
   
  
  
  
  
  
}
