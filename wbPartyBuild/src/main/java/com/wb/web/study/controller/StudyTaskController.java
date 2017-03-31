package com.wb.web.study.controller;

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

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.study.dto.examPaper.ExamPaperDTO;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyTask.StudyTaskDTO;
import com.wb.web.study.dto.studyTask.StudyTaskQueryDTO;
import com.wb.web.study.service.IExamPaperService;
import com.wb.web.study.service.IStudyDataService;
import com.wb.web.study.service.IStudyTaskService;

@Controller
@Scope("prototype")
@RequestMapping("/studyTaskController")
public class StudyTaskController extends BaseController {

	private static final long serialVersionUID = -3641575183607658763L;
	@Resource
	private IStudyTaskService studyTaskService;
	@Resource
	private IStudyDataService studyDataService;
	@Resource
	private IExamPaperService examPaperService;

	/**
	 * 跳转查看分页查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET })
	public String viewStudyTaskPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "study/studyTask/studyTaskList.jsp";
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
	public Map<String, Object> loadStudyTaskByPage(HttpServletRequest request,
			StudyTaskQueryDTO queryDTO) {
		this.wrapTableQueryParams(request, queryDTO);
		Page<StudyTaskDTO> pageResult = this.studyTaskService
				.searchStudyTaskPage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());
		return jsonMap;
	}

	/**
	 * 单击添加跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.add, method = { RequestMethod.GET })
	public String skipAddStudyData(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		// TODO examPaperDTOs可以不用
		List<ExamPaperDTO> examPaperDTOs = examPaperService.listExamPaper();
		request.setAttribute("examPaperDTOs", examPaperDTOs);
		return "study/studyTask/addStudyTask.jsp";

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/addStudyTask", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson addStudyTask(HttpServletRequest request, StudyTaskDTO dto,
			Long[] taskStu) {
		this.studyTaskService.addStudyTask(dto, taskStu);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}

	/**
	 * 单击修改跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.edit, method = { RequestMethod.GET })
	public String detailStudyTask(HttpServletRequest request,
			@RequestParam(value = "stId", required = true) Long stId) {
		this.wrapMenuTitle(request);
		// TODO examPaperDTOs可以不用
		List<ExamPaperDTO> examPaperDTOs = examPaperService.listExamPaper();
		List<StudyDataDTO> studyDataDTO = studyDataService
				.getStudydataByIdAndName(stId);
		StudyTaskDTO studyTaskDTO = studyTaskService.getStudyTaskById(stId);
		StringBuilder stuId = new StringBuilder();
		if (studyDataDTO.size() > 0) {
			for (StudyDataDTO DTO : studyDataDTO) {
				stuId.append(DTO.getSdId() + ",");
			}
			request.setAttribute("stuId",
					stuId.toString()
							.substring(0, stuId.toString().length() - 1));
		}
		request.setAttribute("examPaperDTOs", examPaperDTOs);
		request.setAttribute("studyTask", studyTaskDTO);
		request.setAttribute("studyData", studyDataDTO);
		return "study/studyTask/editStudyTask.jsp";
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/editStudyTask", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson updateStudyTask(StudyTaskDTO dto, Long[] taskStu) {
		this.studyTaskService.updateStudyTask(dto, taskStu);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}

	/**
	 * 删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "studyTaskDel", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson studyTAskDel(Long[] stId) {
		return studyTaskService.deleteStudyTask(stId);
	}

	// app分页查询学习任务
	@RequestMapping(value = "getAppPage", method = { RequestMethod.POST })
	@ResponseBody
	public Page<StudyTaskDTO> getAppPage(HttpServletRequest request,
			StudyTaskQueryDTO queryDTO) {
		queryDTO.setUserId(this.getNowUser().getId());
		Page<StudyTaskDTO> dtoPage = this.studyTaskService.getAppPage(queryDTO);
		return dtoPage;
	}

}
