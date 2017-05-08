package com.wb.core.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.util.List;
import java.util.UUID;

import com.wb.core.utils.pojo.VideoImg;
import com.wb.core.utils.pojo.VideoInfoItem;
import com.wb.web.system.entity.ZonePath;

public class VideoInfoUtils {
	private static final String FFMPEG_PATH_KEY = "ffmpeg_path"; 	
    private static Encoder encoder;
    private static  String FFMPEG_PATH = "D:/video/ffmpeg.exe";
    static{
    	encoder = new Encoder();
    	 
	    String classBaseDir =  ConvertFile.class.getClassLoader().getResource("/").getPath();		
		String propertiesfile = classBaseDir+"convertFileConfig.properties";
		
		FFMPEG_PATH= PropertiesUtil.GetValueByKey(propertiesfile, FFMPEG_PATH_KEY);
    }
    
    
    /**
     * 视频截图
     * @param path		 源文件路径
     * @param fileType   文件类型
     * @param datePath   日期路径
     * @param prefixPath 存储空间前缀路径
     * @param smallSize  小分辨率
     * @param bigSize	  大分辨率
     * @return
     */
    public static VideoImg processImg(String path,String fileType,Long vdieoTime,String datePath,String prefixPath,
    		String smallSize,String bigSize){
    	if(vdieoTime<1){
    		return null;
    	}
    	
    	int time = 36; 
    	if(vdieoTime<36){
    		time = (int) (vdieoTime/2);
    	}
    	
    	File file = new File(path);
		if(!file.exists()) {
			return null;
		}

		String directory = prefixPath +File.separator+ datePath;
		File dirFile = new File(directory);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		
		String smallImgName = UUID.randomUUID().toString() + ".jpg";
		String bigImgName = UUID.randomUUID().toString() + ".jpg";
		String smallImgPath = dirFile.getAbsolutePath() + File.separator + smallImgName;
		String bigImgPath = dirFile.getAbsolutePath() + File.separator + bigImgName;
		
		VideoImg videoImg = new VideoImg();
		if(processImg(path, smallSize,time, smallImgPath)){//截取小图片
			videoImg.setSmallImg((datePath + File.separator + smallImgName).replaceAll("\\\\", "/"));
		}
		
		if(processImg(path, bigSize,time, bigImgPath)){//截取大图片
			videoImg.setBigImg((datePath + File.separator + bigImgName).replaceAll("\\\\", "/"));
		}
    	
		return videoImg;
    }
    
   
    public static String processImg(String path,String fileType,Long vdieoTime,String datePath,String prefixPath,
    								String size){
    	if(vdieoTime<1){
    		return null;
    	}
    	
    	int time = 36; 
    	if(vdieoTime<36){
    		time = (int) (vdieoTime/2);
    	}
    	
    	File file = new File(path);
		if(!file.exists()) {
			return null;
		}

		String directory = prefixPath +File.separator+ datePath;
		File dirFile = new File(directory);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}

		String imgName = UUID.randomUUID().toString() + ".jpg";
		String imgPath = dirFile.getAbsolutePath() + File.separator + imgName;
		
		
		if(processImg(path,size,time,imgPath)){//截取大图片
			return (datePath + File.separator + imgName).replaceAll("\\\\", "/");
		
		}else{
			return null;
		}
    	
		
    }
    
    
    
    
    /**
     * 截取视频画面
     * @param path
     * @param size
     * @param imgSavePath
     * @return
     */
    public static boolean processImg(String path,String size,Integer startTime,String imgSavePath) {    	
    	
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(FFMPEG_PATH);
		commands.add("-i");
		commands.add(path);
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss");
		commands.add(startTime.toString());
		commands.add("-t");
		commands.add("0.001");
		commands.add("-s");
		commands.add(size);
		commands.add(imgSavePath);
		
		try {

			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			/*Process process = builder.start();

			 InputStream in =process.getInputStream();  
			  
	         byte[] re = new byte[1024];  
	  
	         System.out.print("正在进行截图，请稍候");  	  
	         while (in.read(re) != -1) {  	  
	        	 System.out.print(".");  	  
	         }  	  
	         	  
	         in.close();  	  
	         System.out.println("视频截图完成...");  */
	         
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}   	   
    	
	}
    
   public static VideoInfoItem getVideoInfo(File source){
    	 try {
			MultimediaInfo m = encoder.getInfo(source);
			
			return new VideoInfoItem(m.getDuration(),getVideoTime(m.getDuration()), m.getVideo().getSize().getWidth()+"",
								m.getVideo().getSize().getHeight()+"");
    	    	 
    	 } catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
    }
   
    
    public static String getVideoTime(long time){

		if(time > (60*60*1000)){//大于一小时
			int hour = (int) (time/(60*60*1000));
			int minute = (int) ( (time - (hour*(60*60*1000))) / (60*1000) );
			int second = (int) ( (time - (hour*(60*60*1000) + minute*(60*1000) )) / (1000) );
			
			return hour+"小时"+minute+"分"+second+"秒";
		
		}else if(time > (60*1000) && time < (60*60*1000)){//大于一分钟且小于一小时
			int minute = (int) ( time / (60*1000) );
			int second = (int) ( (time - ( minute*(60*1000) )) / (1000) );
			
			return minute+"分"+second+"秒";
		
		}else{//小于一分钟

			int second = (int) ( time / (1000) );
			
			return second+"秒";
		}
		
				
	}
	
  
    
}
