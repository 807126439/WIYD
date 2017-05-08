package com.wb.web.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.wb.core.common.entity.BaseEntity;


/**
 * 流程定义
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="flow_proc_definition")
public class ProcessDefinition extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -468052513574606076L;
	public static final int INVAILD_STATUS  = 0;		  //删除	
	public static final int NORMAL_STATUS = 1;	  //正常	


	private String processCode;		//流程标识符
	private String processName;     //流程名
	private String description;     //描述
	private Integer version;		//版本号
	private Date createTime;		//创建时间
	private String updateBy;		//修改者 
	private Integer status;			//状态
	
	
	
	
	public ProcessDefinition(){}
	
	
	
	
	
	public ProcessDefinition(String processCode, String processName,
			String description,String updateBy, Integer status,Integer version) {
		super();
		this.processCode = processCode;
		this.processName = processName;
		this.description = description;
		this.createTime = new Date();
		this.updateBy = updateBy;
		this.status = status;
		this.version = version;
	}
	
	
	
	@Column(name="process_code",length=80,nullable=false)
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	
	
	@Column(name="process_name",length=80,nullable=false)
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	
	@Column(name="description",length=1000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	@Column(name="status",nullable=false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
	
}
