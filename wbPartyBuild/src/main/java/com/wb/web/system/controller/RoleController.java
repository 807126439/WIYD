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
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.dto.role.RoleDTO;
import com.wb.web.system.dto.role.RoleQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IAuthorityService;
import com.wb.web.system.service.IBaseDictService;
import com.wb.web.system.service.IRoleService;

@Controller
@Scope("prototype")
@RequestMapping("/roleController")
public class RoleController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4744567795873479218L;
	@Resource
	private IRoleService roleService;
	@Resource
	private IAuthorityService authorityService;
    @Resource
	private IBaseDictService baseDictService;
	
	
	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewRolePage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		return "system/role/roleList.jsp";
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadRoleByPage(HttpServletRequest request,RoleQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<RoleDTO> pageResult = this.roleService.searchRoleByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加角色
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddRole(HttpServletRequest request){
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.ROLE_TYPE_DICT_TYPE);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);
	    
	    String authTrees =  this.authorityService.getWholeZtreeWithCheck(null);
	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("nodes", authTrees);
		
		return "system/role/addRole.jsp";
	}
	
	
	/**
	 * 添加角色
	 * @param dto
	 * @param authIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public AjaxJson addRole(RoleDTO dto, String[] authIds,Boolean isRefresh) throws Exception{
	
		this.roleService.addRole(dto, authIds);
		
		if(isRefresh!=null && isRefresh){
			this.roleService.reloadAuthMap();
		}
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个角色信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params=MyRequestParam.detail)
	public ModelAndView loadRoleDetail(HttpServletRequest request,@RequestParam(value="rid",required=true)String rid){
			  
		RoleDTO dto = this.roleService.getRoleById(rid);
		request.setAttribute("roleItem", dto);
		

		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.ROLE_TYPE_DICT_TYPE);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);
		
	    String authTrees =  this.authorityService.getWholeZtreeWithCheck(rid);
	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("nodes", authTrees);
	    
		
		return new ModelAndView("system/role/editRole.jsp")	;
	
	}
	
	/**
	 * 修改角色信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editRole")
	@ResponseBody
	public AjaxJson updateRole(RoleDTO dto,String[] authIds,Boolean isRefresh){
		
		this.roleService.updateRole(dto, authIds);
		
		if(isRefresh!=null && isRefresh){
			this.roleService.reloadAuthMap();
		}
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 删除角色
	 * @param rids
	 * @return
	 */
	@RequestMapping("/delRole")
	@ResponseBody
	public AjaxJson deleteRole(String[] rids){
		
		this.roleService.deleteRoles(rids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
}
