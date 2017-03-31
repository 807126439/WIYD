package com.wb.web.portals.service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.portals.dto.opinionfeedback.OpinionCountDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackQueryDto;

public interface IOpinionFeedbackService {

	/***
	 * 按时间进行意见反馈的分页查询
	 */
	public Page<OpinionFeedbackDto> searchOpinionFeedbackByPage(OpinionFeedbackQueryDto queryDTO);
	/***
	 * 按照id删除意见反馈的记录
	 */
	public void deleteOpinionFeedback(Long[] ids);
	/***
	 * 添加意见反馈
	 */
	public void saveOpinionFeedback(OpinionFeedbackDto dto,String ipAddress);
	/***
	 * 按编号查询反馈信息
	 * @return
	 */
	public OpinionFeedbackDto findById(Long id);

	/**
	 * 统计
	 * @param queryDTO
	 * @return
	 */
	public Page<OpinionCountDto> opinionCount(OpinionFeedbackQueryDto queryDTO);
	
	/**
	 * 导出
	 * @param queryDTO
	 * @return
	 */
	public DownLoadDTO exportExcel(OpinionFeedbackQueryDto queryDTO);
}
