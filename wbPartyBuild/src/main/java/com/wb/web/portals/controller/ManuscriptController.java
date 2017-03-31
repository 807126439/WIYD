package com.wb.web.portals.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.manuscript.ManuscriptDTO;
import com.wb.web.portals.dto.manuscript.ManuscriptQueryDTO;
import com.wb.web.portals.dto.manuscript.PhotoDTO;
import com.wb.web.portals.service.IManuscriptService;


@Controller
@Scope("prototype")
@RequestMapping("/manuscriptController")
public class ManuscriptController extends BaseController{
	
	@Resource
	private IManuscriptService manuscriptService;

	
	private static final long serialVersionUID = 2140886114660019652L;
	
	
	
	
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewManuscriptPage(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId,@RequestParam(value="banChunkId",required=false)Long banChunkId,@RequestParam(value="type",required=false)Long type,Long activityType){		
		this.wrapMenuTitle(request);
		request.setAttribute("activityId", activityId);
		if(null!=type){
			return "portals/manuscript/cityBuilderManuscriptList.jsp";
		}else if(null==banChunkId||banChunkId==0){
			//月月精彩-1
			if(activityType == 1){
				return "portals/manuscript/monthlyPhotoList.jsp";
			}else{
				return "portals/manuscript/manuscriptList.jsp";
			}			
		}else{
			request.setAttribute("banChunkId", banChunkId);
			return "portals/manuscript/cityBuilderManuscriptList.jsp";
		}
	}


	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddManuscript(HttpServletRequest request,@RequestParam(value="activityId",required=true)Long activityId,@RequestParam(value="banChunkId",required=false)Long banChunkId,@RequestParam(value="type",required=false)Integer type){	
		request.setAttribute("activityId", activityId);
		if(type == 1){			
			return "portals/manuscript/addManuscript.jsp";			
		}if(type == 2){			
			request.setAttribute("banChunkId", banChunkId);
			return "portals/manuscript/addCityBuilderManuscript.jsp";			
		}return "portals/manuscript/addThemeEassay.jsp";

	}
	
	
	@RequestMapping(value="/addManuscript",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addManuscript(ManuscriptDTO dto,@RequestParam(required=false)CommonsMultipartFile file){
				
		this.manuscriptService.addManuscript(dto,file);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	

	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadManuscriptByPage(HttpServletRequest request,ManuscriptQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);

		Page<ManuscriptDTO> pageResult = this.manuscriptService.searchManuscriptByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	 
		@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
		public ModelAndView loadManuscriptDetail(HttpServletRequest request,@RequestParam(value="id",required=true)Long id,@RequestParam(value="activityId",required=true)Long activityId){			
			ManuscriptDTO result = this.manuscriptService.getManuscriptById(id);
			request.setAttribute("result", result);
			request.setAttribute("activityId", activityId);
			return new ModelAndView("portals/manuscript/editManuscript.jsp");
				
		}
		
		
		@RequestMapping(value="/editManuscript",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateManuscript(ManuscriptDTO dto,@RequestParam(required=false)CommonsMultipartFile file){
						
			this.manuscriptService.updateManuscript(dto,file);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
			

		 
		@RequestMapping(value="delManuscript",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delManuscript(Long[] ids){
			
			this.manuscriptService.deleteManuscript(ids);
			return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
		}
		
		@RequestMapping(value="/checkManuscript",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson checkManuscript(ManuscriptDTO dto){
						
			this.manuscriptService.checkManuscript(dto);		
			return new AjaxJson("审核完成", AjaxJson.success);
		}
		/**
		 * 下载稿件
		 * @return
		 */
		@RequestMapping(value="/downLoadManuscript",method={RequestMethod.GET})
		public void downLoadManuscript(HttpServletRequest request,HttpServletResponse response,ManuscriptDTO dto){
			
			ManuscriptDTO mdto=this.manuscriptService.getManuscriptById(dto.getMsId());			
			DownLoadDTO dldto=new DownLoadDTO(mdto.getFilename(),mdto.getFilePath());			
			this.createDownLoadStream(request,response, dldto);
									
		}
		
		
		@RequestMapping(params="check",method={RequestMethod.GET})
		public String skipCheckManuscript(HttpServletRequest request,@RequestParam(value="id",required=true)Long id,@RequestParam(value="pageType",required=true)Long pageType){	

			ManuscriptDTO result = this.manuscriptService.getManuscriptById(id);
			//1--普通稿件  2 -- 月月精彩图片列表
			request.setAttribute("pageType", pageType);			
			request.setAttribute("result", result);
			
			return "portals/manuscript/checkManuscript.jsp";
		}
	
		
		
				
		
		//作品展示
		@RequestMapping(value="getAllPhoto")
		@ResponseBody
		public Page<PhotoDTO> getAllPhotoByActivityId(ManuscriptQueryDTO queryDTO){	
						
			Page<PhotoDTO> result = this.manuscriptService.queryPhotosByPage(queryDTO);	
			this.manuscriptService.setVoteMessage(result.getList());
			this.manuscriptService.setPhotoViewPath(result.getList());	
			return  result;	
		}
		
		
		
		//照片墙
		@RequestMapping(value="getPhotoWall")
		@ResponseBody
		public List<PhotoDTO> getPhotoWall(Long activityId){	
			List<PhotoDTO> result = this.manuscriptService.getCheckedPhotosByActivityId(activityId);			
			return result;	
		}
		
			
		//月月精彩后台分页
		@RequestMapping(value="getMonthlyPage")
		@ResponseBody
		public Page<PhotoDTO> getMonthlyPage(ManuscriptQueryDTO queryDTO){		
			
			Page<PhotoDTO> result = this.manuscriptService.queryPhotosByPage(queryDTO);			
			this.manuscriptService.setPhotoViewPath(result.getList());			
			return result;	
		}
		
		
		
	
	
	

}
