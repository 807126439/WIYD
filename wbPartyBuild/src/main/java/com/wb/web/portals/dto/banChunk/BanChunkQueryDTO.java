package com.wb.web.portals.dto.banChunk;

import com.wb.core.common.dto.BaseQueryDTO;

public class BanChunkQueryDTO extends BaseQueryDTO{
	private long treeParId=0;
	private Integer level;
	private long activityId;
	private int model;		//将编辑板块与编辑内容分为两个模式：1.板块编辑只显示非叶子节点。2.编辑模式把所有节点都显示
	
	
	public long getTreeParId() {
		return treeParId;
	}
	public void setTreeParId(long treeParId) {
		this.treeParId = treeParId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	
	

}
