package com.wb.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.wb.web.base.entity.BaseFile;

/**
 * 路径生成器
 * @author zjr
 *
 */
public class PathHelper {
	private String realFileName;
	private String fullPath;    //文件上传的目录地址
	private String savePath;    //数据库保存的地址
    private String picPartPath; //图片预览部分地址
	private String swfPath;     //数据库保存的预览地址
	private String fileType;    //文件类型
	private short fileKind = BaseFile.FILE_KIND_COMMON;
	private Character turnStatus = BaseFile.STATUS_UNTURN;
	
	/**
	 * 获取日期路径（2015/07-12/）
	 * @return （2015/07-12/）
	 */
	public static String makeNowDatePath(){
		SimpleDateFormat year = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd"); 		 
		 
		Date currentDate = new Date();	
		
		return year.format(currentDate) + File.separator + monthDay.format(currentDate)+File.separator;
	}
	

	

	
	/**
	 * 生成上传路径
	 * 根据当前年月日建立文件夹（例如：D:/resource/2015/07-12）
	 * @param path 		保存前缀路径（尾部不要\）
	 * @param fileName  上传的文件名
	 * @param isPublic  是否是公共文件处理
	 * @return
	 */
    public PathHelper(String path,String fileName,boolean isPublic) {
    	this.fileType = FileOperation.getExt(fileName).toLowerCase();
    	this.realFileName = UUID.randomUUID().toString()+ "." +fileType;
    	
    	
		SimpleDateFormat year = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd"); 		 
		 
		Date currentDate = new Date();	
		//2008\12-01
		String datePath = year.format(currentDate) + File.separator + monthDay.format(currentDate);
		//E:\xx\2008\12-01
		String fullPath = path + datePath;
		
		File dir = new File(fullPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		this.picPartPath = datePath;
		this.savePath = datePath + File.separator + this.realFileName;  //数据库保存的路径
		this.fullPath = fullPath + File.separator + this.realFileName;	//文件保存的物理路径
		
						
		String suffix = ".swf";
		// 2008/12-01/874b168d-c9a7-4dfb-a54d-7e99b2ee4ef8.swf(注意预览路径必须讲\转换为/，否则不预览失败)
		this.swfPath = (this.picPartPath + File.separator + UUID.randomUUID().toString()+ suffix).replaceAll("\\\\", "/");

		
		if(FilePatten.isVideo(fileType)){//视频类型
			this.turnStatus = BaseFile.STATUS_UNTURN;
			this.fileKind = BaseFile.FILE_KIND_VIDEO;
			
			if(fileType.equals("mp4") || fileType.equals("flv")){
				suffix = "." + fileType;
				
				if(isPublic){//不需要转换格式，预览路径直接是源文件路径
					this.savePath = this.savePath.replaceAll("\\\\", "/");
					this.turnStatus = BaseFile.STATUS_UNNEED;
				}
					
			}else{
				suffix = ".flv";								
			}			
		
			
		}else if(FilePatten.isAudio(fileType)){
			if(fileType.equals("mp3") || fileType.equals("m4a") || fileType.equals("oga")){
				suffix = "." + fileType;				
			}else{
				suffix = ".mp3";								
			}
			this.turnStatus = BaseFile.STATUS_UNTURN;
			this.fileKind = BaseFile.FILE_KIND_AUDIO;
			
		}else if(FilePatten.isOfficeFile(fileType)){//office文件类型
			suffix = ".swf";
			this.turnStatus = BaseFile.STATUS_UNTURN;
			this.fileKind = BaseFile.FILE_KIND_OFFICE;
			
		}else if(FilePatten.isPicture(fileType)){//图片类型
			this.turnStatus = BaseFile.STATUS_UNNEED;
			this.fileKind = BaseFile.FILE_KIND_PICTURE;		
			
		}else{//其他
			this.turnStatus = BaseFile.STATUS_UNNEED;
			this.fileKind = BaseFile.FILE_KIND_COMMON;
		}
			
		
		
	
	}



    
    


	public String getRealFileName() {
		return realFileName;
	}





	public String getFullPath() {
		return fullPath;
	}





	public String getSavePath() {
		return savePath;
	}





	public String getPicPartPath() {
		return picPartPath;
	}





	public String getSwfPath() {
		return swfPath;
	}





	public String getFileType() {
		return fileType;
	}





	public short getFileKind() {
		return fileKind;
	}





	public Character getTurnStatus() {
		return turnStatus;
	}
	
    

    
    
    
	

	
	

}
