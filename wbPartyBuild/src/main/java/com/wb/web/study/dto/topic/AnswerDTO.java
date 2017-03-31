package com.wb.web.study.dto.topic;

public class AnswerDTO {
	
	private Long topicId;
	private Integer[] rightIndex;
	private String[] rightText;
	private String[] analysisList;
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Integer[] getRightIndex() {
		return rightIndex;
	}
	public void setRightIndex(Integer[] rightIndex) {
		this.rightIndex = rightIndex;
	}
	public String[] getRightText() {
		return rightText;
	}
	public void setRightText(String[] rightText) {
		this.rightText = rightText;
	}
	public String[] getAnalysisList() {
		return analysisList;
	}
	public void setAnalysisList(String[] analysisList) {
		this.analysisList = analysisList;
	}
	
}
