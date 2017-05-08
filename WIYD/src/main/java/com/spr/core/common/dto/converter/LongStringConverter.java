package com.spr.core.common.dto.converter;

import org.dozer.CustomConverter;

public class LongStringConverter implements CustomConverter{

	
	public Object convert(Object destinationFieldValue,
            Object sourceFieldValue, Class<?> destinationClass,
            Class<?> sourceClass) {
	    
		    Object returnVale = null;
	        if(sourceFieldValue!=null){
	            if(sourceFieldValue instanceof Long ){
	                
	                    returnVale =sourceFieldValue.toString();
              
	            }
	            if(sourceFieldValue instanceof String ){
	              
	            	returnVale =Long.parseLong((String) sourceFieldValue);
	            }

	        }
	        return returnVale;

}
}
