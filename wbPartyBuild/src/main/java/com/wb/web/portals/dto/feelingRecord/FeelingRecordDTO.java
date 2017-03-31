package com.wb.web.portals.dto.feelingRecord;

import java.util.Date;

import com.wb.core.common.dto.BaseQueryDTO;

public class FeelingRecordDTO extends BaseQueryDTO {

	private String id;				//用户id
	private String ownerId;			//发布用户
	private String title;			//标题
	private String content;			//内容
	private Date createTime;		//创建时间
	private Long sort;				//排序
	private String ownerName;
	private String name;
	private int number;
	
	
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	
}
