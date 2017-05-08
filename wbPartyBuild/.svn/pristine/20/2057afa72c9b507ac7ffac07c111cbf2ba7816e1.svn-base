package com.wb.web.base.service;

import java.util.Set;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.base.dto.result.SaveResult;

public interface IBaseFileService {

	public SaveResult addBaseFile(CommonsMultipartFile uploadFile);
	
	public SaveResult addPublicBaseFile(CommonsMultipartFile uploadFile);
	
	public String getTempFileWholePath(String fileType);
	
	public void deleteBaseFile(Long id);
	
	public String getDownLoadFilePath(Long id);
	
	public String getFileViewPath(Long id);
	
	public String updateWaterMarkImg(Long srcId,Long wmId,Integer markType,Float alpha,Integer marginX, Integer marginY);

	public DownLoadDTO getDownLoadInfoById(Long baseFileId);
	
	public void saveByCache(String uuid,CommonsMultipartFile uploadFile);
	
	public Set<SaveResult> getTempCacheVal(String uuid);
	
	public void removeTempCacheObj(String uuid);
	
}
