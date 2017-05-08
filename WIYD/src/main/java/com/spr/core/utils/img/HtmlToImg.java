package com.spr.core.utils.img;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;

import org.fit.cssbox.demo.ImageRenderer;
import org.springframework.util.Assert;

public class HtmlToImg {

	/**
	 * url的页面转svg
	 * @param url
	 * @param saveFilePath
	 * @return
	 */
	public static boolean urlToSvg(String url,String saveFilePath){
		Assert.hasText(url, "param[url] could not be null");
		Assert.hasText(saveFilePath, "param[saveFilePath] could not be null");
		
		try {
			ImageRenderer render = new ImageRenderer();
			
			FileOutputStream out = new FileOutputStream(new File(saveFilePath));
			render.setWindowSize(new Dimension(2500, 800), true);
			render.renderURL(url, out, ImageRenderer.Type.SVG);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
