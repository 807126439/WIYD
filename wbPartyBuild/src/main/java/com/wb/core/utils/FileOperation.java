package com.wb.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.wb.core.common.bean.UploadFile;
import com.wb.core.common.exception.MyException;
import com.wb.web.system.entity.ZonePath;

public class FileOperation {

	
	
	public static long getFolderSize(File folder){
	      if(folder.isDirectory()){	      
			    long size = 0;
		        File flist[] = folder.listFiles();
		        for (int i = 0; i < flist.length; i++){
		            if (flist[i].isDirectory()){
		                size = size + getFolderSize(flist[i]);
		            } else{
		                size = size + flist[i].length();
		            }
		        }
		        return size;
	       }else{
	    	   return 0;
	       }
	 }
	
	
	
	
	public static String getFileType(String filename ){
		
		return  filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
		
	}
	
	
	public static String checkWhetherTheSame(File upLoad,File store){
		String upLoadMd5 = FileMd5.fileMD5(upLoad);
		String storeMd5 = FileMd5.fileMD5(store);
		if (upLoadMd5.equals(storeMd5)) {
			return upLoadMd5;
		}else{
			store.delete();
			return null;
		}		
	}
	
	
	
	/**
	 * 
	 * @param newFile
	 * @param original
	 * @return
	 * @throws MyException
	 */
	public static boolean upLoadFile(String newFile, File original) throws MyException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
	
		try {
			File file = new File(newFile);
			if(!file.exists()) {

				file.createNewFile();
				fis = new FileInputStream(original);
				fos = new FileOutputStream(file);
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
				
				 return true;
				 
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
	
		return false;
	}
	
	
	/**
	 * 覆盖文件
	 * @param targetPath  原来文件路径
	 * @param uploadFile  上传文件
	 * @throws MyException
	 */
	public static void coverFile(String targetPath,UploadFile uploadFile) throws MyException {
			
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			File file = new File(targetPath);
			if(!file.exists()) {
				file.createNewFile();
			}
			
			fis = new FileInputStream(uploadFile.getUpload());
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, count);
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

	}
	
	
/**
 * 上传头像处理	
 * @param zp
 * @param uploadFile
 * @return
 * @throws MyException
 */
 public static String upLoadFile(ZonePath zp,UploadFile uploadFile) throws MyException {
		String middlePath = PathHelper.makeNowDatePath();
		File dir = new File(zp.getPath()+middlePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//文件保存后半路径
		String tailPath = middlePath+UUID.randomUUID().toString()+"."+ getExt(uploadFile.getUploadFileName());
		//文件保存全路径
		String fullPath = zp.getPath()+tailPath;
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			File file = new File(fullPath);
			if(!file.exists()) {

				file.createNewFile();
				fis = new FileInputStream(uploadFile.getUpload());
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

		return tailPath.replaceAll("\\\\", "/");
	}
	
	
	
	
	
	/**
	 * 根据文件名获取文件类型 (xx.doc)
	 * @param fileName
	 * @return
	 */
	public static String getExt(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	
	
	/**
	 * 删除压缩图片
	 * @param compressPath   路径前缀
	 * @param pattern		  小图案	
	 * @param bigPattern	  大图案	
	 */
	public static void delteCompressPicture(String compressPath,String pattern,String bigPattern){
	   if(StringUtils.isBlank(compressPath) || StringUtils.isBlank(pattern) ||
			   StringUtils.isBlank(bigPattern) ){
		   return;
	   }
		
	   File smallPic = new File(compressPath + pattern);
	   File bigPic = new File(compressPath + bigPattern);
	   
	   if(smallPic.exists()){
		   smallPic.delete();
		 }
	   
	   if(bigPic.exists()){
		   bigPic.delete();
	   }
	   
	}
	
	/**
	 *   删除swf文件
	 * @param compressPath  路径前缀
	 * @param swfPath       swf路径
	 */
	public static void delteCompressSwf(String compressPath,String swfPath){
	    if(StringUtils.isBlank(compressPath) || StringUtils.isBlank(swfPath)){
		   return;
	    }
	   
		File swfFile = new File(compressPath + swfPath);
		
		if(swfFile.exists()){
			swfFile.delete();
		}
		
	}
	
	
	/**
	 * 删除文件
	 * @param filePath 文件路径
	 */
	public static void deleteFile(String filePath){
		File delFile = new File(filePath);
		if(delFile.isFile()){
			delFile.delete();
		}
	}
	
	
	
	
	/**
	 * 删除用物理空间上的文件夹及里面的文件
	 * @param file
	 */
	public static void deleteFolderInDisk(File file){
		  if(file.exists()){    
			   if(file.isFile()){           
				   file.delete(); 
				   
				 } else if(file.isDirectory()){          
					 File files[] = file.listFiles();    
						  
					 for(int i=0;i<files.length;i++){     
					    deleteFolderInDisk(files[i]);     
					    
					 } 
				   file.delete();
			 }  
			  
		 }
		
	}
	
	/**
	 * 截取文件类型
	 * @param fileName
	 * @return
	 */
	public static String getFileTypeByName(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
	}
	
	
	/**
	 * 过滤物理路径
	 * 检查路径末尾是否带有'\\' 或 '/'，没有则加上
	 * @param path
	 * @return
	 */
	public static String filterPath(String path){
		
		if(path.endsWith("\\")){
			return  path;
			
		}else if(path.endsWith("/")){
			return  path;
			
		}else{
			return path + File.separator;
		}
		
	}
	
}
