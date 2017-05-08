package com.wb.core.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.wb.core.common.exception.MyException;
  
   
  
/**  
 * 图片水印  
 */  
public class ImageWaterMark {   
  
	public static final int LEFT_TOP_POSITION = 0;  
	public static final int LEFT_BOTTOM_POSITION = 1; 
	public static final int RIGHT_TOP_POSITION = 2; 
	public static final int RIGHT_BOTTOM_POSITION = 3; 
	public static final int CENTER_POSITION = 4; 
	public static final int CUSTOM_POSITION = 5; 
  
  
    /**  
     * 给图片添加水印  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     */  
    public static boolean markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath,Integer markType,Float alpha,Integer marginX, Integer marginY) {   
        return markImageByIcon(iconPath, srcImgPath, targerPath,markType,alpha, null,marginX,marginY);   
    }   
  
    /**  
     * 给图片添加水印、可设置水印图片旋转角度  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     * @param degree 水印图片旋转角度  
     */  
    public static boolean markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath,Integer markType,Float alpha,Integer degree,Integer marginX, Integer marginY) {   
        OutputStream os = null;   
        try {   
            Image srcImg = ImageIO.read(new File(srcImgPath));   
            int srcWidth = srcImg.getWidth(null);
            int srcHeigth = srcImg.getHeight(null);
   
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),   
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
  
            // 得到画笔对象   
            // Graphics g= buffImg.getGraphics();   
            Graphics2D g = buffImg.createGraphics();   
  
            // 设置对线段的锯齿状边缘处理   
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
  
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg   
            			.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);   
  
            if (null != degree) {   
                // 设置水印旋转   
                g.rotate(Math.toRadians(degree),   
                        (double) buffImg.getWidth() / 2, (double) buffImg   
                                .getHeight() / 2);   
            }   
  
            //水印图象的路径水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);   
  
            // 得到Image对象。   
            Image img = imgIcon.getImage();   
  
          
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));  // 透明度     
  
            // 表示水印图片的位置   
            int[] wh = drawSize(img, srcWidth, srcHeigth, markType,marginX == null? 0 :marginX,marginY== null? 0 :marginY);
          
            g.drawImage(img, wh[0], wh[1], null);   
  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
  
            g.dispose();   
  
            os = new FileOutputStream(targerPath);   
  
            // 生成图片   
            ImageIO.write(buffImg, "JPG", os);   
  
            System.out.println("图片完成添加Icon印章。。。。。。"); 
            
            return true;
        } catch (MyException me) {   
             throw me;   
        }catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        } 
        
        return false;
    }  
    
    
    private static int[] drawSize(Image img,int srcWidth,int srcHeigth,Integer markType,
    		 int marginX, int marginY){
    	int w = 5;
    	int h = 5;
    	int mw = img.getWidth(null);		//水印宽度
    	int mh = img.getHeight(null);		//水印高度
    			
    	if(markType == LEFT_TOP_POSITION){
    		w = 5;
    		h = 5;
    	
    	}else if(markType == LEFT_BOTTOM_POSITION){
    		w = 5;
    		h = srcHeigth-mh-5;
    		
    	
    	}else if(markType == RIGHT_TOP_POSITION){ 
    		w = srcWidth-mw-5;
    		h = 5;   	
    		
    	}else if(markType == RIGHT_BOTTOM_POSITION){  
    		w = srcWidth-mw-5;
    		h = srcHeigth-mh-5;    
    	
    	}else if(markType == CENTER_POSITION){
    		 w = (srcWidth-mw)/2;
    		 h = (srcHeigth-mh)/2; 	
    	
    	}else if(markType == CUSTOM_POSITION){
    		w = marginX;
    		h = marginY;
    		
    	}
    	
    	// 校验水印是否全部落在图片中
		if (w<= 0 ||h<= 0 || w > srcWidth - mw || h > srcHeigth - mh) {
			throw new MyException("添加水印失败！（水印未全部落在图片上，请重新设置打印位置）");
		}
		
		return new int[]{w,h};
    }
    
    
    
} 