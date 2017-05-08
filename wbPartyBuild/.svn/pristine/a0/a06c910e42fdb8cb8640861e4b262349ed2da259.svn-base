package com.wb.core.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;



/**
 * 图片压缩工具
 * @author zjr
 *
 */
public class ImgCompress {
	private String prefixPath;
	private String datePath;
	private Image img;
	private int width;
	private int height;
    private String type;

    
  /**      
   * @param source			源文件
   * @param type			文件类型
   * @param prefixPath		保存前部分路径(zonePath.path)	
   * @param datePath		日期路径(2015/07-10)
   * @throws IOException
   */
	public ImgCompress(CommonsMultipartFile uploadFile,String type,String prefixPath,String datePath) throws IOException {
		this.img = ImageIO.read(uploadFile.getInputStream());    // 构造Image对象		
		this.width = img.getWidth(null);    // 得到源图宽
		this.height = img.getHeight(null);  // 得到源图长
		this.type = type;       			// 文件类型
		this.prefixPath = prefixPath;		// 保存文件路径前缀
		this.datePath = datePath;			// 保存文件部分路径

	}
	
	
	
	
	
	public ImgCompress(String srcPath) throws IOException {
		File file = new File(srcPath);
		if(!file.exists()){
			throw new RuntimeException("文件不存在！");
		}
		this.img = ImageIO.read(file);
		this.width = img.getWidth(null);    // 得到源图宽
		this.height = img.getHeight(null);  // 得到源图长

	}





	/**
	 * 按照宽度还是高度进行压缩
	 * @param w int 最大宽度
	 * @param h int 最大高度
	 */
	public String resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			return resizeByWidth(w);
		} else {
			return resizeByHeight(h);
		}
	}
	
	
	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w int 新宽度
	 */
	public String resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		return resize(w, h);
	}
	
	
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 */
	public String resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		return resize(w, h);
	}
	
	
	/**
	 * 按照宽度还是高度进行压缩
	 * @param w int 最大宽度
	 * @param h int 最大高度
	 */
	public void resizeFix2(int w, int h,String savePath) throws IOException {
		if (width / height > w / h) {
			 resizeByWidth2(w,savePath);
		} else {
			resizeByHeight2(h,savePath);
		}
	}
	
	
	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w int 新宽度
	 */
	public void resizeByWidth2(int w,String savePath) throws IOException {
		int h = (int) (height * w / width);
		 resize2(w, h,savePath);
	}
	
	
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 */
	public void resizeByHeight2(int h,String savePath) throws IOException {
		int w = (int) (width * h / height);
		resize2(w, h,savePath);
	}
	
	
	
	
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public String resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB); 
		//image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图		
		image.getGraphics().drawImage(img.getScaledInstance(w, h,  Image.SCALE_SMOOTH), 0, 0, null);//压缩后质量比较好
		//压缩图片文件名
		String fileName = UUID.randomUUID().toString() + "." + type;
					
		
		//建立文件夹(E:\cloudOne\personDisk\Compress\2015\01-10)
		String directory = prefixPath + File.separator+ datePath;
		File dirFile = new File(directory);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		//压缩图片保存路径(E:\cloudOne\personDisk\CompressPictures\2015\01-10\4fedfkk34ss.jpg)
		File destFile = new File(directory+ File.separator + fileName);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
		
		//返回的图片路径(1/2015/01/10/4fedfkk34ss.jpg)
		return  (datePath + File.separator + fileName).replaceAll("\\\\", "/");
	}
	
	
	public void resize2(int w, int h,String savePath) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB); 
		//image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图		
		image.getGraphics().drawImage(img.getScaledInstance(w, h,  Image.SCALE_SMOOTH), 0, 0, null);//压缩后质量比较好
	

		File destFile = new File(savePath);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
				
		
	}
	
	
	
	public static String getExt(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	
	
	
	
	
   public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public static Dimension getImageDim(String type,CommonsMultipartFile uploadFile) {
	     	     
	       //解码具有给定后缀的文件
	       Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(type);
	 
	       if (iter.hasNext()) {
	              ImageReader reader = iter.next();
	              ImageInputStream stream = null; 
	              try {
	                 stream = new  MemoryCacheImageInputStream(uploadFile.getInputStream());
	           
	                 reader.setInput(stream);
	                 int width = reader.getWidth(reader.getMinIndex());
	                 int height = reader.getHeight(reader.getMinIndex());
	                  
	                  return new Dimension(width, height);
	                  
	              } catch (IOException e) {
	                 e.printStackTrace();
	              } finally {
	            	  if(stream!=null){
	            		  try {
							stream.close();
						  } catch (IOException e) {							
							 e.printStackTrace();
						  }
	            	  }
	            	 
	                  reader.dispose();
	             }
	          }
	       
	         
	         return null;
	    }
	

	  
	  public static Dimension getImageDim(String type,File file) {
  	     
	       //解码具有给定后缀的文件
	       Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(type);
	 
	       if (iter.hasNext()) {
	              ImageReader reader = iter.next();
	              ImageInputStream stream = null; 
	              try {
	                 stream = new  FileImageInputStream(file);
	           
	                 reader.setInput(stream);
	                 int width = reader.getWidth(reader.getMinIndex());
	                 int height = reader.getHeight(reader.getMinIndex());
	                  
	                  return new Dimension(width, height);
	                  
	              } catch (IOException e) {
	                 e.printStackTrace();
	              } finally {
	            	  if(stream!=null){
	            		  try {
							stream.close();
						  } catch (IOException e) {							
							 e.printStackTrace();
						  }
	            	  }
	            	 
	                  reader.dispose();
	             }
	          }
	       
	         
	         return null;
	    }
	
	  
	   
}