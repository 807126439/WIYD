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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.study.dto.examPaper.ExamPaperDTO;
import com.wb.web.study.dto.examPaper.ExamPaperQueryDTO;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.service.IExamPaperService;
import com.wb.web.study.service.IStudyCategoryService;


@Controller
@Scope("prototype")
@RequestMapping("/examPaperController")


public class ExamPaperController extends BaseController{
	
private static final long serialVersionUID = 8711854367024728721L;
	
	@Resource
	private IExamPaperService examPaperService;
	@Resource
	private IStudyCategoryService studyCategoryService;
	

	
	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET})
	public String viewExamPaperPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "study/examPaper/examPaperList.jsp";
	}	
	
	
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddExamPaper(HttpServletRequest request,Integer type){	
		
		
		if(type ==1){		
			List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
			request.setAttribute("categoryList", categoryList);
			return "study/examPaper/addExamPaperR.jsp";
		}else{
			List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
			request.setAttribute("categoryList", categoryList);
		
			return "study/examPaper/addExamPaperC.jsp";			
		}
	}
	

	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public String skipEditExamPaper(HttpServletRequest request,Long id){	

		ExamPaperDTO result = this.examPaperService.getExamPaperDTOById(id);
		request.setAttribute("result", result);
			
		if(result.getPaperType() ==1){
			
			List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
			request.setAttribute("categoryList", categoryList);
			return "study/examPaper/editExamPaperR.jsp";  
		}else{
			List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
			request.setAttribute("categoryList", categoryList);		
			return "study/examPaper/editExamPaperC.jsp";			
		}		
		
	}
	
	
	
	
	
	@RequestMapping(value="/editExamPaper",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson editExamPaper(ExamPaperDTO dto){	
		this.examPaperService.editExamPaper(dto);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="/addExamPaper",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addExamPaper(ExamPaperDTO dto){	
		this.examPaperService.addExamPaper(dto);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	

	
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadExamPaperByPage(HttpServletRequest request,ExamPaperQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<ExamPaperDTO> pageResult = this.examPaperService.searchExamPaperPage(queryDTO);		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());			
		return jsonMap;
	}
	
	
	@RequestMapping(value="deleteExamPaper",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson delExamPaper(Long[] ids){		
		this.examPaperService.deleteExamPaper(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}

}
