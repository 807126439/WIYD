package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.opinionfeedback.OpinionCountDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackDto;
import com.wb.web.portals.dto.opinionfeedback.OpinionFeedbackQueryDto;
import com.wb.web.portals.entity.OpinionFeedback;

public interface IOpinionFeedbackDao extends IBaseDao<Long, OpinionFeedback>{

	/***
	 * 按时间进行意见反馈的分页查询
	 */
	public Page<OpinionFeedbackDto> searchOpinionFeedbackByPage(OpinionFeedbackQueryDto queryDTO);
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
	
}
