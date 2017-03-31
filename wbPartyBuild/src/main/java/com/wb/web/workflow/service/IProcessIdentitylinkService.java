package com.wb.web.workflow.service;

import java.util.List;

import com.wb.web.system.dto.user.UserIDAndNameDTO;
import com.wb.web.workflow.entity.ProcessIdentitylink;

public interface IProcessIdentitylinkService {
	
	public List<ProcessIdentitylink>getProcessIdentitylinksById(String nodeId);
	
	public UserIDAndNameDTO getAllEditer(String id);
}
