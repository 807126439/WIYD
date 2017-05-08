package com.wb.core.spring.security.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.wb.core.common.exception.MyException;
import com.wb.core.spring.security.user.MyUser;

public class SecurityPrincipalUtil {

	
	 public static MyUser getNowUser(){
		   Object obj = (Object) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   if(obj == null ){
			   throw new MyException("error request!"); 
		   }

		   if(!(obj instanceof MyUser)){
			   throw new MyException("未登录或无权访问！");
		   }
		   return (MyUser) obj;
	  }
	
	 

	
	/**
	 * 检查当前用户用户是否拥有权限
	 * @param operCode	权限码
	 * @return
	 */
	public static boolean checkIsHasAuth(String operCode){

		  Set<String> codes = splitCodes(operCode);
			
		  List<String> authCodes = getNowUser().getAuthCodes();
		  
		  if(authCodes == null || authCodes.isEmpty()){
			  return false;
		  }
	  
		  for (String cd : codes) {
			  if(authCodes.contains(cd)){
				  return true;
			  }
		 }
		  
		  
		 return false;
 
		
		  
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
