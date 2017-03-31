package com.wb.core.utils;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class ExcelUtil {
	
	
	public static String getCellValue(HSSFCell cell) { 
		if(cell!=null){
			String cellValue = "";  
	        DecimalFormat df = new DecimalFormat("#");  
	        switch (cell.getCellType()) {  
	        case HSSFCell.CELL_TYPE_STRING:  
	            cellValue = cell.getRichStringCellValue().getString().trim();  
	            break;  
	        case HSSFCell.CELL_TYPE_NUMERIC:  
	            cellValue = df.format(cell.getNumericCellValue()).toString();  
	            break;  
	        case HSSFCell.CELL_TYPE_BOOLEAN:  
	            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
	            break;  
	        case HSSFCell.CELL_TYPE_FORMULA:  
	            cellValue = cell.getCellFormula();  
	            break;  
	        default:  
	            cellValue = "";  
	        }  
	        return cellValue;  
			
		}else{
			return null;
		}
    }  


	
	
}
