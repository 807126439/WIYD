package com.wb.web.system.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.dto.department.JobDepartRelationQueryDTO;

public interface IJobDepartRelationService {

	public Page<JobDepartRelationDTO> searchJobDepartRelationByPage(JobDepartRelationQueryDTO queryDTO);
	
	public JobDepartRelationDTO getJobDepartRelationById(String id);
	
	public void addJobDepartRelation(JobDepartRelationDTO dto);
	
	public void updateJobDepartRelation(JobDepartRelationDTO dto);
	
	public void deleteJobDepartRelation(String[] ids);

	public List<String> getUserMainDepart(String userId);

	
}
