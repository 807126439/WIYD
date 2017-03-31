package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.web.system.entity.User;

/**
 * @author wb_java_lzx
 * 评论
 */


@Entity
@Table(name="cy_comment")
public class Comment extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5253841771541929002L;
	
	
	
	private Communication communication;	   //互动议题
	private User user;						   //评论人	
	private String content;     			   //回复内容
	private Date commentTime;                  //评论时间
	private User target;					   //评论目标
	private Boolean isHavaChild;               //是否有子评论
	private Comment parent;                    //父评论
	private Set<Comment> children = new HashSet<Comment>();  //子评论	
	private Integer love;					   //点赞数
	private Set<User> voter;					//点赞人	
	
	
	public Comment() {
		super();
	}
	

	public Comment(Long id) {
		setId(id);
	}



	
	public Comment(Communication communication, User user, String content,
			Date commentTime, User target, Boolean isHavaChild, Comment parent,
			Set<Comment> children) {
		super();
		this.communication = communication;
		this.user = user;
		this.content = content;
		this.commentTime = commentTime;
		this.target = target;
		this.isHavaChild = isHavaChild;
		this.parent = parent;
		this.children = children;
	}



	@Column(name="love")
	public Integer getLove() {
		return love;
	}
	public void setLove(Integer love) {
		this.love = love;
	}
	@ManyToOne(targetEntity=User.class) 
	@JoinColumn(name="user_id") 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	@OneToOne(targetEntity=User.class) 
	@JoinColumn(name="target_user_id") 
	public User getTarget() {
		return target;
	}
	public void setTarget(User target) {
		this.target = target;
	}

	@Column(name="is_have_child")
	public Boolean getIsHavaChild() {
		return isHavaChild;
	}
	public void setIsHavaChild(Boolean isHavaChild) {
		this.isHavaChild = isHavaChild;
	}


	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	
	@ManyToOne(targetEntity=Communication.class) 
	@JoinColumn(name="communication_id") 
	public Communication getCommunication() {
		return communication;
	}
	public void setCommunication(Communication communication) {
		this.communication = communication;
	}
	
	
	@Column(name="comment_time")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	
	@ManyToOne(targetEntity=Comment.class)
	@JoinColumn(name="parent_id") 	
	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}
	
	
	@OneToMany(targetEntity=Comment.class)
	@JoinColumn(name="parent_id") 
	public Set<Comment> getChildren() {
		return children;
	}

	public void setChildren(Set<Comment> children) {
		this.children = children;
	}

	
	
	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="cy_comment_voter",joinColumns={@JoinColumn(name="comment_id")},
	   inverseJoinColumns={@JoinColumn(name="user_id")})
	public Set<User> getVoter() {
		return voter;
	}


	public void setVoter(Set<User> voter) {
		this.voter = voter;
	}
		

	
	
	
}
