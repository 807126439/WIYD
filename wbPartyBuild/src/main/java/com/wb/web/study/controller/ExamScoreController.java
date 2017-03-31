package com.wb.web.study.controller;

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
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.study.dao.IExamScoreDao;
import com.wb.web.study.dto.examScore.ExamScoreDTO;
import com.wb.web.study.dto.examScore.ExamScoreQueryDTO;
import com.wb.web.study.dto.examScore.ExaminationCountDTO;
import com.wb.web.study.dto.examScore.ExaminationCountQueryDTO;
import com.wb.web.study.service.IExamScoreService;
import com.wb.web.study.service.IStudyCategoryService;

@Controller
@Scope("prototype")
@RequestMapping("/examScoreController")
public class ExamScoreController extends BaseController {

	// ===========================================================
	@Resource
	private IExamScoreDao examScoreDao;
	// ===========================END OF================================
	private static final long serialVersionUID = 8711854367024728721L;

	@Resource
	private IExamScoreService examScoreService;
	@Resource
	private IStudyCategoryService studyCategoryService;

	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET })
	public String viewExamScorePage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "study/examScore/examScoreList.jsp";
	}

	//
	// @RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	// public String skipEditExamScore(HttpServletRequest request,Long id){
	//
	// ExamScoreDTO result = this.examScoreService.getExamScoreDTOById(id);
	// request.setAttribute("result", result);
	//
	// if(result.getScoreType() ==1){
	// return "study/examScore/editExamScoreR.jsp";
	// }else{
	// List<StudyCategory> categoryList =
	// studyCategoryService.getStudyCategoriesAndIdAndName();
	// request.setAttribute("categoryList", categoryList);
	// return "study/examScore/editExamScoreC.jsp";
	// }
	//
	// }

	@RequestMapping(value = "/editExamScore", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson editExamScore(ExamScoreDTO dto) {
		this.examScoreService.editExamScore(dto);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}

	@RequestMapping(value = "/addExamScore", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson addExamScore(ExamScoreDTO dto) {
		this.examScoreService.addExamScore(dto);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}

	@RequestMapping(value = "/checkIsExist", method = { RequestMethod.POST })
	@ResponseBody
	public Boolean checkIsExist(Long taskId) {
		return this.examScoreService.checkIsExist(taskId);
	}

	/**
	 * 2017.02.13
	 * 
	 * 导出全部
	 */
	@RequestMapping(value = "/exportAllExaminationCount")
	public void exportMyNormApplies(HttpServletRequest request,
			HttpServletResponse response) {
		this.createDownLoadStream(request, response,
				this.examScoreService.exportExcel());
	}

	/**
	 * 2017.02.10
	 * 
	 * 分页统计
	 * 
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("countList")
	@ResponseBody
	public Map<String, Object> countExaminationByPage(
			HttpServletRequest request, ExaminationCountQueryDTO queryDTO) {

		this.wrapTableQueryParams(request, queryDTO);
		Page<ExaminationCountDTO> pageResult = this.examScoreService
				.countExaminationPage(queryDTO);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());

		return jsonMap;
	}

	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadExamScoreByPage(HttpServletRequest request,
			ExamScoreQueryDTO queryDTO) {

		this.wrapTableQueryParams(request, queryDTO);
		Page<ExamScoreDTO> pageResult = this.examScoreService
				.searchExamScorePage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());
		return jsonMap;
	}

	@RequestMapping(value = "deleteExamScore", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson delExamScore(Long[] ids) {
		this.examScoreService.deleteExamScore(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}

	@RequestMapping(value = "/downLoadScoreExcel", method = {RequestMethod.GET})
	public void downLoadScoreExcel(HttpServletRequest request,
			HttpServletResponse response) {
		DownLoadDTO dto = this.examScoreService.createXlsFile();
		this.createDownLoadStream2(request, response, dto);
	}

}
