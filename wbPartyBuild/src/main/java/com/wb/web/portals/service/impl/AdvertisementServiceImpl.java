package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.base.dao.IBaseFileDao;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dao.IAdvertisementDao;
import com.wb.web.portals.dto.ads.AdvertisementDTO;
import com.wb.web.portals.dto.ads.AdvertisementQueryDTO;
import com.wb.web.portals.dto.ads.IndexAdsDTO;
import com.wb.web.portals.entity.Advertisement;
import com.wb.web.portals.service.IAdvertisementService;
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IZonePathService;

@Service("advertisementService")
@Transactional
public class AdvertisementServiceImpl extends BaseService implements IAdvertisementService{
	@Resource
	private IAdvertisementDao advertisementDao;
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IBaseFileDao baseFileDao;
	@Resource
	private IBaseDictDao baseDictDao;
	@Resource
	private IZonePathService zonePathService;
	
	
	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	public Page<AdvertisementDTO> searchAdvertisementByPage(AdvertisementQueryDTO queryDTO) {
		
	
		Page<Advertisement> result = this.advertisementDao.searchEntityByPage(queryDTO);
		List<Advertisement> list = result.getList();

		List<BaseDict> dictList = this.baseDictDao.searcheDcitListByCondition(BaseDict.ADS_TYPE, null, null);
		
		List<AdvertisementDTO> dtoList = new ArrayList<AdvertisementDTO>();
		for (int i = 0; i < list.size(); i++) {
			AdvertisementDTO dto = new AdvertisementDTO();
			this.getMapper().map(list.get(i), dto);	
			
			if(dto.getTypeId()!=null){
				
				for (BaseDict dict : dictList) {
					if(dict.getDictValue().equals(dto.getTypeId().toString())){
						dto.setTypeName(dict.getDictItem());
						break;
					}
				}
			}
			
			
			dtoList.add(dto);
		}
		
		return new Page<AdvertisementDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}
	
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public AdvertisementDTO getAdvertisementById(Long id){
		Assert.notNull(id, "id must not be null");
		
		Advertisement ad = this.advertisementDao.getById(id);
		AdvertisementDTO dto = new AdvertisementDTO();
		this.getMapper().map(ad, dto);		
		
		return dto;
	}
	
	
	/**
	 * 添加广告
	 * @param dto
	 * @param uploadFile
	 */
	public void addAdvertisement(AdvertisementDTO dto,CommonsMultipartFile uploadFile,Integer isCompress){
		Assert.notNull(dto.getSortNum(), "序号不能为空!");
		Assert.notNull(dto.getTypeId(), "广告类型不能为空!");
		
		Advertisement ad = new Advertisement(
							dto.getTitle(), 
							dto.getSortNum(), 
							StringUtils.isBlank(dto.getLinkUrl())? null:dto.getLinkUrl(),
							dto.getCreateTime(),
							dto.getTypeId());
		
		if(uploadFile!=null && !uploadFile.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
			
			if(isCompress!=null && isCompress==1){
				ad.setPattern(aResult.getBigPattern());
			}else{
				ad.setPattern(aResult.getSrcPath());
			}
			
			ad.setBaseFileId(aResult.getId());						
		}
		
		this.advertisementDao.save(ad);
	}
	
	/**
	 * 修改广告信息
	 * @param dto
	 * @param uploadFile
	 */
	public void updateAdvertisement(AdvertisementDTO dto,CommonsMultipartFile uploadFile,Integer isCompress){
		Assert.notNull(dto.getAdsId(), "id must not be null");
		Assert.notNull(dto.getSortNum(), "序号不能为空!");
		Assert.notNull(dto.getTypeId(), "广告类型不能为空!");
		
		Advertisement ad = this.advertisementDao.getById(dto.getAdsId());
		ad.setTitle(dto.getTitle());
		ad.setSortNum(dto.getSortNum());
		ad.setLinkUrl(StringUtils.isBlank(dto.getLinkUrl())? null:dto.getLinkUrl());
		ad.setCreateTime(dto.getCreateTime());
		ad.setTypeId(dto.getTypeId());
							
		
		if(uploadFile!=null && !uploadFile.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
			if(isCompress!=null && isCompress==1){
				ad.setPattern(aResult.getBigPattern());
			}else{
				ad.setPattern(aResult.getSrcPath());
			}
			
			ad.setBaseFileId(aResult.getId());						
		}
		
		this.advertisementDao.update(ad);
	}
	
	
	/**
	 * 删除广告
	 * @param ids
	 */
	public void deleteAdvertisement(Long[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			Advertisement ad = this.advertisementDao.getById(ids[i]);
			if(ad!=null){
				this.advertisementDao.delete(ad);
				if(ad.getBaseFileId()!=null){
					this.baseFileService.deleteBaseFile(ad.getBaseFileId());
				}
			}
			
			if(i%20 == 0){
				this.advertisementDao.flush();
				this.advertisementDao.clear();
			}
		}
	}
	
	
	public Long countTotalNum(){
		return this.advertisementDao.countTotalNum();
	}
	
	
	
	/**
	 * 组装首页所有的广告信息
	 * @return
	 */
	public List<Object> searchAdsForIndex(){
		List<IndexAdsDTO> adsList = this.advertisementDao.searchIndexAdsList();
							
		List<Object> resultList = new ArrayList<Object>();
		List<IndexAdsDTO> lunboList = new ArrayList<IndexAdsDTO>();
		List<IndexAdsDTO> friendLinkList = new ArrayList<IndexAdsDTO>();
		
		for (IndexAdsDTO ad : adsList) {
			
			if(ad.getTypeId() == Advertisement.TYPE_LUNBO){								
			
				lunboList.add(ad);								
			}	
			else{
				resultList.add(ad);
			}			
		}
		
		if(resultList.size()>0){	
			resultList.add(1, lunboList);//把轮播图片置于集合第二个位置
		}
		return resultList;	
	}
	

}
