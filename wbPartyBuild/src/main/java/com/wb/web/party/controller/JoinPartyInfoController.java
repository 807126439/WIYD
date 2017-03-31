package com.wb.web.party.controller;

import java.util.HashMap;
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
import com.wb.web.party.dto.JoinPartyInfoDTO;
import com.wb.web.party.dto.JoinPartyQueryDTO;
import com.wb.web.party.dto.JoinPartyStatsDTO;
import com.wb.web.party.service.IJoinPartyInfoService;

@Controller
@Scope("prototype")
@RequestMapping("/joinPartyInfoController")
public class JoinPartyInfoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841051147774651873L;
	@Resource
	private IJoinPartyInfoService joinPartyInfoService;
	
	
	

	@RequestMapping(value="/viewPage")
	public String viewPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "party/join/joinPartyInfoList.jsp";
	}
	
	
	  
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("/getPageData")
	@ResponseBody
	public Map<String, Object> loadJoinPartyInfoByPage(HttpServletRequest request,JoinPartyQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<JoinPartyInfoDTO> pageResult = this.joinPartyInfoService.searchByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	
	
	@RequestMapping("/getStatsData")
	@ResponseBody
	public Map<String, Object> searchStatsByPage(HttpServletRequest request,JoinPartyQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		Page<JoinPartyStatsDTO> pageResult = this.joinPartyInfoService.searchStatsByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 跳转到添加入党申请信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/skipAddJoinPartyInfo")
	public String skipAddJoinPartyInfo(HttpServletRequest request){
		
		
		return "party/join/addJoinPartyInfo.jsp";
	}
	
	/**
	 * 添加入党申请信息
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addJoinPartyInfo")
	@ResponseBody
	public AjaxJson addJoinPartyInfo(JoinPartyInfoDTO dto) throws Exception{
	
		this.joinPartyInfoService.addJoinPartyInfo(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 查询单个入党申请信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDetail")
	public ModelAndView loadJoinPartyInfoDetail(HttpServletRequest request,
			@RequestParam(value="jid",required=true)Long jid){
			  
		
		JoinPartyInfoDTO result = this.joinPartyInfoService.getDetailById(jid);

	    request.setAttribute("joinItem", result);
		
		return new ModelAndView("party/join/editJoinPartyInfo.jsp")	;
	
	}
	
	
	/**
	 * 开始入党申请
	 * @param jid
	 * @return
	 */
	@RequestMapping("/startJoinPartyApply")
	@ResponseBody
	public AjaxJson startJoinPartyApply(Long jid){
		
		this.joinPartyInfoService.startJoinPartyApply(jid);
				
		return new AjaxJson("执行成功！", AjaxJson.success);
	}
	
	
	/**
	 * 修改入党申请信息信息
	 * @param dto
	 * @return
	 */
	@RequestMapping("/editJoinPartyInfo")
	@ResponseBody
	public AjaxJson updateJoinPartyInfo(JoinPartyInfoDTO dto){
		
		this.joinPartyInfoService.updateJoinPartyInfo(dto);
				
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	/**
	 * 删除入党申请信息
	 * @param jids
	 * @return
	 */
	@RequestMapping("/delJoinPartyInfo")
	@ResponseBody
	public AjaxJson deleteJoinPartyInfo(Long[] jids){
		
		this.joinPartyInfoService.deleteJoinPartyInfo(jids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
}
