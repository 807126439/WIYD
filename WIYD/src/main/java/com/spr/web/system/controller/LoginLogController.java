package com.spr.web.system.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.controller.BaseController;
import com.spr.web.system.dto.log.LoginLogDTO;
import com.spr.web.system.service.ILoginLogService;

@Controller
@Scope("prototype")
@RequestMapping("/loginLogController")
public class LoginLogController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2486002775559927563L;
	@Resource
	private ILoginLogService loginLogService;
	
	
	
	@RequestMapping(value="viewPage")
	public String viewLogPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		return "system/log/logList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("getPageData")
	@ResponseBody
	public Map<String, Object> loadLogByPage(HttpServletRequest request,DataQuery dq,
							String userName,String startTime,String endTime){

		this.wrapTableQueryParams(request, dq);
		dq.getQueryMap().put("userName", StringUtils.isBlank(userName)?null:userName);
		dq.getQueryMap().put("startTime", StringUtils.isBlank(startTime)?null:startTime);
		dq.getQueryMap().put("endTime", StringUtils.isBlank(endTime)?null:endTime);
		
		Page<LoginLogDTO> pageResult = this.loginLogService.searchLoginLogByPage(dq);
		 
	
		return this.handlePageReult(pageResult);
	}
	
	
	
	
	/**
	 * 删除日志
	 * @param rids
	 * @return
	 */
	@RequestMapping(value="/delLoginLog",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson deleteLog(Long[] ids){
		
		this.loginLogService.deleteLogs(ids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
}
