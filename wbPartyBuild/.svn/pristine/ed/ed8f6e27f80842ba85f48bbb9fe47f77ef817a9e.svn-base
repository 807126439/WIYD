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
import com.wb.web.system.dto.authority.AuthQueryDTO;
import com.wb.web.system.dto.authority.AuthTreeQueryDTO;
import com.wb.web.system.dto.authority.AuthZtreeDto;
import com.wb.web.system.dto.authority.AuthorityDTO;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IAuthorityService;
import com.wb.web.system.service.IBaseDictService;

@Controller
@Scope("prototype")
@RequestMapping("/authController")
public class AuthorityController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7179495045003000755L;
	
	@Resource
	private IAuthorityService authorityService;
	@Resource
	private IBaseDictService baseDictService;

	
	
	
	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewAuthPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.AUTH_FLAG_DICT_TYPE);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);
	    bdqd.setDictType(BaseDict.AUTH_TYPE_DICT_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);	    
	   	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);
	
		
		return "system/auth/authList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadAuthByPage(HttpServletRequest request,AuthQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<AuthorityDTO> pageResult = this.authorityService.searchAuthByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加权限
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddAuth(HttpServletRequest request){
		
	    BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.AUTH_FLAG_DICT_TYPE);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);
	    bdqd.setDictType(BaseDict.AUTH_TYPE_DICT_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);
	    
	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);

		
		return "system/auth/addAuth.jsp";
	}
	
	/**
	 * 添加权限
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addAuth")
	@ResponseBody
	public AjaxJson addAuth(AuthorityDTO dto) throws Exception{
	
		this.authorityService.addAuth(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个权限信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params=MyRequestParam.detail)
	public ModelAndView loadAuthDetail(HttpServletRequest request,@RequestParam(value="aid",required=true)String aid){
			  
		
	    AuthorityDTO result = this.authorityService.getAuthById(aid);
	    request.setAttribute("authItem", result);						
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.AUTH_FLAG_DICT_TYPE);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);
	    bdqd.setDictType(BaseDict.AUTH_TYPE_DICT_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);
	    
	 	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);
	
			
		
		return new ModelAndView("system/auth/editAuth.jsp")	;
	
	}
	
	
	/**
	 * 修改权限信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editAuth")
	@ResponseBody
	public AjaxJson updateAuth(AuthorityDTO dto){
		
		this.authorityService.updateAuth(dto);
				
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 删除权限
	 * @param rids
	 * @return
	 */
	@RequestMapping("/delAuth")
	@ResponseBody
	public AjaxJson deleteAuth(String[] aids){
		
		this.authorityService.deleteAuth(aids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping("/getZtree")
	@ResponseBody
	public List<AuthZtreeDto> loadAuthTree(AuthTreeQueryDTO queryDTO){
		
		List<AuthZtreeDto> result = this.authorityService.searchAuthZtree(queryDTO);				
		
		return result;
	}
	
	

	
	
	
}
