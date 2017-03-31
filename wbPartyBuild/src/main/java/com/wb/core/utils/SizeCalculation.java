package com.wb.core.utils;


import java.text.DecimalFormat;

public class SizeCalculation {
     

	
	public static String getDataSize(long size){
		DecimalFormat formater = new DecimalFormat("####.00");
		if(size<1024){
			return size+"bytes";
			
		}else if(size<1024*1024l){
			float kbsize = size/1024f;  
			return formater.format(kbsize)+"KB";
			
		}else if(size<1024*1024*1024l){
			float mbsize = size/1024f/1024f;  
			return formater.format(mbsize)+"MB";
			
		}else if(size<1024l*1024l*1024l*1024l){
			float gbsize = size/1024f/1024f/1024f;  
			return formater.format(gbsize)+"GB";
			
		}else if(size<1024l*1024l*1024l*1024l*1024l){
			float gbsize = size/1024f/1024f/1024f/1024f;  
			return formater.format(gbsize)+"TB";
			
		}else{
			return "size: error";
		}
		
	}

	

	
	
	
	
	
	
}
