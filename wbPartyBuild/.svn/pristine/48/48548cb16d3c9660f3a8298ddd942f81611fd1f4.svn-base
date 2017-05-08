package com.wb.web.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.system.dao.IJobDepartRelationDao;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.dto.department.JobDepartRelationQueryDTO;
import com.wb.web.system.entity.Department;
import com.wb.web.system.entity.JobDepartRelation;
import com.wb.web.system.entity.JobDuty;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IJobDepartRelationService;

/**
 * 
 * @author wb_java_zjr
 *
 */
@Service("jobDepartRelationService")
@Transactional
public class JobDepartRelationServiceImpl extends BaseService implements IJobDepartRelationService {
	@Resource
	private IJobDepartRelationDao jobDepartRelationDao;
	
	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	public Page<JobDepartRelationDTO> searchJobDepartRelationByPage(JobDepartRelationQueryDTO queryDTO){
		
		return this.jobDepartRelationDao.searchJobDepartRelationByPage(queryDTO);
	}
	
	
	public JobDepartRelationDTO getJobDepartRelationById(String id){
		Assert.hasText(id, "id must not be null");
		
		return this.jobDepartRelationDao.getJobDepartRelationById(id);
		
	}
	
	
	
	public void addJobDepartRelation(JobDepartRelationDTO dto){
		Assert.hasText(dto.getDepartId(), "部门不能为空！");
		Assert.hasText(dto.getJobId(), "职务不能为空！");
		
		
		JobDepartRelation jdr = new JobDepartRelation(
									new Department(dto.getDepartId()), 
									new JobDuty(dto.getJobId()), 
									dto.getUserId() == null ? null:new User(dto.getUserId()), 
									dto.getMemo(), 
									dto.getStatus(),
									this.getNowUser().getUsername(),
									dto.getSortNum());
		
		
		this.jobDepartRelationDao.save(jdr);
	}
	
	
	
	public void updateJobDepartRelation(JobDepartRelationDTO dto){
		Assert.hasText(dto.getId(), "id must not be null");
		Assert.hasText(dto.getDepartId(), "部门不能为空！");
		Assert.hasText(dto.getJobId(), "职务不能为空！");
		
		JobDepartRelation jdr = this.jobDepartRelationDao.getById(dto.getId());
		jdr.update(
				new Department(dto.getDepartId()), 
				new JobDuty(dto.getJobId()), 
				dto.getUserId() == null ? null:new User(dto.getUserId()), 
				dto.getMemo(), 
				dto.getStatus(),
				this.getNowUser().getUsername());
		jdr.setSortNum(dto.getSortNum());
		
		
		this.jobDepartRelationDao.update(jdr);
	}
	
	
	
	public void deleteJobDepartRelation(String[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			JobDepartRelation jdr = this.jobDepartRelationDao.getById(ids[i]);
			if(jdr!=null){
				this.jobDepartRelationDao.delete(jdr);
			}
		}
		
	}


	@Override
	public List<String> getUserMainDepart(String userId) {
		return this.jobDepartRelationDao.getUserMainDepart(userId);
	}



	
	
	
}
