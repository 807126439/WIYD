package com.wb.web.portals.dto.communication;

import java.util.Date;
import java.util.List;

import com.wb.web.portals.dto.comment.CommentDTO;

public class FiledDTO {
	
	private Long id;
	private String title;       //议题名称
	private String content;     //议题简述	
	private String sponsor;     //发起人
	private Date startDate;     //开始时间
	private Integer totalPage;
	
	private CommunicationDTO pre;
	private CommunicationDTO next;
	private List<CommentDTO> commentList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public CommunicationDTO getPre() {
		return pre;
	}
	public void setPre(CommunicationDTO pre) {
		this.pre = pre;
	}
	public CommunicationDTO getNext() {
		return next;
	}
	public void setNext(CommunicationDTO next) {
		this.next = next;
	}
	public List<CommentDTO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
}
