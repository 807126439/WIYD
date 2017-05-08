package com.spr.web.system.controller;

import java.util.List;
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
import com.spr.web.system.dto.role.RoleDTO;
import com.spr.web.system.entity.BaseDict;
import com.spr.web.system.service.IAuthorityService;
import com.spr.web.system.service.IBaseDictService;
import com.spr.web.system.service.IRoleService;

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
	
	
	@RequestMapping(value="viewPage",method={RequestMethod.GET})
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
	@RequestMapping(value="getPageData",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> loadRoleByPage(HttpServletRequest request,DataQuery dq,
					String roleName,String roleText){

		this.wrapTableQueryParams(request, dq);
		dq.getQueryMap().put("roleName", StringUtils.isBlank(roleName)?null:roleName);
		dq.getQueryMap().put("roleText", StringUtils.isBlank(roleText)?null:roleText);
		Page<RoleDTO> pageResult = this.roleService.searchRoleByPage(dq);

		
		return this.handlePageReult(pageResult);
	}
	
	
	
	
	/**
	 * 跳转到添加角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skipAddRole")
	public String skipAddRole(HttpServletRequest request){
		DataQuery dq = new DataQuery();
		dq.putToMap("dictType", BaseDict.ROLE_TYPE_DICT_TYPE);
		dq.putToMap("dictFlag", true);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(dq);
	        
	    dq.putToMap("dictType", BaseDict.ROLE_TYPE);
		List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	  
	    
	    String authTrees =  this.authorityService.getWholeZtreeWithCheck(null);
	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);
	    
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
	@RequestMapping("/getDetail")
	public ModelAndView loadRoleDetail(HttpServletRequest request,@RequestParam(value="rid",required=true)String rid){
			  
		RoleDTO dto = this.roleService.getRoleById(rid);
		request.setAttribute("roleItem", dto);
		

		DataQuery dq = new DataQuery();
		dq.putToMap("dictType", BaseDict.ROLE_TYPE_DICT_TYPE);
		dq.putToMap("dictFlag", true);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(dq);
	        
	    dq.putToMap("dictType", BaseDict.ROLE_TYPE);
		List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	  
		
	    String authTrees =  this.authorityService.getWholeZtreeWithCheck(rid);
	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);
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
