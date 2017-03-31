package com.wb.web.workflow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.workflow.dao.IAttachmentDao;
import com.wb.web.workflow.dao.ITaskDao;
import com.wb.web.workflow.dto.attachment.AttachmentDTO;
import com.wb.web.workflow.dto.attachment.AttachmentQueryDTO;
import com.wb.web.workflow.entity.Attachment;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IAttachmentService;

@Service("attachmentService")
@Transactional
public class AttachmentServiceImpl extends BaseService implements IAttachmentService  {
	
	@Resource
	private IAttachmentDao attachmentDao;
	@Resource
	private ITaskDao taskDao;
	@Resource
	private IBaseFileService baseFileService;
	
	
	/**
	 * 上传附件
	 * @param taskId		任务id
	 * @param uploadFile    上传文件
	 */
	public void addAttachment(Long taskId,CommonsMultipartFile uploadFile){
		Assert.notNull(taskId, "taskId could not be null");
		Assert.notNull(uploadFile, "上传文件不能为空！");
		
		Task task = this.taskDao.getById(taskId);
		if(task!=null){
	
			SaveResult as =  this.baseFileService.addBaseFile(uploadFile);
			Attachment attachment = new Attachment(getNowUserId(),
					as.getFileName(), null, task.getType(), task.getProcessInstance().getId(),
					taskId,as.getId());
			
			this.attachmentDao.save(attachment);
						
		}
	}
	
	

	/**
	 * 根据任务id查询附件
	 * @param taskId
	 * @return
	 */
	public List<AttachmentDTO> getAttachmentByTaskId(Long taskId){
		
		List<Attachment> resultList = this.attachmentDao.getAttachmentByTaskId(taskId);
		List<AttachmentDTO> dtoList = new ArrayList<AttachmentDTO>();
		for (Attachment at : resultList) {
			AttachmentDTO dto = new AttachmentDTO();
			this.getMapper().map(at, dto);
			
			dtoList.add(dto);
		}
		
		
		return dtoList;
	}
	
	
	public Page<AttachmentDTO> searchByPage(AttachmentQueryDTO queryDTO){
		
		Page<Attachment> pageResult = this.attachmentDao.searchAttachmentByPage(queryDTO);
		
		List<AttachmentDTO> dtoList = new ArrayList<AttachmentDTO>();
		for (Attachment at : pageResult.getList()) {
			AttachmentDTO dto = new AttachmentDTO();
			this.getMapper().map(at, dto);
			if(queryDTO.getTaskIds()!=null){
				if(queryDTO.getTaskIds().contains(at.getTaskId().toString())){
					dto.setIsThisTask(true);
				}
			}
			
			
			dtoList.add(dto);
		}
		
		return new Page<AttachmentDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), dtoList, pageResult.getRecTotal());
	}
	
	
	/**
	 * 下载附件
	 */
	public DownLoadDTO getDownAttachmentInfo(Long id){
		Assert.notNull(id, "id could not be null");
		
		Attachment attachment = this.attachmentDao.getById(id);
		if(attachment!=null){
			DownLoadDTO result = this.baseFileService.getDownLoadInfoById(attachment.getBaseFileId());
			result.setFileName(attachment.getName());
			
			return result;
			
		}else{
			throw new MyException("Counld not find the record!");
		}
		
		
	}
	
	
	/**
	 * 根据任务id删除附件
	 * @param taskId
	 */
	public void deleteAttachmentByTaskId(Long taskId){
		Assert.notNull(taskId, "taskId could not be null");
		List<Attachment> attachments = this.attachmentDao.getAttachmentByTaskId(taskId);
		for (Attachment at : attachments) {
			if(at.getBaseFileId()!=null){
					this.baseFileService.deleteBaseFile(at.getBaseFileId());			
			}
			
			this.attachmentDao.delete(at);
		}
	}
	
	
	
	public void updateStats(Long[] ids,Short status){
		
		this.attachmentDao.updateStats(ids, status);
		
	}
	
	
	
}
