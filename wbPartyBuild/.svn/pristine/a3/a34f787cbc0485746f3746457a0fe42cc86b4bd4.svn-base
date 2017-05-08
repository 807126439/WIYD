package com.wb.core.common.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wb.core.common.exception.MyException;
import com.wb.core.spring.security.user.MyUser;

public class BaseService {
    @Resource
	private Mapper mapper ;
	public Logger logger = Logger.getLogger(getClass());
	
	
	public Mapper getMapper() {
		return mapper;
	}


	
	 /**
	  * 获取当前MyUser实体
	  * @return
	 */ 
	 public MyUser getNowUser(){
		   Object o = (Object) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   if(o == null ){
			   throw new MyException("error request!"); 
		   }

		   if(!(o instanceof MyUser)){
			   throw new MyException("未登录或无权访问！");
		   }
		   return (MyUser) o;
	  }
	 
	  
	 public String getNowUserId(){
		 
		 return getNowUser().getId();
	 }
	
	 
	 
	 
	   
	  /**
	   * 检验是否登录了
	   * @return
	  */
	  public boolean checkWheatherLogined(){
		   Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   if(obj == null){
			   	return false;		   
		   }
		   
		   if(obj instanceof MyUser){
			   return true;
		   }else{
			   return false;
		   }
		   
	   } 
	   
	  

	  

	  

	
	/**
	 * 获取当前请求的ip地址
	 * @return
	 
	public String getNowIPAddress(){
		String IP_ADDRESS = ServletActionContext.getRequest().getRemoteAddr();		
		
	    if(IP_ADDRESS.equals("127.0.0.1")){
	    	try {
				IP_ADDRESS = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				IP_ADDRESS = "0.0.0.0";
			}
	    }
		
	    return IP_ADDRESS;
	}*/
	  
}
