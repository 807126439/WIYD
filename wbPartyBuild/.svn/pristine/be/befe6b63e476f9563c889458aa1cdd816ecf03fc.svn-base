package com.wb.core.spring.security.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.spring.security.user.MyUser;
import com.wb.web.system.service.IAuthorityService;





/** 
 * 该过滤器的主要作用就是通过spring的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceImpl。
 * 该MyInvocationSecurityMetadataSourceImpl作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 */
@Transactional
public class MyFilterSecurityInterceptorImpl extends AbstractSecurityInterceptor implements Filter {
	 //myInvocationSecurityMetadataSource是一个权限资源获取器
	 private SecurityMetadataSource securityMetadataSource;
	 @Resource
	 private IAuthorityService authorityService; 
	 
	  //拦截请求
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			  		throws IOException, ServletException{
	   
	   FilterInvocation fi = new FilterInvocation( request, response, chain);
	   invoke(fi);
	   
	  }
	  
	  
	  public Class<? extends Object> getSecureObjectClass(){
		   return FilterInvocation.class;
	  }
	 
	  
	  //用户具体的拦截操作
	  public void invoke(FilterInvocation fi) throws IOException, ServletException{
	   //beforeInvocation(fi)就是用于调用资源权限获取器和访问决策器的对应方法，进行权限的拦截。
		  

	   Authentication nowAuthentication = SecurityContextHolder.getContext().getAuthentication();	
	   boolean isAuthenticated = nowAuthentication.isAuthenticated();
	   
	   InterceptorStatusToken  token = super.beforeInvocation(fi);
	   
	    /*如果用户没有验证，则重新验证且获取权限菜单和权限码*/  
	   if(!isAuthenticated){			   
	   
		    MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					    
		    String roleList = MyUserDetailsServiceImpl.getRoleStrsFromGrantedAuthority(user.getAuthorities());
		    HttpServletRequest request = fi.getRequest();
			String menu = this.authorityService.getAuthMenu(roleList,request.getContextPath());
			request.getSession().setAttribute("menu", menu);
		
			List<String> authCodes = this.authorityService.getAuthControlItem(roleList);
			user.setAuthCodes(authCodes);
		
			System.out.println("-------重新验证-------");
	   }
	   
	   
	   
	    try{
	    	fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
	    	
	    }finally{
	    	super.afterInvocation(token, null);
	    }
	   
	  }
	   
	  

	  public SecurityMetadataSource obtainSecurityMetadataSource(){
		  return this.securityMetadataSource;
	  }
	  
	
		  
	  public void setSecurityMetadataSource(SecurityMetadataSource securityMetadataSource){
		  this.securityMetadataSource = securityMetadataSource;
	  }
	  
	  //销毁
	   public void destroy(){
	   
	  }
	  //初始化
	   public void init( FilterConfig filterconfig ) throws ServletException{
	   
	  }
	  
	  

}
