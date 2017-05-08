package com.wb.web.portals.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingCountDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.entity.FeelingRecord;

public interface IFeelingRecordService {
	
	/**
	 * 分页查询心得体会
	 * @param queryDTO
	 * @return
	 */
	public Page<FeelingRecordDTO> searchFeelingRecordByPage(FeelingRecordQueryDTO queryDTO);
	/**
	 * 删除心得体会
	 * @param ids
	 */
	public void deleteFeelingRecord(String[] ids);
	/**
	 * 添加心得体会
	 * @param dto
	 */
	public AjaxJson addFeelingRecord(FeelingRecordDTO dto);
	/**
	 * 编辑心得体会
	 * @param dto
	 */
	public AjaxJson editFeelingRecord(FeelingRecordDTO dto);
	/**
	 * 根据编号查询心得体会
	 * @param id
	 * @return
	 */
	public FeelingRecord getFeelingRecordById(String id);
	
	/**
	 * 心得体会统计
	 * @param queryDTO
	 * @return
	 */
	public Page<FeelingRecordDTO> getByCount(FeelingRecordQueryDTO queryDTO);
	
	/**
	 * 导出
	 * @param queryDTO
	 * @return
	 */
	public DownLoadDTO exportExcel(FeelingRecordQueryDTO queryDTO);
}
