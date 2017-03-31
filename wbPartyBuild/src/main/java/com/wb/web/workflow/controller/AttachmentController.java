package com.wb.web.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dto.attachment.AttachmentDTO;
import com.wb.web.workflow.dto.attachment.AttachmentQueryDTO;
import com.wb.web.workflow.entity.Attachment;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IAttachmentService;

@Controller
@Scope("prototype")
@RequestMapping("/attachment")
public class AttachmentController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5328734838408922647L;
	
	@Resource
	private IAttachmentService attachmentService;
	
	
	@RequestMapping(value="/getAttachment")
	@ResponseBody
	public AjaxJson getAttachment(@RequestParam(required=true)Long taskId){
		
		List<AttachmentDTO> result = this.attachmentService.getAttachmentByTaskId(taskId);
		
		return new AjaxJson("success", AjaxJson.success, result);
		
	}
	
	
	
	@RequestMapping("viewAttachment")
	public String viewAttachment(HttpServletRequest request,@RequestParam(required=true)Long procInstId){
		
		request.setAttribute("procInstId", procInstId);
	
		return "work-flow/attachment/attachmentList.jsp";
	}
	
	
	
	@RequestMapping("getPageData")
	@ResponseBody
	public Map<String, Object> loadByPage(HttpServletRequest request,AttachmentQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		if(!StringUtils.isBlank(queryDTO.getKind())){
			if(queryDTO.getKind().equals("apply")){
				queryDTO.setType(Task.SQZ_TYPE);
			}
		}
			
		Page<AttachmentDTO> pageResult = this.attachmentService.searchByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}

	@RequestMapping("goUploadAttachment")
	public String goUploadAttachment(HttpServletRequest request,@RequestParam(required=true)Long taskId){
		
		request.setAttribute("taskId", taskId);
	
		return "work-flow/attachment/uploadFile.jsp";
	}
	
	
	
	
	/**
	 * 上传附件
	 * @param taskId
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadAttachment")
	@ResponseBody
	public AjaxJson uploadAttachment(Long taskId,@RequestParam(required=true)CommonsMultipartFile file){
		
		try {
			
			this.attachmentService.addAttachment(taskId, file);
			
			return new AjaxJson(this.UPLOAD_SUCCESS_MESSAGE, AjaxJson.success);
		} catch (Exception e) {
			
			return new AjaxJson(e.getMessage(), AjaxJson.error);
		}
	
	}
	
	
	@RequestMapping("/downAttachment")
	public void downLoadFile(HttpServletRequest request,HttpServletResponse response,Long aid){
		
		DownLoadDTO result = this.attachmentService.getDownAttachmentInfo(aid);
		
		this.createDownLoadStream(request,response, result);
	}
	
	
	/**
	 * 设置附件是否公开
	 * @param aids
	 * @param dataType
	 * @return
	 */
	@RequestMapping("setAttaStatus")
	@ResponseBody
	public AjaxJson setStatus(Long[] aids,String dataType){
		Assert.hasText(dataType, "param[dataType] could not be null");
		Short status = null;
		if("open".equals(dataType)){
			status = Attachment.status_open;
		}else if("hide".equals(dataType)){
			status = Attachment.status_close;
		}else{
			throw new MyException("param[dataType] value error");
		}
		
		this.attachmentService.updateStats(aids, status);
		
		return new AjaxJson("设置成功！", AjaxJson.success);

	
	}
	
	
}
