package com.wb.web.workflow.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.workflow.dao.IAttachmentDao;
import com.wb.web.workflow.dao.IHistoryCommentDao;
import com.wb.web.workflow.dao.ITaskDao;
import com.wb.web.workflow.entity.HistoryComment;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IHistoryCommentService;

@Service(value="historyCommentService")
@Transactional
public class HistoryCommentServiceImpl extends BaseService implements IHistoryCommentService{
	
	
	@Resource
	private IHistoryCommentDao historyCommentDao;
	@Resource
	private ITaskDao taskDao;
	@Resource
	private IAttachmentDao attachmentDao;
	
	
	/**
	 * 添加审核意见
	 * @param taskId
	 * @param content
	 * @param ucode
	 */
	public void addComment(Long taskId,String content,String ucode){
		Assert.notNull(taskId, "taskId could not be null");
		Assert.hasText(content, "意见不能为空！");
		
		Task task = this.taskDao.getById(taskId);
		if(task!=null){
			HistoryComment comment = new HistoryComment(getNowUserId(), content, task.getType(),
					task.getProcessInstance().getId(), taskId);
			
			this.historyCommentDao.save(comment);			
			
		}
	}
	
}
