package com.wb.web.portals.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.utils.excel.ExcelUtils;
import com.wb.core.utils.excel.WrapExcelData;
import com.wb.core.utils.excel.ExcelUtils.TitleSet;
import com.wb.web.portals.dao.IFeelingRecordDao;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingCountDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.entity.FeelingRecord;
import com.wb.web.portals.service.IFeelingRecordService;
import com.wb.web.system.service.IZonePathService;

@Service("feelingRecordService")
@Transactional
public class FeelingRecordServiceImpl extends BaseService implements IFeelingRecordService {

	
	@Resource 
	private IFeelingRecordDao feelingRecordDao;
	
	@Resource
	private IZonePathService zonePathService;
	/**
	 * 分页查询心得体会
	 * @param queryDTO
	 * @return
	 */
	@Override
	public Page<FeelingRecordDTO> searchFeelingRecordByPage(FeelingRecordQueryDTO queryDTO) {
		Page<FeelingRecordDTO> page=this.feelingRecordDao.searchFeelingRecordByPage(queryDTO);
		Long start=0L;
		for (FeelingRecordDTO entity : page.getList()) {
			entity.setSort((long)++start);
		}
		return page;
	}
	/**
	 * 删除心得体会
	 * @param queryDTO
	 * @return
	 */
	@Override
	public void deleteFeelingRecord(String[] ids) {
		if (ids!=null) {
			for (int i = 0; i < ids.length; i++) {
				FeelingRecord feelingRecord = this.feelingRecordDao.getById(ids[i]);
				if (feelingRecord!= null) {				
					this.feelingRecordDao.delete(feelingRecord);
				}
				if (i % 20 == 0) {
					this.feelingRecordDao.flush();
					this.feelingRecordDao.clear();
				}
			}
		}
		
		
	}
	/**
	 * 添加心得体会
	 * @param dto
	 */
	@Override
	public AjaxJson addFeelingRecord(FeelingRecordDTO dto) {
		Assert.notNull(dto.getTitle(), "标题不能为空！");
		if (!StringUtils.isBlank(dto.getContent())) {
			FeelingRecord feelingRecord = new FeelingRecord(
					this.getNowUserId(),
					dto.getTitle(),
					dto.getContent(),
					new Date()
					);
			this.feelingRecordDao.save(feelingRecord);
			return new AjaxJson("添加成功！",AjaxJson.success);
		}else{
			return new AjaxJson("内容不能为空！",AjaxJson.error);
		}
		

		
		
	}
	/**
	 * 编辑心得体会
	 * @param dto
	 */
	@Override
	public AjaxJson editFeelingRecord(FeelingRecordDTO dto) {
		Assert.notNull(dto.getId(), "id不能为空");
		Assert.notNull(dto.getTitle(), "标题不能为空");
		if (!StringUtils.isBlank(dto.getContent())) {
			FeelingRecord feelingRecord = this.feelingRecordDao.getById(dto.getId());
			feelingRecord.setTitle(dto.getTitle());
			feelingRecord.setContent(dto.getContent());
			feelingRecord.setLastOperatorTime(new Date());
			this.feelingRecordDao.update(feelingRecord);
			return new AjaxJson("编辑成功！",AjaxJson.success);
		}else{
			return new AjaxJson("内容不能为空！",AjaxJson.error);
		}
		

	}
	/**
	 * 根据编号查询心得体会
	 * @param id
	 * @return
	 */
	@Override
	public FeelingRecord getFeelingRecordById(String id) {
		if (id!=null) {
			return this.feelingRecordDao.getById(id);
		}else {
			return null;
		}
		
	}
	
	/**
	 * 统计
	 */
	@Override
	public Page<FeelingRecordDTO> getByCount(FeelingRecordQueryDTO queryDTO) {
		
		Page<FeelingRecordDTO> page = this.feelingRecordDao.getByCount(queryDTO);
		
		Long start=0L;
		for (FeelingRecordDTO entity : page.getList()) {
			entity.setSort((long)++start);
		}
		
		
		return page;
	}
	
	/**
	 * 导出
	 */
	@Override
	public DownLoadDTO exportExcel(FeelingRecordQueryDTO queryDTO) {
		 //查询导出数据
		queryDTO.setPageSize(null);
	   	 List<FeelingRecordDTO> feelingCountList = this.feelingRecordDao.getByCount(queryDTO).getList();
	 	/*for(FeelingRecordDTO o:feelingCountList){
	 		o.setSort(0l);
	 	}*/
			 Assert.notEmpty(feelingCountList, "无导出的数据！");	
			 WrapExcelData wrapExcelData = null;	
	   	
			 String fileName = null;
			 TitleSet[] titleArray = null;   //表头
			 
			 Date dt=new Date();
		     SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
			 
	   		 fileName = "心得体会统计列表"+"(截至"+sdf.format(dt)+")";
	   		 
	   		 
	   		 titleArray = new TitleSet[]{
	   				 new TitleSet("序号", 20),new TitleSet("用户名", 40),new TitleSet("心得体会数量", 20),new TitleSet("最近一次心得体会时间",20)
	   		 }; 
		 
	   		 wrapExcelData = new WrapExcelData<FeelingRecordDTO>() {
					
					public void wrapData(FeelingRecordDTO obj, HSSFRow row) {
						row.createCell(0).setCellValue(obj.getSort());
						row.createCell(1).setCellValue(obj.getName());
						row.createCell(2).setCellValue(obj.getNumber());
						row.createCell(3).setCellValue(null != obj.getCreateTime() ? obj.getCreateTime().toString() : "");
					}
				};
	   		 
	   	
	   			
	   	 
	   	 String savePath = this.zonePathService.getTempFileWholePath("xls");
	   	 ExcelUtils<FeelingRecordDTO> excelUtils = new ExcelUtils<FeelingRecordDTO>(titleArray,savePath,feelingCountList,wrapExcelData);
	   	
	   	 if(excelUtils.generateExecl()){
	   		 
	   		 return new DownLoadDTO(fileName+".xls", savePath);
	   	 }else{
	   		 throw new MyException("导出失败！");
	   	 }
	}

}
