package com.spr.core.spring.security.user;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.spr.core.spring.security.user.extend.MyUser;

public class UserBO extends MyUser{

		
	 /**
	 * 
	 */
	private static final long serialVersionUID = -770844264548674026L;

	private String headImgUrl;   
	
	
	 public UserBO(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			Set<String> roleIds, String id, Short userType, String chineseName,
		String headImgUrl) {
		 
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities, roleIds, id, userType, chineseName);
		
		

		this.headImgUrl = headImgUrl;
		
	}




	public String getHeadImgUrl() {
		return headImgUrl;
	}


	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	 
	 
	 

}
