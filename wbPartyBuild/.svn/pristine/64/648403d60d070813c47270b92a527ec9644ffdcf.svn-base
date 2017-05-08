package com.wb.core.utils;

public class PageUtil {
	
	public static Integer getTotalPage(int listSize,int pageSize){
		Integer totalPage = null;		
		if(listSize>pageSize){	 		
	 		if(listSize%pageSize==0){	 			
	 			totalPage = listSize/pageSize;
	 		}else{
	 			totalPage = listSize/pageSize+1;
	 			
	 		}
	 	}else{	 		
	 		totalPage = 1;
	 	}
		return totalPage;
	}

}
