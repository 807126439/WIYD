package com.wb.web.workflow.service;

import com.wb.core.common.bean.Page;
import com.wb.web.workflow.dto.task.TaskDetail;
import com.wb.web.workflow.dto.task.TaskInfo;
import com.wb.web.workflow.dto.task.TaskQueryDTO;

public interface ITaskService {

	public void finishTaskByProcExeId(Long procExeId);
	
	public TaskInfo getCurrTaskByProcExeId(Long procExeId,String nodeId,String userId);
	
	public Page<TaskDetail> searchTaskDetail(TaskQueryDTO queryDTO);
	
	public TaskDetail getTaskDetailById(Long taskId);
	
	public void handleTask(Long taskId,String choiceNodeId,String content);
	
	public void finishProcess(Long taskId,String content);
	
	public void rollBackTask(Long taskId);
	
	public void deleteProcessByProcInstId(Long procInstId);
}
