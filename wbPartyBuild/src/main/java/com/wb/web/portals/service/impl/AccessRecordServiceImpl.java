package com.wb.web.portals.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.service.BaseService;
import com.wb.web.portals.dao.IAccessRecordDao;
import com.wb.web.portals.dto.accessRecord.AccessRecordDTO;
import com.wb.web.portals.entity.AccessRecord;
import com.wb.web.portals.service.IAccessRecordService;
import com.wb.web.system.service.ILoginLogService;

@Service("accessRecordService")
@Transactional
public class AccessRecordServiceImpl extends BaseService implements IAccessRecordService{
	@Resource
	private IAccessRecordDao accessRecordDao;
	@Resource
	private ILoginLogService loginLogService;
	
	@Override
	public void accessIncreas() {
		AccessRecordDTO dto=new AccessRecordDTO();
		if(null==this.accessRecordDao.getAccessRecord()){
			this.accessRecordDao.save(new AccessRecord(0l,0l));
		}
		dto=this.accessRecordDao.getAccessRecord();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String d1 = sdf.format(dto.getLastOperatorTime());//上次添加记录操作时间日期
	    String d2 = sdf.format(new Date(System.currentTimeMillis()));//当前日期
		//判断是否当天，是当天不用操作，不是即创建新记录原记录加上操作时间之后，登录日志不重复的条数。
	    if(!(d2.equals(d1))){
	    	Long todayaccess=this.loginLogService.searchCountsAfterDate(dto.getLastOperatorTime());
	    	AccessRecord dt=new AccessRecord();
			dt.setTodayAccess(0l);
			dt.setTotalAccess(dto.getTotalAccess()+todayaccess-1);
			this.accessRecordDao.save(dt);
		}
		
	}
	public AccessRecordDTO getLastRecord(){
		AccessRecordDTO dto=new AccessRecordDTO();
		if(null==this.accessRecordDao.getAccessRecord()){
			this.accessRecordDao.save(new AccessRecord(0l,0l));
		}
		dto=this.accessRecordDao.getAccessRecord();
		return dto;
	}
	
	
}
