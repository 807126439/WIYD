package com.wb.web.portals.controller;

import java.util.HashMap;
import java.util.List;
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
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksDTO;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksQueryDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.manuscript.ManuscriptDTO;
import com.wb.web.portals.service.IAwardWinningWorksService;
import com.wb.web.portals.service.IAwardsSettingService;
import com.wb.web.portals.service.IManuscriptService;


@Controller
@Scope("prototype")
@RequestMapping("/awardWinningWorksController")
public class AwardWinningWorksController extends BaseController{
	
	@Resource
	private IAwardWinningWorksService awardWinningWorksService;
	@Resource
	private IAwardsSettingService awardsSettingService;
	@Resource
	private IManuscriptService manuscriptService;
	

	
	private static final long serialVersionUID = 2140886114660019652L;
	
	
	
	
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewAwardWinningWorksPage(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId){		
		this.wrapMenuTitle(request);
		request.setAttribute("activityId", activityId);
		return "portals/awardWinningWorks/awardWinningWorksList.jsp";
	}


	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddAwardWinningWorks(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId){	
		//查询该活动可以颁发的所有奖项
		List<AwardsSettingDTO> awardSettingList=awardsSettingService.getAwardsSettingsByActivityId(activityId);
		//查询该活动可以获奖的所有作品
		List<ManuscriptDTO> manuscriptList=manuscriptService.getAllManuscriptByActivityId(activityId);
		
		
		request.setAttribute("activityId", activityId);
		request.setAttribute("awardSettingList", awardSettingList);
		request.setAttribute("manuscriptList", manuscriptList);
		return "portals/awardWinningWorks/addAwardWinningWorks.jsp";
	}
	
	
	@RequestMapping(value="/addAwardWinningWorks",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addAwardWinningWorks(AwardWinningWorksDTO dto){	
		this.awardWinningWorksService.addAwardWinningWorks(dto);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */

	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadAwardWinningWorksByPage(HttpServletRequest request,AwardWinningWorksQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<AwardWinningWorksDTO> pageResult = this.awardWinningWorksService.searchAwardWinningWorksByPage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	 
	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public ModelAndView loadAwardWinningWorksDetail(HttpServletRequest request,@RequestParam(value="awwId",required=true)Long id,@RequestParam(value="activityId",required=true)Long activityId){			
		//查询该活动所有奖项
		List<AwardsSettingDTO> awardSettingList=awardsSettingService.getAwardsSettingsByActivityId(activityId);
		
		AwardWinningWorksDTO result = this.awardWinningWorksService.getAwardWinningWorksById(id);
		
		request.setAttribute("awardSettingList", awardSettingList);
		request.setAttribute("result", result);
		request.setAttribute("activityId", activityId);
		return new ModelAndView("portals/awardWinningWorks/editAwardWinningWorks.jsp");
			
	}
		
		
		@RequestMapping(value="/editAwardWinningWorks",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateAwardWinningWorks(AwardWinningWorksDTO dto){
			
			this.awardWinningWorksService.updateAwardWinningWorks(dto);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
			
		
		
		@RequestMapping(value="delAwardWinningWorks",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delAwardWinningWorks(Long[] awwIds){
			
			this.awardWinningWorksService.deleteAwardWinningWorks(awwIds);
			return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
		}

		
		
		
	
	

}
