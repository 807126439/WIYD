package com.wb.web.base.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.utils.json.ReadJsonFile;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.dto.result.UploadResult;
import com.wb.web.base.service.IBaseFileService;


@Controller
@Scope("prototype")
@RequestMapping("/baseFileController")
public class BaseFileController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 349432056410773525L;
	@Resource
	private IBaseFileService baseFileService;
	
	/**
	 * 富文本上传文件
	 * @param upfile
	 * @return
	 */
	@RequestMapping(value="/uploadPublicFile")
	@ResponseBody
	private Object uploadPublicFile(HttpServletRequest request,String action,@RequestParam(required=false)CommonsMultipartFile upfile){
		
		if("config".equals(action)){
		 	InputStream inputStream = request.getServletContext().getResourceAsStream("plug-in/h-ui/lib/ueditor/config.json");
	        try {
				String result = ReadJsonFile.readFile(inputStream);
				JSONObject jsonConfig = JSONObject.fromObject(result);
				
				return jsonConfig;
				
	        } catch (IOException e) {
	        	
				return "error";
			}
	        
		}else if("uploadimage".equals(action) || "uploadfile".equals(action)){
			
		    SaveResult aResult = this.baseFileService.addPublicBaseFile(upfile);
			
			return new UploadResult(UploadResult.SUCCESS,aResult.getBigPattern(),upfile.getOriginalFilename(), upfile.getOriginalFilename());
		
		}else{
			
			return "Invalid request!";
		}
		
	 
	}
	
	
	
	/**
	 * 文件上传接口
	 * @param file	
	 * @param ucode 唯一标识
	 * （多个文件用ucode标识，等另外操作完成后通过ucode获取上传文件id（baseFileService.getTempCacheVal()））
	 * @return
	 */
	@RequestMapping("/file/upload")
	@ResponseBody
	public AjaxJson uploadFile(@RequestParam(value="file")CommonsMultipartFile file,String ucode){
		
		this.baseFileService.saveByCache(ucode,file);
		
		
		return new AjaxJson("success", AjaxJson.success);
	}

}
