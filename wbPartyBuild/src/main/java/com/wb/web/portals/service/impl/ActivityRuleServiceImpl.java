package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IActivityRuleDao;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.activityRule.ActivityRuleQueryDTO;
import com.wb.web.portals.entity.ActivityRule;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IActivityRuleService;


@Service("activityRuleService")
@Transactional
public class ActivityRuleServiceImpl extends BaseService implements IActivityRuleService{

	@Resource
	private IActivityRuleDao activityRuleDao;
	
	

	public void deleteActivityRule(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			ActivityRule as = this.activityRuleDao.getById(ids[i]);
			if(as!=null){
				this.activityRuleDao.delete(as);
			}			
			if(i%20 ==0){
				this.activityRuleDao.flush();
				this.activityRuleDao.clear();
			}
		}
		
	}




	public ActivityRuleDTO getActivityRuleById(Long id) {
		Assert.notNull(id, "Id不能为空!");
		ActivityRule activityRule = this.activityRuleDao.getById(id);
		ActivityRuleDTO dto = new ActivityRuleDTO();	
		this.getMapper().map(activityRule, dto);
		return dto;
	}



	public void addActivityRule(ActivityRuleDTO dto) {
		Assert.notNull(dto.getActivityId(), "主题活动不能为空!");
		Assert.notNull(dto.getContent(), "内容不能为空!");
		Assert.notNull(dto.getNum(), "序号不能为空!");
		
		ActivityRule activityRule = new ActivityRule(
				new ThemeActivity(dto.getActivityId()),
				dto.getNum(),
				dto.getIsMain(),
				dto.getContent()
				);
		
		this.activityRuleDao.save(activityRule);		
	}



	public void updateActivityRule(ActivityRuleDTO dto) {
		Assert.notNull(dto.getId(), "Id不能为空!");
		Assert.notNull(dto.getActivityId(), "主题活动不能为空!");
		Assert.notNull(dto.getContent(), "内容不能为空!");
		Assert.notNull(dto.getNum(), "序号不能为空!");
		
		ActivityRule activityRule = this.activityRuleDao.getById(dto.getId());
		
		activityRule.setContent(dto.getContent());
		activityRule.setNum(dto.getNum());
		activityRule.setIsMain(dto.getIsMain());
		
		this.activityRuleDao.update(activityRule);
		
	}



	public Page<ActivityRuleDTO> searchActivityRuleByPage(
			ActivityRuleQueryDTO queryDTO) {
		Page<ActivityRule> result = this.activityRuleDao.searchActivityRuleByPage2(queryDTO);
		List<ActivityRule> list = result.getList();
		
		
		List<ActivityRuleDTO> dtoList = new ArrayList<ActivityRuleDTO>();
		for (int i = 0; i < list.size(); i++) {
			ActivityRuleDTO dto = new ActivityRuleDTO();
			this.getMapper().map(list.get(i), dto);				
			dtoList.add(dto);			
		}
		
		return  new Page<ActivityRuleDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}

	
	
	
	
}
