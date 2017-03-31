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
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.banChunk.BanChunkQueryDTO;
import com.wb.web.portals.dto.banChunk.BanChunkTreeDTO;
import com.wb.web.portals.service.IBanChunkService;

@Controller
@Scope("prototype")
@RequestMapping("/banChunkController")
public class BanChunkController extends BaseController{
	
	
	@Resource
	private IBanChunkService banChunkService;
	
	
	private static final long serialVersionUID = -8311309487668094326L;
	
//	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
//	public String viewBanChunkPage(HttpServletRequest request){		
//		this.wrapMenuTitle(request);			
//		return "portals/banChunk/banChunkList.jsp";	
//	}
	
	@RequestMapping(params=MyRequestParam.viewPage)
	public String viewBanChunkPage(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "portals/banChunk/banChunkList.jsp";
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadBanChunkByPage(HttpServletRequest request,BanChunkQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		
		
		Page<BanChunkDTO> pageResult = this.banChunkService.searchBanChunkByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		return jsonMap;
	}
	/**
	 * 查询部门树形结构数据
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("/getBanChunkTree")
	@ResponseBody
	public List<BanChunkTreeDTO> getBanChunkTree(HttpServletRequest request,BanChunkQueryDTO queryDTO){
		
		return this.banChunkService.searchBanChunkZtree(queryDTO);	
	}
	
	/**
	 * 转到投稿页面新增板块页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddBanChunk(HttpServletRequest request){
		return "portals/banChunk/addBanChunk.jsp";
	}
	/**
	 * 转到内容编辑页面，新增叶子板块页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="addLeaf",method={RequestMethod.GET})
	public String skipAddLeafBanChunk(HttpServletRequest request){
		return "portals/content/addLeafBanChunk.jsp";
	}
	
	//添加板块
	@RequestMapping(value="/addBanChunk",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addBanChunk(BanChunkDTO dto){
		
		long addId=this.banChunkService.addBanChunk(dto);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success,addId);
	}
	
	
	
	
	 
	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public ModelAndView loadBanChunkDetail(HttpServletRequest request,@RequestParam(value="banChunkId",required=true)Long Id){	
		BanChunkDTO result = this.banChunkService.getBanChunkById(Id.toString());
		request.setAttribute("BanChunkItem", result);
			
		return new ModelAndView("portals/banChunk/editBanChunk.jsp");
			
	}
		
		//修改板块
		@RequestMapping(value="/editBanChunk",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateBanChunk(BanChunkDTO dto){
			this.banChunkService.updateBanChunk(dto);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
			
		
		//删除板块
		@RequestMapping(value="delBanChunk",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delBanChunk(String[] Ids,long activityId){
			return this.banChunkService.deleteBanChunk(Ids);
			
			 
		}
	

}
