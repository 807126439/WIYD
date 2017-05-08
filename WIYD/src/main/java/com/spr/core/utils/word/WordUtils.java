package com.spr.core.utils.word;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordUtils {

	private static Configuration configuration = null;  
    
	static{  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
    }  
    
      /**
       * 创建word
       * @param dataMap   数据源
       * @param filePath  文件路径
       * @param ftlName   模板名称
       */
    public static void createWord(Map<String, Object> dataMap,String filePath,String ftlName){  
   
    
        configuration.setClassForTemplateLoading(WordUtils.class, "/");  //FTL文件所存在的位置  
        Template t=null;  
        try {  
            t = configuration.getTemplate(ftlName); //文件名  
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
        } finally{
        	
        	if(out!=null){
        		try {
					out.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
        	}
        }
    }  
  
    
    /**
     * 向word模板渲染数据
     * @param destFile			保存word文件地址
     * @param srcFilePath		模板文件地址
     * @param map				数据源
     */
    public static File drawDataInDoc(String destFile,String srcFilePath, Map<String, String> map){
    	  HWPFDocument document = replaceDoc(srcFilePath, map);
    	  if(document == null){
    		  return null;
    	  }
    	  ByteArrayOutputStream ostream = new ByteArrayOutputStream();
    	 // File file = new File(destFile); 	  
          try {
        	
        	 // document.write(file);
              document.write(ostream);
              //输出word文件
              OutputStream outs = new FileOutputStream(destFile);
                       
              outs.write(ostream.toByteArray());
              outs.close();
              
              System.out.println("finish");
              
              return new File(destFile);
          } catch (IOException e) {
              e.printStackTrace();
          }
          
          return null;
    }
    
    
    /**
     * 读取word模板并替换变量
     * @param srcPath
     * @param map
     * @return
     */
    public static HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
        try {
            // 读取word模板
            FileInputStream fis = new FileInputStream(new File(srcPath));
            HWPFDocument doc = new HWPFDocument(fis);
            // 读取word文本内容
            Range bodyRange = doc.getRange();
            if(map!=null){
            	// 替换文本内容
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    bodyRange.replaceText("${" + entry.getKey() + "}", entry
                            .getValue());
                }
            }
           
           
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    
    
    public static void main(String[] args) {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("gcmc", "东莞市道滘镇");
    	map.put("gcdz", "东莞市道滘镇");
    	map.put("jsdw", "东莞市道滘镇水利防灾减灾工程建设领导小组");
    	map.put("jldw", "东莞市道滘镇水利防灾减灾工程建设领导小组");
    	map.put("sgdw", "东莞市道滘镇水利防灾减灾工程建设领导小组");
    	map.put("djbh", "DG002");
    	map.put("bh", "JDJC-2016-0008");
		drawDataInDoc("d:/yy2.doc", "d:/jctzs.doc", map);
	}
    
   
	
}
