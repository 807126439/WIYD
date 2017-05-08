package com.wb.web.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.FileOperation;
import com.wb.core.utils.PathHelper;
import com.wb.core.utils.SizeCalculation;
import com.wb.web.system.dao.IZoneDao;
import com.wb.web.system.dao.IZonePathDao;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.dto.zone.ZonePathQueryDTO;
import com.wb.web.system.entity.Zone;
import com.wb.web.system.entity.ZonePath;
import com.wb.web.system.service.IZonePathService;

@Service("zonePathService")
public class ZonePathServiceImpl extends BaseService implements IZonePathService{
	@Resource
	private IZonePathDao zonePathDao;
	@Resource
	private IZoneDao zoneDao;
	
	

	public ZonePathDTO getZonePathById(Long id){		
		ZonePath zp = this.zonePathDao.getById(id);
		ZonePathDTO dto = new ZonePathDTO();
		this.getMapper().map(zp, dto);
		
		return dto;
	}
	
	
	
	/**
	 * 获取可用的空间来保存
	 * @param type
	 * @return
	 */
	public ZonePathDTO getUseableZone(String type){
		List<ZonePath> zpList = this.zonePathDao.searchListByTypeWithMarkASC(type);
		if(!zpList.isEmpty() && zpList.size()>0){
			for (int i = 0; i < zpList.size(); i++) {
				 File zone = new File(zpList.get(i).getPath());
				 if(!zone.exists()){
					zone.mkdirs();
				 }
				 
				 Long freeSpace = zone.getFreeSpace();
				 if(freeSpace > zpList.get(i).getWarmValue()){
					 
					 return new ZonePathDTO(
							 zpList.get(i).getId(),
							 zpList.get(i).getZoneName(), 
							 zpList.get(i).getMark(), 
							 zpList.get(i).getPath(), 
							 zpList.get(i).getVirtualPath());
					
				 }
		
			}
			
			throw new MyException("upload path is error!");
		   
		}else{
			throw new MyException("upload path is error!");
		}
			
	}
	
	public ZonePath getUseableZone2(String type){
		List<ZonePath> zpList = this.zonePathDao.searchListByTypeWithMarkASC(type);
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
			
			throw new MyException("upload path is error!");
		   
		}else{
			throw new MyException("upload path is error!");
		}
			
	}
	
	
	/**
	 * 后台查询
	 * @param queryDTO
	 */
	public Page<ZonePathDTO> searchZonePathByPagging(ZonePathQueryDTO queryDTO) {
        Page<ZonePath> pageResult = new Page<ZonePath>();
		Zone zone = this.zoneDao.getById(queryDTO.getZoneId());//获取分区
		queryDTO.setZoneName(zone.getZoneName());//设置分区名查询
		
		queryDTO.wrapQueryVal();
		
		if(null!=queryDTO.getRules() && null!=queryDTO.getGroupOp() && queryDTO.getRules().size()>0
				&& !queryDTO.getGroupOp().isEmpty()){
			
			pageResult = this.zonePathDao.searchEntityWithConditionForJqgrid(queryDTO);
			
		}else{
			
			pageResult = this.zonePathDao.searchEntityByPage(queryDTO);
		}
		
		
		List<ZonePath> list = pageResult.getList();
		List<ZonePathDTO> dtoList = new ArrayList<ZonePathDTO>();
		for (int i = 0; i < list.size(); i++) {
			ZonePathDTO dto = new ZonePathDTO();
			this.getMapper().map(list.get(i),dto);
		    File folder = new File(dto.getPath());
			if(folder.exists()){
				Long sum = FileOperation.getFolderSize(folder);
				dto.setUseCapacity(SizeCalculation.getDataSize(sum));
			}
			dtoList.add(dto);
		}
		
		return new Page<ZonePathDTO>(pageResult.getCurrentPage(),pageResult.getPageSize(),dtoList,pageResult.getRecTotal());
	}

	

	/**
	 * 保存
	 * @param zonePathDto
	 * @param zonePathQueryDto
	 */
	public void saveZonePath(ZonePathDTO dto,ZonePathQueryDTO queryDTO) {
		Zone z = this.zoneDao.getById(queryDTO.getZoneId());
		ZonePath zp = new ZonePath();
		this.getMapper().map(dto, zp);
		if (zp.getPath() != null) {
			File dir = new File(zp.getPath());
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}

		zp.setZoneName(z.getZoneName());
		this.zonePathDao.save(zp);

	}

	
	/**
	 * 修改
	 * @param zonePathDto
	 */
	public void updateZonePath(ZonePathDTO dto) {
		ZonePath zp = this.zonePathDao.getById(dto.getId());
		if (zp != null) {
			dto.setZoneName(zp.getZoneName());
			this.getMapper().map(dto, zp);
			this.zonePathDao.update(zp);
		}
	}


	/**
	 * 删除
	 * @param zonePathDto
	 */
	public void deleteZonePath(ZonePathDTO dto) {
		ZonePath zp = this.zonePathDao.getById(dto.getId());
		if (zp != null) {
			this.zonePathDao.delete(zp);
		}

	}



	@Override
	public String getTempFileWholePath(String fileType) {
		ZonePath zp = this.getUseableZone2(ZonePath.TEMP_FILE);	
		
		if(zp == null){
			throw new MyException("Not create the temp zone");
		}
		
		StringBuilder result = new StringBuilder();
		result.append(zp.obtainDownLoadHead());
		result.append(PathHelper.makeNowDatePath()); //日期
		
		File directory = new File(result.toString());
		if(!directory.exists()){
			directory.mkdirs();
		}
		
		result.append(UUID.randomUUID().toString()); //uuid
		result.append(".");
		result.append(fileType);
		
		return result.toString();
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
	
}
