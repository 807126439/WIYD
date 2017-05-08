package com.spr.web.system.controller;

import java.util.HashMap;
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
import com.spr.web.system.dto.authority.AuthZtreeDto;
import com.spr.web.system.dto.authority.AuthorityDTO;
import com.spr.web.system.dto.baseDict.BaseDictDTO;
import com.spr.web.system.entity.BaseDict;
import com.spr.web.system.service.IAuthorityService;
import com.spr.web.system.service.IBaseDictService;

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

	
	
	
	@RequestMapping(value="viewPage",method={RequestMethod.GET})
	public String viewAuthPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		DataQuery dq = new DataQuery(new HashMap<String, Object>());
		dq.getQueryMap().put("dictType", BaseDict.AUTH_FLAG_DICT_TYPE);
		dq.getQueryMap().put("dictFlag", true);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(dq);	    
	
	    dq.getQueryMap().put("dictType", BaseDict.AUTH_TYPE_DICT_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	    
	   	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);
	
		return "system/auth/authList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param dq
	 * @param authName
	 * @param authCode
	 * @param parAuthName
	 * @param authType
	 * @param flag
	 * @return
	 */
	@RequestMapping("getPageData")
	@ResponseBody
	public Map<String, Object> loadAuthByPage(HttpServletRequest request,DataQuery dq,String authName,
		String authCode,String parAuthName,Short authType,Short flag){

		this.wrapTableQueryParams(request, dq);
		dq.getQueryMap().put("authName", StringUtils.isBlank(authName)?null:authName);
		dq.getQueryMap().put("authCode", StringUtils.isBlank(authCode)?null:authCode);
		dq.getQueryMap().put("parAuthName", StringUtils.isBlank(parAuthName)?null:parAuthName);
		dq.getQueryMap().put("authType", authType);
		dq.getQueryMap().put("flag",flag);
		
		Page<AuthorityDTO> pageResult = this.authorityService.searchAuthByPage(dq);
	
		return this.handlePageReult(pageResult);
	}
	
	
	/**
	 * 跳转到添加权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skipAddAuth")
	public String skipAddAuth(HttpServletRequest request){
		
		DataQuery dq = new DataQuery(new HashMap<String, Object>());
		dq.getQueryMap().put("dictType", BaseDict.AUTH_FLAG_DICT_TYPE);
		dq.getQueryMap().put("dictFlag", true);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(dq);	    
	
	    dq.getQueryMap().put("dictType", BaseDict.AUTH_TYPE_DICT_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	   
	    
	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);

		String authStructure = this.authorityService.getWholeZtreeStructure(null);
		request.setAttribute("authStructure", authStructure);
	    
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
	
		String id = this.authorityService.addAuth(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success,id);
	}
	
	
	/**
	 * 查询单个权限信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDetail")
	public ModelAndView loadAuthDetail(HttpServletRequest request,@RequestParam(value="aid",required=true)String aid){
			  
		
	    AuthorityDTO result = this.authorityService.getAuthById(aid);
	    request.setAttribute("authItem", result);						
		
		DataQuery dq = new DataQuery(new HashMap<String, Object>());
		dq.getQueryMap().put("dictType", BaseDict.AUTH_FLAG_DICT_TYPE);
		dq.getQueryMap().put("dictFlag", true);
	    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(dq);	    
	
	    dq.getQueryMap().put("dictType", BaseDict.AUTH_TYPE_DICT_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	 
	    
	 	    
	    request.setAttribute("flagDict", flagDicts);
	    request.setAttribute("typeDict", typeDicts);
	
	    String authStructure = this.authorityService.getWholeZtreeStructure(result.getId());
		request.setAttribute("authStructure", authStructure);
		request.setAttribute("parAuthId", result.getParentId());
		
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
	public List<AuthZtreeDto> loadAuthTree(String pid,Integer level){
		
		List<AuthZtreeDto> result = this.authorityService.searchAuthZtree(pid,level);				
		
		return result;
	}
	
	

	
	
	
}
