package com.wb.web.portals.dao;

import java.util.List;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.web.portals.dto.comment.CommentDTO;
import com.wb.web.portals.dto.comment.CommentQueryDTO;
import com.wb.web.portals.dto.comment.CountCommentDTO;
import com.wb.web.portals.entity.Comment;

public interface ICommentDao extends IBaseDao<Long, Comment>{
	
	
	public Page<CommentDTO> searchEntityByPage(CommentQueryDTO queryDTO);

	public Page<CommentDTO> getAllComment(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove);
	public Integer conutComment(Long comId,Integer type);
	public Integer countTotlaPage(Long comId);
	public List<CommentDTO> getAllChildComment(Long parentId);	
	public Integer countTotalSize(Long comId);			
	public Integer countChildComment(Long comId);	
	public List<CommentDTO> getCommentList(Long comId);
	public void deleteVote(Long commentId,String userId);
	
	
	public List<CountCommentDTO> getCountCommentList();
	public Page<CountCommentDTO> searchCountCommentByPage(CommentQueryDTO queryDTO);
	
	
}
