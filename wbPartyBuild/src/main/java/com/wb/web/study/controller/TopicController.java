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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.study.dto.topic.AnswerDTO;
import com.wb.web.study.dto.topic.TopicDTO;
import com.wb.web.study.dto.topic.TopicQueryDTO;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.service.IStudyCategoryService;
import com.wb.web.study.service.ITopicService;

@Controller
@Scope("prototype")
@RequestMapping("/topicController")
public class TopicController extends BaseController{

	
	
	private static final long serialVersionUID = 8711854367024728721L;
	
	@Resource
	private ITopicService topicService;
	@Resource
	private IStudyCategoryService studyCategoryService;
	

	
	@RequestMapping(params = MyRequestParam.viewPage, method = { RequestMethod.GET})
	public String viewTopicPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);		
		List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
		request.setAttribute("categoryList", categoryList);
		return "study/topic/topicList.jsp";
	}	
	
	
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddTopic(HttpServletRequest request){	
		List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
		request.setAttribute("categoryList", categoryList);
		return "study/topic/addTopic.jsp";
	}
	

	@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
	public String skipEditTopic(HttpServletRequest request,Long id){	
		List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
		TopicDTO result = this.topicService.getTopicDTOById(id);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("result", result);
		return "study/topic/editTopic.jsp";
	}
	
	@RequestMapping(value="/editTopic",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson editTopic(TopicDTO dto){	
		this.topicService.editTopic(dto);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="/addTopic",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addTopic(TopicDTO dto){	
		this.topicService.addTopic(dto);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	@RequestMapping(params="import",method={RequestMethod.GET})
	public String skipImportTopic(HttpServletRequest request){	
		List<StudyCategory>  categoryList = studyCategoryService.getStudyCategoriesAndIdAndName();
		request.setAttribute("categoryList", categoryList);
		return "study/topic/importTopic.jsp";
	}
	
	
	@RequestMapping(value="/importTopic",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson importTopic(TopicDTO dto,@RequestParam(required=false)CommonsMultipartFile file){	
		
		this.topicService.importTopic(dto, file);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadTopicByPage(HttpServletRequest request,TopicQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<TopicDTO> pageResult = this.topicService.searchTopicPage(queryDTO);		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());			
		return jsonMap;
	}
	
	
	@RequestMapping(value="deleteTopic",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson delTopic(Long[] ids){		
		this.topicService.deleteTopic(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="getTopicDetail",method={RequestMethod.POST})
	@ResponseBody
	public AnswerDTO getTopicDetail(Long id){		
		AnswerDTO result = this.topicService.getTopicDetail(id);
		return result;		
	}
	
	
}
