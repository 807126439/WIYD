package com.wb.web.workflow.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.workflow.dto.attachment.AttachmentQueryDTO;
import com.wb.web.workflow.entity.Attachment;

public interface IAttachmentDao extends IBaseDao<Long, Attachment>{

	public List<Attachment> getAttachmentByProcInstId(List<Long> procInstIds);
	
	public List<Attachment> getAttachmentByTaskId(Long taskId);
	
	public Page<Attachment> searchAttachmentByPage(AttachmentQueryDTO queryDTO);
	
	public void deleteByProcInstId(Long procInstId);
	
	public void updateStats(Long[] ids,Short status);
}
