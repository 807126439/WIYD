package com.wb.core.spring.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.wb.core.spring.security.utils.matcher.MyAntPathRequestMatcher;
import com.wb.web.system.dto.role.RoleDTO;
import com.wb.web.system.entity.Authority;
import com.wb.web.system.service.IAuthorityService;
import com.wb.web.system.service.IRoleService;



/** 
 * 权限资源获取器，最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义。 
 * 
 */

@Component(value="myInvocationSecurityMetadataSource")
public class MyInvocationSecurityMetadataSourceImpl implements IMySecurityMetadataSource {
	 @Resource
	 private IRoleService roleService;
	 @Resource
	 private IAuthorityService authorityService;

	 private  Map<String, Collection<ConfigAttribute>> resourceMap = null;
	 
     
	
	//用于将权限和资源的对应关系存放在resourcMap 其中url为key,权限为值
	private void loadResourceDefine(){
		//获取所有权限
		 List<RoleDTO> roles= roleService.loadAllRole();
		 resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 
		 for (RoleDTO ro : roles){
			 
			 ConfigAttribute ca = new SecurityConfig(ro.getRoleName()); 
			
			 List<Authority> authList = this.authorityService.searchAuthoritiesByRole(ro.getId(), null, Authority.auth_acess, Authority.auth_button);
			 Set<Authority> authorities = new HashSet<Authority>(authList);
			 
			 for (Authority auth : authorities){
				 String url = auth.getResourecesUrl();
				 				   			
				 //判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				 if (resourceMap.containsKey(url)) {   
					 Collection<ConfigAttribute> value = resourceMap.get(url);
					 value.add(ca);
					 resourceMap.put(url, value);
				 }else{
					 Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					 atts.add(ca);
					 resourceMap.put(url, atts);   
				 }
			 }
		 }
		 
	 }

	
	
	

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

	    for (Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
	      allAttributes.addAll((Collection<ConfigAttribute>)entry.getValue());
	    }

	    return allAttributes;
	}

	 
  
	//将请求的资源与和数据库中的资源url匹配，如果匹配正确，则将url对应的权限返回
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		Iterator<String> ite = resourceMap.keySet().iterator();
		
		while (ite.hasNext()) {
			String resURL = ite.next();
			
			RequestMatcher requestMatcher = new MyAntPathRequestMatcher(resURL);
			//这一步是进行匹配
			if (requestMatcher.matches(request)) {
				//返回一个用权限封装成ConfigAttributeDefinition对象			
				return resourceMap.get(resURL);
			}
		}

		return null;
	}

	public boolean supports(Class<?> clazz) {
		
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	



	public void reloadResourceMap() {
		loadResourceDefine();
		
	}

	

	
}
