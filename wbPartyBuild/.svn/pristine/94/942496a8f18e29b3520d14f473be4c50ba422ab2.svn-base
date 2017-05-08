package com.wb.web.study.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.web.study.dto.topic.AnswerDTO;
import com.wb.web.study.dto.topic.TopicDTO;
import com.wb.web.study.dto.topic.TopicQueryDTO;

public interface ITopicService {

	public void importTopic(TopicDTO dto,CommonsMultipartFile file);
	
	public Page<TopicDTO> searchTopicPage(TopicQueryDTO queryDTO);
	
	public void deleteTopic(Long[] ids);
	
	public void addTopic(TopicDTO dto);
	
	public void editTopic(TopicDTO dto);
	
	public TopicDTO getTopicDTOById(Long id);

	public Long getTopicByIdFindcateName(Long id);
	
	public List<TopicDTO> getTopicsRandom(Long categoryType,Integer topicType,Integer num);

	public AnswerDTO getTopicDetail(Long topicId);
}

