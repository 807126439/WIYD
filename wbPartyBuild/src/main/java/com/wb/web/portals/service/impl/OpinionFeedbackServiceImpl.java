package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

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
import com.wb.web.portals.dao.IOpinionFeedbackDao;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.opinionfeedback.OpinionCountDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackQueryDto;
import com.wb.web.portals.entity.OpinionFeedback;
import com.wb.web.portals.service.IOpinionFeedbackService;
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IZonePathService;
@Service("OpinionFeedbackService")
@Transactional
public class OpinionFeedbackServiceImpl extends BaseService implements IOpinionFeedbackService {
	@Resource
	private IOpinionFeedbackDao iOpinionFeedbackDao;
	@Resource
	private IBaseDictDao baseDictDao;
	@Resource
	private IZonePathService zonePathService;
	
	public Page<OpinionFeedbackDto> searchOpinionFeedbackByPage(
			OpinionFeedbackQueryDto queryDTO) {
			Page<OpinionFeedbackDto> result = this.iOpinionFeedbackDao.searchOpinionFeedbackByPage(queryDTO);
			List<OpinionFeedbackDto> list = result.getList();
			List<BaseDict> dictList = this.baseDictDao.searcheDcitListByCondition(BaseDict.OPINION_FEEDBACK_TYPE, null, null);
			List<OpinionFeedbackDto> dtoList = new ArrayList<OpinionFeedbackDto>();
			
			for (int i = 0; i < list.size(); i++) {
				OpinionFeedbackDto dto = new OpinionFeedbackDto();
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
		
			return new Page<OpinionFeedbackDto>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}

	/***
	 * deleteOpinionFeedback方法
	 * 按照id删除意见反馈的记录
	 */								
	public void deleteOpinionFeedback(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			OpinionFeedback of = this.iOpinionFeedbackDao.getById(ids[i]);
			if(of!=null){
				this.iOpinionFeedbackDao.delete(of);
			}
			
			
		}
		
		
	}

	/***
	 * 添加反馈的意见
	 */
	public void saveOpinionFeedback(OpinionFeedbackDto dto,String ipAddress) {
		Assert.notNull(dto.getTypeId());
					
		

		if(dto.getTypeId() == OpinionFeedback.Reasonable_Advise_TYPE){
			OpinionFeedback op = new OpinionFeedback(
					             dto.getContent(), new Date(), 
					             ipAddress, 
					             getNowUser().getUsername(),
					             dto.getTypeId() , 
					             getNowUser().getChineseName(), 
					             dto.getDepartment(), 
					             dto.getPost(), 
					             dto.getTheme(), 
					             dto.getForecast());
			
			this.iOpinionFeedbackDao.save(op);
			
		}else if(dto.getTypeId() == OpinionFeedback.Wisdom_Advise_TYPE){
			OpinionFeedback op = new OpinionFeedback(
								 dto.getContent(), 
								 new Date(),
								 ipAddress,
								 getNowUser().getUsername(), 
								 dto.getTypeId() ,
								 dto.getTheme());
			
			this.iOpinionFeedbackDao.save(op);
		}
		
		
		
		
	}
	/***
	 * 按编号查询反馈信息
	 * @return
	 */
	@Override
	public OpinionFeedbackDto findById(Long id) {
		if(id == null){
			throw new NullPointerException("id must not be null");
		}
		return iOpinionFeedbackDao.findById(id);
	}

	@Override
	public Page<OpinionCountDto> opinionCount(OpinionFeedbackQueryDto queryDTO) {
		// TODO Auto-generated method stub
		
		Page<OpinionCountDto> page =this.iOpinionFeedbackDao.opinionCount(queryDTO);
		Long start=0L;
		for (OpinionCountDto entity : page.getList()) {
			entity.setSort((long)++start);
		}
		return page;
	}

	@Override
	public DownLoadDTO exportExcel(OpinionFeedbackQueryDto queryDTO) {
		 //查询导出数据
		queryDTO.setPageSize(null);
	   	 /*List<OpinionCountDto> opinionCountList = this.iOpinionFeedbackDao.countList();*/
		List<OpinionCountDto> opinionCountList = this.iOpinionFeedbackDao.opinionCount(queryDTO).getList();
	   	 
			 Assert.notEmpty(opinionCountList, "无导出的数据！");	
			 WrapExcelData wrapExcelData = null;	
	   	
			 String fileName = null;
			 TitleSet[] titleArray = null;   //表头
			 
	   		 fileName = "意见统计列表";
	   		 
	   		 
	   		 titleArray = new TitleSet[]{
	   				new TitleSet("序号", 20),new TitleSet("用户名", 20),new TitleSet("意见数量", 40),new TitleSet("最近一次意见反馈时间",20)
	   		 }; 
		 
	   		 wrapExcelData = new WrapExcelData<OpinionCountDto>() {
					
					public void wrapData(OpinionCountDto obj, HSSFRow row) {
						row.createCell(0).setCellValue(obj.getSort());
						row.createCell(1).setCellValue(obj.getFeedbackUser());
						row.createCell(2).setCellValue(obj.getNum());
						row.createCell(3).setCellValue(null != obj.getFeedbackTime() ? obj.getFeedbackTime().toString() : "");
					}
				};
	   		 
	   	
	   			
	   	 
	   	 String savePath = this.zonePathService.getTempFileWholePath("xls");
	   	 ExcelUtils<OpinionCountDto> excelUtils = new ExcelUtils<OpinionCountDto>(titleArray,savePath,opinionCountList,wrapExcelData);
	   	
	   	 if(excelUtils.generateExecl()){
	   		 
	   		 return new DownLoadDTO(fileName+".xls", savePath);
	   	 }else{
	   		 throw new MyException("导出失败！");
	   	 }
	}

}
