package com.wb.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.wb.core.common.exception.MyException;
import com.wb.web.system.entity.ZonePath;


public class ConvertFile {
  private static final String pdf2swf_path_key = "pdf2swf_tool_path"; 	
  private static Runtime runtime;
  private static String pdf2swf_path;	
 // private static final String pdf2swf_path = "C:/Program Files (x86)/SWFTools/pdf2swf.exe";	 
  private static final String PDF = ".pdf";	
  private static final String SWF = ".swf";	
  private final static long MAXSIZE = 4194304l; //最大限制转化大小4MB
  
  static{
	  
	    String classBaseDir =  ConvertFile.class.getClassLoader().getResource("/").getPath();		
		String propertiesfile = classBaseDir+"convertFileConfig.properties";
		
		pdf2swf_path= PropertiesUtil.GetValueByKey(propertiesfile, pdf2swf_path_key);
		
		//System.out.println(pdf2swf_path);
  }
	
	
  

  /**
   * 转码
   * @param fileType    文件类型
   * @param sourceFile	源文件
   * @param swfPath     swf路径（2015/07-28/40569b30-a0b9-45ec-b55a-17d7d68a68f8.swf）
   * @param path		头路径 （E:\cloudOne\personDisk\）
   * @return     true 成功  false 失败
   */
   public static boolean do2ConvertFile(String fileType,File sourceFile,String swfPath,String path){
	     if(sourceFile.length() > MAXSIZE){
	    	 throw new MyException("the size of file  out of range");
	     }
	     	   
	   
	     boolean isPdf = false;
		 	
	     //2015\07-27
		 String dateDir = swfPath.substring(0, swfPath.lastIndexOf("/"));	
	  
		 File folder = new File(path+ ZonePath.Compress + File.separator +dateDir);
		 if(!folder.exists()){
			 folder.mkdirs(); 
		 }
		 
		  //临时pdf文件路径 E:\cloudOne\personDisk\Compress\2015\07-27\qq.doc
		  String pdfPath = path + ZonePath.Compress + File.separator +dateDir+ File.separator +UUID.randomUUID().toString() + PDF;
		  String convertSwfPath = path + ZonePath.Compress + File.separator + swfPath;
		  
		  //临时pdf文件
		  File pdfFile = new File(pdfPath);
		
	      InputStream inputStream = null;
	      OutputStream outputStream = null;
		 
	     //转换成pdf文件,如果是pdf文件跳过这步
		 if(!fileType.equals("pdf")){
			 isPdf = true;
			 if(!pdfFile.exists()) {		 		 
	            // 获得文件格式  
		        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();  
		        DocumentFormat pdfFormat = formatReg.getFormatByFileExtension("pdf");  
		        DocumentFormat docFormat = formatReg.getFormatByFileExtension("doc");  	      
		   
		        /** 
		         *  在此之前需先开启openoffice服务，用命令行打开（cd C:\Program Files\OpenOffice.org 3\program （openoffice安装的路径）
	    			soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard）
		         */  
		        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);  
		      
	
		        try {
		        	// stream 流的形式  
			        inputStream = new FileInputStream(sourceFile);  
			        outputStream = new FileOutputStream(pdfFile); 
		            connection.connect();  
		            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);  
		     
		            converter.convert(inputStream, docFormat, outputStream, pdfFormat);  
		            
		        }catch (FileNotFoundException e) {			
					e.printStackTrace();
				}  catch (ConnectException e) {  
		            e.printStackTrace();  
		        }finally {  
		            if (connection != null) {  
		                connection.disconnect();  
		                connection = null;  
		            } 
		            
		        	try {
						inputStream.close();
					    outputStream.close();
					} catch (IOException e) {
				
						e.printStackTrace();
					}
				
		          
		        }
		        System.out.println("转化pdf成功....");
			 }else{
				 throw new MyException("the file has existed!");
				 
			 }
		 
		 }else{			 
			pdfFile = sourceFile;
		 }
			
		 //转换成swf文件
		 runtime = Runtime.getRuntime();
		
			if(pdfFile.exists()){  
				Process p = null;
				try {
					/*
					PDDocument doc = PDDocument.load(pdfFile.getPath());					
					int end = doc.getNumberOfPages();
					if(end>10){
						end=10;
					}*/
								
					//-p后面参数为分页参数，如1-12就是只转前12页。其余参数请参考swftool参数表
					//第一个文件路径为pdf2swf.exe的路径
			
					//p = runtime.exec(pdf2swf_path+" " + pdfFile.getPath() + " -o " + convertSwfPath + " -T 9"+" -p " + 1 + "-" + end+ " ");				      
					p = runtime.exec(pdf2swf_path +" " + pdfFile.getPath() + " -o " + convertSwfPath + " -T 9");				
					
					/*new DoOutput(p.getInputStream()).start();
					new DoOutput(p.getErrorStream()).start();*/
					
					p.waitFor();
					//swfFile.createNewFile();

					
					}catch (IOException e) {
						System.out.println("出现io错误！");
			
			           
						e.printStackTrace();
					}catch (InterruptedException e) {
						System.out.println("出现进程中断错误！");
						
					}finally{ 
										
						if(p!=null){
							  try {								  
							
								p.getErrorStream().close();
								p.getInputStream().close();  
					            p.getOutputStream().close(); 
					            p.destroy();
							   } catch (IOException e) {
								  System.out.println("关闭io失败！");
								  e.printStackTrace();
							  }  
				             
						}					
						 
						//如果pdf文件是新生成的则删除
						if(isPdf){
							if(pdfFile != sourceFile){
								
							  if(pdfFile.exists()){	
								  pdfFile.delete();
								
							  }
							}
						}
						
					}
		         
			        System.out.println("转swf成功...");
					return true;
				
			}else {
				//文件转化为pdf格式失败，返回null
				return false;
			}
			
			
			
		} 
  
  
  
  
   /**
    * 复制文件
    * @param targetPath 目标地址
    * @param original	源文件
    * @return
    * @throws MyException
    */
   public static boolean copyFile(String targetPath,File original) throws MyException {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		 //2015\07-27
		 String dateDir = targetPath.substring(0, targetPath.lastIndexOf("/"));	
	     //无日期文件夹则创建
		 File folder = new File(dateDir);
		 if(!folder.exists()){
			 folder.mkdirs(); 
		 }
		
		
		try {
			File file = new File(targetPath);
			if(!file.exists()) {

				file.createNewFile();
				fis = new FileInputStream(original);
				fos = new FileOutputStream(file);
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
				

			} else {
				throw new MyException("the file has existed!");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	
		return true;
	}
  
  
  
  
  
  
  
    public static boolean checkIsCanConvert(String fileType){
    	 if(fileType.equals("doc") || fileType.equals("docx") || 
				   fileType.equals("ppt") || fileType.equals("pptx") ||
				   fileType.equals("xls") || fileType.equals("xlsx") ||
				   fileType.equals("txt") ||  fileType.equals("pdf")){	
    	     return true;	 
		 }else{
			 return false;
		 }
    }
  
  
    public static boolean checkIsCanPlayForVideo(String fileType){
   	    if(fileType.equals("flv") || fileType.equals("mp4") || 
				   fileType.equals("mp3") || fileType.equals("swf") 
				   ){	
   	         return true;	 
		 }else{
			 return false;
		 }
   }
	
    
    /**
     * 复制文件
     * @param orginal 源文件
     * @param target  目标文件
     */
    public static void copySwfFile(String orginal,String target){
    	File orginalFile = new File(orginal);
    	if(!orginalFile.exists()){//源文件不存在则直接返回
    		return;
    	}
    	  
    	File targetFile = new File(target);
		try {			
	    	if(!targetFile.exists()){
	    		targetFile.createNewFile();
	    	}
	    	
	    	 FileUtils.copyFile(orginalFile,targetFile);
	    	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    }
    
    
    
    
    private static class DoOutput extends Thread {
        public InputStream is;
      
        //构造方法
         public DoOutput(InputStream is) {
            this.is = is;
        }
      
        public void run() {
            BufferedInputStream br = new BufferedInputStream(this.is);
            int line = 0;
            byte [] size = new byte[1024];
            try {
                //这里并没有对流的内容进行处理，只是读了一遍
                  while ((line = br.read(size)) != -1);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    

    
    
}
