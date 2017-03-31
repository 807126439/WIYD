package com.wb.web.portals.service.impl;

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
import com.wb.core.utils.excel.WrapExcelData;
import com.wb.core.utils.excel.ExcelUtils.TitleSet;
import com.wb.web.portals.dao.IGeneralStatDao;
import com.wb.web.portals.dto.contentStat.ContentStatDTO;
import com.wb.web.portals.dto.generalStat.GeneralStatDTO;
import com.wb.web.portals.dto.generalStat.GeneralStatQueryDTO;
import com.wb.web.portals.service.IGeneralStatService;
import com.wb.web.system.service.IZonePathService;

@Service("generalStatService")
@Transactional
public class GeneralStatServiceImpl extends BaseService implements IGeneralStatService{
	@Resource
	private IGeneralStatDao GeneralStatDao;
	@Resource
	private IZonePathService zonePathService;

	@Override
	public Page<GeneralStatDTO> searchStatByPage(GeneralStatQueryDTO queryDTO) {
		Page<GeneralStatDTO> result = this.GeneralStatDao.searchStatByPage(queryDTO);
		return result;
	}

	@Override
	public DownLoadDTO exportExcel(GeneralStatQueryDTO queryDTO) {
	   	 //查询导出数据
		queryDTO.setPageSize(null);
	   	 List<GeneralStatDTO> contentStatlist = this.GeneralStatDao.searchStatByPage(queryDTO).getList();
	   	 
			 Assert.notEmpty(contentStatlist, "无导出的数据！");	
			 WrapExcelData wrapExcelData = null;	
	   	
			 String fileName = null;
			 TitleSet[] titleArray = null;   //表头
			 
	   		 fileName = "汇总统计列表";
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
	   				 new TitleSet("序号", 20),new TitleSet("用户名", 30),new TitleSet("登陆次数", 20),
	   				new TitleSet("评论次数", 20),new TitleSet("文章浏览次数", 20),new TitleSet("测试次数", 20),
	   				new TitleSet("发表合理化建议征集次数", 20),new TitleSet("发表智慧党建板块意见次数", 20),new TitleSet("发表心得体会次数", 20)
	   		 }; 
		 
	   		 wrapExcelData = new WrapExcelData<GeneralStatDTO>() {
					
					public void wrapData(GeneralStatDTO obj, HSSFRow row) {
						row.createCell(0).setCellValue(obj.getSort());
						row.createCell(1).setCellValue(obj.getUserName());
						row.createCell(2).setCellValue(obj.getLoginNum()==null?0:obj.getLoginNum());
						row.createCell(3).setCellValue(obj.getCommunicationNum()==null?0:obj.getCommunicationNum());
						row.createCell(4).setCellValue(obj.getArticleNum()==null?0:obj.getArticleNum());
						row.createCell(5).setCellValue(obj.getExamNum()==null?0:obj.getExamNum());
						row.createCell(6).setCellValue(obj.getT1feedbackNum()==null?0:obj.getT1feedbackNum());
						row.createCell(6).setCellValue(obj.getT2feedbackNum()==null?0:obj.getT2feedbackNum());
						row.createCell(6).setCellValue(obj.getFeelingNum()==null?0:obj.getFeelingNum());
					}
				};
	   		 
	   	
	   			
	   	 
	   	 String savePath = this.zonePathService.getTempFileWholePath("xls");
	   	 ExcelUtils<GeneralStatDTO> excelUtils = new ExcelUtils<GeneralStatDTO>(titleArray,savePath,contentStatlist,wrapExcelData);
	   	
	   	 if(excelUtils.generateExecl()){
	   		 
	   		 return new DownLoadDTO(fileName+".xls", savePath);
	   	 }else{
	   		 throw new MyException("导出失败！");
	   	 }
	}
	

}
