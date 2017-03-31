package com.wb.web.workflow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IAttachmentDao;
import com.wb.web.workflow.dao.IHistoryCommentDao;
import com.wb.web.workflow.dao.IProcessExecutionDao;
import com.wb.web.workflow.dao.IRunIdentitylinkDao;
import com.wb.web.workflow.dao.ITaskDao;
import com.wb.web.workflow.dto.task.TaskDetail;
import com.wb.web.workflow.dto.task.TaskInfo;
import com.wb.web.workflow.dto.task.TaskQueryDTO;
import com.wb.web.workflow.entity.HistoryComment;
import com.wb.web.workflow.entity.ProcessExecution;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IAttachmentService;
import com.wb.web.workflow.service.IProcessNodeService;
import com.wb.web.workflow.service.ITaskService;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends BaseService implements ITaskService{
	@Resource
	private ITaskDao taskDao;
	@Resource
	private IAttachmentService attachmentService;
	@Resource
	private IHistoryCommentDao historyCommentDao;
	@Resource
	private IProcessNodeService processNodeService;	
	@Resource
	private IProcessExecutionDao processExecutionDao;
	@Resource
	private IRunIdentitylinkDao runIdentitylinkDao;
	@Resource
	private IAttachmentDao attachmentDao;
	
	/**
	 * 设置流程执行点下所有任务为完成状态
	 * @param procExeId
	 */
	public void finishTaskByProcExeId(Long procExeId){
		Assert.notNull(procExeId, "procExeId could not be null");
		
		List<Task> taskList = this.taskDao.getTasksByProcExeId(procExeId,null);
		for (Task task : taskList) {
			task.setStatus(Task.FINISH_STATUS);
			
			this.taskDao.update(task);
		}
	}
	
	
	
	/**
	 * 根据流程执行点查找任务
	 * @param procExeId
	 * @param userId
	 * @return
	 */
	public TaskInfo getCurrTaskByProcExeId(Long procExeId,String nodeId,String userId){
		Assert.notNull(procExeId, "procExeId could not be null");
				
		Task task = this.taskDao.getApplyTaskByCondition(procExeId, nodeId, userId);
		if(task!=null && task.getStatus() == Task.UNFINISH_STATUS){
			TaskInfo result = new TaskInfo();
			this.getMapper().map(task, result);
			
			
			return result;
			
		}else{
			throw new MyException("The task is expired");
		}
		
	}
	
	
	/**
	 * 分页条件查询
	 * @param queryDTO
	 * @return
	 */
	public Page<TaskDetail> searchTaskDetail(TaskQueryDTO queryDTO) {
	
		Page<TaskDetail> pageResult = this.taskDao.searchTaskDetailtByCondition(queryDTO);
		for (TaskDetail td : pageResult.getList()) {
			td.setHandler(getNowUser().getUsername());
		}
		return  pageResult;
	}
    
	
	/**
	 * 查询任务详情
	 * @param taskId
	 * @return
	 */
	public TaskDetail getTaskDetailById(Long taskId){
		 Assert.notNull("taskId", "taskId could not be null");
		
		TaskDetail result = this.taskDao.getTaskDetailByTaskId(taskId);
		 
		Assert.isTrue(this.taskDao.checkIsCanHandleTask(taskId, getNowUser().getUsername(), getNowUser().getRoleIds()),
				"Can not handle the task");
		
		List<Long> ownerTaskIds = this.taskDao.getTaskIdByParentExeId(result.getProcExeId());
		if(!ownerTaskIds.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (Long id : ownerTaskIds) {
				sb.append(id+",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			result.setOwnerTaskIds(sb.toString());
		}
		
		
		return result;
	}
	
	
	
	/**
	 * 处理任务
	 * @param taskId			任务id
	 * @param choiceNodeId      下一节点
	 * @param content			处理意见
	 */
	public void handleTask(Long taskId,String choiceNodeId,String content){
		Assert.notNull(taskId, "taskId String not be null");
		Assert.hasText(choiceNodeId, "choiceNodeId could not be null");
		Assert.hasText(content, "处理意见不能为空！");
			
		Task task = this.taskDao.getById(taskId);
		Assert.notNull(task, "Could not find the record!");
		
		Assert.isTrue(this.taskDao.checkIsCanHandleTask(taskId, getNowUser().getUsername(), getNowUser().getRoleIds()),
				"Can not handle the task");
		Assert.isTrue(task.getStatus() == Task.UNFINISH_STATUS,
				"The task was finished");
		
		task.setDealUser(getNowUser().getUsername());
		task.setStatus(Task.FINISH_STATUS);
		
		this.taskDao.update(task);
		
		
		//处理意见
		HistoryComment comment = new HistoryComment(getNowUser().getUsername(), content, task.getType(),
				task.getProcessInstance().getId(), taskId);
		
		this.historyCommentDao.save(comment);	
		
		//设置当前流程执行点为结束状态
		ProcessExecution curreExecution = task.getProcessExecution();
		curreExecution.setStatus(ProcessExecution.OVER_STATUS);
		this.processExecutionDao.update(curreExecution);
		
		this.processNodeService.goNextStep(task.getProcessExecution().getId(), choiceNodeId);
		
	}
	
	
	/**
	 * 结束流程
	 * @param taskId
	 * @param content
	 */
	public void finishProcess(Long taskId,String content){
		Assert.notNull(taskId, "taskId String not be null");		
		Assert.hasText(content, "处理意见不能为空！");
			
		Task task = this.taskDao.getById(taskId);
		Assert.notNull(task, "Could not find the record!");
		
		Assert.isTrue(this.taskDao.checkIsCanHandleTask(taskId, getNowUser().getUsername(), getNowUser().getRoleIds()),
				"Can not handle the task");
		Assert.isTrue(task.getStatus() == Task.UNFINISH_STATUS,
				"The task was finished");
		
		task.setDealUser(getNowUser().getUsername());
		task.setStatus(Task.FINISH_STATUS);
		
		this.taskDao.update(task);
		
		//处理意见
		HistoryComment comment = new HistoryComment(getNowUser().getUsername(), content, task.getType(),
				task.getProcessInstance().getId(), taskId);
		
		this.historyCommentDao.save(comment);	
		
		//设置当前流程执行点为结束状态
		ProcessExecution curreExecution = task.getProcessExecution();
		curreExecution.setStatus(ProcessExecution.OVER_STATUS);
		this.processExecutionDao.update(curreExecution);
		
		this.processNodeService.endProcess(curreExecution.getId());
		
	}
	
	
	
	
	/**
	 * 回滚任务，包括删除当前流程执行节点，附件和处理意见
	 * @param taskId
	 */
	public void rollBackTask(Long taskId){
		Assert.notNull(taskId, "taskId String not be null");
		
		Task task = this.taskDao.getById(taskId);
		if(task!=null && task.getStatus() == Task.UNFINISH_STATUS){
			//任务所属流程的当前流程执行点
			ProcessExecution curExecution = this.processExecutionDao.getCurrentExecutionByProcInstId(task.getProcessInstance().getId());
			//判断当前流程执行点是否跟任务所属流程节点一致
			Assert.isTrue(curExecution.getId().equals(task.getProcessExecution().getId()), "此任务属于历史任务，无法回滚！");
			
			//上一级节点
			ProcessExecution preExecution = curExecution.getPreExecution();
			Assert.isTrue(preExecution!=null, "此任务无法回滚！");
			//设置上一级节点为活跃
			preExecution.setStatus(ProcessExecution.ACTIVIT_STATUS);
			//设置上一级节点的任务为未完成
			List<Task> preTasks = this.taskDao.getTasksByProcExeId(preExecution.getId(), null);
			for (Task ot : preTasks) {
				ot.setDealUser(null);
				ot.setStatus(Task.UNFINISH_STATUS);
				this.taskDao.update(ot);
			}
			this.processExecutionDao.update(preExecution);
			
			
			
			
			//删除申请人的任务
			List<Task> ownerTask = this.taskDao.getTasksByPreExeId(curExecution.getId(), Task.SQZ_TYPE);
			for (Task ot : ownerTask) {//删除申请人的附件和意见
				this.historyCommentDao.deleteByTaskId(ot.getId());
				this.attachmentService.deleteAttachmentByTaskId(ot.getId());
				//流程执行点
				this.processExecutionDao.delete(ot.getProcessExecution());
				
				this.taskDao.delete(ot);
			}
			
			
			//删除任务的处理意见
			this.historyCommentDao.deleteByTaskId(task.getId());
			//删除任务的附件
			this.attachmentService.deleteAttachmentByTaskId(task.getId());
			//删除人员关系
			this.runIdentitylinkDao.deleteByTaskId(task.getId());
			//删除任务			
			this.taskDao.delete(task);
			//删除执行点
			this.processExecutionDao.delete(curExecution);
		
		}else{
			throw new MyException("此任务无法回滚！");
		}
		
	}
	
	
	
	/**
	 * 根据流程实例id删除所有有关信息
	 * @param procInstId
	 */
	public void deleteProcessByProcInstId(Long procInstId){
		Assert.notNull(procInstId, "procInstId could not be null");
		
		this.runIdentitylinkDao.deleteByProcInstId(procInstId);
		this.historyCommentDao.deleteByProcInstId(procInstId);
		this.attachmentDao.deleteByProcInstId(procInstId);
		this.taskDao.deleteByProcInstId(procInstId);
		this.processExecutionDao.deleteByProcInstId(procInstId);
		
		
	}
	
	
}
