package com.wb.web.study.controller;

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

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyData.StudyDataQueryDTO;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.service.IStudyCategoryService;
import com.wb.web.study.service.IStudyDataService;
import com.wb.web.system.service.IZonePathService;

@Controller
@Scope("prototype")
@RequestMapping("/studyDataController")
public class StudyDataController extends BaseController {

	private static final long serialVersionUID = -3075157458794978500L;

	@Resource
	private IStudyDataService studyDataService;
	@Resource
	private IStudyCategoryService studyCategoryService;
	@Resource
	private IZonePathService zonePathService;

	/**
	 * 跳转查看分页查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET })
	public String viewStudydataPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "study/studydata/studyDataList.jsp";

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
	public Map<String, Object> loadStudyDataByPage(HttpServletRequest request,
			StudyDataQueryDTO queryDTO) {
		this.wrapTableQueryParams(request, queryDTO);
		Page<StudyDataDTO> pageResult = this.studyDataService
				.searchStudyDataPage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());
		return jsonMap;
	}

	/**
	 * 删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "studyDataDel", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson studyDataDel(Long[] sdId) {
		return studyDataService.deleteStuData(sdId);
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
		List<StudyCategory> scsList = studyCategoryService
				.getStudyCategoriesAndIdAndName();
		request.setAttribute("scsList", scsList);
		return "study/studydata/addStudydata.jsp";
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/addStudyData", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson addStudyData(HttpServletRequest request, StudyDataDTO dto,
			@RequestParam(required = false) CommonsMultipartFile file) {
		this.studyDataService.addStudyData(dto, file);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}

	/**
	 * 单击修改跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.edit, method = { RequestMethod.GET })
	public String detailStudyData(HttpServletRequest request,
			@RequestParam(value = "sdId", required = true) Long sdId) {
		this.wrapMenuTitle(request);
		List<StudyCategory> scsList = studyCategoryService
				.getStudyCategoriesAndIdAndName();
		StudyDataDTO stuDataDto = studyDataService.getStudyDataById(sdId);
		request.setAttribute("scsList", scsList);
		request.setAttribute("Dto", stuDataDto);
		return "study/studydata/editStudydata.jsp";
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/editStudyData", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson updateStudyData(HttpServletRequest request,
			StudyDataDTO dto,
			@RequestParam(required = false) CommonsMultipartFile file) {
		// this.studyDataService.updateStudyData(dto);
		this.studyDataService.updateStudyData(dto, file);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}

	/**
	 * 下载学习资料
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("/downloadStudyData")
	public void downloadStudyData(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		DownLoadDTO result = this.studyDataService.downloadStudyData(id);
		this.createDownLoadStream(request, response, result);
	}

	/**
	 * 下载学习资料
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	// @RequestMapping(value = "downloadFeedback", method = { RequestMethod.POST
	// })
	// public void opinionFeedbackByIdDownload(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam(value = "id", required = true) Long id) {
	//
	// Map<Long, String> cache = new HashMap<Long, String>();
	// StudyDataDTO studyDataDTO = studyDataService.getFileStudyDataById(id);
	// if (studyDataDTO.getZonePathId() != null) {
	// String prefixPath = null;
	// ZonePathDTO zp = this.zonePathService.getZonePathById(studyDataDTO
	// .getZonePathId());
	// cache.put(studyDataDTO.getZonePathId(), zp.getPath());
	// prefixPath = zp.getPath();
	// studyDataDTO.setPattern(prefixPath + studyDataDTO.getPattern());
	// System.out.println(prefixPath);
	// this.createDownLoadStream(request, response,
	// new DownLoadDTO("学习资料-" + studyDataDTO.getFilename(),
	// studyDataDTO.getPattern()));
	// }
	// }
}
