package com.wb.web.study.controller;

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
import com.wb.web.study.dto.studycategory.StudyCategoryDTO;
import com.wb.web.study.dto.studycategory.StudyCategoryQueryDTO;
import com.wb.web.study.service.IStudyCategoryService;

@Controller
@Scope("prototype")
@RequestMapping("/studyCategoryController")
public class StudyCategoryController extends BaseController{

	private static final long serialVersionUID = 5824714622135883349L;
	@Resource
	private IStudyCategoryService studyCategoryService;
	
	/**
	 * 跳转查看分页查询
	 * 
	 * @param request
	 * @param sf
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET})
	public String viewStudyCategoryPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "study/studycategory/studyCategoryList.jsp";
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
	public Map<String, Object> loadStudyCategoryByPage(HttpServletRequest request,
			StudyCategoryQueryDTO queryDTO) {
		this.wrapTableQueryParams(request, queryDTO);
		Page<StudyCategoryDTO> pageResult = this.studyCategoryService.searchStudyCategoryPage(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		return jsonMap;
	}

	/**
	 * 单击添加跳转页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddStudyCategory(HttpServletRequest request){
		this.wrapMenuTitle(request);
		return "study/studycategory/addStudyCategory.jsp";
	}
	
	/**
	 * 添加
	 * 
	 * @param dto
	 * @param pwd
	 * @param pwd2
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value = "/addCategory", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson addStudyCategory(HttpServletRequest request,StudyCategoryDTO dto) {
		
		this.studyCategoryService.addStudyCategory(dto);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}

	/**
	 * 单击修改跳转页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = MyRequestParam.edit, method = { RequestMethod.GET })
	public ModelAndView studyCategoryGetById(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) {
		StudyCategoryDTO categoryDto = this.studyCategoryService.getStudyCategoryById(id);
		request.setAttribute("categoryDto", categoryDto);
		return new ModelAndView("study/studycategory/editStudyCategory.jsp");
	}
	/**
	 * 修改
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/editCategory",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateCategory(StudyCategoryDTO dto){
	
		this.studyCategoryService.updateStudyCategory(dto);	
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
		
	/**
	 * 删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "categoryDel", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson studyCategoryDel(Long[] dtoId) {
		return studyCategoryService.deleteStudyCategory(dtoId);
	}
	
	
	
}
