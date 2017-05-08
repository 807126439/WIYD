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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.contentStat.ContentStatQueryDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingCountDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.entity.FeelingRecord;
import com.wb.web.portals.service.IFeelingRecordService;

@Controller
@Scope("prototype")
@RequestMapping("/feelingRecordController")
public class FeelingRecordController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833828692067116191L;
	
	@Resource
	private IFeelingRecordService feelingRecordService;
	/**
	 * 跳转到心得体会列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewFeelingRecordPage(HttpServletRequest request){		
		this.wrapMenuTitle(request);
		return "portals/FeelingRecord/feelingRecordList.jsp";
	}
	/**
	 * 分页查询心得体会
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadFeelingRecordByPage(HttpServletRequest request,FeelingRecordQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		/*queryDTO.setOwnerId(this.getNowUser().getId());*/
		Page<FeelingRecordDTO> pageResult = this.feelingRecordService.searchFeelingRecordByPage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		return jsonMap;
	}
	
	/**
	 * 统计数据
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("feelingRecordCount")
	@ResponseBody
	public Map<String, Object> getFeelingRecordCount(HttpServletRequest request,FeelingRecordQueryDTO queryDTO){
		this.wrapTableQueryParams(request, queryDTO);
		
		Page<FeelingRecordDTO> pageResult = this.feelingRecordService.getByCount(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
		
	}
	
	
	
	
	
	
	/**
	 * 跳转到心得体会的添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddFeelingRecord(HttpServletRequest request){	
		return "portals/FeelingRecord/addFeelingRecord.jsp";
	}
	/**
	 * 添加心得体会
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/addFeelingRecord",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addFeelingRecord(FeelingRecordDTO dto){	
		return this.feelingRecordService.addFeelingRecord(dto);
	}
	/**
	 * 跳转到心得体会的编辑页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public ModelAndView loadFeelingRecordDetail(HttpServletRequest request,@RequestParam(value="id",required=true)String id){			
		FeelingRecord result = this.feelingRecordService.getFeelingRecordById(id);
		request.setAttribute("result", result);
		return new ModelAndView("portals/FeelingRecord/editFeelingRecord.jsp");
			
	}
	/**
	 * 编辑心得体会
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/editFeelingRecord",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateFeelingRecord(FeelingRecordDTO dto){
		return this.feelingRecordService.editFeelingRecord(dto);		
	}
	/**
	 * 删除心得体会
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="delFeelingRecord",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson delFeelingRecord(String[] ids){
		this.feelingRecordService.deleteFeelingRecord(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	/**
	 * 跳转到查看心得体会的详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="detailFeelingRecord",method={RequestMethod.POST})
	@ResponseBody
	public FeelingRecord loadDetailFeelingRecord(String id){		

		FeelingRecord result = this.feelingRecordService.getFeelingRecordById(id);
		return result;
		
			
	}

	
	/**
	 * 统计
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFeelingCount",method={RequestMethod.GET})
	public String viewFeelingRecordCount(HttpServletRequest request){		
		this.wrapMenuTitle(request);
		System.out.println("统计");
		return "portals/FeelingRecord/feelingCountList.jsp";
	}
	
	/**
	 * 导出
	 * @param queryDTO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportExcel")
	 public void exportExcel(FeelingRecordQueryDTO queryDTO,HttpServletRequest request,HttpServletResponse response) {
		 
	   this.createDownLoadStream(request,response, this.feelingRecordService.exportExcel(queryDTO));
        
     
	 }


}
