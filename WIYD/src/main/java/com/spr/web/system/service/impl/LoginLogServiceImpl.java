package com.spr.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.service.BaseService;
import com.spr.core.utils.IpUtils;
import com.spr.web.system.dao.ILoginLogDao;
import com.spr.web.system.dto.log.LoginLogDTO;
import com.spr.web.system.entity.LoginLog;
import com.spr.web.system.service.ILoginLogService;

@Service("loginLogService")
@Transactional
public class LoginLogServiceImpl extends BaseService implements ILoginLogService{
    @Resource
	private ILoginLogDao loginLogDao;
	
    
    /**
     * 保存日志
     * @param dto
     */
    public void saveLog(LoginLogDTO dto){
    	LoginLog log = new LoginLog();
    	this.getMapper().map(dto, log);
    	
    	this.loginLogDao.insert(log);
    	

    }
    
	
    /**
     * 分页
     * @param queryDTO
     * @return
     */
	public Page<LoginLogDTO> searchLoginLogByPage(DataQuery dq) {
	
		Long recTotal = this.loginLogDao.countByCondition(dq.assemblePageOffset().getQueryMap());
		
		List<LoginLog> list = this.loginLogDao.selectListByCondition(dq.assembleOrderInfo(LoginLog.class, null).getQueryMap());
		
		List<LoginLogDTO> resultList = new ArrayList<LoginLogDTO>();
		for (LoginLog log : list) {
			LoginLogDTO dto = new LoginLogDTO();
			this.getMapper().map(log, dto);
			dto.setConvertIP(IpUtils.longToIP(log.getLoginIp()));
			
			resultList.add(dto);
		}
		
				
		return  new Page<LoginLogDTO>(dq.getCurrentPage(), dq.getPageSize(), resultList, recTotal);
	}
    
	
	
	
	/**
	 * 删除日志
	 * @param ids
	 */
	public void deleteLogs(Long[] ids){
		
		for (int i = 0; i < ids.length; i++) {			
		    this.loginLogDao.deleteById(ids[i]);				
		}
	}
    
    
}
