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
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.dto.themeActivity.ThemeActivityQueryDTO;
import com.wb.web.portals.service.IThemeActivityService;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IBaseDictService;


@Controller
@Scope("prototype")
@RequestMapping("/themeActivityController")
public class ThemeActivityController  extends BaseController{

		@Resource
		private IThemeActivityService themeActivityService;
		@Resource
		private IBaseDictService baseDictService;
	
		private static final long serialVersionUID = -8311309487668094326L;
	
		
		/**
		 * 跳转主题活动管理页面
		 * @param request
		 * @return
		 */
		@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
		public String viewThemeActivityPage(HttpServletRequest request){	

			//查询活动类型
			BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
		    bdqd.setDictType(BaseDict.ACTIVITY_TYPE);
		    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);		
		
		    request.setAttribute("flagDicts", flagDicts);	
		    
			this.wrapMenuTitle(request);	
			return "portals/themeActivity/themeActivityList.jsp";
			
		}
		

		/**
		 * 跳转到添加活动
		 * @param request
		 * @return
		 */
		@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
		public String skipAddThemeActivity(HttpServletRequest request){	
			
			BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
		    bdqd.setDictType(BaseDict.ACTIVITY_TYPE);
		    List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);		    
		    request.setAttribute("flagDicts", flagDicts);
		    
			return "portals/themeActivity/addThemeActivity.jsp";
		}
		
		
		
		@RequestMapping(value="/addThemeActivity",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson addThemeActivity(ThemeActivityDTO dto){		
			this.themeActivityService.addThemeActivity(dto);
			return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
		}
		
		
		/**
		 * 分页查询主题活动
		 * @param request
		 * @param queryDTO
		 * @return
		 */
		@RequestMapping("pageList")
		@ResponseBody
		public Map<String, Object> loadThemeActivityByPage(HttpServletRequest request,ThemeActivityQueryDTO queryDTO){
			
			this.wrapTableQueryParams(request, queryDTO);
			Page<ThemeActivityDTO> pageResult = this.themeActivityService.searchThemeActivityByPage(queryDTO);			 
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("aaData", pageResult.getList());
			jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
			jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
			jsonMap.put("sEcho", queryDTO.getsEcho());						
			return jsonMap;
		}
		
		 
		@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
		public ModelAndView loadThemeActivityDetail(HttpServletRequest request,@RequestParam(value="id",required=true)Long id){			
			ThemeActivityDTO result = this.themeActivityService.getThemeActivityById(id);
			request.setAttribute("theme", result);		    
			return new ModelAndView("portals/themeActivity/editThemeActivity.jsp");				
		}
			
			
			@RequestMapping(value="/editThemeActivity",method={RequestMethod.POST})
			@ResponseBody
			public AjaxJson updateThemeActivity(ThemeActivityDTO dto){
							
				this.themeActivityService.updateThemeActivity(dto);		
				return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
			}
				
			
			
			@RequestMapping(value="delThemeActivity",method={RequestMethod.POST})
			@ResponseBody
			public AjaxJson delThemeActivity(Long[] ids){
				
				this.themeActivityService.deleteThemeActivity(ids);
				return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
			}
			
			
			

			@RequestMapping(value="activateThemeActivity",method={RequestMethod.POST})
			@ResponseBody
			public AjaxJson activateThemeActivity(Long id){
				
				this.themeActivityService.activateThemeActivity(id);
				return new AjaxJson("激活成功", AjaxJson.success);
			}
		
			
			
			@RequestMapping(value="getPage",method={RequestMethod.POST})
			@ResponseBody
			public Page<ThemeActivityDTO> getPage(Integer curPage){				
				Page<ThemeActivityDTO>  themePage = this.themeActivityService.searchThemeActivityByPgae2(3,curPage,12);
				return themePage;				
			}
			
		
		
}
