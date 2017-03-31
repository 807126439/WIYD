	package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.web.system.entity.User;

/**
 * @author wb_java_lzx
 * 议题
 */

@Entity
@Table(name="cy_communication")
public class Communication extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -5916467502912195930L;
	public static final Short STATUS_ACTIVE = 1;   //活跃
	public static final Short STATUS_FILED = 2;	   //归档
	private String title;       //议题名称
	private String content;     //议题简述
	private String sponsor;     //发起人
	private Date startDate;      //开始时间
	private Date endDate; 		//结束时间
	private Integer love;       //点赞数	
	private Short status;       // 活跃-1 归档-2	
	private Set<User> voter;	//点赞者
	
	
	public Communication() {
		super();
	}

	
	public Communication(Long id) {
		setId(id);
	}
	

	public Communication(String title, String content, String sponsor,
			Date startDate, Date endDate,Short status,Integer love) {
		super();
		this.title = title;
		this.content = content;
		this.sponsor = sponsor;
		this.startDate = startDate;
		this.status = status;
		this.love = love;
	}
	

	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

	@Column(name="title",length=50)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="sponsor",length=20)
	public String getSponsor() {
		return sponsor;
	}
	
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="love",length=10)
	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}
	
	@Column(name="status",length=1)
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	
	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="cy_communication_voter",joinColumns={@JoinColumn(name="communication_id")},
	   inverseJoinColumns={@JoinColumn(name="user_id")})
	public Set<User> getVoter() {
		return voter;
	}


	public void setVoter(Set<User> voter) {
		this.voter = voter;
	}
	
	

	
}
