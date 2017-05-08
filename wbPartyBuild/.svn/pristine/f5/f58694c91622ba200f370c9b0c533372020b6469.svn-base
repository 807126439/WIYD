package com.wb.web.portals.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.activityRule.ActivityRuleQueryDTO;
import com.wb.web.portals.service.IActivityRuleService;


@Controller
@Scope("prototype")
@RequestMapping("/activityRuleController")
public class ActivityRuleController extends BaseController{
	
	@Resource
	private IActivityRuleService activityRuleService;

	
	private static final long serialVersionUID = 2140886114660019652L;
	
	
	
	
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewActivityRulePage(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId){		
		this.wrapMenuTitle(request);
		request.setAttribute("activityId", activityId);
		return "portals/activityRule/activityRuleList.jsp";
	}


	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddActivityRule(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId){	

		request.setAttribute("activityId", activityId);
		return "portals/activityRule/addActivityRule.jsp";
	}
	
	
	@RequestMapping(value="/addActivityRule",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addActivityRule(ActivityRuleDTO dto){	
		this.activityRuleService.addActivityRule(dto);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	

	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadActivityRuleByPage(HttpServletRequest request,ActivityRuleQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<ActivityRuleDTO> pageResult = this.activityRuleService.searchActivityRuleByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	 
	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public ModelAndView loadActivityRuleDetail(HttpServletRequest request,@RequestParam(value="id",required=true)Long id,@RequestParam(value="activityId",required=true)Long activityId){			
		ActivityRuleDTO result = this.activityRuleService.getActivityRuleById(id);
		request.setAttribute("result", result);
		request.setAttribute("activityId", activityId);
		return new ModelAndView("portals/activityRule/editActivityRule.jsp");
			
	}
		
		
		@RequestMapping(value="/editActivityRule",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateActivityRule(ActivityRuleDTO dto){
						
			this.activityRuleService.updateActivityRule(dto);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
			
		
		
		@RequestMapping(value="delActivityRule",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delActivityRule(Long[] ids){
			
			this.activityRuleService.deleteActivityRule(ids);
			return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
		}

	
	

}
