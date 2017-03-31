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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingQueryDTO;
import com.wb.web.portals.service.IAwardsSettingService;


@Controller
@Scope("prototype")
@RequestMapping("/awardsSettingController")
public class AwardsSettingController extends BaseController{
	
	@Resource
	private IAwardsSettingService awardsSettingService;

	private static final long serialVersionUID = 2140886114660019652L;
	
	
	
	
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewAwardsSettingPage(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId){		
		this.wrapMenuTitle(request);
		request.setAttribute("activityId", activityId);
		return "portals/awardsSetting/awardsSettingList.jsp";
	}


	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddAwardsSetting(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId){	

		request.setAttribute("activityId", activityId);
		return "portals/awardsSetting/addAwardsSetting.jsp";
	}
	
	
	@RequestMapping(value="/addAwardsSetting",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addAwardsSetting(AwardsSettingDTO dto,@RequestParam(required=false)CommonsMultipartFile file){	
		this.awardsSettingService.addAwardsSetting(dto,file);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	

	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadAwardsSettingByPage(HttpServletRequest request,AwardsSettingQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<AwardsSettingDTO> pageResult = this.awardsSettingService.searchEntityByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	 
	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public ModelAndView loadAwardsSettingDetail(HttpServletRequest request,@RequestParam(value="asId",required=true)Long asId,@RequestParam(value="activityId",required=true)Long activityId){			
		AwardsSettingDTO result = this.awardsSettingService.getAwardsSettingById(asId);
		request.setAttribute("result", result);
		request.setAttribute("activityId", activityId);
		return new ModelAndView("portals/awardsSetting/editAwardsSetting.jsp");
			
	}
		
		
		@RequestMapping(value="/editAwardsSetting",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateAwardsSetting(AwardsSettingDTO dto,@RequestParam(required=false)CommonsMultipartFile file){
			this.awardsSettingService.updateAwardsSetting(dto,file);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
			
		
		
		@RequestMapping(value="delAwardsSetting",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delAwardsSetting(Long[] ids){
			
			this.awardsSettingService.deleteAwardsSetting(ids);
			return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
		}

	
	

}
