package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IActivityRuleDao;
import com.wb.web.portals.dao.IAwardWinningWorksDao;
import com.wb.web.portals.dao.IAwardsSettingDao;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.awardWinningWorks.AwardDTO;
import com.wb.web.portals.dto.awardWinningWorks.AwardDTO2;
import com.wb.web.portals.dto.awardWinningWorks.AwardMessage;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksDTO;
import com.wb.web.portals.dto.awardWinningWorks.AwardWinningWorksQueryDTO;
import com.wb.web.portals.entity.AwardWinningWorks;
import com.wb.web.portals.entity.AwardsSetting;
import com.wb.web.portals.entity.Manuscript;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IAwardWinningWorksService;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.service.IZonePathService;
@Service("awardWinningWorksService")
@Transactional
public class AwardWinningWorksServiceImpl extends BaseService implements IAwardWinningWorksService{

	@Resource
	private IAwardWinningWorksDao awardWinningWorksDao;
	@Resource
	private IActivityRuleDao activityRulesDao;
	@Resource
	private IAwardsSettingDao awardsSettingDao;
	@Resource
	private IZonePathService zonePathService;
	
	
	
	@Override
	public AwardWinningWorksDTO getAwardWinningWorksById(Long id) {
		Assert.notNull(id,"获奖作品ID不能为空!");
		
		AwardWinningWorks dt=awardWinningWorksDao.getById(id);
		AwardWinningWorksDTO dto=new AwardWinningWorksDTO();
		dto.setActivityId(dt.getActivity().getId());
		dto.setAwardsSettingId(dt.getAwardsSetting().getId());
		dto.setAwwId(dt.getId());
		dto.setComment(dt.getComment());
		dto.setManuscriptId(dt.getManuscript().getId());
		dto.setManuscriptTitle(dt.getManuscript().getTitle());
		dto.setManuscriptUsername(dt.getManuscript().getUser().getUserName());
		dto.setAwardsName(dt.getAwardsSetting().getAwardsName());
		
		
		return dto;
	}

	@Override
	public void addAwardWinningWorks(AwardWinningWorksDTO dto) {
		Assert.notNull(dto.getActivityId(), "活动ID不能为空!");
		Assert.notNull(dto.getAwardsSettingId(), "奖项ID不能为空!");
		Assert.notNull(dto.getManuscriptId(), "稿件ID不能为空!");
		
		AwardWinningWorks aww=new AwardWinningWorks(
				new ThemeActivity(dto.getActivityId()),
				new AwardsSetting(dto.getAwardsSettingId()),
				new Manuscript(dto.getManuscriptId()),
				dto.getComment(),
				new Date(),
				this.getNowUser().getUsername());
		awardWinningWorksDao.save(aww);
		
	}

	@Override
	public void updateAwardWinningWorks(AwardWinningWorksDTO dto) {
		Assert.notNull(dto.getAwwId(), "获奖作品ID不能为空!");
		Assert.notNull(dto.getAwardsSettingId(), "奖项ID不能为空!");
		
		AwardWinningWorks aww=new AwardWinningWorks();
		aww.setId(dto.getAwwId());
		aww.setComment(dto.getComment());
		aww.setAwardsSetting(new AwardsSetting(dto.getAwardsSettingId()));
		
		
		awardWinningWorksDao.update((aww));
	}

	@Override
	public void deleteAwardWinningWorks(Long[] awwIds) {
		if(null!=awwIds){
			for(int i=0;i<awwIds.length;i++){
				AwardWinningWorks dt=awardWinningWorksDao.getById(awwIds[i]);
				awardWinningWorksDao.delete(dt);
			}
		}
	}

	@Override
	public Page<AwardWinningWorksDTO> searchAwardWinningWorksByPage(
			AwardWinningWorksQueryDTO queryDTO) {
		
		
		Page<AwardWinningWorks> result = this.awardWinningWorksDao.searchAwardWinningWorksByPage(queryDTO);
		List<AwardWinningWorks> list = result.getList();
		List<AwardWinningWorksDTO> dtoList = new ArrayList<AwardWinningWorksDTO>();


		for (AwardWinningWorks dt:list) {
			AwardWinningWorksDTO dto = new AwardWinningWorksDTO();
			dto.setActivityId(dt.getActivity().getId());
			dto.setAwardsSettingId(dt.getAwardsSetting().getId());
			dto.setAwwId(dt.getId());
			dto.setManuscriptId(dt.getManuscript().getId());
			dto.setManuscriptTitle(dt.getManuscript().getTitle());
			dto.setManuscriptUsername(dt.getManuscript().getUser().getUserName());
			dto.setAwardsName(dt.getAwardsSetting().getAwardsName());
			
			dtoList.add(dto);
		}
		
		return new Page<AwardWinningWorksDTO>(result.getCurrentPage(),
				result.getPageSize(), dtoList, result.getRecTotal());
	}
	
	

	@Override
	public AwardMessage getAwardMessage(Long activityId) {	
		AwardMessage am = new AwardMessage();
		List<ActivityRuleDTO> ruleList = this.activityRulesDao.getAllRulesByActivityId(activityId);	
		List<AwardDTO> awardList =  this.awardsSettingDao.getAllAwardByActivityId(activityId);		
		for (int i = 0; i < awardList.size(); i++) {		
			List<AwardDTO2> manuscriptList = this.awardWinningWorksDao.getAwardMessage(activityId,awardList.get(i).getId());			
			if(manuscriptList.size()>0){
				ZonePathDTO zp = this.zonePathService.getZonePathById(manuscriptList.get(0).getZonePathId());
				for (int j = 0; j < manuscriptList.size(); j++) {
					manuscriptList.get(j).setPattern(zp.getVirtualPath()+manuscriptList.get(j).getPattern());	
				}
			}
			awardList.get(i).setManuscriptList(manuscriptList);		
		}
		am.setAwardList(awardList);
		am.setRuleList(ruleList);		
		return am;
	}

	

	@Override
	public Integer checkIsAward(Long activityId) {
		return this.awardWinningWorksDao.checkIsAward(activityId);
	}
		

	
		
}
