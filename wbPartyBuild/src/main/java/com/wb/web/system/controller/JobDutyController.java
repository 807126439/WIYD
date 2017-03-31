package com.wb.web.system.controller;

import java.util.HashMap;
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
import com.wb.web.system.dto.jobDuty.JobDutyDTO;
import com.wb.web.system.dto.jobDuty.JobDutyQueryDTO;
import com.wb.web.system.service.IJobDutyService;

@Controller
@Scope("prototype")
@RequestMapping("/jobDutyController")
public class JobDutyController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4798569243340852106L;
	@Resource
	private IJobDutyService jobDutyService;
	
	
	
	
	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewJobDutyPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "system/jobduty/jobDutyList.jsp";
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadJobDutyByPage(HttpServletRequest request,JobDutyQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<JobDutyDTO> pageResult = this.jobDutyService.searchJobDutyByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加职务
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add)
	public String skipAddJobDuty(HttpServletRequest request){
	
		
		return "system/jobduty/addJobDuty.jsp";
	}
	
	
	/**
	 * 添加职务
	 * @param dto
	 * @param authIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addJobDuty")
	@ResponseBody
	public AjaxJson addJobDuty(JobDutyDTO dto){
	
		this.jobDutyService.addJobDuty(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个职务信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params=MyRequestParam.detail)
	public String loadJobDutyDetail(HttpServletRequest request,@RequestParam(value="jid",required=true)String jid){
			  
		JobDutyDTO dto = this.jobDutyService.getJobDutyById(jid);
		request.setAttribute("jobDutyItem", dto);
		
	    
		
		return "system/jobduty/editJobDuty.jsp";
	
	}
	
	/**
	 * 修改职务信息
	 * @param dto
	 * @param authIds
	 * @return
	 */
	@RequestMapping("/editJobDuty")
	@ResponseBody
	public AjaxJson updateJobDuty(JobDutyDTO dto){
		
		this.jobDutyService.updateJobDuty(dto);
	
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 删除职务
	 * @param rids
	 * @return
	 */
	@RequestMapping("/deleteJobDuty")
	@ResponseBody
	public AjaxJson deleteJobDuty(String[] jids){
		
		return this.jobDutyService.deleteJobDuty(jids);
		
		
	}
	
	
	
	
}
