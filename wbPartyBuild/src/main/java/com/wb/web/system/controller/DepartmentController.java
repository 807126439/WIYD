package com.wb.web.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.system.dto.department.DepartTreeDTO;
import com.wb.web.system.dto.department.DepartmentDTO;
import com.wb.web.system.dto.department.DepartmentQueryDTO;
import com.wb.web.system.service.IDepartmentService;


@Controller
@Scope("prototype")
@RequestMapping("/departmentController")
public class DepartmentController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5648175278430519679L;
	@Resource
	private IDepartmentService departmentService;
	
	
	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewDepartmentPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "system/department/departmentList.jsp";
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadDepartmentByPage(HttpServletRequest request,DepartmentQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<DepartmentDTO> pageResult = this.departmentService.searchDepartmentyByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加部门
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddDepartment(HttpServletRequest request){
	
		
		return "system/department/addDepartment.jsp";
	}
	
	
	/**
	 * 添加部门
	 * @param dto
	 * @param authIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addDepartment")
	@ResponseBody
	public AjaxJson addDepartment(DepartmentDTO dto){
	
		String addId = this.departmentService.addDepartment(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success,addId);
	}
	
	
	/**
	 * 查询单个部门信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params=MyRequestParam.detail)
	public String loadDepartmentDetail(HttpServletRequest request,@RequestParam(value="depId",required=true)String depId){
			  
		DepartmentDTO dto = this.departmentService.getDepartmentById(depId);
		request.setAttribute("departItem", dto);
		
	    
		
		return "system/department/editDepartment.jsp";
	
	}
	
	/**
	 * 修改部门信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editDepartment")
	@ResponseBody
	public AjaxJson updateDepartment(DepartmentDTO dto){
		
		this.departmentService.updateDepartment(dto);
	
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 移动部门
	 * @param rids
	 * @return
	 */
	@RequestMapping("/moveDepartment")
	@ResponseBody
	public AjaxJson moveDepartment(String depId,String parId){
		
		 this.departmentService.updateMoveDepartment(depId, parId);
		
		 return new AjaxJson("移动成功！", AjaxJson.success);
	}
	
	
	
	/**
	 * 删除部门
	 * @param rids
	 * @return
	 */
	@RequestMapping("/deleteDepartment")
	@ResponseBody
	public AjaxJson deleteDepartment(String[] depIds){
		
		return this.departmentService.deleteDepartment(depIds);
		
		
	}
	
	/**
	 * 查询部门树形结构数据(ajax动态查询)
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("/getDepTree")
	@ResponseBody
	public List<DepartTreeDTO> getDepartTree(DepartmentQueryDTO queryDTO){
		return this.departmentService.searchDepartmentZtree(queryDTO);	
	}
	
	/**
	 * 查询部门树形结构数据(查询所有主部门并拼成Json格式,一次返回)
	 * @param checkedDepId (可选)输入已选择部门ID,该部门变为勾选状态
	 * @return
	 */
	@RequestMapping("/getMainDepTree")
	@ResponseBody
	public AjaxJson getMainDepTree(HttpServletRequest request,@RequestParam(value="seeOrgId",required=false)String checkedDepId){
		String nodes=null;
		String[] checkedDepIds=null; 
		if(null!=checkedDepId){
			checkedDepIds=checkedDepId.split(",");
		}
		return new AjaxJson("查询成功！", AjaxJson.success,this.departmentService.getZtreeNodesByContentId(checkedDepIds));	
	}
	

}
