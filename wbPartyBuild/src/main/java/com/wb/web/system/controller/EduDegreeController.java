package com.wb.web.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.system.dto.EduDegree.EduDegreeDTO;
import com.wb.web.system.dto.EduDegree.EduDegreeQueryDTO;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.dto.department.DepartmentDTO;
import com.wb.web.system.dto.department.DepartmentQueryDTO;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.entity.EduDegree;
import com.wb.web.system.service.IEduDegreeService;


@Controller
@Scope("prototype")
@RequestMapping("/eduDegreeController")
public class EduDegreeController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5448683777441807815L;
	
	@Resource
	private IEduDegreeService eduDegreeService;
	
	
	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewEduDegreePage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "system/eduDegree/eduDegreeList.jsp";
	}
	

	/**
	 * 分页查询教育学历等级
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadEduDegreeByPage(HttpServletRequest request,EduDegreeQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<EduDegreeDTO> pageResult = this.eduDegreeService.searchEduDegreeByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		return jsonMap;
	}
	
	/**
	 * 跳转到教育学历等级
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddEduDegree(HttpServletRequest request){
		return "system/eduDegree/addEduDegree.jsp";
	}
	
	/**
	 * 添加教育学历等级
	 * @param dto
	 * @param authIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addEduDegree")
	@ResponseBody
	public AjaxJson addEduDegree(EduDegreeDTO dto){
	
		this.eduDegreeService.addEduDegree(dto);
		 
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	/**
	 * 查询单个教育学历等级信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params=MyRequestParam.detail)
	public String loadEduDegreeDetail(HttpServletRequest request,@RequestParam(value="id",required=true)String id){
			  
		EduDegree dto = this.eduDegreeService.getEduDegreeById(id);
		request.setAttribute("EduDegree", dto);
		return "system/eduDegree/editEduDegree.jsp";
	
	}
	
	/**
	 * 修改教育学历等级
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editEduDegree")
	@ResponseBody
	public AjaxJson updateEduDegree(EduDegreeDTO dto){
		
		this.eduDegreeService.updateEduDegree(dto);
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
	
	/**
	 * 删除教育学历等级
	 * @param rids
	 * @return
	 */
	@RequestMapping("/deleteEduDegree")
	@ResponseBody
	public AjaxJson deleteEduDegree(String[] ids){
		
		this.eduDegreeService.deleteEduDegree(ids);
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
		
		
	}
	
	

}
