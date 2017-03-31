package com.wb.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * Create on 2010-12-7 上午10:18:09
 * 工具类
 * @version
 */
public class ZipUtil {
	
	  public static void zipFile(List files,ZipOutputStream outputStream) {
		  int size = files.size();
		  for(int i = 0; i < size; i++) {
      File file = (File) files.get(i);
      zipFile(file, outputStream);
		  }
	  }
	  
	  public static void zipFile(File inputFile,
	            ZipOutputStream ouputStream) {
	        try {
	            if(inputFile.exists()) {
	                /**如果是目录的话这里是不采取操作的，
	                 * 至于目录的打包正在研究中*/
	                if (inputFile.isFile()) {
	                    FileInputStream IN = new FileInputStream(inputFile);
	                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
	                    //org.apache.tools.zip.ZipEntry
	                    ZipEntry entry = new ZipEntry(inputFile.getName());
	                    ouputStream.putNextEntry(entry);
	                    // 向压缩文件中输出数据   
	                    int nNumber;
	                    byte[] buffer = new byte[512];
	                    while ((nNumber = bins.read(buffer)) != -1) {
	                        ouputStream.write(buffer, 0, nNumber);
	                    }
	                    // 关闭创建的流对象   
	                    bins.close();
	                    IN.close();
	                } else {
	                    try {
	                        File[] files = inputFile.listFiles();
	                        for (int i = 0; i < files.length; i++) {
	                            zipFile(files[i], ouputStream);
	                        }
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	  
	  public static HttpServletResponse downloadZip(File file,HttpServletResponse response) {
	        try {
	        // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();

	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");

	//如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
	        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(file.getName(), "UTF-8"));
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	        } catch (IOException ex) {
	        ex.printStackTrace();
	        }finally{
	             try {
	                    File f = new File(file.getPath());
	                    f.delete();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        }
	        return response;
	    }
}
