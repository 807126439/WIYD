package com.wb.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordUtils {

	private static Configuration configuration = null;  
    
	static{  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
    }  
    
      
    public static void createWord(Map<String, Object> dataMap,String filePath,String ftlName){  
   	
    
        configuration.setClassForTemplateLoading(WordUtils.class, "/");  //FTL文件所存在的位置  
        Template t=null;  
        try {  
            t = configuration.getTemplate("advise.ftl"); //文件名  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        File outFile = new File(filePath);  
        Writer out = null;  
        try {  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8")); //在web环境下需添加编码格式，否则生成word文件无法打开 
        
        } catch (FileNotFoundException | UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        }  
           
        try {  
            t.process(dataMap, out);  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    
    
    
   /* private static void getData(Map<String, Object> dataMap) {  
    	dataMap.put("number", "程峰232"); 
        dataMap.put("chineseName", "程峰232");  
        dataMap.put("department", "科技部323");  
        dataMap.put("duty", "组长");  
        dataMap.put("createTime", "2016-2-15");  
        dataMap.put("themeTitle", "关于促进局内信息化建设建议");  
        dataMap.put("advise", "fghgfhngjghjghjghjghjgh");  
        dataMap.put("forecast", "2222222222222222222222");  

    }  */
    
   
	
}
