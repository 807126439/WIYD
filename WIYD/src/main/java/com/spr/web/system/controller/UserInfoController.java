package com.spr.web.system.controller;

import com.spr.web.system.service.IUserInfoService;
import com.spr.web.system.dto.user.UserInfoDTO;
import com.spr.core.common.controller.BaseController;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;

@Controller
@Scope("prototype")
@RequestMapping("/userInfoController")
public class UserInfoController extends BaseController{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private IUserInfoService userInfoService;
	
	
	
	@RequestMapping(value="/viewPage",method={RequestMethod.GET})
	public String viewPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "system/userInfo/userInfoList.jsp";
	}
	
	
	@RequestMapping(value="/getPageData",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> loadPageData(HttpServletRequest request,DataQuery dq){

		this.wrapTableQueryParams(request, dq);
				
		Page<UserInfoDTO> pageResult = this.userInfoService.searchByPage(dq);
		 
		return this.handlePageReult(pageResult);
	}
	
	
	/*@RequestMapping(value="/skipAddUserInfo")
	public String skipAddUserInfo(HttpServletRequest request){
		
		
		return "system/userInfo/addUserInfo.jsp";
	}
	
	
	@RequestMapping(value="/addUserInfo",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addUserInfo(UserInfoDTO dto) throws Exception{
	
		this.userInfoService.addUserInfo(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping("/getDetail")
	public String loadDetail(HttpServletRequest request,@RequestParam(value="id",required=true)String id){
			  		
	    UserInfoDTO result = this.userInfoService.getDetailById(id);
		request.setAttribute("model", result);
		
	
		return "system/userInfo/editUserInfo.jsp";
	}
	
	
	@RequestMapping(value="/editUserInfo",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateUserInfo(UserInfoDTO dto){
		
		this.userInfoService.updateUserInfo(dto);
				
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="/deleteUserInfo",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson deleteUserInfo(String[] ids){
		
		this.userInfoService.deleteUserInfos(ids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}*/
	
}
