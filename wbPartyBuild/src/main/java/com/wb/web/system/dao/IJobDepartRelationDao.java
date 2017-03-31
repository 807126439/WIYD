package com.wb.web.system.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.dto.department.JobDepartRelationQueryDTO;
import com.wb.web.system.entity.JobDepartRelation;

public interface IJobDepartRelationDao extends IBaseDao<String, JobDepartRelation>{
	
	public Page<JobDepartRelationDTO> searchJobDepartRelationByPage(JobDepartRelationQueryDTO queryDTO);
	
	public JobDepartRelationDTO getJobDepartRelationById(String id);

	public List<String> getUserMainDepart(String userId);
}
