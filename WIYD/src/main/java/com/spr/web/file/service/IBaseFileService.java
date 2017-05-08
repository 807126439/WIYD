package com.spr.web.file.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spr.core.common.dto.DownLoadDTO;
import com.spr.web.file.dto.baseFile.PreviewImageDTO;
import com.spr.web.file.dto.baseFile.SaveResult;
import com.spr.web.file.entity.ZonePath;

public interface IBaseFileService {
	

	public SaveResult addBaseFile(CommonsMultipartFile uploadFile,String zoneType);
	
	public SaveResult addEmptyBaseFile(String zoneType,String fileName,String contentType);
	
	public void deleteBaseFile(Object array);
	
	public void realDeteleFile(Long id);
	
	public String getDownLoadFilePath(Long id);
	
	public String getBigPatternViewPath(Long id);
	
	public String getFileViewPath(Long id);
	
	public DownLoadDTO getDownLoadInfoById(Long baseFileId);
	
	public String saveByCache(String uuid,String type,CommonsMultipartFile uploadFile);
	
	public void saveByCache(String uuid,String type,Long baseFileId);
	
	public Map<String, SaveResult> getTempCacheVal(String uuid);
	
	public void removeTempCacheObj(String uuid);
	
	public String getVirtualSavePath(ZonePath zp,String fileType);
	
	public void saveTransformImgFile(Long mainId,Long zonePathId,String fileName,String savePath,Long length,String fileType,String userName);

	public List<PreviewImageDTO> viewFileByImgs(Long baseFileId);

	public List<PreviewImageDTO> getPreviewImageByBaseFileId(Long baseFileId);
	
	public DownLoadDTO getViewFileInfo(Long baseFileId);
	
	public void updateConvertImgsById(Long baseFileId,String userName);
	
	public void deleteInnerCache(String uuid,String key);
	
	public void monitorCache();
		
	
}
