package com.wb.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.system.dao.IJobDutyDao;
import com.wb.web.system.dto.jobDuty.JobDutyDTO;
import com.wb.web.system.dto.jobDuty.JobDutyQueryDTO;
import com.wb.web.system.entity.JobDuty;
import com.wb.web.system.service.IJobDutyService;

/**
 * 
 * @author wb_java_zjr
 *
 */
@Service("jobDutyService")
@Transactional
public class JobDutyServiceImpl extends BaseService implements IJobDutyService{
	@Resource
	private IJobDutyDao jobDutyDao;
	
	
	/**
	 * 分页查询对象
	 * @param queryDTO
	 * @return
	 */
	public Page<JobDutyDTO> searchJobDutyByPage(JobDutyQueryDTO queryDTO){
			
		Page<JobDuty> pageResult = this.jobDutyDao.searchEntityByPage(queryDTO.wrapQueryVal());
		
		List<JobDutyDTO> dtoList = new ArrayList<JobDutyDTO>();
		for (JobDuty jt : pageResult.getList()) {
			JobDutyDTO dto = new JobDutyDTO();
			this.getMapper().map(jt, dto);
			dtoList.add(dto);
		}
		
		return new Page<JobDutyDTO>(pageResult.getCurrentPage(), pageResult.getPageSize(), dtoList, pageResult.getRecTotal());
	}
	
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public JobDutyDTO getJobDutyById(String id){
		Assert.hasText(id, "id must not be null");
		
		JobDuty jt = this.jobDutyDao.getById(id);
		JobDutyDTO dto = new JobDutyDTO();
		this.getMapper().map(jt, dto);
		
		return dto;
	}
	
	
	/**
	 * 添加职务
	 * @param dto
	 */
	public void addJobDuty(JobDutyDTO dto){
		Assert.hasText(dto.getJobName(), "职务名称不能为空！");
		Assert.hasText(dto.getJobNum(), "职务编号不能为空！");
		
		JobDuty jt = new JobDuty(
						 dto.getJobName(), 
						 dto.getJobCode(),
						 dto.getJobMemo(), 
						 dto.getLevel(), 
						 dto.getJobNum(), 
						 getNowUser().getUsername()); 
		
		this.jobDutyDao.save(jt);
		
	}
	
	
	/**
	 * 修改职务
	 * @param dto
	 */
	public void updateJobDuty(JobDutyDTO dto){
		Assert.hasText(dto.getId(), "id must not be null");
		Assert.hasText(dto.getJobName(), "职务名称不能为空！");
		Assert.hasText(dto.getJobNum(), "职务编号不能为空！");
		
		JobDuty jt = this.jobDutyDao.getById(dto.getId());
		
		jt.update(
				 dto.getJobName(), 
				 dto.getJobCode(),
				 dto.getJobMemo(), 
				 dto.getLevel(), 
				 dto.getJobNum(), 
				 getNowUser().getUsername()); 
		
		this.jobDutyDao.update(jt);
		
	}
	
	
	/**
	 * 删除职务
	 * @param ids
	 * @return
	 */
	public AjaxJson deleteJobDuty(String[] ids){
		StringBuilder sb = new StringBuilder();
		
		if(ids!=null && ids.length>0){
			for (int i = 0; i < ids.length; i++) {
				JobDuty jt = this.jobDutyDao.getById(ids[i]);
				if(jt!=null){
					
					if(jt.getRelations().size()>0){
						sb.append("编号："+jt.getJobNum()+" 的职务存在部门引用，删除失败！\n");	
					}else{
						this.jobDutyDao.delete(jt);
					}
				}
			}
			
			
			if(sb.length() == 0){			
				return new AjaxJson("删除成功！",AjaxJson.success);			
			}else{
				return new AjaxJson(sb.toString(),AjaxJson.error);	
			}
		
		}else{
			return new AjaxJson("操作记录不能为空!",AjaxJson.error);	
		}
	
		
	}
	
	
}
