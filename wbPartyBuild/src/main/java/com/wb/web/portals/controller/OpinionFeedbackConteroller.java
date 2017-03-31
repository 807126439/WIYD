package com.wb.web.portals.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.core.utils.DateUtil;
import com.wb.core.utils.WordUtils;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.dto.opinionfeedback.OpinionCountDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackQueryDto;
import com.wb.web.portals.service.IOpinionFeedbackService;
@Controller
@Scope("prototype")
@RequestMapping("/OpinionFeedbackConteroller")
public class OpinionFeedbackConteroller extends BaseController {

	private static final long serialVersionUID = -8995394391079301384L;
	@Resource
	private IOpinionFeedbackService opinionFeedbackService;
	@Resource
	private IBaseFileService baseFileService;

	/**
	 * 跳转查看分页查询
	 * 
	 * @param request
	 * @param sf
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET})
	public String viewUserPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "portals/opinionFeedback/opinionFeedbackList.jsp";
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param queryDTO
	 * @return
	 */

	@RequestMapping(value = "pageList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> loadContentByPage(HttpServletRequest request,
			OpinionFeedbackQueryDto queryDTO) {
		this.wrapTableQueryParams(request, queryDTO);
		Page<OpinionFeedbackDto> pageResult = this.opinionFeedbackService.searchOpinionFeedbackByPage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONArray array = new JSONArray();
		for (OpinionFeedbackDto dto : pageResult.getList()) {
			JSONObject obj = new JSONObject();
			if (queryDTO.getTypeid() == 1) {
				obj.put("id", dto.getId());
				obj.put("content", dto.getContent());
				obj.put("feedbackTime",DateUtil.Date2Str(dto.getFeedbackTime()));
				obj.put("typeId", dto.getTypeId());
				obj.put("feedbackUser", dto.getFeedbackUser());
				obj.put("chineseName", dto.getChineseName());
				obj.put("department", dto.getDepartment());
				obj.put("post", dto.getPost());
				obj.put("theme", dto.getTheme());
				obj.put("forecast", dto.getForecast());
			} else {
				obj.put("id", dto.getId());
				obj.put("typeId", dto.getTypeId());
				obj.put("theme", dto.getTheme());
				obj.put("feedbackTime",DateUtil.Date2Str(dto.getFeedbackTime()));
				obj.put("content", dto.getContent());
			}

			array.add(obj);
		}
		jsonMap.put("aaData", array);
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());
		jsonMap.put("totalPage", pageResult.getTotalPage());
		return jsonMap;
	}
	
	/**
	 * 统计数据
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "opinionCount", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> loadCount(HttpServletRequest request,
			OpinionFeedbackQueryDto queryDTO) {
		this.wrapTableQueryParams(request, queryDTO);
		Page<OpinionCountDto> pageResult = this.opinionFeedbackService.opinionCount(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());
		jsonMap.put("totalPage", pageResult.getTotalPage());
		return jsonMap;
	}

	
	
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddOpinion(HttpServletRequest request){
	
	  	
	    
		request.setAttribute("chinesename", this.getNowUser().getChineseName());
		
		return "portals/opinionFeedback/addOpinionfeedback.jsp";
	}

	
	
	
	
	
	/**
	 * 删除反馈意见
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "opinionDel", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson OpinionDel(Long[] dtoId) {
		this.opinionFeedbackService.deleteOpinionFeedback(dtoId);
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	/**
	 * 添加反馈意见
	 * 
	 * @param dto
	 * @param pwd
	 * @param pwd2
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value = "/addOpinion", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson addOpinion(HttpServletRequest request,OpinionFeedbackDto dto) {
		this.opinionFeedbackService.saveOpinionFeedback(dto,getNowIPAddress(request));
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}

	
	
	@RequestMapping(params = MyRequestParam.edit, method = { RequestMethod.GET })
	public ModelAndView viewfindById(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) {
		OpinionFeedbackDto pageResult = this.opinionFeedbackService.findById(id);
		
		OpinionFeedbackDto dto = new OpinionFeedbackDto();
		if (pageResult != null) {
			if (pageResult.getTypeId() == 1) {
				dto.setId(pageResult.getId());
				dto.setContent(pageResult.getContent());
				dto.setFeedbackTime(pageResult.getFeedbackTime());
				dto.setTypeId(pageResult.getTypeId());
				dto.setFeedbackUser(pageResult.getFeedbackUser());
				dto.setChineseName(pageResult.getChineseName());
				dto.setDepartment(pageResult.getDepartment());
				dto.setPost(pageResult.getPost());
				dto.setTheme(pageResult.getTheme());
				dto.setForecast(pageResult.getForecast());
			} else {
				dto.setId(pageResult.getId());
				dto.setFeedbackTime(pageResult.getFeedbackTime());
				dto.setContent(pageResult.getContent());
				dto.setTheme(pageResult.getTheme());
				dto.setTypeId(pageResult.getTypeId());
			}
		}
		request.setAttribute("dto", dto);
		return new ModelAndView(
				"portals/opinionFeedback/detailOpinionfeedback.jsp");
	}

	/* 根据编号下载反馈信心的DOC */
	@RequestMapping(value = "downloadFeedback", method = { RequestMethod.POST })
	public void opinionFeedbackByIdDownload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) {
					
		OpinionFeedbackDto dto = this.opinionFeedbackService.findById(id);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("chineseName", dto.getChineseName());
		dataMap.put("department", dto.getDepartment());
		dataMap.put("forecast", dto.getForecast());
		dataMap.put("advise", dto.getContent());
		dataMap.put("themeTitle", dto.getTheme());
		dataMap.put("createTime", dto.getFeedbackTime());
		dataMap.put("post", dto.getPost());
		String savePath = this.baseFileService.getTempFileWholePath("doc");
		WordUtils.createWord(dataMap,savePath,"advise.ftl");
		
		this.createDownLoadStream(request,response,new DownLoadDTO("合理化建议征集-" + dto.getChineseName(), savePath));
	}

	/**
	 * 跳转统计
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOpinionCount", method = { RequestMethod.GET})
	public String viewOpinioCount(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "portals/opinionFeedback/opinionCount.jsp";
	}
	
	/**
	 * 导出
	 * @param queryDTO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportExcel")
	 public void exportExcel(OpinionFeedbackQueryDto queryDTO,HttpServletRequest request,HttpServletResponse response) {
		 
	   this.createDownLoadStream(request,response, this.opinionFeedbackService.exportExcel(queryDTO));
       
    
	 }

}
