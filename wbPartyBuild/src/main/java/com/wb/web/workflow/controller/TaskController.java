package com.wb.web.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.workflow.dto.node.ChoiceNodeDTO;
import com.wb.web.workflow.dto.task.TaskDetail;
import com.wb.web.workflow.dto.task.TaskInfo;
import com.wb.web.workflow.dto.task.TaskQueryDTO;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IAttachmentService;
import com.wb.web.workflow.service.IProcessNodeService;
import com.wb.web.workflow.service.ITaskService;

@Controller
@Scope("prototype")
@RequestMapping("/task")
public class TaskController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6713406253137284124L;
	
	@Resource
	private ITaskService taskService;
	@Resource
	private IAttachmentService attachmentService;
	@Resource
	private IProcessNodeService processNodeService;
	
	
	
	@RequestMapping(value="/getCurrTask")
	public ModelAndView getCurrTask(@RequestParam(required=true)Long procExeId,@RequestParam(required=true)String nodeId){
		
		TaskInfo result = this.taskService.getCurrTaskByProcExeId(procExeId,nodeId, getNowUser().getUsername());
		
		return new ModelAndView("work-flow/task/taskDetail.jsp","info",result);
		
	}

	
	/**
	 * 跳转到我的任务页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public String viewMyTask(HttpServletRequest request){
		this.wrapMenuTitle(request);
		
		return "work-flow/task/myTaskList.jsp";
	}
	
	
	  
	/**
	 * 分页查询我的任务
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("/getMyTaskData")
	@ResponseBody
	public Map<String, Object> loadMyTaskByPage(HttpServletRequest request,TaskQueryDTO queryDTO){

		this.wrapTableQueryParams(request, queryDTO);
		
		queryDTO.setUserName(getNowUser().getUsername());
		queryDTO.setGroupIds(getNowUser().getRoleIds());
		queryDTO.setStatus(Task.UNFINISH_STATUS);
		Page<TaskDetail> pageResult = this.taskService.searchTaskDetail(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	
	/**
	 * 查询单个任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getDetail")
	public ModelAndView loadTask(HttpServletRequest request,@RequestParam(value="tid",required=true)Long tid){
			  
		
		TaskDetail result = this.taskService.getTaskDetailById(tid);
	    request.setAttribute("taskItem", result);
	    
	    List<ChoiceNodeDTO> nodes = this.processNodeService.getNextNodes(result.getNodeId());
	    request.setAttribute("nodes", nodes);
		
		return new ModelAndView("work-flow/task/handleTask.jsp");
	
	}
	
	
	
    /**
     * 办理任务
     * @param taskId
     * @param choiceNodeId
     * @param content
     * @return
     * @throws Exception
     */
	@RequestMapping("/handleTask")
	@ResponseBody
	public AjaxJson handleTask(Long taskId,String choiceNodeId,String content) throws Exception{
	
		this.taskService.handleTask(taskId, choiceNodeId, content);
				
		return new AjaxJson("处理成功！", AjaxJson.success);
	}
	
	
	
	@RequestMapping("/finishProcess")
	@ResponseBody
	public AjaxJson finishProcess(Long taskId,String content) throws Exception{
	
		this.taskService.finishProcess(taskId, content);
				
		return new AjaxJson("处理成功！", AjaxJson.success);
	}
	
	/**
	 * 回滚任务
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rollBackTask")
	@ResponseBody
	public AjaxJson rollBackTask(Long taskId) throws Exception{
	
		this.taskService.rollBackTask(taskId);
				
		return new AjaxJson("回滚成功！", AjaxJson.success);
	}
	
	
	
	
	
}
