package com.wb.web.study.dto.studyData;
import com.wb.core.common.dto.BaseQueryDTO;

public class StudyDataQueryDTO extends BaseQueryDTO{
	private String dataName; // 资料名称
	private String cateName; // 类别名称 
	private String stuIds;
	private Boolean isVideo;
	
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getStuIds() {
		return stuIds;
	}
	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}
	public Boolean getIsVideo() {
		return isVideo;
	}
	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}
	

	
	
	
}
