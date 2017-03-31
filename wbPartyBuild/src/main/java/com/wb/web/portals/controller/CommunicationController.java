package com.wb.web.portals.controller;

import java.text.ParseException;
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
import com.wb.web.portals.dto.communication.CommunicationDTO;
import com.wb.web.portals.dto.communication.CommunicationQueryDTO;
import com.wb.web.portals.service.ICommunicationService;

@Controller
@Scope("prototype")
@RequestMapping("/communController")
public class CommunicationController extends BaseController{

		@Resource
		private ICommunicationService communicationService;
		
		
		private static final long serialVersionUID = -8311309487668094326L;
		
		
		/**
		 * 跳转互动议题管理
		 * @param request
		 * @return
		 */
		@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
		public String viewCommunPage(HttpServletRequest request){		
			this.wrapMenuTitle(request);				
			return "portals/communication/communList.jsp";		
		}
	
		/**
		 * 跳转到添加议题
		 * @param request
		 * @return
		 */
		@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
		public String skipAddCommun(HttpServletRequest request){				
			return "portals/communication/addCommun.jsp";
		}
		
		
		
		/**
		 * 添加议题
		 */
		@RequestMapping(value="/addCommun",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson addCommun(CommunicationDTO dto){	
			this.communicationService.addCommunication(dto);
			return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
		}
		
		
		/**
		 * 互动议题后台分页
		 * @param request
		 * @param queryDTO
		 * @return
		 */
		@RequestMapping("pageList")
		@ResponseBody
		public Map<String, Object> loadCommunByPage(HttpServletRequest request,CommunicationQueryDTO queryDTO){
			
			this.wrapTableQueryParams(request, queryDTO);
			Page<CommunicationDTO> pageResult = this.communicationService.searchCommunicationByPage(queryDTO);
			 
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("aaData", pageResult.getList());
			jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
			jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
			jsonMap.put("sEcho", queryDTO.getsEcho());
			
			
			return jsonMap;
		}
	
	 
		/**
		 * 跳转编辑议题
		 * @param request
		 * @param comId
		 * @return
		 */
		@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
		public ModelAndView loadCommunDetail(HttpServletRequest request,@RequestParam(value="comId",required=true)Long comId){			
			CommunicationDTO result = this.communicationService.getCommunicationById(comId);
			request.setAttribute("comItem", result);				
			return new ModelAndView("portals/communication/editCommun.jsp");
				
		}
		
		/**
		 * 更新议题
		 * @param dto
		 * @return
		 */
		@RequestMapping(value="/editCommun",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateCommun(CommunicationDTO dto){
			this.communicationService.updateCommunication(dto);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
			

		/**
		 * 删除议题
		 * @param ids
		 * @return
		 */
		@RequestMapping(value="delCommun",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delCommun(Long[] ids){			
			this.communicationService.deleteCommunication(ids);
			return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
		}
	
		
		
		/**
		 * 手机端分页
		 * @param queryDTO
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="getAppPage",method={RequestMethod.POST})
		@ResponseBody
		public Page<CommunicationDTO> getAppPage(CommunicationQueryDTO queryDTO) throws ParseException{		
			queryDTO.setIsActive(true);
			Page<CommunicationDTO> dtoPage=this.communicationService.getAppPage(queryDTO);
			return dtoPage;				
		}
			
}
