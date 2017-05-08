package com.wb.web.study.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.study.dao.IExamPaperDao;
import com.wb.web.study.dto.examPaper.ExamPaperDTO;
import com.wb.web.study.dto.examPaper.ExamPaperQueryDTO;
import com.wb.web.study.dto.examPaper.OnlineTestDTO;
import com.wb.web.study.dto.topic.TopicDTO;
import com.wb.web.study.entity.ExamPaper;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.entity.Topic;
import com.wb.web.study.service.IExamPaperService;
import com.wb.web.study.service.ITopicService;

@Service("examPaperService")
@Transactional
public class ExamPaperServiceImpl extends BaseService implements IExamPaperService{
	
	
	@Resource
	private IExamPaperDao examPaperDao;
	@Resource
	private ITopicService topicService;
	
	
	@Override
	public Page<ExamPaperDTO> searchExamPaperPage(ExamPaperQueryDTO queryDTO) {
		
		return this.examPaperDao.searchExamPaperPage(queryDTO);
	}

	@Override
	public void deleteExamPaper(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			ExamPaper examPaper = this.examPaperDao.getById(ids[i]);
			if (examPaper!= null) {		
				this.examPaperDao.delete(examPaper);
			}
			if (i % 20 == 0) {
				this.examPaperDao.flush();
				this.examPaperDao.clear();
			}
		}
	}

	@Override
	public void addExamPaper(ExamPaperDTO dto) {
		
		Assert.notNull(dto.getExamMinute(), "考试时间不能为空");
		Assert.notNull(dto.getPaperName(), "试卷名称不能为空");
	
		
		ExamPaper examPaper = new ExamPaper(
				dto.getPaperName(),
				dto.getExamMinute(),
				dto.getPaperType(),
				new Date(),
				getNowUser().getUsername(),
				getNowUser().getUsername()
				);
		
		if( dto.getPaperType() == 1){
		    examPaper.setBlankNum(dto.getBlankNum());
			examPaper.setJudgeNum(dto.getJudgeNum());
			examPaper.setSingleNum(dto.getSingleNum());
			examPaper.setMultiNum(dto.getMultiNum());
		    examPaper.setStudyCategory(new StudyCategory(dto.getCategoryId()));
		}else if(dto.getPaperType() == 2){		
			Assert.notNull(dto.getChoosentopic(),"请选择试题");
			String topics[] = dto.getChoosentopic().split(",");			
			Set<Topic> topicSet = new HashSet<Topic>();			
			for (int i = 0; i < topics.length; i++) {
				Topic topic = new Topic(Long.valueOf(topics[i]));
				topicSet.add(topic);
			}
			examPaper.setTopics(topicSet);			
		}
		this.examPaperDao.save(examPaper);
	}

	@Override
	public void editExamPaper(ExamPaperDTO dto) {
		
		Assert.notNull(dto.getExamMinute(), "考试时间不能为空");
		Assert.notNull(dto.getPaperName(), "试卷名称不能为空");
		ExamPaper examPaper = this.examPaperDao.getById(dto.getId());
		examPaper.setLastOperatorTime(new Date());
		
		//随机类型
		if(dto.getPaperType() == 1){
			examPaper.setStudyCategory(new StudyCategory(dto.getCategoryId()));
			examPaper.setPaperName(dto.getPaperName());
			examPaper.setExamMinute(dto.getExamMinute());
			examPaper.setBlankNum(dto.getBlankNum());
			examPaper.setJudgeNum(dto.getJudgeNum());
			examPaper.setSingleNum(dto.getSingleNum());
			examPaper.setMultiNum(dto.getMultiNum());
			
		}else if(dto.getPaperType() == 2){
			//指定选题
			String topics[] = dto.getChoosentopic().split(",");	
			Set<Topic> topicSet = new HashSet<Topic>();	
			for (int i = 0; i < topics.length; i++) {
				Topic topic = new Topic(Long.valueOf(topics[i]));
				topicSet.add(topic);
			}
			examPaper.setTopics(topicSet);	
			examPaper.setPaperName(dto.getPaperName());
			examPaper.setExamMinute(dto.getExamMinute());
		}		
		this.examPaperDao.update(examPaper);
		
	}

	@Override
	public ExamPaperDTO getExamPaperDTOById(Long id) {
		Assert.notNull(id, "信息异常");
		ExamPaper examPaper = this.examPaperDao.getById(id);
		ExamPaperDTO dto = new ExamPaperDTO();		
		this.getMapper().map(examPaper, dto);	
		if(dto.getPaperType() ==1){		
			dto.setCategoryId(examPaper.getStudyCategory().getId());
		}	
		if(dto.getPaperType() ==2){		
			Set<Topic> topicSet = examPaper.getTopics();		
			StringBuffer choosenTopic = new StringBuffer();
			StringBuffer cN = new StringBuffer();
			StringBuffer tY = new StringBuffer();
			StringBuffer score = new StringBuffer();
			StringBuffer text = new StringBuffer();
		
		//  1：单选  2：多选  3：判断  4：填空	
		
				int i = 0;
				for(Topic topic:topicSet){				
					choosenTopic.append(topic.getId()+",");
					cN.append(topic.getStudyCategory().getCateName()+",");
					
					if(topic.getTopicType()==1){
						tY.append("单选,");
					}if(topic.getTopicType()==2){
						tY.append("多选,");
					}if(topic.getTopicType()==3){
						tY.append("判断,");												
					}if(topic.getTopicType()==4){
						tY.append("填空,");						
					}
					
					
					score.append(topic.getScore()+",");
					text.append(topic.getQuestionText()+",");
					
					i++;
				}		
				dto.setChoosentopic(choosenTopic.toString().substring(0,choosenTopic.toString().length()-1));	
				dto.setTopicCName(cN.toString().substring(0,cN.toString().length()-1));	 	
				dto.setTopicTypeName(tY.toString().substring(0,tY.toString().length()-1));	
				dto.setTopicScore(score.toString().substring(0,score.toString().length()-1));	
				dto.setTopicText(text.toString().substring(0,text.toString().length()-1));	
				
				
				 
		}
		return dto;
	}


	@Override
	public OnlineTestDTO getOnlinePaperById(Long id) {
		ExamPaper examPaper = this.examPaperDao.getById(id);
		
		OnlineTestDTO dto = new OnlineTestDTO(
				examPaper.getId(),
				examPaper.getPaperName(),
				examPaper.getExamMinute()
				);
		
		//随机试卷
		if(examPaper.getPaperType() == 1){
			
			List<TopicDTO> resultList = new ArrayList();
			List<TopicDTO> singleList = null;
			List<TopicDTO> multiList  = null;
			List<TopicDTO> judgeList  = null;
			List<TopicDTO> blankList  = null;
			
			if(examPaper.getSingleNum()!=null&&examPaper.getSingleNum()>0){
				singleList = this.topicService.getTopicsRandom(examPaper.getStudyCategory().getId(),1,examPaper.getSingleNum());					
			}if(examPaper.getMultiNum()!=null&&examPaper.getMultiNum()>0){
				 multiList = this.topicService.getTopicsRandom(examPaper.getStudyCategory().getId(),2,examPaper.getMultiNum());				
			}if(examPaper.getJudgeNum()!=null&&examPaper.getJudgeNum()>0){
				 judgeList = this.topicService.getTopicsRandom(examPaper.getStudyCategory().getId(),3,examPaper.getJudgeNum());	
			}if(examPaper.getBlankNum()!=null&&examPaper.getBlankNum()>0){
				 blankList = this.topicService.getTopicsRandom(examPaper.getStudyCategory().getId(),4,examPaper.getBlankNum());		
			}	
						
			if(singleList!=null){
				resultList.addAll(singleList);
			}if(multiList!=null){
				resultList.addAll(multiList);
			}if(judgeList!=null){	
				resultList.addAll(judgeList);
			}if(blankList!=null){
				resultList.addAll(blankList);			
			}
			
			
			dto.setTopicList(resultList);	
			dto.setTopicSize(resultList.size());
			
			Integer totalScore = 0;
			for (int i = 0; i < resultList.size(); i++) {
				totalScore = totalScore+resultList.get(i).getScore();
				resultList.get(i).setSortNum((long) (i+1));
				//  1：单选  2：多选  3：判断  4：填空				
				if(resultList.get(i).getTopicType()==1){
					resultList.get(i).setTopicTypeName("单选题");
				}else if(resultList.get(i).getTopicType()==2){
					resultList.get(i).setTopicTypeName("多选题");
				}else if(resultList.get(i).getTopicType()==3){
					resultList.get(i).setTopicTypeName("判断题");
				}else if(resultList.get(i).getTopicType()==4){
					resultList.get(i).setTopicTypeName("填空题");
					
					//填空题有多少个空
					String[] array =  resultList.get(i).getAnswer().split(",");
					String[] array2 =  resultList.get(i).getAnswer().split("，");
					
					if(array.length>0){
						resultList.get(i).setFillNums(array.length);
					}else{
						resultList.get(i).setFillNums(array2.length);
					}
					
					
				}
			}
			dto.setTotalScore(totalScore);
			dto.setPassScore((int) (totalScore*0.6));
			
		}else if (examPaper.getPaperType() == 2){
			
			Set<Topic> topics = examPaper.getTopics();
			List<TopicDTO> topicList = new ArrayList<TopicDTO>();
			for(Topic topic:topics){	
				TopicDTO tDTO = new TopicDTO();				
				this.getMapper().map(topic,tDTO);
				tDTO.setTopicId(topic.getId());
				//填空题有多少个空
				if(topic.getTopicType() == 4){
					String[] array =  topic.getAnswer().split(",");
					String[] array2 =  topic.getAnswer().split("，");
					if(array.length>0){
						tDTO.setFillNums(array.length);
					}else{
						tDTO.setFillNums(array2.length);
					}
				
				}

				
				topicList.add(tDTO);			
			}	
				
			dto.setTopicList(topicList);
			dto.setTopicSize(topicList.size());
			
			Integer totalScore = 0;
			for (int i = 0; i < topicList.size(); i++) {
				totalScore = totalScore+topicList.get(i).getScore();
				topicList.get(i).setSortNum((long) (i+1));
				
				if(topicList.get(i).getTopicType()==1){
					topicList.get(i).setTopicTypeName("单选题");
				}else if(topicList.get(i).getTopicType()==2){
					topicList.get(i).setTopicTypeName("多选题");
				}else if(topicList.get(i).getTopicType()==3){
					topicList.get(i).setTopicTypeName("判断题");
				}else if(topicList.get(i).getTopicType()==4){
					topicList.get(i).setTopicTypeName("填空题");
				}
			}
			dto.setTotalScore(totalScore);
			dto.setPassScore((int) (totalScore*0.6));
		}
		return dto;
	}

	@Override
	public List<ExamPaperDTO> listExamPaper() {
		return examPaperDao.listExamPaper();
	}

	
	

}
