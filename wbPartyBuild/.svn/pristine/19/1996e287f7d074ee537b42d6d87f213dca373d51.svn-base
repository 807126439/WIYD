package com.wb.web.portals.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.web.portals.dto.ads.AdvertisementDTO;
import com.wb.web.portals.dto.ads.AdvertisementQueryDTO;

public interface IAdvertisementService {
	
	public Page<AdvertisementDTO> searchAdvertisementByPage(AdvertisementQueryDTO queryDTO);
	
	public AdvertisementDTO getAdvertisementById(Long id);
	
	public void addAdvertisement(AdvertisementDTO dto,CommonsMultipartFile uploadFile,Integer isCompress);
	
	public void updateAdvertisement(AdvertisementDTO dto,CommonsMultipartFile uploadFile,Integer isCompress);
	
	public void deleteAdvertisement(Long[] ids);
	
	public Long countTotalNum();
	
	public List<Object> searchAdsForIndex();
}
