package com.wb.web.study.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.study.dto.topic.TopicDTO;
import com.wb.web.study.dto.topic.TopicQueryDTO;
import com.wb.web.study.entity.Topic;

public interface ITopicDao extends IBaseDao<Long, Topic>{
	/**
	 * 根据编号查找study_category_id的数量
	 * @param id
	 * @return
	 */
	public Long getTopicByIdFindcateName(Long id);
	
	public Page<TopicDTO> searchTopicPage(TopicQueryDTO queryDTO);
	
	public List<TopicDTO> getTopicsRandom(Long categoryType,Integer topicType,Integer num);

	
}
