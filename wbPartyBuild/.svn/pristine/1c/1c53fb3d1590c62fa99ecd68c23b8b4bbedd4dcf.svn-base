package com.wb.web.system.dto.jobDuty;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.wb.core.common.dto.BaseQueryDTO;

public class JobDutyQueryDTO extends BaseQueryDTO{
	
	private String jobName; 
	private String jobNum;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	} 
	
	
	public JobDutyQueryDTO wrapQueryVal(){
		this.queryMap = new HashMap<String, Object>();
		
		if(!StringUtils.isBlank(jobName)){
			this.queryMap.put("jobName", jobName);
		}
		
		if(!StringUtils.isBlank(jobNum)){
			this.queryMap.put("jobNum", jobNum);
		}
		
		return this;
	}
	
}
