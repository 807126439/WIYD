package com.spr.core.utils.pdf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.spr.core.utils.PropertiesUtil;



public class PdfToSwfUtils {
  private static final String pdf2swf_path_key = "pdf2swf_tool_path"; 	
  private static final String pdf2swf_path_temp = "C:/Program Files (x86)/SWFTools/pdf2swf.exe";	 
  private static String pdf2swf_path = pdf2swf_path_temp;	
  
  static{
	  
	    String classBaseDir =  PdfToSwfUtils.class.getClassLoader().getResource("/").getPath();		
		String propertiesfile = classBaseDir+"systemConfig.properties";
		
		pdf2swf_path= PropertiesUtil.GetValueByKey(propertiesfile, pdf2swf_path_key);
		
		System.out.println(pdf2swf_path);
  }
	
	
  

  /**
   * pdf文件转swf文件
   * @param pdfFile
   * @param swfPath
   * @param path
   * @return
   */
   public static boolean pdfToSwf(File pdfFile,String swfPath){
	       			
	   Runtime  runtime = Runtime.getRuntime();
		
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
				p = runtime.exec(pdf2swf_path +" " + pdfFile.getPath() + " -o " + swfPath + " -T 9");				
				
				new DoOutput(p.getInputStream()).start();
				new DoOutput(p.getErrorStream()).start();
				
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
					
					pdfFile.delete();
				}
	         
		        System.out.println("转swf成功...");
				return true;
			
		}else {
			
			return false;
		}
		
		
		
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
