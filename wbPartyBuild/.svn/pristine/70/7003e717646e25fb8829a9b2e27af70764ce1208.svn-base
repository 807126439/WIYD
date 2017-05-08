package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dao.IActivityRuleDao;
import com.wb.web.portals.dao.IAwardWinningWorksDao;
import com.wb.web.portals.dao.IAwardsSettingDao;
import com.wb.web.portals.dao.IBanChunkDao;
import com.wb.web.portals.dao.IContentDao;
import com.wb.web.portals.dao.IManuscriptDao;
import com.wb.web.portals.dao.IThemeActivityDao;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.manuscript.PhotoDTO;
import com.wb.web.portals.dto.themeActivity.PhotoActivity;
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.dto.themeActivity.ThemeActivityQueryDTO;
import com.wb.web.portals.entity.BanChunk;
import com.wb.web.portals.entity.Content;
import com.wb.web.portals.entity.Manuscript;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IBanChunkService;
import com.wb.web.portals.service.IManuscriptService;
import com.wb.web.portals.service.IThemeActivityService;
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IBaseDictService;
import com.wb.web.system.service.IZonePathService;

@Service("themeActivityService")
@Transactional
public class ThemeActivityServiceImpl extends BaseService implements
		IThemeActivityService {

	
	@Resource
	private IBaseDictService baseDictService;
	@Resource
	private IBaseDictDao baseDictDao;
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IZonePathService zonePathService;
	@Resource
	private IAwardsSettingDao awardsSettingDao;
	@Resource
	private IActivityRuleDao activityRulesDao;
	@Resource
	private IAwardWinningWorksDao awardWinningWorksDao;
	@Resource
	private IManuscriptDao manuscriptDao;
	@Resource
	private IManuscriptService manuscriptService;
	@Resource
	private IThemeActivityDao themeActivityDao;
	@Resource
	private IBanChunkService banChunkService;
	@Resource
	private IBanChunkDao banChunkDao;
	@Resource
	private IContentDao contentDao;
	

 
	
	
	@Override
	public Page<ThemeActivityDTO> searchThemeActivityByPage(
			ThemeActivityQueryDTO queryDTO) {
		Page<ThemeActivity> result = this.themeActivityDao.searchThemeActivityByPage(queryDTO);
				
		List<ThemeActivity> list = result.getList();
		List<ThemeActivityDTO> dtoList = new ArrayList<ThemeActivityDTO>();

		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
		bdqd.setDictType(BaseDict.ACTIVITY_TYPE);
		List<BaseDictDTO> flagDicts = this.baseDictService.searchListByCondition(bdqd);
				
		for (int i = 0; i < list.size(); i++) {
			ThemeActivityDTO dto = new ThemeActivityDTO();
			this.getMapper().map(list.get(i), dto);

			for (int j = 0; j < flagDicts.size(); j++) {
				if (list.get(i).getActivityType().toString()
						.equals(flagDicts.get(j).getDictValue())) {
					dto.setActivityTypeName(flagDicts.get(j).getDictItem());
				}
			}
			dtoList.add(dto);
		}
		return new Page<ThemeActivityDTO>(result.getCurrentPage(),
				result.getPageSize(), dtoList, result.getRecTotal());
	}
	
	
	
	
	
	

	@Override
	public ThemeActivityDTO getThemeActivityById(Long id) {
		Assert.notNull(id, "id must not be null");
		ThemeActivity themeActivity = this.themeActivityDao.getById(id);
		ThemeActivityDTO dto = new ThemeActivityDTO();
		if(themeActivity!=null){
			BaseDict baseDict=this.baseDictDao.searchOneDictByCondition2(BaseDict.ACTIVITY_TYPE,themeActivity.getActivityType().toString(),1);
			if(baseDict!=null){
				dto.setActivityTypeName(baseDict.getDictItem());			
			}			
		}
		this.getMapper().map(themeActivity, dto);
		return dto;
	}
	
	
	

	@Override
	public void addThemeActivity(ThemeActivityDTO dto) {
		Assert.hasText(dto.getActivityName(), "活动名称不能为空");
		Assert.notNull(dto.getStartDate(), "开始时间不能为空");
		Assert.notNull(dto.getEndDate(), "结束时间不能为空");

		ThemeActivity themeActivity = new ThemeActivity(
				dto.getActivityName(),
				dto.getContent(), 
				dto.getStartDate(), 
				dto.getEndDate(),
				dto.getActivityType(), 
				(short)0,     //新增活动默认为失效   
				new Date(), 
				getNowUser().getUsername()
			   );		
		
		
		//1-月月精彩-需要评奖
		//2-城建人组稿-板块
		//3-征文
		if(dto.getActivityType() == 1){
			themeActivity.setIsReward(true);
			themeActivity.setIsOpenChunk(false);
		}if(dto.getActivityType() == 2){
			themeActivity.setIsReward(false);
			themeActivity.setIsOpenChunk(true);
		}if(dto.getActivityType() == 3){
			themeActivity.setIsReward(false);
			themeActivity.setIsOpenChunk(false);
		}
		
		
		this.themeActivityDao.save(themeActivity);
	}
	
	@Override
	public void updateThemeActivity(ThemeActivityDTO dto) {
		Assert.notNull(dto.getActivityName(), "活动名称不能为空");
		Assert.notNull(dto.getStartDate(), "开始时间不能为空");
		Assert.notNull(dto.getEndDate(), "结束时间不能为空");
		
		ThemeActivity themeActivity = this.themeActivityDao.getById(dto.getId());
		themeActivity.setActivityName(dto.getActivityName());
		themeActivity.setContent(dto.getContent());
		themeActivity.setStartDate(dto.getStartDate());
		themeActivity.setEndDate(dto.getEndDate());	
		themeActivity.setLastOperatorTime(new Date());
		themeActivity.setUpdateBy(getNowUser().getUsername());
	
		this.themeActivityDao.save(themeActivity);
	}
	
	
	
	@Override
	public void deleteThemeActivity(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			ThemeActivity themeActivity = this.themeActivityDao.getById(ids[i]);
			if (themeActivity!= null) {
				
				//1-月月精彩 
				if(themeActivity.getActivityType() == 1){
					//删除活动规则，奖项设定，评奖结果		
					this.awardWinningWorksDao.delAWWByActivityId(themeActivity.getId());					
					this.awardsSettingDao.delASByActivityId(themeActivity.getId());				
					this.activityRulesDao.delARByActivityId(themeActivity.getId());
					
					//先删除图片，再删除稿件
					String hql = "from Manuscript manuscript where manuscript.activity.id="+themeActivity.getId();
					List<Manuscript> manuscriptList = this.manuscriptDao.findList(hql);
					if(!manuscriptList.isEmpty()){
						for (Manuscript manuscript : manuscriptList) {						
							if(manuscript.getBaseFileId()!=null){
								this.baseFileService.deleteBaseFile(manuscript.getBaseFileId());
							}	
							this.manuscriptDao.delete(manuscript);
						}					
					}
					this.themeActivityDao.delete(themeActivity);
					
				}
				//2-城建人组稿	
				if(themeActivity.getActivityType() == 2){
					
					//删除内容					
					String hql = "from Content content where content.activity.id="+themeActivity.getId();
					List<Content> contentList = this.contentDao.findList(hql);
					if(!contentList.isEmpty()){
						for (Content content : contentList) {						
							content.setActivity(null);
							this.contentDao.save(content);
						}					
					}
					
					//删除稿件
					String hql2 = "from Manuscript manuscript where manuscript.activity.id="+themeActivity.getId();
					List<Manuscript> manuscriptList = this.manuscriptDao.findList(hql2);
					if(!manuscriptList.isEmpty()){
						for (Manuscript manuscript : manuscriptList) {						
							if(manuscript.getBaseFileId()!=null){
								this.baseFileService.deleteBaseFile(manuscript.getBaseFileId());
							}							
							this.manuscriptDao.delete(manuscript);
						}					
					}
					
					//删除板块
					String hql3 = "from BanChunk banChunk where banChunk.activity.id="+themeActivity.getId();
					List<BanChunk> banChunkList = this.banChunkDao.findList(hql3);
					if(!banChunkList.isEmpty()){
						for (BanChunk banChunk : banChunkList) {						
							banChunk.setParent(null);
							this.banChunkDao.save(banChunk);
							this.banChunkDao.delete(banChunk);
						}					
					}
					
					this.themeActivityDao.delete(themeActivity);					
				}
				 //3-征文	
				if(themeActivity.getActivityType() == 3){
				//先删除图片，再删除稿件
					String hql = "from Manuscript manuscript where manuscript.activity.id="+themeActivity.getId();
					List<Manuscript> manuscriptList = this.manuscriptDao.findList(hql);
					if(!manuscriptList.isEmpty()){
						for (Manuscript manuscript : manuscriptList) {						
							if(manuscript.getBaseFileId()!=null){
								this.baseFileService.deleteBaseFile(manuscript.getBaseFileId());
							}	
							this.manuscriptDao.delete(manuscript);
						}						
					}
					this.themeActivityDao.delete(themeActivity);					
				}				
			}
			if (i % 20 == 0) {
				this.themeActivityDao.flush();
				this.themeActivityDao.clear();
			}
		}
	}
	

	
//	
//	@Override
//	public PhotoActivity getPhotoActivityById(Long id) {
//		PhotoActivity result = new PhotoActivity();		
//		
//		ThemeActivityDTO activity = this.themeActivityDao.getActivePhotoActivity();
//		
//		
//		if (activity != null) {
//			result.setActivity(activity);
//			List<AwardsSettingDTO> awardsSettingList = this.awardsSettingDao.getAllAwardsSettingByActivityId(activity.getId());
//			
//			for (int i = 0; i < awardsSettingList.size(); i++) {
//				awardsSettingList.get(i).setPattern("/public/"+awardsSettingList.get(i).getPattern());
//			}
//			
//			List<ActivityRuleDTO> activityRuleList = this.activityRulesDao.getAllRulesByActivityId(activity.getId());
//			ThemeActivityDTO lastActivity = this.themeActivityDao.getLastPhotoActivity(activity.getId());	
//			if (activityRuleList.size() > 0) {
//				for (int i = 0; i < activityRuleList.size(); i++) {
//					activityRuleList.get(i).setNum(i + 1);
//				}
//			}
//			result.setLastActivity(lastActivity);
//			result.setAwardsSettingList(awardsSettingList);
//			result.setActivityRuleList(activityRuleList);
//			return result;
//		} else {
//			return null;
//		}
//	}

	/**
	 * 激活所选活动
	 */
	@Override
	public void activateThemeActivity(Long id) {
		ThemeActivity activity = this.themeActivityDao.getById(id);
		List<ThemeActivity> activitys = new ArrayList<ThemeActivity>();
		// 每一类
		
		for(int i=0;i<=3;i++){
		if (activity.getActivityType() == i) {
			activitys = this.themeActivityDao.searchByFild("activityType",activity.getActivityType());
			if (activitys.size() != 0) {
				for (ThemeActivity dt : activitys) {
					dt.setStatus((short) 0);
				}
			}
			activity.setStatus((short) 1);
		}
		}
	}







	@Override
	public List<ThemeActivityDTO> getThemeActivityByType(Short activityType) {
		List<ThemeActivity> dtlist=this.themeActivityDao.searchByFild("activityType", activityType);
		List<ThemeActivityDTO> dtolist=new ArrayList<ThemeActivityDTO>();
		for (int i = 0; i < dtlist.size(); i++) {
			ThemeActivityDTO dto = new ThemeActivityDTO();
			this.getMapper().map(dtlist.get(i), dto);
			dtolist.add(dto);
		}
		return dtolist;
	}





	

	@Override
	public ThemeActivityDTO getNowCityBuilderActivity() {
		List<ThemeActivityDTO> talist=this.getThemeActivityByType((short) 2);
		for(ThemeActivityDTO dto:talist){
			if(dto.getStatus()==1){
				return dto;
			}
		}
		return null;
	}


	@Override
	public PhotoActivity getNowPhotoActivity() {
		
		ThemeActivityDTO activity = this.themeActivityDao.getActivePhotoActivity();
		if(activity!=null){	
			PhotoActivity result = this.getRelevantMessage(activity.getId());
			result.setActivity(activity);
			return result;		
		}else{
			return null;
		}
	}


	@Override
	public PhotoActivity getLastPhotoActivity(Long nowId) {
		PhotoActivity result = new PhotoActivity();	
		ThemeActivityDTO activity = this.themeActivityDao.getLastPhotoActivity(nowId);
		if(activity!=null){			
			result = this.getRelevantMessage(activity.getId());
			result.setActivity(activity);
			return result;		
		}else{
			return null;
		}
	}


	@Override
	public PhotoActivity getRelevantMessage(Long activityId) {
		PhotoActivity result = new PhotoActivity();	
		//查出活动规则
		List<ActivityRuleDTO> ruleList = this.activityRulesDao.getAllRulesByActivityId(activityId);
		if (ruleList.size() > 0) {
			for (int i = 0; i < ruleList.size(); i++) {
				ruleList.get(i).setNum(i + 1);
			}
		}			
		//查出活动奖项
		List<AwardsSettingDTO> awardsList = this.awardsSettingDao.getAllAwardsSettingByActivityId(activityId);
		if(awardsList.size()>0){
			ZonePathDTO zp = this.zonePathService.getZonePathById(awardsList.get(0).getZonePathId());			
			for (int i = 0; i < awardsList.size(); i++) {
				awardsList.get(i).setPattern(zp.getVirtualPath()+awardsList.get(i).getPattern());
			}
		}
		//查出上次摄影活动信息
		ThemeActivityDTO lastActivity = this.themeActivityDao.getLastPhotoActivity(activityId);
		if(lastActivity!=null){
			
			
			
			//查出稿件
			List<PhotoDTO> manuscripts = this.manuscriptDao.getCheckedPhotosByActivityId(lastActivity.getId());
			if(manuscripts.size()>0){								
				Map<Long, String> cache = new HashMap<Long, String>();				
				for (PhotoDTO photoDTO : manuscripts) {					
					photoDTO.setPattern(this.zonePathService.viewCache(photoDTO.getZonePathId(),cache)+photoDTO.getPattern());
				}
				
				lastActivity.setManuscripts(manuscripts);				
			}			
		}
		
		
		//查出是否有获奖作品
		Integer isAward = this.awardWinningWorksDao.checkIsAward(activityId);
		
		result.setIsAwarded(isAward);
		result.setLastActivity(lastActivity);
		result.setActivityRuleList(ruleList);
		result.setAwardsSettingList(awardsList);
		return result;
	}







	@Override
	public Page<ThemeActivityDTO> searchThemeActivityByPgae2(Integer activityType,Integer curPage,
			Integer pageSize) {
		
		return this.themeActivityDao.searchThemeActivityByPgae2(activityType,curPage,pageSize);
	}







	@Override
	public Integer countListSize(Integer activityType, Integer status) {
		// TODO Auto-generated method stub
		return this.themeActivityDao.countListSize(activityType,status);
	}







	@Override
	public ThemeActivityDTO getBySql(Long id) {
		// TODO Auto-generated method stub
		return this.themeActivityDao.getBySql(id);
	}
	

}
	
