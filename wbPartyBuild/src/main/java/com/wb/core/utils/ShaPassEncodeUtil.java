package com.wb.core.utils;

import java.util.ArrayList;

import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.wb.core.common.exception.MyException;

	  
	/** 
	 * @author XUYI 
	 * Spring Security 3.1 PasswordEncoder 
	 */  
	
	public class ShaPassEncodeUtil {   	    
	    private static final String SITE_WIDE_SECRET = "my-secret-key";  
	    private static final int SECRET_TYPE = 256; 
	    private static final String saltName = "username";
		private static ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(SECRET_TYPE);
	    
	    
	    /**.
	     * 加密
	     * @param userName 用户名
	     * @param password 密码
	     * @return
	     */
		public static String encodePassWord(String userName,String password){
			if(userName == null || userName.isEmpty() || password == null || password.isEmpty()){
				throw new MyException("params can not be null or empty");
			}
			
	    	ReflectionSaltSource saltSource = new ReflectionSaltSource();	    	
	    	saltSource.setUserPropertyToUse(saltName);
	    		    	
	    	UserDetails userDetails =  new User(userName,password,true,true,true,true,new ArrayList<GrantedAuthority>());		
		  	    	
			Object salt = saltSource.getSalt(userDetails);
			String lastpassword = passwordEncoder.encodePassword(password, salt);
			
			return lastpassword;
		}
	 
	      

}
