package com.wb.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileMd5 {

	public static String fileMD5(CommonsMultipartFile uploadFile)  {
	      // 缓冲区大小（这个可以抽出一个参数）
	      int bufferSize = 256 * 1024;
	    
	      DigestInputStream digestInputStream = null;

	      try {
	         // 拿到一个MD5转换器（同样，这里可以换成SHA1）
	         MessageDigest messageDigest = MessageDigest.getInstance("MD5");	 
	         // 使用DigestInputStream	     
	         digestInputStream = new DigestInputStream(uploadFile.getInputStream(),messageDigest);	 
	         // read的过程中进行MD5处理，直到读完文件

	         byte[] buffer =new byte[bufferSize];
	         while (digestInputStream.read(buffer) > 0);

	         // 获取最终的MessageDigest
	         messageDigest= digestInputStream.getMessageDigest();	 
	         // 拿到结果，也是字节数组，包含16个元素
	         byte[] resultByteArray = messageDigest.digest(); 
	         // 同样，把字节数组转换成字符串
	         return byteArrayToHex(resultByteArray);	 

	      } catch (NoSuchAlgorithmException e) {
	         e.printStackTrace();
	         
	    	  return null;
	      } catch (IOException e) {
	    	  e.printStackTrace();
	    	  
		      return null;
		      
		  } finally {
			  
	         try {
	            digestInputStream.close();
	            uploadFile.getInputStream().close();
	         } catch (Exception e) {
	        	 
	         }
	         
	        
	      }
	   }

	
	public static String fileMD5(InputStream inputStream)  {
	      // 缓冲区大小（这个可以抽出一个参数）
	      int bufferSize = 256 * 1024;
	    
	      DigestInputStream digestInputStream = null;

	      try {
	         // 拿到一个MD5转换器（同样，这里可以换成SHA1）
	         MessageDigest messageDigest = MessageDigest.getInstance("MD5");	 
	         // 使用DigestInputStream	     
	         digestInputStream = new DigestInputStream(inputStream,messageDigest);	 
	         // read的过程中进行MD5处理，直到读完文件

	         byte[] buffer =new byte[bufferSize];
	         while (digestInputStream.read(buffer) > 0);

	         // 获取最终的MessageDigest
	         messageDigest= digestInputStream.getMessageDigest();	 
	         // 拿到结果，也是字节数组，包含16个元素
	         byte[] resultByteArray = messageDigest.digest(); 
	         // 同样，把字节数组转换成字符串
	         return byteArrayToHex(resultByteArray);	 

	      } catch (NoSuchAlgorithmException e) {
	         e.printStackTrace();
	         
	    	  return null;
	      } catch (IOException e) {
	    	  e.printStackTrace();
	    	  
		      return null;
		      
		  } finally {
			  
	         try {
	            digestInputStream.close();
	            inputStream.close();
	         } catch (Exception e) {
	         }
	         
	        
	      }
	   }

	
	
	
	
	
	
	public static String fileMD5(File inputFile)  {
	      // 缓冲区大小（这个可以抽出一个参数）
	      int bufferSize = 256 * 1024;
	      FileInputStream fileInputStream = null;
	      DigestInputStream digestInputStream = null;

	      try {
	         // 拿到一个MD5转换器（同样，这里可以换成SHA1）
	         MessageDigest messageDigest = MessageDigest.getInstance("MD5");	 
	         // 使用DigestInputStream
	         fileInputStream = new FileInputStream(inputFile);
	         digestInputStream = new DigestInputStream(fileInputStream,messageDigest);	 
	         // read的过程中进行MD5处理，直到读完文件

	         byte[] buffer =new byte[bufferSize];
	         while (digestInputStream.read(buffer) > 0);
 
	         // 获取最终的MessageDigest
	         messageDigest= digestInputStream.getMessageDigest();	 
	         // 拿到结果，也是字节数组，包含16个元素
	         byte[] resultByteArray = messageDigest.digest(); 
	         // 同样，把字节数组转换成字符串
	         return byteArrayToHex(resultByteArray);	 

	      } catch (NoSuchAlgorithmException e) {
	         e.printStackTrace();
	    	  return null;
	      } catch (IOException e) {
	    	  e.printStackTrace();
		      return null;
		      }
	      finally {
	         try {
	            digestInputStream.close();
	            fileInputStream.close();
	         } catch (Exception e) {
	         }
	        
	      }
	   }

	
	//下面这个函数用于将字节数组换成成16进制的字符串
	   public static String byteArrayToHex(byte[] byteArray) {
	      // 首先初始化一个字符数组，用来存放每个16进制字符
	      char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
	      // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
	      char[] resultCharArray =new char[byteArray.length * 2];	 

	      // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
	      int index = 0;
	      for (byte b : byteArray) {
	         resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
	         resultCharArray[index++] = hexDigits[b& 0xf];
	      }	 
	      // 字符数组组合成字符串返回
	      return new String(resultCharArray);
	}
		
	   
	  /*public static void main(String[] args) throws IOException {
			long begin = System.currentTimeMillis();
            File file=new File("D:/前端资料/H-ui.admin_v2.3.2.zip");
			String md5 = fileMD5(new FileInputStream(file));

			long end = System.currentTimeMillis();
			System.out.println("md5:" + md5);
			System.out.println("time:" + ((end - begin)) + "ms");

		}*/

	   
	   
}
