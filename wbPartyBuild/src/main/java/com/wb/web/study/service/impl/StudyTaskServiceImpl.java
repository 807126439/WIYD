package com.wb.web.study.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.study.dao.IStudyTaskDao;
import com.wb.web.study.dto.studyTask.StudyTaskDTO;
import com.wb.web.study.dto.studyTask.StudyTaskQueryDTO;
import com.wb.web.study.entity.ExamPaper;
import com.wb.web.study.entity.StudyData;
import com.wb.web.study.entity.StudyTask;
import com.wb.web.study.service.IStudyTaskService;
import com.wb.web.system.entity.User;

@Service("studyTaskService")
@Transactional
public class StudyTaskServiceImpl  extends BaseService implements IStudyTaskService {
	@Resource
	private IStudyTaskDao studyTaskDao;
	@Override
	public Long getStudyTaskByIdExists(Long id) {
		return this.studyTaskDao.getStudyTaskByIdExists(id);
	}
	/**
	 * 分页
	 */
	@Override
	public Page<StudyTaskDTO> searchStudyTaskPage(StudyTaskQueryDTO queryDTO) {
		return studyTaskDao.searchStudyTaskPage(queryDTO);
	}
	/**
	 * 添加
	 */
	public void addStudyTask(StudyTaskDTO dto,Long[] taskStu) {
		Assert.notNull(dto.getStatus(), "任务状态不能为空!");
		Assert.hasText(dto.getTaskName(), "任务名称不能为空!");
		Assert.hasText(dto.getTaskNum(), "任务编号不能为空!");
		
		StudyTask studyData=new StudyTask(
				dto.getTaskNum(),
				dto.getTaskName(),
				dto.getTaskMemo(),
				dto.getStartTime(),
				dto.getEndTime(), 
				dto.getStatus(), 
				null,
				null,
				this.getNowUser().getUsername(),
				this.getNowUser().getUsername()
			);
		
		if(taskStu!=null){
			Set<StudyData> authSet = new HashSet<StudyData>();
			for (int i = 0; i < taskStu.length; i++) {
				authSet.add(new StudyData(taskStu[i]));
				
			}
			studyData.setStudyData(authSet);
		}
		if (dto.getPaperid()!=null) {
			if(dto.getPaperid()>0){
				studyData.setExamPaper(new ExamPaper(dto.getPaperid()));
			}else {
				studyData.setExamPaper(null);
			}
		}
		this.studyTaskDao.save(studyData);
	}
	/**
	 * 根据id查找一条信息
	 */
	@Override
	public StudyTaskDTO getStudyTaskById(Long stId) {
		return studyTaskDao.getStudyTaskById(stId); 
	}
	/**
	 * 使用baseDao中根据id查找一条信息的方法
	 * @param stId
	 * @return
	 */
	public StudyTaskDTO getStudyTaskByIdUseBaseDao(Long stId) {
	
		StudyTask dt = this.studyTaskDao.getById(stId);
		
		StudyTaskDTO dto = new StudyTaskDTO();
		this.getMapper().map(dt, dto);
		dto.setStId(dt.getId());
		
		if(dt.getExamPaper()!=null){
			dto.setPaperid(dt.getExamPaper().getId());
			dto.setPaperName(dt.getExamPaper().getPaperName());			
		}
		
		return dto; 
	}
	/**
	 * 修改学习任务
	 */
	@Override
	public void updateStudyTask(StudyTaskDTO dto,Long[] taskStu) {
		Assert.notNull(dto.getStId(), "Id不能为空!");
		Assert.hasText(dto.getTaskNum(), "任务编号不能为空!");
		Assert.hasText(dto.getTaskName(), "任务名称不能为空!");
		StudyTask studyTask=this.studyTaskDao.getById(dto.getStId());
		studyTask.setLastOperatorTime(new Date());
		studyTask.setTaskNum(dto.getTaskNum());
		studyTask.setTaskName(dto.getTaskName());
		studyTask.setTaskMemo(dto.getTaskMemo());
		studyTask.setStartTime(dto.getStartTime());
		studyTask.setEndTime(dto.getEndTime());
		studyTask.setStatus(dto.getStatus());
		studyTask.setUpdateBy(this.getNowUser().getUsername());
		
		if (taskStu != null) {
			Set<StudyData> authSet = new HashSet<StudyData>();
			for (int i = 0; i < taskStu.length; i++) {
				authSet.add(new StudyData(taskStu[i]));
			}
			studyTask.getStudyData().clear();
			studyTask.setStudyData(authSet);
		} else {
			studyTask.getStudyData().clear();
		}
		
		if (dto.getPaperid()!=null) {
			if (dto.getPaperid()>0) {
				studyTask.setExamPaper(new ExamPaper(dto.getPaperid()));
			}else {
				studyTask.setExamPaper(null);
			}
		}
	
		this.studyTaskDao.update(studyTask);
		
		
		
		
	}

	@Override
	public AjaxJson deleteStudyTask(Long[] ids ) {
		Assert.notNull(ids, "Id不能为空!");
		StringBuilder stuBuilder=new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			StudyTask st = this.studyTaskDao.getById(ids[i]);

				Set<StudyData> authSet = new HashSet<StudyData>();
				st.setStudyData(authSet);
				Set<User> finishUers = new HashSet<User>(); 
				st.setFinishUers(finishUers);
				this.studyTaskDao.delete(st);
				if (i % 20 == 0) {
					this.studyTaskDao.flush();
					this.studyTaskDao.clear();
				}

		}
		
		if (stuBuilder.length()>0) {
			return new AjaxJson(stuBuilder.toString(),AjaxJson.error);
		}else {
			return new AjaxJson("删除 成功！",AjaxJson.success);
		}
	}
	
	/**
	 * 判断用户有无完成学习任务
	 * @param list
	 * @param nowUser
	 */
	public void checkIfFinished(List<StudyTaskDTO> list, String userId) {
		for(StudyTaskDTO dto:list){
			dto.setFinish(this.studyTaskDao.checkIfFinished(dto.getStId(),userId));
		}
	}
	
	
	
	
	@Override
	public Page<StudyTaskDTO> getAppPage(StudyTaskQueryDTO queryDTO) {
		Page<StudyTaskDTO> dtoPage=this.studyTaskDao.getAppPage(queryDTO);	
		for (StudyTaskDTO dto : dtoPage.getList()) {
			StudyTask task = this.studyTaskDao.getById(dto.getStId());
			
			int num = task.getStudyData().size();
			if(num>0){
				String dataName="";				
				for (StudyData  data : task.getStudyData()) {
					dataName = data.getDataName()+",";					
				}
				dto.setDataName(dataName.substring(0, dataName.length()-1));				
			}else{
				dto.setDataName("");
			}
		}
		return dtoPage;
	}
	
	
	public Integer countTotalSize(String userId){		
		return this.studyTaskDao.countTotalSize(userId);
	}
	
	
	

}
