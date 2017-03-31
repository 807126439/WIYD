package com.wb.web.study.dto.examPaper;

import java.util.List;

import com.wb.web.study.dto.topic.TopicDTO;

public class OnlineTestDTO {
	
	private Long id;
	private String paperName;	
	private Integer testTime;	
	private Integer topicSize;	
	private Integer totalScore;	
	private Integer passScore;		
	private List<TopicDTO> topicList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Integer getTestTime() {
		return testTime;
	}
	public void setTestTime(Integer testTime) {
		this.testTime = testTime;
	}
	public Integer getTopicSize() {
		return topicSize;
	}
	public void setTopicSize(Integer topicSize) {
		this.topicSize = topicSize;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getPassScore() {
		return passScore;
	}
	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}
	public List<TopicDTO> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<TopicDTO> topicList) {
		this.topicList = topicList;
	}
	public OnlineTestDTO(Long id, String paperName, Integer testTime
			) {
		super();
		this.id = id;
		this.paperName = paperName;
		this.testTime = testTime;
	}
	public OnlineTestDTO() {
		super();
	}
	
	
		
}
