package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.base.dao.IBaseFileDao;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.entity.BaseFile;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dao.IAwardWinningWorksDao;
import com.wb.web.portals.dao.IAwardsSettingDao;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingQueryDTO;
import com.wb.web.portals.entity.AwardsSetting;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IAwardsSettingService;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.service.IZonePathService;


@Service("awardsSettingService")
@Transactional
public class AwardsSettingServiceImpl extends BaseService implements IAwardsSettingService{

	@Resource
	private IAwardsSettingDao awardsSettingDao;
	
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IBaseFileDao baseFileDao;
	@Resource
	private IZonePathService zonePathService;
	
	@Resource
	private IAwardWinningWorksDao awardWinningWorksDao;
	
	@Override
	public Page<AwardsSettingDTO> searchAwardsSettingByPage(
			AwardsSettingQueryDTO queryDTO) {
		Page<AwardsSetting> result = this.awardsSettingDao.searchEntityByPage(queryDTO);
		List<AwardsSetting> list = result.getList();
		
		
		List<AwardsSettingDTO> dtoList = new ArrayList<AwardsSettingDTO>();
		for (int i = 0; i < list.size(); i++) {
			AwardsSettingDTO dto = new AwardsSettingDTO();
			this.getMapper().map(list.get(i), dto);
			
			dtoList.add(dto);			
		}
		
		return  new Page<AwardsSettingDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}

	@Override
	public AwardsSettingDTO getAwardsSettingById(Long id) {
		AwardsSetting as = this.awardsSettingDao.getById(id);
		AwardsSettingDTO asDTO= new AwardsSettingDTO();
		if(as.getBaseFileId()!=null){			
			BaseFile file = this.baseFileDao.getById(as.getBaseFileId());
			asDTO.setPattern("/public/"+file.getPattern());
		}
		asDTO.setAmount(as.getAmount());
		asDTO.setAsId(as.getId());
		asDTO.setAwardsName(as.getAwardsName());		
		asDTO.setMemo(as.getMemo());
		asDTO.setPrize(as.getPrize());		
		return asDTO;
	}

	@Override
	public void addAwardsSetting(AwardsSettingDTO dto,CommonsMultipartFile file) {
		Assert.notNull(dto.getActivityId(), "主题活动不能为空");
		Assert.notNull(dto.getAwardsName(), "奖项名称不能为空");
		Assert.notNull(dto.getPrize(), "奖金/奖品不能为空");
		Assert.notNull(dto.getAmount(), "奖项数量不能为空");
		
		AwardsSetting aw = new AwardsSetting(
				new ThemeActivity(dto.getActivityId()),dto.getAwardsName(),dto.getPrize(),
				dto.getAmount(),dto.getMemo(),new Date(),getNowUser().getUsername(),dto.getSortNum());
		
		if(file!=null && !file.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(file);			
			aw.setBaseFileId(aResult.getId());						
		}
		
		this.awardsSettingDao.save(aw);		
	}
	
	
	
	@Override
	public void updateAwardsSetting(AwardsSettingDTO dto,CommonsMultipartFile file) {
		Assert.notNull(dto.getAsId(), "asId不能为空");
		Assert.notNull(dto.getActivityId(), "主题活动不能为空");
		Assert.notNull(dto.getAwardsName(), "奖项名称不能为空");
		Assert.notNull(dto.getPrize(), "奖金/奖品不能为空");
		Assert.notNull(dto.getAmount(), "奖项数量不能为空");
		
		
		AwardsSetting aw = this.awardsSettingDao.getById(dto.getAsId());
		aw.setAmount(dto.getAmount());
		aw.setAwardsName(dto.getAwardsName());
		aw.setPrize(dto.getPrize());
		aw.setMemo(dto.getMemo());
		aw.setUpdateBy(getNowUser().getUsername());
		aw.setLastOperatorTime(new Date());
		aw.setSortNum(dto.getSortNum());
		
		if(file!=null && !file.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(file);			
			aw.setBaseFileId(aResult.getId());						
			}
		this.awardsSettingDao.update(aw);		
	}
	
	

	@Override
	public void deleteAwardsSetting(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			AwardsSetting as = this.awardsSettingDao.getById(ids[i]);
			if(as!=null){
				this.awardsSettingDao.delete(as);
			}			
			if(i%20 ==0){
				this.awardsSettingDao.flush();
				this.awardsSettingDao.clear();
			}
		}
		
	}
/**
 * 分页之后显示找出缩略图的地址并显示
 */
	@Override
	public Page<AwardsSettingDTO> searchEntityByPage(
			AwardsSettingQueryDTO queryDTO) {
		
		Map<Long, String> cache = new HashMap<Long, String>();
		Page<AwardsSettingDTO> page = this.awardsSettingDao.searchEntityByPage2(queryDTO);
		for (AwardsSettingDTO dto : page.getList()) {
			if(dto.getZonePathId()!=null){
				String prefixPath = null;
				if(cache.containsKey(dto.getZonePathId())){
					 prefixPath = cache.get(dto.getZonePathId());
				}else{
					ZonePathDTO zp = this.zonePathService.getZonePathById(dto.getZonePathId());
					cache.put(dto.getZonePathId(), zp.getVirtualPath());
					prefixPath = zp.getVirtualPath();
				}
				
				dto.setPattern(prefixPath + dto.getPattern());
			}
		}
		
		return page;
	}

	@Override
	public List<AwardsSettingDTO> getAwardsSettingsByActivityId(Long id) {
		List<AwardsSettingDTO> aslist = awardsSettingDao.getAllAwardsSettingByActivityId(id);
		List<AwardsSettingDTO> aslist2=awardsSettingDao.delAssignedAwardsSettingByActivityId(aslist, id);
		return aslist2;
	}

	
	
	
	
}
