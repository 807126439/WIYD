package com.spr.web.file.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spr.core.common.utils.Assert;
import com.spr.core.utils.ImgCompress;
import com.spr.core.utils.file.FilePatten;
import com.spr.web.file.entity.BaseFile;

/**
 * 路径生成器
 * 生成文件的保存路径信息
 * @author wb_java_zjr
 *
 */
public class PathGenerator {
	
	
	private String realFileName;	//保存的文件名
	private String fullPath;    	//保存的全路径名
	private String savePath;    	//数据库保存的地址
    private String datePath; 	
	private String transferPath;    //转换后的文件地址后缀
	private String fileType;    	//文件类型
	
	private String pattern;
	private String bigPattern;
	
	private short fileKind = BaseFile.FILE_KIND_OTHER;
	private short turnStatus = BaseFile.TURN_STATUS_NOT_TURNED;
	
	private boolean copySource = false; //是否复制源文件
	
	private final String SWF = ".swf";
	private final String FLV = ".flv";
	private final String MP3 = ".mp3";
	
	private final int mid_width = 320;	//图片压缩最小宽度
	private final int mid_heigth = 320; //图片压缩最小高度
	private final int big_width = 1024; //图片压缩最大宽度
	private final int big_heigth = 768; //图片压缩最大高度
	
	
	public PathGenerator(String path,String fileName) {
		Assert.hasText(path,Assert.NULL_PARAM_STR("path"));
		Assert.hasText(fileName,Assert.NULL_PARAM_STR("fileName"));
		
		this.fileType = getExt(fileName).toLowerCase();	  //通过文件名获取文件类型
    	this.realFileName = UUID.randomUUID().toString()+ "." + fileType;
    	
    	
		SimpleDateFormat year = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd"); 		 
		 
		Date currentDate = new Date();
		datePath = new StringBuilder(year.format(currentDate)).append(File.separator).append(monthDay.format(currentDate))
				   .toString();		
		String fullPath = path + datePath;
		
		File dir = new File(fullPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
										
		this.savePath = datePath + File.separator + this.realFileName; 
		this.fullPath = fullPath + File.separator + this.realFileName;	
		
						
		handleTransferPath();
		
	}
	
	/**
	 * 处理文件转换后的路径地址
	 */
	private void handleTransferPath(){
		Assert.hasText(fileType,Assert.NULL_PARAM_STR("fileType"));
		
		String suffix = null; //转换的文件格式		
		if(FilePatten.isVideo(fileType)){//视频类型
			
			this.fileKind = BaseFile.FILE_KIND_VIDEO;
			
			if(fileType.equals("mp4") || fileType.equals("flv") || fileType.equals("mov")){
				suffix = "." + fileType;			
				this.turnStatus = BaseFile.TURN_STATUS_FINISHED;	
				copySource = true;
			}else{
				suffix = FLV;	
				this.turnStatus = BaseFile.TURN_STATUS_NOT_TURNED;
			}			
		
			
		}else if(FilePatten.isAudio(fileType)){
			if(fileType.equals("mp3") || fileType.equals("m4a") || fileType.equals("oga")){
				suffix = "." + fileType;				
			}else{
				suffix = MP3;								
			}
			
			this.turnStatus = BaseFile.TURN_STATUS_NOT_TURNED;
			this.fileKind = BaseFile.FILE_KIND_AUDIO;
			
		}else if(FilePatten.isOfficeFile(fileType)){//office文件类型
			suffix = SWF;
			this.turnStatus = BaseFile.TURN_STATUS_NOT_TURNED;
			this.fileKind = BaseFile.FILE_KIND_OFFICE;
			
		}else if(FilePatten.isPicture(fileType)){//图片类型
			this.turnStatus = BaseFile.TURN_STATUS_NO_NEED;
			this.fileKind = BaseFile.FILE_KIND_PICTURE;
			if(fileType.equals("svg")){
				suffix = "." + fileType;		
				copySource = true;
			}
			
		}else{//其他
			this.turnStatus = BaseFile.TURN_STATUS_NO_NEED;
			this.fileKind = BaseFile.FILE_KIND_OTHER;
		}
			
		
		//2008/12-01/874b168d-c9a7-4dfb-a54d-7e99b2ee4ef8.swf(注意预览路径必须将\转换为/，否则预览失败)
		if(suffix!=null){					
			this.transferPath = new StringBuilder(this.datePath).append(File.separator).append(UUID.randomUUID().toString()).append(suffix)
					   .toString().replaceAll("\\\\", "/");;
		}
	}
	
	
	

	
	/**
	 * 生成文件缩略图
	 * 图片文件则进行压缩生成缩略图，其他按照文件类型获取默认图标
	 * @param uploadFile
	 * @param virtualPath
	 */
	public void generatePattern(CommonsMultipartFile uploadFile,String virtualPath){
		
		if(fileKind == BaseFile.FILE_KIND_PICTURE){//压缩图片
			if(!fileType.toLowerCase().equals("svg")){																
				try {															
					//如果图片大于500kb进行大图压缩
					if(uploadFile.getSize()>(512*1024)){
						ImgCompress icp = new ImgCompress(uploadFile,fileType,virtualPath,datePath);			
						this.pattern = icp.resizeFix(mid_width, mid_heigth);
						this.bigPattern = icp.resizeFix(big_width, big_heigth);	
						
					}else{//原图作为缩略图	
						
						this.pattern = new StringBuilder(this.datePath).append(File.separator).append(UUID.randomUUID().toString()).append(".").append(fileType).toString();
						this.bigPattern = pattern;
						this.copySource = true;
					}
					
				} catch (Exception e) {				
					this.pattern = FilePatten.PINTUREPATTEN;
					this.bigPattern = FilePatten.PINTUREPATTEN;						
				
				}			

			}else{
				this.pattern = new StringBuilder(this.datePath).append(File.separator).append(UUID.randomUUID().toString()).append(".svg").toString();
				this.bigPattern = pattern;
			}
						
		}else{		
			this.pattern = FilePatten.getPatternUrl(fileType);
		}
		
	}
	
	
	
	public void generatePattern(String virtualPath){
				
		this.pattern = FilePatten.getPatternUrl(fileType);
				
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


	public String getDatePath() {
		return datePath;
	}


	public String getTransferPath() {
		return transferPath;
	}


	public String getFileType() {
		return fileType;
	}


	public String getPattern() {
		return pattern;
	}


	public String getBigPattern() {
		return bigPattern;
	}


	public short getFileKind() {
		return fileKind;
	}


	public short getTurnStatus() {
		return turnStatus;
	}


	public boolean isCopySource() {
		return copySource;
	}

	public static String getExt(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	
	public static String makeNowDatePath(){
		SimpleDateFormat year = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd"); 		 
		 
		Date currentDate = new Date();	
		
		return year.format(currentDate) + File.separator + monthDay.format(currentDate) + File.separator;
	}
	
	/**
	 * 生成一个保持文件路径
	 * @param fileType
	 * @return
	 */
	public static String makeSavePath(String prefix,String fileType){
		SimpleDateFormat year = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd"); 		 
		 
		Date currentDate = new Date();	
		String datePath = year.format(currentDate) + File.separator + monthDay.format(currentDate);
		if(!StringUtils.isBlank(prefix)){
			File dir  = new File(prefix+datePath); 
			if(!dir.exists()){
				dir.mkdirs();
			}
		}
		
		
		return  prefix+datePath+File.separator 
				+ UUID.randomUUID().toString() + "." + fileType;
	}
	
	
	public static String makeSavePath2(String prefix,String fileType){
		SimpleDateFormat year = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd"); 		 
		 
		Date currentDate = new Date();	
		String datePath = year.format(currentDate) + File.separator + monthDay.format(currentDate);
		if(!StringUtils.isBlank(prefix)){
			File dir  = new File(prefix+datePath); 
			if(!dir.exists()){
				dir.mkdirs();
			}
		}
		
		
		return  datePath+File.separator 
				+ UUID.randomUUID().toString() + "." + fileType;
	}

}
