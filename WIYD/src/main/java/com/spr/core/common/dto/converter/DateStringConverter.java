package com.spr.core.common.dto.converter;

import java.util.Date;

import org.dozer.CustomConverter;

import com.spr.core.utils.DateUtil;

public class DateStringConverter implements CustomConverter{

	
	public Object convert(Object destinationFieldValue,
            Object sourceFieldValue, Class<?> destinationClass,
            Class<?> sourceClass) {
	    
		    Object returnVale = null;
	        if(sourceFieldValue!=null){
	            if(sourceFieldValue instanceof Date ){
	            	returnVale = DateUtil.DateToStrByShort(sourceFieldValue);
              
	            }
	            if(sourceFieldValue instanceof String ){
	            	returnVale = DateUtil.StrToDate(String.valueOf(sourceFieldValue));
	            	
	            }

	        }
	        
	        
	        return returnVale;

}
}
