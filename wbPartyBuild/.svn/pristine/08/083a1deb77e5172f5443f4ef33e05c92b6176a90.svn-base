package com.wb.core.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.wb.core.common.dto.BaseQueryDTO;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.interceptors.DateConvertEditor;
import com.wb.core.spring.security.user.MyUser;

public class BaseController implements Serializable{

	private static final long serialVersionUID = 1L;	
	public Logger logger = Logger.getLogger(getClass());

	
	
	private final String mDataProp = "mDataProp_";
	public  final String ADD_SUCCESS_MESSAGE = "添加成功！";
	public  final String EDIT_SUCCESS_MESSAGE = "修改成功！";
	public  final String DEL_SUCCESS_MESSAGE = "删除成功！";
	public  final String UPLOAD_SUCCESS_MESSAGE = "上传成功！";

	
	/**
	 * 过滤html特殊字符
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		//binder.registerCustomEditor(String.class, new XXSEscapeEditor());
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}
	
	
	/**
	 * 封装dataTable请求参数,包括开始位置，页面大小和排序信息
	 * @param queryDto
	 */
	public void wrapTableQueryParams(HttpServletRequest request,BaseQueryDTO queryDto){		
		Integer iDisplayStart = null; //查询起始位置
		String iDisplayStartParam = request.getParameter("iDisplayStart");
		if(iDisplayStartParam!=null){
			iDisplayStart = Integer.parseInt(iDisplayStartParam) ; 			
 		}
		Integer iDisplayLength = null ; //查询长度
		String iDisplayLengthParam = request.getParameter("iDisplayLength");
		if(iDisplayLengthParam!=null){
			iDisplayLength = Integer.parseInt(iDisplayLengthParam) ;
		}
		
 		String iSortCol_0 = request.getParameter("iSortCol_0") == null? "id":request.getParameter("iSortCol_0");//排序字段编号
 		String sSortDir_0 = request.getParameter("sSortDir_0") == null? "asc":request.getParameter("sSortDir_0");//排序方式
 		
		queryDto.setStartQuery(iDisplayStart == null? 0:iDisplayStart);
		queryDto.setPageSize(iDisplayLength == null? queryDto.getPageSize():iDisplayLength);
		
		String sidx = request.getParameter(mDataProp+iSortCol_0);//获取排序字段
		queryDto.setSidx(sidx);
		queryDto.setSord(sSortDir_0);
		queryDto.setsEcho(request.getParameter("sEcho"));
		
		
	} 
	
	
	/**
	 * 处理前台传来的菜单名称
	 * @param request
	 */
	public void wrapMenuTitle(HttpServletRequest request){
		 String title = request.getParameter("title");
		 String topTitle = request.getParameter("top");
		 String tailTitle = request.getParameter("tail");
		
		 if(!StringUtils.isBlank(title)){			 				 
			 try {
				 request.setAttribute("TITLE", URLDecoder.decode(title, "utf-8"));
		     
			 } catch (UnsupportedEncodingException e) {				
				 e.printStackTrace();
			  }
			
		 }
		 
		 if(!StringUtils.isBlank(topTitle)){	
			 
			 try {
				request.setAttribute("TOP_TITLE", URLDecoder.decode(topTitle, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		 }
		 
		 if(!StringUtils.isBlank(tailTitle)){	
			 
			 try {
				request.setAttribute("TAIL_TITLE", URLDecoder.decode(tailTitle, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		 }
	}
	
	


	/**
	 * 检测用户是否登录
	 * @return
	 * @author wb_java_zjr
	 */
	public boolean checkWheatherLogined(){	
	   Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   if(obj == null){
		   	return false;		   
	   }
	   
	   if(obj instanceof MyUser){
		   return true;
	   }else{
		   return false;
	   }		
		 
	}
	
	
	 /**
	  * 获取当前MyUser实体
	  * @return
	  * @author wb_java_zjr
	 */ 
	 public MyUser getNowUser(){
		   Object o = (Object) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   if(o == null ){
			   throw new MyException("error request!"); 
		   }

		   if(!(o instanceof MyUser)){
			   throw new MyException("未登录或无权访问！");
		   }
		   return (MyUser) o;
	  }
	
	 
  
	 public void createDownLoadStream2(HttpServletRequest request,HttpServletResponse response,DownLoadDTO fileInfo){
		   
		   try {
		         
	    	   File file = new File(fileInfo.getFilePath());  
	    	   
	    	   if(fileInfo.getFileName().lastIndexOf(".") == -1){//文件名没有后缀的话添加后缀
	    		   String fileType = file.getPath().substring(file.getPath().lastIndexOf(".")+1);
	    		   fileInfo.setFileName(fileInfo.getFileName() + "." +fileType);
	    	   }
	    	   
	    	   String agent = request.getHeader("User-Agent");
	    	   boolean isMSIE = (agent != null && (agent.indexOf("MSIE") != -1 || agent.indexOf("rv:11") != -1));

	    	   String fileName = fileInfo.getFileName();
	    	   if (isMSIE) {
	    	       fileName = URLEncoder.encode(fileName.replace(" ", ""), "UTF-8");
	    	   } else {
	    	       fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	    	   }
	    	   response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	    	   
	    	   //String fileName = new String(fileInfo.getFileName().getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
	    	   
	           response.setCharacterEncoding("utf-8");
	           response.setContentType("multipart/form-data");
	           response.setContentLength((int) file.length());
	           response.setHeader("Content-Disposition","attachment;fileName="+ fileName);
	                     
	           InputStream inputStream = new FileInputStream(file);
	      
	           OutputStream os = response.getOutputStream();
	           byte[] b = new byte[8192];
	           int length;
	           while ((length = inputStream.read(b)) > 0) {
	               os.write(b, 0, length);
	           }
	         
	           os.close();

	           inputStream.close();
	        
	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
		   
	   }
	   
		 

   
	 public void createDownLoadStream(HttpServletRequest request,HttpServletResponse response,DownLoadDTO fileInfo){
		   
		   try {
		         
	    	   File file = new File(fileInfo.getFilePath());  
	    	   
	    	   if(fileInfo.getFileName().lastIndexOf(".") == -1){//文件名没有后缀的话添加后缀
	    		   String fileType = file.getPath().substring(file.getPath().lastIndexOf(".")+1);
	    		   fileInfo.setFileName(fileInfo.getFileName() + "." +fileType);
	    	   }
	    	   
	    	   String agent = request.getHeader("User-Agent");
	    	   boolean isMSIE = (agent != null && (agent.indexOf("MSIE") != -1 || agent.indexOf("rv:11") != -1));

	    	   String fileName = fileInfo.getFileName();
	    	   if (isMSIE) {
	    	       fileName = URLEncoder.encode(fileName.replace(" ", ""), "UTF-8");
	    	   } else {
	    	       fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	    	   }
	    	   response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	    	   
	    	   //String fileName = new String(fileInfo.getFileName().getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
	    	   
	           response.setCharacterEncoding("utf-8");
	           response.setContentType("multipart/form-data");
	           response.setContentLength((int) file.length());
	           response.setHeader("Content-Disposition","attachment;fileName="+ fileName);
	                     
	           InputStream inputStream = new FileInputStream(file);
	      
	           OutputStream os = response.getOutputStream();
	           byte[] b = new byte[8192];
	           int length;
	           while ((length = inputStream.read(b)) > 0) {
	               os.write(b, 0, length);
	           }
	         
	           os.close();

	           inputStream.close();
	        
	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
		   
	   }
   
	 
  
	 
	 
	 /**
	  * 解析jqgrid请求
	  * @param queryDto
	  * @param filters
	  */
	  public void setSearchValues(BaseQueryDTO queryDto,String filters){
    	  
			JSONObject jsonObject = JSONObject.fromObject(filters);
			queryDto.setGroupOp(jsonObject.getString("groupOp"));
			queryDto.setRules(jsonObject.getJSONArray("rules"));
							
	   }
	 
	  
	  protected String getNowIPAddress(HttpServletRequest request) {
			String IP_ADDRESS = request.getRemoteAddr();		
			
		    if(IP_ADDRESS.equals("127.0.0.1")){
		    	try {
					IP_ADDRESS = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
					IP_ADDRESS = "0.0.0.0";
				}
		    }
			return IP_ADDRESS;
		}
	  
	  
	  
	  /**
	   * 设置未验证
	   */
	  public void setUnauthenticated(){
		  SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	  }
	  
	
	}
