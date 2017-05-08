package com.wb.core.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

public class ReflectUtil {

	
	
	
	/**
	 * 通过java反射获取类的成员变量值
	 * @param clazz      类
	 * @param fieldName  变量名
	 * @return
	 * @author zjr
	 */
	public static String getClassFiledValue(Object obj,String fieldName){
						
		try {
			 Field filed = obj.getClass().getDeclaredField(fieldName);			
			 //filed.setAccessible(true);
	
			return filed.get(obj).toString();
			 	
		} catch (Exception e) {
	
			 e.printStackTrace();
			 
			 return "";
		}
		
	}
	
	
	public static String getValueByGetMethod(Object obj,String fieldName){
		
		try {
			 PropertyDescriptor pd = new PropertyDescriptor(fieldName,  obj.getClass());  
			 Method method = pd.getReadMethod();		
		     String returnType = method.getReturnType().getName();
		     
		     Object val = method.invoke(obj);
		     if(returnType.contains("java.util.Date")){
		    	 if(val!=null){
		    		return  DateUtil.Date2Str((Date)val);
		    		 
		    	 }else{
		    		 return null;
		    	 }
		    	 
		     }else {
		    	 return val == null ? "":val.toString();
		     }
			 
	
	
			 
			 	
		} catch (Exception e) {
	
			 e.printStackTrace();
			 
			 return "";
		}
		
	}
	
	
	
	/**
	 * 根据成员变量名得到其Get方法，再返回Get方法的注解的数据库字段名
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static String getFiledValueByAnnotation(Class<?> clazz,String fieldName){
		
		try {
			 PropertyDescriptor pd = new PropertyDescriptor(fieldName,  clazz);  
			 Method method = pd.getReadMethod();
			 Column column = method.getAnnotation(Column.class);
			 if(column!=null){
				 return column.name();
			 }
			 
			 JoinColumn joinColumn = method.getAnnotation(JoinColumn.class);
			 if(joinColumn!=null){
				 return joinColumn.name();
			 }
		 
			 return null;
			 
		} catch (Exception e) {
	
			 e.printStackTrace();
			 
			 return "";
		}

		
	}
	
}
