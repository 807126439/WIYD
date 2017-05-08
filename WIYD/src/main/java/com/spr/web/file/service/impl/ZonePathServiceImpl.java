package com.spr.web.file.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.exception.BusinessException;
import com.spr.core.common.service.BaseService;
import com.spr.core.common.utils.Assert;
import com.spr.web.file.dao.IZonePathDao;
import com.spr.web.file.dto.zone.ZonePathDTO;
import com.spr.web.file.entity.ZonePath;
import com.spr.web.file.service.IZonePathService;
import com.spr.web.file.util.PathGenerator;

@Service("zonePathService")
@Transactional
public class ZonePathServiceImpl extends BaseService implements IZonePathService{
	
	@Resource
	private IZonePathDao zonePathDao;

	
	
	/**
	 * 获取可用的空间路径来进行保存
	 * @param type
	 * @return
	 */
	public ZonePath getUseableZone(String type){
		List<ZonePath> zpList = this.zonePathDao.selectListByType(type);
		if(!zpList.isEmpty()){
			
			for (ZonePath zp : zpList) {
				 File zone = new File(zp.getPath());
				 if(!zone.exists()){
					zone.mkdirs();
				 }
				 
				 if(zp.getWarmValue()!=null){
					 Long freeSpace = zone.getFreeSpace();
					 if(freeSpace > zp.getWarmValue()){
						 return zp;						
					 }
					 
				 }else{
					 return zp;
				 }
				
		
			}
			
			throw new BusinessException("upload path is error!");
		   
		}else{
			throw new BusinessException("upload path is error!");
		}
			
	}
	
	 
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	public Page<ZonePathDTO> searchZonePathByPage(DataQuery dq) {
		
				
		Long recTotal = this.zonePathDao.countByCondition(dq.assemblePageOffset().getQueryMap());
		dq.assembleOrderInfo(ZonePath.class, null);
		List<ZonePath> list = this.zonePathDao.selectListByCondition(dq.getQueryMap());
		
		List<ZonePathDTO> dtoList = new ArrayList<ZonePathDTO>();
		for (int i = 0; i < list.size(); i++) {
			ZonePathDTO dto = new ZonePathDTO();
			this.getMapper().map(list.get(i), dto);
			
			File dir = new File(dto.getPath());
			if(!dir.isDirectory()){
				dto.setIsValid(false);
			}
			
			
			dtoList.add(dto);
		}
		
		return new Page<ZonePathDTO>(dq.getCurrentPage(), dq.getPageSize(), dtoList, recTotal);
	}
    

    
    
    /**
     * 通过id查找对象
     * @param id
     * @return
     */
    public ZonePath getZonePathById(Long id){
    	Assert.notNull(id, "id must not be null");
    	
    	ZonePath zp = this.zonePathDao.getById(id);

    	
    	return zp;
    }
    
    
    /**
     * 保存对象
     * @param dto
     */
    public void addZonePath(ZonePathDTO dto){
    	Assert.hasText(dto.getText(), "路径描述不能为空！");
    	Assert.hasText(dto.getPath(), "路径地址不能为空！");
    	Assert.hasText(dto.getType(), "路径类别不能为空！");
    	Assert.notNull(dto.getPriority(), "优先级不能为空！");
    	
    	ZonePath zp  = new ZonePath(
    				  dto.getText(), dto.getPriority(),
    				  dto.getPath(), dto.getType(), 
    				  dto.getViewPath(),
    				  dto.getVirtualPath(), 
    				  dto.getWarmValue(), 
    				  dto.getPath().substring(0, 1));
    			
    			
    	this.zonePathDao.insert(zp);
    }
    
    /**
     * 修改对象
     * @param dto
     */
    public void updateZonePath(ZonePathDTO dto){
    	Assert.notNull(dto.getId(), "id must not be null");
    	Assert.hasText(dto.getText(), "路径描述不能为空！");
    	Assert.hasText(dto.getPath(), "路径地址不能为空！");
    	Assert.hasText(dto.getType(), "路径类别不能为空！");
    	Assert.notNull(dto.getPriority(), "优先级不能为空！");
    	
    	ZonePath zp = this.zonePathDao.getById(dto.getId());
    	zp.setText(dto.getText());
    	zp.setPath(dto.getPath());
    	zp.setType(dto.getType());
    	zp.setPriority(dto.getPriority());
    	zp.setViewPath(dto.getViewPath());
    	zp.setVirtualPath(dto.getVirtualPath());
    	zp.setWarmValue(dto.getWarmValue());
    	zp.setZoneName(dto.getPath().substring(0, 1));
    	zp.setGmtModified(new Date());
    	
    	
    	this.zonePathDao.update(zp);
    }
    
    
    /**
     * 删除对象
     * @param ids
     */
    public void deleteZonePath(Long[] ids){
    	for (int i = 0; i < ids.length; i++) {
    		
    		
			this.zonePathDao.deleteById(ids[i]);
		}
    }
    
    
    /**
     * 查找对象->创建目录
     * @param ids
     */
    public void createDirectory(Long[] ids){
    	
    	for (int i = 0; i < ids.length; i++) {
    		ZonePath zp = this.zonePathDao.getById(ids[i]);
    		if(zp!=null){
    			File dir = new File(zp.getPath());
    			if(!dir.exists()){
    				dir.mkdirs();
    			}
    			
    			if(!StringUtils.isBlank(zp.getVirtualPath())){
    				File virtualDir = new File(zp.getVirtualPath());
        			if(!virtualDir.exists()){
        				virtualDir.mkdirs();
        			}
    			}
    			
    			
    		}   		
	
		}
    	
    }
	
    
    
    /**
     * 路径缓存
     * @param zpId  路径id
     * @param cache 缓存容器
     * @return
     */
    public String viewCache(Long zpId,Map<Long, String> cache){
    	Assert.isTrue(cache!=null);
    	
    	String viewPath = null;
    	if(cache.containsKey(zpId)){
    		viewPath = cache.get(zpId);
    	}else{
    		ZonePath zp = this.zonePathDao.getById(zpId);
    		cache.put(zpId, zp.obtainViewHead());   
    		viewPath = zp.obtainViewHead();
    	}
    	
    	return viewPath;
    }
    
    
    
    /**
	 * 获取临时文件保存路径
	 * @param fileType  文件类型
	 * @return 文件保存路径
	 */
	public String getTempFileWholePath(String fileType){
		ZonePath zp = this.getUseableZone(ZonePath.TEMP_FILE);	
		
		if(zp == null){
			throw new BusinessException("Not create the temp zone");
		}
		
		StringBuilder result = new StringBuilder();
		result.append(zp.obtainDownLoadPath());
		result.append(PathGenerator.makeNowDatePath()); //日期
		
		
		File directory = new File(result.toString());
		if(!directory.exists()){
			directory.mkdirs();
		}
		
		result.append(UUID.randomUUID().toString()); //uuid
		result.append(".");
		result.append(fileType);
		
		return result.toString();
	}
    
}
