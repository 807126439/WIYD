package com.spr.web.system.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.controller.BaseController;
import com.spr.web.system.dto.baseDict.BaseDictDTO;
import com.spr.web.system.service.IBaseDictService;

@Controller
@Scope("prototype")
@RequestMapping("/dictController")
public class BaseDictController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4496906479824788301L;
	@Resource
	private IBaseDictService baseDictService;
	
	
	
	

	@RequestMapping(value="viewPage",method={RequestMethod.GET})
	public String viewDictPage(HttpServletRequest request,String dictType){
		this.wrapMenuTitle(request);
		
		if(!StringUtils.isBlank(dictType)){
			request.setAttribute("dictType", dictType);
			request.setAttribute("model", "profile");
		}
		
		return "system/dict/dictList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value="getPageData",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> loadDictByPage(HttpServletRequest request,DataQuery dq,
						String dictType,String dictItem){

		this.wrapTableQueryParams(request, dq);
		dq.getQueryMap().put("dictType", StringUtils.isBlank(dictType)? null:dictType);
		dq.getQueryMap().put("dictItem", StringUtils.isBlank(dictItem)? null:dictItem);
		
		Page<BaseDictDTO> pageResult = this.baseDictService.searchBaseDictByPage(dq);
		 
		return this.handlePageReult(pageResult);
	}
	
	
	/**
	 * 跳转到添加字典
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skipAddDict")
	public String skipAddDict(HttpServletRequest request,String dictType,String model){
		
		if(!StringUtils.isBlank(dictType) && !StringUtils.isBlank(model)){
			request.setAttribute("dictType", dictType);
			request.setAttribute("model",model);
		}
		
		return "system/dict/addDict.jsp";
	}
	
	
	
	/**
	 * 添加字典
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addDict",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addDict(BaseDictDTO dto) throws Exception{
	
		this.baseDictService.addDict(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个字典
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDetail")
	public String loadDictDetail(HttpServletRequest request,@RequestParam(value="did",required=true)String did,String model){
			  
		
	    BaseDictDTO result = this.baseDictService.getDictById(did);
	    request.setAttribute("dictItem", result);
		if(!StringUtils.isBlank(model)){
			request.setAttribute("model",model);
			request.setAttribute("dictType",result.getDictType());
		}
		
		return "system/dict/editDict.jsp";
	
	}
	
	
	/**
	 * 修改字典信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping(value="/editDict",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateDict(BaseDictDTO dto){
		
		this.baseDictService.updateDict(dto);
				
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 删除字典
	 * @param rids
	 * @return
	 */
	@RequestMapping(value="/delDict",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson deleteDict(String[] dids){
		
		this.baseDictService.deleteDicts(dids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
	
	
	
}
