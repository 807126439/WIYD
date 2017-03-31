package com.wb.web.study.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.utils.ExcelUtil;
import com.wb.web.study.dao.ITopicDao;
import com.wb.web.study.dto.topic.AnswerDTO;
import com.wb.web.study.dto.topic.TopicDTO;
import com.wb.web.study.dto.topic.TopicQueryDTO;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.entity.Topic;
import com.wb.web.study.service.ITopicService;
@Service("topicService")
@Transactional
public class TopicServiceImpl extends BaseService implements ITopicService {
	@Resource
	private ITopicDao topicDao;

	@Override
	public void importTopic(TopicDTO dto, CommonsMultipartFile file)  {
		//根据指定的文件输入流导入Excel从而产生Workbook对象
		Workbook wb;
		try {
			wb = new HSSFWorkbook(file.getInputStream());
			//获取Excel文档中的第一个表单
			Sheet sht0 = wb.getSheetAt(0);
			for (Row r : sht0) {
		        //从第二行开始循环			
				if(r.getRowNum()>0&&r.getFirstCellNum() == 0&& r.getCell(0)!=null && !r.getCell(0).equals("") &&r.getCell(0).getCellType() !=HSSFCell.CELL_TYPE_BLANK){					
					if(r!=null){
						Topic topic = new Topic(new Date(),
								new StudyCategory(dto.getCategoryId()),
								getNowUser().getUsername(),
								getNowUser().getUsername());
									
					    topic.setTopicType(Integer.valueOf(ExcelUtil.getCellValue((HSSFCell) r.getCell(0))));	
						topic.setQuestionText(ExcelUtil.getCellValue((HSSFCell) r.getCell(1)));
						topic.setOptionA(ExcelUtil.getCellValue((HSSFCell) r.getCell(2)));
						topic.setOptionB(ExcelUtil.getCellValue((HSSFCell) r.getCell(3)));
						topic.setOptionC(ExcelUtil.getCellValue((HSSFCell) r.getCell(4)));
						topic.setOptionD(ExcelUtil.getCellValue((HSSFCell) r.getCell(5)));
						topic.setOptionE(ExcelUtil.getCellValue((HSSFCell) r.getCell(6)));
						topic.setOptionF(ExcelUtil.getCellValue((HSSFCell) r.getCell(7)));
						topic.setOptionG(ExcelUtil.getCellValue((HSSFCell) r.getCell(8)));
						topic.setAnswer(ExcelUtil.getCellValue((HSSFCell) r.getCell(9)));
						topic.setExplainA(ExcelUtil.getCellValue((HSSFCell) r.getCell(10)));
						topic.setExplainB(ExcelUtil.getCellValue((HSSFCell) r.getCell(11)));
						topic.setExplainC(ExcelUtil.getCellValue((HSSFCell) r.getCell(12)));
						topic.setExplainD(ExcelUtil.getCellValue((HSSFCell) r.getCell(13)));
						topic.setExplainE(ExcelUtil.getCellValue((HSSFCell) r.getCell(14)));
						topic.setExplainF(ExcelUtil.getCellValue((HSSFCell) r.getCell(15)));
						topic.setExplainG(ExcelUtil.getCellValue((HSSFCell) r.getCell(16)));
						topic.setScore(Integer.valueOf(ExcelUtil.getCellValue((HSSFCell) r.getCell(17))));
						this.topicDao.save(topic);						
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Page<TopicDTO> searchTopicPage(TopicQueryDTO queryDTO) {
		Page<TopicDTO> page = this.topicDao.searchTopicPage(queryDTO);
		List<TopicDTO> list = page.getList();
		
		
		
		for (int i = 0; i<list.size(); i++) {
			int x = list.get(i).getTopicType();
			if(x == 1){
				list.get(i).setTopicTypeName("单选");				
			}else if(x == 2){
				list.get(i).setTopicTypeName("多选");
			}else if(x == 3){
				list.get(i).setTopicTypeName("判断");
			}else{
				list.get(i).setTopicTypeName("填空");
			}
						
			if(queryDTO.getChoosentopic()!=""&& queryDTO.getChoosentopic()!=null){				
				String str[] = queryDTO.getChoosentopic().split(",");				
				for (int j = 0; j < str.length; j++) {
					if(Long.valueOf(str[j]) == list.get(i).getTopicId() ){		
						
						list.get(i).setCheck(true);
					}
				}
			}
			
		}
		if(queryDTO.getChoosentopic()!=""&& queryDTO.getChoosentopic()!=null){	
			
			Collections.sort(list,new Comparator<TopicDTO>(){
				@Override
				public int compare(TopicDTO o1, TopicDTO o2) {
					        int i = 0 ;
				       if(o1.getCheck() == o2.getCheck()){
				    	   i = 0;
				       } 
				       if(i == 0){
				    	   return (int) (o1.getTopicId() - o2.getTopicId());
				       }
				        return i;
				}
				
			});						
		}		
		return page;
	}

	@Override
	public void deleteTopic(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Topic topic = this.topicDao.getById(ids[i]);
			if (topic!= null) {				
				this.topicDao.delete(topic);
			}
			if (i % 20 == 0) {
				this.topicDao.flush();
				this.topicDao.clear();
			}
		}
		
	}

	@Override
	public void addTopic(TopicDTO dto) {
		Assert.notNull(dto.getQuestionText(), "题目文本不能为空");
		Assert.notNull(dto.getAnswer(), "答案不能为空");
		Assert.notNull(dto.getCategoryId(), "题目类别不能为空");
		Assert.notNull(dto.getTopicType(), "题目类型不能为空");
		
		Topic topic = new Topic(new Date(),new StudyCategory(dto.getCategoryId()),getNowUser().getUsername(),getNowUser().getUsername());
			
		
		topic.setTopicType(dto.getTopicType());
		topic.setQuestionText(dto.getQuestionText());
		topic.setAnswer(dto.getAnswer());
		if(dto.getOptionA()!=null&&dto.getOptionA()!=""){			
			topic.setOptionA(dto.getOptionA());			
		}if(dto.getOptionB()!=null&&dto.getOptionB()!=""){			
			topic.setOptionB(dto.getOptionB());			
		}if(dto.getOptionC()!=null&&dto.getOptionC()!=""){			
			topic.setOptionC(dto.getOptionC());			
		}if(dto.getOptionD()!=null&&dto.getOptionD()!=""){			
			topic.setOptionD(dto.getOptionD());			
		}if(dto.getOptionE()!=null&&dto.getOptionE()!=""){			
			topic.setOptionE(dto.getOptionE());			
		}if(dto.getOptionF()!=null&&dto.getOptionF()!=""){			
			topic.setOptionF(dto.getOptionF());			
		}if(dto.getOptionG()!=null&&dto.getOptionG()!=""){			
			topic.setOptionG(dto.getOptionG());			
		}
		
		if(dto.getExplainA()!=null&&dto.getExplainA()!=""){			
			topic.setExplainA(dto.getExplainA());			
		}if(dto.getExplainB()!=null&&dto.getExplainB()!=""){			
			topic.setExplainB(dto.getExplainB());			
		}if(dto.getExplainC()!=null&&dto.getExplainC()!=""){			
			topic.setExplainC(dto.getExplainC());			
		}if(dto.getExplainD()!=null&&dto.getExplainD()!=""){			
			topic.setExplainD(dto.getExplainD());			
		}if(dto.getExplainE()!=null&&dto.getExplainE()!=""){			
			topic.setExplainE(dto.getExplainE());			
		}if(dto.getExplainF()!=null&&dto.getExplainF()!=""){			
			topic.setExplainF(dto.getExplainF());			
		}if(dto.getExplainG()!=null&&dto.getExplainG()!=""){			
			topic.setExplainG(dto.getExplainG());			
		}
		
		
		topic.setScore(dto.getScore());
		this.topicDao.save(topic);		
	}

	@Override
	public void editTopic(TopicDTO dto) {
		Assert.notNull(dto.getQuestionText(), "题目文本不能为空");
		Assert.notNull(dto.getAnswer(), "答案不能为空");
		Assert.notNull(dto.getCategoryId(), "题目类别不能为空");
		Assert.notNull(dto.getTopicType(), "题目类型不能为空");
		
		Topic topic = this.topicDao.getById(dto.getTopicId());
		
		topic.setTopicType(dto.getTopicType());
		topic.setQuestionText(dto.getQuestionText());
		topic.setAnswer(dto.getAnswer());
		topic.setStudyCategory(new StudyCategory(dto.getCategoryId()));
		
		
		if(dto.getOptionA()!=null&&dto.getOptionA()!=""){			
			topic.setOptionA(dto.getOptionA());			
		}if(dto.getOptionB()!=null&&dto.getOptionB()!=""){			
			topic.setOptionB(dto.getOptionB());			
		}if(dto.getOptionC()!=null&&dto.getOptionC()!=""){			
			topic.setOptionC(dto.getOptionC());			
		}if(dto.getOptionD()!=null&&dto.getOptionD()!=""){			
			topic.setOptionD(dto.getOptionD());			
		}if(dto.getOptionE()!=null&&dto.getOptionE()!=""){			
			topic.setOptionE(dto.getOptionE());			
		}if(dto.getOptionF()!=null&&dto.getOptionF()!=""){			
			topic.setOptionF(dto.getOptionF());			
		}if(dto.getOptionG()!=null&&dto.getOptionG()!=""){			
			topic.setOptionG(dto.getOptionG());			
		}
		
		if(dto.getExplainA()!=null&&dto.getExplainA()!=""){			
			topic.setExplainA(dto.getExplainA());			
		}if(dto.getExplainB()!=null&&dto.getExplainB()!=""){			
			topic.setExplainB(dto.getExplainB());			
		}if(dto.getExplainC()!=null&&dto.getExplainC()!=""){			
			topic.setExplainC(dto.getExplainC());			
		}if(dto.getExplainD()!=null&&dto.getExplainD()!=""){			
			topic.setExplainD(dto.getExplainD());			
		}if(dto.getExplainE()!=null&&dto.getExplainE()!=""){			
			topic.setExplainE(dto.getExplainE());			
		}if(dto.getExplainF()!=null&&dto.getExplainF()!=""){			
			topic.setExplainF(dto.getExplainF());			
		}if(dto.getExplainG()!=null&&dto.getExplainG()!=""){			
			topic.setExplainG(dto.getExplainG());			
		}
		
		
		
		topic.setScore(dto.getScore());
		topic.setLastOperatorTime(new Date());
		topic.setUpdateBy(getNowUser().getUsername());
		
		this.topicDao.update(topic);
	}

	@Override
	public TopicDTO getTopicDTOById(Long id) {
		Assert.notNull(id, "数据异常");
		Topic topic = this.topicDao.getById(id);
		TopicDTO dto = new TopicDTO();		
		this.getMapper().map(topic,dto);			
		dto.setTopicId(topic.getId());
		return dto;
	}


	@Override
	public List<TopicDTO> getTopicsRandom(Long categoryType,Integer topicType,Integer num) {
		return this.topicDao.getTopicsRandom(categoryType,topicType,num);
	}
	

	@Override
	public Long getTopicByIdFindcateName(Long id) {		
		return topicDao.getTopicByIdFindcateName(id);
	}

	
	@Override
	public AnswerDTO getTopicDetail(Long topicId) {
		AnswerDTO dto = new AnswerDTO();
		TopicDTO topic = this.getTopicDTOById(topicId);
		
		dto.setTopicId(topic.getTopicId());
	//	dto.setRightText(topic.getAnswer());
		
		List<Integer> rightIndexList = new ArrayList<Integer>();
		List<String> analysisList = new ArrayList<String>();
		List<String> rightTextList = new ArrayList<String>();
				
		//单选
		if(topic.getTopicType() == 1){
			
			if(topic.getAnswer().equals(topic.getOptionA())){
				rightIndexList.add(0);
				if(topic.getExplainA()!=null&&topic.getExplainA()!=""){	
					analysisList.add(topic.getExplainA());				
				}								
			}if(topic.getAnswer().equals(topic.getOptionB())){
				rightIndexList.add(1);
				if(topic.getExplainB()!=null&&topic.getExplainB()!=""){					
					analysisList.add(topic.getExplainB());								
				}	
			}if(topic.getAnswer().equals(topic.getOptionC())){
				rightIndexList.add(2);
				if(topic.getExplainC()!=null&&topic.getExplainC()!=""){					
					analysisList.add(topic.getOptionC());			
				}	
			}if(topic.getAnswer().equals(topic.getOptionD())){
				rightIndexList.add(3);
				if(topic.getExplainD()!=null&&topic.getExplainD()!=""){					
					analysisList.add(topic.getOptionD());			
				}	
			}if(topic.getAnswer().equals(topic.getOptionE())){
				rightIndexList.add(4);
				if(topic.getExplainE()!=null&&topic.getExplainE()!=""){					
					analysisList.add(topic.getOptionE());			
				}	
			}if(topic.getAnswer().equals(topic.getOptionF())){
				rightIndexList.add(5);
				if(topic.getExplainF()!=null&&topic.getExplainF()!=""){					
					analysisList.add(topic.getOptionF());			
				}	
			}if(topic.getAnswer().equals(topic.getOptionG())){
				rightIndexList.add(6);
				if(topic.getExplainG()!=null&&topic.getExplainG()!=""){					
					analysisList.add(topic.getOptionG());				
				}	
			}
			
		}  
		//多选
		if(topic.getTopicType() == 2){
			String[] answerArray = this.getAnswerArray(topic.getAnswer());
			for (int i = 0; i < answerArray.length; i++) {
				if(answerArray[i].equals(topic.getOptionA())){					
					rightIndexList.add(0);
					if(topic.getExplainA()!=null&&topic.getExplainA()!=""){					
						analysisList.add(topic.getExplainA());				
					}					
				}if(answerArray[i].equals(topic.getOptionB())){
					rightIndexList.add(1);
					if(topic.getExplainB()!=null&&topic.getExplainB()!=""){					
						analysisList.add(topic.getExplainB());					
					}
				}if(answerArray[i].equals(topic.getOptionC())){
					rightIndexList.add(2);
					if(topic.getExplainC()!=null&&topic.getExplainC()!=""){					
						analysisList.add(topic.getExplainC());						
					}
				}if(answerArray[i].equals(topic.getOptionD())){
					rightIndexList.add(3);
					if(topic.getExplainD()!=null&&topic.getExplainD()!=""){					
						analysisList.add(topic.getExplainD());						
					}
				}if(answerArray[i].equals(topic.getOptionE())){
					rightIndexList.add(4);
					if(topic.getExplainE()!=null&&topic.getExplainE()!=""){					
						analysisList.add(topic.getExplainE());						
					}
				}if(answerArray[i].equals(topic.getOptionF())){
					rightIndexList.add(5);
					if(topic.getExplainF()!=null&&topic.getExplainF()!=""){					
						analysisList.add(topic.getOptionF());								
					}
				}if(answerArray[i].equals(topic.getOptionG())){
					rightIndexList.add(6);
					if(topic.getExplainG()!=null&&topic.getExplainG()!=""){					
						analysisList.add(topic.getOptionG());						
					}
				}
			}			
		}		
		//判断
		if(topic.getTopicType() == 3 ){
		
			if(topic.getAnswer().equals(topic.getOptionA())){
				rightIndexList.add(0);
			}if(topic.getAnswer().equals(topic.getOptionB())){
				rightIndexList.add(1);
			}
			if(topic.getExplainA()!=null&&topic.getExplainA()!=""){				
				analysisList.add(topic.getExplainA());		
			}
		}
		if(topic.getTopicType() == 4){		
			String[] answerArray = this.getAnswerArray(topic.getAnswer());			
			for (int j = 0; j < answerArray.length; j++) {				
				rightTextList.add(answerArray[j]);				
			}
			if(topic.getExplainA()!=null&&topic.getExplainA()!=""){				
				analysisList.add(topic.getExplainA());				
			}
		}
		

		if(rightIndexList.size()>0){
			
			Integer[] integerArray = new Integer[(rightIndexList.size())];
			rightIndexList.toArray(integerArray);			
			dto.setRightIndex(integerArray);
		}if(rightTextList.size()>0){
			String[] stringArray = new String[(rightIndexList.size())];
			rightTextList.toArray(stringArray);			
			dto.setRightText(stringArray);
		}if(analysisList.size()>0){
			
			String[] stringArray = new String[(analysisList.size())];
			analysisList.toArray(stringArray);
			dto.setAnalysisList(stringArray);
		}
		
		return dto;
		
	}
	
	
	public String[] getAnswerArray(String answer){
		String[] answerArray1 = answer.split(",");
		String[] answerArray2 = answer.split("，");
		if(answerArray1.length>1){
			return answerArray1;
		}
		return answerArray2;
	}

}