package com.spr.core.spring.security.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.spr.core.common.exception.BusinessException;
import com.spr.core.spring.security.user.UserBO;
import com.spr.core.spring.security.user.extend.MyUser;

public class SecurityPrincipalUtil {

	
	 public static UserBO getNowUser(){
		   Object obj = (Object) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   if(obj == null ){
			   throw new BusinessException("error request!"); 
		   }

		   if(!(obj instanceof UserBO)){
			   throw new BusinessException("未登录或无权访问！");
		   }
		   return (UserBO) obj;
	  }
	
	 

	
	/**
	 * 检查当前用户用户是否拥有权限
	 * @param operCode	权限码
	 * @return
	 */
	public static boolean checkIsHasAuth(String operCode){
		 
		 /* Set<String> codes = splitCodes(operCode);
				
		  List<String> authCodes = getNowUser().getAuthCodes();
		  
		  if(authCodes == null || authCodes.isEmpty()){
			  return false;
		  }
	  
		  for (String cd : codes) {
			  if(authCodes.contains(cd)){
				  return true;
			  }
		 }*/
		  
		  
		  return true;
		 
	}
	
	 private static Set<String> splitCodes(String authorityString){
	    String[] codesArray = StringUtils.tokenizeToStringArray(authorityString, ",");
	    Set<String> codes = new HashSet<String>();
	    for (String code : codesArray) {
	    	codes.add(code);
	    }
	    return codes;
	  }
	
	
	
	public static  String getRoleStrsFromGrantedAuthority(Collection<GrantedAuthority> authorities){
		    StringBuilder sb = new StringBuilder();
			for (GrantedAuthority authority : authorities) {
				sb.append(authority.getAuthority()+",");
			}
			
			if(sb.length()>0){
				sb.deleteCharAt(sb.length()-1);
			}
			
			
			return sb.toString();
	 }
	
	
	
	
}
