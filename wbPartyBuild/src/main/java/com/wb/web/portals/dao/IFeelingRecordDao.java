package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.entity.FeelingRecord;

public interface IFeelingRecordDao extends IBaseDao<Long,FeelingRecord>{
	
	/**
	 * 分页查询心得体会列表
	 * @param queryDTO
	 * @return
	 */
	public Page<FeelingRecordDTO> searchFeelingRecordByPage(FeelingRecordQueryDTO queryDTO);
	
	/**
	 * 用于导出
	 * @param name
	 * @return
	 */
	public Page<FeelingRecordDTO> getByCount(FeelingRecordQueryDTO queryDTO);
	
	
}
