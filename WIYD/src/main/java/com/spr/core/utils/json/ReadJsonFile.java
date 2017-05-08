package com.spr.core.utils.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 *  读取json文件的内容
 **/
public class ReadJsonFile {

	public static String readFile(InputStream inputStream) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			
			bfReader.close();
		} catch (UnsupportedEncodingException localUnsupportedEncodingException ) {
			
		}

		return filter(builder.toString());
	}
	
	
	
	public static String readFile(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			
			bfReader.close();
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			
		}

		return filter(builder.toString());
	}
	
	
	private static String filter(String input)
	  {
	    return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
	  }
	
}
