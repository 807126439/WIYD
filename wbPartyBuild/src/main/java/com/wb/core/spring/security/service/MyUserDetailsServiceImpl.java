package com.wb.core.spring.security.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.spring.security.user.MyUser;
import com.wb.web.system.dao.IJobDepartRelationDao;
import com.wb.web.system.dao.IRoleDao;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.entity.Role;
import com.wb.web.system.entity.User;


@SuppressWarnings("deprecation")
@Transactional(readOnly=true)
public class MyUserDetailsServiceImpl implements UserDetailsService {
	@Resource
	private IUserDao userDao;
	@Resource
    private IRoleDao roleDao;
	@Resource
	private IJobDepartRelationDao jobDepartRelationDao;
	

	public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException {
		
		try {
			username = URLDecoder.decode(username, "utf-8");			
			System.out.println(username);
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
		}
	
	    User user = userDao.getUniqueByProperty("userName", username);		
		if(user == null){	
			throw new UsernameNotFoundException("不存在此用户！"); 			
		}
		
				
		List<Role> roleList = this.roleDao.getRolesByUserId(user.getId());
		List<String> roleIds = new ArrayList<String>();
		List<String> departIds = this.jobDepartRelationDao.getUserMainDepart(user.getId());
     	return new MyUser(
		     			user.getUserName(), 
		     			user.getPassword(),
		     			user.getEnabled(), 
		     			user.getAccountNonExpired(), 
		     			user.getCredentialsNonExpired(), 
		     			user.getAccountNonLocked(),
		     			getGrantedAuthorities(roleList,roleIds), 
		     			user.getId(),
		     			user.getChineseName(),
		     			user.getPhone(),
		     			user.getLimitOnceUpNum(),
		     			user.getLimitOnceUpCapacity(),
		     			roleIds,departIds);
					
	}
	
	
	
	//用于获取用户的权限
	public  Collection<GrantedAuthority> getGrantedAuthorities(List<Role> roleList,List<String> roleIds){
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();						
	    		  
		for (Role rs : roleList) {		
			authorities.add(new GrantedAuthorityImpl(rs.getRoleName()));
			roleIds.add(rs.getId());
		}
					
		return authorities;
	}
	
	




	public static String getRoleStrsFromGrantedAuthority(Collection<GrantedAuthority> authorities){
	    StringBuilder sb = new StringBuilder();
		for (GrantedAuthority authority : authorities) {
			sb.append(authority.getAuthority()+",");
		}
		
		return sb.toString();
	}
	
}
