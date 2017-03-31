package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.web.system.entity.User;

/**
 * 稿件
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_manuscript")
public class Manuscript extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5871081899735786016L;
	public static final short FAILED_AUDIT_STATUS = 0;  //审核不通过
	public static final short PASS_AUDIT_STATUS = 1;	//审核通过
	public static final short READY_AUDIT_STATUS = 2;	//预审
	public static final short NORMAL_STATUS = 3;		//正常(不需审核)
	
	private String title;			//标题
	private String description;		//描述
	private Short status;			//状态  0:审核不通过  1：审核通过 2：预审 3：正常(不需审核)
	private Long baseFileId;		//文件
	private Date createTime;   		//投稿时间
	private String author; 			//作者
	private User user;				//投稿人
	private BanChunk banChunk;      //所属版块
	private ThemeActivity activity;	//所属主题活动
	private String updateBy;
	
	private Set<User> voter;	    //点赞人	
	private Integer love;  			//点赞数
	

	
	public Manuscript() {
		super();
	}
	
	public Manuscript(Long id){
		setId(id);
	}
	
	public Manuscript(String title,String author,String description, Short status,
			Date createTime, User user, BanChunk banChunk,
			ThemeActivity activity, String updateBy,Integer love) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.status = status;
		this.createTime = createTime;
		this.user = user;
		this.banChunk = banChunk;
		this.activity = activity;
		this.updateBy = updateBy;
		this.love = love;
	}
		
	
	@Column(name="title",length=50)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="description",length=300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="status",nullable=false)
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	@Column(name="base_file_id",nullable=false)
	public Long getBaseFileId() {
		return baseFileId;
	}
	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	
	@Column(name="create_time",nullable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(targetEntity=BanChunk.class)
	@JoinColumn(name="ban_chunk_id")
	public BanChunk getBanChunk() {
		return banChunk;
	}
	public void setBanChunk(BanChunk banChunk) {
		this.banChunk = banChunk;
	}
	
	@ManyToOne(targetEntity=ThemeActivity.class)
	@JoinColumn(name="activity_id")
	public ThemeActivity getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivity activity) {
		this.activity = activity;
	}
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@Column(name="author",length=50)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	@Column(name="love",length=50)
	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}
	
	
	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="c_manuscript_voter",joinColumns={@JoinColumn(name="manuscript_id")},
	   inverseJoinColumns={@JoinColumn(name="user_id")})
	public Set<User> getVoter() {
		return voter;
	}


	public void setVoter(Set<User> voter) {
		this.voter = voter;
	}
		

	
	
	
}
