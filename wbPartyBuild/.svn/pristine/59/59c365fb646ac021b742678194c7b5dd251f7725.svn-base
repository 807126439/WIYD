package com.wb.core.spring.security.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;



/** 
 *AccessdecisionManager在Spring security中是很重要的。
 *
 *在验证部分简略提过了，所有的Authentication实现需要保存在一个GrantedAuthority对象数组中。 
 *这就是赋予给主体的权限。 GrantedAuthority对象通过AuthenticationManager
 *保存到 Authentication对象里，然后从AccessDecisionManager读出来，进行授权判断。 
 *
 *Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。 
 *一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。 
 *这个 AccessDecisionManager 被AbstractSecurityInterceptor调用，
 */


public class MyAccessDecisionManagerImpl implements AccessDecisionManager {
	   
	
		//判断用户是否拥有访问权限
		public void decide(Authentication authentication, Object secureObject,
				Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
				InsufficientAuthenticationException {
			//请求资源对应的判断权限是否为空，如果则可以访问
			if (null == configAttributes) {   
				return;   
			} 
			
			Iterator<ConfigAttribute> cons = configAttributes.iterator();  
			
			 while(cons.hasNext()){
				 ConfigAttribute conf = cons.next(); 
				 String needRole = ((SecurityConfig) conf).getAttribute();  
				 
				  //garn 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
				 for (GrantedAuthority gran : authentication.getAuthorities()) {   
					 
					 if (needRole.trim().equals(gran.getAuthority().trim())) {   
						 return; 
					 }
				 }
			 }	
			 //拒绝访问
			 throw new AccessDeniedException("Access Denied");   
		}

	   //决定MyAccessDecisionManagerImpl是否可以执行传递ConfigAttribute。
		@Override
		public boolean supports(ConfigAttribute attribute) {

			return true;
		}

	// 方法被安全拦截器实现调用， 包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型

		@Override
		public boolean supports(Class<?> clazz) {
		
			return true;
		}



	

}
