package com.wb.web.portals.dto.feelingRecord;

import java.util.Date;

import com.wb.core.common.dto.BaseQueryDTO;

public class FeelingCountDTO extends BaseQueryDTO {

	private String id;
	private Date createTime;		//最近一次
	private String name;			//用户名
	private int number;				//次数
	private Long sort;				//排序
	
	
	
	public FeelingCountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public FeelingCountDTO(String id, Date createTime, String name, int number,
			Long sort) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.name = name;
		this.number = number;
		this.sort = sort;
	}
	
	
	
	
}
