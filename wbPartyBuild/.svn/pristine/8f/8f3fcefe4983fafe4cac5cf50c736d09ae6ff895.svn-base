package com.wb.web.portals.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.contentStat.ContentStatQueryDTO;
import com.wb.web.portals.service.IContentStatService;

@Controller
@Scope("prototype")
@RequestMapping("/contentStatController")
public class ContentStatController extends BaseController{
	
	@Resource
	private IContentStatService contentStatService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2016453250734258358L;
	
	/**
	 * 跳转查看分页查询
	 * @param request
	 * @param sf 
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewContentStatPage(HttpServletRequest request){
		
		this.wrapMenuTitle(request);
				
		return "portals/contentStat/contentStatList.jsp";
		
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadContentStatByPage(HttpServletRequest request,ContentStatQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<ContentStatDTO> pageResult = this.contentStatService.searchContentStatByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	/**
	 * 导出excel
	 * @param dataType
	 * @param ids
	 * @param request
	 * @param response
	 */
	 @RequestMapping(value="/exportExcel")
	 public void exportExcel(ContentStatQueryDTO queryDTO,HttpServletRequest request,HttpServletResponse response) {
		 
	   this.createDownLoadStream(request,response, this.contentStatService.exportExcel(queryDTO));
         
      
	 }

}
