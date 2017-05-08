package com.spr.core.utils.img;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;



public class ImageExIf {
	private String width;
    private String heigth;
    private String Make;// 生产者 指产品生产厂家 　　  
    private String Model;// 型号 指设备型号 　　    
    private String XResolution; // X方向分辨率  
    private String YResolution;// Y方向分辨率  
    private Date DateTime;// 日期和时间  
	
	
    public ImageExIf(CommonsMultipartFile uploadFile) throws JpegProcessingException, IOException{
    	Metadata metadata = JpegMetadataReader.readMetadata(uploadFile.getInputStream());
    	
    	if(metadata.containsDirectoryOfType(ExifIFD0Directory.class)){
    		ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        	
        	this.width = exifIFD0Directory.getString(ExifIFD0Directory.TAG_IMAGE_WIDTH);
        	this.heigth = exifIFD0Directory.getString(ExifIFD0Directory.TAG_IMAGE_HEIGHT);
        	this.Make = exifIFD0Directory.getString(ExifIFD0Directory.TAG_MAKE);
        	this.Model  = exifIFD0Directory.getString(ExifIFD0Directory.TAG_MODEL);
        	this.XResolution = exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_X_RESOLUTION);
        	this.YResolution = exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_Y_RESOLUTION);
        	this.DateTime = exifIFD0Directory.getDate(ExifIFD0Directory.TAG_DATETIME);
    	
    	}else if(metadata.containsDirectoryOfType(JpegDirectory.class)){
    		JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
    		this.width = jpegDirectory.getString(JpegDirectory.TAG_IMAGE_WIDTH);
        	this.heigth = jpegDirectory.getString(JpegDirectory.TAG_IMAGE_HEIGHT);
    	}
    	
    }


    
    
	public String getWidth() {
		return width;
	}




	public String getHeigth() {
		return heigth;
	}




	public String getMake() {
		return Make;
	}


	public String getModel() {
		return Model;
	}


	public String getXResolution() {
		return XResolution;
	}


	public String getYResolution() {
		return YResolution;
	}


	public Date getDateTime() {
		return DateTime;
	}
    
    
    
    
    
    
	
//	public static void main(String[] args) {
//
//	
//
//		try {
//			long s = System.currentTimeMillis();
//			//File jpegFile = new File("D:\\FDA_STORE\\FAMILY_FILE\\2016\\04-01\\4a49a599-b213-4be0-8378-f433230cb13a.jpg");
//			File jpegFile = new File("D:\\21-niutuku.com-2055.jpg");
//			
//			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);			
//			/*JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
//			System.out.println(jpegDirectory.getTagName(JpegDirectory.TAG_IMAGE_WIDTH) +"："+ jpegDirectory.getDescription(JpegDirectory.TAG_IMAGE_WIDTH));			
//			System.out.println(jpegDirectory.getTagName(JpegDirectory.TAG_IMAGE_HEIGHT) +"："+ jpegDirectory.getDescription(JpegDirectory.TAG_IMAGE_HEIGHT));
//			*/
//			
//			ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
//			String sgh = exifIFD0Directory.getString(ExifIFD0Directory.TAG_IMAGE_HEIGHT);
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_IMAGE_HEIGHT) +"："+ exifIFD0Directory.getString(ExifIFD0Directory.TAG_IMAGE_HEIGHT));	
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_IMAGE_WIDTH) +"："+ exifIFD0Directory.getString(ExifIFD0Directory.TAG_IMAGE_WIDTH));	
//			
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_X_RESOLUTION) +"："+ 
//			exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_X_RESOLUTION));	
//			
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_Y_RESOLUTION) +"："+ exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_Y_RESOLUTION));	
//	
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_IMAGE_WIDTH) +"："+ exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_IMAGE_WIDTH));	
//	
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_MAKE) +"："+ exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_MAKE));	
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_MODEL) +"："+ exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_MODEL));
//			System.out.println(exifIFD0Directory.getTagName(ExifIFD0Directory.TAG_DATETIME) +"："+ exifIFD0Directory.getDate(ExifIFD0Directory.TAG_DATETIME));
//			
//			
//		/*	Iterator<Directory> exifs = metadata.getDirectories().iterator();
//			while (exifs.hasNext()) {
//				System.out.println(exifs.next().getClass());	
//				 Iterator<Tag> tags = exifs.next().getTags().iterator();
//			     while (tags.hasNext()) {
//			       Tag tag = (Tag)tags.next();
//			       
//			       System.out.println(tag);
//			     }
//		
//			}*/
//		  	   							    
//		     System.out.println(System.currentTimeMillis()-s);
//		} catch (JpegProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	     
//	     
//	}
	
	
}
