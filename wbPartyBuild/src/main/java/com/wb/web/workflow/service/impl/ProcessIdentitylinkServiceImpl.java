package com.wb.web.workflow.service.impl;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.dto.user.UserIDAndNameDTO;
import com.wb.web.system.entity.User;
import com.wb.web.workflow.dao.IProcessIdentitylinkDao;
import com.wb.web.workflow.entity.ProcessIdentitylink;
import com.wb.web.workflow.service.IProcessIdentitylinkService;
@Service("processIdentitylinkService")
@Transactional
public class ProcessIdentitylinkServiceImpl extends BaseService implements IProcessIdentitylinkService {

	@Resource
	private IProcessIdentitylinkDao processIdentitylinkDao;
	@Resource 
	private IUserDao userDao;

	@Override
	public List<ProcessIdentitylink> getProcessIdentitylinksById(String nodeId) {
		return processIdentitylinkDao.getProcessIdentitylinksById(nodeId);
	}

	@Override
	public UserIDAndNameDTO getAllEditer(String id) {
		Assert.notNull(id, "id must not be null");
		User dt = this.userDao.getById(id);
		UserIDAndNameDTO userdt = new UserIDAndNameDTO();
		userdt.setId(dt.getId());
		userdt.setUserName(dt.getUserName());
		userdt.setCheck(true);
		return userdt;
	}
}
