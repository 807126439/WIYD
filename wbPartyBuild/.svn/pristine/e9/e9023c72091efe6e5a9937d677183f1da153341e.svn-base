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
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.dto.department.JobDepartRelationQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IBaseDictService;
import com.wb.web.system.service.IJobDepartRelationService;

@Controller
@Scope("prototype")
@RequestMapping("/jobDepartRelationController")
public class JobDepartRelationController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5153988181759753247L;
	@Resource
	private IJobDepartRelationService jobDepartRelationService;
	@Resource
	private IBaseDictService baseDictService;
		
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadJobDepartRelationByPage(HttpServletRequest request,JobDepartRelationQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<JobDepartRelationDTO> pageResult = this.jobDepartRelationService.searchJobDepartRelationByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加部门职务人员关系
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddJobDepartRelation(HttpServletRequest request){
				
		
		return "system/department/addRelation.jsp";
	}
	
	
	/**
	 * 添加部门职务人员关系
	 * @param dto
	 * @param authIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addJobDepartRelation")
	@ResponseBody
	public AjaxJson addJobDepartRelation(JobDepartRelationDTO dto){
	
		 this.jobDepartRelationService.addJobDepartRelation(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个部门职务人员关系信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params=MyRequestParam.detail)
	public String loadJobDepartRelationDetail(HttpServletRequest request,@RequestParam(value="rid",required=true)String rid){
			  
		JobDepartRelationDTO dto = this.jobDepartRelationService.getJobDepartRelationById(rid);
		request.setAttribute("relationItem", dto);
		
		
		return "system/department/editRelation.jsp";
	
	}
	
	/**
	 * 修改部门职务人员关系信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editJobDepartRelation")
	@ResponseBody
	public AjaxJson updateJobDepartRelation(JobDepartRelationDTO dto){
		
		this.jobDepartRelationService.updateJobDepartRelation(dto);
	
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
	
	/**
	 * 删除部门职务人员关系
	 * @param rids
	 * @return
	 */
	@RequestMapping("/deleteJobDepartRelation")
	@ResponseBody
	public AjaxJson deleteJobDepartRelation(String[] ids){
		
		this.jobDepartRelationService.deleteJobDepartRelation(ids);
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
		
		
	}
	

}
