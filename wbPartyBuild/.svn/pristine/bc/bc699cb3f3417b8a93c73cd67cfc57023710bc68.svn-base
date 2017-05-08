package com.wb.web.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.utils.SizeCalculation;
import com.wb.web.system.dao.IZoneDao;
import com.wb.web.system.dto.zone.ZoneDTO;
import com.wb.web.system.dto.zone.ZoneQueryDTO;
import com.wb.web.system.entity.Zone;
import com.wb.web.system.service.IZoneService;

@Service("zoneService")
public class ZoneServiceImpl extends BaseService implements IZoneService{
	@Resource
	private IZoneDao zoneDao;
	
	
	
	public Page<ZoneDTO> searchZoneByPage(ZoneQueryDTO queryDTO){
		  Page<Zone> pageResult = new Page<Zone>();
			
			if(null!=queryDTO.getRules() && null!=queryDTO.getGroupOp() && queryDTO.getRules().size()>0
					&& !queryDTO.getGroupOp().isEmpty()){
				
				pageResult = this.zoneDao.searchEntityWithConditionForJqgrid(queryDTO);
				
			}else{
				pageResult = this.zoneDao.searchEntityByPage(queryDTO);
			}
			
			
			List<Zone> list = pageResult.getList();
			List<ZoneDTO> dtoList = new ArrayList<ZoneDTO>();
			for (int i = 0; i < list.size(); i++) {
				ZoneDTO dto = new ZoneDTO();
				this.getMapper().map(list.get(i),dto);
				File zone = new File(dto.getZonePath());
				if(zone.exists()){
					dto.setTotalCapacity(SizeCalculation.getDataSize(zone.getTotalSpace()));			
					dto.setFreeCapacity(SizeCalculation.getDataSize(zone.getFreeSpace()));
					
				}
				dtoList.add(dto);
			}
			
			return new Page<ZoneDTO>(pageResult.getCurrentPage(),pageResult.getPageSize(),dtoList,pageResult.getRecTotal());
	}
	
	
	/**
	 * 保存
	 * @param zoneDto
	 */
	public void saveZone(ZoneDTO zoneDto) {
		Zone zone = new Zone();
		this.getMapper().map(zoneDto, zone);
		this.zoneDao.save(zone);
		
	}

	
	/**
	 * 修改
	 * @param zoneDto
	 */
	public void updateZone(ZoneDTO zoneDto) {
		Zone zone = this.zoneDao.getById(zoneDto.getId());
		this.getMapper().map(zoneDto, zone);
		this.zoneDao.update(zone);
		
	}

	/**
	 * 删除
	 * @param zoneDto
	 */
	public void deleteZone(ZoneDTO zoneDto) {
		String[] delIds = zoneDto.getId().split(",");
		
		for (int i = 0; i < delIds.length; i++) {
			Zone zone  = this.zoneDao.getById(Long.parseLong(delIds[i]));
		
			this.zoneDao.delete(zone);
		}
	
	}
	
	
	public void monitorCapacity(){
		
		System.out.println("定时任务！..............");
	}
	
	
	
}
