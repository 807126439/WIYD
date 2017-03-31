package com.wb.web.system.service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.web.system.dto.jobDuty.JobDutyDTO;
import com.wb.web.system.dto.jobDuty.JobDutyQueryDTO;

public interface IJobDutyService {
	
	public Page<JobDutyDTO> searchJobDutyByPage(JobDutyQueryDTO queryDTO);
	
	public JobDutyDTO getJobDutyById(String id);
	
	public void addJobDuty(JobDutyDTO dto);
	
	public void updateJobDuty(JobDutyDTO dto);
	
	public AjaxJson deleteJobDuty(String[] ids);
}
