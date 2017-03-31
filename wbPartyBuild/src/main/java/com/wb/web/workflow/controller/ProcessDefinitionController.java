package com.wb.web.workflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.workflow.dto.definition.ProcessDefinitionDTO;
import com.wb.web.workflow.dto.definition.ProcessDefinitionQueryDTO;
import com.wb.web.workflow.service.IProcessDefinitionService;


@Controller
@Scope("prototype")
@RequestMapping("/procDefinition")
public class ProcessDefinitionController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126609299763496174L;

	@Resource
	private IProcessDefinitionService processDefinitionService;
	
	
	
	
	
	@RequestMapping(value="/viewPage")
	public String viewProcessDefinitionPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "work-flow/procDef/procDefList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("/getPageData")
	@ResponseBody
	public Map<String, Object> loadProcessDefinitionByPage(HttpServletRequest request,ProcessDefinitionQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<ProcessDefinitionDTO> pageResult = this.processDefinitionService.searchByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加流程定义
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skipAddProcessDefinition")
	public String skipAddProcessDefinition(HttpServletRequest request){
		
		
		return "work-flow/procDef/addProcDef.jsp";
	}
	
	
	
	/**
	 * 添加流程定义
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addProcessDefinition")
	@ResponseBody
	public AjaxJson addProcessDefinition(ProcessDefinitionDTO dto) throws Exception{
	
		this.processDefinitionService.addProcessDefinition(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个流程定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getDetail")
	public ModelAndView loadProcessDefinitionDetail(HttpServletRequest request,@RequestParam(value="id",required=true)Long id){
			  
		
		ProcessDefinitionDTO result = this.processDefinitionService.getById(id);

	    request.setAttribute("defItem", result);
		
		return new ModelAndView("work-flow/procDef/editProcDef.jsp");
	
	}
	
	
	/**
	 * 修改流程定义信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editProcessDefinition")
	@ResponseBody
	public AjaxJson updateProcessDefinition(ProcessDefinitionDTO dto){
		
		this.processDefinitionService.updateProcessDefinition(dto);
				
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 删除流程定义
	 * @param rids
	 * @return
	 */
	@RequestMapping("/delProcessDefinition")
	@ResponseBody
	public AjaxJson deleteProcessDefinition(Long id){
		
		this.processDefinitionService.deleteProcessDefinition(id);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
}
