package com.wb.core.utils.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.wb.core.common.utils.Assert;


public class ExcelUtils<T> {

	
	private TitleSet[] titleArray;	
	private String savePath;
	private WrapExcelData<T> wrapExcelData;
	private List<T> data;
	
	


	/**
	 * 构造完后，调用generateExecl()即可生成excel文件
	 * @param titleArray 表头
	 * @param savePath   文件保存路径
	 * @param data		   数据
	 * @param wrapExcelData 数据输入到excel的规范
	 */
	public ExcelUtils(TitleSet[] titleArray, String savePath,List<T> data,WrapExcelData<T> wrapExcelData) {
		Assert.noNullElements(titleArray);
		Assert.hasText(savePath, "excel file savePath could not be null");
		Assert.notEmpty(data, "excel data source could not be empty ");
		Assert.notNull(wrapExcelData);
		
		this.titleArray = titleArray;
		this.savePath = savePath;
		this.data = data;
		this.wrapExcelData = wrapExcelData;
     
	}


	

	/**
	 * 生成excel文件
	 * @return 是否成功
	 */
	public boolean generateExecl(){
		HSSFWorkbook workbook = new HSSFWorkbook();   
		HSSFSheet sheet = workbook.createSheet();  
 
        HSSFRow row = sheet.createRow(0);
        //表头内容
        for (int i = 0; i < titleArray.length; i++) {
        	sheet.setColumnWidth(i+1, titleArray[i].width * 256);
        	row.createCell(i).setCellValue(titleArray[i].titleName);
		}
        
        int currRow = 1;
        for (T obj : data) {
        	row = sheet.createRow(currRow);
        	wrapExcelData.wrapData(obj, row);
        	currRow ++;
		}
        
        //给表格加样式
		workbook = drawingStyle(workbook,currRow,titleArray.length);
       
    	FileOutputStream out;
		try {
			 out = new FileOutputStream(savePath);
			 workbook.write(out);  
			 
			 return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
		 return false;
	}



	/**
	 * 创建excel文件
	 * @param titleArray  标题设置
	 * @param data		      数据	
	 * @param savePath    文件保存路径
	 * @return
	 */
	public static boolean createExcel(TitleSet[] titleArray,List<List<String>> data,String savePath){
		
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet();  
       /* sheet.setColumnWidth(1, 30 * 256);
        sheet.setColumnWidth(2, 50 * 256);
        sheet.setColumnWidth(3, 20 * 256);*/
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);
        //表头内容
  
        for (int i = 0; i < titleArray.length; i++) {
        	sheet.setColumnWidth(i+1, titleArray[i].width * 256);
        	row.createCell(i).setCellValue(titleArray[i].titleName);
		}
    
       
        int currRow = 1;
        HSSFCell cell = null;
        for (List<String> rowData : data) {
    		row = sheet.createRow(currRow);
        	for (int i = 0; i < rowData.size(); i++) {      
        		cell = row.createCell(i);
        		cell.setCellValue(rowData.get(i));
			}
        	      	
        	currRow ++;
		}
      
        	
      
        //给表格加样式
        wb = drawingStyle(wb,currRow,titleArray.length);
       
    	FileOutputStream out;
		try {
			 out = new FileOutputStream(savePath);
			 wb.write(out);  
			 
			 return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
		 return false;
	}
	
	
	
	public static HSSFWorkbook drawingStyle(HSSFWorkbook wb,int maxRow,int maxCol){
    	
    	  HSSFSheet sheet = wb.getSheetAt(0);
    	  //加边框
		  HSSFCellStyle cellStyle = wb.createCellStyle();
		  cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		  cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		  cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		  cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		  cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		  cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		  cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		  cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		  cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
		  cellStyle.setWrapText(true);
		  cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		  //加边框
		  	HSSFRow row = null;
	        HSSFCell cell = null; 
			for(int i=0;i<maxRow;i++){
				row = sheet.getRow(i);
				for(int j=0;j<maxCol;j++){
					cell = row.getCell(j);
					if(null==cell){
						cell = row.createCell(j);
					}
					cell.setCellStyle(cellStyle);
				}
			  }
    	
    	return wb;
    }
    
	
	
	/**
	 * 表头参数
	 * @author wb_java_zjr
	 *
	 */
	public static class TitleSet{
		
		public String titleName;//标题名
		public Integer width;   //标题宽度
		
		public TitleSet(String titleName, Integer width) {
			super();
			this.titleName = titleName;
			this.width = width;
		}
		
		
		
	}
	
	
	
	
	

	
	
	
	
	
	
	public static void main(String[] args) {
		
		TitleSet[]  titleArray = new TitleSet[]{
				new TitleSet("序号", 20),new TitleSet("用户名", 20),new TitleSet("姓名", 20),
				new TitleSet("性别", 10),new TitleSet("年龄", 20)
				};
	
		
		
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> temp = new ArrayList<String>();
		temp.add("1");temp.add("AA");temp.add("AA");temp.add("男");temp.add("25");
		data.add(temp);
		
		List<String> temp1 = new ArrayList<String>();
		temp1.add("2");temp1.add("BB");temp1.add("BB");temp1.add("男");temp1.add("25");
		data.add(temp1);

		List<String> temp2 = new ArrayList<String>();
		temp2.add("3");temp2.add("BB2342432222dssdsds");temp2.add("BB");temp2.add("男");temp2.add("25");
		data.add(temp2);
		
		List<String> temp3 = new ArrayList<String>();
		temp3.add("4");temp3.add("BB是对方是否收费的方式");temp3.add("BB");temp3.add("男");temp3.add("25");
		data.add(temp3);
		

		List<String> temp4 = new ArrayList<String>();
		temp4.add("5");temp4.add("BB的地方个地方嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎");temp4.add("BB");temp4.add("男");temp4.add("25");
		data.add(temp4);
		
		createExcel(titleArray, data,"d:/1.xls");
		
		System.out.println("finsih...");
	}
	
	
	
	
	
	
}
