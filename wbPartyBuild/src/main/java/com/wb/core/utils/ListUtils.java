package com.wb.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;




public class ListUtils {
  
	
	public static List<Long> convertStringToLong(String pstr) {		
		if(StringUtils.isBlank(pstr)){
			return null;
		}
		
		String[] ids = pstr.split(",");
        List<Long> idList = new ArrayList<Long>();
		
        for (int i = 0; i < ids.length; i++) {
        	idList.add(Long.parseLong(ids[i]));
		}
		return idList;
	}
	
}
