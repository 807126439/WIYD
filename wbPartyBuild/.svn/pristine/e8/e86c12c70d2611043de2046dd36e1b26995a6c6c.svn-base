package com.wb.web.workflow.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.workflow.dto.attachment.AttachmentDTO;
import com.wb.web.workflow.dto.attachment.AttachmentQueryDTO;

public interface IAttachmentService {
	
	public void addAttachment(Long taskId,CommonsMultipartFile uploadFile);
	
	public List<AttachmentDTO> getAttachmentByTaskId(Long taskId);
	
	public Page<AttachmentDTO> searchByPage(AttachmentQueryDTO queryDTO);
	
	public DownLoadDTO getDownAttachmentInfo(Long id);
	
	public void deleteAttachmentByTaskId(Long taskId);
	
	public void updateStats(Long[] ids,Short status);
	
	
}
