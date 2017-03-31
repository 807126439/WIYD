package com.wb.web.portals.service;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.web.portals.dto.comment.CommentDTO;
import com.wb.web.portals.dto.comment.CommentQueryDTO;
import com.wb.web.portals.dto.comment.CountCommentDTO;


public interface ICommentService {
	
	public Page<CommentDTO> searchCommentByPage(CommentQueryDTO queryDTO);
	
	public Page<CountCommentDTO> searchCountCommentByPage(CommentQueryDTO queryDTO);
	
	public CommentDTO getCommentById(Long id);
	
	public void addComment(CommentDTO dto);
	
	public void updateComment(CommentDTO dto);
	
	public void deleteComment(Long[] ids);
	
	public Page<CommentDTO> getAllComment(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove);	
	
	public Page<CommentDTO> getAllParentCommentApp(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove);	
	
	public Page<CommentDTO> getAllChildCommentApp(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove);	
	
	public Integer conutComment(Long comId,Integer type);
	
	public Integer countTotlaPage(Long comId);
	
	public Integer countTotalSize(Long comId);
	
	public List<CommentDTO> getAllChildComment(Long parentId);	
	
	public DownLoadDTO exoprtCountComment();
	
	
	
	
	
}

