package com.wb.core.utils;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.wb.core.utils.img.ImageExIf;
import com.wb.web.system.entity.ZonePath;

/**
 * 文件图案生成工具
 * @author zjr
 *
 */
public class PatternHelper {

	private final int small_width = 76;
	private final int small_heigth = 76;
	private final int mid_width = 320;
	private final int mid_heigth = 320;
	private final int big_width = 1024;
	private final int big_heigth = 768;
	private String pattern = null;
	private String bigPattern = null;
	private String widthRatio;	//水平分辨率
	private String heigthRatio; //垂直分辨率
	private String width;		//图片高度
	private String heigth;		//图片宽度
	private String marker;		//制造商
	private String modelNum;	//设备型号	
	private Date produceDate;   //产生日期
	
  /**
   * 构造函数
   * @param type   		文件类型
   * @param source		源文件流
   * @param datePath	日期路径
   * @param prefixPath	前部分路径
   */
	public PatternHelper(String type,CommonsMultipartFile uploadFile,String prefixPath,String midPath,String datePath){
		
		if(FilePatten.isPicture(type)){//压缩图片
		
			try {								
				ImgCompress icp = new ImgCompress(uploadFile,type,prefixPath + ZonePath.Compress,datePath);			
				this.pattern = icp.resizeFix(small_width, small_heigth);
				this.bigPattern = icp.resizeFix(big_width, big_heigth);				
			    							
			} catch (Exception e) {				
				this.pattern = FilePatten.PINTUREPATTEN;
				this.bigPattern = FilePatten.PINTUREPATTEN;											  
			}
			
			
			try {
				ImageExIf imageExIf = new ImageExIf(uploadFile);
				this.width = imageExIf.getWidth();
				this.heigth = imageExIf.getHeigth();
				this.widthRatio = imageExIf.getXResolution();
				this.heigthRatio = imageExIf.getYResolution();
				this.marker = imageExIf.getMake();
				this.modelNum = imageExIf.getModel();
				this.produceDate = imageExIf.getDateTime();
				
			} catch (JpegProcessingException | IOException e) {
				Dimension dimension = ImgCompress.getImageDim(type, uploadFile);
				this.widthRatio = dimension.getHeight() + "";
				this.heigthRatio = dimension.getWidth() + "";
				e.printStackTrace();
			}
			
			
		}else{
		
			this.pattern = FilePatten.getPatternUrl(type);
		}
				
		
	}

	
	
	public PatternHelper(String type,CommonsMultipartFile uploadFile,String prefixPath,String datePath){
		
		if(FilePatten.isPicture(type)){//压缩图片
			try {								
				ImgCompress icp = new ImgCompress(uploadFile,type,prefixPath,datePath);			
				this.pattern = icp.resizeFix(mid_width, mid_heigth);
				
				//如果图片大于1MB进行大图压缩
				if(uploadFile.getSize()>(1*1024*1024)){
					this.bigPattern = icp.resizeFix(1024, 768);
				}
				
			} catch (Exception e) {				
				this.pattern = FilePatten.PINTUREPATTEN;
										  
			}			
			
			
			try {
				ImageExIf imageExIf = new ImageExIf(uploadFile);
				this.width = imageExIf.getWidth();
				this.heigth = imageExIf.getHeigth();
				this.widthRatio = imageExIf.getXResolution();
				this.heigthRatio = imageExIf.getYResolution();
				this.marker = imageExIf.getMake();
				this.modelNum = imageExIf.getModel();
				this.produceDate = imageExIf.getDateTime();
				
			} catch (JpegProcessingException | IOException e) {
				Dimension dimension = ImgCompress.getImageDim(type, uploadFile);
				this.widthRatio = dimension.getHeight() + "";
				this.heigthRatio = dimension.getWidth() + "";
				e.printStackTrace();
			}
			
		}else{
		
			this.pattern = FilePatten.getPatternUrl(type);
		}
		
	}
	
	
	
	

	public String getPattern() {
		return pattern;
	}
	
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	
	public String getBigPattern() {
		return bigPattern;
	}
	
	
	public void setBigPattern(String bigPattern) {
		this.bigPattern = bigPattern;
	}
	
	
	
	
	public String getWidthRatio() {
		return widthRatio;
	}
	
	
	public void setWidthRatio(String widthRatio) {
		this.widthRatio = widthRatio;
	}
	
	
	public String getHeigthRatio() {
		return heigthRatio;
	}
	
	
	public void setHeigthRatio(String heigthRatio) {
		this.heigthRatio = heigthRatio;
	}
	
	
	
	
	public int getSmall_width() {
		return small_width;
	}
	
	
	public int getSmall_heigth() {
		return small_heigth;
	}
	
	
	public int getBig_width() {
		return big_width;
	}
	
	
	public int getBig_heigth() {
		return big_heigth;
	}


	public String getWidth() {
		return width;
	}


	public String getHeigth() {
		return heigth;
	}


	public String getMarker() {
		return marker;
	}


	public String getModelNum() {
		return modelNum;
	}


	public Date getProduceDate() {
		return produceDate;
	}
		
		
		
		
	

	
	
	
	
}
