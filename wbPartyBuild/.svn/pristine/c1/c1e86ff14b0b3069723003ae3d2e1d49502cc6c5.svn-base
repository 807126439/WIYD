package com.wb.web.portals.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.excel.ExcelUtils;
import com.wb.core.utils.excel.ExcelUtils.TitleSet;
import com.wb.core.utils.excel.WrapExcelData;
import com.wb.web.portals.dao.IContentStatDao;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.contentStat.ContentStatQueryDTO;
import com.wb.web.portals.entity.ContentStat;
import com.wb.web.portals.service.IContentStatService;
import com.wb.web.system.service.IZonePathService;

@Service("contentStatService")
@Transactional
public class ContentStatServiceImpl extends BaseService implements IContentStatService{
	@Resource
	private IContentStatDao contentStatDao;
	@Resource
	private IZonePathService zonePathService;
	
	@Override
	public Page<ContentStatDTO> searchContentStatByPage(ContentStatQueryDTO queryDTO) {
		Page<ContentStatDTO> result = this.contentStatDao.searchContentStatByPage(queryDTO);
		return result;
	}
	/**
	 * 添加关联
	 * @param userId 用户ID
	 * @param ContentId	文章ID
	 */
	@Override
	public void addRecord(String userId, Long contentId) {
		
		ContentStat dt=this.contentStatDao.getByUserIdAndContentId(userId,contentId);
		if(null!=dt){
			dt.setReadTimes(dt.getReadTimes()+1);
			this.contentStatDao.update(dt);
		}else{
			dt=new ContentStat();
			dt.setUserId(userId);
			dt.setContentId(contentId);
			dt.setReadDate(new Date());
			dt.setReadTimes(1);
			this.contentStatDao.save(dt);
		}
		
	}
	@Override
	public DownLoadDTO exportExcel(ContentStatQueryDTO queryDTO) {
	   	 //查询导出数据
		queryDTO.setPageSize(null);
	   	 List<ContentStatDTO> contentStatlist = this.contentStatDao.searchContentStatByPage(queryDTO).getList();
	   	 
			 Assert.notEmpty(contentStatlist, "无导出的数据！");	
			 WrapExcelData wrapExcelData = null;	
	   	
			 String fileName = null;
			 TitleSet[] titleArray = null;   //表头
			 
	   		 fileName = "文章阅读数量统计列表";
	   		 if(null!=queryDTO.getStartTime()){
	   			fileName+=queryDTO.getStartTime();
	   		 };
	   		 if(StringUtils.isEmpty(queryDTO.getEndTime())){
	   			fileName+="至今";
	   		 }else if(StringUtils.isEmpty(queryDTO.getStartTime())){
	   			fileName+=(queryDTO.getEndTime()+"以前");
	   		 }else{
	   			fileName+=("至"+queryDTO.getEndTime());
	   		 };
	   		 
	   		 titleArray = new TitleSet[]{
	   				 new TitleSet("序号", 20),new TitleSet("用户名", 40),new TitleSet("阅读文章量", 20)
	   		 }; 
		 
	   		 wrapExcelData = new WrapExcelData<ContentStatDTO>() {
					
					public void wrapData(ContentStatDTO obj, HSSFRow row) {
						row.createCell(0).setCellValue(obj.getSort());
						row.createCell(1).setCellValue(obj.getUserName());
						row.createCell(2).setCellValue(obj.getArticleNum());
					}
				};
	   		 
	   	
	   			
	   	 
	   	 String savePath = this.zonePathService.getTempFileWholePath("xls");
	   	 ExcelUtils<ContentStatDTO> excelUtils = new ExcelUtils<ContentStatDTO>(titleArray,savePath,contentStatlist,wrapExcelData);
	   	
	   	 if(excelUtils.generateExecl()){
	   		 
	   		 return new DownLoadDTO(fileName+".xls", savePath);
	   	 }else{
	   		 throw new MyException("导出失败！");
	   	 }
	}

}
