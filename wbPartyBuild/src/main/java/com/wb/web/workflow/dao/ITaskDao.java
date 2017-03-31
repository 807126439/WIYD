package com.wb.web.workflow.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.workflow.dto.task.TaskDetail;
import com.wb.web.workflow.dto.task.TaskQueryDTO;
import com.wb.web.workflow.entity.Task;

public interface ITaskDao extends IBaseDao<Long, Task>{
	
	public Task getApplyTaskByCondition(Long procExeId,String nodeId,String owner);
	
	public List<Task> getTasksByProcExeId(Long procExeId,Short type);
	
	public List<Task> getTasksByPreExeId(Long preExeId,Short type);
	
	public Page<TaskDetail> searchTaskDetailtByCondition(TaskQueryDTO queryDTO);
	
	public TaskDetail getTaskDetailByTaskId(Long taskId);
	
	public boolean checkIsCanHandleTask(Long taskId,String userName,List<String> groupIds);
	
	public void deleteByProcInstId(Long procInstId);
	
	public List<Long> getTaskIdByParentExeId(Long parentExeId);
}
