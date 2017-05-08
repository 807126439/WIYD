package com.spr.core.spring.security.gobal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求路径定位controller方法的参数（例：/userContoller.do?add）
 * 1.该参数可在参数队列中的任意位置
 * 2.如非用来定位方法为目的，请勿在其他方法包含该参数
 * 3.不可混合使用参数,不可存在相同参数名字（如：1.viewPage 2.viewPage=1）,否则造成权限校验出现混乱
 * 4.用来在做权限校验时，过滤出包含该参数的路径再进行权限校验
 * 
 * @author wb_java_zjr
 *
 */
public class MyRequestParam {

	public static final String add = "add";
	public static final String detail = "detail";
	public static final String edit = "edit";
	public static final String viewPage = "viewPage";
	public static final String viewHistoryPage = "viewHistoryPage";
	private static List<String> urlParams = new ArrayList<String>();
	
	static{
		Field[] f = MyRequestParam.class.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
					
			if(f[i].getType().getName().endsWith("java.lang.String")){
						
				
				if(Modifier.isPublic(f[i].getModifiers()) && Modifier.isStatic(f[i].getModifiers()) 
						&& Modifier.isStatic(f[i].getModifiers()) ){
					
					try {
						String val = String.valueOf(f[i].get(MyRequestParam.class));
						urlParams.add(val);
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						
						e.printStackTrace();
					}
					
				}
			}

		}
	}
	
	
	
	/**
	 * 从请求参数语句获取定位参数，不包含定位参数则返回null
	 * @param queryString
	 * @return
	 */
	public static String  getPositionFromRequestParams(String queryString){
		
		for (String param : urlParams) {	
			
			if(queryString.contains(param)){				
				return param;
			}
			
		}
		
		return null;
		
	}
}
