package com.wb.web.system.controller;

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
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.service.IBaseDictService;

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
	
	
	
	

	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewDictPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "system/dict/dictList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadDictByPage(HttpServletRequest request,BaseDictQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<BaseDictDTO> pageResult = this.baseDictService.searchBaseDictByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加字典
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddDict(HttpServletRequest request){
		
		
		return "system/dict/addDict.jsp";
	}
	
	/**
	 * 添加字典
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addDict")
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
	@RequestMapping(params=MyRequestParam.detail)
	public ModelAndView loadDictDetail(HttpServletRequest request,@RequestParam(value="did",required=true)String did){
			  
		
	    BaseDictDTO result = this.baseDictService.getDictById(did);

	    request.setAttribute("dictItem", result);
		
		return new ModelAndView("system/dict/editDict.jsp")	;
	
	}
	
	
	/**
	 * 修改字典信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editDict")
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
	@RequestMapping("/delDict")
	@ResponseBody
	public AjaxJson deleteDict(String[] dids){
		
		this.baseDictService.deleteDicts(dids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
	
	
	
}
