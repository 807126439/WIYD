package com.spr.core.spring.security.filter;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spr.core.spring.security.user.extend.MyUser;
import com.spr.web.system.service.IAuthorityService;




public class MyCasAuthenticationFilter extends CasAuthenticationFilter{
	@Resource
	private IAuthorityService authorityService;
	
	 
	 public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			    throws AuthenticationException, IOException{
		
		     
		Authentication authentication = super.attemptAuthentication(request, response);
			
		MyUser user = (MyUser) authentication.getPrincipal();
						
		
		if(user.getRoleIds() == null || user.getRoleIds().isEmpty()){
			return authentication;
		}
		
		String menu = this.authorityService.getAuthMenu(user.getRoleIds(),request.getContextPath());
		request.getSession().setAttribute("menu", menu);
	
		List<String> authCodes = this.authorityService.getAuthControlItem(user.getRoleIds());
		user.setAuthCodes(authCodes);
			
		
		return authentication;
    }


		

	 
	 
	 
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
}